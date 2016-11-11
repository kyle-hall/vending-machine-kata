package com.kylehall.vendingmachinetest

import com.kylehall.vendingmachine.VendingMachine

class VendingMachineSpec extends UnitSpec {

  def vendingMachine = new VendingMachine()

  "A VendingMachine" should "accept dimes and value them at 10 cents" in {
    val total = vendingMachine.insertCoin("dime", 0.0f)
    assert(total == 0.10f)
  }

  it should "accept nickels and value them at 5 cents" in {
    val total = vendingMachine.insertCoin("nickel", 0.0f)
    assert(total == 0.05f)
  }

  it should "accept quarters and value them at 25 cents" in {
    val total = vendingMachine.insertCoin("quarter", 0.0f)
    assert(total == 0.25f)
  }

  it should "reject pennies and value them at 0 cents" in {
    val total = vendingMachine.insertCoin("penny", 0.0f)
    assert(total == 0.0f)
  }

  it should "accept multiple coins and keep running total" in {
    var total = 0.0f
    total = vendingMachine.insertCoin("dime", total)
    assert(total == 0.10f)
    total = vendingMachine.insertCoin("quarter", total)
    assert(total == 0.35f)
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

}
