package exercise.exception;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MbtiThrowsEx {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.println("=== MBTI 검사를 시작합니다.===");
            // logic
            checkEorI(sc);
            System.out.println("=== MBTI 검사가 종료되었습니다.===");

        } catch(InputMismatchException e) {
            System.out.println("키보드 입력이 잘못 되었습니다.");
        } finally {
            // finally는 무조건 여기가 실행되도록 하는 것 이다.
            if(sc != null) {
                System.out.println("Scanner가 닫혔습니다.");
                sc.close();
            }
        }
    }

    private static void checkEorI(Scanner sc) throws InputMismatchException {
        // 1. 나는 밖에서 에너지를 얻는다.
        // 2. 나는 혼자 있을 때 에너지를 얻는다.
        System.out.println("1. 나는 밖에서 에너지를 얻는다.\n2. 나는 혼자 있을 때 에너지를 얻는다.");
//        System.out.println("2. 나는 혼자 있을 때 에너지를 얻는다.");
        System.out.print("당신의 선택은? ");
        // 1. E
        // 2. I
        // 만약 1a 처럼 잘못 입력하면, 예외!

        int answer = sc.nextInt();
        if(answer == 1) {
            System.out.println("E형 인간입니다.");
        } else if(answer == 2) {
            System.out.println("I형 인간입니다.");
        } else {
            System.out.println("1 또는 2 중 입력하셔야합니다.");
        }
    }
}
