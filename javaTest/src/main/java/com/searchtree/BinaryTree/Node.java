package com.searchtree.BinaryTree;

public class Node {

    /**
     * 值
     */
    int value;
    /**
     * 右子树节点
     */
    Node rightNode;
    /**
     * 左子树节点
     */
    Node leftNode;

    public Node(int value, Node rightNode, Node leftNode) {
        this.value = value;
        this.rightNode = rightNode;
        this.leftNode = leftNode;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getRightNode() {
        return rightNode;
    }

    public void setRightNode(Node rightNode) {
        this.rightNode = rightNode;
    }

    public Node getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(Node leftNode) {
        this.leftNode = leftNode;
    }
}
