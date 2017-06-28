package com.collonn.algorithm.tree;  
  
import java.util.HashMap;  
import java.util.LinkedList;  
import java.util.Map;  
import java.util.Queue;  
  
/** 
 * �����������ڵ㶨�� 
 */  
class Node {  
    // �ڵ�ֵ  
    public int value;  
    // �ڵ�����  
    public Node left;  
    // �ڵ��Һ���  
    public Node right;  
    // �ڵ��˫��  
    public Node parent;  
  
    public Node() {  
          
    }  
  
    // �Ƿ�������  
    public boolean hasLeft() {  
        return this.left == null ? false : true;  
    }  
  
    // �Ƿ����Һ���  
    public boolean hasRight() {  
        return this.right == null ? true : false;  
    }  
  
    // �Ƿ�ֻ��һ������  
    public boolean hasOneSub() {  
        return (this.left == null && this.right != null) || (this.left != null && this.right == null) ? true : false;  
    }  
  
    // �Ƿ�����������  
    public boolean hasTwoSub() {  
        return (this.left != null && this.right != null ? true : false);  
    }  
  
    // �Ƿ�û�к���  
    public boolean hasNonSub() {  
        return (this.left == null && this.right == null) ? true : false;  
    }  
  
    // �Ƿ�Ҷ�ӽڵ�  
    public boolean isLeaf() {  
        return this.hasNonSub();  
    }  
  
    // �Ƿ���ROOT�ڵ�  
    public boolean isRoot() {  
        return (this.parent == null) ? true : false;  
    }  
  
    // �Ƿ���˫�׵�����  
    public boolean isLeft() {  
        boolean flag = false;  
        if (this.parent != null && (this == this.parent.left)) {  
            flag = true;  
        }  
        return flag;  
    }  
  
    // �Ƿ���˫�׵��Һ���  
    public boolean isRight() {  
        boolean flag = false;  
        if (this.parent != null && (this == this.parent.right)) {  
            flag = true;  
        }  
        return flag;  
    }  
  
    // �ýڵ�ֻ����һ�����ӣ������������  
    public Node getOneSub() {  
        if (this.left != null && this.right != null) {  
            throw new RuntimeException("�ýڵ�ֻ����һ�����ӡ�");  
        }  
        return this.left == null ? this.right : this.left;  
    }  
}  
  
/** 
 * ���������(BST��)<br/> 
 * �Ƚڵ�С��Ԫ�ط��ڽڵ���������У��Ƚڵ���Ԫ�ط��ڽڵ����������<br/> 
 */  
public class BST {  
    // ������ڵ�  
    public Node root;  
  
    // �������������ֵ  
    public void insert(int nv) {  
        System.out.print("\n----------");  
        if (this.root == null) {  
            this.root = new Node();  
            this.root.value = nv;  
            this.root.left = null;  
            this.root.right = null;  
            this.root.parent = null;  
            System.out.printf("\n������ݽڵ�:" + nv);  
            return;  
        }  
  
        System.out.print("\nvisit:");  
        Node node = this.findPlace(nv);  
        if (node != null) {  
            if (nv < node.value) {  
                System.out.printf("\n��%d���������в���%d:", node.value, nv);  
                node.left = new Node();  
                node.left.value = nv;  
                node.left.left = null;  
                node.left.right = null;  
                node.left.parent = node;  
            } else if (nv > node.value) {  
                System.out.printf("\n��%d���������в���%d:", node.value, nv);  
                node.right = new Node();  
                node.right.value = nv;  
                node.right.left = null;  
                node.right.right = null;  
                node.right.parent = node;  
            }  
        }  
    }  
  
