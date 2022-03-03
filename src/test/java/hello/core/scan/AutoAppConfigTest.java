package hello.core.scan;

import hello.core.AutoAppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutoAppConfigTest {

    @Test
    void basicScan() {
        // 여기서 왜 스프링이 올라가는걸까?
        // new AnnotationConfigApplicationContext(AutoAppConfig.class); 때문일까?
        // AutoAppConfig에 Configuration과 Component Scan이 있어서인가
        // 일단 AppConfig에 Component Scan을 빼버리면
        // AnnotationConfigApplicationContext을 통해 기본적으로 사용하는 빈과 AutoAppConfig 까지만 빈으로 올라가고
        // memberService나 OrderService, MemberRepository, DiscountPolicy는 Bean으로 올라가지 않는다!
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        MemberService memberService = ac.getBean(MemberService.class);
        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);

        OrderServiceImpl bean = ac.getBean(OrderServiceImpl.class);
        MemberRepository memberRepository = bean.getMemberRepository();
        System.out.println("memberRepository = " + memberRepository);
    }
}
