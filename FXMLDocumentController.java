/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flightproj1;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SelectionMode;
//import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author Reem
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private TableView<Flight> flightTable;
    @FXML 
    private TableColumn<Flight, String> colFlight_ID;
    @FXML 
    private TableColumn<Flight, String> colD_Airport;
    @FXML 
    private TableColumn<Flight, String> colA_Airport;
    @FXML 
    private TableColumn<Flight, LocalDate> colD_Date;
    @FXML 
    private TableColumn<Flight, LocalDate> colA_Date;
    @FXML
    private TableColumn<Flight, LocalTime> colD_Time;
    @FXML
    private TableColumn<Flight, LocalTime> colA_Time;
        
    @FXML
    private TextField flightIDText;
    @FXML
    private TextField D_AirportText;
    @FXML
    private TextField A_AirportText;
    @FXML
    private DatePicker D_DateText;
    @FXML
    private DatePicker A_DateText;
    @FXML
    private TextField D_TimeText;
    @FXML
    private TextField A_TimeText;
    
    
    
        
    public void bookTicketView(ActionEvent event) throws IOException{
       Parent bookTViewParent = FXMLLoader.load(getClass().getResource("TicketBookingView.fxml")); 
       Scene bookTViewScene = new Scene(bookTViewParent);
       
       Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
       window.setScene(bookTViewScene);
       window.show();
       Ticket ticket;
       
       
    }
    
    public void addFlightButton() throws FileNotFoundException, IOException{
        File x = new File("Flights.txt");
        BufferedWriter bw = null;
        bw=new BufferedWriter(new FileWriter("Flights.txt", true));
       
    Flight newFlight = new Flight(Integer.parseInt(flightIDText.getText()),
                                   D_AirportText.getText(),
                                   A_AirportText.getText(),
                                   D_DateText.getValue(),
                                   A_DateText.getValue(),
                                   LocalTime.parse(D_TimeText.getText()),
                                   LocalTime.parse(A_TimeText.getText()));
    
    bw.newLine();
     bw.write(""+flightIDText.getText() + "\t" +
              D_AirportText.getText() + "\t"+
              A_AirportText.getText() + "\t"+
              D_DateText.getValue() + "\t" +
              A_DateText.getValue() + "\t" +
              D_TimeText.getText() + "\t" +
              A_TimeText.getText() + "\t");
     
     bw.close();
     
    
    flightTable.getItems().add(newFlight);   
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        colFlight_ID.setCellValueFactory(new PropertyValueFactory<Flight, String>("flightID"));
        colD_Airport.setCellValueFactory(new PropertyValueFactory<Flight, String>("departureAirport"));
        colA_Airport.setCellValueFactory(new PropertyValueFactory<Flight, String> ("arrivalAirport"));
        colD_Date.setCellValueFactory(new PropertyValueFactory<Flight, LocalDate> ("departureDate"));
        colA_Date.setCellValueFactory(new PropertyValueFactory<Flight, LocalDate> ("arrivalDate"));
        colD_Time.setCellValueFactory(new PropertyValueFactory<Flight, LocalTime> ("departureTime"));
        colA_Time.setCellValueFactory(new PropertyValueFactory<Flight, LocalTime> ("arrivalTime"));
        
        try {
            flightTable.setItems(getFlights());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        flightTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
       
       } 
    
    public void deleteFlightButton() throws FileNotFoundException, IOException{
        ObservableList<Flight> selectedRows, allFlights;
        allFlights = flightTable.getItems();
        File x = new File("Flights.txt");
        File y = new File("temp.txt");
        Scanner s = new Scanner(x);
        PrintWriter pw = new PrintWriter(y);
        
        selectedRows = flightTable.getSelectionModel().getSelectedItems();
        
        for(Flight flight: selectedRows)
        {
            while(s.hasNextLine()) {
    String z = null;
           z = s.nextLine().trim();
           System.out.println(z);
    if(!z.equals(flight.toString())) 
    pw.println(z);
   }s.close();
    x.delete(); 
    pw.close();
    y.renameTo(new File("Flights.txt"));

           System.out.println(flight.toString());
            allFlights.remove(flight);
            
        }
        
    }
    public ObservableList<Flight> getFlights() throws FileNotFoundException{
        ObservableList<Flight> flights = FXCollections.observableArrayList();
        File x = new File("Flights.txt");
        Scanner s = new Scanner(x);
        while (s.hasNext()){
        int id = s.nextInt();
        String D_Airport = s.next();
        String A_Airport = s.next();
        String D_Date = s.next();
        String A_Date = s.next();
        String D_Time = s.next();
        String A_Time = s.next();
        flights.add(new Flight(id,D_Airport,A_Airport,LocalDate.parse(A_Date),LocalDate.parse(D_Date),LocalTime.parse(A_Time),LocalTime.parse(D_Time)));
        }
       return flights;
      
        
    
    
    } }   

   
    

