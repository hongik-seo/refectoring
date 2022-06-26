package com.company.design.decorator;

public class AudiDecorator implements ICar {

    protected ICar audi;

    protected String modelName;

    protected int modelCost;

    public AudiDecorator(ICar audi , String  modelName, int modelCost){

        this.audi = audi;
        this.modelName = modelName;
        this.modelCost =audi.getPrice() + modelCost;
    }

    @Override
    public int getPrice() {
        return audi.getPrice() + modelCost;
    }

    @Override
    public void showPrice() {

        System.out.println(modelName + " 의 가격은 "+ modelCost + " 입니다.");

    }
}
