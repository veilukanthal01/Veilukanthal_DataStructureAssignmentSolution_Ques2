package com.greatlearning.Driver;

import java.io.*;

class Node {
	int val;
	Node left, right;

	Node(int item) {
		val = item;
		left = right = null;
	}
}

public class Main {
	public Node node;
	Node prevNode = null;
	Node headNode = null;

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

		Main tree = new Main();
		tree.node = new Node(50);
		tree.node.left = new Node(30);
		tree.node.right = new Node(60);
		tree.node.left.left = new Node(10);
		tree.node.right.left = new Node(55);

		tree.convertBSTToRightSkewedTree(tree.node);
		tree.traverseRightSkewedTree(tree.headNode);
	}
}
