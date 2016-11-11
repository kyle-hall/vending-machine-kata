package com.kylehall.vendingmachine

class VendingMachine {

  def insertCoin(coin: String): Int = {
    var total = 0
    if (coin == "dime") total += 10
    else if (coin == "nickel") total += 5
    else if (coin == "quarter") total += 25
    total
  }

}