    // �ҵ���ֵ,����null  
    // ���򷵻ظ��ڵ�,���²����node��Ϊ�亢��  
    public Node findPlace(int nv) {  
        Node target = null;  
        Node node = this.root;  
  
        while (node != null) {  
            System.out.print(node.value + ",");  
  
            if (nv == node.value) {  
                target = node;  
                break;  
            }  
  
            if (nv < node.value) {  
                if (node.left != null) {  
                    node = node.left;  
                    continue;  
                } else {  
                    target = node;  
                    break;  
                }  
            }  
  
            if (nv > node.value) {  
                if (node.right != null) {  
                    node = node.right;  
                    continue;  
                } else {  
                    target = node;  
                    break;  
                }  
            }  
        }  
  
        return target;  
    }  
  
    // �ڶ�����������ĳֵ  
    // ����ҵ�,�򷵻�ƥ��ڵ�,��������null  
    public Node search(int v) {  
        System.out.print("\n���������Ҿ����Ľڵ㣺");  
        Node target = null;  
        Node node = this.root;  
  
        while (node != null) {  
            System.out.print(node.value + ",");  
  
            if (v == node.value) {  
                target = node;  
                break;  
            }  
  
            if (v < node.value && node.left != null) {  
                if (node.left != null) {  
                    node = node.left;  
                    continue;  
                } else {  
                    target = null;  
                    break;  
                }  
            }  
  
            if (v > node.value) {  
                if (node.right != null) {  
                    node = node.right;  
                    continue;  
                } else {  
                    target = null;  
                    break;  
                }  
            }  
        }  
  
        return target;  
    }  
  
    // �ȸ�����(������)  
    public void dfsFirst(Node node) {  
        if (node == null) {  
            return;  
        }  
  
        // ���ʸ�  
        System.out.print(node.value + ",");  
  
        // ����������  
        if (node.left != null) {  
            this.dfsFirst(node.left);  
        }  
  
        // ����������  
        if (node.right != null) {  
            this.dfsFirst(node.right);  
        }  
    }  
  
    // �и�����(�����)(��С����)  
    public void dfsMid(Node node) {  
        if (node == null) {  
            return;  
        }  
  
        // ����������  
        if (node.left != null) {  
            this.dfsMid(node.left);  
        }  
  
        // ���ʸ�  
        System.out.print(node.value + ",");  
  
        // ����������  
        if (node.right != null) {  
            this.dfsMid(node.right);  
        }  
    }  
  
    // ASC  
    public void dfsASC(Node node) {  
        this.dfsMid(node);  
    }  
  
    // DESC,(�Ҹ���)(�Ӵ�С):  
    public void dfsDESC(Node node) {  
        if (node == null) {  
            return;  
        }  
  
        // ����������  
        if (node.right != null) {  
            this.dfsDESC(node.right);  
        }  
  
        // ���ʸ�  
        System.out.print(node.value + ",");  
  
        // ����������  
        if (node.left != null) {  
            this.dfsDESC(node.left);  
        }  
    }  
  
    // �������(���Ҹ�)  
    public void dfsLast(Node node) {  
        if (node == null) {  
            return;  
        }  
  
        // ����������  
        if (node.left != null) {  
            this.dfsLast(node.left);  
        }  
  
        // ����������  
        if (node.right != null) {  
            this.dfsLast(node.right);  
        }  
  
        // ���ʸ�  
        System.out.print(node.value + ",");  
    }  
  
    // �������Ĺ�����ȱ�����˳�����  
    public void bfs() {  
        System.out.print("\nBFS:");  
        Queue<Node> queue = new LinkedList<Node>();  
        queue.offer(this.root);  
  
        Node node = null;  
        while (!queue.isEmpty()) {  
            // ɾ������ͷ���  
            node = queue.poll();  
            System.out.print(node.value + ",");  
  
            // ���ڵ�����Ӽ������  
            if (node.left != null) {  
                queue.offer(node.left);  
            }  
            // ���ڵ���Һ��Ӽ������  
            if (node.right != null) {  
                queue.offer(node.right);  
            }  
        }  
    }  
  
