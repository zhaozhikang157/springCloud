package com.searchtree.BinaryTree;

import java.util.LinkedList;

public class BinaryTree {

    private static Node rootNode;

    /**
     * 新增节点
     * @param currentNode
     * @param value
     * @return
     */
    public static Node addNode(Node currentNode, int value){
        if(currentNode == null){
            return new Node(value,null,null);
        }
        if(currentNode.getValue() < value){
            currentNode.rightNode = addNode(currentNode.getRightNode(),value);
        }
        if(currentNode.getValue() > value){
            currentNode.leftNode = addNode(currentNode.getLeftNode(),value);
        }
        return currentNode;
    }

    /**
     * 删除节点
     * @param currentNode
     * @param value
     */
    public static Node deleteNode(Node currentNode, int value){
        if(currentNode == null){
            return currentNode;
        }
        if(currentNode.getValue() == value){
            //被删除节点的左右子节点都为空
            if(currentNode.rightNode == null && currentNode.leftNode == null){
                return null;
            }
            //被删除节点有一个子节点
            if(currentNode.rightNode == null){
                return currentNode.leftNode;
            }
            if(currentNode.leftNode == null){
                return currentNode.rightNode;
            }
            //被删除节点有两个子节点。

            //获取节点最小值
            int minValue = getLeftNodeValue(currentNode.rightNode);
            //将最小值替换到删除的节点
            currentNode.setValue(minValue);
            //删除替换的节点
            currentNode.rightNode = deleteNode(currentNode.rightNode,minValue);
        }
        if(currentNode.getValue() > value){
            currentNode.leftNode = deleteNode(currentNode.leftNode,value);
        }
        if(currentNode.getValue() < value){
            currentNode.rightNode = deleteNode(currentNode.rightNode,value);
        }
        return currentNode;
    }

    /**
     * 获取节点最小值
     * @param currentNode
     * @return
     */
    public static int getLeftNodeValue(Node currentNode){
        if(currentNode.leftNode != null){
            return getLeftNodeValue(currentNode.leftNode);
        }
        return currentNode.getValue();
    }

    /**
     * 查询树中是否有特定值
     * @param currentNode
     * @param value
     * @return
     */
    public static boolean containsNodeRecursive(Node currentNode,int value){
        boolean flag = false;
        if(currentNode == null){
            return false;
        }
        if(currentNode.getValue() > value){
            flag =containsNodeRecursive(currentNode.leftNode,value);
        }
        if(currentNode.getValue() < value){
            flag = containsNodeRecursive(currentNode.rightNode,value);
        }
        if(currentNode.getValue() == value){
            return true;
        }
        return flag;
    }

    /**
     * 遍历  从最左侧节点开始
     * @param currentNode
     */
    public static void traverseInOrder(Node currentNode){
        if(currentNode != null){
            traverseInOrder(currentNode.leftNode);
            System.out.print(currentNode.getValue()+ "--");
            traverseInOrder(currentNode.rightNode);
        }
    }

    /**
     * 首先访问根节点，然后是左子树，最后是右子树
     * @param currentNode
     */
    public static void traversePreOrder(Node currentNode){
        if(currentNode != null){
            System.out.print(currentNode.getValue()+ "--");
            traversePreOrder(currentNode.leftNode);
            traversePreOrder(currentNode.rightNode);
        }
    }

    /**
     * 访问左子树，右子树，最后访问根节点
     * @param currentNode
     */
    public static void traversePostOrder(Node currentNode){
        if(currentNode != null){
            traversePostOrder(currentNode.leftNode);
            traversePostOrder(currentNode.rightNode);
            System.out.print(currentNode.getValue()+ "--");
        }
    }

    /**
     * 按层级遍历
     * @param currentNode
     */
    public static void traverseLevelOrder(Node currentNode){
        if(currentNode == null){
            return ;
        }
        LinkedList<Node> nodes = new LinkedList();
        nodes.add(currentNode);
        while (!nodes.isEmpty()){
            Node node = nodes.remove();
            System.out.print(node.getValue()+ "--");
            if(node.leftNode != null){
                nodes.add(node.leftNode);
            }
            if(node.rightNode != null){
                nodes.add(node.rightNode);
            }
        }
    }

    public static void main(String[] args) {
        rootNode = addNode(rootNode,8);
        rootNode = addNode(rootNode,18);
        rootNode = addNode(rootNode,3);
        rootNode = addNode(rootNode,5);
        rootNode = addNode(rootNode,3);
        rootNode = addNode(rootNode,6);
        rootNode = addNode(rootNode,9);
        rootNode = addNode(rootNode,2);

        rootNode = deleteNode(rootNode,8);

        System.out.println(containsNodeRecursive(rootNode,1));
        System.out.println(containsNodeRecursive(rootNode,10));
        System.out.println(containsNodeRecursive(rootNode,18));
        System.out.println(containsNodeRecursive(rootNode,2));

        System.out.print("从最左侧节点开始遍历：");
        traverseInOrder(rootNode);
        System.out.println("");
        System.out.print("首先访问根节点，然后是左子树，最后是右子树：");
        traversePreOrder(rootNode);
        System.out.println();
        System.out.print("访问左子树，右子树，最后访问根节点：");
        traversePostOrder(rootNode);
        System.out.println();
        System.out.print("广度优先搜索：");
        traverseLevelOrder(rootNode);
        System.out.println();
    }
}
