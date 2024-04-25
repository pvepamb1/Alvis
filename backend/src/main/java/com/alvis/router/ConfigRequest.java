package com.alvis.router;

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
