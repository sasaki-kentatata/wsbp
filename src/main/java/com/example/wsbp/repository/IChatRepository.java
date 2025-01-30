
package com.example.wsbp.repository;

import com.example.wsbp.data.Chat;

import java.util.List;

public interface IChatRepository {

    /**
     * AuthUserテーブルのすべての情報を検索する
     *
     * @return レコードの内容を {@link Chat} の {@link List} で返す
     */  public List<Chat> findmsg();
     public int insertmsg(String username, String message);
}