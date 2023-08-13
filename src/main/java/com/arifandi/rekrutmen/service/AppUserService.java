package com.arifandi.rekrutmen.service;

import com.arifandi.rekrutmen.domain.UserDetailResponseDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AppUserService extends UserDetailsService {



	public UserDetailResponseDTO findUserDetail();
}
