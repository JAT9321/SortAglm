package org.example.leetcode;

import java.util.*;

public class ALg {

    public static void main(String[] args) {
//        new Solution1().canJump(new int[]{1, 2, 3});
//        new Solution2().largestSumAfterKNegations(new int[]{4, 2, 3}, 1);
//        new Solution3().eraseOverlapIntervals(new int[][]{{-52, 31}, {-73, -26}, {82, 97}, {-65, -11}});
//        new Solution4().findMinArrowShots(new int[][]{{-2147483646, -2147483645}, {2147483646, 2147483647}});
//        new Solution6().monotoneIncreasingDigits(332);
        new Solution7().atMostNGivenDigitSet(new String[]{"1", "3", "5", "7"}, 100);
    }

}


class Solution1 {
    public boolean canJump(int[] nums) {

        int maxFar = 0;
        int maxIndex = 0;

        for (int i = 0; i < nums.length - 1; ) {
            maxFar = nums[i] + i;
            maxIndex = i;
            for (int j = i + 1; j < nums.length - 1 && j < nums[i] + i; j++) {
                if (maxFar <= nums[j] + j) {
                    maxFar = nums[j] + j;
                    maxIndex = j;
                }
            }
            if (maxFar >= nums.length - 1) return true;
            if (maxIndex == i) return false;
            i = maxIndex;
        }
        return true;
    }

    // 回溯超时
    boolean back(int[] nums, int index) {

        if (index == nums.length - 1) return true;

        int step = nums[index];
        for (int i = 1; i <= step; i++) {
            if (back(nums, index + i)) return true;
        }
        return false;
    }

}

class Solution2 {
    public int largestSumAfterKNegations(int[] nums, int k) {
        Arrays.sort(nums);
        int sum = 0;
        int min = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; ) {
            if (nums[i] < 0) {
                if (k > 0) {
                    sum += -nums[i];
                    k--;
                } else {
                    sum += nums[i];
                }
                min = Math.max(nums[i], min);
                i++;
            } else if (nums[i] == 0) {
                k = 0;
                i++;
            } else {
                if (k > 0) {
                    min = Math.min(nums[i], Math.abs(min));
                    if (min != nums[i]) {
                        sum -= min;
                        sum = sum + ((k + 1) % 2 == 0 ? -min : min);
                        k = 0;
                    } else {
                        sum = sum + (k % 2 == 0 ? nums[i] : -nums[i]);
                        i++;
                        k = 0;
                    }
                } else {
                    sum += nums[i];
                    i++;
                }
            }
        }

        if (k > 0) {
            sum -= -nums[nums.length - 1];
            sum += (k + 1) % 2 == 0 ? nums[nums.length - 1] : -nums[nums.length - 1];
        }

        return sum;
    }
}

class Solution3 {
    public int eraseOverlapIntervals(int[][] intervals) {

        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0]) return a[1] - b[1];
            else return a[0] - b[0];
        });

        int len = intervals.length;
        int remove_count = 0;

        int pre = 0;

        for (int i = 1; i < len; i++) {
            if (intervals[i][0] >= intervals[pre][0] && intervals[i][0] < intervals[pre][1]) {
                remove_count++;
            } else {
                pre = i;
            }
        }

        return remove_count;

    }
}

class Solution4 {
    public int findMinArrowShots(int[][] points) {

        Arrays.sort(points, (a, b) -> {
            if (a[0] >= b[0]) return 1;
            else return -1;
        });

        if (points.length == 1) return 1;
        int count = 1;

        for (int i = 1; i < points.length; i++) {
            if (points[i - 1][1] < points[i][0]) {
                count++;
            } else {
                points[i][1] = Math.min(points[i - 1][1], points[i][1]);
            }

        }
        return count;

    }
}

class Solution5 {
    public int[][] merge(int[][] intervals) {

        List<int[]> res = new ArrayList<>();
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        int left = 0;
        int right = 0;
        int maxRight = intervals[0][1];
        for (; right < intervals.length; right++) {
            if (maxRight >= intervals[right][0]) {
                maxRight = Math.max(maxRight, intervals[right][1]);
            } else {
                res.add(new int[]{intervals[left][0], maxRight});
                maxRight = intervals[right][1];
                left = right;
            }
        }
        res.add(new int[]{intervals[left][0], maxRight});

//        int[][] ans = new int[res.size()][];
//        for (int i = 0; i < res.size(); i++) {
//            ans[i] = res.get(i);
//        }

        return res.toArray(new int[res.size()][]);
    }
}

