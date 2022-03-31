package com.etiya.renACar.core.utilities.results;

public class SuccessResult extends Result {
    public SuccessResult() {               //success demek bnm için true demek
        super(true);
    }

    public SuccessResult(String message) {
        super(true, message);

    }
}





