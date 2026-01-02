package com.provider.interfaces;

import com.provider.model.AnalyticsReport;
import java.util.Map;

public interface ReportAdapter {
    Map<String, Object> adapt(AnalyticsReport report);
}
