package com.example.wsbp.data;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.Serializable;

// AUTH_USER テーブルのデータを入れるクラス
// Wicketの Model に使うかもしれないクラスは、 implements Serializable をつける
public class Subject implements Serializable {

    private final String ID;  // auth_userテーブルのuser_name列のデータ
    private final String Subject_Name;  // auth_userテーブルのuser_pass列のデータ

    public Subject(String ID, String Subject_Name) {
        this.ID = ID;
        this.Subject_Name = Subject_Name;
    }

    public String getID() {
        return ID;
    }

    public String getSubject_Name() {
        return Subject_Name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subject subject = (Subject) o;

        if (!ID.equals(subject.ID)) return false;
        return Subject_Name.equals(subject.Subject_Name);
    }

    @Override
    public int hashCode() {
        int result = ID.hashCode();
        result = 31 * result + Subject_Name.hashCode();
        return result;
    }
}