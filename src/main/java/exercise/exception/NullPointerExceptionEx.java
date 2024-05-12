package exercise.exception;

public class NullPointerExceptionEx {
    public static void main(String[] args) {
        String str = null;
        String[] strings = null;

//        System.out.println(strings[0]);
//        if(str != null)
//            System.out.println(str.charAt(0));
//
//        if(strings != null)
//            System.out.println(strings[0]);

        try {
            // 예외가 발생할 것 같은 의심스러운 코드
            System.out.println("전");
            System.out.println(str.charAt(0));
            System.out.println("후");
        } catch(NullPointerException e) {
            // 예외 터지면 실행되는 코드
            System.out.println("예외 터졌다!");
            System.out.println(e.getMessage());
            // Exception 클래스명 출력
            System.out.println(e.getClass());
            // Exception 발생 이유
            System.out.println(e.getCause());
            System.out.println(e.toString());
        }
    }
}
