package com.provider.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class DeployController {

    @PostMapping(path = "/deploy", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> deploy(@RequestBody Map<String, Object> body) {
        try {
            if (body == null || body.isEmpty()) {
                return ResponseEntity.badRequest().body(Map.of("error", "Request body is empty"));
            }

            Object userObj = body.get("user");
            Object homeIdObj = body.get("homeId");
            Object deviceIdObj = body.get("deviceId");

            String user = userObj != null ? userObj.toString() : null;
            String homeId = homeIdObj != null ? homeIdObj.toString() : null;
            String deviceId = deviceIdObj != null ? deviceIdObj.toString() : null;

            if (user == null || user.isBlank()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Map.of("error", "Missing required field: user"));
            }
            if (homeId == null || homeId.isBlank()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(Map.of("error", "Missing required field: homeId"));
            }

            return ResponseEntity.ok(Map.of(
                    "status", "success",
                    "user", user,
                    "homeId", homeId,
                    "deviceId", deviceId != null ? deviceId : "not_provided",
                    "message", "Activity instance deployed (mock)"
            ));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Internal error", "details", ex.getMessage()));
        }
    }
}
