package org.example.item.asset;

import org.example.item.asset.Asset;

import java.util.Timer;
import java.util.TimerTask;

public class Stock extends Asset {
    public Stock(int change) {
        this.name = name;
        this.price = price;
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
