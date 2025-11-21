package com.provider.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class DeployController {

    @PostMapping("/deploy")
    public Map<String, Object> deploy(@RequestBody Map<String, Object> body) {

        String userId = body.get("userId") != null ? body.get("userId").toString() : "unknownUser";
        String homeId = body.get("homeId") != null ? body.get("homeId").toString() : "unknownHome";
        String deviceId = body.get("deviceId") != null ? body.get("deviceId").toString() : "unknownDevice";

        String instanceId = homeId + "_" + deviceId;

        return Map.of(
            "status", "success",
            "message", "Activity deployed successfully",
            "instanceId", instanceId,
            "userId", userId,
            "parameters", body.get("parameters")
        );
    }
}
