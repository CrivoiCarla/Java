package com.company.classes;

public class Address
{
    private String city;
    private String county;
    private String street;
    private int number;

    public Address() { }

    public Address(String city, String county, String street, int number)
    {
        this.city = city;
        this.county = county;
        this.street = street;
        this.number = number;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getCounty()
    {
        return county;
    }

    public void setCounty(String county)
    {
        this.county = county;
    }

    public String getStreet()
    {
        return street;
    }

    public void setStreet(String street)
    {
        this.street = street;
    }

    public int getNumber()
    {
        return number;
    }

    public void setNumber(int number)
    {
        this.number = number;
    }

    @Override
    public String toString()
    {
        return city + "," + county + "," + street + "," + number;
    }
}
