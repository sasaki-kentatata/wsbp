package com.example.wsbp.repository;
//データベースのLectureから全ての情報を取得する　今回は使用しない
import com.example.wsbp.data.AuthUser;
import com.example.wsbp.data.Lecture;

import java.util.List;

public interface ILectureRepository {
    /**
     * AuthUserテーブルのすべての情報を検索する
     *
     * @return レコードの内容を {@link Lecture の {@link List} で返す
     */  public List<Lecture> find();

//     public List<Lecture> finddetail(String num);
}
