package com.nickzim.enums;

import java.nio.charset.StandardCharsets;

public enum Charset {

    UTF_8(StandardCharsets.UTF_8.toString()),
    WINDOWS_1251("Windows-1251");

    private String charset;

    Charset(String charset){
        this.charset = charset;
    }

    public String getCharset() {
        return charset;
    }
}
