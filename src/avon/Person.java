/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package avon;

/**
 *
 * @author KOURSOS-PC
 */
public  class Person {
    
     private String surname;
    private String name;
    private String phone;

    public Person(String name, String surname, String phone)
    {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
    }

    public String getSurname()
    {
        return surname;
    }

    public String getName()
    {
        return name;
    }

    public String getPhone()
    {
        return phone;
    }
public  Person getperson(){
    return  this;
}
 
}
