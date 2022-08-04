package hello.core;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfig {
    // 구현객체 생성, 생성자를 통해 주입(연결)
    // 관심사 분리
    // 리팩토링 - 역할이 잘 들어나게끔!

    // 생성자 주입
    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    }

    // 레파지토리 역할 드러나게끔 분리
    private MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    // 생성자 주입, 이 서비스는 두가지의 인터페이스 모두 필요, MemberRepository, DiscountPolicy
    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    private FixDiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }
}
