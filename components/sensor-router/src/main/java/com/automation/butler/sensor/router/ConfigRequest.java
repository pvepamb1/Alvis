package com.automation.butler.sensor.router;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ConfigRequest {

    String mac;
    String ip;
    List<String> ids;
}
