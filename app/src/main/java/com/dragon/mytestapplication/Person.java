package com.dragon.mytestapplication;

public class Person {
    private String mName;
    private int mAge;

    public Person(final String name, final int age) {
        mName = name;
        mAge = age;
    }

    public String getName() {
        return mName;
    }

    public int getAge() {
        return mAge;
    }

    @Override
    public String toString() {
        return "Person{" +
                "mName='" + mName + '\'' +
                ", mAge=" + mAge +
                '}';
    }
}
