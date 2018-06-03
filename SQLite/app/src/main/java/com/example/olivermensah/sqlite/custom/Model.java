package com.example.olivermensah.sqlite.custom;

/**
 * Created by olivermensah on 12/5/17.
 */

public  class Model{
    private String name;
    private String username;

    public Model(String name, String username) {
        this.name = name;
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return "Model{" +
                "name='" + name + '\'' +
                ", username='" + username + '\'' +
                '}';
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
