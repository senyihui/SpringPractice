package com.springinaction.knights.config;

import com.springinaction.knights.impls.BraveKnight;
import com.springinaction.knights.impls.SlayDragonQuest;
import com.springinaction.knights.interfaces.Knight;
import com.springinaction.knights.interfaces.Quest;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

@Configurable
public class KnightConfig {
    @Bean
    public Knight knight() {
        return new BraveKnight(quest());
    }

    @Bean
    public Quest quest() {
        return new SlayDragonQuest(System.out);
    }
}
