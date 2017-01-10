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
    case -1.0f => "Thank you!"
    case x: Float => formatAmount(x)
  }

  private def formatAmount(amount: Float): String = {
    "$" + amount.toString() + (if (amount.toString().length() == 3) "0" else "")
  }

  def selectProduct(product: String, insertedAmount: Float): (String,String) = {
    val price = products(product)
    val returnedProduct = if (insertedAmount <= price) product else ""
    val message = checkDisplay(-1.0f)
    (returnedProduct, message)
  }

}