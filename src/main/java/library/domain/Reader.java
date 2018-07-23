package library.domain;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class Reader {

    @Id
    private String id;

    private String firstName;
    private String lastName;
    private Date dob;

    public Reader() {}

    public Reader(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Reader{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob=" + dob +
                '}';
    }

    public void setDOB(Date dateOfBirth) {
        this.dob =dateOfBirth;
    }
}