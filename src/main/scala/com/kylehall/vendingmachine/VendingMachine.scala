package com.kylehall.vendingmachine

class VendingMachine {

  def insertCoin(coin: String, currentTotal: Float): Float = {
    var newTotal = currentTotal
    if (coin == "dime") newTotal += 0.10f
    else if (coin == "nickel") newTotal += 0.05f
    else if (coin == "quarter") newTotal += 0.25f
    else newTotal += 0.0f
    newTotal
  }

  def returnCoins(currentTotal: Float): Float = {
    return 0.0f
  }

  def checkDisplay(currentTotal: Float): String = currentTotal match {
    case 0.0f => "Insert Coins"
    case x: Float => formatAmount(x)
  }

  private def formatAmount(amount: Float): String = {
    "$" + amount.toString() + (if (amount.toString().length() == 3) "0" else "")
  }

}
