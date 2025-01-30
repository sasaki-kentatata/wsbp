package com.example.wsbp.data;

import java.io.Serializable;

// AUTH_USER テーブルのデータを入れるクラス
// Wicketの Model に使うかもしれないクラスは、 implements Serializable をつける
public class Chat implements Serializable {

    private final String userName;  // chat_tableテーブルのuser_name列のデータ
    private final String msgBody;  // chat_tableテーブルのmsg_body列のデータ

    public Chat(String userName, String msgBody) {
        this.userName = userName;
        this.msgBody = msgBody;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserMessage() {
        return msgBody;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Chat authUser = (Chat) o;

        if (!userName.equals(authUser.userName)) return false;
        return msgBody.equals(authUser.msgBody);
    }

    @Override
    public int hashCode() {
        int result = userName.hashCode();
        result = 31 * result + msgBody.hashCode();
        return result;
    }
}