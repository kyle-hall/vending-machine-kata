package com.kylehall.vendingmachine

class VendingMachine {

  def insertCoin(coin: String, currentTotal: Float): Float = {
    var newTotal = currentTotal
    if (coin == "dime") newTotal += 0.10f
    else if (coin == "nickel") newTotal += 0.05f
    else if (coin == "quarter") newTotal += 0.25f
    else if (coin == "penny") newTotal += 0.0f
    newTotal
  }

}
