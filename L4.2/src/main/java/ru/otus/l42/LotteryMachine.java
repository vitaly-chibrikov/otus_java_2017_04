package ru.otus.l42;

import java.util.*;

/**
 * Created by tully.
 */
class LotteryMachine {
    private final int winnersCount;
    private long seed = 0;

    LotteryMachine(int winnersCount) {
        this.winnersCount = winnersCount;
    }

    List<String> draw(List<String> emails) {
        System.out.println("Draw for the seed: " + seed);
        Random rnd = new Random(seed);
        Set<String> winners = new HashSet<>();
        while (winners.size() < Math.min(winnersCount, emails.size())) {
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

    public long getSeed() {
        return seed;
    }

    void dispose() {
        System.out.println("Disposed");
    }
}
