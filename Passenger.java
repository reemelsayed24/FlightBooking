/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightproj1;

/**
 *
 * @author Reem
 */
public class Passenger {
    private int passengerID;
    private String name;
    private String number;
    private String gender;
    private String email;

    public Passenger(int passengerID, String name, String number, String gender, String email) {
        this.passengerID = passengerID;
        this.name = name;
        this.number = number;
        this.gender = gender;
        this.email = email;
    }

    Passenger() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getPassengerID() {
        return passengerID;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public void setPassengerID(int passengerID) {
        this.passengerID = passengerID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Passenger{" + "passengerID=" + passengerID + ", name=" + name + ", number=" + number + ", gender=" + gender + ", email=" + email + '}';
    }
    
    
}
