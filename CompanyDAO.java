package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import models.Company;
import db.DBConnection;

public class CompanyDAO {
    public static void addCompany(Company c) {
        try {
            Connection conn = DBConnection.getConnection();
            String sql = "INSERT INTO companies VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, c.getCompanyId());
            ps.setString(2, c.getName());
            ps.setFloat(3, c.getMinCgpa());
            int rows = ps.executeUpdate();
            System.out.println("Company added successfully.");
        } catch (Exception e) {
            System.out.println(" Error: " + e.getMessage());
        }
    }
    public static List<Company> getAllCompanies() {
    List<Company> companyList = new ArrayList<>();
    try {
        Connection conn = DBConnection.getConnection();
        String sql = "SELECT * FROM companies";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("company_id");
            String name = rs.getString("name");
            float cgpa = rs.getFloat("min_cgpa");

            Company c = new Company(id, name, cgpa);
            companyList.add(c);
        }
    } catch (Exception e) {
        System.out.println(" Error fetching companies: " + e.getMessage());
    }
    return companyList;
}

}
