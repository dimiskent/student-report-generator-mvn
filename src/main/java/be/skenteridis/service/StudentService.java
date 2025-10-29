package be.skenteridis.service;

import be.skenteridis.dao.StudentDAO;
import be.skenteridis.model.Student;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.*;
import java.nio.file.Paths;
import java.sql.*;
import java.util.*;

public class StudentService {
    private final String basePath = Paths.get("src", "main", "resources").toString();
    private final StudentDAO dao = new StudentDAO();

    public List<Student> getStudents() {
        return dao.getStudents();
    }

    public void exportCSV(String filePath) {
        filePath = Paths.get(basePath, filePath).toString();
        List<Student> students = getStudents();
        if(students.isEmpty()) System.out.println("Nothing to export");
        else try(CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
                writer.writeNext(new String[]{"Name", "Subject", "Grade"});
                for(Student student : students) {
                    writer.writeNext(new String[]{
                            student.name(),
                            student.subject(),
                            String.valueOf(student.grade())
                    });
                }
                System.out.println("Student saved successfully!");
            } catch (Exception e) {
                System.out.println("Couldn't save file! " + e.getMessage());
            }
    }
    public void importCSV(String filePath) {
        filePath = Paths.get(basePath, filePath).toString();
        List<Student> students = new ArrayList<>();
        try(CSVReader reader = new CSVReader(new FileReader(filePath))) {
            reader.readNext();
            String[] line;
            while ((line = reader.readNext()) != null) {
                students.add(new Student(0, Objects.requireNonNull(line[0]), Objects.requireNonNull(line[1]), Double.parseDouble(line[2])));
            }
        } catch (NumberFormatException | NullPointerException e) {
            System.out.println("Can't parse file! " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Can't load file! " + e.getMessage());
        }
        if(students.isEmpty()) System.out.println("Nothing to import!");
        else dao.addStudents(students);
    }
}
