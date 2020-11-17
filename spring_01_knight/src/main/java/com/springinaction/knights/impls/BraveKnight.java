package com.springinaction.knights.impls;

import com.springinaction.knights.interfaces.Knight;
import com.springinaction.knights.interfaces.Quest;

public class BraveKnight implements Knight {

    private Quest quest;

    public BraveKnight(Quest quest) {
        this.quest = quest;
    }

    public void embarkOnQuest() {
        quest.embark();
    }
}
