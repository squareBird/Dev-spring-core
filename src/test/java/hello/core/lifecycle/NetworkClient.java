package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;

// javax는 자바에서 공식 지원 하는거여서 스프링 아니어도 사용 가능d
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // 서비스 시작시 호출
    public void connect() {
        System.out.println("connect = " + url);
    }

    public void call(String message) {
        System.out.println("call = " + url + " messgae = " + message);
    }

    // 서비스 종료시 호출출
   public void disconnect() {
        System.out.println("close");
    }

    // 의존관계 주입후에 해당 메소드 호출
    @PostConstruct
   public void init() throws Exception {
       System.out.println("NetworkClient.init");
       connect();
       call("초기화 연결 메시지");
   }
   @PreDestroy
   public void close() throws Exception {
       System.out.println("NetworkClient.close");
       disconnect();
   }
}
