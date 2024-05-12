package exercise.exception;

import java.util.Scanner;

public class InputMismatchEx {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int score = scanner.nextInt();
        char alpha = scanner.next().charAt(0);

        while(true) {
            if(score >= 60)
                System.out.println("합격입니다.");
            else {
                System.out.println("다음에 다시 봐요.");
                break;
            }
        }
        scanner.close();
    }

}
