package com.provider.model;

import java.time.Instant;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class AnalyticsReport {

    private final String instanceId;
    private final Instant generatedAt;
    private final Map<String, Object> metrics;
    private final List<String> alerts;
    private final String period;

    private AnalyticsReport(Builder b) {
        this.instanceId = b.instanceId;
        this.generatedAt = b.generatedAt != null ? b.generatedAt : Instant.now();
        this.metrics = Map.copyOf(b.metrics != null ? b.metrics : new HashMap<>());
        this.alerts = b.alerts != null ? List.copyOf(b.alerts) : Collections.emptyList();
        this.period = b.period != null ? b.period : "mensal";
    }

    public String getInstanceId() { return instanceId; }
    public Instant getGeneratedAt() { return generatedAt; }
    public Map<String, Object> getMetrics() { return metrics; }
    public List<String> getAlerts() { return alerts; }
    public String getPeriod() { return period; }

    public Map<String, Object> toMap() {
        Map<String, Object> m = new HashMap<>();
        m.put("instanceId", instanceId);
        m.put("generatedAt", generatedAt.toString());
        m.put("metrics", metrics);
        m.put("alerts", alerts);
        m.put("period", period);
        return m;
    }


    public static class Builder {
        private String instanceId;
        private Instant generatedAt;
        private Map<String, Object> metrics = new HashMap<>();
        private List<String> alerts = Collections.emptyList();
        private String period = "mensal";

        public Builder instanceId(String id) {
            this.instanceId = id;
            return this;
        }

        public Builder generatedAt(Instant at) {
            this.generatedAt = at;
            return this;
        }

        public Builder addMetric(String name, Object value) {
            this.metrics.put(name, value);
            return this;
        }

        public Builder metrics(Map<String, Object> m) {
            if (m != null) this.metrics.putAll(m);
            return this;
        }

        public Builder alerts(List<String> a) {
            if (a != null) this.alerts = a;
            return this;
        }

        public Builder period(String p) {
            if (p != null) this.period = p;
            return this;
        }

        public AnalyticsReport build() {
            if (this.instanceId == null || this.instanceId.isBlank()) {
                throw new IllegalStateException("instanceId is required");
            }
            return new AnalyticsReport(this);
        }
    }
}
