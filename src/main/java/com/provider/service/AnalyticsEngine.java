package com.provider.service;

import com.provider.model.AnalyticsReport;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.List;

/**
 * AnalyticsEngine responsavel por:
 *  - Agregar dados de sensores
 *  - Calcular métricas
 *  - Gerar alertas
 *  - Construir o relatório via Builder
 */
@Service
public class AnalyticsEngine {

    public AnalyticsReport generateReport(String instanceId, List<String> analyticsRequested) {

        AnalyticsReport.Builder builder = new AnalyticsReport.Builder()
                .instanceId(instanceId)
                .generatedAt(Instant.now());

        // *** MOCK DOS VALORES ***
        
        for (String analytic : analyticsRequested) {
            switch (analytic) {
                case "Consumo Mensal (kWh)":
                    builder.addMetric("Consumo Mensal (kWh)", 22.4);
                    break;

                case "Custo Estimado (€)":
                    builder.addMetric("Custo Estimado (€)", 4.51);
                    break;

                case "Alertas de Consumo":
                    builder.alerts(List.of("Nenhum alerta detetado"));
                    break;

                default:
                    builder.addMetric(analytic, "Analytic não reconhecido (mock)");
                    break;
            }
        }

        return builder.build();
    }
}
