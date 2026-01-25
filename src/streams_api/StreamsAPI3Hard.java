package streams_api;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamsAPI3Hard {

	public static void main(String[] args) {

		System.out.println("--------------- Multi-level Employee Grouping (HARD)");
		System.out.println("--------------- Level 1: Group by department");
		System.out.println("--------------- Level 2: Within each dept, group by salary range (Low: <30k, Medium: 30k-60k, High: >60k)");
		
		List<Employee2> employees = Arrays.asList(
				new Employee2("Alice", "IT", 25000), // Low
				new Employee2("Bob", "IT", 45000), // Medium
				new Employee2("Charlie", "IT", 70000), // High
				new Employee2("Diana", "HR", 55000), // Medium
				new Employee2("Eve", "HR", 28000), // Low
				new Employee2("Frank", "IT", 45000) // Medium
		);
		Map<String, Map<String, List<Employee2>>> result = employees.stream().collect(
				Collectors.groupingBy(
						Employee2::getDept, 
						Collectors.groupingBy(emp -> {
							if (emp.getSalary() < 30000)
								return "Low";
							else if (emp.getSalary() <= 60000)
								return "Medium";
							else
								return "High";
						})));
		System.out.println(result);

	}
	
}

class Employee2 {
    String name;
    String dept;
    double salary;
    
    Employee2(String name, String dept, double salary) {
        this.name = name;
        this.dept = dept;
        this.salary = salary;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return name + "(" + salary + ")";
	}
    
}
