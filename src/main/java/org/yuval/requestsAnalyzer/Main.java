package org.yuval.requestsAnalyzer;

import org.yuval.requestsAnalyzer.handlers.HandlerFactory;
import org.yuval.requestsAnalyzer.handlers.ResourceHandler;
import org.yuval.requestsAnalyzer.handlers.ResourceType;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) {
        try (InputStream is = Main.class.getClassLoader().getResourceAsStream("apache_log.txt")) {
            if (is == null) {
                throw new IllegalStateException("apache_log.txt not found");
            }

            String       logContent   = new String(is.readAllBytes(), StandardCharsets.UTF_8);
            ResourceType resourceType = ResourceType.APACHE;

            ResourceHandler handler = HandlerFactory.createHandler(resourceType);
            handler.printRequestsStatistics(logContent);
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}