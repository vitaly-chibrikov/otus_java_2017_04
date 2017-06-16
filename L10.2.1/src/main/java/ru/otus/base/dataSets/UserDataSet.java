package ru.otus.base.dataSets;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "users")
public class UserDataSet extends DataSet {

    @Column(name = "name")
    private String name;

    //@OneToOne(cascade = CascadeType.ALL)
    //private PhoneDataSet mainPhone;

    @OneToMany
    @JoinTable(name = "phones",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "id")}
    )
    private List<PhoneDataSet> phones;

    //Important for Hibernate
    public UserDataSet() {
    }

    public UserDataSet(long id, String name, PhoneDataSet... phones) {
        this.setId(id);
        this.setName(name);
        this.setMainPhone(phones[0]);
        this.setPhones(Arrays.asList(phones));
    }

    public UserDataSet(String name, PhoneDataSet... phones) {
        this.setId(-1);
        this.setName(name);
        this.setMainPhone(phones[0]);
        this.setPhones(Arrays.asList(phones));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhones(List<PhoneDataSet> phones) {
        this.phones = phones;
    }

    public void setMainPhone(PhoneDataSet mainPhone) {
        //this.mainPhone = mainPhone;
    }

    @Override
    public String toString() {
        return "UserDataSet{" +
                "name='" + name + '\'' +
          //      ", mainPhone=" + mainPhone +
                '}';
    }
}

