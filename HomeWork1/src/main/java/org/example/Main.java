package org.example;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args)
    {
        System.out.println("Hello world!");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        double average = numbers.stream()
                .filter(number -> number % 2 == 0) // Фильтруем только четные числа
                .mapToInt(Integer::intValue) // Преобразуем в поток Integer
                .average() // Вычисляем среднее
                .orElse(0.0); // Возвращаем 0.0, если список пуст

        System.out.println("Среднее значение четных чисел: " + average);
    }
}