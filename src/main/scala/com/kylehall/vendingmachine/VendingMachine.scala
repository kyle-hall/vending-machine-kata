package com.kylehall.vendingmachine

import Products._
import scala.collection.mutable

class VendingMachine {

  val coinOp = new CoinOp()
  val display = new Display()

  val inventory = mutable.Map[String, Int](
    CANDY -> 3,
    CHIPS -> 3,
    COLA -> 3
  )

  def selectProduct(product: String, insertedAmount: Float): (String,String) = {
    val price = products(product)
    val returnedProduct = if (insertedAmount >= price) product else ""
    if (returnedProduct != "") {
      inventory.update(returnedProduct, inventory(returnedProduct) - 1)
      println(s"returnedProduct is ${returnedProduct}")
      println(s"Inventory value for that product is now: ${inventory(returnedProduct)}")
    }
    val message = display.displayMessage(product, returnedProduct)
    (returnedProduct, message)
  }

}