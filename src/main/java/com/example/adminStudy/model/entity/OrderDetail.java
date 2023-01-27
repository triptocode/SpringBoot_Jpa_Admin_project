package com.example.adminStudy.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data // 기본생성자와 변수에 대해 get set 메소드 자동완성
@AllArgsConstructor // 모든매개변수가 들어가는 생성자 자동완성
@NoArgsConstructor
@Entity // 하단의 OrderDetail라는 객체(camel_case)에 맞는 table명(snake_case)에 연결, 즉 order_detail
@ToString(exclude = {"orderGroup","item"})
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String status;
    private LocalDateTime arrivalDate;
    private Integer quantity;
    private BigDecimal totalPrice;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;

//    private  Long itemId; // 22. order_detail 테이블테스트 외래키추가 // 25. 연관관계 - 주석처리
    @ManyToOne // 25. 연관관계 - order_detail N : 1 item
    private Item item;
//    private Long orderGroupId; // 22. order_detail 테이블테스트 외래키추가
    @ManyToOne // 24. 연관관계 -  order_detail N : 1 order_group
    private OrderGroup orderGroup; // 24. 연관관계설정 ( 연관관계설정하는 OrderGroup.java의 mappedBy = "orderGroup" 글자 일치해야함)

}

