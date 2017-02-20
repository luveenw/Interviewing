package com.interviews.luveen;

/**
 * Created by Luveen Wadhwani on 2/12/2017.
 *
 * Create two threads and run them simultaneously, incrementing a global counter variable.
 *
 * @interview DITTO Initial Technical Assessment Quiz (Offline, Take-home) 02/11/2017
 */
public class TwoThreads {
    private static int counter = 0;

    public static void main(String[] args) {
        Thread thread1 = new Thread () {
            public void run () {
                while (true) {

                    System.out.println(String.format("Thread 1 counter: %d", counter));

                    if (counter == 10) {
                        System.out.println("Thread 1 exiting...");
                        return;
                    }

                    System.out.println(String.format("Thread 1 incrementing counter from %d to %d...", counter, counter + 1));
                    ++counter;
                }
            }
        };

        Thread thread2 = new Thread () {
            public void run () {
                while (true) {

                    System.out.println(String.format("Thread 2 counter: %d", counter));

                    if (counter == 10) {
                        System.out.println("Thread 2 exiting...");
                        return;
                    }

                    System.out.println(String.format("Thread 2 incrementing counter from %d to %d...", counter, counter + 1));
                    ++counter;

                }

            }
        };

        thread1.start();
        thread2.start();
    }
}
