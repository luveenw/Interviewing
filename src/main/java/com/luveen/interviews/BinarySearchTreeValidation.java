package com.luveen.interviews;

/**
 * Created by Luveen Wadhwani on 11/10/2016.
 *
 * Traverse a binary tree from its root in inorder fashion to verify that it is a binary search tree.
 */
public class BinarySearchTreeValidation {
    private static Node prev = null;

    private static boolean isBst(Node cur) {
        if (cur != null) {
            if (!isBst(cur.left)) return false;

            if (prev != null && prev.value > cur.value) {
                return false;
            }

            prev = cur;
            return isBst(cur.right);
        }

        return true;
    }

    public static void main(String[] args) {

    }

    private static class Node {
        int value;
        Node left;
        Node right;
    }
}
