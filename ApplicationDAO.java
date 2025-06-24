package dao;

import java.sql.*;
import db.DBConnection;

public class ApplicationDAO {
public static void applyForCompany(int studentId, int companyId) {
    try {
        Connection conn = DBConnection.getConnection();

        // 1. Check if student exists
        PreparedStatement ps1 = conn.prepareStatement("SELECT cgpa FROM students WHERE student_id = ?");
        ps1.setInt(1, studentId);
        ResultSet rs1 = ps1.executeQuery();
        if (!rs1.next()) {
            System.out.println(" Student not found.");
            return;
        }
        float cgpa = rs1.getFloat("cgpa");

        // 2. Check if company exists
        PreparedStatement ps2 = conn.prepareStatement("SELECT min_cgpa FROM companies WHERE company_id = ?");
        ps2.setInt(1, companyId);
        ResultSet rs2 = ps2.executeQuery();
        if (!rs2.next()) {
            System.out.println(" Company not found.");
            return;
        }
        float minCgpa = rs2.getFloat("min_cgpa");

        // 3. Check if already applied
        PreparedStatement check = conn.prepareStatement("SELECT * FROM applications WHERE student_id = ? AND company_id = ?");
        check.setInt(1, studentId);
        check.setInt(2, companyId);
        ResultSet checkRs = check.executeQuery();
        if (checkRs.next()) {
            System.out.println("Already applied to this company.");
            return;
        }

        // 4. Check eligibility
        if (cgpa < minCgpa) {
            System.out.println(" Not eligible: CGPA below company requirement.");
            return;
        }

        // 5. Insert application
        String insertSql = "INSERT INTO applications (student_id, company_id) VALUES (?, ?)";
        PreparedStatement insertPs = conn.prepareStatement(insertSql);
        insertPs.setInt(1, studentId);
        insertPs.setInt(2, companyId);
        insertPs.executeUpdate();

        System.out.println("Application submitted.");
    } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
    }
}
    public static void getApplicationsByStudent(int studentId) {
    try {
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT c.name FROM companies c JOIN applications a ON c.company_id = a.company_id WHERE a.student_id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, studentId);
        ResultSet rs = ps.executeQuery();

        System.out.println("\nCompanies applied by Student ID " + studentId + ":");
        boolean found = false;
        while (rs.next()) {
            String cname = rs.getString("name");
            System.out.println("- " + cname);
            found = true;
        }

        if (!found) {
            System.out.println("No applications found.");
        }

    } catch (Exception e) {
        System.out.println("❌ Error: " + e.getMessage());
    }
}


}
