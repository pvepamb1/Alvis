package com.alvis.deviceaddress;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true, value = "updateDateTime")
public class DeviceAddress {

	@Id
	@NotNull
	@Column(length = 17)
	private String mac;

	@Column(length = 15)
	private String ip;

	@UpdateTimestamp
	private LocalDateTime updateDateTime;

    public DeviceAddress(@NotNull String mac) {
        this.mac = mac;
    }
}
