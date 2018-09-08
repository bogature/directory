package sample.models;

import java.util.List;


public class Contact {
    private Integer id;
    private String name;
    private String surName;
    private String group;

    private List<ContactNumber> numbers;

    public Contact() {
    }

    public Contact(Integer id,String name, String surName, String group) {
        this.name = name;
        this.surName = surName;
        this.group = group;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public List<ContactNumber> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<ContactNumber> numbers) {
        this.numbers = numbers;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return name + ' ' + surName;
    }
}
