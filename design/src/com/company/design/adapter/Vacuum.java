package com.company.design.adapter;

public class Vacuum implements Electronic220V{
    @Override
    public void connect() {
        System.out.println("청소기 220V on");
    }
}
