package com.example.seminar_12.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.seminar_12.model.User;
import com.example.seminar_12.service.RequestLoggingService;

import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import java.time.LocalDateTime;

/**
 * Контроллер для обработки основных веб-запросов приложения.
 * 
 * Обрабатывает следующие URL:
 * - GET  / : главная страница
 * - GET  /greeting : страница приветствия
 * - GET  /register : страница с формой регистрации
 * - POST /register : обработка данных формы регистрации
 * 
 * Использует Thymeleaf шаблоны:
 * - index.html : главная страница с навигацией
 * - greeting.html : страница приветствия
 * - form.html : форма для ввода имени
 * - register.html : форма регистрации пользователя
 * - result.html : страница с результатом регистрации
 * 
 * @see com.example.seminar_12.model.User
 */
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final RequestLoggingService requestLoggingService;

    /**
     * Получает IP адрес клиента из запроса
     */
    private String getClientIp(HttpServletRequest request) {
        String remoteAddr = "";
        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || remoteAddr.isEmpty()) {
                remoteAddr = request.getRemoteAddr();
            }
        }
        return remoteAddr;
    }

    /**
     * Обрабатывает GET-запрос на главную страницу (/).
     * 
     * @return имя представления "index.html"
     */
    @GetMapping("/")
    public String home(HttpServletRequest request) {
        requestLoggingService.logRequest(String.format("Запрос домашней страницы с IP: %s : %s", 
            getClientIp(request), LocalDateTime.now()));
        return "index";
    }

    /**
     * Отображает страницу приветствия
     * 
     * @return имя представления "greeting.html"
     */
    @GetMapping("/greeting")
    public String greeting(Model model, HttpServletRequest request) {
        requestLoggingService.logRequest(String.format("Запрос приветствия с IP: %s : %s", 
            getClientIp(request), LocalDateTime.now()));
        model.addAttribute("name", "Мир");
        return "greeting";
    }

    /**
     * Отображает форму регистрации.
     * 
     * Создает пустой объект User и добавляет его в модель.
     * Thymeleaf использует этот объект для привязки данных формы.
     *
     * @param model модель Spring MVC для передачи данных в представление
     * @return имя представления "register.html"
     */
    @GetMapping("/register")
    public String showRegistrationForm(Model model, HttpServletRequest request) {
        requestLoggingService.logRequest(String.format("Запрос регистрации с IP: %s : %s", 
            getClientIp(request), LocalDateTime.now()));
        // Создаем пустой объект User для привязки данных формы
        model.addAttribute("user", new User());
        return "register";
    }

    /**
     * Обрабатывает отправку формы регистрации.
     * 
     * Поддерживает два шаблона для отображения результата:
     * - result.html: основной шаблон
     *
     * @param user объект с данными пользователя из формы
     * @param model модель для передачи данных в представление
     * @return имя представления "result.html"
     */
    @PostMapping("/register")
    public String processRegistration(@ModelAttribute User user, Model model, HttpServletRequest request) {
        requestLoggingService.logRequest(String.format("Новый юзер добавлен с IP: %s : %s : %s", 
            getClientIp(request), user, LocalDateTime.now()));
        // Добавляем пользователя в модель для отображения введенных данных
        model.addAttribute("user", user);
        return "result";
    }
}
