package com.example.test_retrofit;

import com.google.gson.annotations.SerializedName;

public class admin {
    @SerializedName("name")
    private String name;
    @SerializedName("age")
    private Long age;
    @SerializedName("univ")
    private String univ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public String getUniv() {
        return univ;
    }

    public void setUniv(String univ) {
        this.univ = univ;
    }
}
