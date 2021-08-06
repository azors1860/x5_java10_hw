package ru.chuvashov.course.lesson10.hw10.account.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.chuvashov.course.lesson10.hw10.account.model.exceptions.NotEnoughMoneyException;
import org.springframework.stereotype.Component;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;

/**
 * @author Chuvashov Sergey
 */
@Entity
@Table(name = "account")
@ToString
@Getter
@Setter
@Component
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String holder;
    @Column
    private int amount;

    /**
     * @throws NotEnoughMoneyException В случае если параметр имеет отрицательное значение
     */
    public void setAmount(int amount) throws NotEnoughMoneyException {
        if (amount < 0) {
            throw new NotEnoughMoneyException("Отрицательный баланс");
        }
        this.amount = amount;
    }
}