package com.arifandi.rekrutmen.controller;

import com.arifandi.rekrutmen.dto.JobRequestDto;
import com.arifandi.rekrutmen.dto.JobResponseDTO;
import com.arifandi.rekrutmen.service.JobService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
public class JobController {

    private final JobService jobService;

    @GetMapping("/v1/job")
    public ResponseEntity<List<JobResponseDTO>> findJobList(){
        return ResponseEntity.ok().body(jobService.getJobs());

    }

    @GetMapping("/v1/job/detail")
    public ResponseEntity<JobResponseDTO> findBookDetail(@RequestBody String jobId, JobRequestDto dto) {
        log.info("start findJobDetail "+dto);
        JobResponseDTO result =  jobService.findJobDetailById(jobId,dto);
        return ResponseEntity.ok(result);
    }



}
