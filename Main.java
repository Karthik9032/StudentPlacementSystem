import java.util.List;
import java.util.Scanner;
import models.Company;
import models.Student;
import dao.ApplicationDAO;
import dao.CompanyDAO;
import dao.StudentDAO;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("==== Student Placement Management ====");

        while (true) {
            System.out.println("\n1. Add Student\n2.View Students\n3.Add Company\n4.View all companies\n5.Apply for company\n6.Update Student\n7.Delete Student\n8.View8 applications\n9.Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Student ID: ");
                    int id = sc.nextInt();
                    sc.nextLine(); // consume newline
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Branch: ");
                    String branch = sc.nextLine();
                    System.out.print("CGPA: ");
                    float cgpa = sc.nextFloat();
                    Student s = new Student(id, name, branch, cgpa, false);
                    StudentDAO.addStudent(s);
                    break;
                case 2:
                    List<Student> students = StudentDAO.getAllStudents();
                    if (students.isEmpty()) {
                    System.out.println("No students found.");
                    } else {
                    System.out.println("\n--- Student List ---");
                    for (Student stu : students) {
                        System.out.println("ID: " + stu.getStudentId()+ ", Name: " + stu.getName()+ ", Branch: " + stu.getBranch()+ ",CGPA: " + stu.getCgpa()+ ", Placed: " + (stu.isPlaced() ? "Yes" : "No"));
                     }   
                    }
                     break;
                case 3:
                    System.out.print("Company ID: ");
                    int cid = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Company Name: ");
                    String cname = sc.nextLine();
                    System.out.print("Min CGPA: ");
                    float minCgpa = sc.nextFloat();
                    Company c = new Company(cid, cname, minCgpa);
                    CompanyDAO.addCompany(c);
                    break;
                case 4:
                    List<Company> companies = CompanyDAO.getAllCompanies();
                    if (companies.isEmpty()) {
                        System.out.println("No companies found.");
                    } else {
                        System.out.println("\n--- Company List ---");
                        for (Company comp : companies) {
                            System.out.println("ID: " + comp.getCompanyId()+ ", Name: " + comp.getName()+ ", Min CGPA: " + comp.getMinCgpa());
                        }
                    }
                    break;
                case 5:
                    System.out.print("Enter Student ID: ");
                    int sid = sc.nextInt();
                    System.out.print("Enter Company ID: ");
                    int coid = sc.nextInt();
                    ApplicationDAO.applyForCompany(sid, coid);
                    break;
                case 6:
                    System.out.print("Enter Student ID to update: ");
                    int uid = sc.nextInt(); sc.nextLine();
                    System.out.print("New Name: ");
                    String uname = sc.nextLine();
                    System.out.print("New Branch: ");
                    String ubranch = sc.nextLine();
                    System.out.print("New CGPA: ");
                    float ucgpa = sc.nextFloat();
                    System.out.print("Placed? (true/false): ");
                    boolean uplaced = sc.nextBoolean();
                    Student updatedStudent = new Student(uid, uname, ubranch, ucgpa, uplaced);
                    StudentDAO.updateStudent(updatedStudent);
                    break;
                case 7:
                    System.out.print("Enter Student ID to delete: ");
                    int did = sc.nextInt();
                    StudentDAO.deleteStudent(did);
                    break;
                case 8:
                    System.out.print("Enter Student ID: ");
                    int sid2 = sc.nextInt();
                    ApplicationDAO.getApplicationsByStudent(sid2);
                    break;
                case 9:
                    System.out.println("Exiting...");
                    System.exit(0);
            }
        }
        
    }
}
