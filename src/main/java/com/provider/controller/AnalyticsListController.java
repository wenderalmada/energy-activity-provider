package com.provider.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class AnalyticsListController {

    @GetMapping("/analytics-list")
    public Map<String, Object> getAnalyticsList() {

        return Map.of(
            "qualAnalytics", List.of(
                Map.of(
                    "name", "Alertas de Consumo",
                    "type", "text/plain"
                )
            ),
            "quantAnalytics", List.of(
                Map.of(
                    "name", "Consumo Mensal (kWh)",
                    "type", "float"
                ),
                Map.of(
                    "name", "Custo Estimado (€)",
                    "type", "float"
                )
            )
        );
    }
}
