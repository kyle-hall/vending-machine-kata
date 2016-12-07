package com.kylehall.vendingmachinetest

import com.kylehall.vendingmachine.VendingMachine

class VendingMachineSpec extends UnitSpec {

  val ZERO = 0.0f
  val NICKEL = 0.05f
  val DIME = 0.10f
  val QUARTER = 0.25f

  def vendingMachine = new VendingMachine()

  "A VendingMachine" should "accept dimes and value them at 10 cents" in {
    val total = vendingMachine.insertCoin("dime", ZERO)
    assert(total == DIME)
  }

  it should "accept nickels and value them at 5 cents" in {
    val total = vendingMachine.insertCoin("nickel", ZERO)
    assert(total == NICKEL)
  }

  it should "accept quarters and value them at 25 cents" in {
    val total = vendingMachine.insertCoin("quarter", ZERO)
    assert(total == QUARTER)
  }

  it should "reject pennies and value them at 0 cents" in {
    val total = vendingMachine.insertCoin("penny", ZERO)
    assert(total == ZERO)
  }

  it should "accept multiple coins and keep running total" in {
    val dimeTotal = vendingMachine.insertCoin("dime", ZERO)
    assert(dimeTotal == 0.10f)
    val plusQuartertotal = vendingMachine.insertCoin("quarter", dimeTotal)
    assert(plusQuartertotal == 0.35f)
  }

  it should "return all coins if requested" in {
    var total = 0.0f
    total = vendingMachine.insertCoin("quarter", total) + vendingMachine.insertCoin("quarter", total)
    assert(total == 0.50f)
    total = vendingMachine.returnCoins(total)
    assert(total == 0.0f)
  }

  it should "display 'Insert Coins' if no coins have been entered and the display is checked" in {
    val total = 0.0f
    val message = vendingMachine.checkDisplay(total)
    assert(message == "Insert Coins")
  }

  it should "display the total value of all inserted coins" in {
    var total = 0.0f
    total = vendingMachine.insertCoin("quarter", total) + vendingMachine.insertCoin("quarter", total)
    total = vendingMachine.insertCoin("dime", total)
    assert(total == 0.60f)
    val message = vendingMachine.checkDisplay(total)
    assert(message == "$0.60")
  }

}
