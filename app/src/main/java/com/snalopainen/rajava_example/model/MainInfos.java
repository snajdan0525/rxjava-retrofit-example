package com.snalopainen.rajava_example.model;

import com.google.gson.annotations.SerializedName;



public class MainInfos
{
    private Double temp;

    private Double pressure;

    private Double humidity;

    private Double tempMin;

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public void setTempMin(Double tempMin) {
        this.tempMin = tempMin;
    }

    public void setTempMax(Double tempMax) {
        this.tempMax = tempMax;
    }

    private Double tempMax;

    public Double getTemp()
    {
        return temp;
    }

    public Double getPressure()
    {
        return pressure;
    }

    public Double getHumidity()
    {
        return humidity;
    }

    public Double getTempMin()
    {
        return tempMin;
    }

    public Double getTempMax()
    {
        return tempMax;
    }
}
