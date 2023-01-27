package com.example.adminStudy.repository;

import com.example.adminStudy.AdminStudyApplicationTests;
import com.example.adminStudy.model.entity.Item;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

public class ItemRepositoryTest extends AdminStudyApplicationTests {
    @Autowired
    private ItemRepository itemRepository;
    @Test
    public void create(){
        Item item = new Item();
        item.setStatus("UNREGISTERED");
        item.setName("HP laptop");
        item.setTitle("SPECTURE 360");
        item.setPrice(30000);
        item.setContent("2022년 출시 SPECTURE 360");
        item.setBrandName("HP");
        item.setCreatedAt(LocalDateTime.now());
        item.setCreatedBy("Partner01");
        //item.setPartnerId(1L); //25. 연관관계 - 디비 insert 외래키 id 주석처리

        Item newItem = itemRepository.save(item);
        Assert.assertNotNull(newItem);
    }
    @Test
    public void read(){
        Long id = 1L;
        Optional<Item> item = itemRepository.findById(id);
        Assert.assertTrue(item.isPresent());
//        item.ifPresent(i->{System.out.println(i);}); 를 위의 Assert 코드로 변경해줌
    }
}
