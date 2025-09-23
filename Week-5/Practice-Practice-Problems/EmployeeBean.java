import java.io.Serializable;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Objects;

public class EmployeeBean implements Serializable {
    private static final long serialVersionUID = 1L;

    // ✅ Private fields (JavaBean convention)
    private String employeeId;
    private String firstName;
    private String lastName;
    private double salary;
    private String department;
    private Date hireDate;
    private boolean isActive;

    // ✅ Default no-arg constructor (JavaBean requirement)
    public EmployeeBean() {}

    // ✅ Parameterized constructor
    public EmployeeBean(String employeeId, String firstName, String lastName,
                        double salary, String department, Date hireDate, boolean isActive) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = Math.max(salary, 0); // validation
        this.department = department;
        this.hireDate = hireDate;
        this.isActive = isActive;
    }

    // ✅ Standard Getters & Setters
    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public double getSalary() { return salary; }
    public void setSalary(double salary) {
        if (salary >= 0) this.salary = salary;
        else throw new IllegalArgumentException("Salary must be positive");
    }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public Date getHireDate() { return hireDate; }
    public void setHireDate(Date hireDate) { this.hireDate = hireDate; }

    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { isActive = active; }

    // ✅ Computed properties
    public String getFullName() {
        return (firstName != null ? firstName : "") + " " + (lastName != null ? lastName : "");
    }

    public int getYearsOfService() {
        if (hireDate == null) return 0;
        LocalDate hireLocal = hireDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return (int) ChronoUnit.YEARS.between(hireLocal, LocalDate.now());
    }

    public String getFormattedSalary() {
        return NumberFormat.getCurrencyInstance().format(salary);
    }

    // ✅ Derived property setter
    public void setFullName(String fullName) {
        if (fullName != null && fullName.contains(" ")) {
            String[] parts = fullName.split(" ", 2);
            this.firstName = parts[0];
            this.lastName = parts[1];
        }
    }

    // ✅ Override toString()
    @Override
    public String toString() {
        return "EmployeeBean{" +
                "employeeId='" + employeeId + '\'' +
                ", fullName='" + getFullName() + '\'' +
                ", salary=" + getFormattedSalary() +
                ", department='" + department + '\'' +
                ", hireDate=" + hireDate +
                ", yearsOfService=" + getYearsOfService() +
                ", isActive=" + isActive +
                '}';
    }

    // ✅ Override equals() and hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmployeeBean)) return false;
        EmployeeBean that = (EmployeeBean) o;
        return Objects.equals(employeeId, that.employeeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId);
    }

    // ✅ Testing the bean
    public static void main(String[] args) {
        // Using parameterized constructor
        EmployeeBean emp1 = new EmployeeBean("E001", "John", "Doe", 50000,
                "IT", new Date(100, 0, 1), true); // hireDate = 2000-01-01

        // Using default + setters
        EmployeeBean emp2 = new EmployeeBean();
        emp2.setEmployeeId("E002");
        emp2.setFullName("Jane Smith");
        emp2.setSalary(60000);
        emp2.setDepartment("HR");
        emp2.setHireDate(new Date(110, 5, 15)); // 2010-06-15
        emp2.setActive(true);

        // Print employees
        System.out.println(emp1);
        System.out.println(emp2);

        // Computed properties
        System.out.println("Years of service (emp1): " + emp1.getYearsOfService());
        System.out.println("Formatted salary (emp2): " + emp2.getFormattedSalary());
    }
}
