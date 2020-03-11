/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library_system;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import static library_system.login.jLabel2log;
import static library_system.login.jLabel3log;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Cris Mateo Uriarte
 */
public class purchase extends javax.swing.JFrame {
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;
    /**
     * Creates new form purchase
     */
    public purchase() {
        initComponents();
        menu_title18.setText(login.txt_namelog.getText());
        menu_title36.setText(login.txt_namelog.getText());
        menu_title43.setText(login.txt_namelog.getText());
        menu_title55.setText(login.txt_namelog.getText());
        menu_title71.setText(login.txt_namelog.getText());
        menu_title85.setText(login.txt_namelog.getText());
        menu_title97.setText(login.txt_namelog.getText());
        menu_title109.setText(login.txt_namelog.getText());
        menu_title122.setText(login.txt_namelog.getText());
        menu_title133.setText(login.txt_namelog.getText());
        jLabel1.setText(Main.jLabel78.getText());
        panel1.setVisible(true);
        conn = (Connection) MySqlConnect.ConnectDB();
        CurrentDate();
        all_ref();
        classif_list();
        //pub_list();
        book_list();
    }
    
    public void all_ref(){
    table_ref();
        phdash();
        pocount();
        po();
        ifelse();
        paydash();
        payment_ref();
        delivery_ref();
        deldash();
        summary_ref();
        delsum();
        col_ref();
        delcol();
        dam_ref();
        colcount();
        damcol();
        damcount();
        overd_ref();
        brrd_ref();
        brrdcount();
        boh_ref();
        bohcount();
        bloan_ref();
        bloancount();
    }
    
    public void ifelse(){
        switch (jLabel1.getText()) {
            case "purchase":
                print.setVisible(true);
                print2.setVisible(false);
                print3.setVisible(false);
                print4.setVisible(false);
                print5.setVisible(false);
                print6.setVisible(false);
                print7.setVisible(false);
                print8.setVisible(false);
                print9.setVisible(false);
                print10.setVisible(false);
                starter.setVisible(false);
                break;
            case "payment":
                print.setVisible(false);
                print2.setVisible(true);
                print3.setVisible(false);
                print4.setVisible(false);
                print5.setVisible(false);
                print6.setVisible(false);
                print7.setVisible(false);
                print9.setVisible(false);
                print10.setVisible(false);
                starter.setVisible(false);
                break;
            case "delivery":
                print.setVisible(false);
                print2.setVisible(false);
                print3.setVisible(true);
                print4.setVisible(false);
                print5.setVisible(false);
                print6.setVisible(false);
                print7.setVisible(false);
                print8.setVisible(false);
                print9.setVisible(false);
                print10.setVisible(false);
                starter.setVisible(false);
                break;
            case "summary":
                print.setVisible(false);
                print2.setVisible(false);
                print3.setVisible(false);
                print4.setVisible(true);
                print5.setVisible(false);
                print6.setVisible(false);
                print7.setVisible(false);
                print8.setVisible(false);
                print9.setVisible(false);
                print10.setVisible(false);
                starter.setVisible(false);
                break;
            case "collection":
                print.setVisible(false);
                print2.setVisible(false);
                print3.setVisible(false);
                print4.setVisible(false);
                print5.setVisible(true);
                print6.setVisible(false);
                print7.setVisible(false);
                print8.setVisible(false);
                print9.setVisible(false);
                print10.setVisible(false);
                starter.setVisible(false);
                break;
            case "stockout":
                print.setVisible(false);
                print2.setVisible(false);
                print3.setVisible(false);
                print4.setVisible(false);
                print5.setVisible(false);
                print6.setVisible(true);
                print7.setVisible(false);
                print8.setVisible(false);
                print9.setVisible(false);
                print10.setVisible(false);
                starter.setVisible(false);
                break;
            case "overdue":
                print.setVisible(false);
                print2.setVisible(false);
                print3.setVisible(false);
                print4.setVisible(false);
                print5.setVisible(false);
                print6.setVisible(false);
                print7.setVisible(true);
                print8.setVisible(false);
                print9.setVisible(false);
                print10.setVisible(false);
                starter.setVisible(false);
                break;
            case "borrowed":
                print.setVisible(false);
                print2.setVisible(false);
                print3.setVisible(false);
                print4.setVisible(false);
                print5.setVisible(false);
                print6.setVisible(false);
                print7.setVisible(false);
                print8.setVisible(true);
                print9.setVisible(false);
                print10.setVisible(false);
                starter.setVisible(false);
                break;
            case "boh":
                print.setVisible(false);
                print2.setVisible(false);
                print3.setVisible(false);
                print4.setVisible(false);
                print5.setVisible(false);
                print6.setVisible(false);
                print7.setVisible(false);
                print8.setVisible(false);
                print9.setVisible(true);
                print10.setVisible(false);
                starter.setVisible(false);
                break;
            case "bookloan":
                print.setVisible(false);
                print2.setVisible(false);
                print3.setVisible(false);
                print4.setVisible(false);
                print5.setVisible(false);
                print6.setVisible(false);
                print7.setVisible(false);
                print8.setVisible(false);
                print9.setVisible(false);
                print10.setVisible(true);
                starter.setVisible(false);
                break;
            default:
        }
    }
    public void classif_list(){
        jComboBox2.removeAllItems();
        jComboBox6.removeAllItems();
    String sqll = "select * from classification_tbl";
    try{
        pst= conn.prepareStatement(sqll);
        rs = pst.executeQuery();
        while(rs.next()){
            jComboBox2.addItem(rs.getString("Classname"));
            jComboBox6.addItem(rs.getString("Classname"));
        }
    }catch (Exception e) {
    }
    }
//    public void pub_list(){
//        jComboBox5.removeAllItems();
//    String sqll = "select * from publisher_tbl";
//    try{
//        pst= conn.prepareStatement(sqll);
//        rs = pst.executeQuery();
//        while(rs.next()){
//            jComboBox5.addItem(rs.getString("Publisher"));
//        }
//    }catch (Exception e) {
//    }
//    }
    public void book_list(){
        jComboBox4.removeAllItems();
    String sqll = "select * from stockin_tbl";
    try{
        pst= conn.prepareStatement(sqll);
        rs = pst.executeQuery();
        while(rs.next()){
            jComboBox4.addItem(rs.getString("Book_title"));
        }
    }catch (Exception e) {
    }
    }
    
