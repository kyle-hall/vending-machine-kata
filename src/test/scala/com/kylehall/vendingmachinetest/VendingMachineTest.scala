package com.kylehall.vendingmachinetest

import com.kylehall.vendingmachine.VendingMachine
import com.kylehall.vendingmachine.Coins
import com.kylehall.vendingmachine.Coins.{PENNY, NICKEL, DIME, QUARTER}

import com.kylehall.vendingmachine.Products.{CANDY, COLA, CHIPS}

class VendingMachineSpec extends UnitSpec {

  val SIXTY_CENT_DISPLAY = "$0.60"
  val INSERT_COINS = "Insert Coins"

  val DIME_PLUS_QUARTER = 0.35f
  val FIFTY_CENTS = 0.50f
  val SIXTY_CENTS = 0.60f
  val SIXTY_FIVE_CENTS = 0.65f
  val ONE_DOLLAR = 1.00f

  def vendingMachine = new VendingMachine()

  "A VendingMachine" should "accept dimes and value them at 10 cents" in {
    val total = vendingMachine.insertCoin(DIME, Coins.coins(PENNY))
    assert(total == Coins.coins(DIME))
  }

  it should "accept nickels and value them at 5 cents" in {
    val total = vendingMachine.insertCoin(NICKEL, Coins.coins(PENNY))
    assert(total == Coins.coins(NICKEL))
  }

  it should "accept quarters and value them at 25 cents" in {
    val total = vendingMachine.insertCoin(QUARTER, Coins.coins(PENNY))
    assert(total == Coins.coins(QUARTER))
  }

  it should "reject pennies and value them at 0 cents" in {
    val total = vendingMachine.insertCoin(PENNY, Coins.coins(PENNY))
    assert(total == Coins.coins(PENNY))
  }

  it should "accept multiple coins and keep running total" in {
    val dimeTotal = vendingMachine.insertCoin(DIME, Coins.coins(PENNY))
    assert(dimeTotal == Coins.coins(DIME))
    val plusQuarterTotal = vendingMachine.insertCoin(QUARTER, dimeTotal)
    assert(plusQuarterTotal == DIME_PLUS_QUARTER)
  }

  it should "return all coins if requested" in {
    val twoQuarters = vendingMachine.insertCoin(QUARTER, Coins.coins(PENNY)) +
      vendingMachine.insertCoin(QUARTER, Coins.coins(PENNY))
    assert(twoQuarters == FIFTY_CENTS)
    val totalAfterReturnCoins = vendingMachine.returnCoins(twoQuarters)
    assert(totalAfterReturnCoins == Coins.coins(PENNY))
  }

  it should "display 'Insert Coins' if no coins have been entered and the display is checked" in {
    val message = vendingMachine.checkDisplay(Coins.coins(PENNY))
    assert(message == INSERT_COINS)
  }

  it should "display the total value of all inserted coins" in {
    val sixtyCents = vendingMachine.insertCoin(QUARTER, Coins.coins(PENNY)) +
      vendingMachine.insertCoin(QUARTER, Coins.coins(PENNY)) +
      vendingMachine.insertCoin(DIME, Coins.coins(PENNY))
    assert(sixtyCents == SIXTY_CENTS)
    val message = vendingMachine.checkDisplay(sixtyCents)
    assert(message == SIXTY_CENT_DISPLAY)
  }

  it should "allow the customer to select a product and return it to them" in {
    val oneDollar = vendingMachine.insertCoin(QUARTER, Coins.coins(PENNY)) +
      vendingMachine.insertCoin(QUARTER, Coins.coins(PENNY)) +
      vendingMachine.insertCoin(QUARTER, Coins.coins(PENNY)) +
      vendingMachine.insertCoin(QUARTER, Coins.coins(PENNY))
    assert(oneDollar == ONE_DOLLAR)
    val product = vendingMachine.selectProduct(COLA, oneDollar)
    assert(product == COLA)
  }

  it should "allow the customer to select chips and have chips returned to them" in {
    val fiftyCents = vendingMachine.insertCoin(QUARTER, Coins.coins(PENNY)) +
      vendingMachine.insertCoin(QUARTER, Coins.coins(PENNY))
    assert(fiftyCents == FIFTY_CENTS)
    val product = vendingMachine.selectProduct(CHIPS, fiftyCents)
    assert(product == CHIPS)
  }

  it should "allow the customer to select candy and receive candy" in {
    val sixtyFiveCents = vendingMachine.insertCoin(QUARTER, vendingMachine.insertCoin(QUARTER, vendingMachine.insertCoin(NICKEL, vendingMachine.insertCoin(DIME, Coins.coins(PENNY)))))
    assert(sixtyFiveCents == SIXTY_FIVE_CENTS)

    val product = vendingMachine.selectProduct(CANDY, sixtyFiveCents)

    assert(product == CANDY)
  }

}
