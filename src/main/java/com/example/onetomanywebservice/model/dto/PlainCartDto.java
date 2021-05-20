package com.example.onetomanywebservice.model.dto;

import com.example.onetomanywebservice.model.Cart;
import lombok.Data;

@Data
public class PlainCartDto {
    private Long id;
    private String serialNumber;

    public static PlainCartDto from(Cart cart){
        PlainCartDto plainCartDto = new PlainCartDto();
        plainCartDto.setId(cart.getId());
        plainCartDto.setSerialNumber(cart.getName());
        return plainCartDto;
    }
}
