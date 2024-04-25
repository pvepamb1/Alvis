package com.alvis.ldr;

import com.alvis.sensor.Sensor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
class LdrDTO extends Sensor {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private int primaryId;

    @Column(length = 4)
	private int value;
	
	@UpdateTimestamp
	private LocalDateTime updateDateTime;
	
}