    // �������Ĺ�����ȱ��������ղ�Σ����ϵ��£������ң���ӡ����  
    public void bfsLevel() {  
        System.out.print("\n--------------- bfsLevel ---------------");  
        System.out.print("\nBFS:[�ڵ�ֵ,�Ƿ�Ҷ��,˫�׽ڵ�,���]");  
        Queue<Node> queue = new LinkedList<Node>();  
        queue.offer(this.root);  
  
        // ��һ���ڵ�Ĳ��  
        int prevLevel = 0;  
        Node node = null;  
  
        while (!queue.isEmpty()) {  
            // ɾ������ͷ���  
            node = queue.poll();  
            // ĳ�ڵ����������ĵڼ���(ROOTΪ��1��)  
            int levelOfFullTree = this.getLevelOfFullTree(node);  
  
            // �����ǰ��ȱ�ǰһ���ڵ�ĳ��Դ�,��ʼ����һ��Ľڵ����  
            if (levelOfFullTree > prevLevel) {  
                System.out.print("\n");  
            }  
  
            System.out.print("[");  
            System.out.print(node.value + ",");  
            System.out.print((node.parent == null ? null : node.parent.value) + ",");  
            System.out.print(prevLevel);  
            System.out.print("]");  
            // System.out.printf("%3s," , node.value);  
  
            // ���ڵ�����Ӽ������  
            if (node.left != null) {  
                queue.offer(node.left);  
            }  
            // ���ڵ���Һ��Ӽ������  
            if (node.right != null) {  
                queue.offer(node.right);  
            }  
  
            prevLevel = levelOfFullTree;  
        }  
    }  
  
    // ���α���ڵ��ӡ  
    private void printTreeLevel(int[] nodes) {  
        System.out.print("\n|");  
        for (int j = 0; j < nodes.length; j++) {  
            if (nodes[j] == 0) {  
                // ��ӡ��λ���ֵ�ռλ��  
                System.out.printf("--");  
            } else {  
                // ��ӡ�ڵ�  
                System.out.printf("%02d", nodes[j]);  
                // ��������  
                nodes[j] = 0;  
            }  
        }  
        System.out.print("|");  
    }  
  
