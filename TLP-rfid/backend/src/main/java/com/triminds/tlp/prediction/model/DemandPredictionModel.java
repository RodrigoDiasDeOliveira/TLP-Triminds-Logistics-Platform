package com.triminds.tlp.prediction.model;

import weka.classifiers.trees.RandomForest;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;



public class DemandPredictionModel {

    private RandomForest model;

    public DemandPredictionModel() {
        model = new RandomForest();
    }

    public Instances loadData(String filePath) throws Exception {
        DataSource source = new DataSource(filePath);
        Instances data = source.getDataSet();
        data.setClassIndex(data.numAttributes() - 1);
        return data;
    }

    public void train(Instances data) throws Exception {
        model.buildClassifier(data);
    }

    public double predictDemand(Instances newData) throws Exception {
        return model.classifyInstance(newData.instance(0));
    }
}