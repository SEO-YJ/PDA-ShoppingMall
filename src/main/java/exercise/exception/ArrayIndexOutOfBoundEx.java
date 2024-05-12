package exercise.exception;

public class ArrayIndexOutOfBoundEx {
    // 예외 발생 상황
    // try catch
    // 어떤 예외 클래스로 예외 받아줄 건지
    public static void main(String[] args) {
        try {
            String[] names = { "서", "유", "준" };
            System.out.println(names[4]);

        } catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("배열 인덱스 초과");
            System.out.println(e.toString());
        }
    }
}
