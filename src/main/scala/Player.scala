class Player(
              _name: String,
              _initialRating: Double,
              eloCalculator: EloCalculator,
              wins: List[Double],
              losses: List[Double]
            ) {
  val name: String = _name
  val initialRating: Double = _initialRating
  val finalRating = eloCalculator.calculateRating(initialRating, wins, losses)
}
