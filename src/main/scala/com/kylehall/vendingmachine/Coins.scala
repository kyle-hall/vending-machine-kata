package com.kylehall.vendingmachine

object Coins {
  val PENNY = "penny"
  val NICKEL = "nickel"
  val DIME = "dime"
  val QUARTER = "quarter"

  val coins = Map (
    PENNY -> 0.0f,
    NICKEL -> 0.05f,
    DIME -> 0.10f,
    QUARTER -> 0.25f
  )
}