class Solution6 {

    public int monotoneIncreasingDigits(int n) {

        char[] chars = String.valueOf(n).toCharArray();

        int flag = chars.length;

        for (int i = chars.length - 1; i > 0; i--) {
            if (chars[i] < chars[i - 1]) {
                chars[i - 1] -= 1;
                flag = i;
            }
        }

        for (int i = flag; i < chars.length; i++) {
            chars[i] = '9';
        }

        return Integer.parseInt(new String(chars));

    }
}

class Solution7 {

    int ans = 0;

    public int atMostNGivenDigitSet(String[] digits, int n) {

        String target = String.valueOf(n);


        boolean[] used = new boolean[digits.length];
        Arrays.fill(used, false);

        back(target, 0, "", digits, used);

        return ans;

    }

    void back(String target, int startIndex, String nowStr, String[] digits, boolean[] used) {

        if (!nowStr.equals("") && Integer.parseInt(target) >= Integer.parseInt(nowStr)) {
            ans++;
        } else if (Integer.parseInt(target) < Integer.parseInt(nowStr)) {
            return;
        }

        for (int i = startIndex; i < digits.length; i++) {
            if (i > 0 && digits[i - 1].equals(digits[i]) && used[i - 1] == false)
                continue;
            used[i] = true;
            back(target, i + 1, nowStr + digits[i], digits, used);
            used[i] = false;
        }
    }


}