    /** 
     * �������Ĺ�����ȱ������������δ�ӡ���� 
     *  
     * <pre> 
     * �㷨�õ��Ĳ����� 
     * 1���������������ȡ� 
     * 2��ÿ���ڵ��ڶ������еĲ��Level����1��ʼ�� 
     * 3��ÿ���ڵ��ڸò��е����indexOfLevel����1��ʼ�� 
     * ע�� 
     *  (1)Level��indexOfLevel�����ڹ�����ȱ���ʱ�ü�����ʵ�֡� 
     *  (2)Level��indexOfLevelҲ�����������в����½ڵ�ʱ����ʼ�����ڵ��С� 
     *      ���������洢�����������踸�ڵ��±�Ϊi���������ӵ��±���2*i-1���Һ��ӵ��±���2*i+1�� 
     *  
     * �㷨����˼·�� 
     * (1)������һ��ˮƽ���飬ˮƽ����ĳ���Ϊ "��������" �еĽڵ��������������������нڵ㣬���������������ӣ�ͶӰ��ˮƽ�����ϣ�ÿ���ڵ���ˮƽ�����ж��Ծ�һ��λ�á� 
     * (2)�������ܽ�һ�£�����ÿһ���㼶��ӳ�䵽ˮƽ����󣬵�һ���ڵ�Ŀ�ʼ�±�=s�������������ڽڵ�Ĳ���(���)=d���������ʾ 
     * �㼶   ��ʼ�±�        ���� 
     * 1    2^3-1=7     2^4=16 
     * 2    2^2-1=3     2^3=8 
     * 3    2^1-1=1     2^2=4 
     * 4    2^0-1=0     2^1=2 
     * (3)�������������ݣ����ǿ��Լ����������һ�㣬����һ�ڵ���ˮƽ�����е��±꣬ 
     * �±�=��ʼ�±�+(�ýڵ����ڲ��-1)*���� 
     * (4)��OK��ÿһ��ÿ���ڵ��λ��ȷ���ˣ�����ͼ��ȻҲȷ���ˡ� 
     *  
     * �� 
     * �����Ҫ������ر��أ����Ǳ��룺 
     * 1����ȷ��ÿ���ڵ��ֵ(�����������)���ռ���ٸ��ַ���ȣ�����Ϊflength�� 
     *     ��������Ĺ����У�����������ֵ������ֵ������ʽ����������Ȳ���flength�ģ��ÿո��롣 
     * 2�������ʵ��Ľ�ˮƽ��������һ��������ÿ���еĸ��ڵ�֮��ľ��������ˣ����տ����Ľ����������ˮƽ�Ŵ��ˡ� 
     * </pre> 
     */  
    public void bfsTree() {  
        System.out.print("\n------------------ ���δ�ӡ��ʼ ------------------");  
  
        if (this.root == null) {  
            System.out.print("\n��Ϊpw");  
            return;  
        }  
  
        // �������ĸ߶�  
        int maxLevel = this.getDepth(this.root);  
        // ��������ʱ���ܽ����  
        int fullTotal = (int) Math.pow(2, maxLevel) - 1;  
        // ˮƽ����  
        int[] nodes = new int[fullTotal];  
  
        // ��һ���ڵ�Ĳ��  
        int prevLevel = 1;  
        // ÿ�����ʼ�±�  
        int start = 0;  
        // ÿһ���Ԫ�صļ��  
        int stepSize = 0;  
  
        // ������ȱ���  
        Queue<Node> queue = new LinkedList<Node>();  
        queue.offer(this.root);  
        Node node = null;  
        // ���������洢��������indexMap�д洢���ڵ��Ӧ������±�  
        Map<Integer, Integer> indexMap = new HashMap<Integer, Integer>();  
        while (!queue.isEmpty()) {  
            // ɾ������ͷ���  
            node = queue.poll();  
            // ĳ�ڵ����������ĵڼ���(ROOTΪ��1��)  
            int levelOfFullTree = this.getLevelOfFullTree(node);  
  
            // �����ǰ��ȱ�ǰһ���ڵ�ĳ��Դ�,��ʼ����һ��Ľڵ����  
            if (levelOfFullTree > prevLevel) {  
                // ��ӡ��εĽڵ�  
                this.printTreeLevel(nodes);  
            }  
  
            // �����µĲ�εĿ�ʼ�±��벽��  
            start = (int) Math.pow(2, maxLevel - levelOfFullTree) - 1;  
            stepSize = (int) Math.pow(2, maxLevel - levelOfFullTree + 1);  
            // System.out.print("\n" + "start:" + start + ",stepSize:" + stepSize);  
  
            // ��¼�ڵ���±�  
            int idx = -1;  
            if (node == this.root) {  
                indexMap.put(node.value, 1);  
                idx = 1;  
            } else {  
                if (node == node.parent.left) {  
                    idx = 2 * indexMap.get(node.parent.value) - 1;  
                } else if (node == node.parent.right) {  
                    idx = 2 * indexMap.get(node.parent.value);  
                }  
                indexMap.put(node.value, idx);  
            }  
  
            // ����ӳ�䵽ˮƽ�����λ��  
            int y = start + (idx - 1) * stepSize;  
            nodes[y] = node.value;  
            // System.out.print("\n" + "node.value:" + node.value + ",y:" + y);  
  
            // ���ڵ�����Ӽ������  
            if (node.left != null) {  
                queue.offer(node.left);  
            }  
            // ���ڵ���Һ��Ӽ������  
            if (node.right != null) {  
                queue.offer(node.right);  
            }  
  
            // ���������,Ϊ�´�ѭ��ʹ��  
            prevLevel = levelOfFullTree;  
        }  
  
        // ��ӡ��ײ�Ľڵ㣬��Ϊwhile���Ƴ�����ײ�εĽڵ�û�д�ӡ�������ﵥ����ӡ  
        this.printTreeLevel(nodes);  
        System.out.print("\n------------------ ���δ�ӡ���� ------------------");  
    }  
  
