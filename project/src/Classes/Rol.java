package Classes;

import java.io.Serializable;

public class Rol implements Serializable
{
    private String Rol;
    private int salary;

    public Rol() { }

    public Rol(String Rol, int salary)
    {
        this.Rol = Rol;
        this.salary = salary;
    }

    public String getRol()
    {
        return Rol;
    }

    public void setRol(String Rol)
    {
        this.Rol = Rol;
    }

    public int getSalary()
    {
        return salary;
    }

    public void setSalary(int salary)
    {
        this.salary = salary;
    }

    @Override
    public String toString()
    {
        return "Rol: " + Rol + ", Salary: " + salary;
    }
}
