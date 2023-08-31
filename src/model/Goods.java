package model;

import java.io.Serializable;

class Goods implements Serializable {
    private String name;
    private int amount;
    private double dropFrequency;

    Goods(String name, int amount, double dropFrequency){
        this.name = name;
        this.amount = amount;
        this.dropFrequency = dropFrequency;
    }
    String getName(){
        return this.name;
    }
    int getAmount(){
        return  this.amount;
    }
    double getDropFrequency() {
        return this.dropFrequency;
    }
    void changeDropFrequency(double dropFrequency) {
        this.dropFrequency = dropFrequency;
    }
    void changeGoodsAmount(int amount){
        this.amount = amount;
    }
    void changeAmount(){
        this.amount-=1;
    }
}
