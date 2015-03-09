package de.uniulm.omi.executionware.client;

import de.uniulm.omi.executionware.client.metric.IntMetric;
import de.uniulm.omi.executionware.client.metric.StringMetric;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;


public class Example {
    private static final Logger LOGGER = LoggerFactory.getLogger(Example.class);
    private static final String APP_NAME = "ExampleApp";
    private static final Random RANDOM = new Random();

    public static void main(String[] args) throws InterruptedException {


        MetricClient metricClient = null;
        try {
            //Create MetricClient with connection pool
            metricClient = new MetricClient(10, "localhost", 9001);

            for (int i = 0; i < 10; i++) {
                metricClient.report(createRandomMetric());
                Thread.sleep(100);
            }

        } catch (Exception exception) {
            LOGGER.error("Error while executing example.", exception);
        } finally {
            if (metricClient != null) {
                metricClient.close();
            }
        }
    }

    private static AgentMetric createRandomMetric() {
        if (RANDOM.nextInt() % 2 == 0) {
            return IntMetric.build()
                    .withMetricName("IntSampleMetric" + RANDOM.nextInt())
                    .withApplicationName(APP_NAME)
                    .withValue(RANDOM.nextInt())
                    .createMetric();
        } else {
            return StringMetric.build()
                    .withMetricName("StringSampleMetric" + RANDOM.nextInt())
                    .withApplicationName(APP_NAME)
                    .withValue("value" + String.valueOf(RANDOM.nextDouble()))
                    .createMetric();
        }

    }
}
