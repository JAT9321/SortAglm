

//package org.example.acmCoder;
//
//import javax.sound.midi.Soundbank;
//import java.sql.SQLOutput;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Scanner;
//
///**
// * @author : JAT
// * @version : 1.0
// * @email : zgt9321@qq.com
// * @since : 2024/3/29
// **/
//public class ioTest {
//}
//
///*
//有些输入可能是：
//输入一个矩阵，每行以空格分隔。
//3 2 3
//1 6 5
//7 8 9
// */
//
//class Solution1 {
//    public void myFunc(ArrayList<ArrayList<Integer>> arr) {
//        // 使用自测数据按钮时调试用，正式提交时要删掉。
//        System.out.println(arr);
//    }
//}
//
//class Main1 {
//    public static void main(String args[]) {
//        Scanner cin = new Scanner(System.in);
//        ArrayList<ArrayList<Integer>> arr = new ArrayList<ArrayList<Integer>>();
//        while (cin.hasNextLine()) {
//            ArrayList<Integer> row = new ArrayList<Integer>();
//            String line = cin.nextLine();
//            if (line.length() > 0) {
//                String[] arrLine = line.split(" ");
//                for (int i = 0; i < arrLine.length; i++) {
//                    row.add(Integer.parseInt(arrLine[i]));
//                }
//                arr.add(row);
//            } else {
//                break;
//            }
//        }
//
//        new Solution1().myFunc(arr);
//    }
//}
//
///*
//有些输入可能是，输入一个矩阵：
//[[3,2,3],
// [1,6,5],
// [7,8,9]]
// */
//
//
//class Solution2 {
//    public void myFunc(ArrayList<ArrayList<Integer>> arr) {
//        int a = 5;
//
//        // 使用自测数据按钮时调试用，正式提交时要删掉。
//        System.out.println(arr);
//    }
//}
//
//class Main2 {
//    public static void main(String args[]) {
//        Scanner cin = new Scanner(System.in);
//        ArrayList<ArrayList<Integer>> arr = new ArrayList<ArrayList<Integer>>();
//        while (cin.hasNextLine()) {
//            ArrayList<Integer> row = new ArrayList<Integer>();
//            String line = cin.nextLine().trim();
//            if (line.length() > 0) {
//                String[] arrLine = line
//                        .replace("],", "")
//                        .replace(" ", "")
//                        .replace("[", "")
//                        .replace("]", "")
//                        .split(",");
//
//                for (int i = 0; i < arrLine.length; i++) {
//                    row.add(Integer.parseInt(arrLine[i]));
//                }
//                arr.add(row);
//            }
//        }
//
//        new Solution2().myFunc(arr);
//    }
//}
//
//class Fans {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();
//        int x = scanner.nextInt();
//        int[] fans = new int[n];
//        for (int i = 0; i < n; i++) {
//            fans[i] = scanner.nextInt();
//        }
//
//        int[][] dp = new int[x + 1][2];
//        for (int i = 0; i <= x; i++) {
//            Arrays.fill(dp[i], Integer.MAX_VALUE / 2);
//        }
//
//        dp[0][0] = 0;
//        dp[0][1] = 0;
//
//        for (int i = 0; i < n; i++) {
//            for (int j = x; j >= fans[i] / 2; j--) {
//                dp[j][0] = Math.min(Math.min(dp[j][0], dp[j - fans[i] / 2][0] + 1), dp[j - fans[i] / 2][1] + 1);
//                if (fans[i] <= j)
//                    dp[j][1] = Math.min(dp[j][1], dp[j - fans[i]][0] + 1);
//            }
//        }
//
//
//
//
//
//        for (int i = 0; i <= x; i++) {
//            System.out.println(dp[i][0] + "\\" + dp[i][1]);
//        }
//        System.out.println(Math.min(dp[x][0], dp[x][1]));
//    }
//}