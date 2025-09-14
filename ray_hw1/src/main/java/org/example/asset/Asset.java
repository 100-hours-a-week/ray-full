package org.example.asset;

import org.example.Item;

public class Asset extends Item {
    public int change;

    public void printInfo(){
        System.out.println(name+ " (판매가 : " + price+ ")");
    }
}
