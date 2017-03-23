package ru.otus.lottery;

import java.util.*;

/**
 * Created by tully.
 */
class LotteryMachine {
    private final List<String> emails;
    private final int winnersCount;
    private long seed = 0;

    LotteryMachine(List<String> emails, int winnersCount) {
        this.emails = emails;
        this.winnersCount = Math.min(winnersCount, emails.size());
    }

    List<String> draw() {
        System.out.println("Draw for seed: " + seed);
        Random rnd = new Random(seed);
        Set<String> winners = new HashSet<>();
        while (winners.size() < winnersCount) {
            int index = rnd.nextInt(emails.size());
            System.out.println("Ball: " + index);
            winners.add(emails.get(index));
        }
        return new ArrayList<>(winners);
    }

    LotteryMachine setSeed(long seed) {
        this.seed = seed;
        return this;
    }

    LotteryMachine setSeed(String str) {
        this.seed = str.hashCode();
        return this;
    }
}
