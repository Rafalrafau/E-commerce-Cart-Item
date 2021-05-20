package com.example.onetomanywebservice.exception;

import java.text.MessageFormat;

public class CartNotFoundException extends RuntimeException {

    public CartNotFoundException(Long id) {
        super(MessageFormat.format("Could not fid cart with id: {0}",id ));
    }
}
