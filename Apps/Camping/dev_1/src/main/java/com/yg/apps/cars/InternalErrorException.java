package com.yg.apps.cars;

import java.io.IOException;

public class InternalErrorException extends IOException {

    public InternalErrorException(String errorMessage) {
        super("Internal error: " + errorMessage);
    }


//    public InternalErrorException(String errorMessage, Throwable err) {
//        super("Internal error: " + errorMessage, err);
//    }
}
