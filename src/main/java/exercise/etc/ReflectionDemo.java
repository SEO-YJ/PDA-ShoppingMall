package exercise.etc;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
/*
Member join 할 때는 Member 기본 생성자 없이도 잘됨
login 할 때는 왜 필요하지?

Entity 만드는 방향
1. join : DTO -> Entity 변환(개발자)
2. login: Entity (hibernate) 생성 -> DTO
 */

/*
Java Refelction API
클래스 타입을 구체적으로 알려주지 않아도

 */

/*
JVM 실행시: 자바 코드 -> 컴파일러 -> 바이트 코드 -> static 영역에 저장
Reflection API는 이 영역을 '클래스 이름'을 가지고 뒤져서 정보를 가져옴

못 가져오는 정보 : 생성자
 */

/*
Refelction API 사용 용도
보통은 프레임워크나 라이브러리에서 많이 사용

개발자가 어떤 클래스 만들지 예측 불가능!
코드가 static 영역에 올라와야 이거 만들었구나 알음

Spring Container, intellij 자동 완성, jackson 라이브러리, hibernate
 */
public class ReflectionDemo {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // 최상위 클래스(타입) Object에 나의 객체를 담아요
        // 내 객체의 메소드 쓸 수 있어요?
        Object newjeans = new Newjeans();

//        Class newJeansClass = Newjeans.class;
        Method sing = Newjeans.class.getMethod("sing");
        sing.invoke(newjeans, null);
    }
}

class Newjeans {
    public void sing() {
        System.out.println("Bubble gum");
    }
}
