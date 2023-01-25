package com.example.adminStudy.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data // 기본생성자와 변수에 대해 get set 메소드 자동완성
@AllArgsConstructor // 모든매개변수가 들어가는 생성자 자동완성
@NoArgsConstructor
@Entity // 하단의 OrderDetail라는 객체(camel_case)에 맞는 table명(snake_case)에 연결, 즉 order_detail
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

}