package ru.gb.Spring_Test_HW10.configuration;

import org.springframework.boot.autoconfigure.ssl.SslProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.GenericTransformer;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.messaging.MessageChannel;

import java.io.File;

@Configuration
public class IntegrationConfig {
    @Bean
    public MessageChannel textInputChannel(){
        return new DirectChannel();
    }
    @Bean
    public MessageChannel fileWriterChannel(){
        return new DirectChannel();
    }
    @Bean
    @Transformer(inputChannel = "textInputChannel",outputChannel = "fileWriterChannel")
    public GenericTransformer<String, String> mainTransformer(){
        return text -> {text = text.toLowerCase();
             return text;
        };
    }
    @Bean
    @ServiceActivator(inputChannel = "fileWriterChannel")
    public FileWritingMessageHandler myHandler(){
        FileWritingMessageHandler handler = new FileWritingMessageHandler(new File("src/main/resources/resources_project/"));
        handler.setExpectReply(false);
        handler.setFileExistsMode(FileExistsMode.APPEND);
        handler.setAppendNewLine(true);
        return handler;
    }

}
