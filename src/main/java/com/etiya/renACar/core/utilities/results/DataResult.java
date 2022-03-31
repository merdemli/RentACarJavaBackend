package com.etiya.renACar.core.utilities.results;

public class DataResult <T> extends Result{

    private T data;

    public DataResult(T data, boolean success) {
        super(success);
        this.data=data;
    }

    public DataResult(T data, boolean success,String message) {
        super(success,message);
        this.data=data;
    }
//set construtor'dan yapılır
    public T getData() {
        return data;
    }
}
