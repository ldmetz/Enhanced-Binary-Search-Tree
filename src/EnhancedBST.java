//Levi Metzger
//Enhanced Binary Search Tree

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class EnhancedBST extends BinarySearchTree {

    public EnhancedBST(){
        super();
    }

    /**
     * Prints the tree with inorder, preorder, and postorder traversals
     */

    @Override
    public void print() {
        System.out.print("\nInorder: ");
        inOrder(root);
        System.out.print("\nPreorder: ");
        preOrder(root);
        System.out.print("\nPostorder: ");
        postOrder(root);
    }

    /**
     * Recursively prints the preorder traversal of a binary tree
     * @param node the root node of the tree to be printed
     */

    private static void preOrder(Node node){
        if(node == null){
            return;
        }
        System.out.print(node.data + " ");
        preOrder(node.left);
        preOrder(node.right);
    }

    /**
     * Recursively prints the inorder traversal of a binary tree
     * @param node the root node of the tree to be printed
     */

    private static void inOrder(Node node){
        if(node == null){
            return;
        }
        inOrder(node.left);
        System.out.print(node.data + " ");
        inOrder(node.right);
    }

    /**
     * Recursively prints the postorder traversal of a binary tree
     * @param node the root node of the tree to be printed
     */

    private static void postOrder(Node node){
        if(node == null){
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.data + " ");
    }

    /**
     * Gets the height of the binary tree, defined as the number of nodes in the longest path from the root
     * to a leaf.
     * @return the height of the tree
     */

    public int getHeight(){
        return getHeight(root);
    }

    /**
     * Recursively gets the height of a binary tree
     * @param node the root node of the tree
     * @return the height of the tree
     */

    private static int getHeight(Node node){
        if(node == null){
            return 0;
        }
        return 1 + Math.max(getHeight(node.left), getHeight(node.right));
    }

    /**
     * Gets the internal path length of the binary tree
     * @return the internal path length of the tree
     */

    public int getInternalPathLength(){
        return getInternalPathLength(root, 0);
    }

    /**
     * Recursively gets the internal path length of a binary tree
     * @param node the root node of the tree
     * @param depth the root node's depth within the larger tree structure
     * @return the internal path length of the tree
     */

    private static int getInternalPathLength(Node node, int depth){
        if(node == null){
            return 0;
        }
        return depth + (getInternalPathLength(node.left, depth + 1) + getInternalPathLength(node.right, depth + 1));
    }

    /**
     * Gets the number of absent children in the binary search tree
     * @return the number of absent children in the tree
     */

    public int getAbsentChildren(){
        return getAbsentChildren(root);
    }

    /**
     * Recursively gets the number of absent children in a binary tree
     * @param node the root node of the tree
     * @return the number of absent children in the tree
     */

    private static int getAbsentChildren(Node node){
        int num = 0;
        if(node == null){
            return 0;
        }
        if(node.left == null){
            num++;
        }
        if(node.right == null){
            num++;
        }
        return num + getAbsentChildren(node.left) + getAbsentChildren(node.right);
    }

    /**
     * Determines whether the input is a path sum in the binary search tree
     * @param pathSum the number to check
     * @return whether the binary tree contains a path sum equal to the input
     */

    public boolean isPathSum(int pathSum){
        return isPathSum(root, pathSum);
    }

    /**
     * Recursively determines whether the input is a path sum in a binary tree
     * @param node the root node of the tree
     * @param pathSum the number to check
     * @return whether the tree contains a path sum equal to the input
     */

    private boolean isPathSum(Node node, int pathSum){
        if(node == null){
            return false;
        }
        if(node.left == null && node.right == null){
            return (int)node.data == pathSum;
        }
        return isPathSum(node.left, pathSum - (int)node.data) || isPathSum(node.right, pathSum - (int)node.data);
    }

    /**
     * Defines and gets an iterator that traverses the tree in a breadth-first traversal.
     * @return an iterator for the tree
     */

    public Iterator<Integer> getTreeIterator(){
        return new Iterator<Integer>() {
            private Queue<Node> q;
            {
                q = new LinkedList<Node>();
                if(root != null){
                    q.add(root);
                }
            }

            @Override
            public boolean hasNext() {
                return !q.isEmpty();
            }

            @Override
            public Integer next() {
                Node node = q.remove();
                if(node.left != null){
                    q.add(node.left);
                }
                if(node.right != null){
                    q.add(node.right);
                }
                return (Integer)node.data;
            }
        };
    }
}