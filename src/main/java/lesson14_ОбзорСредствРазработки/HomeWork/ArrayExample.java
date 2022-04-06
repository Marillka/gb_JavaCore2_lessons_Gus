package lesson14_ОбзорСредствРазработки.HomeWork;

import java.util.Arrays;

public class ArrayExample {
    // Написать метод, которому в качестве аргумента передается не пустой одномерный целочисленный массив. Метод должен вернуть новый массив, который получен путем вытаскивания из исходного массива элементов, идущих после последней четверки. Входной массив должен содержать хотя бы одну четверку, иначе в методе необходимо выбросить RuntimeException. Написать набор тестов для этого метода (по 3-4 варианта входных данных). Вх: [ 1 2 4 4 2 3 4 1 7 ] -> вых: [ 1 7 ].
    public int[] createNewArray(int[] oldArr) throws RuntimeException {
        // проверка на наличие четверки
        int count = 0;
        int placeOfLustFour = 0;
        int sizeOfNewArray = 0;
        for (int i = 0; i < oldArr.length; i++) {
            var a = 0;
            a = oldArr[i];
            if (a == 4) {
                count++;
                placeOfLustFour = i;
                sizeOfNewArray = (oldArr.length - 1) - i;
            }
        }
        // выкидываем исключение если нет четверки
        if (count == 0) {
            throw new RuntimeException("четверки в массиве нет");
        }


        int[] newArr = new int[sizeOfNewArray];

        for (int i = 0; i < sizeOfNewArray; i++) {
            newArr[i] = oldArr[placeOfLustFour + 1 + i];
        }

        return newArr;
    }

    // Написать метод, который проверяет состав массива из чисел 1 и 4. Если в нем нет хоть одной четверки или единицы, то метод вернет false; Написать набор тестов для этого метода (по 3-4 варианта входных данных).

    public boolean checkForOneAndFour(int[] arr) {
        int countOne = 0;
        int countFour = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1) {
                countOne++;
            }
            if (arr[i] == 4) {
                countFour++;
            }
        }

        if (countFour == 0 && countOne == 0) {
            return false;
        }

        return true;
    }


}


