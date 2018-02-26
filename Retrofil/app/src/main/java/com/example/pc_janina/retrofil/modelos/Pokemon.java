package com.example.pc_janina.retrofil.modelos;

/**
 * Created by PC-JANINA on 24/2/2018.
 */

public class Pokemon {
    private String name;
    private String url;
    private int number;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getNumber() {
        String urlPartes[] = url.split("/");
        number = Integer.parseInt(urlPartes[urlPartes.length -1]);
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
