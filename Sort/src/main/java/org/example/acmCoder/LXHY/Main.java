package org.example.acmCoder.LXHY;

import java.util.*;

/**
 * @author : JAT
 * @version : 1.0
 * @email : zgt9321@qq.com
 * @since : 2024/4/20
 **/
public class Main {

    public static void main(String[] args) {
        System.out.println(Double.MAX_VALUE == Double.MAX_VALUE);
        System.out.println((double) 3 / 2);
    }
}

class Five {

    static int max = -1;
    static int min = Integer.MAX_VALUE;
    static int finMax = -1;
    static int finMin = Integer.MAX_VALUE;
    static double ans = Double.MAX_VALUE;

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        in.nextLine();
        for (int l = 0; l < T; l++) {
            max = -1;
            min = Integer.MAX_VALUE;
            finMax = -1;
            finMin = Integer.MAX_VALUE;
            ans = Double.MAX_VALUE;
            int n = in.nextInt();
            int m = in.nextInt();
            in.nextLine();
            int s = in.nextInt();
            int t = in.nextInt();
            in.nextLine();
            int[][] road = new int[n + 1][n + 1];
            for (int i = 0; i <= n; i++) {
                Arrays.fill(road[i], -1);
            }
            boolean[] flag = new boolean[n + 1];
            for (int i = 0; i < m; i++) {
                int start = in.nextInt();
                int end = in.nextInt();
                int w = in.nextInt();
                road[start][end] = w;
                road[end][start] = w;
            }
            dfs(road, s, t, flag);
            if (ans == Double.MAX_VALUE) {
                System.out.println("%%%");
            } else {
                if (finMax % finMin == 0) {
                    System.out.println(finMax / finMin);
                } else {
                    int gcd_num = gcd(finMax, finMin);
                    System.out.println(finMax / gcd_num + "/" + finMin / gcd_num);
                }
            }
        }

    }


    static void dfs(int[][] road, int s, int t, boolean[] flag) {
        if (flag[s]) return;
        flag[s] = true;
        int tmax = max;
        int tmin = min;
        for (int i = 0; i < road[s].length; i++) {
            if (road[s][i] != -1 && !flag[i]) {
                max = Math.max(max, road[s][i]);
                min = Math.min(min, road[s][i]);
                if (i == t) {
                    double tans = (double) max / (double) min;
                    if (tans < ans) {
                        finMax = max;
                        finMin = min;
                        ans = tans;
                    }
                } else {
                    dfs(road, i, t, flag);
                }
                max = tmax;
                min = tmin;
            }
        }
        flag[s] = false;
    }

    static int gcd(int a, int b) {
        int c;
        while (b != 0) {
            c = a % b;
            a = b;
            b = c;
        }
        return a;
    }
}


class Bo {
    public static int res;
    public static String Res;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int i = 0; i < T; i++) {
            int n = in.nextInt();
            int e = in.nextInt();
            int s = in.nextInt();
            int t = in.nextInt();
            List<Integer>[] g = new List[n + 1];
            int[][] weight = new int[n + 1][n + 1];
            boolean[] flag = new boolean[n + 1];
            Arrays.setAll(g, z -> new ArrayList<>());
            for (int j = 0; j < e; j++) {
                int u = in.nextInt();
                int v = in.nextInt();
                int w = in.nextInt();
                g[u].add(v);
                g[v].add(u);
                weight[u][v] = w;
                weight[v][u] = w;
            }
            List<Integer> list = new ArrayList<>();
            Res = null;
            res = Integer.MAX_VALUE;
            dfs(g, weight, flag, s, t, list);
            if (Res == null) {
                System.out.println("%%%");
            } else {
                System.out.println(Res);
            }

        }

    }

    public static boolean dfs(List<Integer>[] g, int[][] weight, boolean[] flag, int father, int t, List<Integer> list) {
        if (t == father) return true;
        flag[father] = true;
        for (int son : g[father]) {
            list.add(weight[father][son]);
            if (!flag[son] && dfs(g, weight, flag, son, t, list)) {
                int max = 0, min = Integer.MAX_VALUE;
                for (Integer integer : list) {
                    max = integer > max ? integer : max;
                    min = min < integer ? min : integer;
                }
                if (max / min < res) {
                    res = max / min;
                    if (max % min == 0) {
                        Res = String.valueOf(res);
                    } else {
                        Res = max + "/" + min;
                    }
                }
            }
            list.remove(list.size() - 1);
        }
        flag[father] = false;
        return false;
    }
}