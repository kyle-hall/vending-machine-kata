package com.kylehall.vendingmachinetest

import com.kylehall.vendingmachine.VendingMachine
import com.kylehall.vendingmachine.Coins
import com.kylehall.vendingmachine.Coins.{DIME, NICKEL, PENNY, QUARTER}
import com.kylehall.vendingmachine.Products.{CANDY, CHIPS, COLA}
import org.scalatest.BeforeAndAfter

class VendingMachineSpec extends UnitSpec with BeforeAndAfter {

  val SIXTY_CENT_DISPLAY = "$0.60"
  val INSERT_COINS = "Insert Coins"
  val THANK_YOU_MESSAGE = "Thank you!"

  val DIME_PLUS_QUARTER = 0.35f
  val FIFTY_CENTS = 0.50f
  val SIXTY_CENTS = 0.60f
  val SIXTY_FIVE_CENTS = 0.65f
  val ONE_DOLLAR = 1.00f

  val INITIAL_INVENTORY = 3

  val vendingMachine = new VendingMachine()

  before {
    vendingMachine.inventory.update(CANDY, INITIAL_INVENTORY)
    vendingMachine.inventory.update(CHIPS, INITIAL_INVENTORY)
    vendingMachine.inventory.update(COLA, INITIAL_INVENTORY)
  }

  "A VendingMachine" should "accept dimes and value them at 10 cents" in {
    val total = vendingMachine.coinOp.insertCoin(DIME, Coins.coins(PENNY))
    assert(total == Coins.coins(DIME))
  }

  it should "accept nickels and value them at 5 cents" in {
    val total = vendingMachine.coinOp.insertCoin(NICKEL, Coins.coins(PENNY))
    assert(total == Coins.coins(NICKEL))
  }

  it should "accept quarters and value them at 25 cents" in {
    val total = vendingMachine.coinOp.insertCoin(QUARTER, Coins.coins(PENNY))
    assert(total == Coins.coins(QUARTER))
  }

  it should "reject pennies and value them at 0 cents" in {
    val total = vendingMachine.coinOp.insertCoin(PENNY, Coins.coins(PENNY))
    assert(total == Coins.coins(PENNY))
  }

  it should "accept multiple coins and keep running total" in {
    val dimeTotal = vendingMachine.coinOp.insertCoin(DIME, Coins.coins(PENNY))
    assert(dimeTotal == Coins.coins(DIME))
    val plusQuarterTotal = vendingMachine.coinOp.insertCoin(QUARTER, dimeTotal)
    assert(plusQuarterTotal == DIME_PLUS_QUARTER)
  }

  it should "return all coins if requested" in {
    val twoQuarters = vendingMachine.coinOp.insertCoin(QUARTER, Coins.coins(PENNY)) +
      vendingMachine.coinOp.insertCoin(QUARTER, Coins.coins(PENNY))
    assert(twoQuarters == FIFTY_CENTS)
    val totalAfterReturnCoins = vendingMachine.coinOp.returnCoins(twoQuarters)
    assert(totalAfterReturnCoins == Coins.coins(PENNY))
  }

  it should "display 'Insert Coins' if no coins have been entered and the display is checked" in {
    val message = vendingMachine.display.displayMessage(Coins.coins(PENNY))
    assert(message == INSERT_COINS)
  }

  it should "display the total value of all inserted coins" in {
    val sixtyCents = vendingMachine.coinOp.insertCoin(QUARTER, Coins.coins(PENNY)) +
      vendingMachine.coinOp.insertCoin(QUARTER, Coins.coins(PENNY)) +
      vendingMachine.coinOp.insertCoin(DIME, Coins.coins(PENNY))
    assert(sixtyCents == SIXTY_CENTS)
    val message = vendingMachine.display.displayMessage(sixtyCents)
    assert(message == SIXTY_CENT_DISPLAY)
  }

