import scala.io.BufferedSource

class PlayerDataAggregator(currentStandings: BufferedSource,
                           tournamentResults: BufferedSource
                          ) {

  private val standings: Map[String, Double] = readCurrentStandings(currentStandings)
  private val matches: List[Match] = readMatches(tournamentResults)

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

  def readMatches(tournamentResults: BufferedSource): List[Match] = {
    tournamentResults
      .getLines()
      .toList
      .tail
      .map((line: String) => {
        val split = line.split(",")
        Match(split(0), split(1).toInt, split(2), split(3).toInt)
      })
  }

  private def winsForPlayer(playerName: String): List[Double] = {
    matches
      .filter(_.hasPlayer(playerName))
      .flatMap(m => {
      // TODO: fill in new players before this step! New players have no standing yet!
        val opponent = m.getOpponent(playerName)
        List.fill(m.getPlayerWins(playerName))(standings.apply(opponent))
    })
  }

  private def lossesForPlayer(playerName: String): List[Double] = {
    matches
      .filter(_.hasPlayer(playerName))
      .flatMap(m => {
        // TODO: fill in new players before this step! New players have no standing yet!
        val opponent = m.getOpponent(playerName)
        List.fill(m.getPlayerWins(opponent))(standings.apply(opponent))
      })
  }

  def getPlayer(playerName: String): Player = {
    if(standings.keySet.contains(playerName)) {
      Player(
        playerName,
        standings.apply(playerName),
        winsForPlayer(playerName),
        lossesForPlayer(playerName)
      )
    } else {
      // TODO: this means a new player has joined the tournament. calculate their elo
      // TODO: according to wikipedia's algorithm
      throw new RuntimeException(s"player '$playerName' not found in standings list!")
    }
  }

  def getPlayers: Set[Player] = {
    standings.keySet
      .map(playerName => {
        getPlayer(playerName)
      })
  }
}
