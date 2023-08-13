package com.arifandi.rekrutmen.service;

import com.arifandi.rekrutmen.dto.JobRequestDto;
import com.arifandi.rekrutmen.dto.JobResponseDTO;

import java.util.List;

public interface JobService {

    public List<JobResponseDTO> getJobs();

    public JobResponseDTO findJobDetailById(String jobId, JobRequestDto dto);
}
