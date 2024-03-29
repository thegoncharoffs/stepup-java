package org.streamapi;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Коллекции и Stream API
 * Все задачи должны быть выполнены в одну строку
 * - Реализуйте удаление из листа всех дубликатов
 * - Найдите в списке целых чисел 3-е наибольшее число (пример: 5 2 10 9 4 3 10 1 13 => 10)
 * - Найдите в списке целых чисел 3-е наибольшее «уникальное» число (пример: 5 2 10 9 4 3 10 1 13 => 9, в отличие от прошлой задачи здесь разные 10 считает за одно число)
 * - Имеется список объектов типа Сотрудник (имя, возраст, должность), необходимо получить список имен 3 самых старших сотрудников с должностью «Инженер», в порядке убывания возраста
 * - Имеется список объектов типа Сотрудник (имя, возраст, должность), посчитайте средний возраст сотрудников с должностью «Инженер»
 * - Найдите в списке слов самое длинное
 * - Имеется строка с набором слов в нижнем регистре, разделенных пробелом. Постройте хеш-мапы, в которой будут хранится пары: слово - сколько раз оно встречается во входной строке
 * - Отпечатайте в консоль строки из списка в порядке увеличения длины слова, если слова имеют одинаковую длины, то должен быть сохранен алфавитный порядок
 * - Имеется массив строк, в каждой из которых лежит набор из 5 строк, разделенных пробелом, найдите среди всех слов самое длинное, если таких слов несколько, получите любое из них
 */
public class Main {
    public static <T> List<T> removeDuplicates(List<T> arr) {
        return arr.stream()
                .distinct()
                .collect(Collectors.toList());
    }

    public static Integer findNthMax(List<Integer> arr, int n) {
        return arr.stream()
                .sorted(Collections.reverseOrder())
                .collect(Collectors.toList())
                .get(n - 1);
    }

    public static Integer findNthUniqueMax(List<Integer> arr, int n) {
        return arr.stream()
                .distinct()
                .sorted(Collections.reverseOrder())
                .collect(Collectors.toList())
                .get(n - 1);
    }

    public static List<Employee> findNOldestEmployeesByPosition(List<Employee> employees, String position, int n) {
        return employees.stream()
                // Filter by position
                .filter(employee -> employee.getPosition().equals(position))
                // Sort by age
                .sorted((a, b) -> b.getAge() - a.getAge())
                // Limit by n
                .limit(n)
                .collect(Collectors.toList());
    }

    public static double avgAgeByPosition(List<Employee> employees, String position) {
        return employees.stream()
                // Filter by position
                .filter(employee -> employee.getPosition().equals(position))
                // Map obj to age values
                .map(Employee::getAge)
                // Map Integer to int values
                .mapToInt(Integer::intValue)
                // Count avg
                .average()
                // Return NaN in case of error
                .orElse(Double.NaN);
    }

    public static String findLongestWord(List<String> strings) {
        return strings
                .stream()
                .max(Comparator.comparingInt(String::length))
                .orElse("");
    }

    public static Map<String, Long> createWordFrequencyMap(String str) {
        return Arrays.stream(str.split(" "))
                .collect((Collectors.groupingBy(k -> k, Collectors.counting())));
    }

    public static List<String> sortStringsByLengthAndLetters(List<String> strings) {
        return strings.stream()
                .sorted((a, b) -> a.length() - b.length() != 0 ? a.length() - b.length() : a.compareTo(b))
                .collect(Collectors.toList());
    }

    public static String findLongestWordInSeparatedString(List<String> strings) {
        return strings.stream()
                .flatMap(str -> Stream.of(str.split(" ")))
                .max(Comparator.comparingInt(String::length))
                .orElse("");
    }

    public static void main(String[] args) {
        List<Integer> arr = List.of(5, 2, 10, 9, 4, 3, 10, 1, 13);

        // Реализуйте удаление из листа всех дубликатов
        System.out.println(Arrays.toString(removeDuplicates(arr).toArray()));

        // Найдите в списке целых чисел 3-е наибольшее число (пример: 5 2 10 9 4 3 10 1 13 => 10)
        System.out.println(findNthMax(arr, 3));

        // Найдите в списке целых чисел 3-е наибольшее «уникальное» число (пример: 5 2 10 9 4 3 10 1 13 => 9, в отличие от прошлой задачи здесь разные 10 считает за одно число)
        System.out.println(findNthUniqueMax(arr, 3));

        List<Employee> employees = List.of(
                new Employee("Boris", 27, "Engineer"),
                new Employee("Ivan", 22, "Mechanic"),
                new Employee("Max", 31, "Developer"),
                new Employee("Oleg", 44, "Engineer"),
                new Employee("Alex", 35, "Engineer"),
                new Employee("John", 27, "QA"),
                new Employee("Fred", 65, "Engineer")
        );

        // Имеется список объектов типа Сотрудник (имя, возраст, должность), необходимо получить список имен 3 самых старших сотрудников с должностью «Инженер», в порядке убывания возраста
        System.out.println(Arrays.toString(findNOldestEmployeesByPosition(employees, "Engineer", 3).toArray()));

        // Имеется список объектов типа Сотрудник (имя, возраст, должность), посчитайте средний возраст сотрудников с должностью «Инженер»
        System.out.println(avgAgeByPosition(employees, "Engineer"));

        List<String> strings = List.of("a", "aa", "b", "bb", "aaaa", "asdasd", "bbbb");

        // Найдите в списке слов самое длинное
        System.out.println(findLongestWord(strings));

        // Имеется строка с набором слов в нижнем регистре, разделенных пробелом. Постройте хеш-мапы, в которой будут хранится пары: слово - сколько раз оно встречается во входной строке
        System.out.println(createWordFrequencyMap("a b aa bb a b bb a c d d a"));

        // Отпечатайте в консоль строки из списка в порядке увеличения длины слова, если слова имеют одинаковую длины, то должен быть сохранен алфавитный порядок
        System.out.println(sortStringsByLengthAndLetters(strings));

        // Имеется массив строк, в каждой из которых лежит набор из 5 строк, разделенных пробелом, найдите среди всех слов самое длинное, если таких слов несколько, получите любое из них
        List<String> string2 = List.of("a a aa aaaa aaa", "aa sdsd sdsd", "b", "asd sddddsad asd");
        System.out.println(findLongestWordInSeparatedString(string2));
    }
}