package org.example;

public class TestSuper {

    public static void main(String[] args) {
        Father son = new Son("test");
        System.out.println(son.getJiao());
        son.jiao = "father123";
        System.out.println(son.getJiao());
        Son son1 = (Son) son;
        son1.jiao = "son123";
        System.out.println(son1.getJiao());

    }

}


class Son extends Father {

    public String jiao;

    public Son(String jiao) {
        super("jiao father");
        this.jiao = jiao;
    }

    public String getJiao() {
        return jiao;
    }

    public void setJiao(String jiao) {
        this.jiao = jiao;
    }
}


class Father {

    public String jiao;

    public Father(String jiao) {
        this.jiao = jiao;
    }

    public String getJiao() {
//        jiao = "123";
        return jiao;
    }

    public void setJiao(String jiao) {
        this.jiao = jiao;
    }
}
