package com.arifandi.rekrutmen.repository;

import com.arifandi.rekrutmen.domain.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
	
	public Optional<AppUser> findByUsername(String username);

}
