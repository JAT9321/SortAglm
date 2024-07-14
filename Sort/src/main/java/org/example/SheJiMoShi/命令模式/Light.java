package org.example.SheJiMoShi.命令模式;

public class Light {
    public void on() {
        System.out.println("Light is on.");
    }

    public void off() {
        System.out.println("Light is off.");
    }

}

class LightOnCommand implements Command {

    // 接受者
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }
}

class LightOffCommand implements Command {

    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        this.light.off();
    }
}
