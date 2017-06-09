package ru.otus.base.dataSets;

public class UserDataSet {
    private long id;

    private String name;

    //Important for Hibernate
    public UserDataSet() {
    }

    public UserDataSet(long id, String name) {
        this.setId(id);
        this.setName(name);
    }

    public UserDataSet(String name) {
        this.setId(-1);
        this.setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UserDataSet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

