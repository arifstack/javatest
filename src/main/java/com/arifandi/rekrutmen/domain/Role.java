package com.arifandi.rekrutmen.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
@Table(name = "role")
@Entity
public class Role implements GrantedAuthority{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3535157084126830747L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name", nullable = false)
	private String name;

	@Override
	public String getAuthority() {
		return "ROLE_"+name;
	}
}
