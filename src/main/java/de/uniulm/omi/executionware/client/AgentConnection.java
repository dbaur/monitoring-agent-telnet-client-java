package de.uniulm.omi.executionware.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Queue;


public class AgentConnection implements Runnable {
    private final Logger LOGGER = LoggerFactory.getLogger(AgentConnection.class);
    private final String host;
    private final int port;
    private final Queue<AgentMetric> queue;

    public AgentConnection(String host, int port, Queue<AgentMetric> queue) {
        this.host = host;
        this.port = port;
        this.queue = queue;
    }

    @Override
    public void run() {
        Socket socket = null;
        try {
            socket = new Socket(host, port);

            while (!Thread.currentThread().isInterrupted()) {
                AgentMetric metric = queue.poll();

                if (metric == null) {
                    continue;
                }

                if (!socket.isConnected() || socket.isClosed()) {
                    socket = new Socket(host, port);
                }

                DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
                outputStream.writeBytes(metric.toTCPMessage());
            }

        } catch (IOException exception) {
            LOGGER.error("Error while creating socket.", exception);
        } finally {
            if (socket != null && !socket.isClosed()) {
                try {
                    socket.close();
                } catch (IOException exception) {
                    LOGGER.error("Error while closing socket.", exception);
                }
            }
        }
    }

}
