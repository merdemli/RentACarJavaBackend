package com.etiya.renACar.core.utilities.results;

public class Result {
    private boolean success;
    private String message;


    public Result(boolean success) {
        this.success = success;
    }

    public Result(boolean success, String message) {
        //this.success = success;
        this(success);                         //success'i tek parametreli overload'da çalıştırır.Yani bu constructor
        this.message = message;      // çalıştırıldıgında yukarıdaki de otomatik çalışacak, hem success set edilecek
                                    //hem de message
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

//    public void setSuccess(boolean success) {
//        this.success = success;       set etme işlemi sadece constructor'dan olsun
//    }
}
