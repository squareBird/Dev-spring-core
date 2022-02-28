package hello.core.member;

public class MemberServiceImpl implements MemberService{

    // 왼쪽은 역할에 의존, 오른쪽은 구현에 이존
    // OCP, DIP 위반...
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
