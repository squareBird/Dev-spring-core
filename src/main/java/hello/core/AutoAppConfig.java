package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan( // -> @Component 어노테이션이 붙은걸 찾아서 자동으로 스프링 빈으로 등록해주는 기능
//        basePackages = "hello.core.member", << 이걸로하면 member폴더만 등록됨
        basePackages = "hello.core", // 어디서부터 찾는지 지정, 이거 안해주면 온갖 라이브러리까지 다 뒤짐
        basePackageClasses = AutoAppConfig.class, // AutoAppConfig 클래스의 맨 위의 package부터 스캔
        // basePackage 설정 안하면 컴포넌트 스캔이 있는 클래스의 패키지가 시작위치가됨
        // 패키지 위치를 지정하지 않고 설정정보 클래스의 위치를 프로젝트 최상단에 두는 것이 권장
        // 스프링 부트 @SpringBootApplication에 ComponentScan이 있어서 따로 컨피그 안만듬
        // appConfig를 안읽어오려고 필터
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {


}
