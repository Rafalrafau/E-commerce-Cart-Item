package com.example.onetomanywebservice.service;

import com.example.onetomanywebservice.exception.ItemNotFoundException;
import com.example.onetomanywebservice.model.Item;
import com.example.onetomanywebservice.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;


    public Item addItem(Item item){
        return itemRepository.save(item);
    }

    public List<Item> getItems(){
        return StreamSupport.stream(itemRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Item getItem(Long id){
        return itemRepository.findById(id).orElseThrow(()-> new ItemNotFoundException(id));
    }

    public Item deleteItem(Long id){
        Item item = getItem(id);
        itemRepository.delete(item);
        return item;
    }

    @Transactional
    public Item editItem(Long id, Item item){
        Item itemToEdit = getItem(id);
        itemToEdit.setSerialNumber(item.getSerialNumber());
        return itemToEdit;
    }

}
