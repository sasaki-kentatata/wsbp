package com.example.wsbp.data;

import java.io.Serializable;

//データベースのLectureから全ての情報を取得する　今回は使用しない
// lecture テーブルのデータを入れるクラス
// Wicketの Model に使うかもしれないクラスは、 implements Serializable をつける
public class Lecture_password implements Serializable {

//    private final String lecture_iD;  // auth_userテーブルのuser_name列のデータ
    private final String lecture_Password;// auth_userテーブルのuser_pass列のデータ

    public Lecture_password( String lecture_Password) {
//        this.lecture_iD = lecture_iD; String lecture_iD,
        this.lecture_Password = lecture_Password;
    }

//    public String getLecture_iD() {
//        return lecture_iD;
//    }

    public String getlecture_Password() {
        return lecture_Password;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Lecture_password lecture = (Lecture_password) o;
//
//        if (!lecture_iD.equals(lecture.lecture_iD)) return false;
//        return lecture_Password.equals(lecture.lecture_iD);
//    }
//
//    @Override
//    public int hashCode() {
//        int result = lecture_iD.hashCode();
//        result = 31 * result + lecture_Password.hashCode();
//        return result;
//    }
}