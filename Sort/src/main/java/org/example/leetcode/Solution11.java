package org.example.leetcode;//回溯

import java.util.*;

public class Solution11 {
    private static List<int[]> ans = new ArrayList<>();
    private static List<int[]> path = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int[][] maze = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    maze[i][j] = sc.nextInt();
                }
            }
            //深度优先搜索
            dfs(maze, 0, 0);
            for (int[] arr : ans) {
                System.out.println("(" + arr[0] + "," + arr[1] + ")");
            }
            //清空路径path，结果集ans，实际ans指向新建对象，无需清空
            path.clear();
            ans.clear();
        }
    }

    private static void dfs(int[][] maze, int i, int j) {
        //结束条件，一定要用路径新建集合，path最后会remove为空
        if (i == maze.length - 1 && j == maze[0].length - 1) {
            path.add(new int[]{i, j});
            ans = new ArrayList<>(path);
            return;
        }
        //边界条件，剪枝
        if (i < 0 || i >= maze.length || j < 0 || j >= maze[0].length || maze[i][j] == 1) return;

        //i,j加入集合,并且当前位置设置为障碍
        path.add(new int[]{i, j});
        maze[i][j] = 1;
        //向四个方向递归
        dfs(maze, i + 1, j);
        dfs(maze, i, j + 1);
        dfs(maze, i - 1, j);
        dfs(maze, i, j - 1);
        //找到死胡同，回溯，撤销i,j选择，当前位置设置为可走
        path.remove(path.size() - 1);
        maze[i][j] = 0;
    }
}
