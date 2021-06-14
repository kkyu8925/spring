package spring.core.discount;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import spring.core.annotation.MainDiscountPolicy;
import spring.core.member.Grade;
import spring.core.member.Member;

@Component // default : rateDiscountPolicy 으로 빈 등록
//@Qualifier("mainDiscountPolicy")
@Primary
//@MainDiscountPolicy
public class RateDiscountPolicy implements DiscountPolicy {

    private int disCountPercent = 10;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * disCountPercent / 100;
        } else {
            return 0;
        }
    }
}
