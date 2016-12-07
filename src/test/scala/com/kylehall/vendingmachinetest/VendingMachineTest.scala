package com.kylehall.vendingmachinetest

import com.kylehall.vendingmachine.VendingMachine
import com.kylehall.vendingmachine.Coins
import com.kylehall.vendingmachine.Coins.{PENNY, NICKEL, DIME, QUARTER}

class VendingMachineSpec extends UnitSpec {

  val SIXTY_CENT_DISPLAY = "$0.60"
  val INSERT_COINS = "Insert Coins"

  val DIME_PLUS_QUARTER = 0.35f
  val FIFTY_CENTS = 0.50f
  val SIXTY_CENTS = 0.60f

  def vendingMachine = new VendingMachine()

  "A VendingMachine" should "accept dimes and value them at 10 cents" in {
    val total = vendingMachine.insertCoin(DIME, Coins.coins(PENNY))
    assert(total == Coins.coins(DIME))
  }

  it should "accept nickels and value them at 5 cents" in {
    val total = vendingMachine.insertCoin(NICKEL, Coins.coins(PENNY))
    assert(total == Coins.coins(NICKEL))
  }

  it should "accept quarters and value them at 25 cents" in {
    val total = vendingMachine.insertCoin(QUARTER, Coins.coins(PENNY))
    assert(total == Coins.coins(QUARTER))
  }

  it should "reject pennies and value them at 0 cents" in {
    val total = vendingMachine.insertCoin(PENNY, Coins.coins(PENNY))
    assert(total == Coins.coins(PENNY))
  }

  it should "accept multiple coins and keep running total" in {
    val dimeTotal = vendingMachine.insertCoin(DIME, Coins.coins(PENNY))
    assert(dimeTotal == Coins.coins(DIME))
    val plusQuarterTotal = vendingMachine.insertCoin(QUARTER, dimeTotal)
    assert(plusQuarterTotal == DIME_PLUS_QUARTER)
  }

  it should "return all coins if requested" in {
    val twoQuarters = vendingMachine.insertCoin(QUARTER, Coins.coins(PENNY)) +
      vendingMachine.insertCoin(QUARTER, Coins.coins(PENNY))
    assert(twoQuarters == FIFTY_CENTS)
    val totalAfterReturnCoins = vendingMachine.returnCoins(twoQuarters)
    assert(totalAfterReturnCoins == Coins.coins(PENNY))
  }

  it should "display 'Insert Coins' if no coins have been entered and the display is checked" in {
    val message = vendingMachine.checkDisplay(Coins.coins(PENNY))
    assert(message == INSERT_COINS)
  }

  it should "display the total value of all inserted coins" in {
    val sixtyCents = vendingMachine.insertCoin(QUARTER, Coins.coins(PENNY)) +
      vendingMachine.insertCoin(QUARTER, Coins.coins(PENNY)) +
      vendingMachine.insertCoin(DIME, Coins.coins(PENNY))
    assert(sixtyCents == SIXTY_CENTS)
    val message = vendingMachine.checkDisplay(sixtyCents)
    assert(message == SIXTY_CENT_DISPLAY)
  }

}
