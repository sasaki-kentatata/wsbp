package com.example.wsbp.repository;

import com.example.wsbp.data.Lecture_attendance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class Lecture_attendanceRepository implements ILecture_attendanceRepository {

    // SpringJDBCのデータベース制御用インスタンス
    private final JdbcTemplate jdbc;

    @Autowired
    public Lecture_attendanceRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    // jdbc の di/ioc 設定（Wicketとやり方が異なるので注意）
    @Override
    public boolean exsitpass(String lecture_iD,String userName){
        var sql = "select true from lecture_attendance" + " where lecture_id = ? and student_name = ? ";
        // 検索用のSQLを実行する方法。検索結果をList（可変長配列）で返す。
        // データの追加時と若干異なるので注意。
        var booles = jdbc.query(sql,
                SingleColumnRowMapper.newInstance(Boolean.class),
                lecture_iD, userName);
        // Listにデータがある(＝trueの要素ものがある)：照合成功
        // Listにデータがない(要素が何もない)：照合失敗
        return !booles.isEmpty();
    }


    @Override
    public int insertpass(String lecture_iD,String userName){
        var sql = "insert into lecture_attendance values (?, ?)";
        var n = jdbc.update(sql, lecture_iD, userName);
        return n;
    }
}