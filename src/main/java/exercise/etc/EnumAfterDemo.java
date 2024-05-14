package exercise.etc;

class Main {
    public static void main(String[] args) {
        EnumAfterDemo americano = EnumAfterDemo.AMERICANO;

        System.out.println(americano);
    }
}


public enum EnumAfterDemo {
    AMERICANO(0, "아메리카노"),
    LATTE(1, "라떼"),
    STRAWBERRY_ADE(2, "딸기에이드"),
    ESPRESSO(3, "에스프레소");

    private final int menuNum;
    private final String menuName;

    EnumAfterDemo(int menu, String menuName) {
        this.menuNum = menu;
        this.menuName = menuName;
    }

    public int getMenuNum() {
        return menuNum;
    }

    public void selectMenu() {
        System.out.println(menuName);
    }

    @Override
    public String toString() {
        return "EnumAfterDemo{" +
                "menuNum=" + menuNum +
                ", menuName='" + menuName + '\'' +
                '}';
    }
}

