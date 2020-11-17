package com.springinaction.knights.impls;

import com.springinaction.knights.interfaces.Quest;

import java.io.PrintStream;


public class SlayDragonQuest implements Quest {
    private PrintStream stream;

    public SlayDragonQuest(PrintStream stream) {
        this.stream = stream;
    }

    public void embark() {
        stream.println("Slay the dragon!");
    }
}
