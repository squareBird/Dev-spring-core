package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component // 하나의 빈을 찾으려고 햇는데 타입으로 조회하기때문에 2개가 발견되기도 한다
//@Qualifier("fixDiscountPolicy")
public class FixDiscountPolicy implements DiscountPolicy{

    @Override
    public int discount(Member member, int price) {

        return (member.getGrade() == Grade.VIP) ? 1000 : 0;

    }
}
