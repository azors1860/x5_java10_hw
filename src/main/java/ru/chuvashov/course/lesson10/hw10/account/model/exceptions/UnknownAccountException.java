package ru.chuvashov.course.lesson10.hw10.account.model.exceptions;

/**
 * @author Chuvashov Sergey
 * Выбрасывается, чтобы указать, что аккаунт не найден.
 */
public class UnknownAccountException extends Exception {
    public UnknownAccountException(String message) {
        super(message);
    }

    public UnknownAccountException(String message, Throwable e) {
        super(message, e);
    }
}
