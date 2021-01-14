package com.shamanthka.rl.service;

import com.shamanthka.rl.model.Item;
import com.shamanthka.rl.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    /*public ItemService(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }*/

    public Item save(Item item){
        Item newItem = itemRepository.saveAndFlush(item);
        return newItem;
    }

    public List<Item> findAll(){
        List<Item> items = itemRepository.findAll();
        return items;
    }
}
