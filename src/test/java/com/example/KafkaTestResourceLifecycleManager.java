package com.example;

import static com.example.MyReactiveMessagingApplication.INBOUND_CHANNEL;
import static com.example.MyReactiveMessagingApplication.OUTBOUND_CHANNEL;

import java.util.HashMap;
import java.util.Map;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import io.smallrye.reactive.messaging.memory.InMemoryConnector;

public class KafkaTestResourceLifecycleManager implements QuarkusTestResourceLifecycleManager {

    @Override
    public Map<String, String> start() {
        Map<String, String> channels = new HashMap<>(8);
        channels.putAll(InMemoryConnector.switchIncomingChannelsToInMemory(INBOUND_CHANNEL));
        channels.putAll(InMemoryConnector.switchOutgoingChannelsToInMemory(OUTBOUND_CHANNEL));
        return channels;
    }

    @Override
    public void stop() {
        InMemoryConnector.clear();
    }
}
