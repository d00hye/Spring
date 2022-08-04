package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{
    // 마찬가지로 이제 구체적인 클래스에 대해 모르는 상황 오직 인터페이스만 참조, 구현 객체는 오직 외부에서 생성자를 통해 결정 - 의존관계를 외부에서 주입 -DI
    private final MemberRepository memberRepository;
    // DiscountPolicy 의존 + Fix, Rate 할인 정책 객체에도 의존, 추상화+구체의존 DIP 위반, 코드 변경 OCP 위반
    // OrderServiceImpl 즉 구현체가 구현체도 고르는 다양한 책임을 지님 -> 관심사 분리 필요
    // 구현 객체 생성, 연결 책임의 공연 기획자 AppConfig 등장
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    // Interface 에만 의존
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
    // DiscountPolicy 의 구체를 대신 생성하고 주입해줄 무엇인가가 필요

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
