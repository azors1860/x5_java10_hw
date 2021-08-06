package ru.chuvashov.course.lesson10.hw10.account.repository.exception;

/**
 * @author Chuvashov Sergey
 * Выбрасывается, чтобы указать что, возникли проблемы с чтением информации из БД, либо при записи информации в БД.
 */
public class DbHibernateException extends Exception {

    public DbHibernateException(String message, Throwable e) {
        super(message, e);
    }

    public DbHibernateException(String message) {
        super(message);
    }
}
