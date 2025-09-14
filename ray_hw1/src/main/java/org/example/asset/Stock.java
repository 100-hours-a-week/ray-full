package org.example.asset;

import org.example.asset.Asset;

import java.util.Timer;
import java.util.TimerTask;

public class Stock extends Asset {
    public Stock(String n, int p, int c) {
        this.name = name;
        this.price = price;
        this.change = c;
        Timer scheduler = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (Math.random() <= 0.55) {
                    price += c;
                } else {
                    price -= c;
                }
            }
        };
        // 1초 후 3초마다 가격 변경
        scheduler.scheduleAtFixedRate(task, 1000, 3000);
    }
}
