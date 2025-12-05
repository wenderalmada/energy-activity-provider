package com.provider.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ConfigController {

    @GetMapping("/config")
    public Map<String, Object> getConfig() {
        //https://energy-provider.com
        String baseUrl = "https://energy-activity-provider-96zq.onrender.com";

        return Map.of(
            "name", "EnergyConsumptionManager",
            "config_url", baseUrl + "/config",
            "json_params_url", baseUrl + "/params",
            "user_url", baseUrl + "/deploy",
            "analytics_url", baseUrl + "/analytics",
            "analytics_list_url", baseUrl + "/analytics-list"
        );
    }
}
