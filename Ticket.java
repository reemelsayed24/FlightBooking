/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightproj1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Reem
 */
public abstract class Ticket {
    private int ticketID;
    private double price;
    public static double VAT = 14;
    private boolean hasScreen;
    private boolean hasMeal;
    private boolean hasWifi;
    private boolean hasVIPSeat;
    Passenger p = new Passenger();

    public Ticket(int ticketID, double price, double VAT, boolean hasScreen, boolean hasMeal, boolean hasWifi, boolean hasVIPSeat) {
        this.ticketID = ticketID;
        this.price = price;
        this.VAT = VAT;
        this.hasScreen = hasScreen;
        this.hasMeal = hasMeal;
        this.hasWifi = hasWifi;
        this.hasVIPSeat = hasVIPSeat;
    }
public Ticket(){}
    public int getTicketID() {
        return ticketID;
    }

    public double getPrice() {
        return price;
    }

    public double getVAT() {
        return VAT;
    }

    public boolean isHasScreen() {
        return hasScreen;
    }

    public boolean isHasMeal() {
        return hasMeal;
    }

    public boolean isHasWifi() {
        return hasWifi;
    }

    public boolean isHasVIPSeat() {
        return hasVIPSeat;
    }

    public Passenger getP() {
        return p;
    }

    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setVAT(double VAT) {
        this.VAT = VAT;
    }

    public void setHasScreen(boolean hasScreen) {
        this.hasScreen = hasScreen;
    }

    public void setHasMeal(boolean hasMeal) {
        this.hasMeal = hasMeal;
    }

    public void setHasWifi(boolean hasWifi) {
        this.hasWifi = hasWifi;
    }

    public void setHasVIPSeat(boolean hasVIPSeat) {
        this.hasVIPSeat = hasVIPSeat;
    }

    public void setP(Passenger p) {
        this.p = p;
    }

    @Override
    public String toString() {
        return "Ticket{" + "ticketID=" + ticketID + ", price=" + price + ", VAT=" + VAT + ", hasScreen=" + hasScreen + ", hasMeal=" + hasMeal + ", hasWifi=" + hasWifi + ", hasVIPSeat=" + hasVIPSeat + ", p=" + p + '}';
    }
     public boolean checkAvailability(int f, String c) throws FileNotFoundException{
         
         System.out.print("I'm inside the check available, print c : " + c);
         File x = new File("Bookings.txt");
        Scanner s = new Scanner(x);
        int countFlight = 0;
            int countFC = 0;
            int countE = 0;
            
        while(s.hasNext()){
            
            int id = s.nextInt();
            
            int flight = s.nextInt();
            
            String classes = s.next();
            
            System.out.print("I'm inside while loop" + id + flight + classes);
            
            //while(countFlight<=5 && countFC<=2 && countE<=3){
            if(flight == f ){
                countFlight = countFlight +1;
                 System.out.print("reeeeeeeeeeem" + countFlight);

                if(classes.equals("FirstClass"))
                {
                    System.out.print("I'm inside if condition" + classes);
                    
                    if( c.equals(classes) )
                           countFC = countFC +1;
                    
                      System.out.print("reeeeeeeeeeem" + countFC);
                }
                else if( classes.equals("Economy"))
                { 
                    if(c.equals(classes))
                    countE= countE +1;
                    
                      System.out.print("ha3ayat" + countE);
                }
            }
           
            }System.out.println(countFC);
        if(countFlight<6)
        {
                                System.out.print("I'm inside here" + countFlight);

            if (countFC>3 || countE>4)
                return false;
        }
        else 
            return false;
                    
                    return true;
       
    }
    public double calcPrice()
    {
        System.out.println(price);
        return price + (price * (VAT/100));
        
    }
    
    
    
    
}
