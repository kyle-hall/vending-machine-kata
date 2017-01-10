package com.kylehall.vendingmachine

import Coins._

class CoinOp {

  def insertCoin(coin: String, currentTotal: Float): Float = coin match {
    case DIME => currentTotal + coins(DIME)
    case NICKEL => currentTotal + coins(NICKEL)
    case QUARTER => currentTotal + coins(QUARTER)
    case _ => coins(PENNY)
  }

  def returnCoins(currentTotal: Float): Float = {
    return coins(PENNY)
  }

}
