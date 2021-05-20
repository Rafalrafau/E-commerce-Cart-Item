package com.example.onetomanywebservice.exception;

import java.text.MessageFormat;

public class ItemNotFoundException extends RuntimeException{


    public ItemNotFoundException(Long id){
        super(MessageFormat.format("Could not fid item with id: {0}",id ));
    }

}
