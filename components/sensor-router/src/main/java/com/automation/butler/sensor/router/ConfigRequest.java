package com.automation.butler.sensor.router;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ConfigRequest {

    String mac;
    String ip;
    List<String> ids;
}
