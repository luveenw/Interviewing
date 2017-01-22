package com.luveen.interviews;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array of daily stock prices, this class determines the best buy and sell days for the stock in order to make
 * a profit.
 */
public class StockInvestmentCalculator {

    private void maximize(List<Integer> dailyPrices) {
        int minPrice = dailyPrices.get(0);
        int buyDay = 0;
        int sellDay = 0;
        int profit = 0;
        int curPrice;

        for (int i = 0; i < dailyPrices.size(); i++) {
            curPrice = dailyPrices.get(i);

            if (curPrice < minPrice) {
                minPrice = curPrice;
                buyDay = i;
            }

            int curProfit = curPrice - minPrice;

            if (curProfit > profit) {
                profit = curProfit;
                sellDay = i;
            }
        }

        if (buyDay > sellDay) {
            sellDay = buyDay;
        }

        System.out.println(
                String.format(
                        "Buy on day %d, sell on day %d to make the max profit possible, %d",
                        buyDay,
                        sellDay,
                        profit));
    }

    public static void main(String[] args) {
        StockInvestmentCalculator calc = new StockInvestmentCalculator();
        List<Integer> stock1 = new ArrayList<>(Arrays.asList(10, 30, 40, 50, 39, 27, 25, 5, 100));
        List<Integer> stock2 = new ArrayList<>(Arrays.asList(100, 80, 70, 60, 55, 27, 19));
        List<Integer> stock3 = new ArrayList<>(Arrays.asList(10, 30, 40, 50, 25, 27, 39));
        List<Integer> stock4 = new ArrayList<>(Arrays.asList(10, 30, 40, 50, 25, 27, 39));

        calc.maximize(stock1);
        calc.maximize(stock2);
        calc.maximize(stock3);
        calc.maximize(stock4);
    }
}
