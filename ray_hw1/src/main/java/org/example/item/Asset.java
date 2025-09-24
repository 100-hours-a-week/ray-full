package org.example.item;

public class Asset extends Item {
    protected int change;

    public void printInfo(){
        System.out.println(name+ " (판매가 : " + price+ ")");
    }
}
