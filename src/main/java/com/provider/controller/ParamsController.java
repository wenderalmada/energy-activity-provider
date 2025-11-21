package com.provider.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class ParamsController {

    @GetMapping("/params")
    public List<Map<String, Object>> getParams() {

        return List.of(
            Map.of(
                "name", "periodo_analise",
                "type", "text/plain",
                "description", "Define o período da análise (\"mensal\" ou \"anual\")"
            ),
            Map.of(
                "name", "integracao_solar",
                "type", "boolean",
                "description", "Ativa ou desativa a integração com os painéis solares"
            ),
            Map.of(
                "name", "gerar_relatorio",
                "type", "boolean",
                "description", "Indica se o utente pretende gerar relatório"
            )
        );
    }
}
