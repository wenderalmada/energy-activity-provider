package com.provider.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;

import java.util.Map;

@Controller
public class ConfigController {

    @GetMapping("/activity-info")
    public Map<String, Object> getActivityInfo() {
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

    @GetMapping(value = "/config", produces = MediaType.TEXT_HTML_VALUE)
    public String getConfigPage() {
        return "config.html";
    }

}
