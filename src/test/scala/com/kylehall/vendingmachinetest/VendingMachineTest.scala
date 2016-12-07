package com.kylehall.vendingmachinetest

import com.kylehall.vendingmachine.VendingMachine
import com.kylehall.vendingmachine.Coins

class VendingMachineSpec extends UnitSpec {

  val PENNY = "penny"
  val NICKEL = "nickel"
  val DIME = "dime"
  val QUARTER = "quarter"

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
    val total = vendingMachine.insertCoin("penny", Coins.coins(PENNY))
    assert(total == Coins.coins(PENNY))
  }

  it should "accept multiple coins and keep running total" in {
    val dimeTotal = vendingMachine.insertCoin("dime", Coins.coins(PENNY))
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