    public void table_ref(){
        try {
            String sql = "SELECT  Purchase_Date, Request_Date, Supplier, Book_Title, "
                    + "Book_Author, Classification, Price, Quantity, Total"
                    + " FROM order_tbl ORDER BY No DESC";
            pst = (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();

            jTable7.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    public void payment_ref(){
        try {
            String sql = "SELECT Book_Title, "
                    + "Borrower, Status, Quantity, Price, Date"
                    + " FROM sales_tbl ORDER BY ID DESC";
            pst = (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();

            jTable8.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    public void delivery_ref(){
        try {
            String sql = "SELECT Delivery_No, Book_title, Author, Classification, Quantity, Price, Purchase_No,"
                    + " Purchase_Date, Delivery_Date FROM delivery_tbl ORDER BY ID DESC";
            pst = (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();

            jTable9.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    public void summary_ref(){
        try {
            String sql = "SELECT ISBN_No, Book_title, Classification, On_Hand, Borrowed, Damage,"
                    + " Total_Holding FROM holding_tbl ORDER BY ID DESC";
            pst = (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();

            jTable10.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    public void col_ref(){
        try {
            String sql = "SELECT * FROM borrowed_tbl WHERE Payable NOT IN ('0','0.00')";
            pst = (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();

            jTable11.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    public void dam_ref(){
        try {
            String sql = "SELECT * FROM damage_tbl ORDER BY ID ASC";
            pst = (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();

            jTable12.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
     public void overd_ref(){
        try{
            String sql = "SELECT *"
                    + " FROM borrowed_tbl WHERE "
                    + "Remarks like '"+"Borrowed"+"' && Payable NOT IN ('0','0.00')";

            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);

            rs = (ResultSet) pst.executeQuery();
            jTable13.setModel(DbUtils.resultSetToTableModel(rs));
            }
            catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, ex);
            }
    }
     public void bloan_ref(){
        try{
            String sql = "SELECT *"
                    + " FROM borrowed_tbl ORDER BY Book_title ASC";

            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);

            rs = (ResultSet) pst.executeQuery();
            jTable16.setModel(DbUtils.resultSetToTableModel(rs));
            }
            catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, ex);
            }
    }
    public void brrd_ref(){
        try{
            String sql = "SELECT Full_Name, Status, Book_title, Classification, Borrowed_Date, Encoder, Date"
                    + " FROM brrdlog_tbl ORDER BY ID DESC";

            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);

            rs = (ResultSet) pst.executeQuery();
            jTable14.setModel(DbUtils.resultSetToTableModel(rs));
            }
            catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, ex);
            }
    }
    public void boh_ref(){
        try{
            String sql = "SELECT ISBN_No, Book_title, Author, Quantity, Call_Number, Classification, Publisher, "
                    + "Edition, Copy_Right_Year, Price, Date_Arrival"
                    + " FROM stockin_tbl ORDER BY Book_title ASC";

            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);

            rs = (ResultSet) pst.executeQuery();
            jTable15.setModel(DbUtils.resultSetToTableModel(rs));
            }
            catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, ex);
            }
    }
     
    public void payment(){
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            try {
                String sql = "select Book_Title, "
                    + "Borrower, Status, Quantity, Price, Date"
                    + " from  sales_tbl where Date "
                        + "between '" + (String) sdf.format(jLabel2.getText()) + "' "
                        + "and '" + (String) sdf.format(jLabel3.getText()) + "' ";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                jTable8.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            }
    }
    public void phdash (){
        try{
        String sql = "Select sum(Quantity), sum(Total) from Order_tbl";
        pst=conn.prepareStatement(sql);
        rs=pst.executeQuery();
        if(rs.next()){
        String sum=rs.getString("sum(Quantity)");
        String sum1=rs.getString("sum(Total)");
        menu_title14.setText(sum);
        menu_title16.setText(sum1);
        }
        }
        catch (Exception e){
        JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void paydash (){
        try{
        String sql = "Select sum(Quantity), sum(Price) from sales_tbl";
        pst=conn.prepareStatement(sql);
        rs=pst.executeQuery();
        if(rs.next()){
        String sum=rs.getString("sum(Quantity)");
        String sum1=rs.getString("sum(Price)");
        menu_title29.setText(sum);
        menu_title33.setText(sum1);
        }
        }
        catch (Exception e){
        JOptionPane.showMessageDialog(null, e);
        }
    }
    public void paydashx(){
        try{
             SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        String sql = "Select sum(Quantity), sum(Price) from sales_tbl where Date "
                        + "between '" + (String) sdf.format(jDateChooser2.getDate()) + "' "
                        + "and '" + (String) sdf.format(jDateChooser1.getDate()) + "' "; 
        pst=conn.prepareStatement(sql);
        rs=pst.executeQuery();
        if(rs.next()){
        String sum=rs.getString("sum(Quantity)");
        String sum1=rs.getString("sum(Price)");
        menu_title29.setText(sum);
        menu_title33.setText(sum1);
        }
        }
        catch (Exception e){
        JOptionPane.showMessageDialog(null, e);
        }
    }
    public void deldash (){
        try{
        String sql = "Select sum(Quantity), sum(Price) from delivery_tbl";
        pst=conn.prepareStatement(sql);
        rs=pst.executeQuery();
        if(rs.next()){
        String sum=rs.getString("sum(Quantity)");
        String sum1=rs.getString("sum(Price)");
        menu_title38.setText(sum);
        menu_title40.setText(sum1);
        }
        }
        catch (Exception e){
        JOptionPane.showMessageDialog(null, e);
        }
    }
    public void deldashx (){
        
        try{
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        String sql = "Select sum(Quantity), sum(Price) from delivery_tbl where Delivery_Date "
                        + "between '" + (String) sdf.format(jDateChooser3.getDate()) + "' "
                        + "and '" + (String) sdf.format(jDateChooser4.getDate()) + "' ";
        pst=conn.prepareStatement(sql);
        rs=pst.executeQuery();
        if(rs.next()){
        String sum=rs.getString("sum(Quantity)");
        String sum1=rs.getString("sum(Price)");
        menu_title38.setText(sum);
        menu_title40.setText(sum1);
        }
        }
        catch (Exception e){
        JOptionPane.showMessageDialog(null, e);
        }
    }
    public void delsum(){
        try{
        String sql = "Select sum(On_Hand), sum(Borrowed), sum(Damage), sum(Total_Holding) from holding_tbl";
        pst=conn.prepareStatement(sql);
        rs=pst.executeQuery();
        if(rs.next()){
        String sum=rs.getString("sum(On_Hand)");
        String sum1=rs.getString("sum(Borrowed)");
        String sum2=rs.getString("sum(Damage)");
        String sum3=rs.getString("sum(Total_Holding)");
        menu_title52.setText(sum);
        menu_title50.setText(sum1);
        menu_title56.setText(sum2);
        menu_title59.setText(sum3);
        }
        }
        catch (Exception e){
        JOptionPane.showMessageDialog(null, e);
        }
    }
    public void delcol(){
        try{
            
        String sql = "Select sum(Quantity), sum(Payable) from borrowed_tbl WHERE Payable NOT IN ('0','0.00')";
        pst=conn.prepareStatement(sql);
        rs=pst.executeQuery();
        if(rs.next()){
        String sum=rs.getString("sum(Quantity)");
        String sum1=rs.getString("sum(Payable)");
        menu_title66.setText(sum);
        menu_title72.setText(sum1);
        }
        }
        catch (Exception e){
        JOptionPane.showMessageDialog(null, e);
        }
    }
    public void damcol(){
        try{
            
        String sql = "Select sum(Quantity) from damage_tbl";
        pst=conn.prepareStatement(sql);
        rs=pst.executeQuery();
        if(rs.next()){
        String sum=rs.getString("sum(Quantity)");
        menu_title80.setText(sum);
        }
        }
        catch (Exception e){
        JOptionPane.showMessageDialog(null, e);
        }
    }
    public void filterpay() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            try {
                String sql = "select Book_Title, "
                    + "Borrower, Status, Quantity, Price, Date from  sales_tbl where Date "
                        + "between '" + (String) sdf.format(jDateChooser2.getDate()) + "' "
                        + "and '" + (String) sdf.format(jDateChooser1.getDate()) + "' ";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                jTable8.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            }
    }
    
    public void po(){
    try{
        pst = (PreparedStatement) conn.prepareStatement("SELECT Purchase_No FROM order_tbl where No= '" + "1" + "' ");
            rs = pst.executeQuery();
            if(rs.next()){
                String add = rs.getString("Purchase_No");
                menu_title8.setText(add);
            }
    }catch (Exception e){
    
    }
    }
    public void pocount (){//code for cointing table account logs
    int row = jTable7.getRowCount();
        menu_title12.setText(String.valueOf(row));
    }
    public void colcount (){//code for cointing table account logs
    int row = jTable11.getRowCount();
        menu_title68.setText(String.valueOf(row));
    }
    public void damcount (){//code for cointing table account logs
    int row = jTable12.getRowCount();
        menu_title82.setText(String.valueOf(row));
    }
    public void brrdcount (){//code for cointing table account logs
    int row = jTable14.getRowCount();
        menu_title110.setText(String.valueOf(row));
    }
    public void bohcount (){//code for cointing table account logs
    int row = jTable15.getRowCount();
        menu_title123.setText(String.valueOf(row));
    }
    public void bloancount (){//code for cointing table account logs
    int row = jTable16.getRowCount();
        menu_title134.setText(String.valueOf(row));
    }
    public void CurrentDate() {//date and time to toolbar running
        new Timer(0, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Date d = new Date();
                SimpleDateFormat s = new SimpleDateFormat("h:mm aa");
                jLabel2log.setText(s.format(d));

                SimpleDateFormat st = new SimpleDateFormat("MM/dd/yyyy");
                menu_title10.setText(st.format(d));   
                menu_title27.setText(st.format(d));   
                menu_title31.setText(st.format(d));   
                menu_title48.setText(st.format(d));   
                menu_title64.setText(st.format(d));   
                menu_title78.setText(st.format(d));   
                menu_title90.setText(st.format(d));   
                menu_title102.setText(st.format(d));   
                menu_title118.setText(st.format(d));   
                menu_title129.setText(st.format(d));   
            }
        })
                .start();
    } 

    public void printop(){
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setJobName("Job Order" + jLabel3log.getText() + jLabel2log.getText());
        job.setPrintable(new Printable(){
            public int print(Graphics pg,PageFormat pf, int pageNum){
                pf.setOrientation(PageFormat.LANDSCAPE);
                if(pageNum>0){
                    return Printable.NO_SUCH_PAGE;
                }
                Graphics2D g2 = (Graphics2D)pg;
                g2.translate(pf.getImageableX(), pf.getImageableY());
                g2.scale(0.70,0.70);
                print.paint(g2);
                return Printable.PAGE_EXISTS;
            }
        });
        boolean ok = job.printDialog();
        if(ok){
            try {  
                job.print();
            } catch (PrinterException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }  
            this.setVisible(false);
        
    }
    public void printpay(){
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setJobName("Job Order" + jLabel3log.getText() + jLabel2log.getText());
        job.setPrintable(new Printable(){
            public int print(Graphics pg,PageFormat pf, int pageNum){
                pf.setOrientation(PageFormat.LANDSCAPE);
                if(pageNum>0){
                    return Printable.NO_SUCH_PAGE;
                }
                Graphics2D g2 = (Graphics2D)pg;
                g2.translate(pf.getImageableX(), pf.getImageableY());
                g2.scale(0.70,0.70);
                print2.paint(g2);
                return Printable.PAGE_EXISTS;
            }
        });
        boolean ok = job.printDialog();
        if(ok){
            try {  
                job.print();
            } catch (PrinterException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }  
            this.setVisible(false);
        
    }
    public void printdel(){
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setJobName("Job Order" + jLabel3log.getText() + jLabel2log.getText());
        job.setPrintable(new Printable(){
            public int print(Graphics pg,PageFormat pf, int pageNum){
                pf.setOrientation(PageFormat.LANDSCAPE);
                if(pageNum>0){
                    return Printable.NO_SUCH_PAGE;
                }
                Graphics2D g2 = (Graphics2D)pg;
                g2.translate(pf.getImageableX(), pf.getImageableY());
                g2.scale(0.70,0.70);
                print3.paint(g2);
                return Printable.PAGE_EXISTS;
            }
        });
        boolean ok = job.printDialog();
        if(ok){
            try {  
                job.print();
            } catch (PrinterException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }  
            this.setVisible(false);
        
    }
    public void printsum(){
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setJobName("Job Order" + jLabel3log.getText() + jLabel2log.getText());
        job.setPrintable(new Printable(){
            public int print(Graphics pg,PageFormat pf, int pageNum){
                pf.setOrientation(PageFormat.LANDSCAPE);
                if(pageNum>0){
                    return Printable.NO_SUCH_PAGE;
                }
                Graphics2D g2 = (Graphics2D)pg;
                g2.translate(pf.getImageableX(), pf.getImageableY());
                g2.scale(0.70,0.70);
                print4.paint(g2);
                return Printable.PAGE_EXISTS;
            }
        });
        boolean ok = job.printDialog();
        if(ok){
            try {  
                job.print();
            } catch (PrinterException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }  
            this.setVisible(false);
        
    }
    public void printcol(){
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setJobName("Job Order" + jLabel3log.getText() + jLabel2log.getText());
        job.setPrintable(new Printable(){
            public int print(Graphics pg,PageFormat pf, int pageNum){
                pf.setOrientation(PageFormat.LANDSCAPE);
                if(pageNum>0){
                    return Printable.NO_SUCH_PAGE;
                }
                Graphics2D g2 = (Graphics2D)pg;
                g2.translate(pf.getImageableX(), pf.getImageableY());
                g2.scale(0.70,0.70);
                print5.paint(g2);
                return Printable.PAGE_EXISTS;
            }
        });
        boolean ok = job.printDialog();
        if(ok){
            try {  
                job.print();
            } catch (PrinterException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }  
            this.setVisible(false);
        
    }
    public void printdam(){
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setJobName("Job Order" + jLabel3log.getText() + jLabel2log.getText());
        job.setPrintable(new Printable(){
            public int print(Graphics pg,PageFormat pf, int pageNum){
                pf.setOrientation(PageFormat.LANDSCAPE);
                if(pageNum>0){
                    return Printable.NO_SUCH_PAGE;
                }
                Graphics2D g2 = (Graphics2D)pg;
                g2.translate(pf.getImageableX(), pf.getImageableY());
                g2.scale(0.70,0.70);
                print6.paint(g2);
                return Printable.PAGE_EXISTS;
            }
        });
        boolean ok = job.printDialog();
        if(ok){
            try {  
                job.print();
            } catch (PrinterException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }  
            this.setVisible(false);
        
    }
    public void printod(){
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setJobName("Job Order" + jLabel3log.getText() + jLabel2log.getText());
        job.setPrintable(new Printable(){
            public int print(Graphics pg,PageFormat pf, int pageNum){
                pf.setOrientation(PageFormat.LANDSCAPE);
                if(pageNum>0){
                    return Printable.NO_SUCH_PAGE;
                }
                Graphics2D g2 = (Graphics2D)pg;
                g2.translate(pf.getImageableX(), pf.getImageableY());
                g2.scale(0.70,0.70);
                print7.paint(g2);
                return Printable.PAGE_EXISTS;
            }
        });
        boolean ok = job.printDialog();
        if(ok){
            try {  
                job.print();
            } catch (PrinterException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }  
            this.setVisible(false);
        
    }
    public void printbrrd(){
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setJobName("Job Order" + jLabel3log.getText() + jLabel2log.getText());
        job.setPrintable(new Printable(){
            public int print(Graphics pg,PageFormat pf, int pageNum){
                pf.setOrientation(PageFormat.LANDSCAPE);
                if(pageNum>0){
                    return Printable.NO_SUCH_PAGE;
                }
                Graphics2D g2 = (Graphics2D)pg;
                g2.translate(pf.getImageableX(), pf.getImageableY());
                g2.scale(0.70,0.70);
                print8.paint(g2);
                return Printable.PAGE_EXISTS;
            }
        });
        boolean ok = job.printDialog();
        if(ok){
            try {  
                job.print();
            } catch (PrinterException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }  
            this.setVisible(false);
        
    }
    public void printboh(){
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setJobName("Job Order" + jLabel3log.getText() + jLabel2log.getText());
        job.setPrintable(new Printable(){
            public int print(Graphics pg,PageFormat pf, int pageNum){
                pf.setOrientation(PageFormat.LANDSCAPE);
                if(pageNum>0){
                    return Printable.NO_SUCH_PAGE;
                }
                Graphics2D g2 = (Graphics2D)pg;
                g2.translate(pf.getImageableX(), pf.getImageableY());
                g2.scale(0.70,0.70);
                print9.paint(g2);
                return Printable.PAGE_EXISTS;
            }
        });
        boolean ok = job.printDialog();
        if(ok){
            try {  
                job.print();
            } catch (PrinterException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }  
            this.setVisible(false);
        
    }
    public void printbloan(){
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setJobName("Job Order" + jLabel3log.getText() + jLabel2log.getText());
        job.setPrintable(new Printable(){
            public int print(Graphics pg,PageFormat pf, int pageNum){
                pf.setOrientation(PageFormat.LANDSCAPE);
                if(pageNum>0){
                    return Printable.NO_SUCH_PAGE;
                }
                Graphics2D g2 = (Graphics2D)pg;
                g2.translate(pf.getImageableX(), pf.getImageableY());
                g2.scale(0.70,0.70);
                print10.paint(g2);
                return Printable.PAGE_EXISTS;
            }
        });
        boolean ok = job.printDialog();
        if(ok){
            try {  
                job.print();
            } catch (PrinterException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }  
            this.setVisible(false);
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        starter = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        print = new javax.swing.JPanel();
        menu_title3 = new javax.swing.JLabel();
        menu_title4 = new javax.swing.JLabel();
        menu_title5 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        menu_title6 = new javax.swing.JLabel();
        menu_title7 = new javax.swing.JLabel();
        menu_title8 = new javax.swing.JLabel();
        menu_title9 = new javax.swing.JLabel();
        menu_title10 = new javax.swing.JLabel();
        jScrollPane37 = new javax.swing.JScrollPane();
        jTable7 = new javax.swing.JTable();
        menu_title11 = new javax.swing.JLabel();
        menu_title12 = new javax.swing.JLabel();
        menu_title13 = new javax.swing.JLabel();
        menu_title14 = new javax.swing.JLabel();
        menu_title15 = new javax.swing.JLabel();
        menu_title16 = new javax.swing.JLabel();
        menu_title19 = new javax.swing.JLabel();
        menu_title17 = new javax.swing.JLabel();
        menu_title18 = new javax.swing.JLabel();
        print2 = new javax.swing.JPanel();
        panel1 = new java.awt.Panel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        nb_update14 = new javax.swing.JButton();
        jLabel154 = new javax.swing.JLabel();
        jLabel153 = new javax.swing.JLabel();
        menu_title20 = new javax.swing.JLabel();
        menu_title21 = new javax.swing.JLabel();
        menu_title22 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        menu_title23 = new javax.swing.JLabel();
        menu_title27 = new javax.swing.JLabel();
        jScrollPane38 = new javax.swing.JScrollPane();
        jTable8 = new javax.swing.JTable();
        menu_title28 = new javax.swing.JLabel();
        menu_title29 = new javax.swing.JLabel();
        menu_title32 = new javax.swing.JLabel();
        menu_title33 = new javax.swing.JLabel();
        menu_title34 = new javax.swing.JLabel();
        menu_title35 = new javax.swing.JLabel();
        menu_title36 = new javax.swing.JLabel();
        print3 = new javax.swing.JPanel();
        panel2 = new java.awt.Panel();
        jDateChooser3 = new com.toedter.calendar.JDateChooser();
        jDateChooser4 = new com.toedter.calendar.JDateChooser();
        nb_update15 = new javax.swing.JButton();
        jLabel155 = new javax.swing.JLabel();
        jLabel156 = new javax.swing.JLabel();
        menu_title24 = new javax.swing.JLabel();
        menu_title25 = new javax.swing.JLabel();
        menu_title26 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        menu_title30 = new javax.swing.JLabel();
        menu_title31 = new javax.swing.JLabel();
        jScrollPane39 = new javax.swing.JScrollPane();
        jTable9 = new javax.swing.JTable();
        menu_title37 = new javax.swing.JLabel();
        menu_title38 = new javax.swing.JLabel();
        menu_title39 = new javax.swing.JLabel();
        menu_title40 = new javax.swing.JLabel();
        menu_title41 = new javax.swing.JLabel();
        menu_title42 = new javax.swing.JLabel();
        menu_title43 = new javax.swing.JLabel();
        print4 = new javax.swing.JPanel();
        menu_title44 = new javax.swing.JLabel();
        menu_title45 = new javax.swing.JLabel();
        menu_title46 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        menu_title47 = new javax.swing.JLabel();
        menu_title48 = new javax.swing.JLabel();
        jScrollPane40 = new javax.swing.JScrollPane();
        jTable10 = new javax.swing.JTable();
        menu_title49 = new javax.swing.JLabel();
        menu_title50 = new javax.swing.JLabel();
        menu_title51 = new javax.swing.JLabel();
        menu_title52 = new javax.swing.JLabel();
        menu_title53 = new javax.swing.JLabel();
        menu_title54 = new javax.swing.JLabel();
        menu_title55 = new javax.swing.JLabel();
        menu_title56 = new javax.swing.JLabel();
        menu_title57 = new javax.swing.JLabel();
        menu_title58 = new javax.swing.JLabel();
        menu_title59 = new javax.swing.JLabel();
        print5 = new javax.swing.JPanel();
        menu_title60 = new javax.swing.JLabel();
        menu_title61 = new javax.swing.JLabel();
        menu_title62 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        menu_title63 = new javax.swing.JLabel();
        menu_title64 = new javax.swing.JLabel();
        jScrollPane41 = new javax.swing.JScrollPane();
        jTable11 = new javax.swing.JTable();
        menu_title65 = new javax.swing.JLabel();
        menu_title66 = new javax.swing.JLabel();
        menu_title67 = new javax.swing.JLabel();
        menu_title68 = new javax.swing.JLabel();
        menu_title69 = new javax.swing.JLabel();
        menu_title70 = new javax.swing.JLabel();
        menu_title71 = new javax.swing.JLabel();
        menu_title72 = new javax.swing.JLabel();
        menu_title73 = new javax.swing.JLabel();
        print6 = new javax.swing.JPanel();
        menu_title74 = new javax.swing.JLabel();
        menu_title75 = new javax.swing.JLabel();
        menu_title76 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        menu_title77 = new javax.swing.JLabel();
        menu_title78 = new javax.swing.JLabel();
        jScrollPane42 = new javax.swing.JScrollPane();
        jTable12 = new javax.swing.JTable();
        menu_title79 = new javax.swing.JLabel();
        menu_title80 = new javax.swing.JLabel();
        menu_title81 = new javax.swing.JLabel();
        menu_title82 = new javax.swing.JLabel();
        menu_title83 = new javax.swing.JLabel();
        menu_title84 = new javax.swing.JLabel();
        menu_title85 = new javax.swing.JLabel();
        print7 = new javax.swing.JPanel();
        menu_title86 = new javax.swing.JLabel();
        menu_title87 = new javax.swing.JLabel();
        menu_title88 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        menu_title89 = new javax.swing.JLabel();
        menu_title90 = new javax.swing.JLabel();
        jScrollPane43 = new javax.swing.JScrollPane();
        jTable13 = new javax.swing.JTable();
        menu_title91 = new javax.swing.JLabel();
        menu_title92 = new javax.swing.JLabel();
        menu_title93 = new javax.swing.JLabel();
        menu_title94 = new javax.swing.JLabel();
        menu_title95 = new javax.swing.JLabel();
        menu_title96 = new javax.swing.JLabel();
        menu_title97 = new javax.swing.JLabel();
        print8 = new javax.swing.JPanel();
        menu_title98 = new javax.swing.JLabel();
        menu_title99 = new javax.swing.JLabel();
        menu_title100 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        menu_title101 = new javax.swing.JLabel();
        menu_title102 = new javax.swing.JLabel();
        jScrollPane44 = new javax.swing.JScrollPane();
        jTable14 = new javax.swing.JTable();
        menu_title105 = new javax.swing.JLabel();
        menu_title107 = new javax.swing.JLabel();
        menu_title108 = new javax.swing.JLabel();
        menu_title109 = new javax.swing.JLabel();
        menu_title110 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        menu_title106 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        menu_title111 = new javax.swing.JLabel();
        menu_title112 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jDateChooser5 = new com.toedter.calendar.JDateChooser();
        menu_title113 = new javax.swing.JLabel();
        jDateChooser6 = new com.toedter.calendar.JDateChooser();
        menu_title115 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jDateChooser7 = new com.toedter.calendar.JDateChooser();
        jDateChooser8 = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        menu_title116 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        print9 = new javax.swing.JPanel();
        menu_title103 = new javax.swing.JLabel();
        menu_title104 = new javax.swing.JLabel();
        menu_title114 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        menu_title117 = new javax.swing.JLabel();
        menu_title118 = new javax.swing.JLabel();
        jScrollPane45 = new javax.swing.JScrollPane();
        jTable15 = new javax.swing.JTable();
        menu_title119 = new javax.swing.JLabel();
        menu_title120 = new javax.swing.JLabel();
        menu_title121 = new javax.swing.JLabel();
        menu_title122 = new javax.swing.JLabel();
        menu_title123 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jDateChooser10 = new com.toedter.calendar.JDateChooser();
        menu_title128 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jDateChooser12 = new com.toedter.calendar.JDateChooser();
        jLabel9 = new javax.swing.JLabel();
        print10 = new javax.swing.JPanel();
        menu_title124 = new javax.swing.JLabel();
        menu_title125 = new javax.swing.JLabel();
        menu_title126 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        menu_title127 = new javax.swing.JLabel();
        menu_title129 = new javax.swing.JLabel();
        jScrollPane46 = new javax.swing.JScrollPane();
        jTable16 = new javax.swing.JTable();
        menu_title130 = new javax.swing.JLabel();
        menu_title131 = new javax.swing.JLabel();
        menu_title132 = new javax.swing.JLabel();
        menu_title133 = new javax.swing.JLabel();
        menu_title134 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jComboBox4 = new javax.swing.JComboBox<>();
        menu_title138 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        menu_title140 = new javax.swing.JLabel();
        jComboBox6 = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setLayout(new java.awt.CardLayout());

        jLabel1.setText("jLabel1");

        jLabel2.setText("date1");

        jLabel3.setText("date2");

        jLabel4.setText("jLabel4");

        javax.swing.GroupLayout starterLayout = new javax.swing.GroupLayout(starter);
        starter.setLayout(starterLayout);
        starterLayout.setHorizontalGroup(
            starterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(starterLayout.createSequentialGroup()
                .addGap(267, 267, 267)
                .addGroup(starterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(301, Short.MAX_VALUE))
        );
        starterLayout.setVerticalGroup(
            starterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(starterLayout.createSequentialGroup()
                .addGap(202, 202, 202)
                .addComponent(jLabel1)
                .addGap(40, 40, 40)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(34, 34, 34)
                .addComponent(jLabel4)
                .addContainerGap(506, Short.MAX_VALUE))
        );

        jPanel1.add(starter, "card4");

        print.setBackground(new java.awt.Color(255, 255, 255));

        menu_title3.setBackground(new java.awt.Color(96, 96, 96));
        menu_title3.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        menu_title3.setForeground(new java.awt.Color(102, 102, 102));
        menu_title3.setText("CRONASIA");

        menu_title4.setBackground(new java.awt.Color(96, 96, 96));
        menu_title4.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        menu_title4.setForeground(new java.awt.Color(102, 102, 102));
        menu_title4.setText("COLLEGE");

        menu_title5.setBackground(new java.awt.Color(96, 96, 96));
        menu_title5.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title5.setForeground(new java.awt.Color(102, 102, 102));
        menu_title5.setText("General Santos City");

        jLabel78.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cronasia.png"))); // NOI18N

        menu_title6.setBackground(new java.awt.Color(96, 96, 96));
        menu_title6.setFont(new java.awt.Font("Arial Black", 0, 36)); // NOI18N
        menu_title6.setForeground(new java.awt.Color(102, 102, 102));
        menu_title6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        menu_title6.setText("Purchase Request");

        menu_title7.setBackground(new java.awt.Color(96, 96, 96));
        menu_title7.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title7.setForeground(new java.awt.Color(102, 102, 102));
        menu_title7.setText("Purchase Order:");

        menu_title8.setBackground(new java.awt.Color(96, 96, 96));
        menu_title8.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title8.setForeground(new java.awt.Color(102, 102, 102));
        menu_title8.setText("Purchase Order:");

        menu_title9.setBackground(new java.awt.Color(96, 96, 96));
        menu_title9.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title9.setForeground(new java.awt.Color(102, 102, 102));
        menu_title9.setText("Date:");

        menu_title10.setBackground(new java.awt.Color(96, 96, 96));
        menu_title10.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title10.setForeground(new java.awt.Color(102, 102, 102));
        menu_title10.setText("Date:");

        jTable7.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable7.setGridColor(new java.awt.Color(255, 255, 255));
        jTable7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable7KeyPressed(evt);
            }
        });
        jScrollPane37.setViewportView(jTable7);

        menu_title11.setBackground(new java.awt.Color(96, 96, 96));
        menu_title11.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title11.setForeground(new java.awt.Color(102, 102, 102));
        menu_title11.setText("Total Item:");

        menu_title12.setBackground(new java.awt.Color(96, 96, 96));
        menu_title12.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title12.setForeground(new java.awt.Color(102, 102, 102));
        menu_title12.setText("Total Item:");

        menu_title13.setBackground(new java.awt.Color(96, 96, 96));
        menu_title13.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title13.setForeground(new java.awt.Color(102, 102, 102));
        menu_title13.setText("Total Book:");

        menu_title14.setBackground(new java.awt.Color(96, 96, 96));
        menu_title14.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title14.setForeground(new java.awt.Color(102, 102, 102));
        menu_title14.setText("Total Book:");

        menu_title15.setBackground(new java.awt.Color(96, 96, 96));
        menu_title15.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title15.setForeground(new java.awt.Color(102, 102, 102));
        menu_title15.setText("Total Cost:");

        menu_title16.setBackground(new java.awt.Color(96, 96, 96));
        menu_title16.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title16.setForeground(new java.awt.Color(102, 102, 102));
        menu_title16.setText("Total Cost:");

        menu_title19.setBackground(new java.awt.Color(96, 96, 96));
        menu_title19.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        menu_title19.setForeground(new java.awt.Color(102, 102, 102));
        menu_title19.setText("FOUNDATION");

        menu_title17.setBackground(new java.awt.Color(96, 96, 96));
        menu_title17.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title17.setForeground(new java.awt.Color(102, 102, 102));
        menu_title17.setText("Prepared by:");

        menu_title18.setBackground(new java.awt.Color(96, 96, 96));
        menu_title18.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title18.setForeground(new java.awt.Color(102, 102, 102));
        menu_title18.setText("Prepared by:");

        javax.swing.GroupLayout printLayout = new javax.swing.GroupLayout(print);
        print.setLayout(printLayout);
        printLayout.setHorizontalGroup(
            printLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(printLayout.createSequentialGroup()
                .addGroup(printLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(printLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(menu_title9)
                        .addGap(18, 18, 18)
                        .addComponent(menu_title10, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(menu_title6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(printLayout.createSequentialGroup()
                        .addGroup(printLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(printLayout.createSequentialGroup()
                                .addGap(256, 256, 256)
                                .addComponent(jLabel78)
                                .addGap(18, 18, 18)
                                .addGroup(printLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(menu_title3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(menu_title4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(menu_title5)
                                    .addComponent(menu_title19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(printLayout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addGroup(printLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(printLayout.createSequentialGroup()
                                        .addGroup(printLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(menu_title11)
                                            .addComponent(menu_title13))
                                        .addGap(18, 18, 18)
                                        .addGroup(printLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(menu_title14)
                                            .addComponent(menu_title12))
                                        .addGap(393, 393, 393)
                                        .addComponent(menu_title15)
                                        .addGap(18, 18, 18)
                                        .addComponent(menu_title16))
                                    .addGroup(printLayout.createSequentialGroup()
                                        .addComponent(menu_title7)
                                        .addGap(18, 18, 18)
                                        .addComponent(menu_title8))
                                    .addComponent(jScrollPane37, javax.swing.GroupLayout.PREFERRED_SIZE, 805, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(printLayout.createSequentialGroup()
                                        .addComponent(menu_title17)
                                        .addGap(18, 18, 18)
                                        .addComponent(menu_title18)))))
                        .addGap(0, 9, Short.MAX_VALUE)))
                .addContainerGap())
        );
        printLayout.setVerticalGroup(
            printLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(printLayout.createSequentialGroup()
                .addGroup(printLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(printLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel78))
                    .addGroup(printLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(menu_title3, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(menu_title19, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(menu_title4, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(menu_title5, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(menu_title6, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(printLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(menu_title7, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menu_title8, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menu_title9, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menu_title10, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane37, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(printLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(menu_title11, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menu_title12, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menu_title15, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menu_title16, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(printLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(menu_title13, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menu_title14, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                .addGroup(printLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(menu_title17, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menu_title18, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47))
        );

        jPanel1.add(print, "card11");

        print2.setBackground(new java.awt.Color(255, 255, 255));
        print2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel1.setBackground(new java.awt.Color(153, 153, 153));
        panel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jDateChooser2.setDateFormatString("M/d/yyyy");
        panel1.add(jDateChooser2, new org.netbeans.lib.awtextra.AbsoluteConstraints(129, 58, 166, 24));

        jDateChooser1.setDateFormatString("M/d/yyyy");
        panel1.add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(352, 58, 166, 24));

        nb_update14.setBackground(new java.awt.Color(51, 153, 255));
        nb_update14.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        nb_update14.setForeground(new java.awt.Color(255, 255, 255));
        nb_update14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/filter.png"))); // NOI18N
        nb_update14.setText("Filter");
        nb_update14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nb_update14ActionPerformed(evt);
            }
        });
        panel1.add(nb_update14, new org.netbeans.lib.awtextra.AbsoluteConstraints(204, 117, 188, -1));

        jLabel154.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel154.setForeground(new java.awt.Color(255, 255, 255));
        jLabel154.setText("From:");
        panel1.add(jLabel154, new org.netbeans.lib.awtextra.AbsoluteConstraints(191, 28, -1, 24));

        jLabel153.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel153.setForeground(new java.awt.Color(255, 255, 255));
        jLabel153.setText("To:");
        panel1.add(jLabel153, new org.netbeans.lib.awtextra.AbsoluteConstraints(431, 28, 31, 24));

        print2.add(panel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 220, 660, 300));

        menu_title20.setBackground(new java.awt.Color(96, 96, 96));
        menu_title20.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        menu_title20.setForeground(new java.awt.Color(102, 102, 102));
        menu_title20.setText("CRONASIA");
        print2.add(menu_title20, new org.netbeans.lib.awtextra.AbsoluteConstraints(424, 40, 152, 21));

        menu_title21.setBackground(new java.awt.Color(96, 96, 96));
        menu_title21.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        menu_title21.setForeground(new java.awt.Color(102, 102, 102));
        menu_title21.setText("COLLEGE");
        print2.add(menu_title21, new org.netbeans.lib.awtextra.AbsoluteConstraints(424, 94, 152, 18));

        menu_title22.setBackground(new java.awt.Color(96, 96, 96));
        menu_title22.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title22.setForeground(new java.awt.Color(102, 102, 102));
        menu_title22.setText("General Santos City");
        print2.add(menu_title22, new org.netbeans.lib.awtextra.AbsoluteConstraints(424, 118, -1, 18));

        jLabel79.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cronasia.png"))); // NOI18N
        print2.add(jLabel79, new org.netbeans.lib.awtextra.AbsoluteConstraints(256, 11, -1, -1));

        menu_title23.setBackground(new java.awt.Color(96, 96, 96));
        menu_title23.setFont(new java.awt.Font("Arial Black", 0, 36)); // NOI18N
        menu_title23.setForeground(new java.awt.Color(102, 102, 102));
        menu_title23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        menu_title23.setText("Payment Collection");
        print2.add(menu_title23, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 179, 853, 44));

        menu_title27.setBackground(new java.awt.Color(96, 96, 96));
        menu_title27.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title27.setForeground(new java.awt.Color(102, 102, 102));
        menu_title27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        menu_title27.setText("Date:");
        print2.add(menu_title27, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 241, 834, 18));

        jTable8.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable8.setGridColor(new java.awt.Color(255, 255, 255));
        jTable8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable8KeyPressed(evt);
            }
        });
        jScrollPane38.setViewportView(jTable8);

        print2.add(jScrollPane38, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 286, 790, 357));

        menu_title28.setBackground(new java.awt.Color(96, 96, 96));
        menu_title28.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title28.setForeground(new java.awt.Color(102, 102, 102));
        menu_title28.setText("Quantity:");
        print2.add(menu_title28, new org.netbeans.lib.awtextra.AbsoluteConstraints(308, 654, -1, 18));

        menu_title29.setBackground(new java.awt.Color(96, 96, 96));
        menu_title29.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title29.setForeground(new java.awt.Color(102, 102, 102));
        menu_title29.setText("Total Item:");
        print2.add(menu_title29, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 654, -1, 18));

        menu_title32.setBackground(new java.awt.Color(96, 96, 96));
        menu_title32.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title32.setForeground(new java.awt.Color(102, 102, 102));
        menu_title32.setText("Total Cost:");
        print2.add(menu_title32, new org.netbeans.lib.awtextra.AbsoluteConstraints(617, 654, -1, 18));

        menu_title33.setBackground(new java.awt.Color(96, 96, 96));
        menu_title33.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title33.setForeground(new java.awt.Color(102, 102, 102));
        menu_title33.setText("Total Cost:");
        print2.add(menu_title33, new org.netbeans.lib.awtextra.AbsoluteConstraints(737, 654, -1, 18));

        menu_title34.setBackground(new java.awt.Color(96, 96, 96));
        menu_title34.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        menu_title34.setForeground(new java.awt.Color(102, 102, 102));
        menu_title34.setText("FOUNDATION");
        print2.add(menu_title34, new org.netbeans.lib.awtextra.AbsoluteConstraints(424, 67, 152, 21));

        menu_title35.setBackground(new java.awt.Color(96, 96, 96));
        menu_title35.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title35.setForeground(new java.awt.Color(102, 102, 102));
        menu_title35.setText("Prepared by:");
        print2.add(menu_title35, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 760, -1, 18));

        menu_title36.setBackground(new java.awt.Color(96, 96, 96));
        menu_title36.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title36.setForeground(new java.awt.Color(102, 102, 102));
        menu_title36.setText("Prepared by:");
        print2.add(menu_title36, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 760, -1, 18));

        jPanel1.add(print2, "card11");

        print3.setBackground(new java.awt.Color(255, 255, 255));
        print3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel2.setBackground(new java.awt.Color(153, 153, 153));
        panel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jDateChooser3.setDateFormatString("MM/dd/yyyy");
        panel2.add(jDateChooser3, new org.netbeans.lib.awtextra.AbsoluteConstraints(129, 58, 166, 24));

        jDateChooser4.setDateFormatString("MM/dd/yyyy");
        panel2.add(jDateChooser4, new org.netbeans.lib.awtextra.AbsoluteConstraints(352, 58, 166, 24));

        nb_update15.setBackground(new java.awt.Color(51, 153, 255));
        nb_update15.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        nb_update15.setForeground(new java.awt.Color(255, 255, 255));
        nb_update15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/filter.png"))); // NOI18N
        nb_update15.setText("Filter");
        nb_update15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nb_update15ActionPerformed(evt);
            }
        });
        panel2.add(nb_update15, new org.netbeans.lib.awtextra.AbsoluteConstraints(204, 117, 188, -1));

        jLabel155.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel155.setForeground(new java.awt.Color(255, 255, 255));
        jLabel155.setText("From:");
        panel2.add(jLabel155, new org.netbeans.lib.awtextra.AbsoluteConstraints(191, 28, -1, 24));

        jLabel156.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel156.setForeground(new java.awt.Color(255, 255, 255));
        jLabel156.setText("To:");
        panel2.add(jLabel156, new org.netbeans.lib.awtextra.AbsoluteConstraints(431, 28, 31, 24));

        print3.add(panel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 220, 660, 300));

        menu_title24.setBackground(new java.awt.Color(96, 96, 96));
        menu_title24.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        menu_title24.setForeground(new java.awt.Color(102, 102, 102));
        menu_title24.setText("CRONASIA");
        print3.add(menu_title24, new org.netbeans.lib.awtextra.AbsoluteConstraints(424, 40, 152, 21));

        menu_title25.setBackground(new java.awt.Color(96, 96, 96));
        menu_title25.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        menu_title25.setForeground(new java.awt.Color(102, 102, 102));
        menu_title25.setText("COLLEGE");
        print3.add(menu_title25, new org.netbeans.lib.awtextra.AbsoluteConstraints(424, 94, 152, 18));

        menu_title26.setBackground(new java.awt.Color(96, 96, 96));
        menu_title26.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title26.setForeground(new java.awt.Color(102, 102, 102));
        menu_title26.setText("General Santos City");
        print3.add(menu_title26, new org.netbeans.lib.awtextra.AbsoluteConstraints(424, 118, -1, 18));

        jLabel80.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cronasia.png"))); // NOI18N
        print3.add(jLabel80, new org.netbeans.lib.awtextra.AbsoluteConstraints(256, 11, -1, -1));

        menu_title30.setBackground(new java.awt.Color(96, 96, 96));
        menu_title30.setFont(new java.awt.Font("Arial Black", 0, 36)); // NOI18N
        menu_title30.setForeground(new java.awt.Color(102, 102, 102));
        menu_title30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        menu_title30.setText("Delivery Log");
        print3.add(menu_title30, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 179, 853, 44));

        menu_title31.setBackground(new java.awt.Color(96, 96, 96));
        menu_title31.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title31.setForeground(new java.awt.Color(102, 102, 102));
        menu_title31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        menu_title31.setText("Date:");
        print3.add(menu_title31, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 241, 834, 18));

        jTable9.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable9.setGridColor(new java.awt.Color(255, 255, 255));
        jTable9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable9KeyPressed(evt);
            }
        });
        jScrollPane39.setViewportView(jTable9);

        print3.add(jScrollPane39, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 286, 800, 357));

        menu_title37.setBackground(new java.awt.Color(96, 96, 96));
        menu_title37.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title37.setForeground(new java.awt.Color(102, 102, 102));
        menu_title37.setText("Quantity:");
        print3.add(menu_title37, new org.netbeans.lib.awtextra.AbsoluteConstraints(308, 654, -1, 18));

        menu_title38.setBackground(new java.awt.Color(96, 96, 96));
        menu_title38.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title38.setForeground(new java.awt.Color(102, 102, 102));
        menu_title38.setText("Total Item:");
        print3.add(menu_title38, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 654, -1, 18));

        menu_title39.setBackground(new java.awt.Color(96, 96, 96));
        menu_title39.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title39.setForeground(new java.awt.Color(102, 102, 102));
        menu_title39.setText("Total Cost:");
        print3.add(menu_title39, new org.netbeans.lib.awtextra.AbsoluteConstraints(617, 654, -1, 18));

        menu_title40.setBackground(new java.awt.Color(96, 96, 96));
        menu_title40.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title40.setForeground(new java.awt.Color(102, 102, 102));
        menu_title40.setText("Total Cost:");
        print3.add(menu_title40, new org.netbeans.lib.awtextra.AbsoluteConstraints(737, 654, -1, 18));

        menu_title41.setBackground(new java.awt.Color(96, 96, 96));
        menu_title41.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        menu_title41.setForeground(new java.awt.Color(102, 102, 102));
        menu_title41.setText("FOUNDATION");
        print3.add(menu_title41, new org.netbeans.lib.awtextra.AbsoluteConstraints(424, 67, 152, 21));

        menu_title42.setBackground(new java.awt.Color(96, 96, 96));
        menu_title42.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title42.setForeground(new java.awt.Color(102, 102, 102));
        menu_title42.setText("Prepared by:");
        print3.add(menu_title42, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 760, -1, 18));

        menu_title43.setBackground(new java.awt.Color(96, 96, 96));
        menu_title43.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title43.setForeground(new java.awt.Color(102, 102, 102));
        menu_title43.setText("Prepared by:");
        print3.add(menu_title43, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 760, -1, 18));

        jPanel1.add(print3, "card11");

        print4.setBackground(new java.awt.Color(255, 255, 255));

        menu_title44.setBackground(new java.awt.Color(96, 96, 96));
        menu_title44.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        menu_title44.setForeground(new java.awt.Color(102, 102, 102));
        menu_title44.setText("CRONASIA");

        menu_title45.setBackground(new java.awt.Color(96, 96, 96));
        menu_title45.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        menu_title45.setForeground(new java.awt.Color(102, 102, 102));
        menu_title45.setText("COLLEGE");

        menu_title46.setBackground(new java.awt.Color(96, 96, 96));
        menu_title46.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title46.setForeground(new java.awt.Color(102, 102, 102));
        menu_title46.setText("General Santos City");

        jLabel81.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cronasia.png"))); // NOI18N

        menu_title47.setBackground(new java.awt.Color(96, 96, 96));
        menu_title47.setFont(new java.awt.Font("Arial Black", 0, 36)); // NOI18N
        menu_title47.setForeground(new java.awt.Color(102, 102, 102));
        menu_title47.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        menu_title47.setText("Book Stock Summary");

        menu_title48.setBackground(new java.awt.Color(96, 96, 96));
        menu_title48.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title48.setForeground(new java.awt.Color(102, 102, 102));
        menu_title48.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        menu_title48.setText("Date:");

        jTable10.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable10.setGridColor(new java.awt.Color(255, 255, 255));
        jTable10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable10KeyPressed(evt);
            }
        });
        jScrollPane40.setViewportView(jTable10);

        menu_title49.setBackground(new java.awt.Color(96, 96, 96));
        menu_title49.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title49.setForeground(new java.awt.Color(102, 102, 102));
        menu_title49.setText("Borrowed:");

        menu_title50.setBackground(new java.awt.Color(96, 96, 96));
        menu_title50.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title50.setForeground(new java.awt.Color(102, 102, 102));
        menu_title50.setText("Total Item:");

        menu_title51.setBackground(new java.awt.Color(96, 96, 96));
        menu_title51.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title51.setForeground(new java.awt.Color(102, 102, 102));
        menu_title51.setText("On Hand:");

        menu_title52.setBackground(new java.awt.Color(96, 96, 96));
        menu_title52.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title52.setForeground(new java.awt.Color(102, 102, 102));
        menu_title52.setText("Total Cost:");

        menu_title53.setBackground(new java.awt.Color(96, 96, 96));
        menu_title53.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        menu_title53.setForeground(new java.awt.Color(102, 102, 102));
        menu_title53.setText("FOUNDATION");

        menu_title54.setBackground(new java.awt.Color(96, 96, 96));
        menu_title54.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title54.setForeground(new java.awt.Color(102, 102, 102));
        menu_title54.setText("Prepared by:");

        menu_title55.setBackground(new java.awt.Color(96, 96, 96));
        menu_title55.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title55.setForeground(new java.awt.Color(102, 102, 102));
        menu_title55.setText("Prepared by:");

        menu_title56.setBackground(new java.awt.Color(96, 96, 96));
        menu_title56.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title56.setForeground(new java.awt.Color(102, 102, 102));
        menu_title56.setText("Total Cost:");

        menu_title57.setBackground(new java.awt.Color(96, 96, 96));
        menu_title57.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title57.setForeground(new java.awt.Color(102, 102, 102));
        menu_title57.setText("Damage/Loss:");

        menu_title58.setBackground(new java.awt.Color(96, 96, 96));
        menu_title58.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title58.setForeground(new java.awt.Color(102, 102, 102));
        menu_title58.setText("Total:");

        menu_title59.setBackground(new java.awt.Color(96, 96, 96));
        menu_title59.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title59.setForeground(new java.awt.Color(102, 102, 102));
        menu_title59.setText("Total Item:");

        javax.swing.GroupLayout print4Layout = new javax.swing.GroupLayout(print4);
        print4.setLayout(print4Layout);
        print4Layout.setHorizontalGroup(
            print4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(print4Layout.createSequentialGroup()
                .addGroup(print4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(menu_title47, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(print4Layout.createSequentialGroup()
                        .addGroup(print4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(print4Layout.createSequentialGroup()
                                .addGap(0, 4, Short.MAX_VALUE)
                                .addGroup(print4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(print4Layout.createSequentialGroup()
                                        .addGap(256, 256, 256)
                                        .addComponent(jLabel81)
                                        .addGap(18, 18, 18)
                                        .addGroup(print4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(menu_title44, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(menu_title45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(menu_title46)
                                            .addComponent(menu_title53, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addComponent(menu_title48, javax.swing.GroupLayout.PREFERRED_SIZE, 834, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(print4Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(print4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(menu_title49)
                                    .addComponent(menu_title51)
                                    .addComponent(menu_title58)
                                    .addComponent(menu_title57))
                                .addGap(18, 18, 18)
                                .addGroup(print4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, print4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(menu_title56)
                                        .addComponent(menu_title52)
                                        .addComponent(menu_title50))
                                    .addComponent(menu_title59, javax.swing.GroupLayout.Alignment.TRAILING))))
                        .addGap(0, 15, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(print4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(print4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(print4Layout.createSequentialGroup()
                        .addComponent(menu_title54)
                        .addGap(18, 18, 18)
                        .addComponent(menu_title55))
                    .addComponent(jScrollPane40, javax.swing.GroupLayout.PREFERRED_SIZE, 807, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        print4Layout.setVerticalGroup(
            print4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(print4Layout.createSequentialGroup()
                .addGroup(print4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(print4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel81))
                    .addGroup(print4Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(menu_title44, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(menu_title53, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(menu_title45, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(menu_title46, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(menu_title47, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(menu_title48, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jScrollPane40, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(print4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(menu_title52, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menu_title51, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(print4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(menu_title49, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menu_title50, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(print4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(menu_title56, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menu_title57, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(print4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(menu_title58, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menu_title59, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(print4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(menu_title54, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menu_title55, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47))
        );

        jPanel1.add(print4, "card11");

        print5.setBackground(new java.awt.Color(255, 255, 255));

        menu_title60.setBackground(new java.awt.Color(96, 96, 96));
        menu_title60.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        menu_title60.setForeground(new java.awt.Color(102, 102, 102));
        menu_title60.setText("CRONASIA");

        menu_title61.setBackground(new java.awt.Color(96, 96, 96));
        menu_title61.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        menu_title61.setForeground(new java.awt.Color(102, 102, 102));
        menu_title61.setText("COLLEGE");

        menu_title62.setBackground(new java.awt.Color(96, 96, 96));
        menu_title62.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title62.setForeground(new java.awt.Color(102, 102, 102));
        menu_title62.setText("General Santos City");

        jLabel82.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cronasia.png"))); // NOI18N

        menu_title63.setBackground(new java.awt.Color(96, 96, 96));
        menu_title63.setFont(new java.awt.Font("Arial Black", 0, 36)); // NOI18N
        menu_title63.setForeground(new java.awt.Color(102, 102, 102));
        menu_title63.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        menu_title63.setText("Collections");

        menu_title64.setBackground(new java.awt.Color(96, 96, 96));
        menu_title64.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title64.setForeground(new java.awt.Color(102, 102, 102));
        menu_title64.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        menu_title64.setText("Date:");

        jTable11.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable11.setGridColor(new java.awt.Color(255, 255, 255));
        jTable11.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable11KeyPressed(evt);
            }
        });
        jScrollPane41.setViewportView(jTable11);

        menu_title65.setBackground(new java.awt.Color(96, 96, 96));
        menu_title65.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title65.setForeground(new java.awt.Color(102, 102, 102));
        menu_title65.setText("Total Books:");

        menu_title66.setBackground(new java.awt.Color(96, 96, 96));
        menu_title66.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title66.setForeground(new java.awt.Color(102, 102, 102));
        menu_title66.setText("Total Item:");

        menu_title67.setBackground(new java.awt.Color(96, 96, 96));
        menu_title67.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title67.setForeground(new java.awt.Color(102, 102, 102));
        menu_title67.setText("Item Count:");

        menu_title68.setBackground(new java.awt.Color(96, 96, 96));
        menu_title68.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title68.setForeground(new java.awt.Color(102, 102, 102));
        menu_title68.setText("Total Cost:");

        menu_title69.setBackground(new java.awt.Color(96, 96, 96));
        menu_title69.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        menu_title69.setForeground(new java.awt.Color(102, 102, 102));
        menu_title69.setText("FOUNDATION");

        menu_title70.setBackground(new java.awt.Color(96, 96, 96));
        menu_title70.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title70.setForeground(new java.awt.Color(102, 102, 102));
        menu_title70.setText("Prepared by:");

        menu_title71.setBackground(new java.awt.Color(96, 96, 96));
        menu_title71.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title71.setForeground(new java.awt.Color(102, 102, 102));
        menu_title71.setText("Prepared by:");

        menu_title72.setBackground(new java.awt.Color(96, 96, 96));
        menu_title72.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title72.setForeground(new java.awt.Color(102, 102, 102));
        menu_title72.setText("Total Cost:");

        menu_title73.setBackground(new java.awt.Color(96, 96, 96));
        menu_title73.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title73.setForeground(new java.awt.Color(102, 102, 102));
        menu_title73.setText("Collectibles:");

        javax.swing.GroupLayout print5Layout = new javax.swing.GroupLayout(print5);
        print5.setLayout(print5Layout);
        print5Layout.setHorizontalGroup(
            print5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(print5Layout.createSequentialGroup()
                .addGroup(print5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(menu_title63, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(print5Layout.createSequentialGroup()
                        .addGap(0, 4, Short.MAX_VALUE)
                        .addGroup(print5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(print5Layout.createSequentialGroup()
                                .addGap(256, 256, 256)
                                .addComponent(jLabel82)
                                .addGap(18, 18, 18)
                                .addGroup(print5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(menu_title60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(menu_title61, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(menu_title62)
                                    .addComponent(menu_title69, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(menu_title64, javax.swing.GroupLayout.PREFERRED_SIZE, 834, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(print5Layout.createSequentialGroup()
                                .addGap(636, 636, 636)
                                .addComponent(menu_title73)
                                .addGap(18, 18, 18)
                                .addComponent(menu_title72)))
                        .addGap(0, 15, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(print5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(print5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(print5Layout.createSequentialGroup()
                        .addComponent(menu_title70)
                        .addGap(18, 18, 18)
                        .addComponent(menu_title71))
                    .addGroup(print5Layout.createSequentialGroup()
                        .addComponent(menu_title65)
                        .addGap(11, 11, 11)
                        .addComponent(menu_title66))
                    .addGroup(print5Layout.createSequentialGroup()
                        .addComponent(menu_title67)
                        .addGap(18, 18, 18)
                        .addComponent(menu_title68))
                    .addComponent(jScrollPane41, javax.swing.GroupLayout.PREFERRED_SIZE, 808, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        print5Layout.setVerticalGroup(
            print5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(print5Layout.createSequentialGroup()
                .addGroup(print5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(print5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel82))
                    .addGroup(print5Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(menu_title60, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(menu_title69, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(menu_title61, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(menu_title62, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(menu_title63, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(menu_title64, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jScrollPane41, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(print5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(menu_title68, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menu_title67, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menu_title72, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menu_title73, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(print5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(menu_title65, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menu_title66, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                .addGroup(print5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(menu_title70, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menu_title71, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47))
        );

        jPanel1.add(print5, "card11");

        print6.setBackground(new java.awt.Color(255, 255, 255));

        menu_title74.setBackground(new java.awt.Color(96, 96, 96));
        menu_title74.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        menu_title74.setForeground(new java.awt.Color(102, 102, 102));
        menu_title74.setText("CRONASIA");

        menu_title75.setBackground(new java.awt.Color(96, 96, 96));
        menu_title75.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        menu_title75.setForeground(new java.awt.Color(102, 102, 102));
        menu_title75.setText("COLLEGE");

        menu_title76.setBackground(new java.awt.Color(96, 96, 96));
        menu_title76.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title76.setForeground(new java.awt.Color(102, 102, 102));
        menu_title76.setText("General Santos City");

        jLabel83.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cronasia.png"))); // NOI18N

        menu_title77.setBackground(new java.awt.Color(96, 96, 96));
        menu_title77.setFont(new java.awt.Font("Arial Black", 0, 36)); // NOI18N
        menu_title77.setForeground(new java.awt.Color(102, 102, 102));
        menu_title77.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        menu_title77.setText("Stock-out");

        menu_title78.setBackground(new java.awt.Color(96, 96, 96));
        menu_title78.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title78.setForeground(new java.awt.Color(102, 102, 102));
        menu_title78.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        menu_title78.setText("Date:");

        jTable12.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable12.setGridColor(new java.awt.Color(255, 255, 255));
        jTable12.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable12KeyPressed(evt);
            }
        });
        jScrollPane42.setViewportView(jTable12);

        menu_title79.setBackground(new java.awt.Color(96, 96, 96));
        menu_title79.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title79.setForeground(new java.awt.Color(102, 102, 102));
        menu_title79.setText("Total Books:");

        menu_title80.setBackground(new java.awt.Color(96, 96, 96));
        menu_title80.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title80.setForeground(new java.awt.Color(102, 102, 102));
        menu_title80.setText("Total Item:");

        menu_title81.setBackground(new java.awt.Color(96, 96, 96));
        menu_title81.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title81.setForeground(new java.awt.Color(102, 102, 102));
        menu_title81.setText("Item Count:");

        menu_title82.setBackground(new java.awt.Color(96, 96, 96));
        menu_title82.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title82.setForeground(new java.awt.Color(102, 102, 102));
        menu_title82.setText("Total Cost:");

        menu_title83.setBackground(new java.awt.Color(96, 96, 96));
        menu_title83.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        menu_title83.setForeground(new java.awt.Color(102, 102, 102));
        menu_title83.setText("FOUNDATION");

        menu_title84.setBackground(new java.awt.Color(96, 96, 96));
        menu_title84.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title84.setForeground(new java.awt.Color(102, 102, 102));
        menu_title84.setText("Prepared by:");

        menu_title85.setBackground(new java.awt.Color(96, 96, 96));
        menu_title85.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title85.setForeground(new java.awt.Color(102, 102, 102));
        menu_title85.setText("Prepared by:");

        javax.swing.GroupLayout print6Layout = new javax.swing.GroupLayout(print6);
        print6.setLayout(print6Layout);
        print6Layout.setHorizontalGroup(
            print6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(print6Layout.createSequentialGroup()
                .addGroup(print6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(menu_title77, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(print6Layout.createSequentialGroup()
                        .addGap(0, 4, Short.MAX_VALUE)
                        .addGroup(print6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(print6Layout.createSequentialGroup()
                                .addGap(256, 256, 256)
                                .addComponent(jLabel83)
                                .addGap(18, 18, 18)
                                .addGroup(print6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(menu_title74, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(menu_title75, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(menu_title76)
                                    .addComponent(menu_title83, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(menu_title78, javax.swing.GroupLayout.PREFERRED_SIZE, 834, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 15, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(print6Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(print6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(print6Layout.createSequentialGroup()
                        .addComponent(menu_title84)
                        .addGap(18, 18, 18)
                        .addComponent(menu_title85))
                    .addGroup(print6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, print6Layout.createSequentialGroup()
                            .addComponent(menu_title79)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(menu_title80))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, print6Layout.createSequentialGroup()
                            .addComponent(menu_title81)
                            .addGap(18, 18, 18)
                            .addComponent(menu_title82)))
                    .addComponent(jScrollPane42, javax.swing.GroupLayout.PREFERRED_SIZE, 808, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        print6Layout.setVerticalGroup(
            print6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(print6Layout.createSequentialGroup()
                .addGroup(print6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(print6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel83))
                    .addGroup(print6Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(menu_title74, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(menu_title83, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(menu_title75, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(menu_title76, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(menu_title77, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(menu_title78, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jScrollPane42, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(print6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(menu_title82, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menu_title81, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(print6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(menu_title79, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menu_title80, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                .addGroup(print6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(menu_title84, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menu_title85, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47))
        );

        jPanel1.add(print6, "card11");

        print7.setBackground(new java.awt.Color(255, 255, 255));

        menu_title86.setBackground(new java.awt.Color(96, 96, 96));
        menu_title86.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        menu_title86.setForeground(new java.awt.Color(102, 102, 102));
        menu_title86.setText("CRONASIA");

        menu_title87.setBackground(new java.awt.Color(96, 96, 96));
        menu_title87.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        menu_title87.setForeground(new java.awt.Color(102, 102, 102));
        menu_title87.setText("COLLEGE");

        menu_title88.setBackground(new java.awt.Color(96, 96, 96));
        menu_title88.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title88.setForeground(new java.awt.Color(102, 102, 102));
        menu_title88.setText("General Santos City");

        jLabel84.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cronasia.png"))); // NOI18N

        menu_title89.setBackground(new java.awt.Color(96, 96, 96));
        menu_title89.setFont(new java.awt.Font("Arial Black", 0, 36)); // NOI18N
        menu_title89.setForeground(new java.awt.Color(102, 102, 102));
        menu_title89.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        menu_title89.setText("Over Due Book(s)");

        menu_title90.setBackground(new java.awt.Color(96, 96, 96));
        menu_title90.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title90.setForeground(new java.awt.Color(102, 102, 102));
        menu_title90.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        menu_title90.setText("Date:");

        jTable13.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable13.setGridColor(new java.awt.Color(255, 255, 255));
        jTable13.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable13KeyPressed(evt);
            }
        });
        jScrollPane43.setViewportView(jTable13);

        menu_title91.setBackground(new java.awt.Color(96, 96, 96));
        menu_title91.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title91.setForeground(new java.awt.Color(102, 102, 102));
        menu_title91.setText("Total Books:");

        menu_title92.setBackground(new java.awt.Color(96, 96, 96));
        menu_title92.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title92.setForeground(new java.awt.Color(102, 102, 102));
        menu_title92.setText("Total Item:");

        menu_title93.setBackground(new java.awt.Color(96, 96, 96));
        menu_title93.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title93.setForeground(new java.awt.Color(102, 102, 102));
        menu_title93.setText("Item Count:");

        menu_title94.setBackground(new java.awt.Color(96, 96, 96));
        menu_title94.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title94.setForeground(new java.awt.Color(102, 102, 102));
        menu_title94.setText("Total Cost:");

        menu_title95.setBackground(new java.awt.Color(96, 96, 96));
        menu_title95.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        menu_title95.setForeground(new java.awt.Color(102, 102, 102));
        menu_title95.setText("FOUNDATION");

        menu_title96.setBackground(new java.awt.Color(96, 96, 96));
        menu_title96.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title96.setForeground(new java.awt.Color(102, 102, 102));
        menu_title96.setText("Prepared by:");

        menu_title97.setBackground(new java.awt.Color(96, 96, 96));
        menu_title97.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title97.setForeground(new java.awt.Color(102, 102, 102));
        menu_title97.setText("Prepared by:");

        javax.swing.GroupLayout print7Layout = new javax.swing.GroupLayout(print7);
        print7.setLayout(print7Layout);
        print7Layout.setHorizontalGroup(
            print7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(print7Layout.createSequentialGroup()
                .addGroup(print7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(menu_title89, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(print7Layout.createSequentialGroup()
                        .addGap(0, 4, Short.MAX_VALUE)
                        .addGroup(print7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(print7Layout.createSequentialGroup()
                                .addGap(256, 256, 256)
                                .addComponent(jLabel84)
                                .addGap(18, 18, 18)
                                .addGroup(print7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(menu_title86, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(menu_title87, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(menu_title88)
                                    .addComponent(menu_title95, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(menu_title90, javax.swing.GroupLayout.PREFERRED_SIZE, 834, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 15, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(print7Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(print7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(print7Layout.createSequentialGroup()
                        .addComponent(menu_title96)
                        .addGap(18, 18, 18)
                        .addComponent(menu_title97))
                    .addGroup(print7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, print7Layout.createSequentialGroup()
                            .addComponent(menu_title91)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(menu_title92))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, print7Layout.createSequentialGroup()
                            .addComponent(menu_title93)
                            .addGap(18, 18, 18)
                            .addComponent(menu_title94)))
                    .addComponent(jScrollPane43, javax.swing.GroupLayout.PREFERRED_SIZE, 804, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        print7Layout.setVerticalGroup(
            print7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(print7Layout.createSequentialGroup()
                .addGroup(print7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(print7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel84))
                    .addGroup(print7Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(menu_title86, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(menu_title95, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(menu_title87, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(menu_title88, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(menu_title89, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(menu_title90, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jScrollPane43, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(print7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(menu_title94, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menu_title93, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(print7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(menu_title91, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menu_title92, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                .addGroup(print7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(menu_title96, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menu_title97, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47))
        );

        jPanel1.add(print7, "card11");

        print8.setBackground(new java.awt.Color(255, 255, 255));

        menu_title98.setBackground(new java.awt.Color(96, 96, 96));
        menu_title98.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        menu_title98.setForeground(new java.awt.Color(102, 102, 102));
        menu_title98.setText("CRONASIA");

        menu_title99.setBackground(new java.awt.Color(96, 96, 96));
        menu_title99.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        menu_title99.setForeground(new java.awt.Color(102, 102, 102));
        menu_title99.setText("COLLEGE");

        menu_title100.setBackground(new java.awt.Color(96, 96, 96));
        menu_title100.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title100.setForeground(new java.awt.Color(102, 102, 102));
        menu_title100.setText("General Santos City");

        jLabel85.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cronasia.png"))); // NOI18N

        menu_title101.setBackground(new java.awt.Color(96, 96, 96));
        menu_title101.setFont(new java.awt.Font("Arial Black", 0, 36)); // NOI18N
        menu_title101.setForeground(new java.awt.Color(102, 102, 102));
        menu_title101.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        menu_title101.setText("Borrowed List");

        menu_title102.setBackground(new java.awt.Color(96, 96, 96));
        menu_title102.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title102.setForeground(new java.awt.Color(102, 102, 102));
        menu_title102.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        menu_title102.setText("Date:");

        jTable14.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable14.setGridColor(new java.awt.Color(255, 255, 255));
        jTable14.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable14KeyPressed(evt);
            }
        });
        jScrollPane44.setViewportView(jTable14);

        menu_title105.setBackground(new java.awt.Color(96, 96, 96));
        menu_title105.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title105.setForeground(new java.awt.Color(102, 102, 102));
        menu_title105.setText("Item Count:");

        menu_title107.setBackground(new java.awt.Color(96, 96, 96));
        menu_title107.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        menu_title107.setForeground(new java.awt.Color(102, 102, 102));
        menu_title107.setText("FOUNDATION");

        menu_title108.setBackground(new java.awt.Color(96, 96, 96));
        menu_title108.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title108.setForeground(new java.awt.Color(102, 102, 102));
        menu_title108.setText("Prepared by:");

        menu_title109.setBackground(new java.awt.Color(96, 96, 96));
        menu_title109.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title109.setForeground(new java.awt.Color(102, 102, 102));
        menu_title109.setText("Prepared by:");

        menu_title110.setBackground(new java.awt.Color(96, 96, 96));
        menu_title110.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title110.setForeground(new java.awt.Color(102, 102, 102));
        menu_title110.setText("Total Cost:");

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });
        jPanel2.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 120, 29));

        menu_title106.setBackground(new java.awt.Color(96, 96, 96));
        menu_title106.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title106.setForeground(new java.awt.Color(102, 102, 102));
        menu_title106.setText("Borrower");
        jPanel2.add(menu_title106, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 18));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Student", "Faculty" }));
        jComboBox1.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jComboBox1PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        jPanel2.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 20, 130, 29));

        menu_title111.setBackground(new java.awt.Color(96, 96, 96));
        menu_title111.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title111.setForeground(new java.awt.Color(102, 102, 102));
        menu_title111.setText("Status");
        jPanel2.add(menu_title111, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 0, -1, 18));

        menu_title112.setBackground(new java.awt.Color(96, 96, 96));
        menu_title112.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title112.setForeground(new java.awt.Color(102, 102, 102));
        menu_title112.setText("Borrowed");
        jPanel2.add(menu_title112, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 0, -1, 18));

        jComboBox2.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jComboBox2PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        jPanel2.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 20, 130, 29));

        jDateChooser5.setDateFormatString("M/d/yyyy");
        jPanel2.add(jDateChooser5, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 50, 120, 30));

        menu_title113.setBackground(new java.awt.Color(96, 96, 96));
        menu_title113.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title113.setForeground(new java.awt.Color(102, 102, 102));
        menu_title113.setText("Classification");
        jPanel2.add(menu_title113, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 0, -1, 18));

        jDateChooser6.setDateFormatString("M/d/yyyy");
        jPanel2.add(jDateChooser6, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 50, 120, 30));

        menu_title115.setBackground(new java.awt.Color(96, 96, 96));
        menu_title115.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title115.setForeground(new java.awt.Color(102, 102, 102));
        menu_title115.setText("Borrowed");
        jPanel2.add(menu_title115, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 0, -1, 18));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/canchu.png"))); // NOI18N
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 20, 40, 30));

        jDateChooser7.setDateFormatString("M/d/yyyy");
        jPanel2.add(jDateChooser7, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 20, 120, 30));

        jDateChooser8.setDateFormatString("M/d/yyyy");
        jPanel2.add(jDateChooser8, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 20, 120, 30));

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Filter");
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel6MouseExited(evt);
            }
        });
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 80, 120, -1));

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Filter");
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel7MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel7MouseExited(evt);
            }
        });
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 80, 120, -1));

        menu_title116.setBackground(new java.awt.Color(96, 96, 96));
        menu_title116.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title116.setForeground(new java.awt.Color(102, 102, 102));
        menu_title116.setText("Book Title");
        jPanel2.add(menu_title116, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 0, -1, 18));

        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField2KeyReleased(evt);
            }
        });
        jPanel2.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, 130, 29));

        javax.swing.GroupLayout print8Layout = new javax.swing.GroupLayout(print8);
        print8.setLayout(print8Layout);
        print8Layout.setHorizontalGroup(
            print8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(print8Layout.createSequentialGroup()
                .addGroup(print8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(menu_title101, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(print8Layout.createSequentialGroup()
                        .addGap(256, 260, Short.MAX_VALUE)
                        .addComponent(jLabel85)
                        .addGap(18, 18, 18)
                        .addGroup(print8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(menu_title98, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(menu_title99, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(menu_title100)
                            .addComponent(menu_title107, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 273, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(print8Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(print8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(print8Layout.createSequentialGroup()
                        .addComponent(menu_title108)
                        .addGap(18, 18, 18)
                        .addComponent(menu_title109))
                    .addGroup(print8Layout.createSequentialGroup()
                        .addComponent(menu_title105)
                        .addGap(18, 18, 18)
                        .addComponent(menu_title110))
                    .addComponent(jScrollPane44, javax.swing.GroupLayout.DEFAULT_SIZE, 804, Short.MAX_VALUE)
                    .addComponent(menu_title102, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        print8Layout.setVerticalGroup(
            print8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(print8Layout.createSequentialGroup()
                .addGroup(print8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(print8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel85))
                    .addGroup(print8Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(menu_title98, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(menu_title107, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(menu_title99, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(menu_title100, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(menu_title101, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menu_title102, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane44, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(print8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(menu_title105, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menu_title110, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(57, 57, 57)
                .addGroup(print8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(menu_title108, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menu_title109, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47))
        );

        jPanel1.add(print8, "card11");

        print9.setBackground(new java.awt.Color(255, 255, 255));

        menu_title103.setBackground(new java.awt.Color(96, 96, 96));
        menu_title103.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        menu_title103.setForeground(new java.awt.Color(102, 102, 102));
        menu_title103.setText("CRONASIA");

        menu_title104.setBackground(new java.awt.Color(96, 96, 96));
        menu_title104.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        menu_title104.setForeground(new java.awt.Color(102, 102, 102));
        menu_title104.setText("COLLEGE");

        menu_title114.setBackground(new java.awt.Color(96, 96, 96));
        menu_title114.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title114.setForeground(new java.awt.Color(102, 102, 102));
        menu_title114.setText("General Santos City");

        jLabel86.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cronasia.png"))); // NOI18N

        menu_title117.setBackground(new java.awt.Color(96, 96, 96));
        menu_title117.setFont(new java.awt.Font("Arial Black", 0, 36)); // NOI18N
        menu_title117.setForeground(new java.awt.Color(102, 102, 102));
        menu_title117.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        menu_title117.setText("Book Onhand Inventory");

        menu_title118.setBackground(new java.awt.Color(96, 96, 96));
        menu_title118.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title118.setForeground(new java.awt.Color(102, 102, 102));
        menu_title118.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        menu_title118.setText("Date:");

        jTable15.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable15.setGridColor(new java.awt.Color(255, 255, 255));
        jTable15.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable15KeyPressed(evt);
            }
        });
        jScrollPane45.setViewportView(jTable15);

        menu_title119.setBackground(new java.awt.Color(96, 96, 96));
        menu_title119.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title119.setForeground(new java.awt.Color(102, 102, 102));
        menu_title119.setText("Item Count:");

        menu_title120.setBackground(new java.awt.Color(96, 96, 96));
        menu_title120.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        menu_title120.setForeground(new java.awt.Color(102, 102, 102));
        menu_title120.setText("FOUNDATION");

        menu_title121.setBackground(new java.awt.Color(96, 96, 96));
        menu_title121.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title121.setForeground(new java.awt.Color(102, 102, 102));
        menu_title121.setText("Prepared by:");

        menu_title122.setBackground(new java.awt.Color(96, 96, 96));
        menu_title122.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title122.setForeground(new java.awt.Color(102, 102, 102));
        menu_title122.setText("Prepared by:");

        menu_title123.setBackground(new java.awt.Color(96, 96, 96));
        menu_title123.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title123.setForeground(new java.awt.Color(102, 102, 102));
        menu_title123.setText("Total Cost:");

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jDateChooser10.setDateFormatString("MM/dd/yyyy");
        jPanel3.add(jDateChooser10, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 50, 180, 30));

        menu_title128.setBackground(new java.awt.Color(96, 96, 96));
        menu_title128.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title128.setForeground(new java.awt.Color(102, 102, 102));
        menu_title128.setText("Book Arrived:");
        jPanel3.add(menu_title128, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, 18));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/canchu.png"))); // NOI18N
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 20, 40, 30));

        jDateChooser12.setDateFormatString("MM/dd/yyyy");
        jPanel3.add(jDateChooser12, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, 180, 30));

        jLabel9.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Filter");
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel9MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel9MouseExited(evt);
            }
        });
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 60, 50, -1));

        javax.swing.GroupLayout print9Layout = new javax.swing.GroupLayout(print9);
        print9.setLayout(print9Layout);
        print9Layout.setHorizontalGroup(
            print9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(print9Layout.createSequentialGroup()
                .addGroup(print9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(menu_title117, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(print9Layout.createSequentialGroup()
                        .addGap(256, 260, Short.MAX_VALUE)
                        .addComponent(jLabel86)
                        .addGap(18, 18, 18)
                        .addGroup(print9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(menu_title103, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(menu_title104, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(menu_title114)
                            .addComponent(menu_title120, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 273, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(print9Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(print9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(print9Layout.createSequentialGroup()
                        .addComponent(menu_title121)
                        .addGap(18, 18, 18)
                        .addComponent(menu_title122))
                    .addGroup(print9Layout.createSequentialGroup()
                        .addComponent(menu_title119)
                        .addGap(18, 18, 18)
                        .addComponent(menu_title123))
                    .addComponent(jScrollPane45, javax.swing.GroupLayout.DEFAULT_SIZE, 804, Short.MAX_VALUE)
                    .addComponent(menu_title118, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        print9Layout.setVerticalGroup(
            print9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(print9Layout.createSequentialGroup()
                .addGroup(print9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(print9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel86))
                    .addGroup(print9Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(menu_title103, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(menu_title120, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(menu_title104, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(menu_title114, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(menu_title117, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menu_title118, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane45, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(print9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(menu_title119, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menu_title123, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(57, 57, 57)
                .addGroup(print9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(menu_title121, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menu_title122, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47))
        );

        jPanel1.add(print9, "card11");

        print10.setBackground(new java.awt.Color(255, 255, 255));

        menu_title124.setBackground(new java.awt.Color(96, 96, 96));
        menu_title124.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        menu_title124.setForeground(new java.awt.Color(102, 102, 102));
        menu_title124.setText("CRONASIA");

        menu_title125.setBackground(new java.awt.Color(96, 96, 96));
        menu_title125.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        menu_title125.setForeground(new java.awt.Color(102, 102, 102));
        menu_title125.setText("COLLEGE");

        menu_title126.setBackground(new java.awt.Color(96, 96, 96));
        menu_title126.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title126.setForeground(new java.awt.Color(102, 102, 102));
        menu_title126.setText("General Santos City");

        jLabel87.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cronasia.png"))); // NOI18N

        menu_title127.setBackground(new java.awt.Color(96, 96, 96));
        menu_title127.setFont(new java.awt.Font("Arial Black", 0, 36)); // NOI18N
        menu_title127.setForeground(new java.awt.Color(102, 102, 102));
        menu_title127.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        menu_title127.setText("Book Loaned");

        menu_title129.setBackground(new java.awt.Color(96, 96, 96));
        menu_title129.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title129.setForeground(new java.awt.Color(102, 102, 102));
        menu_title129.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        menu_title129.setText("Date:");

        jTable16.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable16.setGridColor(new java.awt.Color(255, 255, 255));
        jTable16.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable16KeyPressed(evt);
            }
        });
        jScrollPane46.setViewportView(jTable16);

        menu_title130.setBackground(new java.awt.Color(96, 96, 96));
        menu_title130.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title130.setForeground(new java.awt.Color(102, 102, 102));
        menu_title130.setText("Item Count:");

        menu_title131.setBackground(new java.awt.Color(96, 96, 96));
        menu_title131.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        menu_title131.setForeground(new java.awt.Color(102, 102, 102));
        menu_title131.setText("FOUNDATION");

        menu_title132.setBackground(new java.awt.Color(96, 96, 96));
        menu_title132.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title132.setForeground(new java.awt.Color(102, 102, 102));
        menu_title132.setText("Prepared by:");

        menu_title133.setBackground(new java.awt.Color(96, 96, 96));
        menu_title133.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title133.setForeground(new java.awt.Color(102, 102, 102));
        menu_title133.setText("Prepared by:");

        menu_title134.setBackground(new java.awt.Color(96, 96, 96));
        menu_title134.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title134.setForeground(new java.awt.Color(102, 102, 102));
        menu_title134.setText("Total Cost:");

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.add(jComboBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 220, 29));

        menu_title138.setBackground(new java.awt.Color(96, 96, 96));
        menu_title138.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title138.setForeground(new java.awt.Color(102, 102, 102));
        menu_title138.setText("Book Title");
        jPanel4.add(menu_title138, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 100, 18));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/canchu.png"))); // NOI18N
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });
        jPanel4.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 30, 40, 30));

        menu_title140.setBackground(new java.awt.Color(96, 96, 96));
        menu_title140.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title140.setForeground(new java.awt.Color(102, 102, 102));
        menu_title140.setText("Classification");
        jPanel4.add(menu_title140, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, 130, 18));

        jPanel4.add(jComboBox6, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 30, 220, 29));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/browsing.png"))); // NOI18N
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });
        jPanel4.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 30, 30, 30));

        javax.swing.GroupLayout print10Layout = new javax.swing.GroupLayout(print10);
        print10.setLayout(print10Layout);
        print10Layout.setHorizontalGroup(
            print10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(print10Layout.createSequentialGroup()
                .addGroup(print10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(menu_title127, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(print10Layout.createSequentialGroup()
                        .addGap(256, 260, Short.MAX_VALUE)
                        .addComponent(jLabel87)
                        .addGap(18, 18, 18)
                        .addGroup(print10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(menu_title124, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(menu_title125, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(menu_title126)
                            .addComponent(menu_title131, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 273, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(print10Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(print10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(print10Layout.createSequentialGroup()
                        .addComponent(menu_title132)
                        .addGap(18, 18, 18)
                        .addComponent(menu_title133))
                    .addGroup(print10Layout.createSequentialGroup()
                        .addComponent(menu_title130)
                        .addGap(18, 18, 18)
                        .addComponent(menu_title134))
                    .addComponent(jScrollPane46, javax.swing.GroupLayout.DEFAULT_SIZE, 804, Short.MAX_VALUE)
                    .addComponent(menu_title129, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        print10Layout.setVerticalGroup(
            print10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(print10Layout.createSequentialGroup()
                .addGroup(print10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(print10Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel87))
                    .addGroup(print10Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(menu_title124, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(menu_title131, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(menu_title125, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(menu_title126, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(menu_title127, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menu_title129, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane46, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(print10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(menu_title130, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menu_title134, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(57, 57, 57)
                .addGroup(print10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(menu_title132, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menu_title133, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47))
        );

        jPanel1.add(print10, "card11");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable7KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            printop();
            
            try {
            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("DELETE FROM `order_tbl` WHERE 1-1000");
            int del = pst.executeUpdate();
            if (del > 0) {
                //JOptionPane.showMessageDialog(null, "Book Entry Deleted!");
            }else{
                JOptionPane.showMessageDialog(null, "Please Check Item!!");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        try {
            String sql = "alter table order_tbl AUTO_INCREMENT = 1";
            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement(sql);
            pst.execute();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
        jLabel4.setText("go");
            
            this.dispose();
        }
        else if(evt.getKeyCode()==KeyEvent.VK_ESCAPE){
            this.dispose();
        }
    }//GEN-LAST:event_jTable7KeyPressed

    private void jTable8KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable8KeyPressed
         if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            printpay();
            this.dispose();
        }
        else if(evt.getKeyCode()==KeyEvent.VK_ESCAPE){
            this.dispose();
        }
    }//GEN-LAST:event_jTable8KeyPressed

    private void jTable9KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable9KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            printdel();
            this.dispose();
        }
        else if(evt.getKeyCode()==KeyEvent.VK_ESCAPE){
            this.dispose();
        }
    }//GEN-LAST:event_jTable9KeyPressed

    private void jTable10KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable10KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            printsum();
            this.dispose();
        }
        else if(evt.getKeyCode()==KeyEvent.VK_ESCAPE){
            this.dispose();
        }
    }//GEN-LAST:event_jTable10KeyPressed

    private void jTable11KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable11KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            printcol();
            this.dispose();
        }
        else if(evt.getKeyCode()==KeyEvent.VK_ESCAPE){
            this.dispose();
        }
    }//GEN-LAST:event_jTable11KeyPressed

    private void nb_update14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nb_update14ActionPerformed
        filterpay();
        paydash();
        panel1.setVisible(false);
        
    }//GEN-LAST:event_nb_update14ActionPerformed

    private void nb_update15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nb_update15ActionPerformed
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            try {
                String sql = "select Delivery_No, Book_title, Author, Classification, Quantity, Price, Purchase_No,"
                    + " Purchase_Date from  delivery_tbl where Delivery_Date "
                        + "between '" + (String) sdf.format(jDateChooser3.getDate()) + "' "
                        + "and '" + (String) sdf.format(jDateChooser4.getDate()) + "' ";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                jTable9.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
  JOptionPane.showConfirmDialog(null, e);
            }
            panel2.setVisible(false);
            deldashx();
    }//GEN-LAST:event_nb_update15ActionPerformed

    private void jTable12KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable12KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            printdam();
            this.dispose();
        }
        else if(evt.getKeyCode()==KeyEvent.VK_ESCAPE){
            this.dispose();
        }
    }//GEN-LAST:event_jTable12KeyPressed

    private void jTable13KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable13KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            printod();
            this.dispose();
        }
        else if(evt.getKeyCode()==KeyEvent.VK_ESCAPE){
            this.dispose();
        }
    }//GEN-LAST:event_jTable13KeyPressed

    private void jTable14KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable14KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            jPanel2.setVisible(false);
            printbrrd();
            this.dispose();
        }
        else if(evt.getKeyCode()==KeyEvent.VK_F10) {
            jPanel2.setVisible(true);
        }
        else if(evt.getKeyCode()==KeyEvent.VK_ESCAPE){
            this.dispose();
        }
    }//GEN-LAST:event_jTable14KeyPressed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        try{
            String sql = "SELECT Full_Name, Status, Book_title, Classification, Borrowed_Date, Encoder, Date"
                    + " FROM brrdlog_tbl WHERE "
                    + "Full_Name like ? ";

            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, "%" + jTextField1.getText() + "%");

            rs = (ResultSet) pst.executeQuery();
            jTable14.setModel(DbUtils.resultSetToTableModel(rs));
            }
            catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, ex);
            }
        brrdcount();
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        jTextField1.setText("");
        jComboBox1.setSelectedIndex(0);
        jComboBox2.setSelectedIndex(0);
        jDateChooser5.setDate(null);
        jDateChooser6.setDate(null);
        jDateChooser7.setDate(null);
        jDateChooser8.setDate(null);
        all_ref();
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jComboBox1PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBox1PopupMenuWillBecomeInvisible
        try{
            String sql = "SELECT Full_Name, Status, Book_title, Classification, Borrowed_Date, Encoder, Date"
                    + " FROM brrdlog_tbl WHERE "
                    + "Status like ? ";

            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, "%" + jComboBox1.getSelectedItem() + "%");

            rs = (ResultSet) pst.executeQuery();
            jTable14.setModel(DbUtils.resultSetToTableModel(rs));
            }
            catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, ex);
            }
        brrdcount();
    }//GEN-LAST:event_jComboBox1PopupMenuWillBecomeInvisible

    private void jComboBox2PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBox2PopupMenuWillBecomeInvisible
        try{
            String sql = "SELECT Full_Name, Status, Book_title, Classification, Borrowed_Date, Encoder, Date"
                    + " FROM brrdlog_tbl WHERE "
                    + "Classification like ? ";

            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, "%" + jComboBox2.getSelectedItem() + "%");

            rs = (ResultSet) pst.executeQuery();
            jTable14.setModel(DbUtils.resultSetToTableModel(rs));
            }
            catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, ex);
            }
        brrdcount();
    }//GEN-LAST:event_jComboBox2PopupMenuWillBecomeInvisible

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
        try{
            String sql = "SELECT Full_Name, Status, Book_title, Classification, Borrowed_Date, Encoder, Date"
                    + " FROM brrdlog_tbl WHERE "
                    + "Book_title like ? ";

            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, "%" + jTextField2.getText() + "%");

            rs = (ResultSet) pst.executeQuery();
            jTable14.setModel(DbUtils.resultSetToTableModel(rs));
            }
            catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, ex);
            }
        brrdcount();
    }//GEN-LAST:event_jTextField2KeyReleased

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        SimpleDateFormat sdf = new SimpleDateFormat("M/d/yyyy");
            try {
                String sql = "select Full_Name, Status, Book_title, Classification, Borrowed_Date, Encoder, Date"
                        + " from  brrdlog_tbl where Borrowed_Date "
                        + "between '" + (String) sdf.format(jDateChooser7.getDate()) + "' "
                        + "and '" + (String) sdf.format(jDateChooser5.getDate()) + "' ";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                jTable14.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
  JOptionPane.showConfirmDialog(null, e);
            }
            brrdcount();
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jLabel7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseEntered
        jLabel7.setForeground(new Color(57,167,252,225));
    }//GEN-LAST:event_jLabel7MouseEntered

    private void jLabel7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseExited
        jLabel7.setForeground(Color.BLACK);
    }//GEN-LAST:event_jLabel7MouseExited

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        SimpleDateFormat sdf = new SimpleDateFormat("M/d/yyyy");
            try {
                String sql = "select Full_Name, Status, Book_title, Classification, Borrowed_Date, Encoder, Date"
                        + " from  brrdlog_tbl where Date "
                        + "between '" + (String) sdf.format(jDateChooser8.getDate()) + "' "
                        + "and '" + (String) sdf.format(jDateChooser6.getDate()) + "' ";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                jTable14.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
  JOptionPane.showConfirmDialog(null, e);
            }
            brrdcount();
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseEntered
        jLabel6.setForeground(new Color(57,167,252,225));
    }//GEN-LAST:event_jLabel6MouseEntered

    private void jLabel6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseExited
        jLabel6.setForeground(Color.BLACK);
    }//GEN-LAST:event_jLabel6MouseExited

    private void jTable15KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable15KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            jPanel3.setVisible(false);
            printboh();
            this.dispose();
        }
        else if(evt.getKeyCode()==KeyEvent.VK_F10) {
            jPanel3.setVisible(true);
        }
        else if(evt.getKeyCode()==KeyEvent.VK_ESCAPE){
            this.dispose();
        }
    }//GEN-LAST:event_jTable15KeyPressed

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        jDateChooser12.setDate(null);
        jDateChooser10.setDate(null);
        all_ref();
        bohcount();
    }//GEN-LAST:event_jLabel8MouseClicked

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            try {
                String sql = "select ISBN_No, Book_title, Author, Quantity, Call_Number, Classification, Publisher, "
                        + "Edition, Copy_Right_Year, Price, Date_Arrival"
                        + " from  stockin_tbl where Date_Arrival "
                        + "between '" + (String) sdf.format(jDateChooser12.getDate()) + "' "
                        + "and '" + (String) sdf.format(jDateChooser10.getDate()) + "' ";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                jTable15.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
  JOptionPane.showConfirmDialog(null, e);
            }
            bohcount();
    }//GEN-LAST:event_jLabel9MouseClicked

    private void jLabel9MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseEntered
       jLabel9.setForeground(new Color(57,167,252,225));
    }//GEN-LAST:event_jLabel9MouseEntered

    private void jLabel9MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseExited
        jLabel9.setForeground(Color.BLACK);
    }//GEN-LAST:event_jLabel9MouseExited

    private void jTable16KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable16KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            jPanel4.setVisible(false);
            printbloan();
            this.dispose();
        }
        else if(evt.getKeyCode()==KeyEvent.VK_F10) {
            jPanel4.setVisible(true);
        }
        else if(evt.getKeyCode()==KeyEvent.VK_ESCAPE){
            this.dispose();
        }
    }//GEN-LAST:event_jTable16KeyPressed

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        jComboBox4.getSelectedIndex();
        jComboBox6.getSelectedIndex();
        all_ref();
        bloancount();
    }//GEN-LAST:event_jLabel10MouseClicked

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        try{
            String sql = "SELECT *"
                    + " FROM borrowed_tbl WHERE "
                    + "Book_title like '"+jComboBox4.getSelectedItem()+"' and"
                    + " Classification like '"+jComboBox6.getSelectedItem()+"'";

            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = (ResultSet) pst.executeQuery();
            jTable16.setModel(DbUtils.resultSetToTableModel(rs));
            }
            catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, ex);
            }
        bloancount();
    }//GEN-LAST:event_jLabel11MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(purchase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(purchase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(purchase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(purchase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new purchase().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox6;
    public static com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser10;
    private com.toedter.calendar.JDateChooser jDateChooser12;
    public static com.toedter.calendar.JDateChooser jDateChooser2;
    public static com.toedter.calendar.JDateChooser jDateChooser3;
    public static com.toedter.calendar.JDateChooser jDateChooser4;
    private com.toedter.calendar.JDateChooser jDateChooser5;
    private com.toedter.calendar.JDateChooser jDateChooser6;
    private com.toedter.calendar.JDateChooser jDateChooser7;
    private com.toedter.calendar.JDateChooser jDateChooser8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel153;
    private javax.swing.JLabel jLabel154;
    private javax.swing.JLabel jLabel155;
    private javax.swing.JLabel jLabel156;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane37;
    private javax.swing.JScrollPane jScrollPane38;
    private javax.swing.JScrollPane jScrollPane39;
    private javax.swing.JScrollPane jScrollPane40;
    private javax.swing.JScrollPane jScrollPane41;
    private javax.swing.JScrollPane jScrollPane42;
    private javax.swing.JScrollPane jScrollPane43;
    private javax.swing.JScrollPane jScrollPane44;
    private javax.swing.JScrollPane jScrollPane45;
    private javax.swing.JScrollPane jScrollPane46;
    private javax.swing.JTable jTable10;
    private javax.swing.JTable jTable11;
    private javax.swing.JTable jTable12;
    private javax.swing.JTable jTable13;
    private javax.swing.JTable jTable14;
    private javax.swing.JTable jTable15;
    private javax.swing.JTable jTable16;
    private javax.swing.JTable jTable7;
    private javax.swing.JTable jTable8;
    private javax.swing.JTable jTable9;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JLabel menu_title10;
    private javax.swing.JLabel menu_title100;
    private javax.swing.JLabel menu_title101;
    private javax.swing.JLabel menu_title102;
    private javax.swing.JLabel menu_title103;
    private javax.swing.JLabel menu_title104;
    private javax.swing.JLabel menu_title105;
    private javax.swing.JLabel menu_title106;
    private javax.swing.JLabel menu_title107;
    private javax.swing.JLabel menu_title108;
    private javax.swing.JLabel menu_title109;
    private javax.swing.JLabel menu_title11;
    private javax.swing.JLabel menu_title110;
    private javax.swing.JLabel menu_title111;
    private javax.swing.JLabel menu_title112;
    private javax.swing.JLabel menu_title113;
    private javax.swing.JLabel menu_title114;
    private javax.swing.JLabel menu_title115;
    private javax.swing.JLabel menu_title116;
    private javax.swing.JLabel menu_title117;
    private javax.swing.JLabel menu_title118;
    private javax.swing.JLabel menu_title119;
    private javax.swing.JLabel menu_title12;
    private javax.swing.JLabel menu_title120;
    private javax.swing.JLabel menu_title121;
    private javax.swing.JLabel menu_title122;
    private javax.swing.JLabel menu_title123;
    private javax.swing.JLabel menu_title124;
    private javax.swing.JLabel menu_title125;
    private javax.swing.JLabel menu_title126;
    private javax.swing.JLabel menu_title127;
    private javax.swing.JLabel menu_title128;
    private javax.swing.JLabel menu_title129;
    private javax.swing.JLabel menu_title13;
    private javax.swing.JLabel menu_title130;
    private javax.swing.JLabel menu_title131;
    private javax.swing.JLabel menu_title132;
    private javax.swing.JLabel menu_title133;
    private javax.swing.JLabel menu_title134;
    private javax.swing.JLabel menu_title138;
    private javax.swing.JLabel menu_title14;
    private javax.swing.JLabel menu_title140;
    private javax.swing.JLabel menu_title15;
    private javax.swing.JLabel menu_title16;
    private javax.swing.JLabel menu_title17;
    private javax.swing.JLabel menu_title18;
    private javax.swing.JLabel menu_title19;
    private javax.swing.JLabel menu_title20;
    private javax.swing.JLabel menu_title21;
    private javax.swing.JLabel menu_title22;
    private javax.swing.JLabel menu_title23;
    private javax.swing.JLabel menu_title24;
    private javax.swing.JLabel menu_title25;
    private javax.swing.JLabel menu_title26;
    private javax.swing.JLabel menu_title27;
    private javax.swing.JLabel menu_title28;
    private javax.swing.JLabel menu_title29;
    private javax.swing.JLabel menu_title3;
    private javax.swing.JLabel menu_title30;
    private javax.swing.JLabel menu_title31;
    private javax.swing.JLabel menu_title32;
    private javax.swing.JLabel menu_title33;
    private javax.swing.JLabel menu_title34;
    private javax.swing.JLabel menu_title35;
    private javax.swing.JLabel menu_title36;
    private javax.swing.JLabel menu_title37;
    private javax.swing.JLabel menu_title38;
    private javax.swing.JLabel menu_title39;
    private javax.swing.JLabel menu_title4;
    private javax.swing.JLabel menu_title40;
    private javax.swing.JLabel menu_title41;
    private javax.swing.JLabel menu_title42;
    private javax.swing.JLabel menu_title43;
    private javax.swing.JLabel menu_title44;
    private javax.swing.JLabel menu_title45;
    private javax.swing.JLabel menu_title46;
    private javax.swing.JLabel menu_title47;
    private javax.swing.JLabel menu_title48;
    private javax.swing.JLabel menu_title49;
    private javax.swing.JLabel menu_title5;
    private javax.swing.JLabel menu_title50;
    private javax.swing.JLabel menu_title51;
    private javax.swing.JLabel menu_title52;
    private javax.swing.JLabel menu_title53;
    private javax.swing.JLabel menu_title54;
    private javax.swing.JLabel menu_title55;
    private javax.swing.JLabel menu_title56;
    private javax.swing.JLabel menu_title57;
    private javax.swing.JLabel menu_title58;
    private javax.swing.JLabel menu_title59;
    private javax.swing.JLabel menu_title6;
    private javax.swing.JLabel menu_title60;
    private javax.swing.JLabel menu_title61;
    private javax.swing.JLabel menu_title62;
    private javax.swing.JLabel menu_title63;
    private javax.swing.JLabel menu_title64;
    private javax.swing.JLabel menu_title65;
    private javax.swing.JLabel menu_title66;
    private javax.swing.JLabel menu_title67;
    private javax.swing.JLabel menu_title68;
    private javax.swing.JLabel menu_title69;
    private javax.swing.JLabel menu_title7;
    private javax.swing.JLabel menu_title70;
    private javax.swing.JLabel menu_title71;
    private javax.swing.JLabel menu_title72;
    private javax.swing.JLabel menu_title73;
    private javax.swing.JLabel menu_title74;
    private javax.swing.JLabel menu_title75;
    private javax.swing.JLabel menu_title76;
    private javax.swing.JLabel menu_title77;
    private javax.swing.JLabel menu_title78;
    private javax.swing.JLabel menu_title79;
    private javax.swing.JLabel menu_title8;
    private javax.swing.JLabel menu_title80;
    private javax.swing.JLabel menu_title81;
    private javax.swing.JLabel menu_title82;
    private javax.swing.JLabel menu_title83;
    private javax.swing.JLabel menu_title84;
    private javax.swing.JLabel menu_title85;
    private javax.swing.JLabel menu_title86;
    private javax.swing.JLabel menu_title87;
    private javax.swing.JLabel menu_title88;
    private javax.swing.JLabel menu_title89;
    private javax.swing.JLabel menu_title9;
    private javax.swing.JLabel menu_title90;
    private javax.swing.JLabel menu_title91;
    private javax.swing.JLabel menu_title92;
    private javax.swing.JLabel menu_title93;
    private javax.swing.JLabel menu_title94;
    private javax.swing.JLabel menu_title95;
    private javax.swing.JLabel menu_title96;
    private javax.swing.JLabel menu_title97;
    private javax.swing.JLabel menu_title98;
    private javax.swing.JLabel menu_title99;
    private javax.swing.JButton nb_update14;
    private javax.swing.JButton nb_update15;
    private java.awt.Panel panel1;
    private java.awt.Panel panel2;
    private javax.swing.JPanel print;
    private javax.swing.JPanel print10;
    private javax.swing.JPanel print2;
    private javax.swing.JPanel print3;
    private javax.swing.JPanel print4;
    private javax.swing.JPanel print5;
    private javax.swing.JPanel print6;
    private javax.swing.JPanel print7;
    private javax.swing.JPanel print8;
    private javax.swing.JPanel print9;
    private javax.swing.JPanel starter;
    // End of variables declaration//GEN-END:variables
}
