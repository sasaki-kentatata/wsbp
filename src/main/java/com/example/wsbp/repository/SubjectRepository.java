package com.example.wsbp.repository;

import com.example.wsbp.data.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SubjectRepository implements ISubjectRepository {

    // SpringJDBCのデータベース制御用インスタンス
    private final JdbcTemplate jdbc;

    // jdbc の di/ioc 設定（Wicketとやり方が異なるので注意）
    @Autowired
    public SubjectRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }


    @Override
    public List<Subject> find() {
        // subject テーブルの id, subject_name を検索する
        String sql = "select id, subject_name from subject";

        // 検索用のSQLを実行する方法。
        // 取り出したいデータの型によって、第2引数の RowMapper を切り替える。
        // ? を使うSQLであれば、第3引数の Object型配列 の要素に順番に設定する。
        List<Subject> subjects = jdbc.query(sql,
                DataClassRowMapper.newInstance(Subject.class));

        // 取り出したデータ（Listの要素）をそのまま返値とする。
        return subjects;
    }

}