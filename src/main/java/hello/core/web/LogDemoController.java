package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
    // 스프링 처음에 실행할때 에러뜸
    // myLogger을 주입 받아야하는데 myLogger의 scope가 reuqest여서 스프링이 뜨는 시점에는 아직 MyLogger가 없음 -> Provider로 해결가능
    private final MyLogger myLogger;

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) throws InterruptedException {
        String requestURL = request.getRequestURL().toString();

        // myLogger = class hello.core.common.MyLogger$$EnhancerBySpringCGLIB$$260621e1 이라고 나옴
        // 내가 만든 마이그로거가 아닌 껍데기가 생겨서 돌아가는 것
        // 스프링 컨테이너에 myLogger라는 이름으로 이 가짜 프록시 객체를 등록
        System.out.println("myLogger = " + myLogger.getClass());

        // myLogger의 메소드를 호출하면 진짜 myLogger의 메소드를 찾아서 실행해줌
        // 가짜 프록시 안에는 진짜 myLogger를 찾는 방법을 알고 있음음
        // 가짜 프록시객체는 원본 클래스를 상속 받아서 만들어져서 클라이언트 입장에서는 이게 진짠지 아닌지 알 필요가 없음(다형성)
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        Thread.sleep(1000);
        logDemoService.logic("testId");
        return "OK";
    }



}
