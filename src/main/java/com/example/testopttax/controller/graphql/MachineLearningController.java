package com.example.testopttax.controller.graphql;


import com.example.testopttax.exception.CustomException;
import com.example.testopttax.record.mlDtos.TransactionData;
import com.example.testopttax.service.impl.FraudTrainingService;
import com.example.testopttax.service.impl.PaymentService;
import jdk.dynalink.linker.LinkerServices;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import weka.core.FastVector;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MachineLearningController {
    private final FraudTrainingService fraudTrainingService;
    private final PaymentService paymentService;

    /*
    Метод для тренировки модели
     */
    @MutationMapping
    public List<TransactionData> trainModelForFraud(@Argument("transactionData")  List<TransactionData> transactionData) {
        try{
            return fraudTrainingService.trainModel(transactionData);
        } catch (Exception e) {
            throw new CustomException("Ошибка при тренировке модели!");
        }

    }

    /*
    Проверка транзакции на фрод
     */
    @QueryMapping
    public Boolean checkFraud(@Argument("transaction") TransactionData transaction) {
        try {
            return fraudTrainingService.checkForFraud(transaction);
        } catch (Exception e) {
            throw new CustomException("Ошибка при проверке транзакции");
        }
    }

    /*
    Получение всех платежей
     */
    @QueryMapping
    public List<TransactionData> getPayments() {
        return this.paymentService.getPayments();
    }

    /*
    Создание транзакции с помощью уже сущ. модели
     */
    @MutationMapping
    public TransactionData createPaymentWithTraningModel(@Argument TransactionData payment){
            return this.paymentService.createPaymentWithTraningModel(payment);
    }

}
