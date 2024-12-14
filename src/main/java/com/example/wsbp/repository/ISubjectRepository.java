package com.example.wsbp.repository;

import com.example.wsbp.data.Subject;
import java.util.List;

public interface ISubjectRepository {

    /**
     * AuthUserテーブルのすべての情報を検索する
     *
     * @return レコードの内容を {@link Subject} の {@link List} で返す
     */  public List<Subject> find();
}
