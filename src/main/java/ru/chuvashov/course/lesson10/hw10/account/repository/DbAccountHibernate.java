package ru.chuvashov.course.lesson10.hw10.account.repository;

import lombok.NonNull;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import ru.chuvashov.course.lesson10.hw10.account.model.Account;
import ru.chuvashov.course.lesson10.hw10.account.model.exceptions.UnknownAccountException;
import ru.chuvashov.course.lesson10.hw10.account.repository.exception.DbHibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;


/**
 * @author Chuvashov Sergey
 */
@org.springframework.stereotype.Repository
public class DbAccountHibernate implements Repository<Account> {

    private static SessionFactory sessionFactory;

    public DbAccountHibernate() {
        initialization();
    }

    @Override
    public void create(@NonNull Account item) throws DbHibernateException {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.save(item);
            transaction.commit();
            session.close();
        } catch (Exception e) {
            throw new DbHibernateException("Error creating the accounts", e);
        }
    }

    @Override
    public void update(@NonNull Account item) throws DbHibernateException, UnknownAccountException {
        if (read(item.getId()) != null) {
            try {
                Session session = sessionFactory.openSession();
                Transaction transaction = session.beginTransaction();
                session.update(item);
                transaction.commit();
                session.close();
            } catch (Exception e) {
                throw new DbHibernateException("Error updating the accounts", e);
            }
        }
    }

    @Override
    public Account read(int id) throws DbHibernateException, UnknownAccountException {

        if (id < 1) {
            throw new UnknownAccountException("Некорректный идентификатор акаауета на входе: " + id);
        }
        Account result;
        try {
            Session session = sessionFactory.openSession();
            result = session.get(Account.class, id);
            session.close();
        } catch (Exception e) {
            throw new DbHibernateException("Error reading the accounts", e);
        }
        if (result == null) {
            throw new UnknownAccountException("Произошла ошибка при получении объекта с указанным id: " + id);
        }
        return result;
    }

    @Override
    public void delete(@NonNull Account item) throws DbHibernateException, UnknownAccountException {

        if (read(item.getId()) != null) {
            try {
                Session session = sessionFactory.openSession();
                Transaction transaction = session.beginTransaction();
                session.delete(item);
                transaction.commit();
                session.close();
            } catch (Exception e) {
                throw new DbHibernateException("Error reading the accounts", e);
            }
        }
    }

    @Override
    public List<Account> getListAccounts() throws DbHibernateException {
        List<Account> accounts = null;
        try {
            Session session = sessionFactory.openSession();
            accounts = (List<Account>) session.createQuery("From Account").list();
            session.close();
        } catch (Exception e) {
            throw new DbHibernateException("Error reading the accounts", e);
        }
        if (accounts == null) {
            throw new DbHibernateException("Error reading the accounts");
        } else {
            return accounts;
        }
    }

    /**
     * Инициализирует поле 'sessionFactory'.
     */
    private void initialization() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration().configure();
            configuration.addAnnotatedClass(Account.class);
            StandardServiceRegistryBuilder builder =
                    new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            sessionFactory = configuration.buildSessionFactory(builder.build());
        }
    }
}




