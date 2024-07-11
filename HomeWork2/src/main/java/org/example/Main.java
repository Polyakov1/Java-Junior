package org.example;
import java.lang.reflect.Method;
public class Main {
    public static void main(String[] args)
    {
        Class<?> stringClass = String.class;

        // Получение всех методов класса String
        Method[] methods = stringClass.getMethods();

        // Вывод информации о каждом методе
        for (Method method : methods) {
            System.out.println("Метод: " + method.getName());
            System.out.println("Возвращаемый тип: " + method.getReturnType());
            System.out.println("Параметры: ");
            for (Class<?> parameterType : method.getParameterTypes()) {
                System.out.print("  " + parameterType.getName());
            }
            System.out.println();
            System.out.println();
        }
        //Программа выведет список всех методов класса String,
        // включая их возвращаемые типы и типы параметров.
    }
}