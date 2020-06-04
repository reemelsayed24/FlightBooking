/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightproj1;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author Reem
 */
public class Flight {
    
    private int flightID;
    private String arrivalAirport;
    private String departureAirport;
    private LocalDate arrivalDate;
    private LocalDate departureDate;
    private LocalTime arrivalTime;
    private LocalTime departureTime;
    private ArrayList<Passenger> Passengers = new ArrayList<Passenger>();
    private ArrayList<Ticket> Tickets = new ArrayList<Ticket>();


    public Flight(int flightID, String arrivalAirport, String departureAirport, LocalDate arrivalDate, LocalDate departureDate, LocalTime arrivalTime, LocalTime departureTime) {
        this.flightID = flightID;
        this.arrivalAirport = arrivalAirport;
        this.departureAirport = departureAirport;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    

    public int getFlightID() {
        return flightID;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public ArrayList<Passenger> getPassengers() {
        return Passengers;
    }

    public ArrayList<Ticket> getTickets() {
        return Tickets;
    }

    public void setFlightID(int flightID) {
        this.flightID = flightID;
    }

    public void setArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public void setPassengers(ArrayList<Passenger> Passengers) {
        this.Passengers = Passengers;
    }

    public void setTickets(ArrayList<Ticket> Tickets) {
        this.Tickets = Tickets;
    }
    
      
    @Override
    public String toString() {
        return  (flightID + "\t" + arrivalAirport + "\t" + departureAirport + "\t" + departureDate + "\t" + arrivalDate + "\t" + departureTime + "\t" + arrivalTime );
    }
    
    
    
    
}
