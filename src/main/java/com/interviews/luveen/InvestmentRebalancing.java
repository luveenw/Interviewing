package com.interviews.luveen;

import java.util.ArrayList;

/**
 * Created by Luveen Wadhwani on 2/14/2017.
 *
 * Given current investment amounts in stocks and bonds, and target investment percentages for each, return advice on
 * what amount of stocks and bonds to sell or buy.
 *
 * @interview LendUp Tech Phone Screen 1 2017/02/14
 */
public class InvestmentRebalancing {
/*


$1000

80% stocks
20% bonds
rebalance every year

Feb 2016
$800 stock mutual fund
$200 bond mutual fund

Feb 2017
$1200 stock
$210 bond


sell $75 of stock and buy $75 bonds


 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

        public static void main(String[] args) {
            ArrayList<String> strings = new ArrayList<String>();
            strings.add("Hello, World!");
            strings.add("Welcome to CoderPad.");
            strings.add("This pad is running Java 8.");

            for (String string : strings) {
                System.out.println(string);
            }
        }

        private static Advice rebalance(double curAmtInStock, double curAmtInBonds, double percentStock, double percentBonds) {

            Advice result = new Advice();

            double totAmt = curAmtInStock + curAmtInBonds;
            double targetAmtInStock = percentStock * totAmt;
            double targetAmtInBonds = percentBonds * totAmt;

            result.setStockRebalanceAmt(targetAmtInStock - curAmtInStock);
            result.setBondsRebalanceAmt(targetAmtInBonds - curAmtInBonds);

            return result;
        }

    private static class Advice {
        private double stockRebalanceAmt;
        private double bondsRebalanceAmt;

        public double getStockRebalanceAmt() {
            return stockRebalanceAmt;
        }

        public void setStockRebalanceAmt(double stockRebalanceAmt) {
            this.stockRebalanceAmt = stockRebalanceAmt;
        }

        public double getBondsRebalanceAmt() {
            return bondsRebalanceAmt;
        }

        public void setBondsRebalanceAmt(double bondsRebalanceAmt) {
            this.bondsRebalanceAmt = bondsRebalanceAmt;
        }
    }
}
