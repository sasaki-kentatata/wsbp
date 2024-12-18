package com.example.wsbp.repository;

import com.example.wsbp.data.Lecture;
import com.example.wsbp.data.Lecture_detail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class Lecture_detailRepository implements ILecture_detailRepository {

    // SpringJDBCのデータベース制御用インスタンス
    private final JdbcTemplate jdbc;

    // jdbc の di/ioc 設定（Wicketとやり方が異なるので注意）
    @Autowired
    public Lecture_detailRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }


    @Override
    public List<Lecture_detail> finddetails(String num){
        String sql = "select id, lecture_serial_num, detail from lecture where subject_id = ?";

        List<Lecture_detail> lecture = jdbc.query(sql,DataClassRowMapper.newInstance(Lecture_detail.class),num);
        return lecture;
    }
}