package com.arifandi.rekrutmen.service.impl;

import com.arifandi.rekrutmen.domain.UserDetailResponseDTO;
import com.arifandi.rekrutmen.exception.ResourceNotFoundException;
import com.arifandi.rekrutmen.repository.AppUserRepository;
import com.arifandi.rekrutmen.service.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AppUserServiceImpl implements AppUserService {

	private AppUserRepository appUserRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return appUserRepository.findByUsername(username)
				.orElseThrow(() -> new ResourceNotFoundException("invalid.username"));
	}

	@Override
	public UserDetailResponseDTO findUserDetail() {
		SecurityContext ctx = SecurityContextHolder.getContext();
		UserDetailResponseDTO dto = new UserDetailResponseDTO();
		String username = ctx.getAuthentication().getName();
		dto.setUsername(username);
		return dto;
	}

}
