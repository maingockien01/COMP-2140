class BST{

   private class Node {
      public int item;
      public Node left;
      public Node right;
      public Node (int i) { //makes a leaf
         item = i;
         left = right = null;
      }

      //***insert Node methods here***
      public String inorderToString (Node root) {
         String leftSubtree;
         String rootString;
         String rightSubtree;
         StringBuilder builder = new StringBuilder();
         if (root.left != null) {
            builder.append(inorderToString(root.left));
            builder.append(" ");
         };
         
         builder.append(root.item);
         
         if (root.right != null) {
            builder.append(" ");
            builder.append(inorderToString(root.right));
         };
         
         return builder.toString();
      }
   }//end class Node

   private Node root;

   public BST(){
      root = null;
   }

	public String inorderTraversal() {
      return root.inorderToString(root);
	}//end inorderTraversal

   public void insert( int key ){
      Node toAdd = new Node(key);
      if (root == null) {
         root = toAdd;
         return;
      };
      insert(toAdd, root);
   }//end insert
   
   private void insert(Node toAdd, Node root) {
      int toAddKey = toAdd.item;
      int rootKey = root.item;
      
      if (toAddKey < rootKey) {
         if (root.right == null) {
            root.right = toAdd;
         } else {
            insert(toAdd, root.right);
         }
      } else {
         if (root.left == null) {
            root.left = toAdd;
         } else {
            insert(toAdd, root.left);
         }
      }
   }
   

   public void delete( int key ){
      if (root == null) {
         return;
      };
      
      Node curr = root;
      Node currParent = null;
      while (curr != null && curr.item != key) {
         currParent = curr;
         if (key > curr.item) {
            curr = curr.left;
         } else {
            curr = curr.right;
         }
      }
      //The node not exist!
      if (curr == null) {
         return;
      };
      //The node exist!
      
      //Case1: NO child node
      if (curr.left == null && curr.right == null) {
         
         if (currParent == null) {
            root = null;
            return;
         }
         
          boolean isCurrLeftChild = key > currParent.item;
         if (isCurrLeftChild) {
            currParent.left = null;
         } else {
            currParent.right = null;
         }
         return;
      }
      
      //Case2: 2 child
      if (curr.left != null && curr.right != null) {
         //Smallest child of the left child 
          System.out.println(curr.left.item + " " + curr.item);
         Node smallestParentOfLeft = findSmallestParent(curr.left, curr);
         Node smallestOfLeft;
         if (curr.item != smallestParentOfLeft.item) {
             smallestOfLeft = smallestParentOfLeft.right;
         } else {
             smallestOfLeft = curr.left;
         };

         System.out.println("Smallest Parent: " + smallestParentOfLeft.item);
         System.out.println("Smallest: " + smallestOfLeft.item);
         delete(smallestOfLeft.item);
         System.out.println("Delete leaf: " + inorderTraversal());
         
         smallestOfLeft.left = curr.left;
         smallestOfLeft.right = curr.right;
         
         if (currParent == null) {
            root = smallestOfLeft;
            return;
         }
         
          boolean isCurrLeftChild = key > currParent.item;
         if (isCurrLeftChild) {
            currParent.left = smallestOfLeft;
         } else {
            currParent.right = smallestOfLeft;
         }
         return;
      }
      
      //Case3: 1 child
      Node childNode;
      if (curr.left != null) {
         childNode = curr.left;
      } else {
         childNode = curr.right;
      };
      
      if (currParent == null) {
         root = childNode;
         return;
      }
      
      boolean isCurrLeftChild = key > currParent.item;
      if (isCurrLeftChild) {
         currParent.left = childNode;
      } else {
         currParent.right = childNode;   
      }
      
      return;
   
   }//end delete
   
   private Node findSmallestParent (Node root, Node rootParent) {
      Node curr = root;
      Node currParent = rootParent;
      while (curr.right != null) {
          System.out.println("Curr right: " + curr.item);
         currParent = curr;
         curr = curr.right;
      };
      System.out.println("Curr parent final: " + currParent.item);
      System.out.println("Curr final: " + curr.item);
      
      return currParent;
   }
   //***insert tree methods here***

}//end class BST
