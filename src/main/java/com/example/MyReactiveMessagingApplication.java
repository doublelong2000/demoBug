package com.example;

import org.apache.cxf.helpers.IOUtils;
import org.eclipse.microprofile.reactive.messaging.*;

import jakarta.enterprise.context.ApplicationScoped;

import java.io.IOException;

@ApplicationScoped
public class MyReactiveMessagingApplication {

    public static final String INBOUND_CHANNEL = "test-in";
    public static final String OUTBOUND_CHANNEL = "test-out";

    @Incoming(INBOUND_CHANNEL)
    @Outgoing(OUTBOUND_CHANNEL)
    public ExampleObject processMessage(ExampleObject message) {
        ExampleObject newMessage = message;
        return newMessage;
    }

    public static String getFileContent(String fileName) {
        try {
            var inputStream = Thread.currentThread()
                    .getContextClassLoader()
                    .getResourceAsStream(fileName);
            assert inputStream != null;
            return IOUtils.toString(inputStream);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
