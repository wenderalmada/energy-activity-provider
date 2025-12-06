package com.provider.controller;

import com.provider.model.AnalyticsReport;
import com.provider.service.AnalyticsEngine;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;


@RestController
public class AnalyticsController {

    private final AnalyticsEngine engine;

    public AnalyticsController(AnalyticsEngine engine) {
        this.engine = engine;
    }

    @SuppressWarnings("unchecked")
    @PostMapping("/analytics")
    public Map<String, Object> getAnalytics(@RequestBody Map<String, Object> body) {
        String instanceId = body.getOrDefault("instanceId","unknown").toString();
        List<String> requested = (List<String>) body.get("analytics");
        AnalyticsReport report = engine.generateReport(instanceId, requested);
        return report.toMap();
    }
}