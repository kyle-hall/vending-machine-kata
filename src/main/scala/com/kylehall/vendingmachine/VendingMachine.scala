package com.kylehall.vendingmachine

class VendingMachine {

  def insertCoin(coin: String): Float = {
    var total = 0.0f
    if (coin == "dime") total += 0.10f
    else if (coin == "nickel") total += 0.05f
    else if (coin == "quarter") total += 0.25f
    else if (coin == "penny") total += 0.0f
    total
  }

}
