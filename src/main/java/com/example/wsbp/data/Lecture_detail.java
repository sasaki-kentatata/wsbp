package com.example.wsbp.data;

import java.io.Serializable;

// lecture テーブルのデータを入れるクラス lectureの必要な情報のみを受け取るクラス
// Wicketの Model に使うかもしれないクラスは、 implements Serializable をつける
public class Lecture_detail implements Serializable {

    private final String lecture_serial_Num;// lectureテーブルのlecture_serial_num列のデータ
    private final String Detail;// lectureテーブルのdetail列のデータ
    private final String iD;// lectureテーブルのid列のデータ

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