/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightproj1;

import static flightproj1.FirstClassTicket.extraCost;
import static flightproj1.Ticket.VAT;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Reem
 */


public class TicketBookingViewController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private RadioButton firstClassRB;
    @FXML
    private RadioButton economyRB;
    @FXML
    private CheckBox mealCB;
     @FXML
    private CheckBox wifiCB;
      @FXML
    private CheckBox screenCB;
       @FXML
    private CheckBox vipSeatCB;
       @FXML
       private TextField passengerIDText;
       @FXML
       private TextField nameText;
       @FXML
       private TextField numberText;
       @FXML
       private TextField emailText;
    @FXML
    private ComboBox genderCmb;
    @FXML
    private Label showTicketLabel;
    @FXML
    private Label showTicketPriceLabel;
    private ToggleGroup ticketTypeTG;
    @FXML
    private RadioButton existingP;
    @FXML
    private RadioButton registerP;
    private ToggleGroup passengerTG;
    private Flight selectedFlight;
    @FXML
    private ComboBox flightList;
    @FXML
    private ComboBox passengerList;
    @FXML
    private Button checkButton;
    private boolean meal=false;
    private boolean wifi=false;
    private boolean screen=false;
    private boolean VIP=false;
    private Ticket ticket;
    private int firstClassPrice;
    private int economyClassPrice;
      
    public void bookTicketView(ActionEvent event) throws IOException{
       Parent bookTViewParent = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml")); 
       Scene bookTViewScene = new Scene(bookTViewParent);
       Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
       window.setScene(bookTViewScene);
       window.show();
       
       
    }  
    public void showTicketButton() throws FileNotFoundException{
        if(passengerTG.getSelectedToggle().equals(registerP)){
        String details = "Ticket details:\n";
         details = details + "Passenger ID: " + passengerIDText.getText() + "\n";
         details = details + "Passenger name:" + nameText.getText() + "\n";
         details = details + "Gender: " + genderCmb.getSelectionModel().getSelectedItem().toString() + "\n";
        if(this.ticketTypeTG.getSelectedToggle().equals(firstClassRB))
            details = details + "\nFIRST CLASS SEAT";
        if(this.ticketTypeTG.getSelectedToggle().equals(economyRB))
            details = details + "\nECONOMY SEAT";
        details = details + "\nFlight ID:" + flightList.getSelectionModel().getSelectedItem().toString();
        if(mealCB.isSelected())
        {   meal = true;
            details = details + "\nMeal included";}
        if(wifiCB.isSelected())
        {   wifi = true;
            details = details + "\nWifi included";}
        if(screenCB.isSelected())
        {   screen = true;
            details = details + "\nScreen available";}
        if(vipSeatCB.isSelected())
        {   VIP = true;
            details = details + "\nSeating in VIP area";}
        details = details + "\n" + createID();
                this.showTicketLabel.setText(details);
        }
        if(passengerTG.getSelectedToggle().equals(existingP)){
            String details = "Ticket details:\n";
            File file = new File("Passengers.txt");
            Scanner scan = new Scanner(file);
            Passenger p = null;
            int id=0;
            String name = null;
            String gender= null;
            String number=null;
            String email=null;
            String z=null;
            while(scan.hasNext()){
                
                 id = scan.nextInt();
                 name =scan.next()+ " " + scan.next();
                 z= id + " " + name;
                 number=scan.next();
                 gender=scan.next();
                 email=scan.next();
                
                if(passengerList.getSelectionModel().getSelectedItem().equals(z)){
                    p=new Passenger(id,name,number,gender,email);
                    break;
                }
                System.out.println(name);
                //passengerList.getItems().add(name);
                
                scan.nextLine();
            }
        details = details + "Passenger ID: " + p.getPassengerID() + "\n";
         details = details + "Passenger name:" + p.getName() + "\n";
         details = details + "Gender: " + p.getGender() + "\n";
        
        if(this.ticketTypeTG.getSelectedToggle().equals(firstClassRB))
            details = details + "\nFIRST CLASS SEAT";
        if(this.ticketTypeTG.getSelectedToggle().equals(economyRB))
            details = details + "\nECONOMY SEAT";
        details = details + "\nFlight ID:" + flightList.getSelectionModel().getSelectedItem().toString();
        if(mealCB.isSelected())
        {   meal = true;
            details = details + "\nMeal included";}
        if(wifiCB.isSelected())
        {   wifi = true;
            details = details + "\nWifi included";}
        if(screenCB.isSelected())
        {   screen = true;
            details = details + "\nScreen available";}
        if(vipSeatCB.isSelected())
        {   VIP = true;
            details = details + "\nSeating in VIP area";}
        details = details + "\n" + createID();
                this.showTicketLabel.setText(details);
        }
    }
    
    public void checkButton() throws FileNotFoundException{
        String cl=null;
       if(this.ticketTypeTG.getSelectedToggle().equals(firstClassRB))
        {   int newID = Integer.parseInt(createID());
           ticket = new FirstClassTicket(FirstClassTicket.extraCost,newID,firstClassPrice,Ticket.VAT,screen,meal,wifi,VIP);
        }
       else if(this.ticketTypeTG.getSelectedToggle().equals(economyRB))
        {  int newID = Integer.parseInt(createID());
           ticket = new EconomyTicket(newID,economyClassPrice,Ticket.VAT,screen,meal,wifi,VIP);
        }
        System.out.print("I'm inside the method");
        if(this.ticketTypeTG.getSelectedToggle().equals(firstClassRB))
        { cl = "FirstClass"; 
        
        }
        else if(this.ticketTypeTG.getSelectedToggle().equals(economyRB))
        { cl= "Economy";
        }
        
        int fID= Integer.parseInt(flightList.getSelectionModel().getSelectedItem().toString());
        
        
       if(ticket.checkAvailability(fID, cl) == false)
       {
           
        System.out.print("I'm inside the if");
        
           Alert alert = new Alert(AlertType.INFORMATION);
     alert.setTitle("Message Here...");
     alert.setHeaderText("Ticket booking");
     alert.setContentText("NO AVAILABLE SEATS");
     alert.show();
       }
       else{
           System.out.print("I'm inside the if");
           
           Alert alert = new Alert(AlertType.INFORMATION);
     alert.setTitle("Message Here...");
     alert.setHeaderText("Ticket booking");
     alert.setContentText("AVAILABLE SEATS");
     alert.show();
           
       }
        
    }
           
        
    
    public void bookTicketButton() throws IOException{
        if(passengerTG.getSelectedToggle().equals(registerP)){
        File x = new File("Passengers.txt");
        BufferedWriter bw = null;
        bw=new BufferedWriter(new FileWriter("Passengers.txt", true));
       
       
    Passenger newPassenger = new Passenger(Integer.parseInt(passengerIDText.getText()),
                                   nameText.getText(),
                                   numberText.getText(),
                                   genderCmb.getSelectionModel().getSelectedItem().toString(),
                                   emailText.getText());
    
    bw.newLine();
     bw.write(""+passengerIDText.getText() + "\t" +
              nameText.getText() + "\t"+
              numberText.getText() + "\t"+
              genderCmb.getSelectionModel().getSelectedItem() + "\t" +
              emailText.getText() + "\t");
     
     bw.close();
     
     if(mealCB.isSelected())
        {   meal = true;}
        if(wifiCB.isSelected())
        {   wifi = true;
           }
        if(screenCB.isSelected())
        {   screen = true;
           }
        if(vipSeatCB.isSelected())
        {   VIP = true;
            }
        String C;
        if(ticketTypeTG.getSelectedToggle().equals(firstClassRB))
        {C = "FirstClass";}
        else 
            C="Economy";
    /*int fID= Integer.parseInt(flightList.getSelectionModel().getSelectedItem().toString());
    String cl = ticketTypeTG.getSelectedToggle().toString();
     System.out.println(ticket.checkAvailability(fID, cl));
     if(ticket.checkAvailability(fID, cl) == true)
     */File y = new File("Bookings.txt");
        BufferedWriter b = null;
        b=new BufferedWriter(new FileWriter("Bookings.txt", true));
       
    b.newLine();
     b.write(""+passengerIDText.getText() + "\t" +
               flightList.getSelectionModel().getSelectedItem() + "\t" +
               C + "\t" +
              nameText.getText() + "\t"+
              genderCmb.getSelectionModel().getSelectedItem() + "\t" +
              meal + "\t" + wifi + "\t" + screen + "\t" + VIP + "\t");
     
     b.close();
     Alert alert = new Alert(AlertType.INFORMATION);
     alert.setTitle("Message Here...");
     alert.setHeaderText("Ticket booking");
     alert.setContentText("The ticket booking has been saved");
     alert.show();
    
        }
        else if((passengerTG.getSelectedToggle().equals(existingP))){
            if(mealCB.isSelected())
        {   meal = true;}
        if(wifiCB.isSelected())
        {   wifi = true;
           }
        if(screenCB.isSelected())
        {   screen = true;
           }
        if(vipSeatCB.isSelected())
        {   VIP = true;
            }
        String C;
        if(ticketTypeTG.getSelectedToggle().equals(firstClassRB))
        {C = "FirstClass";}
        else 
            C="Economy";
        File file = new File("Passengers.txt");
            Scanner scan = new Scanner(file);
            Passenger p = null;
            int id=0;
            String name = null;
            String gender= null;
            String number=null;
            String email=null;
            String z=null;
            while(scan.hasNext()){
                
                 id = scan.nextInt();
                 name =scan.next()+ " " + scan.next();
                 z= id + " " + name;
                 number=scan.next();
                 gender=scan.next();
                 email=scan.next();
                
                if(passengerList.getSelectionModel().getSelectedItem().equals(z)){
                    p=new Passenger(id,name,number,gender,email);
                    break;
                }
                System.out.println(name);
                //passengerList.getItems().add(name);
                
                scan.nextLine();
            }
        
        File y = new File("Bookings.txt");
        BufferedWriter b = null;
        b=new BufferedWriter(new FileWriter("Bookings.txt", true));
       
    b.newLine();
     b.write(""+ p.getPassengerID() + "\t" +
               flightList.getSelectionModel().getSelectedItem() + "\t" +
               C + "\t" +
              p.getName() + "\t"+
              p.getGender() + "\t" +
              meal + "\t" + wifi + "\t" + screen + "\t" + VIP + "\t");
     
     b.close();
     Alert alert = new Alert(AlertType.INFORMATION);
     alert.setTitle("Message Here...");
     alert.setHeaderText("Ticket booking");
     alert.setContentText("The ticket booking has been saved");
     alert.show();
        }    
    }
    public void calcTicketPriceButton() throws FileNotFoundException{
        
        File x = new File("FlightPrices.txt");
        Scanner s = new Scanner(x);
        
        while(s.hasNext()){
            int id = s.nextInt();
            firstClassPrice = s.nextInt();
            economyClassPrice = s.nextInt();
            int selectedID = Integer.parseInt(flightList.getSelectionModel().getSelectedItem().toString());
            if(id == selectedID ){
                System.out.println("First class price is : "+firstClassPrice + "\n" +
                                    "Economy Ticket is:" + economyClassPrice);
                break;
            }
            
        }
        
        String details = "\n\nTotal Price:";
        double price = 0;
        
       
        if(this.ticketTypeTG.getSelectedToggle().equals(firstClassRB))
        {   int newID = Integer.parseInt(createID());
           ticket = new FirstClassTicket(FirstClassTicket.extraCost,newID,firstClassPrice,Ticket.VAT,screen,meal,wifi,VIP);
           price = ticket.calcPrice();
           details = details + price + "";
        }else if(this.ticketTypeTG.getSelectedToggle().equals(economyRB))
        {  int newID = Integer.parseInt(createID());
           ticket = new EconomyTicket(newID,economyClassPrice,Ticket.VAT,screen,meal,wifi,VIP);
           price = ticket.calcPrice();
           details = details + price + "";}
        System.out.println("Price: "+ ticket.calcPrice());
        this.showTicketPriceLabel.setText(details);
    }
    
    private static AtomicLong idCounter = new AtomicLong();
    public static String createID(){
        return String.valueOf(idCounter.getAndIncrement());
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            genderCmb.getItems().addAll("Male","Female");
            File x = new File("FlightPrices.txt");
            Scanner s = new Scanner(x);
            
            while(s.hasNext()){
                int id = s.nextInt();
                System.out.println(id);
                flightList.getItems().add(id);
                
                s.nextLine();
            }
            
            
            File file = new File("Passengers.txt");
            Scanner scan = new Scanner(file);
            
            while(scan.hasNext()){
                int id = scan.nextInt();
                String name = Integer.toString(id)+ " " + scan.next()+ " " +scan.next();
                System.out.println(name);
                passengerList.getItems().add(name);
                
                scan.nextLine();
            }
            
            passengerTG = new ToggleGroup();
            this.existingP.setToggleGroup(passengerTG);
            this.registerP.setToggleGroup(passengerTG);
            
            
            ticketTypeTG = new ToggleGroup();
            this.firstClassRB.setToggleGroup(ticketTypeTG);
            this.economyRB.setToggleGroup(ticketTypeTG);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TicketBookingViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }    
    
}
