package com.meritnation.mnframework.modules.account.model.data;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Hukum Singh on 18/5/15.
 */
@DatabaseTable(tableName = "logintable")
public class LoginTableRow {
    // id is generated by the database and set on the object automatically
    @DatabaseField(id = true, columnName = "user")
    String userId;
    @DatabaseField
    String board;
    @DatabaseField
    String grade;
    @DatabaseField
    String redirectUrl;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }


}
