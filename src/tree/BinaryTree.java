package tree;

import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by machenggong on 2019/5/17.
 */
public class BinaryTree {

    private Node root;

    /**
     * 内部节点类
     *
     * @author yhh
     */
    private static class Node {
        private Node left;
        private Node right;
        private int data;

        public Node(int data) {
            this.left = null;
            this.right = null;
            this.data = data;
        }
    }

    public BinaryTree() {
        root = null;
    }

    /**
     * 递归创建二叉树
     *
     * @param node
     * @param data
     */
    public void buildTree(Node node, int data) {
        if (root == null) {
            root = new Node(data);
        } else {
            if (data < node.data) {
                if (node.left == null) {
                    node.left = new Node(data);
                } else {
                    buildTree(node.left, data);
                }
            } else {
                if (node.right == null) {
                    node.right = new Node(data);
                } else {
                    buildTree(node.right, data);
                }
            }
        }
    }

    /**
     * 前序遍历
     *
     * @param node
     */
    public void preOrder(Node node) {
        if (node != null) {
            System.out.println(node.data);
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    /**
     * 中序遍历
     *
     * @param node
     */
    public void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.println(node.data);
            inOrder(node.right);
        }
    }

    /**
     * 后序遍历
     *
     * @param node
     */
    public void postOrder(Node node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.println(node.data);
        }
    }

    /**
     * 非递归前序遍历
     *
     * @param root
     */
    public void preOrderTraverse2(Node root) {
        LinkedList<Node> stack = new LinkedList<>();
        Node pNode = root;
        while (pNode != null || !stack.isEmpty()) {
            if (pNode != null) {
                System.out.print(pNode.data + "  ");//输出当前节点
                stack.push(pNode);//把当前节点保存到堆栈中，以便遍历完左子树后遍历右子树
                pNode = pNode.left;
            } else { //pNode == null && !stack.isEmpty()
                Node node = stack.pop();
                pNode = node.right;
            }
        }
    }

    /**
     * 非递归中序遍历
     *
     * @param root
     */
    public void inOrderTraverse2(Node root) {
        LinkedList<Node> stack = new LinkedList<>();
        Node pNode = root;
        while (pNode != null || !stack.isEmpty()) {
            if (pNode != null) {
                stack.push(pNode);
                pNode = pNode.left;
            } else { //pNode == null && !stack.isEmpty()
                Node node = stack.pop();
                System.out.print(node.data + "  ");
                pNode = node.right;
            }
        }
    }

    /**
     * 非递归层次遍历
     *
     * @param root
     */
    public void levelTraverse(Node root) {
        if (root == null) {
            return;
        }
        LinkedList<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            System.out.print(node.data + "  ");
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }


    /*
     * 后续遍历
     */
    public void lastOrder(Node root) {
        LinkedList<Node> stack = new LinkedList<>();
        Node p = root;//保存当前节点
        Node q = null;//记录最后一个输出的节点，用于检验是不是根节点的右节点

        while (p != null || !stack.isEmpty()) {
            //由根节点向下遍历，知道找到该根节点下的最后一个叶子节点
            while (p != null) {
                stack.push(p);//非叶子节点入栈
                p = p.left;//指向该节点的左孩子
            }

            //p为空，栈非空,说明遍历完了左孩子，处于叶子节点状态
            if (!stack.isEmpty()) {
                p = stack.pop();//栈顶出栈

                //因为如果该节点有右节点，肯定是先访问完右节点才开始访问跟节点的
                //p.right == null：表示没有右节点，可以直接访问根节点
                //p.right == q:刚访问完该节点右节点，则可以访问我该节点
                if (p.right == null || p.right == q) {
                    System.out.println(p.data);//访问当前节点
                    q = p;//记录这个节点
                    p = null;
                } else {//开始遍历右孩子
                    p = p.right;
                }
            }
        }
    }

    public void postOrderFast(Node root) {
        if (root == null) {
            return;
        }

        Stack<Node> s1 = new Stack<>();
        Stack<Node> s2 = new Stack<>();

        s1.push(root);

        Node curNode;
        while (!s1.isEmpty()) {

            curNode = s1.pop();
            // 中、右、左顺序压入栈中
            s2.push(curNode);

            // 压入s1为先左后右，保证中、右、左顺序压入s2中
            if (curNode.left != null) {
                s1.push(curNode.left);
            }
            if (curNode.right != null) {
                s1.push(curNode.right);
            }
        }

        while (!s2.isEmpty()) {
            System.out.print(s2.pop().data + " ");
        }
    }

    public void postOrderTraverse(Node root) {
        Node cur, pre = null;

        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.empty()) {
            cur = stack.peek();
            if ((cur.left == null && cur.right == null) || (pre != null && (pre == cur.left || pre == cur.right))) {
                System.out.print(cur.data + "->");
                stack.pop();
                pre = cur;
            } else {
                if (cur.right != null) {
                    stack.push(cur.right);
                }
                if (cur.left != null) {
                    stack.push(cur.left);
                }
            }
        }
    }

    /**
     * 递归求深度
     *
     * @param root
     * @return
     */
    public static int treeDepth(Node root) {
        if (root == null) {
            return 0;
        }
        // 计算左子树的深度
        int left = treeDepth(root.left);
        // 计算右子树的深度
        int right = treeDepth(root.right);
        // 树root的深度=路径最长的子树深度 + 1
        return left >= right ? (left + 1) : (right + 1);
    }

    /**
     * 非递归，借助栈来计算深度(层数)
     * 比如                root，先放入栈中
     * 5          当前栈的元素数量为1，len=1，取出栈中此时所有的元素，即5，然后将其子节点3和7放入栈中
     * 3       7      当前栈的元素数量为2，len=2，所以连续从栈中pop两次，使栈中不在含有该层元素，同时将下层节点2和4放入栈中
     * 2       4          当前栈的元素数量为2，len=2，所以连续从栈中pop两次
     * 记录深度，所以每次pop出栈中所有元素(某层的所有节点)只需深度+1，即depth++
     *
     * @param root
     * @return
     */
    public static int treeDepth2(Node root) {
        if (root == null) {
            return 0;
        }
        // 初始化深度
        int depth = 0;
        // 存放每层树节点的栈
        Stack<Node> stack = new Stack<>();
        // 将树的根(即第一层)放入栈中
        stack.push(root);
        while (!stack.isEmpty()) {
            // 当栈不为空时，层数+1，
            // 因为每次都会pop出当前层的所有节点，并将该层所有节点的子节点放入栈中
            depth++;
            // 当前栈中元素的数量
            int length = stack.size();
            while (length-- > 0) {
                // 取出栈中所有的节点，并将对应节点的子节点放入栈中
                Node node = stack.pop();
                if (node.left != null) {
                    stack.push(node.left);
                }
                if (node.right != null) {
                    stack.push(node.right);
                }
            }
        }
        return depth;
    }


    /**
     * 深度优先搜索
     *
     * @param root
     */
    public void depthOrderTraverse(Node root) {
        if (root == null) {
            return;
        }
        LinkedList<Node> stack = new LinkedList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            System.out.print(node.data + "  ");
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
    }


    public static void main(String[] args) {
        int[] a = {2, 4, 12, 45, 21, 6, 111};
        BinaryTree bTree = new BinaryTree();
        for (int i = 0; i < a.length; i++) {
            bTree.buildTree(bTree.root, a[i]);
        }
        Node root = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        root.left = node2;
        root.right = node3;

//        bTree.preOrder(bTree.root);
//        bTree.inOrder(bTree.root);
//        bTree.postOrder(bTree.root);
        //bTree.postOrderFast(bTree.root);
//        int t = treeDepth2(bTree.root);
//        System.out.println(t);
        bTree.preOrderTraverse2(root);
        bTree.inOrderTraverse2(root);
        bTree.postOrderFast(root);
        bTree.postOrderTraverse(root);
    }
}
