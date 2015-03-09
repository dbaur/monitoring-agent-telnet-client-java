package de.uniulm.omi.executionware.client.metric;

import java.time.LocalDateTime;
import java.util.Map;


public class LongMetric extends Metric<Long> {
    public LongMetric(String applicationName, String metricName, LocalDateTime time, Long value, Map<String, String> tags) {
        super(applicationName, metricName, time, value, tags);
    }

    public static MetricBuilder<Long> build() {
        return new MetricBuilder<>();
    }

}
