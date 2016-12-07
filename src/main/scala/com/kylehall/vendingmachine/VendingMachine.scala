package com.kylehall.vendingmachine

class VendingMachine {

  def insertCoin(coin: String, currentTotal: Float): Float = coin match {
    case "dime" => currentTotal + 0.10f
    case "nickel" => currentTotal + 0.05f
    case "quarter" => currentTotal + 0.25f
    case _ => 0.0f
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

  def selectProduct(product: String): String = {
    return product
  }

}
