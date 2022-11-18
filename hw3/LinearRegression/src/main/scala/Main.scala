import breeze.linalg.{DenseMatrix, DenseVector}

object Main {
  def linearRegression(pathToTrainData: String = "./Fish_train.csv",
                       pathToTestData: String = "./Fish_test.csv",
                       pathToOutput: String = "./results.csv"): Unit = {

    val datasetTrain: DenseMatrix[Double] = DatasetFunctions.loadDataset(pathToTrainData)
    val datasetTest: DenseMatrix[Double] = DatasetFunctions.loadDataset(pathToTestData)

    val (featuresTrain, targetTrain) = DatasetFunctions.featuresTargetSplit(datasetTrain)
    val (featuresTest, targetTest) = DatasetFunctions.featuresTargetSplit(datasetTest)

    val w = LinearRegression.gradientDescent(featuresTrain, targetTrain,
      DenseVector.ones[Double](featuresTrain.cols),
      epochs = 10000)

    val yPredicted = LinearRegression.predict_abs(featuresTest, w)
    DatasetFunctions.writeToCSV(pathToOutput, yPredicted)
  }

  def main(args: Array[String]): Unit = {
    val pathToTrainData: String = args(0)
    val pathToTestData: String = args(1)
    val pathToOutput: String = args(2)

    linearRegression(pathToTrainData=pathToTrainData, pathToTestData=pathToTestData, pathToOutput=pathToOutput)
  }
}