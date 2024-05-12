package exercise.exception;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MultiCatchEx {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] cards = {4, 5, 6, 1, 2, 3};

        // 6개 카드 숫자 배열
        try {
            System.out.println("몇번째 카드를 뽑으실래요?");
            int cardIdx = scanner.nextInt();

            System.out.println("뽑은 카드 번호는 : " + cardIdx);
            System.out.println("뽑은 카드에 적힌 숫자는 : "+cards[cardIdx]);
        }
        // Index가 범위를 넘으면 에러
        catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("IndexOutOfBoundsException");
            System.out.println("그거 없는 번호야");
        }
        // 다른 타입의 입력값이 들어오면 에러
        catch(InputMismatchException e) {
            System.out.println("InputMismatchException");
            System.out.println("잘못된 입력값이 들어왔어");
        }
    }
}
