package com.automation.sensor;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.annotations.UpdateTimestamp;

import com.automation.embeddable.Sensor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class LDR {

	private Sensor sensor;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private int primaryId;

	@Column(length = 4)
	private String value;

	@UpdateTimestamp
	private LocalDateTime updateDateTime;

	public LDR(Sensor sensor) {
		this.sensor = sensor;
		this.value = sensor.getValue();
	}

}
