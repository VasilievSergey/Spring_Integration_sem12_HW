package ru.gb.Spring_Test_HW10.service;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.file.FileHeaders;
import org.springframework.messaging.handler.annotation.Header;

@MessagingGateway(defaultRequestChannel = "textInputChannel")
public interface InterfaceFileGateway {
    void writeToFile(@Header(FileHeaders.FILENAME) String fileName, String data);
}
