package org.example.data;

public class AssetInfo {
    public String name;
    public int price;
    public int change;
    public AssetInfo(String name, int price, int change){
        this.name = name;
        this.price = price;
        this.change = change;
    }
    public void printInfo(){
        System.out.println(name + "  (가격 : "+ price + ", 3초당 가격 변경액 : "+ change+ ")");
    }
}
