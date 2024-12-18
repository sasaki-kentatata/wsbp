package com.example.wsbp.data;

import java.io.Serializable;

// AUTH_USER テーブルのデータを入れるクラス
// Wicketの Model に使うかもしれないクラスは、 implements Serializable をつける
public class Lecture_detail implements Serializable {

    // auth_userテーブルのuser_pass列のデータ
    private final String lecture_serial_Num;
    private final String Detail;
    private final String iD;

    public Lecture_detail(String iD,String lecture_serial_Num, String Detail) {

        this.lecture_serial_Num = lecture_serial_Num;
        this.Detail = Detail;
        this.iD = iD;
    }

    public String getlecture_serial_Num() {
        return lecture_serial_Num;
    }

    public String getDetail() {
        return Detail;
    }

    public String getlecture_id() {return iD;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lecture_detail lecture = (Lecture_detail) o;

        if (!lecture_serial_Num.equals(lecture.lecture_serial_Num)) return false;
        return Detail.equals(lecture.Detail);
    }

    @Override
    public int hashCode() {
        int result = lecture_serial_Num.hashCode();
        result = 31 * result + Detail.hashCode();
        return result;
    }
}