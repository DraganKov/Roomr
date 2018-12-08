package com.example.ryan.roomr_ticketservice;

class StoreValue {
    private static String value = "";
    private static String ipAddress = "";

    public static void setValue(String v){


        value = v;
    }

    public static String getValue(){
        return value;
    }


    public static String getIpAddress() {
        return ipAddress;
    }

    public static void setIpAddress(String ipAddress) {
        StoreValue.ipAddress = ipAddress;
    }
}
