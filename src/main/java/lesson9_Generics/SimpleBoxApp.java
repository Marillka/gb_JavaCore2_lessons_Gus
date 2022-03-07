package lesson9_Generics;

import lesson1_interface.User;

import java.util.List;

public class SimpleBoxApp {

    public static void main(String[] args) {
        SimpleBox box1 = new SimpleBox(10);
        SimpleBox box2 = new SimpleBox(20);

        SimpleBox box3 = new SimpleBox("abcde");

//        box2.setObj("123");

        //Сложить 2 значения из коробок
         if (box1.getObj() instanceof Integer && box2.getObj() instanceof Integer) {
             int sum = (Integer) box1.getObj() + (Integer) box2.getObj();
             System.out.println("Sum = " + sum );
         } else {
             System.out.println("Невозможно посчитать сумму");
         }

    }

    // Класс-обертка
    private static class SimpleBox {
        private Object obj;

        public SimpleBox(Object obj) {
            this.obj = obj;
        }

        public Object getObj() {
            return obj;
        }

        public void setObj(Object obj) {
            this.obj = obj;
        }
    }
}
