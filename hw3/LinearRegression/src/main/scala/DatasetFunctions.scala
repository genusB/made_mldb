import breeze.linalg._
import java.io._

object DatasetFunctions {

  def loadDataset(path: String): DenseMatrix[Double] = {
    val dataset = csvread(new File(path),',')
    dataset
  }

  def writeToCSV(path: String, predictions: DenseVector[Double]): Unit = {
    val matrix: DenseMatrix[Double] = predictions.toDenseMatrix.reshape(predictions.length, 1)
    csvwrite(new File(path), matrix, separator=',')
  }

  def featuresTargetSplit(dataset: DenseMatrix[Double]): (DenseMatrix[Double], DenseVector[Double]) = {
    val X = dataset(::, 1 to -2)
    val y = dataset(::, -1)
    (X, y)
  }

  def trainTestSplit(X: DenseMatrix[Double], y: DenseVector[Double]):
                            (DenseMatrix[Double], DenseMatrix[Double], DenseVector[Double], DenseVector[Double]) = {

    val cnt = X.rows

    val trainSize = (0.75 * cnt).toInt

    val xTrain = X(0 to trainSize, ::)
    val xTest = X(trainSize to -1, ::)

    val yTrain = y(0 to trainSize)
    val yTest = y(trainSize to -1)

    (xTrain, xTest, yTrain, yTest)
  }
}
