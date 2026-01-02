package com.provider.adapter;

import com.provider.interfaces.ReportAdapter;
import com.provider.model.AnalyticsReport;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class AnalyticsReportAdapter implements ReportAdapter {

    @Override
    public Map<String, Object> adapt(AnalyticsReport report) {
        Map<String, Object> m = new HashMap<>();

        m.put("instanceId", report.getInstanceId());
        m.put("generatedAt", report.getGeneratedAt().toString());
        m.put("metrics", report.getMetrics());
        m.put("alerts", report.getAlerts());
        m.put("period", report.getPeriod());

        return m;
    }
}
