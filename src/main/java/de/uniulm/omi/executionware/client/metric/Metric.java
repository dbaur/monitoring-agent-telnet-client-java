package de.uniulm.omi.executionware.client.metric;

import com.google.common.base.Preconditions;
import de.uniulm.omi.executionware.client.AgentMetric;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.Map;


public class Metric<T extends Serializable> implements AgentMetric {
    private final String applicationName;
    private final String metricName;
    private final LocalDateTime time;
    private final T value;
    private final Map<String, String> tags;

    Metric(String applicationName, String metricName, LocalDateTime time, T value, Map<String, String> tags) {
        Preconditions.checkNotNull(metricName);
        Preconditions.checkNotNull(time);
        Preconditions.checkNotNull(value);
        Preconditions.checkNotNull(tags);

        this.applicationName = applicationName;
        this.metricName = metricName;
        this.time = time;
        this.value = value;
        this.tags = tags;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public String getMetricName() {
        return metricName;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public T getValue() {
        return value;
    }

    public Map<String, String> getTags() {
        return Collections.unmodifiableMap(tags);
    }

    @Override
    public String toTCPMessage() {
        //TODO: Add support for tags
        return applicationName + " "
                + metricName + " "
                + value + " "
                + time.toInstant(ZoneOffset.UTC).getEpochSecond() + "\n";
    }


}
