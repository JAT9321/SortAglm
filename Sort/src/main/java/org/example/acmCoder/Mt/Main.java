package org.example.acmCoder.Mt;

import java.util.*;

/**
 * @author : JAT
 * @version : 1.0
 * @email : zgt9321@qq.com
 * @since : 2024/4/6
 **/


// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.nextLine();
        String[] days = new String[n];
        for (int i = 0; i < n; i++) {
            days[i] = in.nextLine();
        }
        int[][] dp = new int[n][26];
        for (int i = 0; i < days[0].length(); i++) {
            dp[0][days[0].charAt(i) - 'a'] = 1;
        }
        int MOD = (int) (1e9 + 7);
        for (int i = 1; i < n; i++) {
            int preSum = 0;
            for (int k = 0; k < 26; k++) {
                preSum = (preSum + dp[i - 1][k]) % MOD;
            }
            for (int j = 0; j < days[i].length(); j++) {
                int i1 = days[i].charAt(j) - 'a';
                dp[i][i1] = (preSum - dp[i - 1][i1] + MOD) % MOD;
            }
        }
        int sum = 0;
        for (int count : dp[n - 1]) {
            sum = (sum + count) % MOD;
        }
        System.out.println(sum);
    }
}


