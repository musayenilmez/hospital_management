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
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;

/**
 *
 * @author leand
 */
public class Read_Write {

    private String fileName, line;
    private ArrayList<String> aList;
    private LinkedList<String> tickets;

    /**
     *
     */
    public Read_Write() {
    }

    /**
     *
     * @param fileName
     * @param line
     * @param aList
     * @param tickets
     */
    public Read_Write(String fileName, String line,
            ArrayList<String> aList, LinkedList<String> tickets) {
        this.fileName = fileName;
        this.line = line;
        this.aList = aList;
        this.tickets = tickets;
    }

    /**
     *
     * @return
     */
    public String getFileName() {
        return fileName;
    }

    /**
     *
     * @param fileName
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
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
    public ArrayList<String> getaList() {
        return aList;
    }

    /**
     *
     * @param aList
     */
    public void setaList(ArrayList<String> aList) {
        this.aList = aList;
    }

    /**
     *
     * @return
     */
    public LinkedList<String> getTickets() {
        return tickets;
    }

    /**
     *
     * @param tickets
     */
    public void setTickets(LinkedList<String> tickets) {
        this.tickets = tickets;
    }

    /**
     *
     * @param ppsNumber
     * @param filename
     * @param txt_area
     *
     * Its able to search a value into a Array List
     */
    public void searchArray(String ppsNumber, String filename,
            JTextArea txt_area) {

        this.setaList(new ArrayList<>());
        this.setFileName(filename);

        txt_area.setText("");

        // Reading the txt file
        try {
            try (BufferedReader output
                    = new BufferedReader(new FileReader(filename))) {
                if (!output.ready()) {
                    throw new IOException();
                }
                while ((this.line = output.readLine()) != null) {
                    this.getaList().add(this.getLine());
                    // If the String to be searched contains in the txt file
                    // prints out the whole line
                    if (this.getLine().contains(ppsNumber) == true) {
                        txt_area.append(this.getLine() + "\n");
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     *
     * @param ppsNumber
     * @param filename
     * @param txt_area
     *
     * Its able to search a PPS Number into a LinkedList
     */
    public void searchLinked(String ppsNumber, String filename,
            JTextArea txt_area) {

        this.setTickets(new LinkedList<>());
        this.setFileName(filename);

        // cleaning the JTextArea has the button is pressed
        txt_area.setText("");

        // Reading the txt file
        try {
            try (BufferedReader output
                    = new BufferedReader(new FileReader(filename))) {
                if (!output.ready()) {
                    throw new IOException();
                }
                while ((this.line = output.readLine()) != null) {
                    this.getTickets().add(this.getLine());
                    // If the String to be searched contains in the txt file
                    // prints out the whole line
                    if (this.getLine().contains(ppsNumber) == true) {
                        txt_area.append(String.format("%11d %25s",
                                (this.getTickets().indexOf(this.getLine()) + 1),
                                this.getLine()) + "\n");
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     *
     * @param patient
     * @param filename
     * @param position
     *
     * Generate a new ticket into the LinkedList
     *
     * If the position into the JSpinner is equal to zero a new ticket is added
     * at the end at the LinkedList
     *
     * Else a new ticket is added according to the JSpinner position
     */
    public void ticketGenerator(JTextArea patient, String filename,
            JSpinner position) {

        this.setTickets(new LinkedList<>());
        this.setFileName(filename);

        try {
            BufferedReader br = new BufferedReader(new FileReader(getFileName()));

            while ((this.line = br.readLine()) != null) {
                this.getTickets().add(this.getLine());
            }
            //Checking if the JSpinner is equal to zero
            //to add a new ticket into the list (at the end)
            if ((int) position.getValue() == 0) {
                this.getTickets().add(patient.getText(1, 20));
                JOptionPane.showMessageDialog(null,
                        "<html><font size='14' color='blue'>Ticket N. "
                        + getTickets().size());
                //Checking if the JSpinner is different to zero
                //to add a new ticket into the list (according to the JSpinner)
            } else {
                this.getTickets().add(((int) position.getValue() - 1),
                        patient.getText(1, 20));
                JOptionPane.showMessageDialog(null,
                        "<html><font size='14' color='blue'>Ticket N. "
                        + ((int) position.getValue()));
                position.setValue(0);
            }
            try {
                FileWriter fw = new FileWriter(this.getFileName());
                try (Writer wt = new BufferedWriter(fw)) {
                    int ar = this.getTickets().size();
                    for (int i = 0; i < ar; i++) {
                        wt.write(this.getTickets().get(i) + "\n");
                    }
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        } catch (HeadlessException | IOException | BadLocationException e) {
            System.out.println(e);
        }
    }

    /**
     *
     * @param patient
     * @param filename
     *
     * Adding a new ticket into the LinkedList
     *
     * Adding it at the very first position into the LinkedList, as priority
     */
    public void ticketPriority(JTextArea patient, String filename) {

        this.setTickets(new LinkedList<>());
        this.setFileName(filename);

        try {
            BufferedReader br = new BufferedReader(new FileReader(getFileName()));
            while ((this.line = br.readLine()) != null) {
                this.getTickets().add(this.getLine());
            }

            this.getTickets().addFirst(patient.getText(1, 20));
            JOptionPane.showMessageDialog(null,
                    "<html><font size='14' color='blue'>Ticket N. 1");

            try {
                FileWriter fw = new FileWriter(getFileName());
                try (Writer wt = new BufferedWriter(fw)) {
                    int ar = this.getTickets().size();
                    for (int i = 0; i < ar; i++) {
                        wt.write(this.getTickets().get(i) + "\n");
                    }
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        } catch (HeadlessException | IOException | BadLocationException e) {
            System.out.println(e);
        }
    }

    /**
     *
     * @param filename
     * @param field
     *
     * Delete a ticket from the LinkedList The ticket number (index) has to be
     * indicate to be able to delete it
     */
    public void delete(String filename, JTextField field) {

        this.setTickets(new LinkedList<>());
        this.setFileName(filename);

        try {
            BufferedReader br = new BufferedReader(new FileReader(this.getFileName()));
            while ((this.line = br.readLine()) != null) {
                this.getTickets().add(this.getLine());
            }
            this.getTickets().remove(Integer.parseInt(field.getText()) - 1);
            JOptionPane.showMessageDialog(null,
                    "<html><font size='14' color='blue'>Ticket Deleted");
            field.setText(" ");

            try {
                FileWriter fw = new FileWriter(this.getFileName());
                try (Writer wt = new BufferedWriter(fw)) {
                    int ar = this.getTickets().size();
                    for (int i = 0; i < ar; i++) {
                        wt.write(this.getTickets().get(i) + "\n");
                    }
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        } catch (HeadlessException | IOException | NumberFormatException e) {
            System.out.println(e);
        }
    }

    /**
     *
     * @param filename
     * @param txt_area
     *
     * Open the ReadMe file
     */
    public void readMe(String filename, JTextArea txt_area) {

        this.setaList(new ArrayList<>());
        this.setFileName(filename);

        try {
            try (BufferedReader br
                    = new BufferedReader(new FileReader(this.getFileName()))) {
                while ((this.line = br.readLine()) != null) {
                    this.getaList().add(this.getLine());
                    txt_area.append(this.getLine() + "\n");
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
