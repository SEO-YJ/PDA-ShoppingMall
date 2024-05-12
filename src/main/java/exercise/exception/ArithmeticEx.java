package exercise.exception;

import java.util.LinkedList;
import java.util.List;

public class ArithmeticEx {
    public static void main(String[] args) {
        // 프로그램 실행 전부터 메모리에 올라가 있음
        int result = divide(10, 0);
        System.out.println(result);
    }
    public static int divide(int x, int y) {
        int result = 0;
        try{
            result = x / y;
        } catch (ArithmeticException e) {
            System.out.println("0으로 나눌 수 없다.");
            System.out.println(e.toString());
        }
        return result;
    }
}

//package exercise;
//
//public class ArithmeticEx {
//    public static void main(String[] args) {
//        int result = 0;
//
//        try {
//            result = 10 / 0;
//
//        } catch (ArithmeticException e) {
//
//        }
//    }
//}
