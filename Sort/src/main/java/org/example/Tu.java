package org.example;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;

// 邻接矩阵
class Tu {
    public static void main(String[] args) {
        int n = 4, e = 4;//分别代表结点个数和边的数目
        String[] labels = {"V1", "V2", "V3", "V4"};//结点的标识
        AMWGraph graph = new AMWGraph(n);
        for (String label : labels) {
            graph.insertVertex(label);//插入结点
        }
        //插入四条边
        graph.insertEdge(0, 1, 2);
        graph.insertEdge(0, 2, 5);
        graph.insertEdge(2, 3, 8);
        graph.insertEdge(3, 0, 7);

        System.out.println("结点个数是：" + graph.getNumOfVertex());
        System.out.println("边的个数是：" + graph.getNumOfEdges());

        graph.deleteEdge(0, 1);//删除<V1,V2>边
        System.out.println("删除<V1,V2>边后...");
        System.out.println("结点个数是：" + graph.getNumOfVertex());
        System.out.println("边的个数是：" + graph.getNumOfEdges());

        graph.depthFirstSearch();
        System.out.println();
        graph.broadFirstSearch();
        System.out.println();

    }
}

// 测试广搜和深搜
class TestSearch {

    public static void main(String args[]) {
        int n = 8, e = 9;//分别代表结点个数和边的数目
        String labels[] = {"1", "2", "3", "4", "5", "6", "7", "8"};//结点的标识
        AMWGraph graph = new AMWGraph(n);
        for (String label : labels) {
            graph.insertVertex(label);//插入结点
        }
        //插入九条边
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        graph.insertEdge(3, 7, 1);
        graph.insertEdge(4, 7, 1);
        graph.insertEdge(2, 5, 1);
        graph.insertEdge(2, 6, 1);
        graph.insertEdge(5, 6, 1);
        graph.insertEdge(1, 0, 1);
        graph.insertEdge(2, 0, 1);
        graph.insertEdge(3, 1, 1);
        graph.insertEdge(4, 1, 1);
        graph.insertEdge(7, 3, 1);
        graph.insertEdge(7, 4, 1);
        graph.insertEdge(6, 2, 1);
        graph.insertEdge(5, 2, 1);
        graph.insertEdge(6, 5, 1);

        System.out.println("深度优先搜索序列为：");
        graph.depthFirstSearch();
        System.out.println();
        System.out.println("广度优先搜索序列为：");
        graph.broadFirstSearch();
    }
}

// 邻接矩阵的实现方式
class AMWGraph {

    private ArrayList<String> vertexList;//存储点的链表
    private int[][] edges;//邻接矩阵，用来存储边
    private int numOfEdges;//边的数目

    public AMWGraph(int n) {
        //初始化矩阵，一维数组，和边的数目
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
        numOfEdges = 0;
    }

    //得到结点的个数
    public int getNumOfVertex() {
        return vertexList.size();
    }

    //得到边的数目
    public int getNumOfEdges() {
        return numOfEdges;
    }

    //返回结点i的数据
    public String getValueByIndex(int index) {
        return vertexList.get(index);
    }

    //返回边上的权值
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    //插入节点 节点的值为val
    public void insertVertex(String val) {
        vertexList.add(val);
    }

    //插入边 边的权值weight
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        numOfEdges++;
    }

    //删除边
    public void deleteEdge(int v1, int v2) {
        edges[v1][v2] = 0;
        numOfEdges--;
    }

    //删除节点
    public void deleteVertex(int index) {
        for (int i = 0; i < edges.length; i++) {
            edges[index][i] = 0;
            edges[i][index] = 0;
        }
        vertexList.remove(index);
    }

    //得到第一个邻接结点的下标
    public int getFirstNeighbor(int index) {
        for (int i = 0; i < vertexList.size(); i++) {
            if (edges[index][i] > 0) return i;
        }
        return -1;
    }

    //根据前一个邻接结点的下标来取得下一个邻接结点
    public int getNextNeighbor(int index, int indexOfFirstNeighbor) {
        for (int i = indexOfFirstNeighbor + 1; i < vertexList.size(); i++) {
            if (edges[index][i] > 0) return i;
        }
        return -1;
    }

    //深度优先遍历
    private void DFS(boolean[] isVistied, int i) {
        // 访问此节点
        System.out.print(getValueByIndex(i) + "->");
        //设此节点已访问
        isVistied[i] = true;
        //获得其第一个邻接节点
        int index = getFirstNeighbor(i);
        // 存在邻接节点
        while (index != -1) {
            // 未被访问
            if (!isVistied[index]) {
                DFS(isVistied, index);
            }
            // 获取i的下一个邻接节点
            index = getNextNeighbor(i, index);
        }
    }

    public void depthFirstSearch() {
        boolean[] isVistied = new boolean[vertexList.size()];
        for (int i = 0; i < vertexList.size(); i++) {
            if (isVistied[i]) continue;
            DFS(isVistied, i);
            System.out.println();
        }
    }

    // 广度优先遍历
    private void BFS(boolean[] isVistied, int i) {

        LinkedList<Integer> queue = new LinkedList<>();

        queue.offer(i);

        int now = -1;

        while (!queue.isEmpty()) {

            now = queue.poll();

            if (!isVistied[now]) {

                System.out.print(vertexList.get(now) + "->");
                isVistied[now] = true;

                int index = getFirstNeighbor(now);
                while (index != -1) {
                    if (!isVistied[index])
                        queue.offer(index);
                    index = getNextNeighbor(now, index);
                }
            }
        }
    }

    public void broadFirstSearch() {
        boolean[] isVistied = new boolean[vertexList.size()];
        for (int i = 0; i < vertexList.size(); i++) {
            if (isVistied[i]) continue;
            BFS(isVistied, i);
            System.out.println();
        }
    }

}




// 开闭原则
// 里氏代换原则
// 依赖倒置原则
// 迪米特法则
// 合成复用原则
// 接口隔离原则




