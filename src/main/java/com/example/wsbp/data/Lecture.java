package com.example.wsbp.data;

import java.io.Serializable;
//データベースのLectureから全ての情報を取得する　今回は使用しない
// lecture テーブルのデータを入れるクラス
// Wicketの Model に使うかもしれないクラスは、 implements Serializable をつける
public class Lecture implements Serializable {

    private final String iD;  // lectureテーブルのid列のデータ
    private final String subject_iD;// lectureテーブルのsubject_id列のデータ
    private final String lecture_serial_Num;// lectureテーブルのlecture_serial_num列のデータ
    private final String Detail;// lectureテーブルのdetail列のデータ

    public Lecture(String iD, String subject_iD, String lecture_serial_Num, String Detail) {
        this.iD = iD;
        this.subject_iD = subject_iD;
        this.lecture_serial_Num = lecture_serial_Num;
        this.Detail = Detail;
    }

    public String getiD() {
        return iD;
    }

    public String getsubject_iD() {
        return subject_iD;
    }

    public String getlecture_serial_Num() {
        return lecture_serial_Num;
    }

    public String getDetail() {
        return Detail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lecture lecture = (Lecture) o;

        if (!iD.equals(lecture.iD)) return false;
        return subject_iD.equals(lecture.subject_iD);
    }

    @Override
    public int hashCode() {
        int result = iD.hashCode();
        result = 31 * result + subject_iD.hashCode();
        return result;
    }
}