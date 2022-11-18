import breeze.linalg.{DenseMatrix, DenseVector, mmwrite}
import org.scalatest.funsuite.AnyFunSuite


class TestLinearRegression extends AnyFunSuite {

  test("predict method multiplies matrix and weight vector correctly") {
    val matrix: DenseMatrix[Double] = DenseMatrix((1.0, 2.0), (3.0, 4.0))
    val w: DenseVector[Double] = DenseVector(Array(5.0, 6.0))

    val multiplication = LinearRegression.predict(matrix, w)

    assert(multiplication.toArray sameElements Array(17.0, 39.0))
  }

  test("predict_abs method multiplies matrix and weight vector correctly and take absolute value") {
    val matrix: DenseMatrix[Double] = DenseMatrix((-1.0, -2.0), (-3.0, -4.0))
    val w: DenseVector[Double] = DenseVector(Array(5.0, 6.0))

    val multiplication = LinearRegression.predict_abs(matrix, w)

    assert(multiplication.toArray sameElements Array(17.0, 39.0))
  }

  test("mse calculates correctly for given matrix, weight vector and correct answer vector") {
    val matrix: DenseMatrix[Double] = DenseMatrix((1.0, 2.0), (3.0, 4.0))
    val w: DenseVector[Double] = DenseVector(Array(5.0, 6.0))
    val correctVector: DenseVector[Double] = DenseVector(Array(15.0, 36.0))

    val mse = LinearRegression.mse(matrix, correctVector, w)

    assert(mse == 6.5)
  }

  test("mse_derivative calculates correctly for given matrix, weight vector and correct answer vector") {
    val matrix: DenseMatrix[Double] = DenseMatrix((1.0, 2.0), (3.0, 4.0))
    val w: DenseVector[Double] = DenseVector(Array(5.0, 6.0))
    val correctVector: DenseVector[Double] = DenseVector(Array(15.0, 36.0))

    val mse_derivative = LinearRegression.mse_derivative(matrix, correctVector, w)

    assert(mse_derivative.toArray sameElements Array(11.0, 16.0))
  }

  test("gradient descend calculate correct weight vector") {
    val featuresTrain: DenseMatrix[Double] = DenseMatrix((1.0, 2.0), (2.0, 4.0), (3.0, 2.0), (4.0, 5.0))
    val targetTrain: DenseVector[Double] = DenseVector(Array(5.0, 10.0, 11.0, 17.0))

    val featuresTest: DenseMatrix[Double] = DenseMatrix((3.0, 4.0), (6.0, 3.0))
    val targetTest: DenseVector[Double] = DenseVector(Array(13.0, 21.0))

    val w = LinearRegression.gradientDescent(featuresTrain, targetTrain,
      DenseVector.ones[Double](featuresTrain.cols),
      epochs = 1000)

    val mseTest = LinearRegression.mse(featuresTest, targetTest, w)
    assert(mseTest < 0.01)
  }
}