package com.example.adminStudy.repository;


import com.example.adminStudy.AdminStudyApplicationTests;
import com.example.adminStudy.model.entity.Item;
import com.example.adminStudy.model.entity.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public class UserRepositoryTest extends AdminStudyApplicationTests {

    //  @Autowired  를 안붙이면
    // private UserRepository userRepository = new UserRepository; 를 통해서 객체를 생성하고
    //  public void create(){
    //          userRepository.save()
    //          userRepository.count()
    //    }
    // 이런식으로 메소드를 활욯하는 형태로 길어진다.
    @Autowired  // DI = Dependency Injection ( 위와같은 객체를 직접만들지않고 스프링에서 주입하도록 하는 디자인패턴)
    private UserRepository userRepository;

    @Test
    public void create(){
        String account = "TestUser03";
        String password = "Test03";
        String status = "REGISTERED";
        String email ="Test03@gmail.com";
        String phoneNumber = "010-3333-3333";
        LocalDateTime registeredAt = LocalDateTime.now();
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";

        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        user.setStatus(status);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setRegisteredAt(registeredAt);
        user.setCreatedAt(createdAt);
        user.setCreatedBy(createdBy);

        User newUser = userRepository.save(user);
        Assert.assertNotNull(newUser);
    }
    @Test
    @Transactional
    public void read(){
        // UserRepository.java에서 User findFirstByPhoneNumberOrderByIdDesc(String phoneNumber); 추가후 --> 아래 두줄작성
        User user = userRepository.findFirstByPhoneNumberOrderByIdDesc("010-1111-1111");

        // 24. 연관관계설정 : 위의 폰번호를 가진 해당 유저한명에 어떤 장바구니가 있는지 읽어오기
        if(user!=null) {
            user.getOrderGroupList().stream().forEach(orderGroup -> {
                System.out.println("----------------주문묶음 orderGroup-------------------");
                System.out.println(" 수령인: " + orderGroup.getRevName());
                System.out.println(" 수령지: " + orderGroup.getRevAddress());
                System.out.println(" 총금액: " + orderGroup.getTotalPrice());
                System.out.println(" 총수량: " + orderGroup.getTotalQuantity());

                    System.out.println("----------------주문상세 orderDetail-------------------");
                    orderGroup.getOrderDetailList().forEach(orderDetail -> {
                        System.out.println("주문 상품: " + orderDetail.getItem().getName()); // 25. 연관관계 추가 -  partner N : 1 item
                        System.out.println("고객센터 번호: " + orderDetail.getItem().getPartner().getCallCenter()); //25. 연관관계 추가 -  partner N : 1 item
                        System.out.println("주문의 상태: " + orderDetail.getStatus());
                        System.out.println("도착예정 일자: " + orderDetail.getArrivalDate());
                    });
            });
        }
         Assert.assertNotNull(user);
    }
    @Test
    public void update() {
        Optional<User> user = userRepository.findById(2L);
        user.ifPresent(selectUser -> {
            selectUser.setAccount("TestUser02");
            selectUser.setPassword("TestUser02");
            selectUser.setEmail("Test02@gmail.com");
            selectUser.setPhoneNumber("010-2222-2222");
            selectUser.setUpdatedAt(LocalDateTime.now());
            selectUser.setUpdatedBy("update method()");

            userRepository.save(selectUser);
        });
    }
    @Test
    public void delete(){
        Optional<User> user = userRepository.findById(2L);  // 조회  , L은 Id 타입 Long
        Assert.assertTrue(user.isPresent());  // 현재 반드시 값이 있는지여부가  true 여야 통과되게 함
        user.ifPresent(selectUser-> {userRepository.delete(selectUser);} );// 존재한다면, delete메소드로 삭제
        Optional<User> deleteUser = userRepository.findById(2L);  // 다시 해당 아이디로 조회해서 deleteUser에 넣고 , 아래 if else 문으로 삭제유무 체크
        Assert.assertFalse(deleteUser.isPresent());
//        if(deleteUser.isPresent()){
//            System.out.println("데이터 존재 : " + deleteUser.get());
//        }else{
//            System.out.println( "데이터 삭제로 데이터 없음");
//        }
    }
}