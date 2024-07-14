package org.example.SheJiMoShi.命令模式;

public class Tv {
    public void on() {
        System.out.println("Television is on.");
    }

    public void off() {
        System.out.println("Television is off.");
    }
}


// Television On Command
class TelevisionOnCommand implements Command {
    private Tv television;

    public TelevisionOnCommand(Tv television) {
        this.television = television;
    }

    public void execute() {
        television.on();
    }
}

// Television Off Command
class TelevisionOffCommand implements Command {
    private Tv television;

    public TelevisionOffCommand(Tv television) {
        this.television = television;
    }

    public void execute() {
        television.off();
    }
}
