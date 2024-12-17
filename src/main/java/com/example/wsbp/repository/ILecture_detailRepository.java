package com.example.wsbp.repository;

import com.example.wsbp.data.Lecture;
import com.example.wsbp.data.Lecture_detail;

import java.util.List;

public interface ILecture_detailRepository {
    /**
     * AuthUserテーブルのすべての情報を検索する
     *
     * @return レコードの内容を {@link Lecture_detail の {@link List} で返す
     */  public List<Lecture_detail> finddetails(String num);

}
