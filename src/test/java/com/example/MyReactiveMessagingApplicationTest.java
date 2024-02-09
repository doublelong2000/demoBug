package com.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.runtime.StartupEvent;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import io.quarkus.test.junit.QuarkusTest;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.junit.jupiter.api.Test;

import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;

import static com.example.MyReactiveMessagingApplication.INBOUND_CHANNEL;
import static com.example.MyReactiveMessagingApplication.getFileContent;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
@QuarkusTestResource(KafkaTestResourceLifecycleManager.class)
class MyReactiveMessagingApplicationTest {

    @Inject
    MyReactiveMessagingApplication application;

    @Test
    void test() throws JsonProcessingException {
        String testJsonObject = getFileContent("message/exampleObject.json");
        ExampleObject obj = (new ObjectMapper()).readValue(testJsonObject, ExampleObject.class);
        ExampleObject testObj = application.processMessage(obj);
        assertEquals(obj, testObj);
    }
}
