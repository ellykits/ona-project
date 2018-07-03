package com.coder.nerdsoft.projectx.domain.entity;

import java.util.List;

public class TeamEntity {

    private String role;
    private String name;
    private List<String> users = null;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }
}
