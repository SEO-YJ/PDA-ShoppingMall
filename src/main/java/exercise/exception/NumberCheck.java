package exercise.exception;

import java.util.InputMismatchException;
import java.util.Scanner;

public class NumberCheck {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("input: ");
            String str = scanner.nextLine();
            if (str.equals("q")) {
                System.out.println("프로그램 종료");
                break;
            }
            try {
                // inputMismatchException
                int score = Integer.parseInt(str);
                if (score >= 60) {
                    System.out.println("합격입니다.");
                    continue;
                } else {
                    System.out.println("다음에 다시 봐요.");
                }
            }
            catch(NumberFormatException e) {
                System.out.println("NumberFormatException");
            }
            catch(InputMismatchException e) {
                System.out.println("InputMismatchException");
            }
        }
    }
}


