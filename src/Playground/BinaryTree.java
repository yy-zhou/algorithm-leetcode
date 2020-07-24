package Playground;

/**
 * @author BorisMirage
 * Time: 2019/06/10 17:16
 * Created with IntelliJ IDEA
 */


class BinaryTree {
    // Root of Binary Tree
    private Node root;

    private BinaryTree() {
        root = null;
    }

    /* Given a binary tree, print its nodes according to the
      "bottom-up" postorder traversal. */
    private void printPostorder(Node node) {
        if (node == null)
            return;

        // first recur on left subtree
        printPostorder(node.left);

        // then recur on right subtree
        printPostorder(node.right);

        // now deal with the node
        System.out.print(node.key + " ");
    }

    /* Given a binary tree, print its nodes in inorder*/
    private void printInorder(Node node) {
        if (node == null)
            return;

        /* first recur on left child */
        printInorder(node.left);

        /* then print the data of node */
        System.out.print(node.key + " ");

        /* now recur on right child */
        printInorder(node.right);
    }

    /* Given a binary tree, print its nodes in preorder*/
    private void printPreorder(Node node) {
        if (node == null)
            return;

        /* first print data of node */
        System.out.print(node.key + " ");

        /* then recur on left sutree */
        printPreorder(node.left);

        /* now recur on right subtree */
        printPreorder(node.right);
    }

    // Wrappers over above recursive functions
    private void printPostorder() {
        printPostorder(root);
    }

    private void printInorder() {
        printInorder(root);
    }

    private void printPreorder() {
        printPreorder(root);
    }

    // Driver method
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);
        tree.root.left.left.left = new Node(8);
        tree.root.left.left.right = new Node(9);

        tree.root.right.left = new Node(6);
        tree.root.right.right = new Node(7);
        tree.root.right.left.left = new Node(14);
        tree.root.right.left.right = new Node(15);

        tree.root.right.right.left = new Node(12);
        tree.root.right.right.right = new Node(13);

        tree.root.right.right.right.right = new Node(16);
        tree.root.right.right.right.right.right = new Node(17);


        System.out.println("Preorder traversal of binary tree is ");
        tree.printPreorder();

        System.out.println("\nInorder traversal of binary tree is ");
        tree.printInorder();

        System.out.println("\nPostorder traversal of binary tree is ");
        tree.printPostorder();
    }
}


class Node {
    int key;
    Node left, right;

    Node(int item) {
        key = item;
        left = right = null;
    }
}