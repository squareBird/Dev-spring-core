package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
        /**
         * 기존에는 main에서 memberServiceImpl을 생성하고
         * memberServiceImpl이 내부에서 구현(MemoryMemberRepository)를 생성했음
         * 그런데 위처럼
         * MemberService memberService = appConfig.memberService();
         * 로 변경시 memberService를 생성하는것은 appConfig가 해주고
         * memberService의 생성자를 통해 구현(MemoryMemberRepository)의 종류를 주입해줌.
         * 따라서 memberService는 구현(MemoryMemberRepository)를 몰라도 되고 역할(MemberRepository)만 알면 됨됨
       */
//        MemberService memberService = new MemberServiceImpl();

        // 이렇게 하면 AppConfig.class의 @Bean을 다 스프링 컨테이너에 올림
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        // Bean에는 메소드 이름으로 등록됨
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());
    }

}
