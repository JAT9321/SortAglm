package org.example.SheJiMoShi.命令模式;

public class Client {
    public static void main(String[] args) {

        Invoker invoker = new Invoker();

        Light light = new Light();
        Tv tv = new Tv();

        Command lightOn = new LightOnCommand(light);
        Command lightOff = new LightOffCommand(light);
        Command tvOn = new TelevisionOnCommand(tv);
        Command tvOff = new TelevisionOffCommand(tv);

        invoker.setCommand(0, lightOn, lightOff);
        invoker.setCommand(1, tvOn, tvOff);

        // Turn on the light and tv
        invoker.pressOnButton(0);
        invoker.pressOnButton(1);

        // Turn off the light and tv
        invoker.pressOffButton(0);
        invoker.pressOffButton(1);
    }
}
