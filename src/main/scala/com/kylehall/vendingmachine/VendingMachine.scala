package com.kylehall.vendingmachine

import Products._
import scala.collection.mutable

class VendingMachine {

  val INITIAL_INVENTORY = 3

  val coinOp = new CoinOp()
  val display = new Display()

  val inventory = mutable.Map[String, Int](
    CANDY -> INITIAL_INVENTORY,
    CHIPS -> INITIAL_INVENTORY,
    COLA -> INITIAL_INVENTORY
  )

  def selectProduct(product: String, insertedAmount: Float): (String,String) = {
    val price = products(product)
    val returnedProduct = if (insertedAmount >= price) product else ""
    if (returnedProduct != "") {
      inventory.update(returnedProduct, inventory(returnedProduct) - 1)
    }
    val message = display.displayMessage(product, returnedProduct)
    (returnedProduct, message)
  }

}