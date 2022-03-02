package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component // ("service")
public class MemberServiceImpl implements MemberService{

    // 왼쪽은 역할에 의존, 오른쪽은 구현에 이존
    // OCP, DIP 위반...

    private final MemberRepository memberRepository;
    // 이렇게하면 더이상 memorymemberrepository에 대한 내용이 없음
    // 구현에 대한 의존이 없어지고 역할에만 의존하게 변함함

    @Autowired //ac.getBean(MemberRepository.class)
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
