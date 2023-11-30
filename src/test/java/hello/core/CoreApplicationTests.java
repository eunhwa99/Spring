package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class CoreApplicationTests {
    AnnotationConfigApplicationContext ac = new
            AnnotationConfigApplicationContext(AppConfig.class);


    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName() {
        OrderService orderService = (OrderService) ac.getBean("orderService");
        assertThat(orderService).isInstanceOf(OrderService.class);

    }

    @Test
    @DisplayName("이름 없이 타입만으로 조회")
    void findBeanByType() {
        OrderService memberService = ac.getBean(OrderService.class);
        assertThat(memberService).isInstanceOf(OrderServiceImpl.class);
    }

    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanByConcreteType() {
        OrderServiceImpl memberService = ac.getBean("orderService",
                OrderServiceImpl.class);
        assertThat(memberService).isInstanceOf(OrderServiceImpl.class);
    }

    @Test
    @DisplayName("등록되지 않은 빈 이름으로 조회 시 에러를 내 뱉는다.")
    void findBeanByNameX() {
        assertThrows(NoSuchBeanDefinitionException.class, () ->
                ac.getBean("noBean", OrderService.class));
    }



	@Configuration
	static class TestConfig{
		@Bean
		public MemberRepository memberRepository1() {
			return new MemoryMemberRepository();
		}
		@Bean
		public MemberRepository memberRepository2() {
			return new MemoryMemberRepository();
		}

	}

	@Test
	@DisplayName("타입으로 조회시 같은 타입을 가진 빈이 둘 이상이면, 에러를 내 뱉는다.")
	void findBeanByTypeDulicated(){
		assertThrows(NoUniqueBeanDefinitionException.class, () ->
				ac.getBean(MemberRepository.class));
	}

	@Test
	@DisplayName("타입으로 조회시 같은 타입이 둘 이상 있으면, 빈 이름을 지정하면 된다")
	void findBeanByTypeWithName() {
		MemberRepository memberRepository = ac.getBean("memberRepository1", MemberRepository.class);
		assertThat(memberRepository).isInstanceOf(MemberRepository.class);
	}
	@Test
	@DisplayName("특정 타입을 모두 조회")
	void findAllBeanByType() {
		// getBeansOfType()을 사용하면 해당 타입의 모든 빈을 조회할 수 있다.
		Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
		for (String key : beansOfType.keySet()) {
			System.out.println("key = " + key + " value = " +
					beansOfType.get(key));
		}
		System.out.println("beansOfType = " + beansOfType);
		assertThat(beansOfType.size()).isEqualTo(2);
	}



	@Test
	@DisplayName("빈 설정 메타정보 확인")
	void findApplicationBean() {
		String[] beanDefinitionNames = ac.getBeanDefinitionNames();
		for (String beanDefinitionName : beanDefinitionNames) {
			BeanDefinition beanDefinition =
					ac.getBeanDefinition(beanDefinitionName);
			if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
				System.out.println("beanDefinitionName" + beanDefinitionName +
						" beanDefinition = " + beanDefinition);
			}
		}

	}


}
