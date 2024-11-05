package com.papicpaysimplificado.domain.user;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import java.math.BigDecimal;


@Entity(name = "users")
@Table(name = "tb_users")
public class User extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;


    public String firstName;


    public String lastName;


    @Column(unique = true)
    public String  document;


    @Column(unique = true)
    public String email;


    public String password;


    public BigDecimal balance;


    @Enumerated(EnumType.STRING)
    public UserType userType;

}
