package hello.core;

import hello.core.member.MemberService;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

@SpringBootApplication
public class CoreApplication {

	public static void main(String[] args) {
		//	SpringApplication.run(CoreApplication.class, args);

		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);


		String[] beanDefinitionNames = ac.getBeanDefinitionNames();
		for (String beanDefinitionName : beanDefinitionNames) {

			// getBeanDefinition을 사용하려면 ApplicationContext가 아닌
			// AnnotationConfigApplicationContext를 사용해야 한다.
			BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

			//Role ROLE_APPLICATION: 개발자가 직접 등록한 애플리케이션 빈
			//Role ROLE_INFRASTRUCTURE: 스프링이 내부에서 사용하는 빈
			if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
				MemberService memberService = (MemberService) ac.getBean(MemberService.class);

				System.out.println("name = " + beanDefinitionName + ", " + " object = " + memberService);
			}

		}
	}

}