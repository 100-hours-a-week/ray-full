package org.example.item.asset;

import org.example.item.Item;

public class Asset extends Item {
    protected int change;

    public void printInfo(){
        System.out.println(name+ " (판매가 : " + price+ ")");
    }
}
