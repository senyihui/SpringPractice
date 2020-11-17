package com.springinaction.knights.impls;

import com.springinaction.knights.interfaces.Quest;

public class RescueDamselQuest implements Quest {
    public void embark() {
        System.out.println("Rescue Damsel Quest");
    }
}
