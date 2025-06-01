package com.example.testopttax.service.impl;

import com.example.testopttax.exception.CustomException;
import com.example.testopttax.model.Payment;
import com.example.testopttax.record.mlDtos.TransactionData;
import com.example.testopttax.repo.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.LongSummaryStatistics;

@RequiredArgsConstructor
@Service
@Slf4j
public class PaymentService {
    private final PaymentRepository paymentRepository;

    private final FraudTrainingService fraudTrainingService;

    public List<TransactionData> getPayments() {
        var dataFromDb = paymentRepository.findAll();
        return dataFromDb.stream()
                .map(payment -> new TransactionData(
                                payment.getAmount(),
                                payment.getType(),
                                payment.getBalance(),
                                payment.getAge(),
                                payment.getTime(),
                                payment.getCoordinates(),
                                null
                        )
                )
                .toList();
    }

    public TransactionData createPaymentWithTraningModel(TransactionData createPaymentData)  {

            var modelResponse = this.fraudTrainingService.checkForFraud(createPaymentData);
            if (modelResponse){
                log.error("Транзакция: " + createPaymentData + " имеет высокую вероятность мошшенничества!!!");
                throw new CustomException("Платеж: "
                        + createPaymentData.toString()
                        + " передан в департамент безопасности для проверки. Высокая вероятность мошенничества!");
            }




        Payment paymentForSave = new Payment();
        paymentForSave.setAmount(createPaymentData.amount());
        paymentForSave.setAge(createPaymentData.age());
        paymentForSave.setBalance(createPaymentData.balance());
        paymentForSave.setTime(createPaymentData.time());
        paymentForSave.setCoordinates(createPaymentData.coordinates());
        paymentForSave.setType(createPaymentData.type());

        this.paymentRepository.save(paymentForSave);
        log.info("Успешно сохранили платеж: " + paymentForSave);
        return createPaymentData;
    }



}
