package com.luveen.interviews;

import java.util.*;

/**
 * Created by Luveen Wadhwani on 10/22/2016.
 *
 * Given two almost identical trees that differ only in their leaf nodes, find the first leaf nodes at which they differ,
 * and return the differing nodes.
 */
public class TreeDiff {
    private Pair<Node> findMismatchedNodes(Node root1, Node root2) {
        Pair<Node> result = null;
        LinkedList<Node> q1 = new LinkedList<>();
        LinkedList<Node> q2 = new LinkedList<>();

        q1.addLast(root1);
        q2.addLast(root2);

        while (!q1.isEmpty() && !q2.isEmpty()) {
            Node x = q1.remove();
            Node y = q2.remove();

            if (!x.equals(y)) {
                result = new Pair<>(x, y);
                break;
            }

            if (x.left != null) {
                q1.addLast(x.left);
            }
            if (x.right != null) {
                q1.addLast(x.right);
            }
            if (y.left != null) {
                q2.addLast(y.left);
            }
            if (y.right != null) {
                q2.addLast(y.right);
            }
        }

        if(result == null) {
            if (!q1.isEmpty()) {
                result = new Pair<>(q1.remove(), null);
            }
            else if (!q2.isEmpty()){
                result = new Pair<>(null, q2.remove());
            }
            // if both queues are empty and result is null, the two trees are identical
        }

        return result;
    }

    private static class Pair<T> {
        T one;
        T two;

        Pair(T one, T two) {
            this.one = one;
            this.two = two;
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.one, this.two);
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Pair<?>)) {
                return false;
            }

            Pair<?> that = (Pair<?>) o;

            return Objects.equals(this.one, that.one) && Objects.equals(this.one, this.two);
        }

        @Override
        public String toString() {
            String oneString = this.one == null ? null : this.one.toString();
            String twoString = this.two == null ? null : this.two.toString();

            return "[ " + oneString + ", " + twoString + " ]";
        }
    }

    private static class Node {
        Integer value;
        Node left;
        Node right;

        Node (int value) {
            this.value = value;
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.value);
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Node)) {
                return false;
            }

            Node that = (Node) o;

            return Objects.equals(this.value, that.value);
        }

        @Override
        public String toString() {
            if (this.value == null) {
                return null;
            }

            return this.value.toString();
        }
    }

    public static void main(String[] args) {
        Node[] t = new Node[5];

        t[0] = createTree1();
        t[1] = createTree2();
        t[2] = createTree3();
        t[3] = createTree4();
        t[4] = createTree5();

        TreeDiff td = new TreeDiff();

        for (int i = 0; i < t.length - 1; i++) {
            for (int j = i + 1; j < t.length; j++) {
                System.out.println(String.format(
                        "Mismatched nodes between tree %d and tree %d : %s", i + 1, j + 1, td.findMismatchedNodes(t[i], t[j])));
            }
        }
    }

    private static Node createTree1() {
        Node a = new Node(1);
        Node b = new Node(2);
        Node c = new Node(3);
        Node d = new Node(4);
        Node e = new Node(5);
        Node f = new Node(6);
        Node g = new Node(7);
        Node h = new Node(8);
        Node i = new Node(9);
        Node j = new Node(10);

        a.left = b;
        a.right = c;
        b.left = d;
        b.right = e;
        c.left = f;
        c.right = g;
        d.left = h;
        d.right = i;
        e.left = j;

        return a;
    }

    private static Node createTree2() {
        Node a = new Node(1);
        Node b = new Node(2);
        Node c = new Node(3);
        Node d = new Node(4);
        Node e = new Node(5);
        Node f = new Node(6);
        Node g = new Node(7);
        Node h = new Node(8);
        Node i = new Node(9);

        a.left = b;
        a.right = c;
        b.left = d;
        b.right = e;
        c.left = f;
        c.right = g;
        d.left = h;
        d.right = i;

        return a;
    }

    private static Node createTree3() {
        Node a = new Node(1);
        Node c = new Node(3);
        Node d = new Node(4);
        Node e = new Node(5);
        Node f = new Node(6);
        Node g = new Node(7);
        Node h = new Node(8);
        Node i = new Node(9);
        Node j = new Node(10);

        a.left = j;
        a.right = c;
        j.left = d;
        j.right = e;
        c.left = f;
        c.right = g;
        d.left = h;
        d.right = i;

        return a;
    }

    private static Node createTree4() {
        Node a = new Node(1);

        return a;
    }

    private static Node createTree5() {
        return null;
    }
}
