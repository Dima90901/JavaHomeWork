package com.pb.PasichnyiDima.hw11;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class TelephoneBook implements Comparable<TelephoneBook>, Serializable {
    private final static long serialVersionUID = 1L;
    private String name;
    private LocalDate dateOfBirth;
    private List<Integer> phoneNumbers;
    private String adress;
    private LocalDateTime editTime;

    public int compareTo(TelephoneBook p) {
        if (p.getName().equals(name)) {
            return 0;
        }
        return -1;
    }

    public TelephoneBook () {}

    public TelephoneBook(String name, LocalDate dateOfBirth, List<Integer> phoneNumbers, String adress) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumbers = phoneNumbers;
        this.adress = adress;
        this.editTime = LocalDateTime.now();
    }

    public LocalDateTime getEditTime() {
        return editTime;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public List<Integer> getPhoneNumbers() {
        return phoneNumbers;
    }

    public String getAdress() {
        return adress;
    }

    public void editPhoneNumbers (List<Integer> phoneNew) {
        this.phoneNumbers = phoneNew;
        this.editTime = LocalDateTime.now();
    }

    public LocalDateTime getEditCreateTime () {
        return editTime;
    }

    @Override
    public String toString() {
        return "TelephoneBook{" +
                "name = '" + name + '\'' +
                ", dateOfBirth = " + dateOfBirth +
                ", phoneNumbers = " + phoneNumbers +
                ", adress = '" + adress + '\'' +
                '}';
    }
}
