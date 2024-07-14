package org.example.acmCoder.Xhs;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @author : JAT
 * @version : 1.0
 * @email : zgt9321@qq.com
 * @since : 2024/4/2
 **/
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        if (n == 2 && Math.abs(nums[0] - nums[1]) > 1) {
            System.out.println(-1);
        }
        int[] dis = new int[n];
        int max = 0;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, nums[i]);
            sum += nums[i];
        }
        int disMax = 0;
        for (int i = 0; i < n; i++) {
            dis[i] = max - nums[i];
            disMax = Math.max(disMax, dis[i]);
        }
        int min = disMax;
        for (int i = 0; i < n; i++) {
            if (dis[i] < disMax) {
                min -= disMax;
                System.out.println(sum + 2 * dis[i] - 1);
            } else {
                if (min > 0) {
                    System.out.println(sum + 2 * (min - 1) - 1);
                } else {
                    System.out.println(sum + 2 * dis[i] - 1);
                }
            }
        }
    }
}


class Main2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int q = sc.nextInt();
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[1] != b[1]) return 1;
            else return a[0] - b[0];
        });
        sc.nextLine();
        String hot_c = sc.nextLine();
        String[] strings = hot_c.split(" ");
        HashSet<String> hs = new HashSet<>(Arrays.asList(strings));
        String[] names = new String[n];
        for (int i = 0; i < n; i++) {
            String[] name_count = sc.nextLine().split(" ");
            names[i] = name_count[0];
            String q_this = sc.nextLine();
            String[] field = q_this.split(" ");
            int count = 0;
            for (String sf : field) {
                if (hs.contains(sf)) {
                    count++;
                }
            }
            int[] hot = new int[2];
            hot[0] = i;
            hot[1] = count;
            pq.add(hot);
        }
        for (int[] ans : pq) {
            System.out.println(names[ans[0]]);
        }
    }
}

