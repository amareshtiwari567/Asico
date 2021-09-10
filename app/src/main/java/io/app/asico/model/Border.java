package io.app.asico.model;

import com.google.gson.annotations.SerializedName;

public class Border {
    @SerializedName("border")
    String border;

    public Border(String border) {
        this.border = border;
    }

    public String getBorder() {
        return border;
    }

    public void setBorder(String border) {
        this.border = border;
    }
}
