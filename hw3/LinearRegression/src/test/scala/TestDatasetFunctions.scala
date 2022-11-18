import org.scalatest.funsuite.AnyFunSuite

import java.io.{File, FileNotFoundException}
import java.nio.file.Paths
import java.nio.file.Files


class TestDatasetFunctions extends AnyFunSuite {

  test("the dataset is loaded correctly from csv") {
    val dataset = DatasetFunctions.loadDataset("./Fish_test.csv")
    assert(dataset.cols == 5)
    assert(dataset.rows == 32)
  }

  test("the loadDataset function throws error if path is invalid") {
    intercept[FileNotFoundException] {
      val dataset = DatasetFunctions.loadDataset("./Invalid.csv")
    }
  }

  test("featuresTargetSplit splits preloaded dataset correctly into features and target") {
    val dataset = DatasetFunctions.loadDataset("./Fish_test.csv")
    val (features, target) = DatasetFunctions.featuresTargetSplit(dataset)

    assert(features.cols == 3)
    assert(features.rows == dataset.rows)
    assert(features.rows == 32)

    assert(target.length == dataset.rows)
    assert(target.length == 32)
  }


  test("writeToCSV correctly write vector to csv") {
    val dataset = DatasetFunctions.loadDataset("./Fish_test.csv")
    val (features, target) = DatasetFunctions.featuresTargetSplit(dataset)

    val pathToOutput: String = "test_result.csv"

    DatasetFunctions.writeToCSV(pathToOutput, target)

    val readDataset = DatasetFunctions.loadDataset(pathToOutput)

    assert(Files.exists(Paths.get(pathToOutput)))
    assert((target.toDenseMatrix.reshape(target.length, 1) :== readDataset).forall(_ == true))

    new File(pathToOutput).delete()
  }

}