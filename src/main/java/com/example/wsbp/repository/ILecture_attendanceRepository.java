package com.example.wsbp.repository;

import com.example.wsbp.data.Lecture_attendance;

import java.util.List;

public interface ILecture_attendanceRepository {
    /**
     * AuthUserテーブルのすべての情報を検索する
     *
     * @return レコードの内容を {@link Lecture_attendance の {@link List} で返す
     */  public boolean exsitpass(String lecture_iD,String userName);

     public int insertpass(String lecture_iD,String userName);

}
