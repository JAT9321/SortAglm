package org.example.acmCoder.SYZG;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @author : JAT
 * @version : 1.0
 * @email : zgt9321@qq.com
 * @since : 2024/5/11
 **/
public class Main {

    public static void main(String[] args) {

        int[][] numbers = new int[5][];
        numbers[0] = new int[3];
        numbers[1] = new int[5];
        for (int[] number : numbers) {
            for (int i : number) {
                System.out.print(i + " ");
            }
            System.out.println();
        }


    }

}



