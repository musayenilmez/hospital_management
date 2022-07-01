/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package methods;

import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 *
 * @author leand
 */
public class Patient {

    private String pps, name, surname, phone, city, email;
    private String line;
    private Read_Write re_wr = new Read_Write();
    private LinkedList<String> patients;

    /**
     *
     */
    public Patient() {
    }

    /**
     *
     * @param pps
     * @param name
     * @param surname
     * @param phone
     * @param city
     * @param email
     * @param line
     * @param patients
     */
    public Patient(String pps, String name, String surname, String phone,
            String city, String email, String line, LinkedList patients) {
        this.pps = pps;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.city = city;
        this.email = email;
        this.line = line;
        this.patients = patients;
    }

    /**
     *
     * @return
     */
    public String getPps() {
        return pps;
    }

    /**
     *
     * @param pps
     */
    public void setPps(String pps) {
        this.pps = pps;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public String getSurname() {
        return surname;
    }

    /**
     *
     * @param surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     *
     * @return
     */
    public String getPhone() {
        return phone;
    }

    /**
     *
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     *
     * @return
     */
    public String getCity() {
        return city;
    }

    /**
     *
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     */
    public String getLine() {
        return line;
    }

    /**
     *
     * @param line
     */
    public void setLine(String line) {
        this.line = line;
    }

    /**
     *
     * @return
     */
    public LinkedList<String> getPatients() {
        return patients;
    }

    /**
     *
     * @param patients
     */
    public void setPatients(LinkedList<String> patients) {
        this.patients = patients;
    }

    /**
     *
     * @param pps
     * @param name
     * @param surname
     * @param phone
     * @param city
     * @param email
     *
     * Method to save a new patient into the system
     */
    public void savePatient(String pps, String name, String surname,
            String phone, String city, String email) {

        this.setPps(pps);
        this.setName(name);
        this.setSurname(surname);
        this.setPhone(phone);
        this.setCity(city);
        this.setEmail(email);

        // Variables
        String details = pps + " " + name + " " + surname + " " + phone
                + " " + city + " " + email + "\n";
        re_wr.setaList(new ArrayList<>());
        re_wr.setFileName("Patient_List.txt");
        //Adding the details from the new patient into an Array List
        re_wr.getaList().add(details);
        // Wrinting the Array List into the system

        if (pps.isEmpty() & name.isEmpty()) {
            JOptionPane.showMessageDialog(null, "<html><font size='5' "
                    + "color='blue'> PPS Number and Name must be inserted!");
        } else {
            try {
                FileWriter fw = new FileWriter(re_wr.getFileName(), true);
                try (Writer output = new BufferedWriter(fw)) {
                    int ar = re_wr.getaList().size();
                    for (int i = 0; i < ar; i++) {
                        output.write(re_wr.getaList().get(i) + "\n");
                    }
                    JOptionPane.showMessageDialog(null, "<html><font size='5' "
                            + "color='blue'> Patient Saved Successfully");
                }
            } catch (HeadlessException | IOException e) {
                System.out.println(e);
            }
        }
    }

    /**
     *
     * @param ppsNumber
     * @param filename
     * @param txt_area
     *
     * Update patient's information
     *
     * A Linked list is called and all the information show up Then, any
     * alteration can be done to any patient's information
     */
    public void updatePatient(String ppsNumber, String filename, JTextArea txt_area) {

        this.setPatients(new LinkedList<>());
        re_wr.setFileName(filename);
        //read the document first
        try {
            BufferedReader br = new BufferedReader(new FileReader(re_wr.getFileName()));
            while ((this.line = br.readLine()) != null) {
                this.getPatients().add(this.getLine());
            }

            JOptionPane.showMessageDialog(null,
                    "<html><font size='14' color='blue'>SAVED");
            // Write down the new informations into a LinkedList
            try {
                FileWriter fw = new FileWriter(re_wr.getFileName());
                try (Writer wt = new BufferedWriter(fw)) {
                    wt.write(txt_area.getText() + "\n");
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        } catch (HeadlessException | IOException | NumberFormatException e) {
            System.out.println(e);
        }
    }
}
