package com.kylehall.vendingmachinetest

import com.kylehall.vendingmachine.VendingMachine

class VendingMachineSpec extends UnitSpec {

  "A VendingMachine" should "accept dimes and value them at 10 cents" in {
    val vendingMachine = new VendingMachine()
    val total = vendingMachine.insertCoin("dime")
    assert(total == 10)
  }

  it should "accept nickels and value them at 5 cents" in {
    val vendingMachine = new VendingMachine()
    val total = vendingMachine.insertCoin("nickel")
    assert(total == 5)
  }

}
