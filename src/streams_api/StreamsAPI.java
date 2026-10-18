package streams_api;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamsAPI {

    public static void main(String[] args) {

        // **********************************************************************************************************
        System.out.println("--- Find the top 2 highest-paid employees per department");
        List<Employee> employeesByDepartment = Arrays.asList(
                new Employee("Amit", "IT", 30, 120000),
                new Employee("Neha", "IT", 28, 95000),
                new Employee("Rahul", "IT", 35, 140000),
                new Employee("Priya", "HR", 32, 80000),
                new Employee("Siddharth", "HR", 38, 110000),
                new Employee("Vikram", "Finance", 40, 150000)
        );
        System.out.println(topTwoHighestPaidByDepartment(employeesByDepartment));

        // **********************************************************************************************************
        System.out.println("--- Partition integers into even and odd lists");
        List<Integer> numbers = Arrays.asList(71, 18, 42, 21, 67, 32, 95, 14, 56);
        Map<Boolean, List<Integer>> evenAndOdd = partitionEvenAndOdd(numbers);
        System.out.println("Even numbers: " + evenAndOdd.get(true));
        System.out.println("Odd numbers: " + evenAndOdd.get(false));

        // **********************************************************************************************************
        System.out.println("--- Filter names starting with J and uppercase the rest");
        List<String> names = Arrays.asList("John", "Alice", "Jack", "Bob", "Charlie");
        System.out.println(filterAndUppercaseNames(names));
    }

    public static LinkedHashMap<String, List<Employee>> topTwoHighestPaidByDepartment(List<Employee> employees) {
        return employees.stream()
                        .collect(Collectors.groupingBy(
                                Employee::getDepartment,
                                LinkedHashMap::new,
                                Collectors.collectingAndThen(
                                        Collectors.toList(),
                                        departmentEmployees -> departmentEmployees.stream()
                                                .sorted(Comparator.comparingInt(Employee::getSalary).reversed())
                                                .limit(2)
                                                .collect(Collectors.toList())
                                )
                        ));
    }

    public static Map<Boolean, List<Integer>> partitionEvenAndOdd(List<Integer> numbers) {
        return numbers.stream()
                .collect(Collectors.partitioningBy(number -> number % 2 == 0));
    }

        public static List<String> filterAndUppercaseNames(List<String> names) {
                return names.stream()
                                .filter(name -> !name.startsWith("J"))
                                .map(name -> name.toUpperCase(Locale.ROOT))
                                .collect(Collectors.toList());
        }
}
