package org.example.acmCoder.XHS2;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @author : JAT
 * @version : 1.0
 * @email : zgt9321@qq.com
 * @since : 2024/4/26
 **/
public class Main {
}

class One {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        sc.nextLine();
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<int[]>((a, b) -> {
            if (a[3] != b[3]) return b[3] - a[3];
            if (a[1] != b[1]) return b[1] - a[1];
            return a[2] - b[2];
        });
        int x, y, id, sum;
        for (int i = 1; i <= n; i++) {
            x = sc.nextInt();
            y = sc.nextInt();
            id = i;
            sum = x + y * 2;
            priorityQueue.offer(new int[]{x, y, id, sum});
        }

        int[] ids = new int[k];
        for (int i = 0; i < k; i++) {
            int[] poll = priorityQueue.poll();
            assert poll != null;
            ids[i] = poll[2];
        }
        Arrays.sort(ids);
        for (int i : ids) {
            System.out.print(i + " ");
        }
    }
}


class Three {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int x = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        int[][][] dp = new int[n + 1][x + 1][2];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= x; j++) {
                Arrays.fill(dp[i][j], Integer.MAX_VALUE / 2);
            }
        }
        dp[0][0][0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= x; j++) {
                for (int k = 0; k < 2; k++) {
                    dp[i][j][k] = Math.min(dp[i][j][k], dp[i - 1][j][k]);
                    if (j >= a[i - 1] / 2)
                        dp[i][j][k] = Math.min(dp[i][j][k], Math.min(dp[i - 1][j][k], dp[i - 1][j - a[i - 1] / 2][k] + 1));
                    if (k == 1) {
                        if (j >= a[i - 1])
                            dp[i][j][k] = Math.min(dp[i][j][k], Math.min(dp[i - 1][j][k], dp[i - 1][j - a[i - 1]][k - 1] + 1));
                    }
                }
            }
        }
        System.out.println(Math.min(dp[n][x][0], dp[n][x][1]));
    }
}

class Two {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        int max = Integer.MIN_VALUE;
        int chaSum = 0;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
            sum += a[i];
            max = Math.max(a[i], max);
        }

        for (int i = 0; i < n; i++) {
            chaSum += max - a[i];
        }

        for (int i = 0; i < n; i++) {
            if (a[i] == max) {
                System.out.println(sum);
            } else {
                int cha = max - a[i];
                int nowChaSum = chaSum - cha;
                if (cha <= nowChaSum) {
                    System.out.println(sum + cha * 2 - 1);
                } else {
                    int otherCha = cha - nowChaSum - 1;
                    System.out.println(max * (n - 1) + otherCha * 2 * 2 - 1 + a[i] + nowChaSum);
                }
            }

        }

    }
}