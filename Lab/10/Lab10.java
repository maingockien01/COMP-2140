//==============================================================
// Lab10.java
//
// COMP 2140 SUMMER 2020
//
// PURPOSE: Print contents of a 2-3-4 tree, using an inorder traversal,
//          and search for values.
//
// NOTE: We will not cover insertion or deletion code for 2-3-4 trees, although you have 
//       all of the knowledge to write insertion.  This template builds a
//       tree directly from Nodes, and the instance members in the Node class are public.
//       This is not proper programming practice, but will make for a simpler lab. In a
//       2-3-4 tree, insertions should always be done in the leaves.
//==============================================================
import java.util.*;
import java.io.*;

public class Lab10 {

    public static void main( String[] args ) {
		Scanner keyboard;
		String input;

		keyboard = new Scanner( System.in );

		//read "inorder" or a key
      input = keyboard.nextLine();

		keyboard.close();

		//build tree
		Node temp1 = new Node(5, 8, 10);
		Node temp2 = new Node(23, 28);
		Node temp3 = new Node(37, 39, 41);
		Node temp4 = new Node(45, 51, 53);
		Node temp5 = new Node(64, 71);
		Node temp6 = new Node(82, 86, 89);
		Node temp7 = new Node(99);
		Node temp8 = new Node(12, 30, 42, temp1, temp2, temp3, temp4);
		Node temp9 = new Node(80, 95, temp5, temp6, temp7);
		Node temp10 = new Node(60, temp8, temp9);
		My234Tree testTree = new My234Tree(temp10);


		if (input.equals("inorder")){ //traverse tree
			testTree.inorder();
		}
		else { //search tree
			int key = Integer.parseInt(input);
			if (testTree.search(key))
				System.out.println( "The key of " + key + " was FOUND" );
			else
				System.out.println( "The key of " + key + " was NOT found" );
		}

    } // end main
}

class Node{
	public int dataLeft;
	public int dataMid;
	public int dataRight;
	public Node left;
	public Node midLeft;
	public Node midRight;
	public Node right;

	public Node(int d){
		dataLeft = d;
		dataMid = dataRight = Integer.MIN_VALUE;
		left = midLeft = midRight = right = null;
	}

	public Node(int d, Node l, Node ml){
		dataLeft = d;
		dataMid = dataRight = Integer.MIN_VALUE;
		left = l;
		midLeft = ml;
		midRight = right = null;
	}

	public Node(int d1, int d2){
		dataLeft = d1;
		dataMid = d2;
		dataRight = Integer.MIN_VALUE;
		left = midLeft = midRight = right = null;
	}

	public Node(int d1, int d2, Node l, Node ml, Node mr){
		dataLeft = d1;
		dataMid = d2;
		dataRight = Integer.MIN_VALUE;
		left = l;
		midLeft = ml;
		midRight = mr;
		right = null;
	}

	public Node(int d1, int d2, int d3){
		dataLeft = d1;
		dataMid = d2;
		dataRight = d3;
		left = midLeft = midRight = right = null;
	}

	public Node(int d1, int d2, int d3, Node l, Node ml, Node mr, Node r){
		dataLeft = d1;
		dataMid = d2;
		dataRight = d3;
		left = l;
		midLeft = ml;
		midRight = mr;
		right = r;
	}

	//NOTE: Leaf nodes could have one, two, or three items, even though all pointers are null
	public void inorder(){
	   //****Fill in this recursive helper method****
      
	}
	
	

	public boolean search(int key){
	   //****Fill in this recursive helper method****
      return false;
	}
}//end Node class

class My234Tree{
	public Node root;

	public My234Tree(){
		root = null;
	}

	public My234Tree(Node r){
		root = r;
	}

	public void inorder(){
		//****Fill in this driver method. It should call the recursive helper method on the root.****
	   System.out.print(traversalInorder(root));	
	}
	
	private String traversalInorder(Node node) {
        StringBuilder builder = new StringBuilder();
	 
	 String nodeLeft, nodeMidLeft, nodeMidRight, nodeRight;
	 
	 if (node.left != null) {
	   nodeLeft = traversalInorder(node.left);
	   builder.append(nodeLeft);
	   builder.append(" ");
	 };
	 
	 builder.append(node.dataLeft);
	 
	 if (node.midLeft != null) {
	    nodeMidLeft = traversalInorder(node.midLeft);
	    builder.append(" ");
	    builder.append(nodeMidLeft);
	};
	
	if (node.dataMid != Integer.MIN_VALUE) {
	   builder.append(" ");
	   builder.append(node.dataMid);
	};
	
	if (node.midRight != null) {
	   nodeMidRight = traversalInorder(node.midRight);
	   builder.append(" ");
	   builder.append(nodeMidRight);
	};
	
	if (node.dataRight != Integer.MIN_VALUE) {
	   builder.append(" ");
	   builder.append(node.dataRight);
	};
	
	if (node.right != null) {
	   nodeRight = traversalInorder(node.right);
	   builder.append(" ");
	   builder.append(nodeRight);
	};
	
	return builder.toString();
	
	}

	public boolean search(int key){
	   //****Fill in this driver method. It should call the recursive helper method on the root.****
		return search(key, root);
	}

    private boolean search(int key, Node node) {
        if (node == null) {
            return false;
        };
        
        if (key == node.dataLeft || key == node.dataMid || key == node.dataRight) {
            return true;
        } else if (key < node.dataLeft) {
            if (node.left == null) {
                return false;
            } else {
                return search(key, node.left);
            }
        } else if (node.dataMid == Integer.MIN_VALUE || key < node.dataMid) {
            if (node.midLeft == null) {
                return false;
            } else {
                return search(key, node.midLeft);
            }
        } else if (node.dataRight == Integer.MIN_VALUE || key < node.dataRight) {
            if (node.midRight == null) {
                return false;
            } else {
                return search(key, node.midRight);
            }
        } else {
            if (node.right == null) {
                return false;
            } else {
                return search(key, node.right);
            }
        }

    }
}
