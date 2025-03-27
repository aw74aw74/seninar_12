package com.example.seminar_12.service;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.stereotype.Service;

/**
 * Сервис для логирования запросов через Spring Integration
 * 
 * Интерфейс использует Spring Integration Messaging Gateway для автоматической
 * отправки сообщений в канал requestChannel.
 * 
 * Формат сообщений:
 * - "Запрос домашней страницы с IP: {ip} : {timestamp}"
 * - "Запрос приветствия с IP: {ip} : {timestamp}"
 * - "Запрос регистрации с IP: {ip} : {timestamp}"
 * - "Новый юзер добавлен с IP: {ip} : {user} : {timestamp}"
 * 
 * Все сообщения автоматически сохраняются в файл history.log
 */
@Service
@MessagingGateway(defaultRequestChannel = "requestChannel")
public interface RequestLoggingService {
    
    /**
     * Отправляет запрос на сохранение в лог
     * 
     * @param request объект запроса для сохранения
     *               может быть строкой с информацией о запросе
     *               или любым объектом, который будет преобразован в строку
     */
    void logRequest(Object request);
}
