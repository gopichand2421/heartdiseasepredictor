package com.heartpredictorservice.service;

import com.heartpredictorservice.model.PatientReport;
import org.datavec.api.records.reader.RecordReader;
import org.datavec.api.records.reader.impl.csv.CSVRecordReader;
import org.datavec.api.split.FileSplit;
import org.deeplearning4j.datasets.datavec.RecordReaderDataSetIterator;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.Updater;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.lossfunctions.LossFunctions;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;

@Service
public class PreditctorService {

    public Boolean predictHeartdisease(PatientReport patientReport) throws FileNotFoundException {
        boolean modelPredicted = false;
        File csvFile = ResourceUtils.getFile("classpath:datasets/heart.csv");
        try(RecordReader recordReader = new CSVRecordReader(1)){
            recordReader.initialize(new FileSplit(csvFile));
            int batchSize = 304;
            int labelIndex = 13;
            int numClasses = 2;
            DataSetIterator iterator = new RecordReaderDataSetIterator(recordReader, batchSize, labelIndex, numClasses);
            MultiLayerNetwork model = new MultiLayerNetwork(new NeuralNetConfiguration.Builder()
                    .seed(123)
                    .updater(Updater.ADAM)
                    .list()
                    .layer(0, new DenseLayer.Builder().nIn(13).nOut(128).activation(Activation.RELU).build())
                    .layer(1, new DenseLayer.Builder().nIn(128).nOut(64).activation(Activation.RELU).build())
                    .layer(2, new OutputLayer.Builder().nIn(64).nOut(numClasses).activation(Activation.SOFTMAX)
                            .lossFunction(LossFunctions.LossFunction.MCXENT)
                            .build())
                    .build());
            model.init();
            INDArray input = Nd4j.create(reportFormatter(patientReport), new int[]{1,13});
            for(int i=0; i< 1000;i++){
                model.fit(iterator);
            }
            System.out.println("Training completed");
            INDArray predicted = model.output(input);
            int predicatedClass = predicted.argMax(1).getInt(0);
            if(predicatedClass ==1){
                modelPredicted = true;
            }
        }catch (Exception ex) {
            ex.printStackTrace();
        }


        return modelPredicted;
    }

    private double[] reportFormatter(PatientReport patientReport){
//        int age,fbs,restecg,exang,ca = 0;
        if(Objects.nonNull(patientReport)){
            int gen = "MALE".equals(patientReport.getGender()) ? 0:1;
            int fbs = patientReport.getFbs()?1:0;
            int exang = patientReport.getExang() ? 1: 0;
            return new double[]{patientReport.getAge(), gen,
                    patientReport.getCp(),patientReport.getTrestbps(), patientReport.getChol(),
            fbs,patientReport.getRestecg(),patientReport.getThalach(),exang,patientReport.getOldpeak(),
            patientReport.getSloap(), patientReport.getCa(),patientReport.getThal()};
        }
        return new double[]{};
    }
}
