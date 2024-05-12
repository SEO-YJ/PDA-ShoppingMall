package exercise.homework;

import java.util.ArrayList;
import java.util.Scanner;

public class NumberBaseballGameApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 자릿수 비교를 위해 ArrayList를 사용
        ArrayList<Integer> systemNumbers = new ArrayList<>();

        while (systemNumbers.size() < 3) {
            // 0~9까지의 숫자를 생성
            int randomNumber = (int) (Math.random() * 10);

            // 중복 검사 실시
            // systemNumber 배열에 생성한 랜덤값이 없을 경우에만 ArrayList에 추가
            if (!systemNumbers.contains(randomNumber)) {
                systemNumbers.add(randomNumber);
            }
        }

        explainRule();

        System.out.println("사용자 입력");
        System.out.println(systemNumbers);

        // 기회
        int opportunities = 0;

        while (opportunities < 10) {
            opportunities++;
            System.out.print(opportunities + "회차 : ");
            // 사용자에게 숫자를 입력 받음
            ArrayList<Integer> userInputs = getUserInput(opportunities, scanner);

            int strikes = 0;
            int balls = 0;
            int outs = 3;

            // 3번 반복
            for (int i = 0; i < 3; i++) {
                int userNumber = userInputs.get(i);
                if (userNumber == systemNumbers.get(i)) {
                    // 유저가 입력한 숫자랑 시스템 숫자랑 동일하고, 자릿수도 같음
                    strikes++;
                    outs--;
                } else if (systemNumbers.contains(userNumber)) {
                    balls++;
                    outs--;
                }
            }

            if (strikes == 3) {
                System.out.println();
                System.out.println("사용자가 이겼습니다.");
                System.out.println("축하합니다! " + opportunities + "번 만에 숫자를 모두 맞혔습니다!");
                break;
            } else {
                System.out.println("결과: " + strikes + "S " + balls + "B " + outs + "O" );
            }

            if (opportunities == 10) {
                System.out.println();
                System.out.println("사용자가 졌습니다.");
                System.out.println("정답 숫자는 " + systemNumbers + "였습니다.");
            }
        }
        scanner.close();
    }

    // 규칙 설명 메서드
    private static void explainRule() {
        System.out.println("------------------------------");
        System.out.println("-------- 숫자 야구 게임 --------");
        System.out.println("------------------------------");
        System.out.println("시스템이 숫자 3개를 몰래 정하고,\n" +
                "사용자가 10번의 기회 안에 3S를 맞추면,\n" +
                "유저가 이기는 게임입니다.");
        System.out.println();
        System.out.println("규칙은 다음과 같습니다.");
        System.out.println("* 숫자&자리까지 다 맞으면 Strike");
        System.out.println("* 숫자는 있는데 자리는 틀리면 Ball");
        System.out.println("* 숫자가 없으면 Out");
        System.out.println();
    }

    // 사용자 입력 메서드
    private static ArrayList<Integer> getUserInput(int opportunities, Scanner scanner) {
        String input = scanner.nextLine();
        input = input.replace(" ", "");
        ArrayList<Integer> inputs = new ArrayList<>();

        try {
            Integer.parseInt(input);
            for (char c : input.toCharArray()) {
                // 숫자가 아닌 글자를 입력하면 횟수 차감 X
                inputs.add(Character.getNumericValue(c));
            }
            // 입력된 숫자의 갯수가 3개가 아니면
            if (inputs.size() != 3) {
                throw new IllegalArgumentException();
            }
        } catch (NumberFormatException e) {
            System.out.println("입력에 문자가 포함되어있습니다. 숫자만 올바르게 입력해주세요.");
            System.out.print(opportunities + "회차 : ");
            return getUserInput(opportunities, scanner);
        } catch (IllegalArgumentException e) {
            System.out.println("3개의 숫자를 올바르게 입력해주세요.");
            System.out.print(opportunities + "회차 : ");
            return getUserInput(opportunities, scanner);
        }
        return inputs;
    }
}