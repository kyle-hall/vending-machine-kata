package com.kylehall.vendingmachine

import Products._

class Display {

  def displayMessage(currentTotal: Float): String = currentTotal match {
    case 0.0f => "Insert Coins"
    case x: Float => formatAmount(x)
  }

  def displayMessage(currentProduct: String, returnedProduct: String): String = returnedProduct match {
    case "" => "Price: " + formatAmount(products(currentProduct))
    case _: String => "Thank you!"
  }

  private def formatAmount(amount: Float): String = {
    "$" + amount.toString() + (if (amount.toString().length() == 3) "0" else "")
  }

}
