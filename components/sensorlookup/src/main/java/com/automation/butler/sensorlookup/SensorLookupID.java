package com.automation.butler.sensorlookup;

import com.automation.butler.deviceaddress.DeviceAddress;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class SensorLookupID implements Serializable {

	private static final long serialVersionUID = -7769334084663720925L;

	@ManyToOne
	@JoinColumn
    @JsonView(SensorLookupViews.Address.class)
	private DeviceAddress address;

	@Column(length = 5)
    @JsonView(SensorLookupViews.Id.class)
	private String id;

}
