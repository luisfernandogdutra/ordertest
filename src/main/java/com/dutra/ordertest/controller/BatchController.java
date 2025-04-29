package com.dutra.ordertest.controller;

import com.dutra.ordertest.service.BatchLauncherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/batch")
public class BatchController {

    private final BatchLauncherService batchLauncherService;
    private static final Logger logger = LoggerFactory.getLogger(BatchController.class);

    public BatchController(BatchLauncherService batchLauncherService) {
        this.batchLauncherService = batchLauncherService;
    }

    @GetMapping("/start")
    public ResponseEntity<String> startBatch() throws Exception {
        logger.info("request to start batch processing received");
        batchLauncherService.launchBatch();
        return ResponseEntity.ok("batch process started");
    }
}

