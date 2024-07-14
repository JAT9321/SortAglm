package org.example;

import java.util.Scanner;

/**
 * @author : JAT
 * @version : 1.0
 * @email : zgt9321@qq.com
 * @since : 2024/4/28
 **/
public class test1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int P = in.nextInt();
        int Q = in.nextInt();
        int x = in.nextInt();
        int alive = 0;
        int x_1 = x;
        if (P < Q) {
            x -= 5 * (Q - P);
            if (x <= 0) {
                System.out.println(Math.min(P, Q) + x_1 / 5);
            } else {
                System.out.println(Q + getMaxDay(x));
            }
        } else {
            x -= 7 * (P - Q);
            if (x <= 0) {
                System.out.println(Math.min(P, Q) + x_1 / 7);
            } else {
                System.out.println(P + getMaxDay(x));
            }
        }
    }

    private static int getMaxDay(int x) {
        int min = Integer.MAX_VALUE / 2;
        int[] idx = new int[2];
        for (int m = x / 3; m >= 1; m--) {
            int n = x - m * 3;
            if (Math.abs(m - n) < min) {
                min = Math.abs(m - n);
                idx[0] = n;
                idx[1] = m;
            }
            if (m < n) break;
        }
        System.out.println(idx[0]);
        System.out.println(idx[1]);
        return Math.min(idx[0], idx[1]) / 3;
    }
}


