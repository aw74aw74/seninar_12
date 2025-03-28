package com.example.seminar_12.model;

import lombok.Data;

/**
 * Модель данных пользователя.
 * 
 * Класс представляет собой сущность пользователя и используется для:
 * 1. Привязки данных формы регистрации
 * 2. Передачи данных в представление для отображения
 * 
 * Использует Lombok.
 * 
 */
@Data
public class User {
    /**
     * Имя пользователя.
     * Заполняется из поля firstName формы регистрации.
     */
    private String firstName;

    /**
     * Фамилия пользователя.
     * Заполняется из поля lastName формы регистрации.
     */
    private String lastName;

    /**
     * Email пользователя.
     * Заполняется из поля email формы регистрации.
     * В форме используется тип input="email" для базовой валидации.
     */
    private String email;

    /**
     * Пароль пользователя.
     * Заполняется из поля password формы регистрации.
     * В форме используется тип input="password" для скрытия символов.
     */
    private String password;
}
