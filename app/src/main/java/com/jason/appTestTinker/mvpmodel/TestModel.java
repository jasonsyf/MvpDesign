package com.jason.appTestTinker.mvpmodel;

/**
 * Created by jason_syf on 2017/2/22.
 * Email: jason_sunyf@163.com
 */

public class TestModel {
    private String name;
    private int age;

    public TestModel(String name, int age, String hobby) {
        this.name = name;
        this.age = age;
        this.hobby = hobby;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private String hobby;
}
