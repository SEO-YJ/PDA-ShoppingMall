package exercise.etc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class IteratorDemo {
    public static void main(String[] args) {
        ArrayList<String> sportStars = new ArrayList<>();
        // add 이름 5개
        // 1. 순수한 for문 돌려서 순서대로 이름 출력
        sportStars.add("이천수");
        sportStars.add("말디니");
        sportStars.add("황성빈");
        sportStars.add("양현종");
        sportStars.add("황재균");

        for(int i = 0; i < sportStars.size(); i++) {
            System.out.println(sportStars.get(i));
        }

        // 2. map으로 바꾸기
        Map<Integer, String > fruits = new HashMap<Integer, String>();
        fruits.put(1, "체리쥬빌레");
        fruits.put(2, "사과");
        fruits.put(3, "파인애플");
        fruits.put(4, "바나나");
        fruits.put(5, "블루베리");

        for(int i = 1; i < fruits.size(); i++) {
            System.out.println(fruits.get(i));
        }

        // 3. Iterator
        Iterator<String> sportStarIterator = sportStars.iterator();
        while(sportStarIterator.hasNext()) {
            System.out.println(sportStarIterator.next());
        }

        // 4. fruits -> Iterator
        Iterator<Integer> fruitsIterator = fruits.keySet().iterator();
        while(fruitsIterator.hasNext()) {
            System.out.println(fruitsIterator.next());
        }

        // 5. for each
        for (String sportStar : sportStars) {
            System.out.println(sportStar);

            if (sportStar == "황재균")
                sportStars.remove(sportStar);
        }


        for (String fruit : fruits.values()) {
            System.out.println(fruit);
        }
    }
}
