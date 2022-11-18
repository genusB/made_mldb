import breeze.linalg.{DenseMatrix, DenseVector, InjectNumericOps, norm}
import breeze.numerics.{abs, pow}
import breeze.stats.mean

import java.io._
import scala.collection.mutable.ListBuffer

object LinearRegression {

  def predict(X: DenseMatrix[Double], w: DenseVector[Double]): DenseVector[Double] = {
    X * w
  }

  def predict_abs(X: DenseMatrix[Double], w: DenseVector[Double]): DenseVector[Double] = {
    abs(X * w)
  }

  def mse(X: DenseMatrix[Double], y: DenseVector[Double], w: DenseVector[Double]): Double = {
    mean(pow((X * w) - y, 2))
  }

  def mse_derivative(X: DenseMatrix[Double], y: DenseVector[Double], w: DenseVector[Double]): DenseVector[Double] = {
    2.0 *:* (X.t * ((X * w) - y)) /:/ y.length.toDouble
  }

  def gradientDescent(X: DenseMatrix[Double], y: DenseVector[Double],
                      w: DenseVector[Double], learning_rate: Double = 0.01,
                      epochs: Int = 100): DenseVector[Double] = {

    var w_estimate: DenseVector[Double] = w

    val fileObject = new File("./metrics.txt")
    val printWriter = new PrintWriter(fileObject)

    for (i <- 0 to epochs) {
      val empirical_risk = mse(X, y, w_estimate)
      var gradient = mse_derivative(X, y, w_estimate)
      val gradient_norm = norm(gradient)

      if (gradient_norm > 5.0) {
        gradient = gradient /:/ gradient_norm *:* 5.0
      }

      w_estimate = w_estimate - (learning_rate *:* gradient)

      if (i % 25 == 0) {
        printWriter.write(empirical_risk.toString + "\n")
      }
    }

    printWriter.close()

    w_estimate
  }

}
