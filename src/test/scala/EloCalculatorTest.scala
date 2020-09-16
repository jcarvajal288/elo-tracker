import org.scalatest.flatspec.AnyFlatSpec

class EloCalculatorTest extends AnyFlatSpec {

  private val eloCalculator = new EloCalculator(32)

  it should "calculate 2400 player beating 2000 player correctly" in {
    val ratingChange = eloCalculator.calculateSingleRoundChange(2400, 2000, isWin = true)
    assert(3 == ratingChange)
  }

  it should "calculate 2000 player beating 2400 player correctly" in {
    val ratingChange = eloCalculator.calculateSingleRoundChange(2000, 2400, isWin = true)
    assert(29 == ratingChange)
  }

  it should "calculate 2400 player losing to 2000 player correctly" in {
    val ratingChange = eloCalculator.calculateSingleRoundChange(2400, 2000, isWin = false)
    assert(-29 == ratingChange)
  }

  it should "calculate 2000 player losing to 2400 player correctly" in {
    val ratingChange = eloCalculator.calculateSingleRoundChange(2000, 2400, isWin = false)
    assert(-3 == ratingChange)
  }

  it should "calculate a new player's elo rating" in {
    val wins = List(604.0, 604.0, 1048.0, 1048.0, 2925.0)
    val losses = List(604.0, 1048.0, 2925.0, 2925.0, 237.0, 237.0)
    assert(eloCalculator.freshRating(wins, losses) == 1255)
  }
}
