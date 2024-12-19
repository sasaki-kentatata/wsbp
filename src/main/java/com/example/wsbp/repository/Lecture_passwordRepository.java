package com.example.wsbp.repository;

import com.example.wsbp.data.Lecture_password;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class Lecture_passwordRepository implements ILecture_passwordRepository {

    // SpringJDBCのデータベース制御用インスタンス
    private final JdbcTemplate jdbc;

    // jdbc の di/ioc 設定（Wicketとやり方が異なるので注意）
    @Autowired
    public Lecture_passwordRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }


    @Override
    public List<Lecture_password> findpass(String num){
        String sql = "select lecture_password from lecture_password where lecture_id = ?";
        List<Lecture_password> lecture = jdbc.query(sql,DataClassRowMapper.newInstance(Lecture_password.class),num);
        return lecture;
    }
}