package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class PrototypeTest {

    @Test
    void prototypeBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ProtypeBean.class);
        System.out.println("find protypeBean1");
        ProtypeBean protypeBean1 = ac.getBean(ProtypeBean.class);
        System.out.println("find protypeBean2");
        ProtypeBean protypeBean2 = ac.getBean(ProtypeBean.class);
        System.out.println("protypeBean1 = " + protypeBean1);
        System.out.println("protypeBean2 = " + protypeBean2);
        Assertions.assertThat(protypeBean1).isNotSameAs(protypeBean2);

        // destory 필요하면 직접 호출해 주어야 함
        protypeBean1.destroy();
        protypeBean2.destroy();

        ac.close();
    }

    @Scope("prototype")
    static class ProtypeBean {

        @PostConstruct
        public void init() {
            System.out.println("ProtypeBean.init");
        }
        // prototype이기 때문에 디스트로이 호출이 안됨
        @PreDestroy
        public void destroy() {
            System.out.println("ProtypeBean.destroy");
        }
    }
}
