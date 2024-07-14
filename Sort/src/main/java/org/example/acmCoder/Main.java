package org.example.acmCoder;

import java.util.Scanner;

/**
 * @author : JAT
 * @version : 1.0
 * @email : zgt9321@qq.com
 * @since : 2024/3/29
 **/
public class Main {

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int a, b;
        while (sc.hasNextInt()) {
            a = sc.nextInt();
            b = sc.nextInt();
            Solution s = new Solution();
            int add = s.add(a, b);
            System.out.println(add);
        }

    }

}


class Solution {
    public int add(int a, int b) {
        return a + b;
    }
}
