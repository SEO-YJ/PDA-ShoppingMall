package exercise.exception;

public class ArithmeticThrows {
    public static void main(String[] args) {
        // 프로그램 실행 전부터 메모리에 올라가 있음
        try {
            int result = divide(10, 0);
        } catch(ArithmeticException e) {
            System.out.println("내가 대신 처리할게..");
        }
    }
    // 호출한 위치로 예외 처리를 전가
    public static int divide(int x, int y) throws ArithmeticException {
        int result = 0;
        result = x / y;
        return result;
    }
}