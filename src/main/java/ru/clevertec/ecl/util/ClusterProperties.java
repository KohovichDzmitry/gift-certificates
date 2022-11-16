package ru.clevertec.ecl.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@ConfigurationProperties(prefix = "cluster")
@Getter
@RequiredArgsConstructor
public class ClusterProperties {

    private final Map<Integer, Map<Integer, String>> shards;
    private final Map<Integer, String> allNodes;
}
