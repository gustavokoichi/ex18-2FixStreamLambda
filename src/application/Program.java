package application;

import entities.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Program {
    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner input = new Scanner((System.in));

        System.out.print("Enter full file path: ");
        String path = input.nextLine();
        System.out.print("Enter salary: ");
        double salary = input.nextDouble();

        try (BufferedReader br = new BufferedReader(new FileReader(path))){

            List<Employee> list = new ArrayList<>();

            String line = br.readLine();
            while (line != null){
                String[] fields = line.split(",");
                list.add(new Employee(fields[0], fields[1], Double.parseDouble(fields[2])));
                line = br.readLine();
            }

            List<String> supSalary = list.stream()
                    .filter(p -> p.getSalary() > salary)
                    .map( p -> p.getEmail())
                    .sorted()
                    .collect(Collectors.toList());
            System.out.printf("Email of people whose salary is more than %.2f:%n", salary);
            supSalary.forEach(System.out::println);

            Double sum = list.stream()
                                        .filter(n -> n.getName().charAt(0) == 'M')
                                        .map(n -> n.getSalary())
                                        .reduce(0.0, (x,y) -> x + y);
            System.out.printf("Sum of salary of people whose name starts with 'M': %.2f%n", sum);

        }

        catch (IOException e){
            System.out.println("Error: " + e.getMessage());
        }
        input.close();
    }
}