class Solution8 {
    /**
     * 假设 nums 数组长度是 m, 假设n是5位数，
     * <p>
     * 先考虑位数比n少的情况
     * 考虑四位数时，每一位上的数字就可以在nums数组中随意选择，共 m ^ 4
     * 考虑三位数时，每一位上的数字就可以在nums数组中随意选择，共 m ^ 3
     * 考虑二位数时，每一位上的数字就可以在nums数组中随意选择，共 m ^ 2
     * 考虑一位数时，每一位上的数字就可以在nums数组中随意选择，共 m ^ 1
     * 累计以上结果即可；由于 nums 中不存在 0，所以不用排序0的情况
     * 再考虑位数和n一样的情况
     * 从高位往低位考虑，假设n从高到低位分别为 a1 a2 a3 a4 a5
     * 题目：nums递增，nums中元素各不相同
     * <p>
     * 第一位数字必须小于等于a1，
     * 如果nums[0] > a1，nums中所有元素都大于a1，当前位置没有可选择的数字，结束
     * 如果nums[n-1] < a1, nums中所有元素都小于a1，当前位置可以随便选，当前位置选完后，剩余位置也可以随便选，共m*m^4种，结束
     * 假设nums中最后一个小于等于a1的数字在第i个位置，其值为x
     * 如果 x < a1, 说明当前位置，可以选择 x 及以前 共i个位置元素，有 i 种选择，
     * 那么后边四位数字可以随便选，共 m^4 种
     * 此时选法有 i * m^4，
     * 直接结束
     * 如果 x = a1,
     * 如果当前位置选择 x 以前 共i-1个位置元素，有 i 种选择，
     * 此时后边四位数字可以随便选，共 m^4 种
     * 此时选法有 i * m^4,
     * (这里正确的前提是nums中元素不重复且递增，否则x=a1并不能保证x前面的数字都小于a1，也就是需要过滤重复数字)
     * 如果当前位置选择x，相当于1种选择
     * 此时之后的数字如何选择，也就是继续流程，考虑a2，a3，a4，a5了
     * 如果此时已经是最后一个位置了，那么无需后续，直接ans+1即可。
     * 第二位数字必须小于等于a2，
     * ...
     * <p>
     * 因为nums递增，考虑有边界二分搜索来找 小于等于x 的最右边界
     * <p>
     * <p>
     * 总结一下就是：
     * 对 x 进行「从高到低」的处理（假定 x 数位为 n），
     * 对于我们要拼凑的数字的第 k 位而言（k不为最高位），
     * 假设在 x 中第 k 位为 cur，那么为了满足「大小限制」关系，我们只能在 [1,cur] 范围内取数(当取cur时，需要考虑后续取值)，
     * 同时为了满足「数字只能取自 nums」的限制，我们可以利用 nums 本身有序，对其进行二分，找到满足 nums[mid] <= cur 的最大下标 r ，
     * 根据 nums[r] 与 cur 的关系进行分情况讨论：
     * nums[r] < cur: 此时算上 nums[r]，位置 k 共有 r+1 种选择，而后面的每个位置由于 nums[i] 可以使用多次，每个位置都有 m 种选择，共有 n-k 个位置，
     * 因此该分支共有 (r+1)*m^(n-k) 种合法方案，由于后续位置的方案数（均满足小于关系）已经在这次被统计完成，累加后进行 break；
     * nums[r] = cur：此时位置 k 也有 r+1 种选择
     * 若k位置选择r前面的数字，肯定小于cur，此时后面的每个位置由于 nums[i] 可以使用多次，每个位置都有 m 种选择，共有 n-k 个位置，
     * 因此该分支往后共有 r*m^(n-k) 种合法方案。
     * 若k位置选择nums[r]，由于 nums[r]=cur，往后还有分支可决策（需要统计），因此需要继续处理，也就是不能break；
     * 该分支有多少种方案，就是继续走后续流程对ans累加了多少呗
     * 如果k=0，说明当前已经是最后一个位置了，那没有后续了，直接ans+1就行了
     * nums[r] > cur: 说明nums所有元素都比cur大，没法选择，并且该分支往后不再满足「大小限制」要求，合法方案数为 0，直接 break。
     * <p>
     * 扩展：
     * 本题相当于考虑 数值在 1-n 之间的取值情况
     * dp(x): 取值在 [1,n] 的组合数
     * 如果求 取值在 [l,r] 范围的组合数 = dp(r) - dp(l - 1)
     *
     * @param digits
     * @param n
     * @return
     */
    public int atMostNGivenDigitSet(String[] digits, int n) {
        int ans = 0;
        // 得到n的位数，以及每个位置上的数字，倒序保存在list中
        List<Integer> list = new ArrayList<>();
        while (n != 0) {
            list.add(n % 10);
            n /= 10;
        }
        n = list.size();
        // 可选数组的元素个数
        int m = digits.length;
        int[] nums = new int[m];
        // 字符串数组转整型数组
        for (int i = 0; i < m; ++i) {
            nums[i] = Integer.parseInt(digits[i]);
        }
        // 第一部分：位数 小于 n 的部分
        for (int i = 1; i < n; ++i) {
            // 一位数，每次可以选择m个，选择1次
            // 二位数，每次可以选择m个，选择2次
            // n-1位数，每次可以选择m个，选择n-1次
            ans += permutations(m, i);
        }
        // 第二部分：位数 等于 n 的部分
        // i 执行原数字n中从高到低位数值的索引，j 代表当前要凑的数字的从高到低位置索引
        for (int i = n - 1, j = 1; i >= 0; --i, ++j) {
            // 原数字当前位置取值
            int cur = list.get(i);
            // 所有可选值都比 cur 大，没法选择，结束
            if (nums[0] > cur) {
                break;
            }
            // 所有可选值都比cur小，那么当前位置可以随意选择
            // 并且当前位置选完后，后续所有位置也可以随意选择
            if (nums[m - 1] < cur) {
                ans += m * permutations(m, n - j);
                // 注意这里相当于已经考虑完了全部后续，需要结束
                break;
            }
            // 在 nums 中寻找 小于等于 cur 的最大值位置
            // 由于上面两个if已经排除掉了特殊情况，所以这里l不可能会是-1
            int l = rightBoundBinarySearch(nums, cur);
            // 如果这个值小于cur，那么当前位置可以选择这个值及之前所有位置，也就是 l + 1 种选择
            // 并且当前位置选完后，后续所有位置也可以随意选择
            if (nums[l] < cur) {
                ans += (l + 1) * permutations(m, n - j);
                // 注意这里相当于已经考虑完了全部后续，需要结束
                break;
            }
            // 如果这个值等于cur，由于nums递增且不重复，那么这个值前面所有取值肯定都小于cur
            // 如果当前位置选择前cur种情况，那么后续位置可以随意选择
            ans += l * permutations(m, n - j);
            // 如果当前位置选择cur这个值，那么后续位置如何取值，相当于继续进行for循环，所以这里没有break
            // 如果已经是最后一个位置了，那直接ans+1把这种取值算进来即可
            if (i == 0) {
                ans++;
            }
        }
        return ans;
    }


    /**
     * 排列数：每次可以选择k个数字，选择n次，结果数
     *
     * @param k
     * @param n
     * @return
     */
    private int permutations(int k, int n) {
        return (int) Math.pow(k, n);
    }


