import org.scalatest.flatspec.AnyFlatSpec

import scala.io.Source

class PlayerDataAggregatorTest extends AnyFlatSpec {

  it should "aggregate a single player's data correctly" in {
    val currentStandings = Source.fromResource("currentStandings.csv")
    val tournamentResults = Source.fromResource("tournamentResults.csv")
    val aggregator = new PlayerDataAggregator(currentStandings, tournamentResults)
    val player = aggregator.getPlayer("SalientBlue")
    assert("SalientBlue" == player.name)
    assert(102 == player.initialRating)
  }
}
