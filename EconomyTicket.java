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
public class EconomyTicket extends Ticket {
    
    public EconomyTicket(int ticketID, double price, double VAT, boolean hasScreen, boolean hasMeal, boolean hasWifi, boolean hasVIPSeat) {
        super(ticketID, price, VAT, hasScreen, hasMeal, hasWifi, hasVIPSeat);
    }
    
    public double calcPrice()
    {
        return super.calcPrice();
    }
    
}
