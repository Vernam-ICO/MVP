package com.dimiroma.vernam.config.auth;

public class LoginResponse {
    private Long userId;
    private String email;
    private boolean isAdmin;
    private String tokenId;

    public LoginResponse() {
    }

    public LoginResponse(Long userId, String email, boolean isAdmin, String tokenId) {
        this.userId = userId;
        this.email = email;
        this.isAdmin = isAdmin;
        this.tokenId = tokenId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }
}
