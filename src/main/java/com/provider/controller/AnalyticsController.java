package com.provider.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AnalyticsController {

    @SuppressWarnings("unchecked")
    @PostMapping("/analytics")
    public Map<String, Object> getAnalytics(@RequestBody Map<String, Object> body) {

        String instanceId = body.get("instanceId") != null ? body.get("instanceId").toString() : "unknown_instance";
        List<String> requestedAnalytics = (List<String>) body.get("analytics");

        Map<String, Object> response = new HashMap<>();

        if (requestedAnalytics == null || requestedAnalytics.isEmpty()) {
            response.put("error", "No analytics requested");
            return response;
        }

        for (String analytic : requestedAnalytics) {
            switch (analytic) {
                case "Consumo Mensal (kWh)":
                    // exemplo
                    response.put("Consumo Mensal (kWh)", 22.4);
                    break;
                case "Custo Estimado (€)":
                    response.put("Custo Estimado (€)", 4.51);
                    break;
                case "Alertas de Consumo":
                    response.put("Alertas de Consumo", "Nenhum alerta detetado para a instância " + instanceId);
                    break;
                default:
                    response.put(analytic, "Analytic não reconhecido (mock)");
            }
        }

        return response;
    }
}
