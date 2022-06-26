package com.company.design.strategy;

public class Encoder {

    private EncodingStrategy encodingStrategy;

    public String getMessage(String message){

        return this.encodingStrategy.encode(message);
    }

    public EncodingStrategy getEncodingStrategy() {
        return encodingStrategy;
    }

    public void setEncodingStrategy(EncodingStrategy encodingStrategy) {
        this.encodingStrategy = encodingStrategy;
    }
}
