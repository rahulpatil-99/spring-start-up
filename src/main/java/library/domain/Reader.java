package library.domain;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class Reader {

    @Id
    public String id;

    public String firstName;
    public String lastName;
    public Date dob;

    public Reader() {}

    public Reader(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void setLastName(String lastName) {
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
