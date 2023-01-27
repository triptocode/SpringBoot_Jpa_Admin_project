package com.example.adminStudy.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data // 기본생성자와 변수에 대해 get set 메소드 자동완성
@AllArgsConstructor // 모든매개변수가 들어가는 생성자 자동완성
@NoArgsConstructor
@Entity // 하단의 OrderDetail라는 객체(camel_case)에 맞는 table명(snake_case)에 연결, 즉 order_detail
@ToString(exclude = {"user", "orderDetailList"}) // 24. 연관관계설정 -1
public class OrderGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String status;
    private String orderType;
    private String revAddress;
    private String RevName;
    private String paymentType;
    private BigDecimal totalPrice;
    private Integer totalQuantity;
    private LocalDateTime orderAt;
    private LocalDateTime arrivalDate;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
    //private Long userId; // 23. 테이블테스트: 외래키 추가 , OrderGroupRepositoryTest.java의 orderGroup.setUserId(1L);  와 연동되는 코드 , // 24. 연관관계설정 -1 에서삭제

    @ManyToOne // 24. 연관관계설정 (  order_group 테이블 : user 테이블= N : 1 )
    private User user; // 24. 연관관계설정 : Long userId 삭제후 User user 객체로 넣음
    @OneToMany(fetch= FetchType.LAZY, mappedBy="orderGroup") //  24. 연관관계설정 -1 ( order_group 테이블: order_detail 테이블 = 1: N)
    private List<OrderDetail> orderDetailList;
}