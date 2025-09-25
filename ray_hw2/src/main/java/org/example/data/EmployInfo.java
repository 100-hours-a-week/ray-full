package org.example.data;

public class EmployInfo {
    private String name;
    private int price;
    private int workTime;
    private String description;

    public EmployInfo(String name, int price, int workTime, String description){
        this.name = name;
        this.price = price;
        this.workTime = workTime;
        this.description = description;
    }

    public void printInfo(){
        System.out.println(name + "  (가격 : "+ price + ", " + description + ")");
    }

    public String getName() { return this.name; }

    public int getPrice() {
        return price;
    }
    public int getWorkTime() {
        return workTime;
    }
    public String getDescription() {
        return description;
    }


}
