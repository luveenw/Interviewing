package com.luveen.interviews;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by luvee on 1/22/2017.
 *
 * Return the first date of the previous quarter for a specified date.
 *
 * @interview Google Bebop TSC Phone 1 01/07/2017
 */
public class FirstDateOfPreviousQuarter {
    public static void main(String[] args) {
        Arrays.stream(new LocalDate[]{
                LocalDate.of(2017, 3, 13),
                LocalDate.of(2017, 12, 4),
                LocalDate.of(2017, 4, 1),
                LocalDate.of(2017, 8, 20),
        })
                .map(FirstDateOfPreviousQuarter::firstOfPreviousQuarter)
                .forEach(System.out::println);
    }

    private static LocalDate firstOfPreviousQuarter(LocalDate date) {
        int month = date.getMonth().getValue();
        boolean isFirstQuarter = month <= 3;

        return LocalDate.of(
                isFirstQuarter ? date.getYear() - 1 : date.getYear(),
                isFirstQuarter ? 10 : date.getMonth().firstMonthOfQuarter().getValue() - 3,
                1);
    }
}
