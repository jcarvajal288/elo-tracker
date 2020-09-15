import scala.io.BufferedSource

class PlayerDataAggregator(currentStandings: BufferedSource, tournamentResults: BufferedSource) {

  private val standings: Map[String, Double] = readCurrentStandings(currentStandings)
  //private val matches: List[Match] = readMatches(tournamentResults)

  private def readCurrentStandings(standings: BufferedSource): Map[String, Double] = {
    standings
      .getLines()
      .toList
      .tail
      .map((line: String) => {
        val split = line.split(",")
        split(0) -> split(1).toDouble
      })
      .toMap
  }

  def getPlayer(playerName: String): Player = {
    if(standings.keySet.contains(playerName)) {
      new Player(playerName, standings.apply(playerName))
    } else {
      throw new RuntimeException(s"player '$playerName' not found in standings list!")
    }
  }
}
