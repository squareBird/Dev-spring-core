package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.DiscountPolicyImpl;
import hello.core.member.*;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class OrderApp {

    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();
        /*
        MemberService memberService = new MemberServiceImpl(null);
        OrderService orderService = new OrderServiceImpl(null, null);
        */
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order ordr = orderService.createOrder(1L, "iteamA", 20000);

        System.out.println("order = " + ordr.toString());

    }
}
