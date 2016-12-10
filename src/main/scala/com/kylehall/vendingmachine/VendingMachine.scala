package com.kylehall.vendingmachine

import Coins._
import Products._

class VendingMachine {

  def insertCoin(coin: String, currentTotal: Float): Float = coin match {
    case DIME => currentTotal + coins(DIME)
    case NICKEL => currentTotal + coins(NICKEL)
    case QUARTER => currentTotal + coins(QUARTER)
    case _ => coins(PENNY)
  }

  def returnCoins(currentTotal: Float): Float = {
    return coins(PENNY)
  }

  def checkDisplay(currentTotal: Float): String = currentTotal match {
    case 0.0f => "Insert Coins"
    case x: Float => formatAmount(x)
  }

  private def formatAmount(amount: Float): String = {
    "$" + amount.toString() + (if (amount.toString().length() == 3) "0" else "")
  }

  def selectProduct(product: String, insertedAmount: Float): String = {
    val returnedProduct = if (product == "cola" && insertedAmount == 1.00f) "cola" else ""
    returnedProduct
  }

}
