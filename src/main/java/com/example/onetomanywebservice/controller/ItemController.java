package com.example.onetomanywebservice.controller;

import com.example.onetomanywebservice.model.Item;
import com.example.onetomanywebservice.model.dto.ItemDto;
import com.example.onetomanywebservice.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemService itemService;


    @PostMapping
    public ResponseEntity<ItemDto> addIem(@RequestBody final ItemDto itemDto){
        Item item = itemService.addItem(Item.from(itemDto));
        return new ResponseEntity<>(ItemDto.from(item), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ItemDto>> getItems(){
        List<Item> items = itemService.getItems();
        List<ItemDto> itemsDto = items.stream().map(ItemDto::from).collect(Collectors.toList());
        return new ResponseEntity<>(itemsDto, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<ItemDto>getItem(@PathVariable final Long id){
        Item item = itemService.getItem(id);
        return new ResponseEntity<>(ItemDto.from(item),HttpStatus.OK);
    }


    @DeleteMapping(value = "{id}")
    public ResponseEntity<ItemDto> deleteItem(@PathVariable final Long id){
        Item item = itemService.deleteItem(id);
        return new ResponseEntity<>(ItemDto.from(item),HttpStatus.OK);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<ItemDto> editItem(@PathVariable final Long id,
                                            @RequestBody final ItemDto itemDto){
        Item editedItem = itemService.editItem(id,Item.from(itemDto));
        return new ResponseEntity<>(ItemDto.from(editedItem),HttpStatus.OK);
    }

}
