package com.ygsoft.apps.enums;



public enum SizesEnum {
    // Long
    TF_LO (110),

    // Short
    TF_SH (50),

    // Very long
    TF_VL (200),
    ;

    private final int size;


    public int getSize() {
        return this.size;
    }


    SizesEnum(int size) {
        this.size = size;
    }
}
