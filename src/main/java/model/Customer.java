package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class Customer {

    private String id;
    private String title;
    private String name;
    private String address;
    private String contactNumber;
    private LocalDate dateOfBirth;
    private String nameWithTitle;

    public Customer(String id, String title, String name, String address, String contactNumber, LocalDate dateOfBirth) {
        this.id = id;
        this.title = title;
        this.name = name;
        this.address = address;
        this.contactNumber = contactNumber;
        this.dateOfBirth = dateOfBirth;
        this.nameWithTitle = title + name;
    }
}
