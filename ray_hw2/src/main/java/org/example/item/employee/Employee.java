package org.example.item.employee;

import org.example.Me;
import org.example.data.EmployInfo;
import org.example.item.Item;

import java.util.Random;

public class Employee extends Item implements Runnable{
    private int price;
    private int workTime;
    private Me me;
    private Random random = new Random();

    @Override
    public void run() {
        try {
            Thread.sleep(workTime * 1000);
        } catch (InterruptedException e) {
            return;
        }
        int randomValue = random.nextInt(100);
        int total;
        String result;

        if (randomValue < 10) {
            total = 0;
            result = "돈 벌어오기에 실패했습니다. 0원을 받으세요 ^^";
        } else if (randomValue < 30) {
            total = price / 2;
            result = "에게게.." + total + "원 밖에 벌어오지 못했군요.";
        } else if (randomValue < 80) {
            total = (int)(price * 1.5);
            result = "근면성실하게 일해서 돈을 벌었습니다! " + total + "원을 받으세요.";
        } else if (randomValue < 95) {
            total = price * 2;
            result = "돈 벌기 대성공! " + total + "원을 받으세요.";
        } else {
            total = price * 3;
            result = "은행털기에 성공했습니다." + total + "원을 받으세요!";
        }
        me.addEmployeeResult(name, result, total);
    }

    public Employee(String name, int price, int workTime, Me me) {
        this.name = name;
        this.price = price;
        this.workTime = workTime;
        this.me = me;
    }
    public Employee(EmployInfo employInfo, Me me) {
        this.name = employInfo.getName();
        this.price = employInfo.getPrice();
        this.workTime = employInfo.getWorkTime();
        this.me = me;
    }

    public void printInfo(){
        System.out.println(name+ " (판매가 : " + price+ ", 근무시간 : " + workTime + ")");
    }
}
