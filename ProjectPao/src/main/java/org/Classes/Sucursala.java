package org.Classes;

public class Sucursala
{
    private String name;
    private Address address = new Address();

    public Sucursala() { }

    public Sucursala(String name, Address address)
    {
        this.name = name;
        this.address = address;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Address getAddress()
    {
        return address;
    }

    public void setAddress(Address address)
    {
        this.address = address;
    }

    @Override
    public String toString()
    {
        return "Name: " + name + ", Address: " + address.toString();
    }
}
