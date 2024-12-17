package com.example.wsbp.repository;

import com.example.wsbp.data.Lecture;
import com.example.wsbp.data.Lecture_detail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
//データベースのLectureから全ての情報を取得する　今回は使用しない
@Repository
public class LectureRepository implements ILectureRepository {

    // SpringJDBCのデータベース制御用インスタンス
    private final JdbcTemplate jdbc;

    // jdbc の di/ioc 設定（Wicketとやり方が異なるので注意）
    @Autowired
    public LectureRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }


    @Override
    public List<Lecture> find() {
        // subject テーブルの id, subject_name を検索する
        String sql = "select id, subject_id, lecture_serial_num, detail from lecture";

        // 検索用のSQLを実行する方法。
        // 取り出したいデータの型によって、第2引数の RowMapper を切り替える。
        // ? を使うSQLであれば、第3引数の Object型配列 の要素に順番に設定する。
        List<Lecture> lectures = jdbc.query(sql,
                DataClassRowMapper.newInstance(Lecture.class));

        // 取り出したデータ（Listの要素）をそのまま返値とする。
        return lectures;
    }

//    @Override
//    public List<Lecture_detail> finddetail(String num){
//        String sql = "select lecture_serial_num,detail from lecture where subject_id = ?";
//        List<Lecture_detail> lecture = jdbc.query(sql,DataClassRowMapper.newInstance(Lecture_detail.class));
//        return lecture;
//    }
}