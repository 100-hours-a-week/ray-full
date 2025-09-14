package org.example.asset;

import org.example.asset.Asset;

import java.util.Timer;
import java.util.TimerTask;

public class Building extends Asset {
    public Building(String n, int p, int c){
        this.name = n;
        this.price = p;
        this.change = c;
        Timer scheduler = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                price += c;
            }
        };
        // 1초 후 3초마다 가격 상승
        scheduler.scheduleAtFixedRate(task, 1000, 3000);
    }
}
