package com.kylehall.vendingmachine

import Products._

class VendingMachine {

  val coinOp = new CoinOp()
  val display = new Display()

  def selectProduct(product: String, insertedAmount: Float): (String,String) = {
    val price = products(product)
    val returnedProduct = if (insertedAmount >= price) product else ""
    val message = display.displayMessage(product, returnedProduct)
    (returnedProduct, message)
  }

}