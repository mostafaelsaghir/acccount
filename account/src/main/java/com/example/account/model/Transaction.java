package com.example.account.model;

import com.example.account.entity.TransactionType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    String id ;
    TransactionType transactionType;
    BigDecimal amount;
    LocalDateTime date ;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "account_id", nullable = false)
    Account account;

    public Transaction (BigDecimal amount, LocalDateTime date, Account account){
        this.id = "";
        this.amount = amount;
        this.date = date;
        this.transactionType = TransactionType.INITIAL;
        this.account = account;
    }

    @Override
    public int hashCode(){
        int result = id.hashCode();
        result = 31 * result + transactionType.hashCode();
        result = 31 * result + amount.hashCode();
        result = 31 * result + date.hashCode();
        return result;
    }


    @Override
    public boolean equals(Object otherTransaction){
        Transaction other = (Transaction) otherTransaction;
        if (this == other)
            return true;
        if (!(otherTransaction instanceof Account)) {
            return false;
        }
        if (this.id != other.id)
            return false;
        if (this.transactionType != other.transactionType)
            return false;
        if (this.amount != other.amount)
            return false;
        if (this.date != other.date)
            return false;
        if (this.account != other.account)
            return false;

        return true;
    }

}
