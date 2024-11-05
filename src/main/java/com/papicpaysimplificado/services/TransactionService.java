package com.papicpaysimplificado.services;

import com.papicpaysimplificado.domain.Transaction.Transaction;
import com.papicpaysimplificado.domain.user.User;
import com.papicpaysimplificado.dtos.requests.RequestSendNotification;
import com.papicpaysimplificado.dtos.requests.RequestTransaction;
import com.papicpaysimplificado.dtos.responses.ResponseAuthorizationTransaction;
import com.papicpaysimplificado.dtos.responses.ResponseSendNotification;
import com.papicpaysimplificado.mocks.AuthorizationTransaction.AuthorizationTransaction;
import com.papicpaysimplificado.mocks.SendNotification.SendNotification;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.reactive.ClientWebApplicationException;

import java.math.BigDecimal;

@ApplicationScoped
public class TransactionService {


    @Inject
    UserService userService;


    @Transactional
    public Transaction createTransaction(RequestTransaction requestTransaction) throws  Exception{


        BigDecimal amount = requestTransaction.amount;
        User sender = userService.findUserSenderById(requestTransaction.senderId);
        User receiver = userService.findUserReceiverById(requestTransaction.receiverId);
        userService.validadeTransaction(sender, requestTransaction.amount);


        authorizeTransaction();


        sender.balance = sender.balance.subtract(requestTransaction.amount);
        receiver.balance = receiver.balance.add(requestTransaction.amount);


        Transaction transaction = new Transaction();

        transaction.amount = amount;
        transaction.sender = sender;
        transaction.receiver = receiver;
        transaction.notifySender = sendNotificationEmail();
        transaction.notifyReceiver = sendNotificationEmail();

        userService.saveUser(sender);
        userService.saveUser(receiver);
        transaction.persist();

        return transaction;
    }


    @Inject
    @RestClient
    AuthorizationTransaction authorizationTransaction;


    public void authorizeTransaction() throws Exception {
        try {
            ResponseAuthorizationTransaction response = authorizationTransaction.authorize();
        } catch (ClientWebApplicationException err) {
            throw new Exception("Transferência recusada pelo serviço de autorização");
        }
    }


    @Inject
    @RestClient
    SendNotification sendNotification;


    public String sendNotificationEmail() {
        String notifyStatus;
        RequestSendNotification requestSendNotification = new RequestSendNotification();

        try {
            ResponseSendNotification response = sendNotification.sendNotification(requestSendNotification);
            return "Notificação enviada com sucesso";
        } catch (ClientWebApplicationException err) {
            return "Falha ao enviar a notificação";
        }
    }
}
