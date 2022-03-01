package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

// Appconfig를 역할이 드러나게 변경
// 역할과 구현 클래스가 한 눈에 들어옴, 어플리케이션 전체 구성을 빠르게 파악할 수 있음
// 코드의 중복 제거
public class AppConfig {

    // 멤버 서비스 역할
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    // 저장소 역할
    private MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    // 오더 서비스 역할
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    // 할인 역할
    private DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }

}
