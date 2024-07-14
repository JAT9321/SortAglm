package org.example.单例模式;

/**
 * @author : JAT
 * @version : 1.0
 * @email : zgt9321@qq.com
 * @since : 2024/5/30
 **/
public class ehs {
}


class Singleton {
    // 私有构造函数
    private Singleton() {
    }

    // 类加载时就实例化对象
    private static Singleton obj = new Singleton();

    public static Singleton getInstance() {
        return obj;
    }
}

class Singleton2 {
    // 私有构造函数
    private Singleton2() {
    }

    // 静态内部类
    private static class SingletonHolder {
        private static Singleton2 instance = new Singleton2();
    }

    public static Singleton2 getInstance() {
        return SingletonHolder.instance;
    }
}