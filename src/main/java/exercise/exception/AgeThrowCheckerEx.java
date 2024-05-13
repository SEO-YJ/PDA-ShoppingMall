package exercise.exception;

import java.util.Scanner;

public class AgeThrowCheckerEx {
    public static void main(String[] args) {

        // 숫자 입력
        // 당신의 나이는 몇 살 이시네요. 반갑습니다.
        while(true) {
            Scanner sc = new Scanner(System.in);
            System.out.print("나이를 입력하세요. 범위는 (0~100): ");
            int age = sc.nextInt();
            if(age == -1) {
                break;
            }
            if(age < -1 || age > 100) {
                System.out.println("0~100사이로 입력해주세요.");
                break;
            }
            System.out.println(String.format("당신의 나이는 %d 살이네요. 반갑습니다.", age));
//            sc.close();
        }
    }
}
