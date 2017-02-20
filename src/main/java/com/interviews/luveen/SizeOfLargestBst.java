package com.interviews.luveen;

/**
 * Created by Luveen Wadhwani on 2/2/2017.
 *
 * Given a binary tree's root node, determine the number of nodes in the largest BST contained within that tree.
 *
 * @interview ? Interviewing.io Screen 2/2/2017
 */
public class SizeOfLargestBst {
    public static void main(String[] args) {
        Node a = new Node(25);
        Node b = new Node(18);
        Node c = new Node(50);
        Node d = new Node(19);
        Node e = new Node(20);
        Node f = new Node(35);
        Node g = new Node(60);
        Node h = new Node(15);
        Node i = new Node(18);
        Node j = new Node(25);
        Node k = new Node(20);
        Node l = new Node(40);
        Node m = new Node(55);
        Node n = new Node(70);
        Node o = new Node(25);

        a.left = b;
        a.right = c;
        b.left = d;
        b.right = e;
        c.left = f;
        c.right = g;
        d.right = h;
        e.left = i;
        e.right = j;
        f.left = k;
        f.right = l;
        g.left = m;
        g.right = n;
        k.right = o;

        System.out.println(String.format("Size of largest BST: %d", findSizeOfLargestBst(a)));
    }

    private static int findSizeOfLargestBst(Node root) {
        return inorderHelper(root).largestBstSize;
    }

    private static Result inorderHelper(Node node) {
        if (node == null) {
            return null;
        }

        Result leftResult = inorderHelper(node.left);
        Result rightResult = inorderHelper(node.right);

        // At a leaf node - return result representing BST with the one current node
        if (leftResult == null && rightResult == null) {
            return new Result(node, true, 1, node.value, node.value);
        }

        // Both left and right subtrees present - evaluate both to determine result
        if (leftResult != null && rightResult != null) {
            boolean isBst = isLeftBst(node, leftResult) && isRightBst(node, rightResult);
            int size = isBst ? 1 + leftResult.largestBstSize + rightResult.largestBstSize : Math.max(leftResult.largestBstSize, rightResult.largestBstSize);
            int min = isBst ? Math.min(node.value, Math.min(leftResult.minSoFar, rightResult.minSoFar)) : Integer.MIN_VALUE;
            int max = isBst ? Math.max(node.value, Math.max(leftResult.maxSoFar, rightResult.maxSoFar)) : Integer.MAX_VALUE;

            return new Result(node, isBst, size, min, max);
        }

        // Only right subtree present - return result with its information
        if (leftResult == null) {
            if (isRightBst(node, rightResult)) {
                return new Result(node, true, rightResult.largestBstSize + 1, node.value, rightResult.maxSoFar);
            }

            return new Result(node, false, rightResult.largestBstSize, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }

        if (isLeftBst(node, leftResult)) {
            return new Result(node, true, leftResult.largestBstSize + 1, leftResult.minSoFar, node.value);
        }

        return new Result(node, false, leftResult.largestBstSize, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static boolean isRightBst(Node node, Result right) {
        return right.isBst && right.minSoFar >= node.value;
    }

    private static boolean isLeftBst(Node node, Result leftResult) {
        return leftResult.isBst && leftResult.maxSoFar < node.value;
    }

    private static class Node {
        int value;
        Node left;
        Node right;

        private Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    private static class Result {
        Node node;
        boolean isBst;
        int largestBstSize;
        int minSoFar;
        int maxSoFar;

        private Result(Node node, boolean isBst, int largestBstSize, int minSoFar, int maxSoFar) {
            this.node = node;
            this.isBst = isBst;
            this.largestBstSize = largestBstSize;
            this.minSoFar = minSoFar;
            this.maxSoFar = maxSoFar;
        }
    }
}
