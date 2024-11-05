package com.papicpaysimplificado.services;

import com.papicpaysimplificado.domain.user.User;
import com.papicpaysimplificado.domain.user.UserType;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@ApplicationScoped
public class UserService {


    public List<User> listUsers() {
        return User.listAll();
    }


    @Transactional
    public User saveUser(User user) {
        user.persist();
        return user;
    }


    public void validadeTransaction(User sender, BigDecimal amount) throws Exception {
        if(sender.userType == UserType.MERCHANT) {
            throw new Exception("Usuário do tipo lojista não está autorizado a realizar transferências");
        }
        if (sender.balance.compareTo(amount) < 0) {
            throw new Exception("Saldo insuficiente");
        }
    }


    public User findUserSenderById(Long id) throws Exception {
        if (User.findById(id) == null) {
            throw new Exception("Usuário remetente não encontrado");
        }
        return User.findById(id);
    }


    public User findUserReceiverById(Long id) throws Exception {
        if (User.findById(id) == null) {
            throw new Exception("Usuário destinatário não encontrado");
        }
        return User.findById(id);
    }
}