    /**
     * 右边界二分搜索，在 递增序列 nums 中寻找 <= target 的最后一个元素位置
     * 二分搜索的模板：https://labuladong.github.io/algo/1/11/
     *
     * @param nums
     * @param target
     * @return
     */
    public static int rightBoundBinarySearch(int[] nums, int target) {
        int l = 0, r = nums.length;
        while (l < r) {
            int mid = l + ((r - l) >> 1);
            if (nums[mid] <= target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        // 注意while中，当 nums[mid]<=target 时，l 会自增，所以最终结果必然要对l-1
        // 当nums所有元素都比target小时，会返回最后一个位置
        // 当nums所有元素都比target大时，会返回-1
        return l - 1;
    }
}

class Solution9 {
    public static void main(String[] args) {
        rotate(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
    }

    public static void rotate(int[][] matrix) {
        int n = matrix.length;
        int[][] ans = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ans[j][n - 1 - i] = matrix[i][j];
                System.out.print(ans[j][n - 1 - i] + " ");
            }
            System.out.println();

        }
        matrix = ans;
    }
}


//n个小朋友，k个苹果，输出分配苹果的不同方法,和具体怎么分配
//        eg:
//        n=2 k=3
//        输出:
//        4
//        xxx|
//        xx|x
//        x|xx
//        |xxx
class Solution10 {

    List<String> res = new ArrayList<String>();
    List<StringBuilder> ans = new ArrayList<>();

    public static void main(String[] args) {
        new Solution10().apple();
    }

    void apple() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        back(n, k, 0);
        System.out.println(res.size());
        res.forEach(System.out::println);
    }

    void back(int n, int k, int startIndex) { // startIndex 代表分到了第几个苹果
        // ans.size() 代表分给了几个人，如果超过n，没必要继续下去了
        if (ans.size() > n) return;
        // 苹果分完了
        if (startIndex >= k) {
            StringBuilder ssb = new StringBuilder();
            for (int i = 0; i < ans.size() - 1; i++) {
                if (ans.get(i).length() == 0) {
                    ssb.append('|');
                } else {
                    ssb.append(ans.get(i)).append('|');
                }
            }
            ssb.append(ans.get(ans.size() - 1));
//            ssb.append("|".repeat(n - ans.size())); // 剩余的人都没有分到苹果，都是|
            res.add(ssb.toString());
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = startIndex; i < k && ans.size() < n; i++) {
            // 第一次 不给人分苹果
            if (i == startIndex) {
                ans.add(sb);
                back(n, k, i);
                ans.remove(ans.size() - 1);
            }
            // 后续给此人分苹果 ， i 开始向下传递加1 作为下个人的起始startIndex，从第startIndex开始分
            sb.append('x');
            ans.add(sb);
            back(n, k, i + 1);
            ans.remove(ans.size() - 1);
        }
    }
}


class AppleAllocation {


    public static void allocateApples(int n, int k, List<String> allocation, List<String> result) {
        if (n == 0 && k == 0) {
            result.add(String.join("", allocation));
        } else {
            for (int i = Math.min(n, k); i > 0; i--) {
                List<String> newAllocation = new ArrayList<>(allocation);
                for (int j = 0; j < i; j++) {
                    newAllocation.add("x");
                }
                newAllocation.add("|");
                allocateApples(n - i, k - i, newAllocation, result);
            }
        }
    }

    public static void main(String[] args) {
        int n = 2; // 小朋友的个数
        int k = 3; // 苹果的个数

        List<String> allocation = new ArrayList<>();
        List<String> result = new ArrayList<>();

        allocateApples(n, k, allocation, result);

        for (String alloc : result) {
            System.out.println(alloc);
        }
    }
}


class MyHashSet {

    final private LinkedList<Integer>[] array;
    final private int INIT_CAPACITY = 10000;

    public MyHashSet() {
        array = new LinkedList[INIT_CAPACITY];
    }

    public void add(int key) {
        int hashCode = key % INIT_CAPACITY;
        if (array[hashCode] == null) {
            array[hashCode] = new LinkedList<Integer>();
            array[hashCode].add(key);
            return;
        }
        if (exitKey(hashCode, key)) {
            return;
        }
        array[hashCode].add(key);
    }

    public void remove(int key) {
        int hashCode = key % INIT_CAPACITY;
        if (exitKey(hashCode, key)) {
            array[hashCode].remove((Integer) key);
        }
    }