  it should "allow the customer to select a product and return it to them" in {
    val oneDollar = vendingMachine.coinOp.insertCoin(QUARTER, Coins.coins(PENNY)) +
      vendingMachine.coinOp.insertCoin(QUARTER, Coins.coins(PENNY)) +
      vendingMachine.coinOp.insertCoin(QUARTER, Coins.coins(PENNY)) +
      vendingMachine.coinOp.insertCoin(QUARTER, Coins.coins(PENNY))
    assert(oneDollar == ONE_DOLLAR)
    val product = vendingMachine.selectProduct(COLA, oneDollar)._1
    assert(product == COLA)
  }

  it should "allow the customer to select chips and have chips returned to them" in {
    val fiftyCents = vendingMachine.coinOp.insertCoin(QUARTER, Coins.coins(PENNY)) +
      vendingMachine.coinOp.insertCoin(QUARTER, Coins.coins(PENNY))
    assert(fiftyCents == FIFTY_CENTS)
    val product = vendingMachine.selectProduct(CHIPS, fiftyCents)._1
    assert(product == CHIPS)
  }

  it should "allow the customer to select candy and receive candy" in {
    val sixtyFiveCents = vendingMachine.coinOp.insertCoin(QUARTER, vendingMachine.coinOp.insertCoin(QUARTER, vendingMachine.coinOp.insertCoin(NICKEL, vendingMachine.coinOp.insertCoin(DIME, Coins.coins(PENNY)))))
    assert(sixtyFiveCents == SIXTY_FIVE_CENTS)

    val product = vendingMachine.selectProduct(CANDY, sixtyFiveCents)._1

    assert(product == CANDY)
  }

  it should "display THANK YOU after a customer purchases a product" in {
    val sixtyFiveCents = vendingMachine.coinOp.insertCoin(QUARTER, vendingMachine.coinOp.insertCoin(QUARTER, vendingMachine.coinOp.insertCoin(NICKEL, vendingMachine.coinOp.insertCoin(DIME, Coins.coins(PENNY)))))
    assert(sixtyFiveCents == SIXTY_FIVE_CENTS)

    val results = vendingMachine.selectProduct(CANDY, sixtyFiveCents)
    val product = results._1
    val message = results._2
    assert(product == CANDY)
    assert(message == "Thank you!")
  }

  it should "display PRICE and the price of the item if the entered amount is insufficient to purchase the product" in {
    val fiftyCents = vendingMachine.coinOp.insertCoin(QUARTER, Coins.coins(PENNY)) +
      vendingMachine.coinOp.insertCoin(QUARTER, Coins.coins(PENNY))

    assert(fiftyCents == FIFTY_CENTS)

    val results = vendingMachine.selectProduct(CANDY, fiftyCents)
    val product = results._1
    val message = results._2
    assert(product == "")
    assert(message == "Price: $0.65")
  }

  it should "contain an inventory of products with 3 of each product it sells" in {
    val numOfCandies = vendingMachine.inventory(CANDY)
    assert(numOfCandies == INITIAL_INVENTORY)

    val numOfColas = vendingMachine.inventory(COLA)
    assert(numOfCandies == INITIAL_INVENTORY)

    val numOfChips = vendingMachine.inventory(CHIPS)
    assert(numOfChips == INITIAL_INVENTORY)
  }

  it should "remove a purchased item from the inventory" in {
    val sixtyFiveCents = vendingMachine.coinOp.insertCoin(QUARTER, vendingMachine.coinOp.insertCoin(QUARTER, vendingMachine.coinOp.insertCoin(NICKEL, vendingMachine.coinOp.insertCoin(DIME, Coins.coins(PENNY)))))
    assert(sixtyFiveCents == SIXTY_FIVE_CENTS)

    val results = vendingMachine.selectProduct(CANDY, sixtyFiveCents)
    val product = results._1
    val message = results._2
    assert(product == CANDY)
    assert(message == "Thank you!")

    assert(vendingMachine.inventory(CANDY) == 2)
  }
}
