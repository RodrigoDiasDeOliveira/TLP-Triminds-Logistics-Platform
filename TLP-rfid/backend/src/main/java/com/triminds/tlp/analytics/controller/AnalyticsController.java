package com.triminds.tlp.analytics.controller;

import com.triminds.tlp.analytics.dto.DashboardDTO;
import com.triminds.tlp.analytics.service.DashboardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
public class AnalyticsController {

    private final DashboardService dashboardService;

    public AnalyticsController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping
    public DashboardDTO dashboard() {
        return dashboardService.buildDashboard();
    }
}
