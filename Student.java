package models;

public class Student {
    private int studentId;
    private String name;
    private String branch;
    private float cgpa;
    private boolean placed;

    public Student(int studentId, String name, String branch, float cgpa, boolean placed) {
        this.studentId = studentId;
        this.name = name;
        this.branch = branch;
        this.cgpa = cgpa;
        this.placed = placed;
    }

    public int getStudentId() { return studentId; }
    public String getName() { return name; }
    public String getBranch() { return branch; }
    public float getCgpa() { return cgpa; }
    public boolean isPlaced() { return placed; }
}
