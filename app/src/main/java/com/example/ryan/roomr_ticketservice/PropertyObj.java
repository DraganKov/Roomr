package com.example.ryan.roomr_ticketservice;

import java.util.ArrayList;


public class PropertyObj {
    private String address;
    private ArrayList<TenantObj> tenantList;
    private ArrayList<PropertyObj> propertyList;

    //Properties need a fixed address location, and a list of tenants that gets
    //updated if/when somebody moves in or out.
    public PropertyObj(String propertyAddress, ArrayList<TenantObj> tenantList) {
        this.address = propertyAddress;
        this.tenantList = tenantList;

    }
    //retrieve Property Addr
    public String getAddress() {
        return address;
    }

    //retrieve List of Tenants that live there
    public ArrayList<TenantObj> getTenantList() {
        return tenantList;
    }

    public ArrayList<PropertyObj> getProperties() {
        return propertyList;
    }

    public  ArrayList<PropertyObj> addProperties(ArrayList<PropertyObj> addme){
        this.propertyList = addme;
        return propertyList;
    }
}
