package com;
import java.io.FileReader;
import weka.core.Instances;
import weka.classifiers.trees.RandomForest;
import weka.core.Attribute;
import weka.core.Utils;
import weka.classifiers.Evaluation;
import weka.filters.unsupervised.attribute.NumericToNominal;
import weka.classifiers.functions.MultilayerPerceptron;
import javax.swing.JTextArea;
public class NeuralNetwork {
	static int benign,suspicious;
public static void nn(String file) throws Exception {
	 RandomForest rf = new RandomForest();
	 FileReader reader = new FileReader(file); 
     Instances instances = new Instances(reader);
	 instances.setClassIndex(instances.numAttributes()-1);
	 MultilayerPerceptron mlp = new MultilayerPerceptron();
	 mlp.setOptions(Utils.splitOptions("-L 0.1 -M 0.2 -N 500 -V 0 -S 0 -E 20 -H 3"));
	 mlp.buildClassifier(instances);
	 Evaluation eval = new Evaluation(instances);
	 eval.evaluateModel(mlp,instances);
	 ViewPrediction vp = new ViewPrediction();
	 Instances predicteddata = new Instances(instances);
	 for(int i=0;i<instances.numInstances();i++){
		 int clsLabel = (int)mlp.classifyInstance(instances.instance(i));
		 predicteddata.instance(i).setClassValue(clsLabel);
		 String arr[] = predicteddata.instance(i).toString().trim().split(",");
		 vp.dtm.addRow(arr);
		 if(arr[arr.length-1].equals("0"))
			 suspicious = suspicious + 1;
		 else
			 benign = benign + 1;

	 }
	 vp.setVisible(true);
	 vp.setSize(800,600);
}

}

/*
L = Learning Rate
M = Momentum
N = Training Time or Epochs
H = Hidden Layers*/