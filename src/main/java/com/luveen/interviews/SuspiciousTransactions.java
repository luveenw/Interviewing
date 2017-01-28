package com.luveen.interviews;

/**
 * Created by luvee on 1/27/2017.
 *
 * Design a system and write code for looking at a time window of online banking transactions and identifying suspicious
 * transactions in that window.
 * A suspicious set of transactions is defined as taking place in different geos within a specified window of time.
 *
 * @interview Udacity Onsite 01/27/2017
 */
public class SuspiciousTransactions {
//# Online banking in multiple geographies.
//# One datacenter in us-east, br-central, europe-west
//# We want to try to flag suspicious activity on accounts.
//            # Account sees activity from two or more geographies within 1 hour of each other
//# Design an architecture for processing activity stream and flagging accounts
//# implement the account flagging logic
//    {'account_id': 12345, 'geo': 'us-east', timestamp: ISO8601 Utc time}
//...
//        ...
//        ...
//        ...
//        ...
//
//}
//
//(account1,us-east,12:00) (account1,br-central,1:30)  <-- not suspicious
//        (account1,us-east,12:00) a1,us-east,1:00, (account1,br-central,1:30)  <-- suspicious
//        account1,us-east,1:00;
//
//
//class SuspiciousAccountsRegistry {
//    static void flag(TransactionContext);
//}
//
//class TransactionContext {
//    String accountId;
//    String geo;
//    Timestamp utcTimestamp;
//}
//
//    void findSuspiciousIn(List<TransactionContext> contexts) {
//        // map an accountId to a map of geography -> most recent transaction timestamp
//        Map<String, Map<String, Timestamp>> mostRecentTimestamps = new ...;
//
//        for (TransactionContext ctx : contexts) {
//            Map<String, Timestamp> tx = mostRecentTimestamps.get(ctx.accountId);
//
//            if (tx == null) {
//                mostRecentTimestamps.get(ctx.accountId).put(ctx.geo, ctx.utcTimestamp);
//            }
//            else {
//                if (tx.containsKey(ctx.geo)) {
//                    tx.put(ctx.geo, ctx.utcTimestamp);
//                }
//                else {
//                    if (tx.keySet().size() > 1) {
//                        // pairwise compare each geo's most recent timestamp
//                    }
//                    // if there are any OTHER geos, compare delta and flag
//                    SuspiciousAccountsRegistry.flag(ctx);
//                }
//            }
//        }
//    }
}
