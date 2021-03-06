package com.techproed.pojos;

public class BookingDatesPojo {
    //"bookingdates": {
    //                   "checkin": "2020-09-09",
    //                   "checkout": "2020-09-21"
    //                 }

    //1.adim private degiskenler

    private String checkin;
    private String checkout;

    //2. getter setter

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;

    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    //3-constructorlar


    public BookingDatesPojo() {
    }

    public BookingDatesPojo(String checkin, String checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
    }

    //4- toString generate

    @Override
    public String toString() {
        return "BookingDatesPojo{" +
                "checkin='" + checkin + '\'' +
                ", checkout='" + checkout + '\'' +
                '}';
    }
}
