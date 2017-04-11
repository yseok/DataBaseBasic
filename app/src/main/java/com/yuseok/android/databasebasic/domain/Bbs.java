package com.yuseok.android.databasebasic.domain;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

import static android.R.id.content;

/**
 * Created by YS on 2017-02-14.
 */
@DatabaseTable(tableName = "bbs")
public class Bbs {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    private String title;

    @DatabaseField
    private String content;

    @DatabaseField
    private Date currentDate;

    public String getContent() {
        return content;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public void setContent(String content) {
        this.content = content;
    }

    // ORM SQL LITE는 Default생성자를 꼭 하나는 가지고 있어야 한다
    Bbs() {
        // 이게 없으면 ormlite가 동작하지 않는다
    }

    public Bbs(String title, String content, Date currentDate) {
        super();
        this.title = title;
        this.content = content;
        this.currentDate = currentDate;
    }
}
