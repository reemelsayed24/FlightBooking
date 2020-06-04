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
public class FirstClassTicket extends Ticket {
    
    public static double extraCost=100;
    

    public FirstClassTicket(double extraCost, int ticketID, double price, double VAT, boolean hasScreen, boolean hasMeal, boolean hasWifi, boolean hasVIPSeat) {
        super(ticketID, price, VAT, hasScreen, hasMeal, hasWifi, hasVIPSeat);
        this.extraCost = extraCost;
    }

    public double getExtraCost() {
        return extraCost;
    }

    public void setExtraCost(double extraCost) {
        extraCost = extraCost;
    }
    
    public double calcPrice()
    {
        return super.calcPrice() + extraCost;
        
    }
    
}
