package org.example.排序算法;


class Sort {

    // 直接插入排序
    public static void insertSort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            int tmp = nums[i + 1];
            int j = i;
            for (; j >= 0; j--) {
                if (tmp >= nums[j]) break;
                nums[j + 1] = nums[j];
            }
            nums[j + 1] = tmp;
        }
    }


    //希尔排序
    public static void ShellSort(int[] nums, int size) {
        while (size > 1) {
            size = size / 3 + 1;
            for (int i = 0; i < nums.length - size; i++) {
                int tmp = nums[i + size];
                int j = i;
                for (; j >= 0; j -= size) {
                    if (tmp >= nums[j]) break;
                    nums[j + size] = nums[j];
                }
                nums[j + size] = tmp;
            }
        }
    }

    //选择排序
    public static void choiceSort(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[minIndex] > nums[j]) {
                    minIndex = j;
                }
            }
            int tmp = nums[minIndex];
            nums[minIndex] = nums[i];
            nums[i] = tmp;
        }
    }

    //冒泡排序
    public static void bubbleSort(int[] nums) {

        for (int i = 0; i < nums.length; i++) {
            boolean flag = true;
            for (int j = 0; j < nums.length - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    int tmp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = tmp;
                    flag = false;
                }
            }
            if (flag) break;
        }
    }

    //快排
    public static void quickSort(int[] nums, int startIndex, int endIndex) {
        if (startIndex >= endIndex) return;
        int pivotIndex = partition(nums, startIndex, endIndex);
        quickSort(nums, startIndex, pivotIndex - 1);
        quickSort(nums, pivotIndex + 1, endIndex);
    }

    static int partition(int[] nums, int startIndex, int endIndex) {
        int pivotNum = nums[startIndex];
        int left = startIndex;
        int right = endIndex;
        int index = startIndex;
        while (left < right) {
            while (left < right) {
                if (nums[right] < pivotNum) {
                    nums[index] = nums[right];
                    index = right;
                    left++;
                    break;
                }
                right--;
            }
            while (left < right) {
                if (nums[left] > pivotNum) {
                    nums[index] = nums[left];
                    index = left;
                    right--;
                    break;
                }
                left++;
            }
        }
        nums[index] = pivotNum;
        return index;
    }


    //归并排序
    public static void mergeSort(int[] nums) {
        doMergeSort(nums, 0, nums.length - 1);
    }

    static void doMergeSort(int[] nums, int startIndex, int endIndex) {
        if (startIndex >= endIndex) return;
        int mid = startIndex + (endIndex - startIndex) / 2;
        doMergeSort(nums, startIndex, mid);
        doMergeSort(nums, mid + 1, endIndex);
        merge(nums, startIndex, mid, endIndex);
    }

    static void merge(int[] nums, int startIndex, int midIndex, int endIndex) {
        int[] _now = new int[endIndex - startIndex + 1];
        int start1 = startIndex;
        int start2 = midIndex + 1;
        int index = 0;
        while (start1 <= midIndex && start2 <= endIndex) {
            if (nums[start1] < nums[start2]) {
                _now[index] = nums[start1];
                start1++;
            } else {
                _now[index] = nums[start2];
                start2++;
            }
            index++;
        }
        while (start1 <= midIndex) _now[index++] = nums[start1++];
        while (start2 <= endIndex) _now[index++] = nums[start2++];
        for (int i = 0; i < _now.length; i++) {
            nums[i + startIndex] = _now[i];
        }
    }
}


