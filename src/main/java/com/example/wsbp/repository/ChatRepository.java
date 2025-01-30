package com.example.wsbp.repository;

import com.example.wsbp.data.Chat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChatRepository implements IChatRepository {

    // SpringJDBCのデータベース制御用インスタンス
    private final JdbcTemplate jdbc;

    // jdbc の di/ioc 設定（Wicketとやり方が異なるので注意）
    @Autowired
    public ChatRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public List<Chat> findmsg() {
        // auth_user テーブルの user_name, user_pass を検索する
        String sql = "select user_name, msg_body from chat_table";

        // 検索用のSQLを実行する方法。
        // 取り出したいデータの型によって、第2引数の RowMapper を切り替える。
        // ? を使うSQLであれば、第3引数の Object型配列 の要素に順番に設定する。
        List<Chat> chats = jdbc.query(sql,
                DataClassRowMapper.newInstance(Chat.class));

        // 取り出したデータ（Listの要素）をそのまま返値とする。
        return chats;
    }

    @Override
    public int insertmsg(String userName, String userMsg) {
        var sql = "insert into chat_table values (?, ?)";
        var n = jdbc.update(sql, userName, userMsg);
        return n;
    }

}