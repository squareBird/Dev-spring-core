package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

public class DiscountPolicyImpl implements DiscountPolicy{

    @Override
    public int discount(Member member, int price) {

        return (member.getGrade() == Grade.VIP) ? 1000 : 0;

    }
}
