package com.ticketbooking.system.entity;

public enum Role{
    OWNER("owner"),
    USER("user");

    public final String label;

    private Role(String label) {this.label = label; }

    public String getValue(){ return label; }

    public static Role valueOfLabel(String label){
        for(Role e: values()){
            if(e.label.equals(label)){
                return e;
            }
        }
        return null;
    }

}
