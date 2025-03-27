package com.example.seminar_12.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.integration.transformer.ObjectToStringTransformer;
import org.springframework.messaging.MessageChannel;

import java.io.File;

/**
 * Конфигурация Spring Integration для сохранения запросов в файл
 * 
 * Компоненты конфигурации:
 * 1. requestChannel - входной канал для сообщений
 * 2. transformer - преобразует объекты в строки
 * 3. fileChannel - канал для подготовленных к записи сообщений
 * 4. fileWriter - записывает сообщения в файл history.log
 * 
 * Процесс обработки сообщения:
 * requestChannel -> transformer -> fileChannel -> fileWriter -> history.log
 */
@Configuration
public class IntegrationConfig {

    private static final String LOG_FILE = "history.log";

    /**
     * Канал для отправки сообщений на запись
     * Принимает сообщения от RequestLoggingService
     * @return DirectChannel для синхронной передачи сообщений
     */
    @Bean
    public MessageChannel requestChannel() {
        return new DirectChannel();
    }

    /**
     * Канал для преобразованных сообщений
     * Принимает сообщения после трансформации в строку
     * @return DirectChannel для синхронной передачи сообщений
     */
    @Bean
    public MessageChannel fileChannel() {
        return new DirectChannel();
    }

    /**
     * Преобразователь объектов в строки
     * Конвертирует входящие объекты в текстовый формат для записи
     * @return ObjectToStringTransformer для преобразования сообщений
     */
    @Bean
    @Transformer(inputChannel = "requestChannel", outputChannel = "fileChannel")
    public ObjectToStringTransformer transformer() {
        return new ObjectToStringTransformer();
    }

    /**
     * Обработчик для записи сообщений в файл
     * Настройки:
     * - Запись в единый файл history.log
     * - Режим добавления (append) для сохранения истории
     * - Автоматический перенос строки после каждой записи
     * @return FileWritingMessageHandler для записи сообщений в файл
     */
    @Bean
    @ServiceActivator(inputChannel = "fileChannel")
    public FileWritingMessageHandler fileWriter() {
        FileWritingMessageHandler handler = new FileWritingMessageHandler(new File("."));
        handler.setFileNameGenerator(message -> LOG_FILE);
        handler.setExpectReply(false);
        handler.setFileExistsMode(FileExistsMode.APPEND);
        handler.setAppendNewLine(true);
        return handler;
    }
}
