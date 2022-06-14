package Lab_3.Strategy;

public class Student {
    private String lastname;
    private Subject[] subjects;
    private double average;

    public Student() {
        subjects = new Subject[0];
    }

    public Student(Subject[] subject) {
        this.subjects = subject;
    }

    public void calcAverage() {
        double sum = 0;
        for (Subject subject : subjects) {
            sum += subject.getMark();
        }
        average = sum / (double) subjects.length;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Subject[] getSubjects() {
        return subjects;
    }

    public void setSubjects(Subject[] subjects) {
        this.subjects = subjects;
    }

    public Double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }
}
