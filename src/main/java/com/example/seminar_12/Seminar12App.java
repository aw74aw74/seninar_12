package com.example.seminar_12
;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Точка входа в Spring Boot приложение.
 * 
 * Приложение демонстрирует:
 * - Работу с Spring MVC
 * - Использование Thymeleaf как шаблонизатора
 * - Обработку форм и привязку данных
 * - Работу со стилями CSS
 * 
 * Основные компоненты:
 * - HomeController: обработка HTTP запросов
 * - User: модель данных пользователя
 * - Thymeleaf шаблоны: отображение страниц
 * 
 * @see com.example.seminar_12.controller.HomeController
 * @see com.example.seminar_12.model.User
 */
@SpringBootApplication
public class Seminar12App {

    /**
     * Запускает Spring Boot приложение
     * 
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        SpringApplication.run(Seminar12App.class, args);
    }
}
