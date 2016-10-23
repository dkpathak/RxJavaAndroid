package com.dkpathak.rxjavaandroid;

/**
 * Created by dharmendra on 23/10/16.
 */

public class RowItem {
    private String name;
    private boolean isChecked;

    public RowItem(String name, boolean isChecked) {
        this.name = name;
        this.isChecked = isChecked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
