import java.io.*;
import java.util.ArrayList;
import java.util.List;

class Employee {
    String name;
    double baseSalary;
    int yearsOfService;

    public Employee(String name, double baseSalary, int yearsOfService) {
        this.name = name;
        this.baseSalary = baseSalary;
        this.yearsOfService = yearsOfService;
    }

    public double calculateAnnualSalary() {
        return baseSalary + (baseSalary * 0.05 * yearsOfService);
    }
}

public class EmployeeSalaryProcessor {

    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        String inputFilePath ="C:/Users/ariff/Documents/employeesalaries.txt";
        String outputFilePath = "employeeData.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
             PrintWriter writer = new PrintWriter(new FileWriter(outputFilePath))) {

            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    String[] parts = line.split("\\|");
                    String name = parts[0].trim();
                    double baseSalary = Double.parseDouble(parts[1].trim());
                    int yearsOfService = Integer.parseInt(parts[2].trim());
                    employees.add(new Employee(name, baseSalary, yearsOfService));
                } catch (Exception e) {
                    System.out.println("Skipping invalid record: " + line);
                }
            }

            Employee topPerformer = null;
            Employee leastExperienced = null;

            for (Employee emp : employees) {
                if (topPerformer == null || emp.calculateAnnualSalary() > topPerformer.calculateAnnualSalary()) {
                    topPerformer = emp;
                }
                if (leastExperienced == null || emp.yearsOfService < leastExperienced.yearsOfService) {
                    leastExperienced = emp;
                }
                writer.println(emp.name + " | " + emp.calculateAnnualSalary() + " | " + emp.yearsOfService);
            }

            writer.println("Top Performer: " + topPerformer.name);
            writer.println("Least Years of Service: " + leastExperienced.name);

            System.out.println("Processing complete. Check " + outputFilePath + " for details.");

        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());}
}
}