package org.example.item;

import java.util.Timer;
import java.util.TimerTask;

public class Stock extends Asset {
    public Stock(String name, int initialPrice, int change) {
        this.name = name;
        this.price = initialPrice;
        this.change = change;
        Timer scheduler = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (Math.random() <= 0.55) {
                    price += change;
                } else {
                    price -= change;
                }
            }
        };
        // 1초 후 3초마다 가격 변경
        scheduler.scheduleAtFixedRate(task, 1000, 3000);
    }
}
