package com.automation.butler.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	
	@Id
	@NotEmpty
	@Column(length = 30)
	private String name;
	
	@NotEmpty
	@Column(length = 70)
	private String email;
	
	@NotEmpty
	@Column(length = 20)
	private String password;
	
}
