package com.snalopainen.rajava_example.model;

import com.google.gson.annotations.SerializedName;



public class Weather
{
    private String strDesc;

    private String strIconName;

    public String getStrDesc()
    {
        return strDesc;
    }

    public void setStrDesc(String strDesc)
    {
        this.strDesc = strDesc;
    }

    public String getStrIconName()
    {
        return strIconName;
    }

    public void setStrIconName(String strIconName)
    {
        this.strIconName = strIconName;
    }
}
