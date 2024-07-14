package org.example.图;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Graph {

    //图的实现:利用邻接矩阵的形式实现
    //注意:此处实现的是无向图
    //定义图的内部属性
    private List<String> vertexList;//用于存储顶点的集合
    private int[][] edges;//用来保存图对应的邻接矩阵
    private int edgeOfNums;//显示边的个数
    //定义boolean数组:记录某个结点是否已经被访问,数组大小和结点个数大小相同
    private boolean[] isVisited;

    public static void main(String[] args) {
        //定义图的所有顶点
        String[] vertexs = {"A", "B", "C", "D", "E"};
        //创建图
        Graph graph = new Graph(vertexs.length);
        //添加顶点到图中
        for (String vertex : vertexs) {
            graph.addVertex(vertex);
        }
        //添加边到图中
        //A-B A-C B-C B-D B-E
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        //显示图的邻接矩阵信息
        graph.showGraph();
        //进行图的深度优先遍历A==>B==>C==>D==>E
        graph.DFS();

    }

    //定义图的构造器
    public Graph(int n) {//n表示定义的图的顶点的个数
        //初始化邻接矩阵和顶点集合
        edges = new int[n][n];
        vertexList = new ArrayList<String>(n);
    }

    //===========深度优先遍历的方法===========
    //1.根据当前结点的下标获取其第一个邻接结点的下标
    //若存在,返回其下标;若不存在,则返回-1
    public int getFirstNeighbor(int index) {
        //遍历顶点集合
        for (int i = 0; i < vertexList.size(); i++) {
            if (edges[index][i] == 1) {
                return i;
            }
        }
        //若没有邻接的结点,则返回-1
        return -1;
    }

    //2.根据当前结点的前一个邻接结点的下标获取下一个邻接结点的下标
    //同上:若存在,返回其下标;若不存在,则返回-1
    public int getNextNeighbor(int v1, int v2) {
        for (int i = v2 + 1; i < vertexList.size(); i++) {
            if (edges[v1][i] == 1) {
                return i;
            }
        }
        //若没有下一个邻接的结点,则返回-1
        return -1;
    }

    //3.深度优先算法DFS
    private void DFS(boolean[] isVisited, int i) {
        //i表示访问的当前结点的下标
        //1.访问该结点,输出
        System.out.print(getVertexByIndex(i) + "==>");
        //2.将该结点设置为已经访问
        isVisited[i] = true;
        //3.查找结点i的第一个邻接的结点w
        int w = getFirstNeighbor(i);
        //4.判断w是否存在
        while (w != -1) {
            //表示w(第一个邻接的结点存在)
            //5.判断w是否已经被访问
            if (!isVisited[w]) {
                //6.1如果没有被访问,则对w进行深度优先遍历递归
                DFS(isVisited, w);
            }
            //6.2如果w已经被访问过了
            //查找v的w的邻接结点的下一个邻接结点
            //同时回到4:判断其是否存在
            w = getNextNeighbor(i, w);
        }
    }

    //上面的DFS没有考虑W不存在,返回到结点i的下一个结点继续进行DFS
    //我们利用一个DFS方法的重载来完成DFS的回溯
    public void DFS() {
        isVisited = new boolean[vertexList.size()];//初始化isVisited的size
        for (int i = 0; i < getVertexNum(); i++) {
            if (!isVisited[i]) {
                DFS(isVisited, i);
            }
        }
    }
    //===========深度优先遍历的方法(为上)===========

    //插入结点(顶点的方法)
    public void addVertex(String vertex) {
        vertexList.add(vertex);
    }
    //添加边:传入参数

    /**
     * @param v1     表示点的下标即使第几个顶点  "A"-"B" "A"->0 "B"->1
     * @param v2     第二个顶点对应的下标
     * @param weight 表示是否连接0/1
     */
    public void insertEdge(int v1, int v2, int weight) {
        //由于图为无向图,故将两个顶点的正序和反序都设置为weight
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        edgeOfNums++;//每增加一条边,将边的条数加1
    }

    //图的常用API
    //1.获取两个顶点的权值weight
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    //2.获取图的边的长度
    public int getEdgeOfNums() {
        return edgeOfNums;
    }

    //3.返回结点i(下标)对应的数据 0->"A" 1->"B" 2->"C"
    public String getVertexByIndex(int index) {
        return vertexList.get(index);
    }

    //4.图的邻接矩阵的显示方法
    public void showGraph() {
        for (int[] edge : edges) {
            System.out.println(Arrays.toString(edge));
        }
    }

    //5.获取顶点的个数
    public int getVertexNum() {
        return vertexList.size();
    }


}
//输出结果:
//        图的邻接矩阵:
//        [0,1,1,0,0]
//        [1,0,1,1,1]
//        [1,1,0,0,0]
//        [0,1,0,0,0]
//        [0,1,0,0,0]
//        图的深度优先遍历:
//        A==>B==>C==>D==>E==>
