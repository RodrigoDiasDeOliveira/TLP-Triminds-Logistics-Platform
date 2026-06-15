package com.triminds.tlp.analytics.controller;

import com.triminds.tlp.analytics.dto.DashboardDTO;
import com.triminds.tlp.analytics.service.AnalyticsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    public AnalyticsController(
            AnalyticsService analyticsService) {

        this.analyticsService = analyticsService;
    }

    @GetMapping()
    public DashboardDTO dashboard() {
        return analyticsService.getDashboard();
    }
} 