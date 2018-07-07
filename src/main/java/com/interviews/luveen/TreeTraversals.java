package com.interviews.luveen;

import java.util.LinkedList;

/**
 * Youtube link - https://youtu.be/nzmtCFNae9k
 * Youtube link - https://youtu.be/elQcrJrfObg
 * Youtube link - https://youtu.be/qT65HltK2uE
 * Youtube link - https://youtu.be/ZM-sV9zQPEs
 * 
 * http://www.geeksforgeeks.org/inorder-tree-traversal-without-recursion/
 * http://www.geeksforgeeks.org/inorder-tree-traversal-without-recursion-and-without-stack/
 * http://www.geeksforgeeks.org/iterative-preorder-traversal/
 */
public class TreeTraversals {

    public void inOrder(Node root){
        if(root == null){
            return;
        }
        inOrder(root.left);
        System.out.print(root.data + " ");
        inOrder(root.right);
    }
    
    public void preOrder(Node root){
        if(root == null){
            return;
        }
        System.out.print(root.data + " ");
        preOrder(root.left);
        preOrder(root.right);
    }
    
    public void postOrder(Node root){
        if(root == null){
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.data + " ");
    }

    public void inorderItr(Node root){
        LinkedList<Node> stack = new LinkedList<Node>();
        Node node = root;
        while(true){
            if(node != null){
                stack.addFirst(node);
                node = node.left;
            }
            else{
                if(stack.isEmpty()){
                    break;
                }
                node = stack.pollFirst();
                System.out.println(node.data);
                node = node.right;
            }
        }
    }
    
    public void preOrderItr(Node root){
        LinkedList<Node> stack = new LinkedList<Node>();
        stack.addFirst(root);
        while(!stack.isEmpty()){
            root = stack.pollFirst();
            System.out.print(root.data + " ");
            if(root.right != null){
                stack.addFirst(root.right);
            }
            if(root.left!= null){
                stack.addFirst(root.left);
            }
        }
    }
    
    public void postOrderItr(Node root){
        LinkedList<Node> stack1 = new LinkedList<Node>();
        LinkedList<Node> stack2 = new LinkedList<Node>();
        stack1.addFirst(root);
        while(!stack1.isEmpty()){
            root = stack1.pollFirst();
            if(root.left != null){
                stack1.addFirst(root.left);
            }
            if(root.right != null){
                stack1.addFirst(root.right);
            }
            stack2.addFirst(root);
        }
        while(!stack2.isEmpty()){
            System.out.print(stack2.pollFirst().data + " ");
        }
    }
    
    public void postOrderItrOneStack(Node root){
        Node current = root;
        LinkedList<Node> stack = new LinkedList<>();
        while(current != null || !stack.isEmpty()){
            if(current != null){
                stack.addFirst(current);
                current = current.left;
            }else{
                Node temp = stack.peek().right;
                if (temp == null) {
                    temp = stack.poll();
                    System.out.print(temp.data + " ");
                    while (!stack.isEmpty() && temp == stack.peek().right) {
                        System.out.println();
                        System.out.println(String.format("temp (%d) == stack.peek().right (%d)", temp.data, stack.peek().right.data));
                        temp = stack.poll();
                        System.out.print(temp.data + " ");
                    }
                } else {
                    current = temp;
                }
            }
        }
    }

    public void postOrderItrOneStack1(Node root){
        LinkedList<Node> stack = new LinkedList<>();
        Node cur = root;

        while (true) {
            if (cur != null) {
                stack.addFirst(cur);
                cur = cur.left;
            } else if (stack.isEmpty()) {
                break;
            } else {
                Node top = stack.peek();

                if (top.right == cur) {
                    stack.removeFirst();
                    System.out.print(top.data + " ");
                } else {
                    cur = top;
                }
            }
        }
    }

    public static void main(String args[]){
        BinaryTree bt = new BinaryTree();
        Node head = null;
/*
        head = bt.addNode(10, head);
        head = bt.addNode(15, head);
        head = bt.addNode(19, head);
        head = bt.addNode(17, head);
        head = bt.addNode(11, head);

        head = bt.addNode(-11, head);
*/

        head = bt.addNode(30, head);
        head = bt.addNode(20, head);
        head = bt.addNode(35, head);
        head = bt.addNode(17, head);
        head = bt.addNode(27, head);
        head = bt.addNode(34, head);
        head = bt.addNode(37, head);
        head = bt.addNode(16, head);
        head = bt.addNode(19, head);
        head = bt.addNode(32, head);
        head = bt.addNode(45, head);

        TreeTraversals tt = new TreeTraversals();
//        tt.postOrder(head);
//        System.out.println();
//        tt.postOrderItr(head);
//        System.out.println();
        tt.postOrderItrOneStack1(head);
//        tt.postOrderItrOneStack(head);
    }

    private static class Node{
        Node left;
        Node right;
        Node next;
        int data;
        int lis;
        int height;
        int size;

        public static Node newNode(int data){
            Node n = new Node();
            n.left = null;
            n.right = null;
            n.data = data;
            n.lis = -1;
            n.height = 1;
            n.size = 1;
            return n;
        }
    }

    private static class BinaryTree {
        public Node addNode(int data, Node head){
            Node tempHead = head;
            Node n = Node.newNode(data);
            if(head == null){
                head = n;
                return head;
            }
            Node prev = null;
            while(head != null){
                prev = head;
                if(head.data < data){
                    head = head.right;
                }else{
                    head = head.left;
                }
            }
            if(prev.data < data){
                prev.right = n;
            }else{
                prev.left = n;
            }
            return tempHead;
        }

        class IntegerRef{
            int height;
        }

        public int height(Node root){
            if(root == null){
                return 0;
            }
            int leftHeight  = height(root.left);
            int rightHeight = height(root.right);
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }
}
