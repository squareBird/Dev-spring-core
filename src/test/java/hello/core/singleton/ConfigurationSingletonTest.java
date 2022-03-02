package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.*;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean(MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();;
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        System.out.println("memberService -> memberRepository = " + memberRepository1);
        System.out.println("orderService -> memberRepository = " + memberRepository2);
        System.out.println("memberRepository = " + memberRepository);

        Assertions.assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        Assertions.assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
    }
    
    @Test
    void configurationBean() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);

        // 순수한 클래스면 class hello.core.AppConfig여야 함 근데 실제로는 아래와 같음
        // bean.getClass() = class hello.core.AppConfig$$EnhancerBySpringCGLIB$$293b8ddb
        // 스프링이 CGLIB라는 바이트코드 조작 라이브러리를 사용해서
        // AppConfig 클래스를 상속받은 임의의 다른 클래스를 만들고 그 다른 클래스를 스프링 빈으로 등록한 것
        // AppConfig@CGLIB은 AppConfig의 자식이기 때문에 AppConfig.class로 걸어도 찾을 수 있는 것이다

        // 그럼 내부에선 어떻게 동작할까?
        // CGLIB가 MemberRepository를 상속하든지 해서 만듬
        // MemberRepository가 이미 있으면 그걸 반환, 없으면 원래 코드 호출해서 새로 생성하는 방식!
        System.out.println("bean.getClass() = " + bean.getClass());
    }
}
