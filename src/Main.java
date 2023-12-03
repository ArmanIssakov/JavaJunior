import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * 0.1. Посмотреть разные статьи на Хабр.ру про Stream API
 * 0.2. Посмотреть видеоролики на YouTube.com Тагира Валеева про Stream API
 *
 * 1. Создать список из 1_000 рандомных чисел от 1 до 1_000_000
 * 1.1 Найти максимальное
 * 2.2 Все числа, большие, чем 500_000, умножить на 5, отнять от них 150 и просуммировать
 * 2.3 Найти количество чисел, квадрат которых меньше, чем 100_000
 *
 * 2. Создать класс Employee (Сотрудник) с полями: String name, int age, double salary, String department
 * 2.1 Создать список из 10-20 сотрудников
 * 2.2 Вывести список всех различных отделов (department) по списку сотрудников
 * 2.3 Всем сотрудникам, чья зарплата меньше 10_000, повысить зарплату на 20%
 * 2.4 * Из списка сотрудников с помощью стрима создать Map<String, List<Employee>> с отделами и сотрудниками внутри отдела
 * 2.5 * Из списока сорудников с помощью стрима создать Map<String, Double> с отделами и средней зарплатой внутри отдела
 */

public class Main {
    public static void main(String[] args) {
/*Создать список из 1_000 рандомных чисел от 1 до 1_000_000*/
        List<Integer> list = Stream.generate(() -> ThreadLocalRandom.current().nextInt(1000000))
                        .limit(1000)
                                .toList();
        System.out.println(list);

 /*1.1 Найти максимальное*/
        Optional<Integer> max = list.stream().max(Comparator.naturalOrder());
        Integer maximum = max.get();
        System.out.println("Maximum " + maximum);

/*2.2 Все числа, большие, чем 500_000, умножить на 5, отнять от них 150 и просуммировать*/
        int sum = list.stream()
                .filter(it -> it > 500000)
                .peek(it -> {
                    it = it * 5 - 150;
//                    System.out.println(it);
                })
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println(sum);

/*2.3 Найти количество чисел, квадрат которых меньше, чем 100_000*/

        long count = list.stream().filter(it -> it < it * it).count();
        System.out.println("Количество чисел квадрат которых меньше 100_000 = " + count);

        /*2.1 Создать список из 10-20 сотрудников*/
        List<Employee> employees = new ArrayList<>(List.of(
                new Employee("Tom", 20, 2000, "Blue"),
                new Employee("Kate", 22, 4000, "Green"),
                new Employee("Jane", 24, 6000, "Black"),
                new Employee("Den", 30, 8000, "Green"),
                new Employee("Roy", 32, 10000, "Black"),
                new Employee("Yan", 20, 12000, "Yellow"),
                new Employee("Odin", 22, 4000, "Blue"),
                new Employee("Li", 26, 6000, "Yellow"),
                new Employee("Jade", 30, 10000, "Green"),
                new Employee("Bella", 28, 16000, "Black")
        ));
/*2.2 Вывести список всех различных отделов (department) по списку сотрудников*/
        System.out.println("Список всех различных отделов: ");
        employees.stream().map(it -> it.getDepartment()).distinct().forEach(it -> System.out.println(it));
        System.out.println();

/*2.3 Всем сотрудникам, чья зарплата меньше 10_000, повысить зарплату на 20%*/

        employees.stream()
                .filter(it -> it.getSalary() < 10000)
                .forEach(it -> it.setSalary(it.getSalary()* 1.2));

        System.out.println(employees);
    }


}

