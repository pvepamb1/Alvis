package com.alvis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.support.DatabaseStartupValidator;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.io.File;
import java.util.stream.Stream;

@Slf4j
@EnableScheduling
@SpringBootApplication
public class AlvisService {

	private static final String CONFIG_DIR = System.getenv("HOME") + "/.alvis/config/";
	
	public static void main(String[] args) {

		// Config dir is needed only when running on host to config props. Container-runs use packaged props.
		if(!isDockerInstance() && !validateConfigDir()) {
            log.error("Config directory error: {}", CONFIG_DIR);
		}
		else {
			//load config based on instance host
			new SpringApplicationBuilder(AlvisService.class)
					.properties(isDockerInstance()?"":"spring.config.location:classpath:/," + CONFIG_DIR)
					.build()
					.run(args);
		}
	}

	@Bean
	public static BeanFactoryPostProcessor dependsOnPostProcessor() {
		return bf -> {
			// Let beans that need the database depend on the DatabaseStartupValidator
			// like the JPA EntityManagerFactory or Flyway
			String[] jpa = bf.getBeanNamesForType(EntityManagerFactory.class);
			Stream.of(jpa)
					.map(bf::getBeanDefinition)
					.forEach(it -> it.setDependsOn("databaseStartupValidator"));
		};
	}

	@Bean
	public DatabaseStartupValidator databaseStartupValidator(DataSource dataSource) {
		DatabaseStartupValidator dsv = new DatabaseStartupValidator();
		dsv.setDataSource(dataSource);
		return dsv;
	}

	//create all the necessary dirs if non-existent
	private static boolean validateConfigDir() {
		File folder = new File(CONFIG_DIR);
		if(!folder.exists()) {
			log.info("Creating Config directory..");
			return folder.mkdirs();
		}
		log.info("Config directory found!");
		return true;
	}

	// identify if the app is running on a container like docker
	private static boolean isDockerInstance(){
		String containerType = System.getenv("CONTAINER");
		return "docker".equals(containerType);
	}
}
