package models;

public class Company {
    private int companyId;
    private String name;
    private float minCgpa;

    public Company(int companyId, String name, float minCgpa) {
        this.companyId = companyId;
        this.name = name;
        this.minCgpa = minCgpa;
    }

    public int getCompanyId() { return companyId; }
    public String getName() { return name; }
    public float getMinCgpa() { return minCgpa; }
}
