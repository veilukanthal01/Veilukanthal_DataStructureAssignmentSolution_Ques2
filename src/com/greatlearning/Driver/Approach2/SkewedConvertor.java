package com.greatlearning.Driver.Approach2;


import java.io.*;
import java.util.Scanner;

class Node {
	int val;
	Node left, right;

	Node(int item) {
		val = item;
		left = right = null;
	}
}

public class SkewedConvertor{
	public Node node;
	public Node prevNode = null;
	public Node headNode = null;
	
	void insert(int key) { node = insertRec(node, key); }
	 
    /* A recursive function to
       insert a new key in BST */
	Node insertRec( Node node, int key)
    {
 
        /* If the tree is empty,
           return a new node */
        if (node == null) {
        	node = new Node(key);
            return node;
        }
 
        /* Otherwise, recur down the tree */
        if (key < node.val)
        	node.left = insertRec(node.left, key);
        else if (key > node.val)
        	node.right = insertRec(node.right, key);
 
        /* return the (unchanged) node pointer */
       return node;
    }

	void convertBSTToRightSkewedTree(Node root) {

		if (root == null) {
			return;
		}
		//Recurse to its left node if it exists, to get smaller value.
		convertBSTToRightSkewedTree(root.left);

		Node rightNode = root.right;

		//After the complete traversal of its left subtree, connect the previous node of the skewed tree to the root node.
		if (headNode == null) {
			headNode = root;
			root.left = null;
			prevNode = root;
		} else {
			prevNode.right = root;
			root.left = null;
			prevNode = root;
		}
		//Recurse to the right node if it exists, for larger values.
		convertBSTToRightSkewedTree(rightNode);
	}

	void traverseRightSkewedTree(Node root) {
		if (root == null) {
			return;
		}
		System.out.print(root.val + " ");
		traverseRightSkewedTree(root.right);
	}

	// Driver Code
	public static void main(String[] args) {

		SkewedConvertor tree = new SkewedConvertor();
		int n = 0;

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the number of elements you want to process: ");
		n = sc.nextInt();
		int[] array = new int[n];

		if (n == 0) {
			while (true) {
				System.out.println("The number of elements you want to process should be greater than zero: Please enter valid number again");
				n = sc.nextInt();
				if (n != 0)
					break;
			}
		}

		for (int i = 0; i < n; i++) {
			System.out.println("Enter the element: " + (i+1));
			array[i] = sc.nextInt();
			if (array[i] != 0)
				tree.insert(array[i]);
			if (array[i] == 0) {
				while (true) {
					System.out.println("Element value must be greater than zero: ");
					array[i] = sc.nextInt();
					if (array[i] != 0) {
						tree.insert(array[i]);
						break;
					}
				}
			}
		}

		//Performs inorder Traversal and arranges elements to form skewed tree
		tree.convertBSTToRightSkewedTree(tree.node);
		tree.traverseRightSkewedTree(tree.headNode);
		sc.close();
	}
}