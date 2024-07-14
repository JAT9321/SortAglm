package org.example;

import java.util.Scanner;

/**
 * @author : JAT
 * @version : 1.0
 * @email : zgt9321@qq.com
 * @since : 2024/4/28
 **/
public class test {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int a = in.nextInt();
        int b = in.nextInt();
        int n = in.nextInt();
        for(int i = 0; i < n; i++){
            if(a == 0 || b == 0)break;
            int Azhao = in.nextInt();
            int Bzhao = in.nextInt();
            if(Azhao == Bzhao){
                a--;
                b--;
                continue;
            }
            if(Azhao == 0){
                if(Bzhao == 1){
                    a -= 2;
                }else{
                    b--;
                }
            }else if(Azhao == 1){
                if(Bzhao == 0){
                    b -= 2;
                }else{
                    a -= 3;
                }
            }else{
                if(Bzhao == 0){
                    a--;
                }else{
                    b -= 3;
                }
            }
        }
        if(a == 0 && b != 0){
            System.out.println("B WIN");
        }else if(b == 0 && a != 0){
            System.out.println("A WIN");
        }else if(a != 0 && b != 0){
            System.out.println("WIN WIN");
        }else{
            System.out.println("DEAD DEAD");
        }
    }
}
