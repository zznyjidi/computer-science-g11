public class Member {

    // Fields
    private String firstName;
    private String lastName;
    private int age;
    private String major;

    // Constructor
    public Member(String firstName, String lastName, int age, String major) {
        this.firstName = firstName;
        this.lastName = lastName;
        setAge(age);
        this.major = major;
    }

    // Getters and Setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age > 0)
            this.age = age;
        else
            System.out.println("Age is invalid, not changing. ");
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    // toString
    @Override
    public String toString() {
        return "Member [firstName=" + firstName + ", lastName=" + lastName + ", age=" + age + ", major=" + major + "]";
    }
    
}
