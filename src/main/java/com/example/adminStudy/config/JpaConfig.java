package com.example.adminStudy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration // 설정파일에 관한것이 들어간다고 표시
@EnableJpaAuditing // jpa에 대해서 감시를 활성화 시키겠다!
public class JpaConfig {
}
