package com.abernathyclinic.mediscreendiabetes.domain;

public class DiabetesReport {

    private String lastName;
    private String firstName;
    private int age;
    private DiabetesAssessmentResult diabetesAssessmentResult;

    public DiabetesReport() {
    }

    public DiabetesReport(String lastName, String firstName, int age, DiabetesAssessmentResult diabetesAssessmentResult) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
        this.diabetesAssessmentResult = diabetesAssessmentResult;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public DiabetesAssessmentResult getDiabetesAssessmentResult() {
        return diabetesAssessmentResult;
    }

    public void setDiabetesAssessmentResult(DiabetesAssessmentResult diabetesAssessmentResult) {
        this.diabetesAssessmentResult = diabetesAssessmentResult;
    }

    @Override
    public String toString() {
        return "DiabetesReport{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", age=" + age +
                ", diabetesAssessmentResult=" + diabetesAssessmentResult +
                '}';
    }
}
