import org.scalatest.flatspec.AnyFlatSpec

class EloCalculatorTest extends AnyFlatSpec {

  it should "calculate 2400 player beating 2000 player correctly" in {
    val eloCalculator = new EloCalculator(32)
    val ratingChange = eloCalculator.calculateSingleRoundChange(2400, 2000, isWin = true)
    assert(3 == ratingChange)
  }

  it should "calculate 2000 player beating 2400 player correctly" in {
    val eloCalculator = new EloCalculator(32)
    val ratingChange = eloCalculator.calculateSingleRoundChange(2000, 2400, isWin = true)
    assert(29 == ratingChange)
  }

  it should "calculate 2400 player losing to 2000 player correctly" in {
    val eloCalculator = new EloCalculator(32)
    val ratingChange = eloCalculator.calculateSingleRoundChange(2400, 2000, isWin = false)
    assert(-29 == ratingChange)
  }

  it should "calculate 2000 player losing to 2400 player correctly" in {
    val eloCalculator = new EloCalculator(32)
    val ratingChange = eloCalculator.calculateSingleRoundChange(2000, 2400, isWin = false)
    assert(-3 == ratingChange)
  }
}
