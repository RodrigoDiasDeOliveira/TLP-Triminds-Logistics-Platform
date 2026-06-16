package com.triminds.tlp.prediction.model;

import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.graph.ComputationGraph;

import org.deeplearning4j.nn.conf.ComputationGraphConfiguration;

import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.learning.config.Adam;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.api.ndarray.INDArray;




public class LocationPredictionModel {

    private ComputationGraph model;

    public LocationPredictionModel() {

        ComputationGraphConfiguration config =
            new NeuralNetConfiguration.Builder()
                .seed(123)
                .updater(new Adam(0.01))
                .graphBuilder()
                .addInputs("input")

                .addLayer("L1",
                    new DenseLayer.Builder()
                        .nIn(2)
                        .nOut(64)
                        .activation(Activation.RELU)
                        .build(),
                    "input")

                .addLayer("L2",
                    new DenseLayer.Builder()
                        .nIn(64)
                        .nOut(32)
                        .activation(Activation.RELU)
                        .build(),
                    "L1")

                .addLayer("output",
                    new OutputLayer.Builder()
                        .nIn(32)
                        .nOut(2)
                        .activation(Activation.IDENTITY)
                        .build(),
                    "L2")

                .setOutputs("output")
                .build();

        model = new ComputationGraph(config);
        model.init();
    }

    // ✅ TREINAMENTO CORRETO
    public void train(INDArray inputData, INDArray outputData) {
        DataSet dataSet = new DataSet(inputData, outputData);
        model.fit(dataSet);
    }

    // ✅ PREDIÇÃO
    public INDArray predict(INDArray inputData) {
        return model.output(inputData)[0];
    }
}