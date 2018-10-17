package com.example.myflupifanmenu;

public class MenuItemObj {

    private int order;
    private String title;
    private String applicationPath;
    private String urlImage;

    public MenuItemObj(int order, String title, String applicationPath, String urlImage) {
        this.order = order;
        this.title = title;
        this.applicationPath = applicationPath;
        this.urlImage = urlImage;
    }


    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getApplicationPath() {
        return applicationPath;
    }

    public void setApplicationPath(String applicationPath) {
        this.applicationPath = applicationPath;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    @Override
    public String toString() {
        return "MenuItemObj{" +
                "order=" + order +
                ", title='" + title + '\'' +
                ", applicationPath='" + applicationPath + '\'' +
                ", urlImage='" + urlImage + '\'' +
                '}';
    }
}
