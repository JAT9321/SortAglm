package org.example.排序算法;

import java.util.Arrays;

public class 归并排序 {

    static int count = 0;

    public static void main(String[] args) {

        int[] ints = {9, 7, 5, 4, 6};
        Arrays.stream(ints).forEach(num -> System.out.print(num + " "));
        System.out.println();
        sort(ints, 0, ints.length - 1);
        Arrays.stream(ints).forEach(num -> System.out.print(num + " "));
        System.out.println();
        System.out.println(count);
    }

    static void sort(int[] nums, int left, int right) {

        if (left >= right) return;

        int mid = left + (right - left) / 2;

        sort(nums, left, mid);
        sort(nums, mid + 1, right);
        merge(nums, left, mid, right);
    }

    private static void merge(int[] nums, int left, int mid, int right) {
        int len = right - left + 1;
        int[] tmp = new int[len];
        int l = left;
        int r = mid + 1;
        int index = 0;
        while (l <= mid && r <= right) {
            if (nums[l] <= nums[r]) {
                tmp[index++] = nums[l++];
            } else {
                count += mid - l + 1;
                tmp[index++] = nums[r++];
            }
        }
        while (l <= mid) {
            tmp[index++] = nums[l++];
        }
        while (r <= right) {
            tmp[index++] = nums[r++];
        }
        index = 0;
        while (left <= right) {
            nums[left++] = tmp[index++];
        }
    }

}



