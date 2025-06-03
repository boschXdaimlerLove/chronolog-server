package com.bxdlove.worktimeserver.api.json;

import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.validation.constraints.NotNull;

public class PasswordChangeObject {
    @NotNull
    @JsonbProperty("old_password")
    private String oldPassword;

    @NotNull
    @JsonbProperty("new_password")
    private String newPassword;

    public PasswordChangeObject() {
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}