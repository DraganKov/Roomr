package com.example.ryan.roomr_ticketservice;



public class TenantObj {
    private String name;
    private String homeAddress;

    public TenantObj(String name, String homeAddress) {
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
