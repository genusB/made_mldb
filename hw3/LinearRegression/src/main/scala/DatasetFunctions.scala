import breeze.linalg._
import java.io._

object DatasetFunctions {

  def loadDataset(path: String): DenseMatrix[Double] = {
    val dataset = csvread(new File(path), ',')
    dataset
  }

  def featuresTargetSplit(dataset: DenseMatrix[Double]): (DenseMatrix[Double], DenseVector[Double]) = {
    val X = dataset(::, 1 to -2)
    val y = dataset(::, -1)
    (X, y)
  }

  def writeToCSV(path: String, predictions: DenseVector[Double]): Unit = {
    val matrix: DenseMatrix[Double] = predictions.toDenseMatrix.reshape(predictions.length, 1)
    csvwrite(new File(path), matrix, separator = ',')
  }
}
