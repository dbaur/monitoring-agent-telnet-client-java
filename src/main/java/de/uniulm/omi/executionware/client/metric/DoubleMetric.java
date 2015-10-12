package de.uniulm.omi.executionware.client.metric;

import java.time.LocalDateTime;
import java.util.Map;

public class DoubleMetric extends Metric<Double> {
    public DoubleMetric(String applicationName, String metricName, LocalDateTime time, Double value, Map<String, String> tags) {
        super(applicationName, metricName, time, value, tags);
    }

    public static MetricBuilder<Double> build() {
        return new MetricBuilder<>();
    }
}