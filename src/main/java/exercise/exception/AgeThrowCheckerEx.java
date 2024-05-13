package exercise.exception;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AgeThrowCheckerEx {
    public static void main(String[] args) {

        // 숫자 입력
        // 당신의 나이는 몇 살 이시네요. 반갑습니다.
        while(true) {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print("나이를 입력하세요. 범위는 (0~100): ");
                int age =  sc.nextInt();
//                sc.nextLine(); // 입력 버퍼 비우기
                if (age == -1) {
                    break;
                }
                // 우리 로직의 예외
                if (age < -1 || age > 100) {
                    throw new InputBoundErrorException("0~100 사이로 입력해주세요.");
                }
                System.out.println(String.format("당신의 나이는 %d 살이네요. 반갑습니다.", age));
//                sc.close();
            } catch (InputBoundErrorException e) {
//                System.out.println(e.getMessage());
                e.getMessage();
            }
        }
    }
}

//// TODO: Scanner Test
//// 숫자 입력
//// 당신의 나이는 몇 살 이시네요. 반갑습니다.
//        while(true) {
////            try {
//Scanner sc = new Scanner(System.in);
//                System.out.print("나이를 입력하세요. 범위는 (0~100): ");
//int age =  sc.nextInt();
//                sc.nextLine(); // 입력 버퍼 비우기
//                if (age == -1) {
//        break;
//        }
//        // 우리 로직의 예외
//        if (age < -1 || age > 100) {
////                    throw new Exception("0~100 사이로 입력해주세요.");
//        System.out.println("0~100사이로 입력해주세요.");
//                    break;
//                            }
//                            System.out.println(String.format("당신의 나이는 %d 살이네요. 반갑습니다.", age));
//
////            } catch (Exception e) {
////                System.out.println(e.getMessage());
////            }
//        sc.close();
////                System.out.println(sc.next());
//        }

