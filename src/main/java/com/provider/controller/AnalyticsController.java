package com.provider.controller;

import com.provider.interfaces.ReportAdapter;
import com.provider.model.AnalyticsReport;
import com.provider.service.AnalyticsEngine;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class AnalyticsController {

    private final AnalyticsEngine engine;
    private final ReportAdapter adapter;

    public AnalyticsController(AnalyticsEngine engine, ReportAdapter adapter) {
        this.engine = engine;
        this.adapter = adapter;
    }

    @PostMapping("/analytics")
    public Map<String, Object> getAnalytics(@RequestBody Map<String, Object> body) {
        String instanceId = body.get("instanceId").toString();

        @SuppressWarnings("unchecked")
        List<String> analyticsRequested = (List<String>) body.get("analytics");

        AnalyticsReport report = engine.generateReport(instanceId, analyticsRequested);

        return adapter.adapt(report);
    }
}
