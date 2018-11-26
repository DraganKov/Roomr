package com.example.ryan.roomr_ticketservice;



public class Tenant {
    private String name;
    private String homeAddress;

    public Tenant(String name, String homeAddress) {
        this.name = name;
        this.homeAddress = homeAddress;

    }

    //retrieve Property Addr
    public String getName() {
        return name;
    }

    //retrieve List of Tenants that live there
    public String getHomeAddress() {
        return homeAddress;
    }


}
