package com.interviews.luveen;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

import java.util.*;

/**
 * Paginates a collection of Airbnb listings, ensuring a page does not repeat host IDs.
 *
 * @interview Airbnb Phone 1 11/07/2016
 */
public class ListingPagination {
    private static final int PAGE_SIZE = 5;

    // 14 listings; some repeated host ids in page 1
    private static final List<String> LISTINGS_1 = Lists.newArrayList(
            "1,2,40",
            "2,2,38",
            "3,1,37",
            "4,9,30",
            "5,7,27",
            "6,5,17",
            "7,10,14",
            "8,7,14",
            "9,6,13",
            "10,3,12",
            "11,8,10",
            "12,9,9",
            "13,1,7",
            "14,3,3"
    );

    // 11 listings; less than page size
    private static final List<String> LISTINGS_2 = Lists.newArrayList(
            "1,2,40",
            "2,2,38",
            "3,1,37",
            "4,9,30",
            "5,7,27",
            "6,5,17",
            "7,10,14",
            "8,7,14",
            "9,6,13",
            "10,3,12",
            "11,8,10"
    );

    // 12 listings; exactly page size
    private static final List<String> LISTINGS_3 = Lists.newArrayList(
            "1,2,40",
            "2,2,38",
            "3,1,37",
            "4,9,30",
            "5,7,27",
            "6,5,17",
            "7,10,14",
            "8,7,14",
            "9,6,13",
            "10,3,12",
            "11,8,10",
            "12,9,9"
    );

    public static void main(String[] args) {
        System.out.println("Listings 1...");
        foo(toListings(LISTINGS_1));
        print(paginate2(toListings(LISTINGS_1)));
        System.out.println();
//        System.out.println("Listings 2...");
//        print(paginate(toListings(LISTINGS_2)));
//        System.out.println();
//        System.out.println("Listings 3...");
//        print(paginate(toListings(LISTINGS_3)));
//        System.out.println();
    }

    private static List<Listing> _WRONG_paginate_WRONG_(List<Listing> listings) {
        int numPages = (int) Math.ceil((double)(listings.size()) / (double)(PAGE_SIZE));

        List<Listing> leftoverListings = new ArrayList<>();
        List<Listing> result = new ArrayList<>();
        Set<Integer> pageHostIds = new HashSet<>();
        int pageIndex = 0;
        int listIndex = 0;
        boolean isUsedLeftoversThisPage = false;

        // Fill everything but the last page - we will handle the last page separately since it is a special snowflake
        while (listIndex < (numPages - 1) * PAGE_SIZE - 1 ) {

            // Use any previously encountered listings that needed to be saved for later, if possible
            if (!isUsedLeftoversThisPage) {
                for (int i = 0; i < leftoverListings.size() && pageIndex < PAGE_SIZE - 1; i++) {
                    Listing leftover = leftoverListings.get(i);

                    if (!pageHostIds.contains(leftover.hostId)) {
                        result.add(leftover);
                        leftoverListings.remove(leftover);
                        pageIndex++;
                    }
                }

                isUsedLeftoversThisPage = true;
            }

            // If we haven't yet filled a page, use listings from the input list
            if (pageIndex < PAGE_SIZE) {
                Listing l = listings.get(listIndex);

                if (!pageHostIds.contains(l.hostId)) {
                    result.add(l);
                    pageHostIds.add(l.hostId);
                    pageIndex++;
                } else {
                    leftoverListings.add(l);
                }

                listIndex++;
            }

            // If we managed to fill a page, reset page management state
            if (pageIndex == PAGE_SIZE - 1) {
                pageIndex = 0;
                pageHostIds.clear();
                isUsedLeftoversThisPage = false;
            }
        }

        // We've reached the last page. Here, we:
        // 1. Use any leftover listings
        // 2. Use the rest of the input list

        if (!leftoverListings.isEmpty()) {
            result.addAll(leftoverListings);
        }

        for (; listIndex <= listings.size() - 1; listIndex++) {
            result.add(listings.get(listIndex));
        }

        return result;
    }

    private static List<Listing> paginate2(List<Listing> listings) {
        int numPages = (int) Math.ceil((double)(listings.size()) / (double)(PAGE_SIZE));

        List<Listing> leftoverListings = new ArrayList<>();
        List<Listing> result = new ArrayList<>();
        Set<Integer> pageHostIds = new HashSet<>();
        int pageIndex = 0;
        int listIndex = 0;
        boolean isUsedLeftoversThisPage = false;

        // Fill everything but the last page - we will handle the last page separately since it is a special snowflake
        while (listIndex < ((numPages - 1) * PAGE_SIZE) ) {

            // Use any previously encountered listings that needed to be saved for later, if possible
            if (!isUsedLeftoversThisPage && !leftoverListings.isEmpty()) {
                for (int i = 0; i < leftoverListings.size() && pageIndex < PAGE_SIZE; i++) {
                    Listing leftover = leftoverListings.get(i);

                    if (!pageHostIds.contains(leftover.hostId)) {
                        result.add(leftover);
                        leftoverListings.remove(leftover);
                        pageIndex++;
                    }
                }

                isUsedLeftoversThisPage = true;
            }

            // If we haven't yet filled a page, use listings from the input list
            if (pageIndex < PAGE_SIZE) {
                Listing l = listings.get(listIndex);

                if (!pageHostIds.contains(l.hostId)) {
                    result.add(l);
                    pageHostIds.add(l.hostId);
                    pageIndex++;
                } else {
                    leftoverListings.add(l);
                }

                listIndex++;
            }

            // If we managed to fill a page, reset page management state
            if (pageIndex == PAGE_SIZE) {
                pageIndex = 0;
                pageHostIds.clear();
                isUsedLeftoversThisPage = false;
            }
        }

        // We've reached the last page. Here, we:
        // 1. Use any leftover listings
        // 2. Use the rest of the input list

        if (!leftoverListings.isEmpty()) {
            result.addAll(leftoverListings);
        }

        for (; listIndex <= listings.size() - 1; listIndex++) {
            result.add(listings.get(listIndex));
        }

        return result;
    }

    private static String[] foo(List<Listing> listings) {
        String[] result = listings.stream().map(Listing::toString).toArray(String[]::new);

        return result;
    }

    private static List<Listing> toListings(List<String> listingStrs) {
        return Lists.newArrayList(Collections2.transform(listingStrs, new Function<String, Listing>() {
            @Override
            public Listing apply(String s) {
                return new Listing(s);
            }
        }));
    }

    private static void print(List<Listing> listings) {
        for (Listing l : listings) {
            System.out.println(l.toString());
        }
    }

    private static class Listing {
        private int listingId;
        private int hostId;
        private int score;

        Listing (String listingStr) {
            String[] parts = listingStr.split(",");

            this.listingId = Integer.parseInt(parts[0]);
            this.hostId = Integer.parseInt(parts[1]);
            this.score = Integer.parseInt(parts[2]);
        }

        @Override
        public String toString() {
            return String.format("%d,%d,%d", this.listingId, this.hostId, this.score);
        }
    }
}
