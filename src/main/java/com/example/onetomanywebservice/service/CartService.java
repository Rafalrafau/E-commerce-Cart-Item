package com.example.onetomanywebservice.service;

import com.example.onetomanywebservice.exception.CartNotFoundException;
import com.example.onetomanywebservice.exception.ItemIsAlreadyAssignedException;
import com.example.onetomanywebservice.model.Cart;
import com.example.onetomanywebservice.model.Item;
import com.example.onetomanywebservice.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ItemService itemService;

    public Cart addCart(Cart cart){
        return cartRepository.save(cart);
    }

    public List<Cart> getCarts(){
        return StreamSupport.stream(cartRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Cart getCart(Long id){
        return cartRepository.findById(id).orElseThrow(()->
                new CartNotFoundException(id));
    }

    public Cart deleteCart(Long id){
        Cart cart = getCart(id);
        cartRepository.delete(cart);
        return cart;
    }

    @Transactional
    public Cart editCart(Long id, Cart theCart){
        Cart cartToEdit = getCart(id);
        cartToEdit.setName(theCart.getName());
        return cartToEdit;
    }


    // Special method for adding item to the cart

    @Transactional
    public Cart addItemToCart(Long cartId, Long itemId){
        Cart cart = getCart(cartId);
        Item item = itemService.getItem(itemId);
        if(Objects.nonNull(item.getCart())){
            throw new ItemIsAlreadyAssignedException(itemId, item.getCart().getId());
        }
        cart.addItem(item);

        return cart;
    }

    @Transactional
    public Cart removeItemFromCart(Long cartId, Long itemId){
        Cart cart = getCart(cartId);
        Item item = itemService.getItem(itemId);
        cart.removeItem(item);

        return cart;
    }
}
