package be.skenteridis.app;

import be.skenteridis.model.Student;
import be.skenteridis.service.StudentService;
import be.skenteridis.util.InputUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        StudentService service = new StudentService();
        InputUtil input = new InputUtil();
        boolean run = true;
        do {
            System.out.println("Student DB / CSV Tool");
            System.out.println("0) Exit");
            System.out.println("1) Import CSV");
            System.out.println("2) Export DB");
            System.out.println("3) List Students");
            switch (input.getInt("Choice")) {
                case 0 -> run = false;
                case 1 -> service.importCSV(input.getValidString("File Name"));
                case 2 -> service.exportCSV(input.getValidString("Target File Name"));
                case 3 -> {
                    List<Student> students = service.getStudents();
                    if(students.isEmpty()) {
                        System.out.println("No students found!");
                        break;
                    }
                    Map<String, double[]> averages = new HashMap<>();
                    for(Student student : students) {
                        averages.putIfAbsent(student.name(), new double[]{0, 0});
                        averages.get(student.name())[0] += student.grade();
                        averages.get(student.name())[1]++;
                    }
                    for(Student student : students) {
                        System.out.println("Student ID: " + student.id());
                        System.out.println("Student Name: " + student.name());
                        double avg = averages.get(student.name())[0] / averages.get(student.name())[1];
                        System.out.println("Average Grade: " + avg);
                    }
                }
            }
        } while (run);
        System.out.println("Bye!");
        input.close();
    }
}
