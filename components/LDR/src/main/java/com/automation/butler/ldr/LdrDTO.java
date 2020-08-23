package com.automation.butler.ldr;

import com.automation.butler.sensor.Sensor;
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
@AttributeOverride(name = "value", column = @Column(length = 4))
class LdrDTO extends Sensor {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private int primaryId;

	@Column(length = 5)
	private int value;
	
	@UpdateTimestamp
	private LocalDateTime updateDateTime;
	
}