    public boolean contains(int key) {
        int hashCode = key % INIT_CAPACITY;
        return exitKey(hashCode, key);
    }

    private boolean exitKey(int hashCode, int key) {
        if (array[hashCode] == null) return false;
        for (int k : array[hashCode]
        ) {
            if (k == key) {
                return true;
            }
        }
        return false;
    }
}


class Solution12 {
    private Node[] array;
    private int vision = 0;

    public static void main(String[] args) {
        Solution12 solution12 = new Solution12();
        solution12.SnapshotArray(3);
//        System.out.println(solution12.snap());
        solution12.set(0, 5);
        System.out.println(solution12.snap());
        solution12.set(0, 6);
        System.out.println(solution12.get(0, 0));
//        Solution12 solution12 = new Solution12();
//        solution12.SnapshotArray(2);
//        System.out.println(solution12.snap());
//        System.out.println(solution12.get(1, 0));
//        System.out.println(solution12.get(0, 0));
//        solution12.set(1, 8);
//        System.out.println(solution12.get(1, 0));
//        solution12.set(0, 20);
//        System.out.println(solution12.get(0, 0));
//        solution12.set(0, 7);

    }

    class Node {
        int val;
        int vision;
        Node next;
    }

    public void SnapshotArray(int length) {
        this.array = new Node[length];
        for (int i = 0; i < length; i++) {
            Node node = new Node();
            node.val = 0;
            node.vision = 0;
            array[i] = node;
        }
    }

    public void set(int index, int val) {
        if (array[index].vision == this.vision)
            array[index].val = val;
        else {
            Node node = new Node();
            node.val = val;
            node.vision = this.vision;
            node.next = array[index];
            array[index] = node;
        }
    }

    public int snap() {
        this.vision += 1;
        return this.vision - 1;
    }

    public int get(int index, int snap_id) {
        if (snap_id == this.vision) return 0;
        Node t = array[index];
        while (t != null) {
            if (t.vision <= snap_id) {
                return t.val;
            }
            t = t.next;
        }
        return 0;
    }
}

class Solution13 {
//    public static void main(String[] args) {
//        int n = 5;
//        System.out.println(n * n - n + 1);
//    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        sc.nextLine();
        String s = sc.nextLine();
        char[] cs = s.toCharArray();
        if (k <= 1) {
            System.out.println(s);
        } else if (k >= n) {
            reverse(cs, 0, s.length() - 1);
            System.out.println(new String(cs));
        } else {
            char[] left = s.substring(0, k - 1).toCharArray();
            String right = s.substring(k - 1);
            if (n % 2 == k % 2) {
                reverse(left, 0, left.length - 1);
            }
            System.out.println(s.substring(k - 1) + new String(left));
        }
    }

    public static void reverse(char[] cs, int start, int end) {
        while (start < end) {
            char c = cs[start];
            cs[start] = cs[end];
            cs[end] = c;
            start++;
            end--;
        }
    }
}

class Solution14 {
    public static void main(String[] args) {
        System.out.println(new Solution14().baseNeg2(8));
    }

    public String baseNeg2(int n) {
        StringBuilder ans = new StringBuilder();
        while (n != 0) {
            if (n > 0) {
                int y = n % 2;
                n = n / -2;
                ans.append(y);
            } else {
                int y = n % 2;
                if (y == 0) {
                    ans.append(0);
                    n = n / -2;
                } else {
                    ans.append(1);
                    n = (n - 1) / -2;
                }
            }
        }
        return ans.reverse().toString();
    }
}

// leetcode 2462
class Solution15 {
    public static void main(String[] args) {
        totalCost(new int[]{31, 25, 72, 79, 74, 65, 84, 91, 18, 59, 27, 9, 81, 33, 17, 58}, 11, 2);
    }

