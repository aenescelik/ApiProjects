package com.techproed.pojos;

public class TodosPojo {

    /*
    {
 "userId": 21,
 "id": 201,
 "title": "Tidy your room",
 "completed": false
}
*/
    //1- degiskenleri private yapiyoruz

    private int userID;
    private int id;
    private boolean completed;
    private String title;

    //2- bu değerlere ulaşabilmek için public getter ve setter methodlar oluşturalım
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    //3- parametreli ve parametresiz constructor oluşturuyoruz...


    public TodosPojo() {
    }

    public TodosPojo(int userID, int id, boolean completed, String title) {
        this.userID = userID;
        this.id = id;
        this.completed = completed;
        this.title = title;
    }

    //4- toString metodu oluşturuyoruz

    @Override
    public String toString() {
        return "TodosPojo{" +
                "userID=" + userID +
                ", id=" + id +
                ", completed=" + completed +
                ", title='" + title + '\'' +
                '}';
    }
}
