package com.kylehall.vendingmachinetest

import com.kylehall.vendingmachine.VendingMachine

class VendingMachineSpec extends UnitSpec {

  def vendingMachine = new VendingMachine()

  "A VendingMachine" should "accept dimes and value them at 10 cents" in {
    val total = vendingMachine.insertCoin("dime")
    assert(total == 0.10f)
  }

  it should "accept nickels and value them at 5 cents" in {
    val total = vendingMachine.insertCoin("nickel")
    assert(total == 0.05f)
  }

  it should "accept quarters and value them at 25 cents" in {
    val total = vendingMachine.insertCoin("quarter")
    assert(total == 0.25f)
  }

  it should "reject pennies and value them at 0 cents" in {
    val total = vendingMachine.insertCoin("penny")
    assert(total == 0.0f)
  }

}
