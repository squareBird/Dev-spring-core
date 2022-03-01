package hello.core.singleton;

public class SingletonService {
    
    // 2. 자기자신을 내부에 private static으로 가지고 있음
    // 1. 자바가 뜰때 static 영역에 이게 생성됨
    private static final SingletonService instance = new SingletonService();

    // 4. 가져갈때는 getInstance로만 사용가능
    public static SingletonService getInstance() {
        return instance;
    }

    // 3. private 생성자가 있기 때문에 자기 자신을 제외한 그 어디에서도 new SingletonService()가 불가능
    private SingletonService() {
    }
    //
    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
    
}
