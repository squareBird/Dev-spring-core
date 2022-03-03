package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Or;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }



    @Test
    void createOrder() {
        // given
        Long memberId = 1L;

        // when
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);
        Order order = orderService.createOrder(1L, "itemA", 10000);

        // then
        assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

//    @Test
//    void fieldInjectionTest() {
//        // 필드주입으로 완성하고 이렇게 스프링이 없는 환경에서 테스트를 진행할수도 있다
//        // 지금 이렇게만 해두면 스프링이 없기때문에 Autowired가 동작하지 않아 의존성이 주입되지 않았다.
//        // private이라서 외부에서 orderservice.memberRepository 이런식으로 넣어줄수도 없다.
//        // 결국 setter를 또 추가로 만들어줘야함...
//        OrderServiceImpl orderService = new OrderServiceImpl();
//        // createOrder는 memberRepository와 discountPolicy가 필요한데 두개의 의존성이 주입되지 않았으니 nullpointException이 발생한다
//        orderService.createOrder(1L, "itema", 10000);
//    }


}