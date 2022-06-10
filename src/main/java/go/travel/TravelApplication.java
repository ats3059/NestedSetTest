package go.travel;

import go.travel.global.aop.TestAspect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
//프록시 등록
@Import(TestAspect.class)
public class TravelApplication {

	public static void main(String[] args) {
		SpringApplication.run(TravelApplication.class, args);
	}

	//추가하려면 추가 가능.. SpringSecurity ..??
//	@Bean
//	public AuditorAware<String> auditorProvider() {
//		return ()->
//	}
}
