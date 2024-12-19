package com.example.wsbp.repository;

import com.example.wsbp.data.Lecture_password;

import java.util.List;

public interface ILecture_passwordRepository {
    /**
     * AuthUserテーブルのすべての情報を検索する
     *
     * @return レコードの内容を {@link Lecture_password の {@link List} で返す
     */  public List<Lecture_password> findpass(String num);

}
