package com.arifandi.rekrutmen.service.impl;

import com.arifandi.rekrutmen.dto.JobRequestDto;
import com.arifandi.rekrutmen.dto.JobResponseDTO;
import com.arifandi.rekrutmen.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {
    private RestTemplate restTemplate;

    @Autowired
    public JobServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<JobResponseDTO> getJobs() {
        ResponseEntity<JobResponseDTO[]> response = restTemplate.getForEntity(
                "http://dev3.dansmultipro.co.id/api/recruitment/positions.json",
                JobResponseDTO[].class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return Arrays.asList(response.getBody());
        } else {
            throw new RuntimeException("Failed to retrieve jobs");
        }
    }

    public JobResponseDTO findJobDetailById(String jobId, JobRequestDto dto) {
        String apiUrl = "http://dev3.dansmultipro.co.id/api/recruitment/positions/" + jobId;
        ResponseEntity<JobResponseDTO> response = restTemplate.getForEntity(apiUrl, JobResponseDTO.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            throw new RuntimeException("Failed to retrieve job detail");
        }
    }
}
