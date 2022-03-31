package com.etiya.renACar.core.utilities.results;

public class ErrorResult extends Result{

    public ErrorResult() {               //success demek bnm için true demek
        super(false);
    }

    public ErrorResult(String message) {
        super(false, message);
    }
}

