package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan( // -> @Component 어노테이션이 붙은걸 찾아서 자동으로 스프링 빈으로 등록해주는 기능
        // appConfig를 안읽어오려고 필터
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {


}
