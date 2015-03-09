package de.uniulm.omi.executionware.client.metric;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author mciolek
 */
public class IntMetric extends Metric<Integer> {
    public IntMetric(String applicationName, String metricName, LocalDateTime time, Integer value, Map<String, String> tags) {
        super(applicationName, metricName, time, value, tags);
    }

    public static MetricBuilder<Integer> build() {
        return new MetricBuilder<>();
    }
}
