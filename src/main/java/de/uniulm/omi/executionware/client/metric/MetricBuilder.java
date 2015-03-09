package de.uniulm.omi.executionware.client.metric;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class MetricBuilder<T extends Serializable> {
    private String applicationName;
    private String metricName;
    private LocalDateTime time = LocalDateTime.now();
    private T value;
    private Map<String, String> tags = new HashMap<>();


    MetricBuilder() {
    }

    public MetricBuilder<T> withApplicationName(String applicationName) {
        this.applicationName = applicationName;
        return this;
    }

    public MetricBuilder<T> withMetricName(String metricName) {
        this.metricName = metricName;
        return this;
    }

    public MetricBuilder<T> withTime(LocalDateTime time) {
        this.time = time;
        return this;
    }

    public MetricBuilder<T> withValue(T value) {
        this.value = value;
        return this;
    }

    public MetricBuilder<T> withTags(Map<String, String> tags) {
        this.tags = tags;
        return this;
    }

    public Metric<T> createMetric() {
        return new Metric<T>(applicationName, metricName, time, value, tags);
    }
}