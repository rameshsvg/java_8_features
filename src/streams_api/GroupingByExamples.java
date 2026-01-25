package streams_api;

import java.util.*;
import java.util.stream.Collectors;

// Collectors.groupingBy() accepts a downstream collector as the second parameter to perform additional processing on elements within each group, 
// beyond just collecting them into a List.
public class GroupingByExamples {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
            new Employee("John", "Sales", 30, 50000),
            new Employee("Jane", "Sales", 25, 45000),
            new Employee("Jack", "IT", 35, 70000),
            new Employee("Tom", "IT", 28, 65000),
            new Employee("Jerry", "Sales", 30, 48000),
            new Employee("Jerry", "Sales", 35, 78000)
        );

        // Example 1: COUNTING
        System.out.println("=== 1. Counting employees per department ===");
        Map<String, Long> deptCount = employees.stream()
									            .collect(Collectors.groupingBy(
									                Employee::getDepartment, 
									                Collectors.counting()
									            ));
        System.out.println(deptCount); 
        // Output: {Sales=3, IT=2}

        // Example 2: SUMMING SALARIES
        System.out.println("\n=== 2. Total salary per department ===");
        Map<String, Integer> totalSalary = employees.stream()
										            .collect(Collectors.groupingBy(
										                Employee::getDepartment, 
										                Collectors.summingInt(Employee::getSalary)
										            ));
        System.out.println(totalSalary);
        // Output: {Sales=143000, IT=135000}

        // Example 3: MAPPING (Names only, unique)
        System.out.println("\n=== 3. Names per department (unique) ===");
        Map<String, Set<String>> deptNames = employees.stream()
											            .collect(Collectors.groupingBy(
											                Employee::getDepartment,
											                Collectors.mapping(Employee::getName, Collectors.toSet())
											            ));
        System.out.println(deptNames);
        // Output: {Sales=[John, Jane, Jerry], IT=[Jack, Tom]}

        // Example 4: JOINING STRINGS
        System.out.println("\n=== 4. Names joined per department ===");
        Map<String, String> namesJoined = employees.stream()
										            .collect(Collectors.groupingBy(
										                Employee::getDepartment,
										                Collectors.mapping(Employee::getName, Collectors.joining(", "))
										            ));
        System.out.println(namesJoined);
        // Output: {Sales=John, Jane, Jerry, IT=Jack, Tom}

        // Example 5: NESTED GROUPING (Dept → Age → Count)
        System.out.println("\n=== 5. Nested: Dept → Age → Count ===");
        Map<String, Map<Integer, Long>> deptToAgeCount = employees.stream()
														            .collect(Collectors.groupingBy(
														                Employee::getDepartment,
														                Collectors.groupingBy(
														                    Employee::getAge,
														                    Collectors.counting()
														                )
														            ));
        System.out.println(deptToAgeCount);
        // Output: {Sales={30=2, 25=1}, IT={35=1, 28=1}}
    }
}

// Simple Employee class
class Employee {
    private String name;
    private String department;
    private int age;
    private int salary;

    public Employee(String name, String department, int age, int salary) {
        this.name = name;
        this.department = department;
        this.age = age;
        this.salary = salary;
    }

    // Getters
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public int getAge() { return age; }
    public int getSalary() { return salary; }

    @Override
    public String toString() {
        return name + " (" + department + ", " + age + ", $" + salary + ")";
    }
}

