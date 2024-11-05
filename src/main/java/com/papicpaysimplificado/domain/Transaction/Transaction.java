package com.papicpaysimplificado.domain.Transaction;

import com.papicpaysimplificado.domain.user.User;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity(name = "transactions")
@Table(name = "tb_transactions")
public class Transaction extends PanacheEntityBase {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;


    public BigDecimal amount;


    public String notifySender;


    public String notifyReceiver;


    @ManyToOne
    @JoinColumn(name = "sender_id")
    public User sender;


    @ManyToOne
    @JoinColumn(name = "receiver_id")
    public User receiver;


    @CreationTimestamp
    public LocalDateTime timestamp;
}
