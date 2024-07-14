package org.example.leetcode;


/*
迷宫问题是一个经典的算法问题，
目标是找到从迷宫的起点到终点的最短路径，
在程序中可以简单的抽象成一个M*N的二维数组矩阵，
然后我们需要从这个二维矩阵中找到从起点到终点的最短路径。
其中，通常使用 0 表示可行走的路，
用 1 表示障碍物，起点和终点分别标记为 S 和 E。
例如，下图是一个简单的迷宫问题：
0 0 0 0 0 0
0 1 0 1 1 0
0 1 0 0 0 0
0 0 1 1 0 0
0 1 0 1 0 0
S 0 0 0 E 0
 */

import java.util.*;
import java.util.stream.Collectors;

public class 迷宫 {

    public static void main(String[] args) {
        深度遍历.dfs(深度遍历.startX, 深度遍历.startY);
    }

}

class 深度遍历 {

    static final int ROW = 5;
    static final int COL = 5;

    // 迷宫的地图，0 表示可以通过的路，1 表示墙壁，2 表示已经走过的路
    static int[][] map = new int[][]{
            {0, 1, 1, 1, 1},
            {0, 0, 0, 1, 1},
            {0, 1, 0, 0, 1},
            {0, 1, 1, 0, 0},
            {0, 0, 0, 0, 0}
    };

    // 迷宫的起点和终点
    static final int startX = 0;
    static final int startY = 0;
    static final int endX = 4;
    static final int endY = 4;

    static List<int[]> path = new ArrayList<int[]>();

    static int[][] steps = new int[][]{
            {-1, 0}, {0, -1}, {1, 0}, {0, 1}
    };

    // 深度遍历
    public static void dfs(int x, int y) {

        if (x < 0 || x >= ROW || y < 0 || y >= COL) return;
        if (x == endX && y == endY) {
            for (int[] p : path) {
                System.out.print("(" + p[0] + "," + p[1] + ") ");
            }
            System.out.println("(" + x + "," + y + ")");
            return;
        }
        if (map[x][y] != 0) return;

        //此位置标记为 已走过
        map[x][y] = 2;
        path.add(new int[]{x, y});
        // 分别搜索当前位置的上下左右四个方向
        for (int[] step : steps) {
            dfs(x + step[0], y + step[1]);
        }
//        //向上
//        dfs(x - 1, y);
//        //向左
//        dfs(x, y - 1);
//        //向下
//        dfs(x + 1, y);
//        //向右
//        dfs(x, y + 1);
        path.remove(path.size() - 1);
        map[x][y] = 0;
    }

    public static void main(String[] args) {
        dfs(startX, startY);
    }
}


class 广度遍历 {

    static int[][] maze = {
            {0, 1, 0, 0, 0},
            {0, 1, 0, 1, 0},
            {0, 0, 0, 0, 0},
            {0, 1, 1, 1, 0},
            {0, 0, 0, 0, 0}
    };

    public static void main(String[] args) {

        // 寻找路径
        List<int[]> path = bfs(maze, new int[]{0, 0}, new int[]{4, 2});

        // 输出路径
        if (path != null) {
            for (int[] point : path) {
                System.out.print(Arrays.toString(point));
            }
            System.out.println();
        } else {
            System.out.println("No solution found.");
        }
    }

    public static List<int[]> bfs(int[][] maze, int[] start, int[] end) {

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(start);
        // 定义路径跟踪数组
        Map<int[], int[]> trace = new HashMap<>();
        trace.put(start, null);
        // 定义已经访问过的点集合
        Set<int[]> visited = new HashSet<>();
        visited.add(start);
        // 定义方向数组，分别表示上下左右四个方向
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!queue.isEmpty()) {
            // 取出队列中的下一个点
            int[] current = queue.poll();

            // 如果当前点是终点，返回路径
            if (Arrays.equals(current, end)) {
                List<int[]> path = new ArrayList<>();
                while (current != null) {
                    path.add(current);
                    current = trace.get(current);
                }
                Collections.reverse(path);
                return path;
            }

            // 遍历四个方向
            for (int[] direction : directions) {
                int[] neighbor = new int[]{current[0] + direction[0], current[1] + direction[1]};
                // 如果邻居在迷宫范围内，且没有被访问过，且不是墙，加入队列和访问集合，并记录路径
                if (neighbor[0] >= 0 && neighbor[0] < maze.length && neighbor[1] >= 0 && neighbor[1] < maze[0].length
                        && !visited.contains(neighbor) && maze[neighbor[0]][neighbor[1]] == 0) {
                    queue.add(neighbor);
                    visited.add(neighbor);
                    trace.put(neighbor, current);
                }
            }


        }
        // 如果搜索结束还没有找到路径，返回null
        return null;
    }
}
