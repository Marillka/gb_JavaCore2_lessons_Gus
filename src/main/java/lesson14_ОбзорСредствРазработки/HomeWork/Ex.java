package lesson14_ОбзорСредствРазработки.HomeWork;

import java.util.Arrays;

public class Ex {

    public static void main(String[] args) {

        ArrayExample arrayExample = new ArrayExample();
        int[] array = {1, 2, 4, 4, 2, 3, 7, 1, 8};
        int[] newArray = arrayExample.createNewArray(array);
        System.out.println(Arrays.toString(newArray));
        System.out.println(arrayExample.checkForOneAndFour(array));

        int[] a = {8, 2, 3, 6, 2, 3, 4, 1, 7};
        System.out.println(arrayExample.checkForOneAndFour(a));
    }
}
