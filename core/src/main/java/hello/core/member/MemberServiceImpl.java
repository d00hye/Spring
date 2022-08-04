package hello.core.member;

public class MemberServiceImpl implements MemberService{

    // Interface 에만 의존, 구체적인 memberRepository 구현체는 몰라도 됨
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
//        구현체 MemoryMemberRepository 가 호출
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
