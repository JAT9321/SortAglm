package org.example.单例模式;

/**
 * @author : JAT
 * @version : 1.0
 * @email : zgt9321@qq.com
 * @since : 2024/5/30
 **/
public class lhs {
}

class Singleton3 {
    // 私有构造函数
    private Singleton3() {
    }

    // 最后解释volatile关键字
    private volatile static Singleton3 obj;

    public static Singleton3 getInstance() {
        // 已有实例则直接返回，不走锁
        if (obj == null) {
            // 仅在没生成实例时加锁控制，使并发访问串行化
            synchronized (Singleton3.class) {
                // 多个线程会按序执行到此处，需要再次检查是否已实例化
                if (obj == null) {
                    obj = new Singleton3();
                }
            }
        }
        return obj;
    }
}