    // ������ĳ�ڵ�Ϊ�����������(��1��ʼ)  
    public int getDepth(Node node) {  
        if (node == null) {  
            return 0;  
        }  
  
        return 1 + Math.max(this.getDepth(node.left), this.getDepth(node.right));  
    }  
  
    // ����ĳ�ڵ����������ĵڼ���(ROOTΪ��1��)  
    public int getLevelOfFullTree(Node node) {  
        int depth = 0;  
        Node t = node;  
        while (t != null) {  
            depth++;  
            t = t.parent;  
        }  
        return depth;  
    }  
  
    // ������ĳ�ڵ�Ϊ���Ķ��������ܽ����(�������ڵ�)  
    public int getTotalNode(Node node) {  
        if (node == null) {  
            return 0;  
        }  
  
        int L = getTotalNode(node.left);  
        int R = getTotalNode(node.right);  
  
        return 1 + (L + R);  
    }  
  
    // �Ըýڵ�Ϊ������������������Ԫ��  
    public Node getMaxNode(Node node) {  
        Node nodeMax = node;  
        while (nodeMax != null) {  
            if (nodeMax.right != null) {  
                nodeMax = nodeMax.right;  
            } else {  
                break;  
            }  
        }  
        return nodeMax;  
    }  
  
    // �Ըýڵ�Ϊ������������������Ԫ��  
    public Node getMinNode(Node node) {  
        Node nodeMax = node;  
        while (nodeMax != null) {  
            if (nodeMax.left != null) {  
                nodeMax = nodeMax.left;  
            } else {  
                break;  
            }  
        }  
        return nodeMax;  
    }  
  
