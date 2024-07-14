package org.example.acmCoder.xc;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @author : JAT
 * @version : 1.0
 * @email : zgt9321@qq.com
 * @since : 2024/4/16
 **/
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];
        int[] c = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        for (int i = 0; i < n; i++) {
            b[i] = in.nextInt();
        }
        Set<Integer> sumAB = new HashSet<>();
        for (int i = 0; i < n; i++) {
            c[i] = in.nextInt();
            sumAB.add(a[i] + b[i]);
        }
        int res = 0;
        for (int cc : c) {
            if (sumAB.contains(cc)) res++;
        }
        System.out.println(res);
    }

}


