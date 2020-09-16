import org.scalatest.flatspec.AnyFlatSpec

import scala.io.Source

class PlayerDataAggregatorTest extends AnyFlatSpec {

  private val currentStandings = Source.fromResource("currentStandings.csv")
  private val tournamentResults = Source.fromResource("tournamentResults.csv")
  private val aggregator = new PlayerDataAggregator(currentStandings, tournamentResults)
  private val salientBlue = aggregator.getPlayer("SalientBlue")

  it should "aggregate a single player's data correctly" in {
    assert(salientBlue.name == "SalientBlue")
    assert(salientBlue.initialRating == 102)
  }

  it should "aggregate a single player's wins" in {
    assert(salientBlue.wins.size == 5)
    assert(salientBlue.wins.count(_ == 604) == 2)
    assert(salientBlue.wins.count(_ == 1048) == 2)
    assert(salientBlue.wins.count(_ == 2925) == 1)
  }

  it should "aggregate a single player's losses" in {
    assert(salientBlue.losses.size == 6)
    assert(salientBlue.losses.count(_ == 604) == 1)
    assert(salientBlue.losses.count(_ == 1048) == 1)
    assert(salientBlue.losses.count(_ == 2925) == 2)
    assert(salientBlue.losses.count(_ == 237) == 2)
  }
}
