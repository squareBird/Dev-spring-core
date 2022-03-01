package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.DiscountPolicyImpl;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    // 이렇게하면 new RateDiscountPolicy() 즉 구현에 의존하던 부분은 빼고 역할에만 의존함
    // 그냥 이렇게 하면 NullPointException
    // 누군가가 OrderServiceImpl 클래스의 discountPolicy 객체에 구현 객체를 대신 생성하고 주입해 주어야 함
    private DiscountPolicy discountPolicy;

    // order은 멤버나 할인에 대해 모름 그냥 인터페이스에 넘김
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {

        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

}