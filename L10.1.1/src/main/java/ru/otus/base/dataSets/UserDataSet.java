package ru.otus.base.dataSets;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class UserDataSet extends DataSet {

    @Column(name = "name")
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    private PhoneDataSet phone;

    //Important for Hibernate
    public UserDataSet() {
    }

    public UserDataSet(long id, String name, PhoneDataSet phone) {
        this.setId(id);
        this.setName(name);
        this.setPhone(phone);
    }

    public UserDataSet(String name, PhoneDataSet phone) {
        this.setId(-1);
        this.setName(name);
        this.setPhone(phone);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(PhoneDataSet phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "UserDataSet{" +
                "name='" + name + '\'' +
                ", phone=" + phone +
                '}';
    }
}