    public static long totalCost(int[] costs, int k, int candidates) {
        PriorityQueue<int[]> left = new PriorityQueue<>((a, b) -> {
            if (a[1] == b[1]) return a[0] - b[0];
            return a[1] - b[1];
        });
        PriorityQueue<int[]> right = new PriorityQueue<>((a, b) -> {
            if (a[1] == b[1]) return a[0] - b[0];
            return a[1] - b[1];
        });
        int left_index = 0;
        for (; left_index < costs.length && left.size() < candidates; left_index++) {
            left.offer(new int[]{left_index, costs[left_index]});
        }
        int right_index = costs.length - 1;
        long min_cost = 0;
        for (; right_index >= left_index && right.size() < candidates; right_index--) {
            right.offer(new int[]{right_index, costs[right_index]});
        }
        int i = 0;
        for (; i < k && !left.isEmpty() && !right.isEmpty(); i++) {
            if (left.peek()[1] <= right.peek()[1]) {
                min_cost += left.poll()[1];
                if (left_index <= right_index) {
                    left.offer(new int[]{left_index, costs[left_index]});
                    left_index++;
                }
            } else {
                min_cost += right.poll()[1];
                if (right_index >= left_index) {
                    right.offer(new int[]{right_index, costs[right_index]});
                    right_index--;
                }
            }
        }
        if (i < k) {
            while (i < k && !left.isEmpty()) {
                min_cost += left.poll()[1];
                i++;
            }
            while (i < k && !right.isEmpty()) {
                min_cost += right.poll()[1];
                i++;
            }
        }
        return min_cost;
    }
}

class Solution16 {
    public int garbageCollection(String[] garbage, int[] travel) {
        int cost = 0;
        int[] preSum = new int[travel.length + 1];
        for (int i = 1; i < preSum.length; i++) {
            preSum[i] = preSum[i - 1] + travel[i - 1];
        }
        Map<Character, Integer> lastGar = new HashMap<>();
        for (int i = 0; i < garbage.length; i++) {
            cost += garbage[i].length();
            for (char c : garbage[i].toCharArray()) {
                lastGar.put(c, preSum[i]);
            }
        }
        return cost + lastGar.values().stream().reduce(0, Integer::sum);
    }
}


class Solution17 {
    // [2,4,6,8,10], profit = [10,20,30,40,50], worker = [4,5,6,7]
    public static void main(String[] args) {
        new Solution17().maxProfitAssignment(new int[]{2, 6, 8, 4, 10}, new int[]{10, 30, 40, 20, 50}, new int[]{4, 5, 6, 7});
    }

    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int len = difficulty.length;
        int[][] dp = new int[len][2];
        for (int i = 0; i < len; i++) {
            dp[i][0] = difficulty[i];
            dp[i][1] = profit[i];
        }
        Arrays.sort(dp, (a, b) -> {
            return a[0] - b[0];
        });
        Arrays.sort(worker);
        int nowMaxProfit = 0;
        int workerIndex = 0;
        int dpIndex = 0;
        int ans = 0;
        while (workerIndex < worker.length) {
            while (dpIndex < len) {
                if (dp[dpIndex][0] <= worker[workerIndex]) {
                    nowMaxProfit = Math.max(nowMaxProfit, dp[dpIndex][1]);
                    dpIndex++;
                } else {
                    break;
                }
            }
            ans += nowMaxProfit;
            workerIndex++;
        }
        return ans;
    }
}


class Solution18 {
    public String discountPrices(String sentence, int discount) {
        StringBuilder sb = new StringBuilder();
        char[] cs = sentence.toCharArray();
        int len = sentence.length();
        double dis = (double) discount / 100;
        for (int i = 0; i < len; ) {
            long val = 0;
            if (i > 0 && cs[i - 1] != ' ') {
                sb.append(cs[i]);
                i++;
            } else if (cs[i] == '$' && (i < len - 1 && cs[i + 1] != ' ' && cs[i + 1] != '0') && i != len - 1) {
                int j = i;
                j++;
                while (j < len && cs[j] >= '0' && cs[j] <= '9') {
                    val = val * 10 + cs[j] - '0';
                    j++;
                }
                if (j == len || cs[j] == ' ') {
                    String format = String.format("%.2f", ((double) val * (1 - dis) * 100) / 100);
                    sb.append('$').append(format);
                    if (j < len) {
                        sb.append(' ');
                    }
                } else {
                    sb.append(sentence.substring(i, j + 1));
                }
                i = j + 1;
            } else {
                sb.append(cs[i]);
                i++;
            }
        }
        return sb.toString();
    }

}


class Solution19 {
    public static void main(String[] args) {
        System.out.println(new Solution19().smallestString("aa"));
    }

    public String smallestString(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        int i = 0;
        while (i < n && chars[i] == 'a') {
            i++;
        }
        if (i == n) {
            chars[n - 1] = 'z';
            return new String(chars);
        }
        for (; i < n && chars[i] != 'a'; i++) {
            chars[i]--;
        }
        return new String(chars);


    }
}
