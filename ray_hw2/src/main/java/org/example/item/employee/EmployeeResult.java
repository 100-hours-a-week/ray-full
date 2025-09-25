package org.example.item.employee;

public class EmployeeResult {
    private String name;
    private String result;
    private int total;

    public EmployeeResult(String name, String result, int total){
        this.name = name;
        this.result = result;
        this.total = total;
    }

    public String getName() {
        return name;
    }

    public String getResult() {
        return result;
    }

    public int getTotal() {
        return total;
    }
}
