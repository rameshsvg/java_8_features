package streams_api;

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