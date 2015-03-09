package de.uniulm.omi.executionware.client.metric;

import java.time.LocalDateTime;
import java.util.Map;


public class StringMetric extends Metric<String> {

    public StringMetric(String applicationName, String metricName, LocalDateTime time, String value, Map<String, String> tags) {
        super(applicationName, metricName, time, value, tags);
    }

    public static MetricBuilder<String> build() {
        return new MetricBuilder<>();
    }

}
