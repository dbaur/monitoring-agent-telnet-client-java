package de.uniulm.omi.executionware.client;

import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class MetricClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(MetricClient.class);
    private final ExecutorService executorService;
    private final Queue<AgentMetric> queue;

    /**
     * Create MetricClient with connection pool
     *
     * @param connectionPool number of connections in the pool
     * @param host           host address metric agent
     * @param port           port of metric agent
     */
    public MetricClient(int connectionPool, String host, int port) {
        Preconditions.checkArgument(connectionPool > 0);
        Preconditions.checkNotNull(host);

        this.executorService = Executors.newFixedThreadPool(connectionPool);
        this.queue = new ConcurrentLinkedQueue<>();

        for (int i = 0; i < connectionPool; i++) {
            executorService.submit(new AgentConnection(host, port, queue));
        }
    }

    public void report(AgentMetric agentMetric) {
        queue.add(agentMetric);
    }

    public void close() {
        try {
            executorService.shutdownNow();
            executorService.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException exception) {
            LOGGER.error("Error while closing client.", exception);
        }
    }

}
