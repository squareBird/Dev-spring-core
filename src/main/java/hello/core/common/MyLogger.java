package hello.core.common;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
@Scope("request")
public class MyLogger {

    private String uuid;
    private String requestURL;

    // request URL은 생성시점에 알 수 없으므로
    // 외부에서 setter를 통해 주입받음
    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.println("["+uuid+"]"+"["+requestURL+"] "+message);
    }

    // request가 올때 uuid 할당받음.
    // http 요청과 uuid를 통해 구분
    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString();
        System.out.println("["+uuid+"] request scope bean create."+this);
    }

    @PreDestroy
    public void close() {
        System.out.println();
        System.out.println("["+uuid+"] request scope bean close."+this);
    }
}
