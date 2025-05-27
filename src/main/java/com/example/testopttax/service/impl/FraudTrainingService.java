package com.example.testopttax.service.impl;

import com.example.testopttax.record.mlDtos.TransactionData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import weka.classifiers.trees.RandomForest;
import weka.core.Attribute;
import weka.core.Instances;
import weka.core.DenseInstance;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FraudTrainingService {
    public List<TransactionData> trainModel(List<TransactionData> transactions) throws Exception {
        // Создаем атрибуты
        ArrayList<Attribute> attributes = new ArrayList<>();
        attributes.add(new Attribute("amount"));
        attributes.add(new Attribute("type", Arrays.asList("debit", "credit")));
        attributes.add(new Attribute("balance"));
        attributes.add(new Attribute("age"));
        attributes.add(new Attribute("time"));
        attributes.add(new Attribute("fraud"));

        // Создаем экземпляр Instances с указанными аттрибутами
        Instances dataset = new Instances("FraudData", attributes, 0);
        dataset.setClassIndex(dataset.numAttributes() - 1);

        // Заполняем dataset экземплярами данных
        for (TransactionData transactionData : transactions) {
            double[] instanceValues = new double[dataset.numAttributes()];

            instanceValues[0] = transactionData.amount();
            instanceValues[1] = dataset.attribute(1).indexOfValue(transactionData.type());
            instanceValues[2] = transactionData.balance();
            instanceValues[3] = transactionData.age();
            instanceValues[4] = transactionData.time().toSecondOfDay();
            instanceValues[5] = transactionData.fraud() ? 1.0 : 0.0;

            // Добавляем экземпляр данных в dataset
            dataset.add(new DenseInstance(1.0, instanceValues));
        }

        // Загружаем существующую модель (если она есть)
        RandomForest model = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("fraud_model.model"))) {
            model = (RandomForest) ois.readObject();
        } catch (Exception e) {
            // Если модели нет, создаем новую
            model = new RandomForest();
        }

        // Обучаем модель (или дообучаем её на новых данных)
        model.buildClassifier(dataset);

        // Сохраняем модель в файл
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("fraud_model.model"))) {
            oos.writeObject(model);
        }

        return transactions;
    }

    public Boolean checkForFraud(TransactionData transactionData) throws Exception {
        // Загружаем существующую модель
        RandomForest model;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("fraud_model.model"))) {
            model = (RandomForest) ois.readObject();
        }

        // Создаем атрибуты
        ArrayList<Attribute> attributes = new ArrayList<>();
        attributes.add(new Attribute("amount"));
        attributes.add(new Attribute("type", Arrays.asList("debit", "credit")));
        attributes.add(new Attribute("balance"));
        attributes.add(new Attribute("age"));
        attributes.add(new Attribute("time"));
        attributes.add(new Attribute("fraud"));

        // Создаем экземпляр Instances
        Instances dataset = new Instances("FraudData", attributes, 0);
        dataset.setClassIndex(dataset.numAttributes() - 1);

        // Заполняем dataset экземпляром данных
        // Заполняем dataset экземпляром данных
        double[] instanceValues = new double[dataset.numAttributes()];
        instanceValues[0] = transactionData.amount();
        instanceValues[1] = dataset.attribute(1).indexOfValue(transactionData.type());
        instanceValues[2] = transactionData.balance();
        instanceValues[3] = transactionData.age();
        instanceValues[4] = transactionData.time().toSecondOfDay();
        instanceValues[5] = 0.0;

        DenseInstance instance = new DenseInstance(1.0, instanceValues);

        instance.setDataset(dataset);

        dataset.add(instance);

        double prediction = model.classifyInstance(instance);

        // Если prediction == 1, то это мошенничество, если 0, то нет
        return prediction > 0.60;
    }
}