    /** 
     * <pre> 
     * �ҵ�Ŀ��ڵ㲢ɾ��������Ŀ��ڵ� 
     * ���û�ҵ�������null 
     * ע�⣬���Ǵ���ÿһ�ַ���ʱ�������ȴ���ROOT�ڵ� 
     * </pre> 
     *  
     * @param v 
     * @return 
     */  
    public Node delete(int v) {  
        Node t = this.search(v);  
  
        if (t == null) {  
            return t;  
        }  
  
        // -------------------------------- ����Ҷ�ӽڵ㣬��ʼ --------------------------------  
        if (t.isLeaf()) {  
            // t��Ҷ�ӽڵ㣬����ROOT  
            if (t.isRoot()) {  
                this.root = null;  
                return t;  
            }  
            // t��Ҷ�ӽڵ㣬����˫�׵����ӣ�����ROOT  
            if (t.isLeft()) {  
                t.parent.left = null;  
                return t;  
            }  
            // t��Ҷ�ӽڵ㣬����˫�׵��Һ��ӣ�����ROOT  
            if (t.isRight()) {  
                t.parent.right = null;  
                return t;  
            }  
        }  
  
        /** 
         * -------------------------------- �������������ӵĽڵ㣬��ʼ -------------------------------- 
         *  
         * <pre> 
         * ���Ŀ��ڵ�tֻ��һ�����ӣ������������ӣ�����������ͳһ�������������������ӡ� 
         *  
         * t���������ӵ��������Ƚϸ��ӵ㣬������ɾ��t�󣬱����ҵ�һ�����ʵĽڵ�������t��λ�á� 
         * ����ڵ��ѡ�������ַ����� 
         * ����1����Ŀ��ڵ����������ѡ��ֵ���Ľڵ㣬ȥ����t��λ�á� 
         * ����2����Ŀ��ڵ����������ѡ��ֵ��С�Ľڵ㣬����t��λ�á� 
         *  
         * ��������������������ģ� 
         * �ȴ�t�����������У�ѡ��������������(���t.left==null�����������ĳ���Ϊ0)�� 
         * �����t����������ѡ��ֵ���Ľڵ㡣 
         * �����t����������ѡ��ֵ��С�Ľڵ㡣 
         * ���������Ծ����ӳٶ��������ּ��˲�ƽ�������� 
         *  
         * <pre> 
         */  
        if (true) {  
            int L = this.getDepth(t.left);  
            int R = this.getDepth(t.right);  
            // ��ѡ���Ľڵ�  
            Node nm = null;  
  
            if (L >= R) {  
                // ��t����������ѡ��һ������  
                nm = this.getMaxNode(t.left);  
                // ���max��t���������ĸ�����ômaxһ��û���Һ���  
                if (nm == t.left) {  
                    nm.parent = t.parent;  
                    nm.right = t.right;  
                    t.right.parent = nm;  
                }  
                // ���max��t�������������Ҳ��Ҷ�ӽ�㣬maxû�����ӣ���max������  
                if (nm.isRight()) {  
                    nm.parent.right = nm.left;  
                    if (nm.left != null) {  
                        nm.left.parent = nm.parent;  
                    }  
                    nm.left = t.left;  
                    if (nm.left != null) {  
                        nm.left.parent = nm;  
                    }  
                    nm.right = t.right;  
                    if (nm.right != null) {  
                        nm.right.parent = nm;  
                    }  
                    nm.parent = t.parent;  
                }  
            } else {  
                // ��t����������ѡ��һ����С��  
                nm = this.getMinNode(t.right);  
                // min��t���������ĸ�����ôminһ��û������  
                if (nm == t.right) {  
                    nm.parent = t.parent;  
                    nm.left = t.left;  
                }  
                // min��t����������������Ҷ�ӽ�㣬minû���Һ��ӣ���min���Һ���  
                if (nm.isLeft()) {  
                    nm.parent.left = nm.right;  
                    if (nm.right != null) {  
                        nm.right.parent = nm.parent;  
                    }  
                    nm.left = t.left;  
                    if (nm.left != null) {  
                        nm.left.parent = nm;  
                    }  
                    nm.right = t.right;  
                    if (nm.right != null) {  
                        nm.right.parent = nm;  
                    }  
                    nm.parent = t.parent;  
                }  
            }  
  
            // ����ѡ�����½ڵ���t��˫�׽ڵ�Ĺ�ϵ  
            if (t.isRoot()) {  
                this.root = nm;  
            } else {  
                if (t == t.parent.left) {  
                    t.parent.left = nm;  
                } else {  
                    t.parent.right = nm;  
                }  
            }  
        }  
  
        return t;  
    }  
  
    public static void main(String[] args) {  
        // ��ʼ�����������  
        BST bst = new BST();  
        bst.insert(50);  
        bst.insert(30);  
        bst.insert(80);  
        bst.insert(10);  
        bst.insert(40);  
        bst.insert(35);  
        bst.insert(90);  
        bst.insert(85);  
        bst.insert(5);  
        bst.insert(15);  
        bst.insert(20);  
        bst.insert(13);  
        bst.insert(3);  
        bst.insert(8);  
        bst.insert(37);  
        bst.insert(70);  
        bst.insert(60);  
        bst.insert(75);  
        bst.insert(78);  
        bst.insert(72);  
        bst.insert(95);  
        bst.insert(99);  
  
        bst.bfsLevel();  
        bst.bfsTree();  
  
        // int v = 35;  
        // Node node = bst.search(v);  
  
        // System.out.print("\ndepth:" + bst.getDepth(node));  
  
        // int k = bst.getMaxLevel(node);  
        // System.out.print("\n�������,R:" + v + ",V:" + k);  
        //  
        // int k2 = bst.getTotalNode(node);  
        // System.out.print("\n���ܽ����,R:" + v + ",V:" + k2);  
        //  
        // Node nodeMax = bst.getMaxNode(node);  
        // System.out.print("\n�������ֵ,R:" + v + ",V:" + (nodeMax == null ? null : nodeMax.value));  
  
//      int value = 30;  
//      bst.delete(value);  
//      bst.bfsTree();  
    }  
  
}  