package com.company.design.decorator;

public class Audi implements ICar{

    private int cost;


    public Audi(){ }

    public Audi(int cost){
        this.cost = cost;
    }

    @Override
    public int getPrice() {
        return cost;
    }

    @Override
    public void showPrice() {
        System.out.println("audi 의 가격은 "+ cost+" 원 입니다.");
    }
}
