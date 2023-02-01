package com.example.adminStudy.repository;

import com.example.adminStudy.AdminStudyApplicationTests;
import com.example.adminStudy.model.entity.AdminUser;
import com.example.adminStudy.model.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

public class AdminUserRepositoryTest extends AdminStudyApplicationTests {

    @Autowired
    private AdminUserRepository adminUserRepository;

    @Test
    public void create(){
//        not null 필수값만 아래 테스트를 위해 작성 ( null 허용은 작성하지 않음)
        AdminUser adminUser = new AdminUser();
        adminUser.setAccount("AdminUser03");
        adminUser.setPassword("AdminUser03");
        adminUser.setStatus("REGISTERED");
        adminUser.setRole("PARTNER");
//      26. 하단의 반복되는 2개 필드는 @등록및 auditor 사용으로 자동화처리 ( 주석처리 = 작성하지 않아도 자동으로 insert되게 처리하여 편해짐)
//        adminUser.setCreatedAt(LocalDateTime.now());
//        adminUser.setCreatedBy("AdminServer");

        AdminUser newAdminUser = adminUserRepository.save(adminUser);
        Assert.assertNotNull(newAdminUser);
    }

    @Test
    public void update() {
        Optional<AdminUser> adminUser = adminUserRepository.findById(2L);
        adminUser.ifPresent(selectUser -> {
            selectUser.setAccount("AdminUser02");
            selectUser.setPassword("AdminUser02");
//      26.  create()에서는 작성하지 않아도 해당필드의 데이터가 자동 insert 되었지만 그와 다르게 update()에서는 CreatedAt 작성안하면 해당필드 업데이트안됨
            selectUser.setCreatedAt(LocalDateTime.now()); // 이 한줄의 코드를 생략하면 createdAT 업데이트 안됨!

            adminUserRepository.save(selectUser);
        });
    }
}
