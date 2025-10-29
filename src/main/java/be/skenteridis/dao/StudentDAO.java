package be.skenteridis.dao;

import be.skenteridis.model.Student;
import be.skenteridis.service.StudentService;
import be.skenteridis.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    public List<Student> getStudents() {
        List<Student> students = new ArrayList<>();
        try(Connection connection = DBUtil.getConnection();
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery("SELECT * FROM students")
        ) {
            while (set.next()) {
                students.add(new Student(
                        set.getInt("id"),
                        set.getString("name"),
                        set.getString("subject"),
                        set.getDouble("grade")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Cannot obtain students! " + e.getMessage());
        }
        return students;
    }
    public void addStudents(List<Student> students) {
        try(Connection connection = DBUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO students(name,subject,grade) VALUES(?,?,?)")
        ) {
            for(Student student : students) {
                statement.setString(1, student.name());
                statement.setString(2, student.subject());
                statement.setDouble(3, student.grade());
                statement.executeUpdate();
            }
            System.out.println("Imported students successfully!");
        } catch (SQLException e) {
            System.out.println("Error while importing CSV onto database: " + e.getMessage());
        }
    }
}
