package exercise.generics;

import java.util.ArrayList;

public class GenericsDemo {
    public static void main(String[] args) {
        // 3종류의 차가 순서대로 bbang()
        Urus urus = new Urus();
        Amg amg = new Amg();
        Santafe santafe = new Santafe();

        // 상위클래스로 배열을 만들고, 하위클래스 객체들을 할당해주었지만
        // 오버라이딩한 메서드가 호출된다.
//        Car[] list = {urus, amg, santafe};
//        for(Car i: list) {
//            i.bbang();
//        }

        // ArrayList 사용법
        ArrayList<Car> carArrayList = new ArrayList<>();
        carArrayList.add(urus);
        carArrayList.add(amg);
        carArrayList.add(santafe);

        for(int i = 0; i < carArrayList.size(); i++) {
            carArrayList.get(i).bbang();
        }

        CarList<Car> carList = new CarList<>();
        carList.add(new Urus());
        carList.add(new Amg());
        carList.add(new Santafe());

        for(int i = 0; i < carList.size(); i++) {
            carList.get(i).bbang();
        }
    }
}

class CarList<T> {
    private ArrayList<T> arrayList = new ArrayList<>();

    void add(T data) {
        // arrayList에 Car 객체를 하나씩 추가
        arrayList.add(data);
    }

    T get(int index) {
        return arrayList.get(index);
    }

    int size() {
        return arrayList.size();
    }

//    void print() {
//        // bbang()을 for 문을 돌려서 전체 리스트 출력
//        for(int i = 0; i < arrayList.size(); i++) {
//            arrayList.get(i).
//        }
//    }
}

class Car {
    void bbang() {
        System.out.println("경적 소리");
    }
}

class Urus extends Car {
    void bbang() {
        System.out.println("bboong");
    }
}

class Amg extends Car {
    void bbang() {
        System.out.println("boorrrrng");
    }
}

class Santafe extends Car {
    void bbang() {
        System.out.println("빵");
    }
}