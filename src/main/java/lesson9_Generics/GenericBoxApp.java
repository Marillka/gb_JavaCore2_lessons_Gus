package lesson9_Generics;

import java.util.List;

public class GenericBoxApp {

    public static void main(String[] args) {
        GenericBox<String> stringBox = new GenericBox<>("string");
        stringBox.showType();

        GenericBox<Integer> intBox1 = new GenericBox<>(100);
        GenericBox<Integer> intBox2 = new GenericBox<>(200);

        intBox1.showType();

        Integer value = intBox1.getObj();
        String str = stringBox.getObj();

        int sum = intBox1.getObj() + intBox2.getObj();


        ComplexGenericBox<String, Integer> complexGenericBox = new ComplexGenericBox<>("str", 111);
        complexGenericBox.showTypes();
    }


    private static class GenericBox<T> { // type erasure - стирание типов. Добавляется кастование без instance of(потому что типы мы проверили на этапе компиляции). В момент компиляции дженерики выбрасываются, под капотом ничего не меняется.
        private T obj;// приватное поле типа T с название obj

        public GenericBox(T obj) {
            this.obj = obj;
        }

        public T getObj() {
            return obj;
        }

        public void setObj(T obj) {
            this.obj = obj;
        }

        public void showType() {
            System.out.println("type of T: " + obj.getClass().getName());
        }
    }


    private static class ComplexGenericBox<V, R> {
        private V obj1;
        private R obj2;

        public ComplexGenericBox(V obj1, R obj2) {
            this.obj1 = obj1;
            this.obj2 = obj2;
        }

        public V getObj1() {
            return obj1;
        }

        public void setObj1(V obj1) {
            this.obj1 = obj1;
        }

        public R getObj2() {
            return obj2;
        }

        public void setObj2(R obj2) {
            this.obj2 = obj2;
        }

        public void showTypes() {
            System.out.println("type of V: " + obj1.getClass().getName());
            System.out.println("type of R: " + obj2.getClass().getName());
        }
    }
}
