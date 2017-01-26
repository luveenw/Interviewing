package com.luveen.interviews;

/**
 * Created by Luveen Wadhwani on 11/10/2016.
 *
 * Traverse a binary tree from its root in inorder fashion to verify that it is a binary search tree.
 */
public class BinarySearchTreeValidation {
    private Node prev = null;

    public boolean isBst(Node cur) {
        return isBst(cur, prev);
    }

    private boolean isBst(Node cur, Node prev) {
        if (cur != null) {
            if (!isBst(cur.left)) return false;

            if (this.prev != null && this.prev.value > cur.value) {
                return false;
            }

            this.prev = cur;
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
