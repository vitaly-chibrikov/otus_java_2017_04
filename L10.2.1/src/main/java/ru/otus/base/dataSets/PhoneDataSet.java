package ru.otus.base.dataSets;

import javax.persistence.*;

/**
 * Created by tully.
 */
@Entity
@Table(name = "phones")
public class PhoneDataSet extends DataSet {

    @Column(name = "number")
    private String number;

    public PhoneDataSet() {
    }

    public PhoneDataSet(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "PhoneDataSet{" +
                "number='" + number + '\'' +
                '}';
    }
}
