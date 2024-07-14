package org.example.SheJiMoShi.命令模式;

public class Invoker {

    private Command[] onCommands;
    private Command[] offCommands;

    public Invoker() {
        this.onCommands = new Command[2];
        this.offCommands = new Command[2];
    }

    public void setCommand(int slot, Command onCommand, Command offCommand) {
        this.onCommands[slot] = onCommand;
        this.offCommands[slot] = offCommand;
    }

    public void pressOnButton(int slot) {
        onCommands[slot].execute();
    }

    public void pressOffButton(int slot) {
        offCommands[slot].execute();
    }
}
