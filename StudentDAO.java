package dao;

import models.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;

public class StudentDAO {
    public static void addStudent(Student s) {
        try {
            Connection conn = DBConnection.getConnection();
            String query = "INSERT INTO students VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, s.getStudentId());
            ps.setString(2, s.getName());
            ps.setString(3, s.getBranch());
            ps.setFloat(4, s.getCgpa());
            ps.setBoolean(5, s.isPlaced());
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Student added successfully.");
            }
        } catch (Exception e) {
            System.out.println("❌ Error adding student: " + e.getMessage());
        }
    }
    public static List<Student> getAllStudents() {
    List<Student> studentList = new ArrayList<>();
    try {
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT * FROM students";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("student_id");
            String name = rs.getString("name");
            String branch = rs.getString("branch");
            float cgpa = rs.getFloat("cgpa");
            boolean placed = rs.getBoolean("placed");

            Student s = new Student(id, name, branch, cgpa, placed);
            studentList.add(s);
        }
    } catch (Exception e) {
        System.out.println("Error fetching students: " + e.getMessage());
    }
    return studentList;
}
// Update Student
public static void updateStudent(Student s) {
    try {
        Connection conn = DBConnection.getConnection();
        String sql = "UPDATE students SET name=?, branch=?, cgpa=?, placed=? WHERE student_id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, s.getName());
        ps.setString(2, s.getBranch());
        ps.setFloat(3, s.getCgpa());
        ps.setBoolean(4, s.isPlaced());
        ps.setInt(5, s.getStudentId());
        int rows = ps.executeUpdate();
        if (rows > 0)
            System.out.println("Student updated.");
        else
            System.out.println("Student not found.");
    } catch (Exception e) {
        System.out.println(" Error updating: " + e.getMessage());
    }
}

// Delete Student
public static void deleteStudent(int id) {
    try {
        Connection conn = DBConnection.getConnection();
        String sql = "DELETE FROM students WHERE student_id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        int rows = ps.executeUpdate();
        if (rows > 0)
            System.out.println("✅ Student deleted.");
        else
            System.out.println("❌ Student not found.");
    } catch (Exception e) {
        System.out.println("❌ Error deleting: " + e.getMessage());
    }
}

}
