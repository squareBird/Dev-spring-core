package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.DiscountPolicyImpl;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Appconfig를 역할이 드러나게 변경
// 역할과 구현 클래스가 한 눈에 들어옴, 어플리케이션 전체 구성을 빠르게 파악할 수 있음
// 코드의 중복 제거
// 구현영역과 사용영역이 완전하게 분리됨
// 구현은 AppConfig에서 다 해주고 실제 사용은 OrderServiceImpl, MemberServiceImpl 등에서 함

// @Configuration을 지우면 CGLIB이 적용되지 않음
@Configuration
public class AppConfig {

    // 이러면 각각 다른 두개이ㅡ MemoryMemberRepository 생성되는 것 처럼 보이면서 싱글톤이 깨지는 것 처럼 보인다.
    // @Bean memberService -> new MemoryMemberRepository()
    // @Bean orderService -> new MemoryMemberRepository()

    // 상상 -> Configuration을 주석처리 하면 CGLIB 사용안해서 memberRepository가 이것처럼 3개 올라옴
    //call AppConfig.memberService
    //call AppConfig.memberRepository
    //call AppConfig.memberRepository
    //call AppConfig.orderService
    //call AppConfig.memberRepository

    // 실제
    //call AppConfig.memberService
    //call AppConfig.memberRepository
    //call AppConfig.orderService


    // 멤버 서비스 역할
    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    // 저장소 역할
    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    // 오더 서비스 역할
    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");

//        return new OrderServiceImpl(memberRepository(), discountPolicy()); // 생성자 없애면 이거 오류남
        return null;

    }

    // 할인 역할
    @Bean
    public DiscountPolicy discountPolicy() {
        return new DiscountPolicyImpl();
    }

}
