package com.example.ryan.roomr_ticketservice;

import java.util.ArrayList;


public class House {
    private String address;
    private ArrayList<Tenant> tenantList;
    private ArrayList<House> propertyList;

    //Properties need a fixed address location, and a list of tenants that gets
    //updated if/when somebody moves in or out.
    public House(String propertyAddress, ArrayList<Tenant> tenantList) {
        this.address = propertyAddress;
        this.tenantList = tenantList;

    }
    //retrieve Property Addr
    public String getAddress() {
        return address;
    }

    //retrieve List of Tenants that live there
    public ArrayList<Tenant> getTenantList() {
        return tenantList;
    }

    public ArrayList<House> getProperties() {
        return propertyList;
    }

    public  ArrayList<House> addProperties(ArrayList<House> addme){
        this.propertyList = addme;
        return propertyList;
    }
}
