package ru.chuvashov.course.lesson10.hw10.account.servlet.exceptions;

import javax.servlet.ServletException;

/**
 * @author Chuvashov Sergey
 * Выбрасывается, чтобы указать что, возникли проблемы при операциях в сервлете.
 */
public class AccountsServletException extends ServletException {
    public AccountsServletException(String message) {
        super(message);
    }

    public AccountsServletException(String message, Throwable rootCause) {
        super(message, rootCause);
    }
}
