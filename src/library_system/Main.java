/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library_system;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;
//import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author Cris Mateo Uriarte
 */
public class Main extends javax.swing.JFrame {
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;
    /**
     * Creates new form Main
     */
    String imgpath = null;
    String imgpath1 = null;
    
    public Main() {
        initComponents();
        conn = (Connection) MySqlConnect.ConnectDB();
        start();
        CurrentDate();
        all_ref();
        supp_list();
        brrr_list();
        //stat_list();
        class_list();
        classif_list();
        pub_list();
        course_list();
    }
    public void all_ref(){
        nb_ref();
        public_ref();
        pur_ref();
        brr_ref();
        acc_ref();
        supp_ref();
        si_ref();
        so_ref();
        sum_ref();
        loginv_ref();
        rb_ref();
        booklist_ref();
        brrdlog_ref();
        ohb_ref();
        brrd_ref();
        masb_ref();
        payable_total();
        myaccount_ohn();
        myaccount_bl();
        logcount();
        brrcount();
        ohb_total();
        classif_ref();
        pobooklist_ref();
        podesc_ref();
        rc_term();
        order_ref();
        book_order_ref();
        pub_ref();
        rc_totitem();
        save_po();
        po_log_ref();
        cancel_ref();
        collection_ref();
        delivery_ref();
        patqty_total();
        pay_total();
        course_ref();
        acclog_ref();
        userlog_ref();
    }
    
    public void accesslvl(){
        if(txt_level.getText().equals("User")){
        men_rec.setVisible(true);    
        men_po.setVisible(false);
        men_rep.setVisible(false);
        men_brrr.setVisible(false);
        men_brrd.setVisible(false);
        men_ua.setVisible(false);
        men_inv.setVisible(true);
        }
        else if(txt_level.getText().equals("Student") || txt_level.getText().equals("Faculty")){
        men_rec.setVisible(false);
        men_po.setVisible(false);
        men_rep.setVisible(false);
        men_brrr.setVisible(false);
        men_brrd.setVisible(false);
        men_ua.setVisible(false);
        men_inv.setVisible(false);
        }
        else if(txt_level.getText().equals("Admin")){
        men_rec.setVisible(true);    
        men_po.setVisible(true);
        men_rep.setVisible(true);
        men_brrr.setVisible(true);
        men_brrd.setVisible(true);
        men_ua.setVisible(true);
        men_inv.setVisible(true);
        }
        
    }
    
    public void termd(){
    int column = 0;
    int row = 0;
    String value = po_test.getModel().getValueAt(row, column).toString();

        dsc_id.setText(value);
    }
    
    public void rc_nb(){
    int row = nb_table.getRowCount();
        jLabel188.setText(String.valueOf(row));
    }
    
    public void rc_term(){
    int row = po_test.getRowCount();
        rc_po.setText(String.valueOf(row));
    }
    
    public void rc_totitem(){
    int row = po_table.getRowCount();
        brr_fn7.setText(String.valueOf(row));
    }
    
    public void payable_total (){
        try{
        String sql = "Select sum(Payable) from borrowed_tbl";
        pst=conn.prepareStatement(sql);
        rs=pst.executeQuery();
        if(rs.next()){
        String sum=rs.getString("sum(Payable)");
        brrd_fd1.setText(sum);
        }
        }
        catch (Exception e){
        JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void tb_total(){
        int sum = 0;
        for(int i = 0; i < po_table.getRowCount(); i++)
        {
            sum = sum + Integer.parseInt(po_table.getValueAt(i, 9).toString());
        }
        brr_fn8.setText(Integer.toString(sum));
    }
    
    public void tc_total(){
        int sum = 0;
        for(int i = 0; i < po_table.getRowCount(); i++)
        {
            sum = sum + Integer.parseInt(po_table.getValueAt(i, 10).toString());
        }
        brr_fn9.setText(Integer.toString(sum));
    }
    
    public void ohb_total (){
        int sum = 0;
        for(int i = 0; i < ohb_table.getRowCount(); i++)
        {
            sum = sum + Integer.parseInt(ohb_table.getValueAt(i, 8).toString());
        }
        nb_is2.setText(Integer.toString(sum));
    }
    
    public void bta_total (){
        int sum = 0;
        for(int i = 0; i < brrd_log_table.getRowCount(); i++)
        {
            sum = sum + Integer.parseInt(brrd_log_table.getValueAt(i, 12).toString());
        }
        brrd_fd1.setText(Integer.toString(sum));
    }
    
    public void patqty_total(){
        int sum = 0;
        for(int i = 0; i < rep_bo_table2.getRowCount(); i++)
        {
            sum = sum + Integer.parseInt(rep_bo_table2.getValueAt(i, 4).toString());
        }
        jLabel132.setText(Integer.toString(sum));
    }
    public void pay_total(){
        try{
        String sql = "Select sum(Price) from sales_tbl";
        pst=conn.prepareStatement(sql);
        rs=pst.executeQuery();
        if(rs.next()){
        String sum=rs.getString("sum(Price)");
        jLabel155.setText(sum);
        }
        }
        catch (Exception e){
        JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void start(){
    setBackground(new Color(0,0,0,150));
        loginpanel.setBackground(new Color(0,0,0,150));
        Login.setBackground(new Color(0,0,0,150));
        publicsearch.setBackground(new Color(0,0,0,150));
        jPanel68.setBackground(new Color(0,0,0,150));
        jPanel2.setBackground(new Color(0,0,0,150));
        jPanel3.setBackground(new Color(0,0,0,150));
        men_dash.setBackground(new Color(255,255,255));
        men_rec.setBackground(new Color(96,96,96));
        men_inv.setBackground(new Color(96,96,96));
        men_brrd.setBackground(new Color(96,96,96));
        men_brrr.setBackground(new Color(96,96,96));
        men_ua.setBackground(new Color(96,96,96));
        men_logout.setBackground(new Color(96,96,96));
        men_logout.setForeground(new Color(255,255,255));
        men_dash.setBackground(new Color(255,255,255));
        men_dash.setForeground(new Color(96,96,96));
        men_rep.setBackground(new Color(96,96,96));
        men_po.setBackground(new Color(96,96,96));
        men_rep.setForeground(new Color(255,255,255));
        men_po.setForeground(new Color(255,255,255));
        menu_title.setText("MY ACCOUNT");
        brrd_rem.setVisible(false);
        brrd_remcb.setVisible(false);
        class_save_hide.setVisible(false);
        sup_save2.setVisible(false);
        jLabel192.setVisible(false);
    }
    public void CurrentDate() {//date and time to toolbar running

        new Timer(0, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Date d = new Date();
                SimpleDateFormat s = new SimpleDateFormat("h:mm aa");
                txt_time.setText(s.format(d));

                SimpleDateFormat st = new SimpleDateFormat("MM/dd/yyyy");
                txt_date1.setText(st.format(d));
                
            }
        })
                .start();
    } 
    private void supp_list(){//filter encode
        po_sup.removeAllItems();
    String sqll = "select * from supplier_tbl";
    try{
        pst= conn.prepareStatement(sqll);
        rs = pst.executeQuery();
        while(rs.next()){
            po_sup.addItem(rs.getString("Supplier_Name"));
        }
    }catch (Exception e) {
    }
    } 
    private void brrr_list(){//filter encode
        
        //jComboBox2.removeAllItems();
    String sqll = "select * from borrower_tbl";
    try{
        pst= conn.prepareStatement(sqll);
        rs = pst.executeQuery();
        while(rs.next()){
            
            //jComboBox2.addItem(rs.getString("Surname"));
        }
    }catch (Exception e) {
    }
    } 
//    private void stat_list(){//filter encode
//        brrd_stat.removeAllItems();
//    String sqll = "select * from borrower_tbl";
//    try{
//        pst= conn.prepareStatement(sqll);
//        rs = pst.executeQuery();
//        while(rs.next()){
//            brrd_stat.addItem(rs.getString("Status"));
//        }
//    }catch (Exception e) {
//    }
//    } 
    public void classif_list(){
        nb_class.removeAllItems();
        po_cl.removeAllItems();
        jComboBox1.removeAllItems();
    String sqll = "select * from classification_tbl";
    try{
        pst= conn.prepareStatement(sqll);
        rs = pst.executeQuery();
        while(rs.next()){
            nb_class.addItem(rs.getString("Classname"));
            po_cl.addItem(rs.getString("Classname"));
            jComboBox1.addItem(rs.getString("Classname"));
        }
    }catch (Exception e) {
    }
    }
    public void pub_list(){
        nb_cpub.removeAllItems();
    String sqll = "select * from publisher_tbl";
    try{
        pst= conn.prepareStatement(sqll);
        rs = pst.executeQuery();
        while(rs.next()){
            nb_cpub.addItem(rs.getString("Publisher"));
        }
    }catch (Exception e) {
    }
    }
    public void course_list(){
        brr_cr.removeAllItems();
    String sqll = "select * from course_tbl";
    try{
        pst= conn.prepareStatement(sqll);
        rs = pst.executeQuery();
        while(rs.next()){
            brr_cr.addItem(rs.getString("Course"));
        }
    }catch (Exception e) {
    }
    }
    private void class_list(){//filter encode
        brrd_class.removeAllItems();
    String sqll = "select * from classification_tbl";
    try{
        pst= conn.prepareStatement(sqll);
        rs = pst.executeQuery();
        while(rs.next()){
            brrd_class.addItem(rs.getString("Classname"));
        }
    }catch (Exception e) {
    }
    } 
    public void login(){
    try {
        String sql = "SELECT * FROM useraccount_tbl where Username =? and Password =?";
            pst = (PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, user.getText());
            pst.setString(2, pass.getText());
            
            rs = pst.executeQuery();
            if (rs.next()) {
            JOptionPane.showMessageDialog(null, "Acess Granted!");
            Login.setVisible(false);
            publicsearch.setVisible(false);
            Menu.setVisible(true);
            mmm.setVisible(true);
            head.setVisible(true);
            stock.setVisible(true);
            

            pst = (PreparedStatement) conn.prepareStatement("SELECT ID, First_Name, Middle_Name, Surname, Level  FROM useraccount_tbl where Username= '" + user.getText() + "' and Password= '" + pass.getText() + "' ");
            rs = pst.executeQuery();
            if(rs.next()){
                String add = rs.getString("ID");
                String add1 = rs.getString("First_Name");
                String add2 = rs.getString("Middle_Name");
                String add3 = rs.getString("Surname");
                String add4 = rs.getString("Level");
                txt_name.setText(add3+", "+add1+" "+add2);
                txt_level.setText(add4);
                idd.setText(add);
                sumup.setText("0");
            
            }
            menu_title1.setText("Hi, "+txt_name.getText()+" ("+txt_level.getText()+")");
        }
            else {
            JOptionPane.showMessageDialog(null, "incorrect username or password");
            int a;
            //a = Double.parseDouble(loginatt.getText());
            a = Integer.parseInt(sumup.getText());
            a = a+1;
            sumup.setText(Integer.toString(a));
            if(a==3){
                JOptionPane.showMessageDialog(null, "Warning 3 attempts occured!");
            System.exit(0);
            }
            }
    }
        catch (SQLException x) {
        //JOptionPane.showMessageDialog(null, "Incorrect Username or Password!");
        }
        user.setText("");
        pass.setText("");
        start();
        accesslvl();
        myaccount_ohn();
        myaccount_bl();
        logcount();
        ohb_total();
        myaccount_details();
    }
    public void classif_ref(){
        try {
            String sql = "SELECT Class_Number, Classname FROM classification_tbl ORDER BY Classname ASC";
            pst = (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();

            class_table1.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    public void pobooklist_ref(){
        try {
            String sql = "SELECT Book_title, Classification, Author, Price FROM stockin_tbl ORDER BY Book_title ASC";
            pst = (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();

            pobl_table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    public void nb_ref(){
        try {
            String sql = "SELECT Book_ID, ISBN_No, Book_title, Author, Quantity, Call_Number,"
                    + " Classification, Publisher, Edition, Copy_Right_Year, Price, Date_Arrival"
                    + " FROM stockin_tbl ORDER BY Book_ID DESC";
            pst = (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();

            nb_table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    public void pur_ref(){
        try {
            String sql = "SELECT *"
                    + " FROM purchased_tbl ORDER BY ID DESC";
            pst = (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();

            nb_table1.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    public void brr_ref(){
        try {
            String sql = "SELECT * FROM borrower_tbl ORDER BY Library_ID DESC";
            pst = (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();

            brr_table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    public void acc_ref(){
        try {
            String sql = "SELECT * FROM useraccount_tbl ORDER BY ID DESC";
            pst = (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();

            acc_table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    public void supp_ref(){
        try {
            String sql = "SELECT * FROM supplier_tbl ORDER BY Supplier_ID DESC";
            pst = (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();

            sup_table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    public void si_ref(){
        try {
            String sql = "SELECT * FROM holding_tbl ORDER BY Book_title ASC";
            pst = (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();

            si_table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    public void sum_ref(){
        try {
            String sql = "SELECT ISBN_No, Book_title, Classification, On_Hand, Borrowed, Damage, "
                    + "Total_Holding FROM holding_tbl ORDER BY Book_title ASC";
            pst = (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();

            sum_table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    public void so_ref(){
        try {
            String sql = "SELECT * FROM damage_tbl ORDER BY Book_title ASC";
            pst = (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();

            so_table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    public void loginv_ref(){
        try {
            String sql = "SELECT * FROM invlog_tbl ORDER BY Book_title ASC";
            pst = (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();

            log_table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    public void rb_ref(){
        try {
            String sql = "SELECT Library_ID, First_Name, Surname, Status FROM borrower_tbl ORDER BY Library_ID ASC";
            pst = (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();

            brrd_rb_table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    public void booklist_ref(){
        try {
            String sql = "SELECT Book_title, Price, Classification, Quantity FROM stockin_tbl ORDER BY Book_title ASC";
            pst = (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();

            brrd_bl_table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    public void brrdlog_ref(){
        try {
            String sql = "SELECT * FROM borrowed_tbl ORDER BY ID DESC";
            pst = (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();

            brrd_log_table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    public void ohb_ref(){
        try {
            String sql = "SELECT * FROM borrowed_tbl ORDER BY ID DESC";
            pst = (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();

            ohb_table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    public void brrd_ref(){
        try {
            String sql = "SELECT * FROM brrdlog_tbl ORDER BY ID DESC";
            pst = (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();

            borrowedlog_table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    public void masb_ref(){
        try {
            String sql = "SELECT ISBN_No, Book_title, Author, Quantity, Call_Number, Classification, Edition, "
                    + "Copy_Right_Year, Price FROM stockin_tbl ORDER BY Book_title ASC";
            pst = (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();

            masb_table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    public void podesc_ref(){
        try {
            String sql = "SELECT * FROM po_num_tbl ORDER BY ID DESC";
            pst = (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();

            po_test.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    public void order_ref(){
        try {
            String sql = "SELECT * FROM order_tbl ORDER BY No ASC";
            pst = (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();

            po_table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    public void book_order_ref(){
        try {
            String sql = "SELECT * FROM requested_tbl ORDER BY ID ASC";
            pst = (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();

            rep_bo_table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    public void pub_ref(){
        try {
            String sql = "SELECT Publisher_ID, Publisher FROM publisher_tbl ORDER BY Publisher_ID ASC";
            pst = (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();

            sup_table1.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    public void po_log_ref(){
        try {
            String sql = "SELECT * FROM po_log_tbl ORDER BY ID DESC";
            pst = (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();

            si_table1.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    public void cancel_ref(){
        try {
            String sql = "SELECT * FROM cancel_tbl ORDER BY ID DESC";
            pst = (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();

            rep_bo_table1.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    public void collection_ref(){
        try {
            String sql = "SELECT * FROM sales_tbl ORDER BY ID DESC";
            pst = (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();

            rep_bo_table2.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    public void delivery_ref(){
        try {
            String sql = "SELECT * FROM delivery_tbl ORDER BY ID DESC";
            pst = (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();

            rep_bo_table4.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
     public void course_ref(){
        try {
            String sql = "SELECT * FROM course_tbl ORDER BY ID DESC";
            pst = (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();

            jTable3.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
     public void userlog_ref(){
        try {
            String sql = "SELECT * FROM user_log_tbl ORDER BY ID DESC";
            pst = (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();

            jTable1.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
     public void acclog_ref(){
        try {
            String sql = "SELECT * FROM account_log_tbl ORDER BY ID DESC";
            pst = (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();

            jTable2.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    public void public_ref(){
        try {
            String sql = "SELECT ISBN_No, Book_title, Author, Call_Number, Classification, Publisher, Edition, Copy_Right_Year"
                    + " FROM stockin_tbl ";
            pst = (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();

            sumpatable.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    public void myaccount_ohn(){
        try {
           String sql = "SELECT Full_Name, Book_title, Classification, Fines, "
                   + "Quantity, Borrowed_Date, Return_Date, Remarks, Payable"
                    + " FROM borrowed_tbl WHERE "
                    + "Full_Name like ?";

            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, "%" + txt_name.getText() + "%");;

            rs = (ResultSet) pst.executeQuery();
            ohb_table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, ex);
        }
    }
    public void myaccount_bl(){
        try {
           String sql = "SELECT Full_Name, Book_title, Classification, Borrowed_Date"
                    + " FROM brrdlog_tbl WHERE "
                    + "Full_Name like ?";

            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, "%" + txt_name.getText() + "%");;

            rs = (ResultSet) pst.executeQuery();
            bl_table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, ex);
        }
    }
    public void nb_clr(){
        nb_is.setText("");
        nb_bt.setText("");
        nb_aut.setText("");
        nb_qty.setText("0");
        nb_cn.setText("");
        nb_class.setSelectedItem("--Choose Book Classification--");
        nb_cpub.setSelectedIndex(0);
        nb_edi.setText("");
        nb_cry.setDate(null);
        nb_prc.setText("");
        nb_da.setDate(null);
        jLabel187.setText("");
    }
    public void nb_disable(){
        nb_is.setEnabled(false);
        nb_bt.setEnabled(false);
        nb_aut.setEditable(false);
        nb_qty.setEnabled(false);
        nb_cn.setEnabled(false);
        nb_class.setEnabled(false);
        class_add2.setEnabled(false);
        nb_edi.setEnabled(false);
        nb_cry.setEnabled(false);
        nb_prc.setEnabled(false);
        nb_da.setEnabled(false);
        class_add.setEnabled(false);
        nb_cpub.setEnabled(false);
    }
    public void nb_able(){
        nb_is.setEnabled(true);
        nb_bt.setEnabled(true);
        nb_aut.setEnabled(true);
        nb_qty.setEnabled(true);
        nb_cn.setEnabled(true);
        nb_class.setEnabled(true);
        nb_cpub.setEnabled(true);
        //nb_sup.setEnabled(true);
        nb_edi.setEnabled(true);
        nb_cry.setEnabled(true);
        nb_prc.setEnabled(true);
        nb_da.setEnabled(true);
    }
    public void brr_able(){
        brr_fn.setEnabled(true);
        brr_mn.setEnabled(true);
        brr_sn.setEnabled(true);
        brr_idt.setEnabled(true);
        brr_cr.setEnabled(true);
        brr_yr.setEnabled(true);
        brr_idn.setEnabled(true);
        brr_stat.setEnabled(true);
        brr_add.setEnabled(true);
        brr_bd.setEnabled(true);
        course_add.setEnabled(true);
    }
    public void brr_disable(){
        brr_fn.setEnabled(false);
        brr_mn.setEnabled(false);
        brr_sn.setEnabled(false);
        brr_idt.setEnabled(false);
        brr_cr.setEnabled(false);
        brr_yr.setEnabled(false);
        brr_idn.setEnabled(false);
        brr_stat.setEnabled(false);
        brr_add.setEnabled(false);
        brr_bd.setEnabled(false);
        course_add.setEnabled(false);
    }
    public void brr_clr(){
        brr_fn.setText("");
        brr_mn.setText("");
        brr_sn.setText("");
        brr_idt.setSelectedItem("--Choose Valid ID--");
        brr_cr.setSelectedItem("--Choose Course--");
        brr_yr.setSelectedItem("--Choose Year Level--");
        brr_idn.setText("");
        brr_stat.setText("");
        brr_add.setText("");
        brr_bd.setDate(null);
        brr_image.setIcon(new ImageIcon(new ImageIcon("").getImage().getScaledInstance(brr_image.getWidth(), brr_image.getHeight(), Image.SCALE_DEFAULT)));
    }
    public void acc_able(){
        acc_fn.setEnabled(true);
        acc_ffmn.setEnabled(true);
        acc_ffsn.setEnabled(true);
        acc_mid.setEnabled(true);
        acc_pos.setEnabled(true);
        acc_user.setEnabled(true);
        acc_pass.setEnabled(true);
        acc_con.setEnabled(true);
        acc_lvl.setEnabled(true);
        acc_mn.setEnabled(true);
        acc_nb.setEnabled(true);
        acc_inv.setEnabled(true);
        acc_brrd.setEnabled(true);
        acc_rep.setEnabled(true);
        acc_brrr.setEnabled(true);
        acc_acc.setEnabled(true);
    }
    public void acc_disable(){
        acc_fn.setEnabled(false);
        acc_ffmn.setEnabled(false);
        acc_ffsn.setEnabled(false);
        acc_mid.setEnabled(false);
        acc_pos.setEnabled(false);
        acc_user.setEnabled(false);
        acc_pass.setEnabled(false);
        acc_con.setEnabled(false);
        acc_lvl.setEnabled(false);
        acc_mn.setEnabled(false);
        acc_nb.setEnabled(false);
        acc_inv.setEnabled(false);
        acc_brrd.setEnabled(false);
        acc_rep.setEnabled(false);
        acc_brrr.setEnabled(false);
        acc_acc.setEnabled(false);
    }
    public void acc_clr(){
        acc_fn.setText("");
        acc_ffmn.setText("");
        acc_ffsn.setText("");
        acc_mid.setText("");;
        acc_pos.setText("");;
        acc_user.setText("");
        acc_pass.setText("");
        acc_con.setText("");
        acc_lvl.setSelectedItem("--Please Select--");
        acc_mn.setText("");
        acc_image.setIcon(new ImageIcon(new ImageIcon("").getImage().getScaledInstance(acc_image.getWidth(), acc_image.getHeight(), Image.SCALE_DEFAULT)));

    }
    public void supp_able(){
        sup_name.setEnabled(true);
        sup_add.setEnabled(true);
        sup_con.setEnabled(true);
        sup_em.setEnabled(true);
        sup_enc.setEnabled(true);
        sup_cp.setEnabled(true);
    }
    public void supp_disable(){
        sup_name.setEnabled(false);
        sup_add.setEnabled(false);
        sup_con.setEnabled(false);
        sup_em.setEnabled(false);
        sup_enc.setEnabled(false);
        sup_cp.setEnabled(false);
    }
    public void supp_clr(){
        sup_id.setText("");
        sup_name.setText("");
        sup_add.setText("");
        sup_con.setText("");
        sup_em.setText("");
        sup_cp.setText("");
        sup_enc.setDate(null);
    }
    public void brrd_clr(){
        brrd_fn.setText("");
        brrd_libid1.setText("");
        brrd_stat.setSelectedItem("--Choose Status--");
        brrd_bt.setText("");
        brrd_bp.setText("");
        brrd_class.setSelectedIndex(0);
        brrd_fd.setText("");
        brrd_qty.setText("1");
        brrd_qty.setForeground(Color.BLACK);
        brrd_min.setEnabled(false);
        borroweddate.setText("");
        returndate.setText("");
        brrd_remcb.setSelectedItem("Borrowed");
        brrd_remcb.setVisible(false);
        brrd_rem.setVisible(false);
        brrd_fd2.setText("");
    }
    public void po_clr(){
        po_or.setText("Generate PO Number");
        po_date.setDate(null);
        po_sup.setSelectedIndex(0);
        po_bt.setText("");
        po_au.setText("");
        po_cl.setSelectedIndex(0);
        po_pr.setText("");
        po_qt.setText("0");
    }
    public void po2_clr(){
        po_bt.setText("");
        po_au.setText("");
        po_cl.setSelectedIndex(0);
        po_pr.setText("");
        po_qt.setText("1");
    }
    public void course_clr(){
        so_enc1.setText("");
        so_date1.setText("");
        so_time1.setText("");
        so_time2.setText("");
        jLabel198.setText("");
        jComboBox2.setSelectedIndex(0);
    }
    public void drp_list(){
        if(sum_class.getSelectedItem().equals("--Choose Book Classification--")){
        all_ref();
        }
        else {
        try {
            String sql = "SELECT ISBN_No, Book_title, Classification, On_Hand, Borrowed, Damage, Total_Holding"
                    + " FROM holding_tbl WHERE "
                    + "Classification like ? ";

            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, "%" + sum_class.getSelectedItem() + "%");

            rs = (ResultSet) pst.executeQuery();
            sum_table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, ex);
        }
        }
    }
    public void logcount (){//code for cointing table account logs
    int row = ohb_table.getRowCount();
        nb_is1.setText(String.valueOf(row));
    }
    public void brrcount (){//code for cointing table account logs
    int row = brr_table.getRowCount();
        brr_count.setText(String.valueOf(row));
    }
    
    public void myaccount_details(){
        try{
        
            String sql="SELECT ID, First_Name, Middle_Name, Surname, Username, Password, Mobile_Number, Photo  FROM useraccount_tbl where ID = '" + (String) idd.getText() + "' ";
            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();
        
            if(rs.next()){
            String name1 =rs.getString("ID");
            String name2 =rs.getString("First_Name");
            String name3 =rs.getString("Middle_Name");
            String name4 =rs.getString("Surname");
            String name5 =rs.getString("Username");
            String name6 =rs.getString("Password");
            String name7 =rs.getString("Mobile_Number");
            
            my_id.setText(name1);
            my_fn.setText(name2);
            my_mnn.setText(name3);
            my_sn.setText(name4);
            my_user.setText(name5);
            my_pass.setText(name6);
            my_mn.setText(name7);
            
            byte[] img = rs.getBytes("Photo");
                ImageIcon image1 =  new ImageIcon(img);
                Image im = image1.getImage();
                Image myImage = im.getScaledInstance(my_image.getWidth(), my_image.getHeight(), Image.SCALE_SMOOTH );
                ImageIcon newImage = new ImageIcon(myImage);
                my_image.setIcon(newImage);
            }
            }
            catch (Exception e) {
            //JOptionPane.showMessageDialog(null, "no image");
            acc_image.setText("Select Image");
            acc_image.setIcon(new ImageIcon(new ImageIcon("").getImage().getScaledInstance(acc_image.getWidth(), acc_image.getHeight(), Image.SCALE_DEFAULT)));
            }
    }
    public void save_po(){
    if(brr_fn7.getText().equals("")){
    nb_update7.setEnabled(false);
    }
    else{
    nb_update7.setEnabled(true);
    }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        jFileChooser2 = new javax.swing.JFileChooser();
        jPanel1 = new javax.swing.JPanel();
        Login = new javax.swing.JPanel();
        loginpanel = new javax.swing.JPanel();
        sumup = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        user = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        pass = new javax.swing.JPasswordField();
        showpass = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        exit = new javax.swing.JLabel();
        minimize = new javax.swing.JLabel();
        publicsearch = new javax.swing.JPanel();
        nb_new8 = new javax.swing.JButton();
        jPanel68 = new javax.swing.JPanel();
        jLabel164 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<String>();
        jLabel165 = new javax.swing.JLabel();
        jLabel166 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jTextField3 = new javax.swing.JTextField();
        jLabel167 = new javax.swing.JLabel();
        jLabel168 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel169 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel170 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel171 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel172 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel173 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jLabel174 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        nb_new9 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane31 = new javax.swing.JScrollPane();
        sumpatable = new javax.swing.JTable();
        jLabel163 = new javax.swing.JLabel();
        Menu = new javax.swing.JPanel();
        mmm = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        men_dash = new javax.swing.JLabel();
        men_rec = new javax.swing.JLabel();
        men_inv = new javax.swing.JLabel();
        men_brrd = new javax.swing.JLabel();
        men_logout = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txt_time = new javax.swing.JLabel();
        txt_date1 = new javax.swing.JLabel();
        men_rep = new javax.swing.JLabel();
        men_po = new javax.swing.JLabel();
        head = new javax.swing.JPanel();
        menu_title = new javax.swing.JLabel();
        txt_name1 = new javax.swing.JLabel();
        menu_title1 = new javax.swing.JLabel();
        stock = new javax.swing.JPanel();
        dashboard = new javax.swing.JPanel();
        booksummary = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel37 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        ohb_table = new javax.swing.JTable();
        ssss = new javax.swing.JPanel();
        jScrollPane13 = new javax.swing.JScrollPane();
        bl_table = new javax.swing.JTable();
        jPanel38 = new javax.swing.JPanel();
        jLabel79 = new javax.swing.JLabel();
        nb_is1 = new javax.swing.JTextField();
        jLabel80 = new javax.swing.JLabel();
        nb_is2 = new javax.swing.JTextField();
        jPanel39 = new javax.swing.JPanel();
        jScrollPane15 = new javax.swing.JScrollPane();
        jPanel40 = new javax.swing.JPanel();
        jLabel89 = new javax.swing.JLabel();
        ohb_bt = new javax.swing.JTextField();
        jLabel90 = new javax.swing.JLabel();
        ohb_class = new javax.swing.JTextField();
        jLabel91 = new javax.swing.JLabel();
        ohb_fines = new javax.swing.JTextField();
        jLabel92 = new javax.swing.JLabel();
        ohb_qty = new javax.swing.JTextField();
        jLabel93 = new javax.swing.JLabel();
        ohb_bd = new javax.swing.JTextField();
        jLabel94 = new javax.swing.JLabel();
        ohb_rd = new javax.swing.JTextField();
        jLabel95 = new javax.swing.JLabel();
        ohn_rem = new javax.swing.JTextField();
        jLabel96 = new javax.swing.JLabel();
        ohb_pay = new javax.swing.JTextField();
        nb_new5 = new javax.swing.JButton();
        account = new javax.swing.JPanel();
        nb_new4 = new javax.swing.JButton();
        jPanel43 = new javax.swing.JPanel();
        jLabel82 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        my_fn = new javax.swing.JTextField();
        jLabel88 = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
        my_mn = new javax.swing.JTextField();
        my_image = new javax.swing.JLabel();
        acc_browse1 = new javax.swing.JButton();
        my_con = new javax.swing.JPasswordField();
        my_pass = new javax.swing.JPasswordField();
        my_user = new javax.swing.JTextField();
        jLabel179 = new javax.swing.JLabel();
        my_mnn = new javax.swing.JTextField();
        jLabel180 = new javax.swing.JLabel();
        my_sn = new javax.swing.JTextField();
        jPanel44 = new javax.swing.JPanel();
        acc_update1 = new javax.swing.JButton();
        my_id = new javax.swing.JLabel();
        findbook = new javax.swing.JPanel();
        jPanel41 = new javax.swing.JPanel();
        jLabel81 = new javax.swing.JLabel();
        masb_search = new javax.swing.JTextField();
        jPanel42 = new javax.swing.JPanel();
        jScrollPane16 = new javax.swing.JScrollPane();
        masb_table = new javax.swing.JTable();
        nb_new6 = new javax.swing.JButton();
        report = new javax.swing.JPanel();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel53 = new javax.swing.JPanel();
        jPanel52 = new javax.swing.JPanel();
        jScrollPane22 = new javax.swing.JScrollPane();
        rep_bo_table = new javax.swing.JTable();
        jLabel123 = new javax.swing.JLabel();
        po_or2 = new javax.swing.JTextField();
        nb_update8 = new javax.swing.JButton();
        jLabel138 = new javax.swing.JLabel();
        jPanel54 = new javax.swing.JPanel();
        jPanel60 = new javax.swing.JPanel();
        jScrollPane26 = new javax.swing.JScrollPane();
        rep_bo_table1 = new javax.swing.JTable();
        jLabel125 = new javax.swing.JLabel();
        po_or3 = new javax.swing.JTextField();
        nb_update9 = new javax.swing.JButton();
        jPanel61 = new javax.swing.JPanel();
        jPanel62 = new javax.swing.JPanel();
        jScrollPane27 = new javax.swing.JScrollPane();
        rep_bo_table2 = new javax.swing.JTable();
        jLabel131 = new javax.swing.JLabel();
        po_or4 = new javax.swing.JTextField();
        jLabel132 = new javax.swing.JLabel();
        nb_update11 = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel153 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLabel154 = new javax.swing.JLabel();
        jLabel155 = new javax.swing.JLabel();
        nb_update13 = new javax.swing.JButton();
        jLabel156 = new javax.swing.JLabel();
        jLabel157 = new javax.swing.JLabel();
        nb_update14 = new javax.swing.JButton();
        jPanel63 = new javax.swing.JPanel();
        jPanel65 = new javax.swing.JPanel();
        nb_update16 = new javax.swing.JButton();
        jScrollPane29 = new javax.swing.JScrollPane();
        rep_bo_table4 = new javax.swing.JTable();
        nb_update12 = new javax.swing.JButton();
        jDateChooser3 = new com.toedter.calendar.JDateChooser();
        jLabel158 = new javax.swing.JLabel();
        jLabel159 = new javax.swing.JLabel();
        po_or5 = new javax.swing.JTextField();
        jLabel160 = new javax.swing.JLabel();
        jDateChooser4 = new com.toedter.calendar.JDateChooser();
        nb_update15 = new javax.swing.JButton();
        Records = new javax.swing.JPanel();
        jTabbedPane6 = new javax.swing.JTabbedPane();
        jPanel73 = new javax.swing.JPanel();
        si_save3 = new javax.swing.JButton();
        jPanel67 = new javax.swing.JPanel();
        jScrollPane28 = new javax.swing.JScrollPane();
        class_table1 = new javax.swing.JTable();
        jPanel66 = new javax.swing.JPanel();
        jLabel161 = new javax.swing.JLabel();
        si_isbn2 = new javax.swing.JTextField();
        jLabel162 = new javax.swing.JLabel();
        class_save_hide1 = new javax.swing.JPanel();
        si_save4 = new javax.swing.JButton();
        btn_del1 = new javax.swing.JButton();
        class_num1 = new javax.swing.JTextField();
        cl_io = new javax.swing.JLabel();
        jPanel74 = new javax.swing.JPanel();
        sup_save2 = new javax.swing.JButton();
        jLabel116 = new javax.swing.JLabel();
        sup_search1 = new javax.swing.JTextField();
        jPanel56 = new javax.swing.JPanel();
        jScrollPane23 = new javax.swing.JScrollPane();
        sup_table1 = new javax.swing.JTable();
        jPanel57 = new javax.swing.JPanel();
        jLabel117 = new javax.swing.JLabel();
        jLabel118 = new javax.swing.JLabel();
        sup_name1 = new javax.swing.JTextField();
        sup_add1 = new javax.swing.JTextField();
        sup_save1 = new javax.swing.JButton();
        sup_update1 = new javax.swing.JButton();
        sup_delete1 = new javax.swing.JButton();
        jPanel75 = new javax.swing.JPanel();
        sup_save3 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        sup_search = new javax.swing.JTextField();
        jPanel21 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        sup_table = new javax.swing.JTable();
        jPanel20 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        sup_name = new javax.swing.JTextField();
        sup_add = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        sup_em = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        sup_con = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        sup_enc = new com.toedter.calendar.JDateChooser();
        jLabel98 = new javax.swing.JLabel();
        sup_cp = new javax.swing.JTextField();
        jPanel22 = new javax.swing.JPanel();
        sup_new = new javax.swing.JButton();
        sup_save = new javax.swing.JButton();
        sup_update = new javax.swing.JButton();
        sup_delete = new javax.swing.JButton();
        jPanel76 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        nb_is = new javax.swing.JTextField();
        nb_bt = new javax.swing.JTextField();
        nb_cn = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        nb_class = new javax.swing.JComboBox<String>();
        nb_edi = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        nb_qty = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        nb_aut = new javax.swing.JTextField();
        nb_prc = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        nb_da = new com.toedter.calendar.JDateChooser();
        class_add = new javax.swing.JButton();
        jLabel119 = new javax.swing.JLabel();
        nb_cpub = new javax.swing.JComboBox<String>();
        class_add2 = new javax.swing.JButton();
        nb_cry = new com.toedter.calendar.JDateChooser();
        jLabel187 = new javax.swing.JLabel();
        jLabel188 = new javax.swing.JLabel();
        jLabel189 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        nb_search = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jTabbedPane5 = new javax.swing.JTabbedPane();
        jPanel58 = new javax.swing.JPanel();
        jScrollPane24 = new javax.swing.JScrollPane();
        nb_table1 = new javax.swing.JTable();
        jPanel59 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        nb_table = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        nb_new = new javax.swing.JButton();
        nb_save = new javax.swing.JButton();
        nb_update = new javax.swing.JButton();
        nb_delete = new javax.swing.JButton();
        jPanel77 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        acc_fn = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        acc_lvl = new javax.swing.JComboBox<String>();
        jLabel37 = new javax.swing.JLabel();
        acc_mn = new javax.swing.JTextField();
        acc_image = new javax.swing.JLabel();
        acc_browse = new javax.swing.JButton();
        acc_con = new javax.swing.JPasswordField();
        acc_pass = new javax.swing.JPasswordField();
        acc_user = new javax.swing.JTextField();
        jLabel175 = new javax.swing.JLabel();
        acc_ffsn = new javax.swing.JTextField();
        acc_ffmn = new javax.swing.JTextField();
        jLabel176 = new javax.swing.JLabel();
        jLabel177 = new javax.swing.JLabel();
        jLabel178 = new javax.swing.JLabel();
        acc_mid = new javax.swing.JTextField();
        acc_pos = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jPanel11 = new javax.swing.JPanel();
        acc_new = new javax.swing.JButton();
        acc_save = new javax.swing.JButton();
        acc_update = new javax.swing.JButton();
        acc_delete = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        acc_table = new javax.swing.JTable();
        jPanel78 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        brr_fn = new javax.swing.JTextField();
        brr_stat = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        brr_bd = new com.toedter.calendar.JDateChooser();
        jLabel31 = new javax.swing.JLabel();
        brr_idn = new javax.swing.JTextField();
        brr_image = new javax.swing.JLabel();
        brr_browse = new javax.swing.JButton();
        brr_yr = new javax.swing.JComboBox<String>();
        brr_add = new javax.swing.JTextField();
        brr_mn = new javax.swing.JTextField();
        jLabel181 = new javax.swing.JLabel();
        jLabel182 = new javax.swing.JLabel();
        brr_idt = new javax.swing.JComboBox<String>();
        jLabel183 = new javax.swing.JLabel();
        brr_cr = new javax.swing.JComboBox<String>();
        brr_sn = new javax.swing.JTextField();
        jLabel184 = new javax.swing.JLabel();
        course_add = new javax.swing.JButton();
        jLabel186 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        brr_new = new javax.swing.JButton();
        brr_save = new javax.swing.JButton();
        brr_update = new javax.swing.JButton();
        brr_delete = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        brr_table = new javax.swing.JTable();
        brr_search = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel185 = new javax.swing.JLabel();
        brr_count = new javax.swing.JTextField();
        Bookloan = new javax.swing.JPanel();
        jPanel30 = new javax.swing.JPanel();
        jLabel62 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        brrd_bp = new javax.swing.JTextField();
        brrd_rem = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        brrd_class = new javax.swing.JComboBox<String>();
        jLabel72 = new javax.swing.JLabel();
        brrd_bt = new javax.swing.JTextField();
        brrd_qty = new javax.swing.JTextField();
        brrd_fn = new javax.swing.JTextField();
        jLabel73 = new javax.swing.JLabel();
        brrd_stat = new javax.swing.JComboBox<String>();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel74 = new javax.swing.JLabel();
        brrd_add = new javax.swing.JButton();
        brrd_min = new javax.swing.JButton();
        brrd_remcb = new javax.swing.JComboBox<String>();
        brrd_fd = new javax.swing.JTextField();
        jLabel78 = new javax.swing.JLabel();
        borroweddate = new javax.swing.JTextField();
        brrd_rem1 = new javax.swing.JLabel();
        returndate = new javax.swing.JTextField();
        brrd_libid1 = new javax.swing.JTextField();
        jLabel190 = new javax.swing.JLabel();
        jLabel191 = new javax.swing.JLabel();
        jPanel31 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel32 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        brrd_rb_table = new javax.swing.JTable();
        jPanel33 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        brrd_bl_table = new javax.swing.JTable();
        jPanel34 = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        brrd_log_table = new javax.swing.JTable();
        brrd_uprem = new javax.swing.JButton();
        brrd_fd1 = new javax.swing.JTextField();
        jLabel75 = new javax.swing.JLabel();
        brrd_return = new javax.swing.JButton();
        jLabel77 = new javax.swing.JLabel();
        brrd_fd2 = new javax.swing.JTextField();
        brrd_clear1 = new javax.swing.JButton();
        jTextField11 = new javax.swing.JTextField();
        jLabel192 = new javax.swing.JLabel();
        jPanel36 = new javax.swing.JPanel();
        jScrollPane14 = new javax.swing.JScrollPane();
        borrowedlog_table = new javax.swing.JTable();
        brrd_fn2 = new javax.swing.JComboBox<String>();
        jLabel69 = new javax.swing.JLabel();
        brrd_fd3 = new javax.swing.JTextField();
        jPanel35 = new javax.swing.JPanel();
        si_calc1 = new javax.swing.JButton();
        brrd_clear = new javax.swing.JButton();
        inventory = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel19 = new javax.swing.JPanel();
        jPanel49 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        po_qt = new javax.swing.JTextField();
        jLabel103 = new javax.swing.JLabel();
        jLabel104 = new javax.swing.JLabel();
        po_sup = new javax.swing.JComboBox<String>();
        po_date = new com.toedter.calendar.JDateChooser();
        po_or = new javax.swing.JTextField();
        jLabel105 = new javax.swing.JLabel();
        po_il = new javax.swing.JButton();
        po_ne = new javax.swing.JButton();
        po_bt = new javax.swing.JTextField();
        po_au = new javax.swing.JTextField();
        jLabel100 = new javax.swing.JLabel();
        po_pr = new javax.swing.JTextField();
        nb_new7 = new javax.swing.JButton();
        nb_save1 = new javax.swing.JButton();
        nb_update1 = new javax.swing.JButton();
        jLabel101 = new javax.swing.JLabel();
        po_add = new javax.swing.JButton();
        po_min = new javax.swing.JButton();
        nb_update2 = new javax.swing.JButton();
        nb_update4 = new javax.swing.JButton();
        po_cl = new javax.swing.JComboBox<String>();
        class_add1 = new javax.swing.JButton();
        nb_update5 = new javax.swing.JButton();
        jLabel110 = new javax.swing.JLabel();
        jLabel113 = new javax.swing.JLabel();
        jLabel114 = new javax.swing.JLabel();
        nb_update7 = new javax.swing.JButton();
        class_add3 = new javax.swing.JButton();
        jPanel50 = new javax.swing.JPanel();
        jScrollPane19 = new javax.swing.JScrollPane();
        po_table = new javax.swing.JTable();
        brr_fn7 = new javax.swing.JTextField();
        jLabel99 = new javax.swing.JLabel();
        jLabel102 = new javax.swing.JLabel();
        brr_fn8 = new javax.swing.JTextField();
        jLabel106 = new javax.swing.JLabel();
        brr_fn9 = new javax.swing.JTextField();
        jLabel107 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        sum_table = new javax.swing.JTable();
        jPanel17 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        sum_class = new javax.swing.JComboBox<String>();
        sum_bt = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        nb_new1 = new javax.swing.JButton();
        nb_new3 = new javax.swing.JButton();
        jPanel47 = new javax.swing.JPanel();
        jPanel48 = new javax.swing.JPanel();
        jScrollPane18 = new javax.swing.JScrollPane();
        si_table1 = new javax.swing.JTable();
        jLabel139 = new javax.swing.JLabel();
        so_search1 = new javax.swing.JTextField();
        jPanel15 = new javax.swing.JPanel();
        jPanel26 = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        so_isbn = new javax.swing.JTextField();
        so_bt = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        so_qty = new javax.swing.JTextField();
        so_class = new javax.swing.JTextField();
        jLabel59 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        so_add = new javax.swing.JButton();
        so_minus = new javax.swing.JButton();
        so_save = new javax.swing.JButton();
        so_stat = new javax.swing.JComboBox<String>();
        jLabel45 = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        so_table = new javax.swing.JTable();
        jPanel28 = new javax.swing.JPanel();
        jLabel63 = new javax.swing.JLabel();
        so_enc = new javax.swing.JTextField();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        so_date = new javax.swing.JTextField();
        so_time = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        so_search = new javax.swing.JTextField();
        jPanel16 = new javax.swing.JPanel();
        jPanel29 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        log_table = new javax.swing.JTable();
        jLabel60 = new javax.swing.JLabel();
        invlog_search = new javax.swing.JTextField();
        jPanel79 = new javax.swing.JPanel();
        jPanel81 = new javax.swing.JPanel();
        jScrollPane33 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jPanel80 = new javax.swing.JPanel();
        jLabel193 = new javax.swing.JLabel();
        so_enc1 = new javax.swing.JTextField();
        jLabel194 = new javax.swing.JLabel();
        jLabel195 = new javax.swing.JLabel();
        so_date1 = new javax.swing.JTextField();
        so_time1 = new javax.swing.JTextField();
        jLabel196 = new javax.swing.JLabel();
        jLabel197 = new javax.swing.JLabel();
        so_time2 = new javax.swing.JTextField();
        jComboBox2 = new javax.swing.JComboBox();
        so_save1 = new javax.swing.JButton();
        so_save2 = new javax.swing.JButton();
        so_save3 = new javax.swing.JButton();
        jLabel198 = new javax.swing.JLabel();
        so_save4 = new javax.swing.JButton();
        user_logo = new javax.swing.JPanel();
        jTabbedPane7 = new javax.swing.JTabbedPane();
        jPanel55 = new javax.swing.JPanel();
        jScrollPane30 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel64 = new javax.swing.JPanel();
        jScrollPane32 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        test = new javax.swing.JPanel();
        jScrollPane21 = new javax.swing.JScrollPane();
        po_test = new javax.swing.JTable();
        jLabel109 = new javax.swing.JLabel();
        dsc_id = new javax.swing.JLabel();
        jLabel111 = new javax.swing.JLabel();
        rc_po = new javax.swing.JLabel();
        jLabel112 = new javax.swing.JLabel();
        rc_tot = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane25 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        txt_name = new javax.swing.JLabel();
        txt_level = new javax.swing.JLabel();
        dddd3 = new com.toedter.calendar.JDateChooser();
        dddd4 = new com.toedter.calendar.JDateChooser();
        dddd1 = new com.toedter.calendar.JDateChooser();
        dddd2 = new com.toedter.calendar.JDateChooser();
        jPanel14 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        si_isbn = new javax.swing.JTextField();
        si_bt = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        si_oh = new javax.swing.JTextField();
        si_class = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        si_brrd = new javax.swing.JTextField();
        si_dmg = new javax.swing.JTextField();
        si_th = new javax.swing.JTextField();
        si_add = new javax.swing.JButton();
        si_minus = new javax.swing.JButton();
        si_calc = new javax.swing.JButton();
        minus_test = new javax.swing.JLabel();
        si_save = new javax.swing.JButton();
        si_id = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        si_table = new javax.swing.JTable();
        jPanel25 = new javax.swing.JPanel();
        jLabel56 = new javax.swing.JLabel();
        si_enc = new javax.swing.JTextField();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        si_date = new javax.swing.JTextField();
        si_time = new javax.swing.JTextField();
        idd = new javax.swing.JLabel();
        brr_q = new javax.swing.JLabel();
        brr_rid = new javax.swing.JLabel();
        account_waste = new javax.swing.JPanel();
        jPanel69 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        acc_nb = new javax.swing.JCheckBox();
        acc_inv = new javax.swing.JCheckBox();
        acc_brrd = new javax.swing.JCheckBox();
        acc_rep = new javax.swing.JCheckBox();
        acc_brrr = new javax.swing.JCheckBox();
        acc_acc = new javax.swing.JCheckBox();
        nb = new javax.swing.JLabel();
        brrd = new javax.swing.JLabel();
        rep = new javax.swing.JLabel();
        inv = new javax.swing.JLabel();
        brrr = new javax.swing.JLabel();
        ua = new javax.swing.JLabel();
        acc_id = new javax.swing.JLabel();
        jPanel70 = new javax.swing.JPanel();
        jLabel128 = new javax.swing.JLabel();
        jLabel120 = new javax.swing.JLabel();
        jLabel133 = new javax.swing.JLabel();
        sup_id = new javax.swing.JLabel();
        so_id = new javax.swing.JLabel();
        sominus_test = new javax.swing.JLabel();
        frm_tbl = new javax.swing.JLabel();
        remain = new javax.swing.JLabel();
        sandh = new javax.swing.JLabel();
        brr_id = new javax.swing.JLabel();
        nb_id = new javax.swing.JLabel();
        jPanel71 = new javax.swing.JPanel();
        jLabel130 = new javax.swing.JLabel();
        brrd_bqty = new javax.swing.JLabel();
        brrd_total = new javax.swing.JLabel();
        jLabel151 = new javax.swing.JLabel();
        jLabel152 = new javax.swing.JLabel();
        brrd_id = new javax.swing.JLabel();
        date1 = new javax.swing.JLabel();
        test3 = new javax.swing.JLabel();
        test6 = new javax.swing.JLabel();
        test_tot1 = new javax.swing.JLabel();
        jLabel129 = new javax.swing.JLabel();
        test_tot = new javax.swing.JLabel();
        test1 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        test2 = new javax.swing.JLabel();
        test5 = new javax.swing.JLabel();
        test4 = new javax.swing.JLabel();
        jPanel72 = new javax.swing.JPanel();
        jLabel124 = new javax.swing.JLabel();
        jLabel136 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel137 = new javax.swing.JLabel();
        jLabel134 = new javax.swing.JLabel();
        jLabel127 = new javax.swing.JLabel();
        jLabel135 = new javax.swing.JLabel();
        jLabel142 = new javax.swing.JLabel();
        jLabel143 = new javax.swing.JLabel();
        jLabel144 = new javax.swing.JLabel();
        jLabel145 = new javax.swing.JLabel();
        jLabel146 = new javax.swing.JLabel();
        jLabel147 = new javax.swing.JLabel();
        jLabel148 = new javax.swing.JLabel();
        jLabel149 = new javax.swing.JLabel();
        jLabel150 = new javax.swing.JLabel();
        jLabel126 = new javax.swing.JLabel();
        jLabel141 = new javax.swing.JLabel();
        jLabel140 = new javax.swing.JLabel();
        jLabel121 = new javax.swing.JLabel();
        jLabel122 = new javax.swing.JLabel();
        ttt = new javax.swing.JLabel();
        code = new javax.swing.JLabel();
        jLabel115 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        booklist = new javax.swing.JPanel();
        jPanel51 = new javax.swing.JPanel();
        jScrollPane20 = new javax.swing.JScrollPane();
        pobl_table = new javax.swing.JTable();
        nb_update3 = new javax.swing.JButton();
        po_or1 = new javax.swing.JTextField();
        jLabel108 = new javax.swing.JLabel();
        classification = new javax.swing.JPanel();
        jPanel45 = new javax.swing.JPanel();
        jScrollPane17 = new javax.swing.JScrollPane();
        class_table = new javax.swing.JTable();
        jPanel46 = new javax.swing.JPanel();
        jLabel85 = new javax.swing.JLabel();
        si_isbn1 = new javax.swing.JTextField();
        jLabel87 = new javax.swing.JLabel();
        class_num = new javax.swing.JLabel();
        class_save_hide = new javax.swing.JPanel();
        si_save1 = new javax.swing.JButton();
        btn_del = new javax.swing.JButton();
        si_save2 = new javax.swing.JButton();
        useraccount = new javax.swing.JPanel();
        men_brrr = new javax.swing.JLabel();
        men_ua = new javax.swing.JLabel();
        borrower = new javax.swing.JPanel();
        newbook = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new java.awt.CardLayout());

        loginpanel.setBackground(new java.awt.Color(51, 51, 51));

        sumup.setText("0");

        jLabel1.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Login Form");

        user.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        user.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        user.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                userKeyPressed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Username");

        pass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                passKeyPressed(evt);
            }
        });

        showpass.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        showpass.setForeground(new java.awt.Color(255, 255, 255));
        showpass.setText("Show Password");
        showpass.setOpaque(false);
        showpass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                showpassMouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Password");

        jButton1.setBackground(new java.awt.Color(0, 153, 255));
        jButton1.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Login");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jButton1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton1KeyPressed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(0, 153, 255));
        jButton3.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Public View");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jButton3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton3KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout loginpanelLayout = new javax.swing.GroupLayout(loginpanel);
        loginpanel.setLayout(loginpanelLayout);
        loginpanelLayout.setHorizontalGroup(
            loginpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginpanelLayout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(loginpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(loginpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(showpass)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(user)
                        .addComponent(pass)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginpanelLayout.createSequentialGroup()
                .addContainerGap(166, Short.MAX_VALUE)
                .addGroup(loginpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginpanelLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(158, 158, 158))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginpanelLayout.createSequentialGroup()
                        .addComponent(sumup, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(206, 206, 206))))
        );
        loginpanelLayout.setVerticalGroup(
            loginpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginpanelLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sumup)
                .addGap(29, 29, 29)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(user, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pass, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(showpass)
                .addGap(64, 64, 64)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(67, Short.MAX_VALUE))
        );

        exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/error.png"))); // NOI18N
        exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitMouseClicked(evt);
            }
        });

        minimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/minimize.png"))); // NOI18N
        minimize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                minimizeMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout LoginLayout = new javax.swing.GroupLayout(Login);
        Login.setLayout(LoginLayout);
        LoginLayout.setHorizontalGroup(
            LoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LoginLayout.createSequentialGroup()
                .addGap(0, 1052, Short.MAX_VALUE)
                .addComponent(minimize)
                .addGap(8, 8, 8)
                .addComponent(exit)
                .addContainerGap())
            .addGroup(LoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(LoginLayout.createSequentialGroup()
                    .addGap(0, 325, Short.MAX_VALUE)
                    .addComponent(loginpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 325, Short.MAX_VALUE)))
        );
        LoginLayout.setVerticalGroup(
            LoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LoginLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(LoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(exit)
                    .addComponent(minimize))
                .addContainerGap(649, Short.MAX_VALUE))
            .addGroup(LoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(LoginLayout.createSequentialGroup()
                    .addGap(0, 60, Short.MAX_VALUE)
                    .addComponent(loginpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 60, Short.MAX_VALUE)))
        );

        jPanel1.add(Login, "card2");

        publicsearch.setBackground(new java.awt.Color(255, 255, 255));
        publicsearch.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        nb_new8.setBackground(new java.awt.Color(51, 153, 255));
        nb_new8.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        nb_new8.setForeground(new java.awt.Color(255, 255, 255));
        nb_new8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/service.png"))); // NOI18N
        nb_new8.setText("Login Account?");
        nb_new8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nb_new8ActionPerformed(evt);
            }
        });
        publicsearch.add(nb_new8, new org.netbeans.lib.awtextra.AbsoluteConstraints(923, 10, 210, -1));

        jLabel164.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel164.setForeground(new java.awt.Color(255, 255, 255));
        jLabel164.setText("Search:");

        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField2KeyReleased(evt);
            }
        });

        jComboBox1.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jComboBox1PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jLabel165.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel165.setForeground(new java.awt.Color(255, 255, 255));
        jLabel165.setText("Book Dateils");

        jLabel166.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel166.setForeground(new java.awt.Color(255, 255, 255));
        jLabel166.setText("Classification");

        javax.swing.GroupLayout jPanel68Layout = new javax.swing.GroupLayout(jPanel68);
        jPanel68.setLayout(jPanel68Layout);
        jPanel68Layout.setHorizontalGroup(
            jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel68Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel164)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel165))
                .addGap(18, 18, 18)
                .addGroup(jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel166)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(79, Short.MAX_VALUE))
        );
        jPanel68Layout.setVerticalGroup(
            jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel68Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel165, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel166, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel164)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18))
        );

        publicsearch.add(jPanel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 750, 100));

        jTextField3.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N

        jLabel167.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel167.setForeground(new java.awt.Color(255, 255, 255));
        jLabel167.setText("ISBN No.:");

        jLabel168.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel168.setForeground(new java.awt.Color(255, 255, 255));
        jLabel168.setText("Book Title:");

        jTextField4.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N

        jLabel169.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel169.setForeground(new java.awt.Color(255, 255, 255));
        jLabel169.setText("Author:");

        jTextField5.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N

        jLabel170.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel170.setForeground(new java.awt.Color(255, 255, 255));
        jLabel170.setText("Call Number:");

        jTextField6.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N

        jLabel171.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel171.setForeground(new java.awt.Color(255, 255, 255));
        jLabel171.setText("Classification:");

        jTextField7.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N

        jLabel172.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel172.setForeground(new java.awt.Color(255, 255, 255));
        jLabel172.setText("Publisher:");

        jTextField8.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N

        jLabel173.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel173.setForeground(new java.awt.Color(255, 255, 255));
        jLabel173.setText("Edition:");

        jTextField9.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N

        jLabel174.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel174.setForeground(new java.awt.Color(255, 255, 255));
        jLabel174.setText("Copy Right");

        jTextField10.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N

        nb_new9.setBackground(new java.awt.Color(51, 153, 255));
        nb_new9.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        nb_new9.setForeground(new java.awt.Color(255, 255, 255));
        nb_new9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/update.png"))); // NOI18N
        nb_new9.setText("Refresh");
        nb_new9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nb_new9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel174)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel173)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel172)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel171)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel170)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel169)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel168)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel167)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nb_new9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE))))
                .addGap(10, 10, 10))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(nb_new9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel167)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel168)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel169)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel170)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel171)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel172)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel173)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel174)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );

        publicsearch.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 190, 360, 500));

        sumpatable.setModel(new javax.swing.table.DefaultTableModel(
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
        sumpatable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sumpatableMouseClicked(evt);
            }
        });
        jScrollPane31.setViewportView(sumpatable);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane31, javax.swing.GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane31, javax.swing.GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE)
                .addContainerGap())
        );

        publicsearch.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 750, 500));

        jLabel163.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/bkg.png"))); // NOI18N
        publicsearch.add(jLabel163, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1150, 700));

        jPanel1.add(publicsearch, "card4");

        Menu.setBackground(new java.awt.Color(255, 255, 255));

        mmm.setBackground(new java.awt.Color(96, 96, 96));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cronasia.png"))); // NOI18N

        men_dash.setBackground(new java.awt.Color(255, 204, 204));
        men_dash.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        men_dash.setForeground(new java.awt.Color(255, 255, 255));
        men_dash.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        men_dash.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/dashboard.png"))); // NOI18N
        men_dash.setText("MY ACCOUNT");
        men_dash.setOpaque(true);
        men_dash.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                men_dashMouseClicked(evt);
            }
        });

        men_rec.setBackground(new java.awt.Color(255, 204, 204));
        men_rec.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        men_rec.setForeground(new java.awt.Color(255, 255, 255));
        men_rec.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        men_rec.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/newbook.png"))); // NOI18N
        men_rec.setText("RECORDS");
        men_rec.setOpaque(true);
        men_rec.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                men_recMouseClicked(evt);
            }
        });

        men_inv.setBackground(new java.awt.Color(255, 204, 204));
        men_inv.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        men_inv.setForeground(new java.awt.Color(255, 255, 255));
        men_inv.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        men_inv.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/invent.png"))); // NOI18N
        men_inv.setText("INVENTORY");
        men_inv.setOpaque(true);
        men_inv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                men_invMouseClicked(evt);
            }
        });

        men_brrd.setBackground(new java.awt.Color(255, 204, 204));
        men_brrd.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        men_brrd.setForeground(new java.awt.Color(255, 255, 255));
        men_brrd.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        men_brrd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/borrowed.png"))); // NOI18N
        men_brrd.setText("BOOK LOAN");
        men_brrd.setOpaque(true);
        men_brrd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                men_brrdMouseClicked(evt);
            }
        });

        men_logout.setBackground(new java.awt.Color(255, 204, 204));
        men_logout.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        men_logout.setForeground(new java.awt.Color(255, 255, 255));
        men_logout.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        men_logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/logout.png"))); // NOI18N
        men_logout.setText("LOG-OUT");
        men_logout.setOpaque(true);
        men_logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                men_logoutMouseClicked(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Britannic Bold", 0, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Library System");

        txt_time.setBackground(new java.awt.Color(96, 96, 96));
        txt_time.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        txt_time.setForeground(new java.awt.Color(255, 255, 255));
        txt_time.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt_time.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/clock.png"))); // NOI18N
        txt_time.setText("Time");

        txt_date1.setBackground(new java.awt.Color(96, 96, 96));
        txt_date1.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        txt_date1.setForeground(new java.awt.Color(255, 255, 255));
        txt_date1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt_date1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/calendar.png"))); // NOI18N
        txt_date1.setText("Date");

        men_rep.setBackground(new java.awt.Color(255, 204, 204));
        men_rep.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        men_rep.setForeground(new java.awt.Color(255, 255, 255));
        men_rep.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        men_rep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/report.png"))); // NOI18N
        men_rep.setText("REPORT");
        men_rep.setOpaque(true);
        men_rep.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                men_repMouseClicked(evt);
            }
        });

        men_po.setBackground(new java.awt.Color(255, 204, 204));
        men_po.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        men_po.setForeground(new java.awt.Color(255, 255, 255));
        men_po.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        men_po.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/purchase_order.png"))); // NOI18N
        men_po.setText("USER LOG");
        men_po.setOpaque(true);
        men_po.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                men_poMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout mmmLayout = new javax.swing.GroupLayout(mmm);
        mmm.setLayout(mmmLayout);
        mmmLayout.setHorizontalGroup(
            mmmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(men_rec, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(men_inv, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(men_brrd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(men_logout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mmmLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(mmmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(jLabel6))
                .addGap(35, 35, 35))
            .addComponent(txt_date1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(txt_time, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(men_rep, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(men_dash, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(men_po, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        mmmLayout.setVerticalGroup(
            mmmLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mmmLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addGap(18, 18, 18)
                .addComponent(men_dash, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(men_rep, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(men_rec, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(men_brrd, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(men_inv, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(men_po, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(men_logout, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 137, Short.MAX_VALUE)
                .addComponent(txt_time)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_date1)
                .addContainerGap())
        );

        head.setBackground(new java.awt.Color(96, 96, 96));
        head.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        menu_title.setBackground(new java.awt.Color(96, 96, 96));
        menu_title.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        menu_title.setForeground(new java.awt.Color(255, 255, 255));
        menu_title.setText("MY ACCOUNT");
        head.add(menu_title, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 259, 51));

        txt_name1.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        txt_name1.setForeground(new java.awt.Color(255, 255, 255));
        txt_name1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/service.png"))); // NOI18N
        head.add(txt_name1, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 10, -1, 40));

        menu_title1.setBackground(new java.awt.Color(96, 96, 96));
        menu_title1.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        menu_title1.setForeground(new java.awt.Color(255, 255, 255));
        menu_title1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        menu_title1.setText("MY ACCOUNT");
        menu_title1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menu_title1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                menu_title1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                menu_title1MouseExited(evt);
            }
        });
        head.add(menu_title1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 20, 441, 40));

        stock.setLayout(new java.awt.CardLayout());

        dashboard.setBackground(new java.awt.Color(255, 255, 255));
        dashboard.setLayout(new java.awt.CardLayout());

        booksummary.setBackground(new java.awt.Color(255, 255, 255));
        booksummary.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Account Dashboard"));

        jTabbedPane3.setFont(new java.awt.Font("Arial Black", 1, 11)); // NOI18N

        ohb_table.setModel(new javax.swing.table.DefaultTableModel(
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
        ohb_table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        ohb_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ohb_tableMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(ohb_table);

        javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
        jPanel37.setLayout(jPanel37Layout);
        jPanel37Layout.setHorizontalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 616, Short.MAX_VALUE)
        );
        jPanel37Layout.setVerticalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 569, Short.MAX_VALUE)
        );

        jTabbedPane3.addTab("ON HAND BOOK", jPanel37);

        bl_table.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane13.setViewportView(bl_table);

        javax.swing.GroupLayout ssssLayout = new javax.swing.GroupLayout(ssss);
        ssss.setLayout(ssssLayout);
        ssssLayout.setHorizontalGroup(
            ssssLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 616, Short.MAX_VALUE)
        );
        ssssLayout.setVerticalGroup(
            ssssLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 569, Short.MAX_VALUE)
        );

        jTabbedPane3.addTab("BOOK LOG", ssss);

        jPanel38.setBackground(new java.awt.Color(255, 255, 255));
        jPanel38.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Summary"));

        jLabel79.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel79.setText("Number of Books Onhand:");

        nb_is1.setEditable(false);

        jLabel80.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel80.setText("Payable/Fines");

        nb_is2.setEditable(false);

        javax.swing.GroupLayout jPanel38Layout = new javax.swing.GroupLayout(jPanel38);
        jPanel38.setLayout(jPanel38Layout);
        jPanel38Layout.setHorizontalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nb_is1)
                    .addComponent(nb_is2)
                    .addGroup(jPanel38Layout.createSequentialGroup()
                        .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 32, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel38Layout.setVerticalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nb_is1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(nb_is2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel39.setBackground(new java.awt.Color(255, 255, 255));
        jPanel39.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Details"));

        jScrollPane15.setBackground(new java.awt.Color(255, 255, 255));

        jPanel40.setBackground(new java.awt.Color(255, 255, 255));
        jPanel40.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel89.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel89.setText("Book Title");

        ohb_bt.setEditable(false);
        ohb_bt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ohb_btKeyReleased(evt);
            }
        });

        jLabel90.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel90.setText("Classification");

        ohb_class.setEditable(false);
        ohb_class.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ohb_classKeyReleased(evt);
            }
        });

        jLabel91.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel91.setText("Fines");

        ohb_fines.setEditable(false);
        ohb_fines.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ohb_finesKeyReleased(evt);
            }
        });

        jLabel92.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel92.setText("Quantity");

        ohb_qty.setEditable(false);
        ohb_qty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ohb_qtyKeyReleased(evt);
            }
        });

        jLabel93.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel93.setText("Borrowed Date");

        ohb_bd.setEditable(false);
        ohb_bd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ohb_bdKeyReleased(evt);
            }
        });

        jLabel94.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel94.setText("Return Date");

        ohb_rd.setEditable(false);
        ohb_rd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ohb_rdKeyReleased(evt);
            }
        });

        jLabel95.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel95.setText("Remarks");

        ohn_rem.setEditable(false);
        ohn_rem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ohn_remKeyReleased(evt);
            }
        });

        jLabel96.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel96.setText("Payable");

        ohb_pay.setEditable(false);
        ohb_pay.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ohb_payKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel40Layout = new javax.swing.GroupLayout(jPanel40);
        jPanel40.setLayout(jPanel40Layout);
        jPanel40Layout.setHorizontalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ohb_pay, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel96, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ohn_rem, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ohb_class, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel89, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ohb_bt, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel95, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ohb_rd, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel94, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ohb_qty, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel93, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ohb_bd, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel92, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ohb_fines, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel91, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel90, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel40Layout.setVerticalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel89, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ohb_bt, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel90, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ohb_class, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel91, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ohb_fines, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel92, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ohb_qty, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel93, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ohb_bd, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel94, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ohb_rd, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel95, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ohn_rem, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel96, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ohb_pay, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane15.setViewportView(jPanel40);

        javax.swing.GroupLayout jPanel39Layout = new javax.swing.GroupLayout(jPanel39);
        jPanel39.setLayout(jPanel39Layout);
        jPanel39Layout.setHorizontalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane15, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
        );
        jPanel39Layout.setVerticalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        nb_new5.setBackground(new java.awt.Color(51, 153, 255));
        nb_new5.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        nb_new5.setForeground(new java.awt.Color(255, 255, 255));
        nb_new5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/book.png"))); // NOI18N
        nb_new5.setText("Search Book");
        nb_new5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nb_new5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout booksummaryLayout = new javax.swing.GroupLayout(booksummary);
        booksummary.setLayout(booksummaryLayout);
        booksummaryLayout.setHorizontalGroup(
            booksummaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(booksummaryLayout.createSequentialGroup()
                .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 621, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(booksummaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nb_new5, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(472, Short.MAX_VALUE))
        );
        booksummaryLayout.setVerticalGroup(
            booksummaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(booksummaryLayout.createSequentialGroup()
                .addGroup(booksummaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(booksummaryLayout.createSequentialGroup()
                        .addComponent(nb_new5, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(jPanel39, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jTabbedPane3))
                .addContainerGap())
        );

        dashboard.add(booksummary, "card2");

        account.setBackground(new java.awt.Color(255, 255, 255));

        nb_new4.setBackground(new java.awt.Color(51, 153, 255));
        nb_new4.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        nb_new4.setForeground(new java.awt.Color(255, 255, 255));
        nb_new4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/book.png"))); // NOI18N
        nb_new4.setText("Account Summary");
        nb_new4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nb_new4ActionPerformed(evt);
            }
        });

        jPanel43.setBackground(new java.awt.Color(255, 255, 255));
        jPanel43.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "ACCOUNT INFORMATION"));
        jPanel43.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel82.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel82.setText("First _Name:");
        jPanel43.add(jLabel82, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 86, 24));

        jLabel83.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel83.setText("Username:");
        jPanel43.add(jLabel83, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 30, 86, 24));

        jLabel84.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel84.setText("Confirm:");
        jPanel43.add(jLabel84, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 110, 99, 24));

        jLabel86.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel86.setText("Photo:");
        jPanel43.add(jLabel86, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 99, 25));
        jPanel43.add(my_fn, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, 170, 31));

        jLabel88.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel88.setText("Mobile Number:");
        jPanel43.add(jLabel88, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 99, 24));

        jLabel97.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel97.setText("Password:");
        jPanel43.add(jLabel97, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 70, 86, 24));
        jPanel43.add(my_mn, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 140, 170, 31));

        my_image.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        my_image.setText("Select Image");
        my_image.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel43.add(my_image, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 210, 200, 170));

        acc_browse1.setBackground(new java.awt.Color(51, 153, 255));
        acc_browse1.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        acc_browse1.setForeground(new java.awt.Color(255, 255, 255));
        acc_browse1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/browsing.png"))); // NOI18N
        acc_browse1.setText("Browse");
        acc_browse1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acc_browse1ActionPerformed(evt);
            }
        });
        jPanel43.add(acc_browse1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, -1, 44));
        jPanel43.add(my_con, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 100, 170, 30));
        jPanel43.add(my_pass, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 60, 170, 30));
        jPanel43.add(my_user, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 20, 170, 31));

        jLabel179.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel179.setText("Middle _Name:");
        jPanel43.add(jLabel179, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 110, 24));
        jPanel43.add(my_mnn, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 60, 170, 31));

        jLabel180.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel180.setText("Surame:");
        jPanel43.add(jLabel180, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 110, 24));
        jPanel43.add(my_sn, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, 170, 31));

        jPanel44.setBackground(new java.awt.Color(255, 255, 255));
        jPanel44.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "OPTION"));

        acc_update1.setBackground(new java.awt.Color(51, 153, 255));
        acc_update1.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        acc_update1.setForeground(new java.awt.Color(255, 255, 255));
        acc_update1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/update.png"))); // NOI18N
        acc_update1.setText("Update");
        acc_update1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acc_update1ActionPerformed(evt);
            }
        });

        my_id.setText("jLabel7");

        javax.swing.GroupLayout jPanel44Layout = new javax.swing.GroupLayout(jPanel44);
        jPanel44.setLayout(jPanel44Layout);
        jPanel44Layout.setHorizontalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel44Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(my_id)
                    .addComponent(acc_update1))
                .addContainerGap(740, Short.MAX_VALUE))
        );
        jPanel44Layout.setVerticalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel44Layout.createSequentialGroup()
                .addComponent(my_id, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(acc_update1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout accountLayout = new javax.swing.GroupLayout(account);
        account.setLayout(accountLayout);
        accountLayout.setHorizontalGroup(
            accountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(accountLayout.createSequentialGroup()
                .addGroup(accountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel43, javax.swing.GroupLayout.PREFERRED_SIZE, 877, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nb_new4))
                .addGap(0, 470, Short.MAX_VALUE))
        );
        accountLayout.setVerticalGroup(
            accountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(accountLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nb_new4, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addComponent(jPanel43, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        dashboard.add(account, "card3");

        findbook.setBackground(new java.awt.Color(255, 255, 255));

        jPanel41.setBackground(new java.awt.Color(255, 255, 255));
        jPanel41.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel81.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel81.setText("Find Book");

        masb_search.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        masb_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                masb_searchKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel41Layout = new javax.swing.GroupLayout(jPanel41);
        jPanel41.setLayout(jPanel41Layout);
        jPanel41Layout.setHorizontalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(masb_search)
                    .addGroup(jPanel41Layout.createSequentialGroup()
                        .addComponent(jLabel81, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 230, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel41Layout.setVerticalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addComponent(jLabel81, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(masb_search, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        jPanel42.setBackground(new java.awt.Color(255, 255, 255));
        jPanel42.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        masb_table.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane16.setViewportView(masb_table);

        javax.swing.GroupLayout jPanel42Layout = new javax.swing.GroupLayout(jPanel42);
        jPanel42.setLayout(jPanel42Layout);
        jPanel42Layout.setHorizontalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel42Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane16)
                .addContainerGap())
        );
        jPanel42Layout.setVerticalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel42Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane16, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE)
                .addContainerGap())
        );

        nb_new6.setBackground(new java.awt.Color(51, 153, 255));
        nb_new6.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        nb_new6.setForeground(new java.awt.Color(255, 255, 255));
        nb_new6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/book.png"))); // NOI18N
        nb_new6.setText("Account Summary");
        nb_new6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nb_new6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout findbookLayout = new javax.swing.GroupLayout(findbook);
        findbook.setLayout(findbookLayout);
        findbookLayout.setHorizontalGroup(
            findbookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(findbookLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(findbookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(findbookLayout.createSequentialGroup()
                        .addComponent(jPanel41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(207, 207, 207)
                        .addComponent(nb_new6)))
                .addContainerGap(492, Short.MAX_VALUE))
        );
        findbookLayout.setVerticalGroup(
            findbookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(findbookLayout.createSequentialGroup()
                .addGroup(findbookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nb_new6, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel42, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        dashboard.add(findbook, "card4");

        stock.add(dashboard, "card2");

        report.setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane4.setFont(new java.awt.Font("Arial Black", 1, 11)); // NOI18N

        jPanel53.setBackground(new java.awt.Color(255, 255, 255));

        jPanel52.setBackground(new java.awt.Color(255, 255, 255));
        jPanel52.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "PURCHASE LIST"));

        rep_bo_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        rep_bo_table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        rep_bo_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rep_bo_tableMouseClicked(evt);
            }
        });
        jScrollPane22.setViewportView(rep_bo_table);

        jLabel123.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel123.setText("Search Book:");

        po_or2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                po_or2KeyReleased(evt);
            }
        });

        nb_update8.setBackground(new java.awt.Color(51, 153, 255));
        nb_update8.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        nb_update8.setForeground(new java.awt.Color(255, 255, 255));
        nb_update8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/update.png"))); // NOI18N
        nb_update8.setText("Update");
        nb_update8.setEnabled(false);
        nb_update8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nb_update8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel52Layout = new javax.swing.GroupLayout(jPanel52);
        jPanel52.setLayout(jPanel52Layout);
        jPanel52Layout.setHorizontalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane22)
            .addGroup(jPanel52Layout.createSequentialGroup()
                .addComponent(nb_update8)
                .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel52Layout.createSequentialGroup()
                        .addGap(174, 174, 174)
                        .addComponent(jLabel138, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel52Layout.createSequentialGroup()
                        .addGap(160, 414, Short.MAX_VALUE)
                        .addComponent(jLabel123, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(po_or2, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel52Layout.setVerticalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel52Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel138, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(po_or2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel123, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nb_update8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane22, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel53Layout = new javax.swing.GroupLayout(jPanel53);
        jPanel53.setLayout(jPanel53Layout);
        jPanel53Layout.setHorizontalGroup(
            jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel53Layout.createSequentialGroup()
                .addComponent(jPanel52, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel53Layout.setVerticalGroup(
            jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel53Layout.createSequentialGroup()
                .addComponent(jPanel52, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane4.addTab("BOOK ORDERED", jPanel53);

        jPanel54.setBackground(new java.awt.Color(255, 255, 255));

        jPanel60.setBackground(new java.awt.Color(255, 255, 255));
        jPanel60.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "CANCELLED LIST"));

        rep_bo_table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        rep_bo_table1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        rep_bo_table1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rep_bo_table1MouseClicked(evt);
            }
        });
        jScrollPane26.setViewportView(rep_bo_table1);

        jLabel125.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel125.setText("Search:");

        po_or3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                po_or3KeyReleased(evt);
            }
        });

        nb_update9.setBackground(new java.awt.Color(51, 153, 255));
        nb_update9.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        nb_update9.setForeground(new java.awt.Color(255, 255, 255));
        nb_update9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/update.png"))); // NOI18N
        nb_update9.setText("Update");
        nb_update9.setEnabled(false);
        nb_update9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nb_update9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel60Layout = new javax.swing.GroupLayout(jPanel60);
        jPanel60.setLayout(jPanel60Layout);
        jPanel60Layout.setHorizontalGroup(
            jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane26)
            .addGroup(jPanel60Layout.createSequentialGroup()
                .addComponent(nb_update9)
                .addGap(0, 456, Short.MAX_VALUE)
                .addComponent(jLabel125, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(po_or3, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel60Layout.setVerticalGroup(
            jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel60Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addGroup(jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(po_or3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel125, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nb_update9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane26, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel54Layout = new javax.swing.GroupLayout(jPanel54);
        jPanel54.setLayout(jPanel54Layout);
        jPanel54Layout.setHorizontalGroup(
            jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel54Layout.createSequentialGroup()
                .addComponent(jPanel60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel54Layout.setVerticalGroup(
            jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel54Layout.createSequentialGroup()
                .addComponent(jPanel60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane4.addTab("CANCELLED ORDER", jPanel54);

        jPanel61.setBackground(new java.awt.Color(255, 255, 255));

        jPanel62.setBackground(new java.awt.Color(255, 255, 255));
        jPanel62.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "PAYMENT COLLECTION"));

        rep_bo_table2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        rep_bo_table2.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        rep_bo_table2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rep_bo_table2MouseClicked(evt);
            }
        });
        jScrollPane27.setViewportView(rep_bo_table2);

        jLabel131.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel131.setText("Search:");

        po_or4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                po_or4KeyReleased(evt);
            }
        });

        jLabel132.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel132.setForeground(new java.awt.Color(255, 51, 51));
        jLabel132.setText("Quantity");

        nb_update11.setBackground(new java.awt.Color(51, 153, 255));
        nb_update11.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        nb_update11.setForeground(new java.awt.Color(255, 255, 255));
        nb_update11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/printer.png"))); // NOI18N
        nb_update11.setText("Print");
        nb_update11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nb_update11ActionPerformed(evt);
            }
        });

        jDateChooser1.setDateFormatString("MM/dd/yyyy");

        jLabel153.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel153.setText("To:");

        jDateChooser2.setDateFormatString("MM/dd/yyyy");

        jLabel154.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel154.setText("From:");

        jLabel155.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel155.setForeground(new java.awt.Color(255, 51, 51));
        jLabel155.setText("Totalprice");

        nb_update13.setBackground(new java.awt.Color(51, 153, 255));
        nb_update13.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        nb_update13.setForeground(new java.awt.Color(255, 255, 255));
        nb_update13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/clear.png"))); // NOI18N
        nb_update13.setText("Clear");
        nb_update13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nb_update13ActionPerformed(evt);
            }
        });

        jLabel156.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel156.setText("Quantity");

        jLabel157.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel157.setText("Total:");

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

        javax.swing.GroupLayout jPanel62Layout = new javax.swing.GroupLayout(jPanel62);
        jPanel62.setLayout(jPanel62Layout);
        jPanel62Layout.setHorizontalGroup(
            jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane27)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel62Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nb_update11)
                    .addComponent(nb_update13))
                .addGap(84, 84, 84)
                .addGroup(jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel62Layout.createSequentialGroup()
                        .addComponent(jLabel154, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel62Layout.createSequentialGroup()
                        .addComponent(jLabel131)
                        .addGap(18, 18, 18)
                        .addComponent(po_or4, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jLabel153, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(nb_update14)
                .addGap(24, 24, 24))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel62Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel156)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel132, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel157)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel155, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(135, 135, 135))
        );
        jPanel62Layout.setVerticalGroup(
            jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel62Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nb_update11)
                    .addGroup(jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(nb_update14)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel153, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel154, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nb_update13)
                    .addComponent(po_or4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel131, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane27, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel132)
                    .addComponent(jLabel155)
                    .addComponent(jLabel156, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel157, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout jPanel61Layout = new javax.swing.GroupLayout(jPanel61);
        jPanel61.setLayout(jPanel61Layout);
        jPanel61Layout.setHorizontalGroup(
            jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel61Layout.createSequentialGroup()
                .addComponent(jPanel62, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel61Layout.setVerticalGroup(
            jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel61Layout.createSequentialGroup()
                .addComponent(jPanel62, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane4.addTab("PAYMENT COLLECTION", jPanel61);

        jPanel63.setBackground(new java.awt.Color(255, 255, 255));

        jPanel65.setBackground(new java.awt.Color(255, 255, 255));
        jPanel65.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "DELIVERY LOG"));

        nb_update16.setBackground(new java.awt.Color(51, 153, 255));
        nb_update16.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        nb_update16.setForeground(new java.awt.Color(255, 255, 255));
        nb_update16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/clear.png"))); // NOI18N
        nb_update16.setText("Clear");
        nb_update16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nb_update16ActionPerformed(evt);
            }
        });

        rep_bo_table4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        rep_bo_table4.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        rep_bo_table4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rep_bo_table4MouseClicked(evt);
            }
        });
        jScrollPane29.setViewportView(rep_bo_table4);

        nb_update12.setBackground(new java.awt.Color(51, 153, 255));
        nb_update12.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        nb_update12.setForeground(new java.awt.Color(255, 255, 255));
        nb_update12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/printer.png"))); // NOI18N
        nb_update12.setText("Print");
        nb_update12.setEnabled(false);
        nb_update12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nb_update12ActionPerformed(evt);
            }
        });

        jDateChooser3.setDateFormatString("MM/dd/yyyy");

        jLabel158.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel158.setText("From:");

        jLabel159.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel159.setText("Search:");

        po_or5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                po_or5KeyReleased(evt);
            }
        });

        jLabel160.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel160.setText("To:");

        jDateChooser4.setDateFormatString("MM/dd/yyyy");

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

        javax.swing.GroupLayout jPanel65Layout = new javax.swing.GroupLayout(jPanel65);
        jPanel65.setLayout(jPanel65Layout);
        jPanel65Layout.setHorizontalGroup(
            jPanel65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane29)
            .addGroup(jPanel65Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nb_update16)
                    .addComponent(nb_update12))
                .addGap(97, 109, Short.MAX_VALUE)
                .addGroup(jPanel65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel65Layout.createSequentialGroup()
                        .addComponent(jLabel159)
                        .addGap(18, 18, 18)
                        .addComponent(po_or5, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel65Layout.createSequentialGroup()
                        .addComponent(jLabel158, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jDateChooser3, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jLabel160, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDateChooser4, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(nb_update15)
                .addContainerGap())
        );
        jPanel65Layout.setVerticalGroup(
            jPanel65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel65Layout.createSequentialGroup()
                .addGroup(jPanel65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel65Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(nb_update15)
                                .addComponent(jDateChooser4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jDateChooser3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel160, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel158, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel65Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(po_or5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel159, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel65Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(nb_update12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nb_update16)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane29, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel63Layout = new javax.swing.GroupLayout(jPanel63);
        jPanel63.setLayout(jPanel63Layout);
        jPanel63Layout.setHorizontalGroup(
            jPanel63Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 873, Short.MAX_VALUE)
            .addGroup(jPanel63Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel65, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel63Layout.setVerticalGroup(
            jPanel63Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 587, Short.MAX_VALUE)
            .addGroup(jPanel63Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel63Layout.createSequentialGroup()
                    .addComponent(jPanel65, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jTabbedPane4.addTab("DELIVERY RECEIPT LOG", jPanel63);

        javax.swing.GroupLayout reportLayout = new javax.swing.GroupLayout(report);
        report.setLayout(reportLayout);
        reportLayout.setHorizontalGroup(
            reportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reportLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 878, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(459, Short.MAX_VALUE))
        );
        reportLayout.setVerticalGroup(
            reportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reportLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 611, Short.MAX_VALUE)
                .addContainerGap())
        );

        stock.add(report, "card8");

        Records.setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane6.setFont(new java.awt.Font("Arial Black", 1, 11)); // NOI18N

        si_save3.setBackground(new java.awt.Color(51, 153, 255));
        si_save3.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        si_save3.setForeground(new java.awt.Color(255, 255, 255));
        si_save3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/back.png"))); // NOI18N
        si_save3.setText("Back");
        si_save3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                si_save3ActionPerformed(evt);
            }
        });

        jPanel67.setBackground(new java.awt.Color(255, 255, 255));
        jPanel67.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "CLASSIFICATION LIST"));

        class_table1.setModel(new javax.swing.table.DefaultTableModel(
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
        class_table1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
        class_table1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                class_table1MouseClicked(evt);
            }
        });
        jScrollPane28.setViewportView(class_table1);

        javax.swing.GroupLayout jPanel67Layout = new javax.swing.GroupLayout(jPanel67);
        jPanel67.setLayout(jPanel67Layout);
        jPanel67Layout.setHorizontalGroup(
            jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane28, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
        );
        jPanel67Layout.setVerticalGroup(
            jPanel67Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane28, javax.swing.GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE)
        );

        jPanel66.setBackground(new java.awt.Color(255, 255, 255));
        jPanel66.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "CLASSIFICATION DETAILS"));

        jLabel161.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel161.setText("Classification Name:");

        si_isbn2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                si_isbn2KeyReleased(evt);
            }
        });

        jLabel162.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel162.setText("Classification Number:");

        class_save_hide1.setBackground(new java.awt.Color(255, 255, 255));

        si_save4.setBackground(new java.awt.Color(51, 153, 255));
        si_save4.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        si_save4.setForeground(new java.awt.Color(255, 255, 255));
        si_save4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/save.png"))); // NOI18N
        si_save4.setText("Save");
        si_save4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                si_save4ActionPerformed(evt);
            }
        });

        btn_del1.setBackground(new java.awt.Color(51, 153, 255));
        btn_del1.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        btn_del1.setForeground(new java.awt.Color(255, 255, 255));
        btn_del1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/trash.png"))); // NOI18N
        btn_del1.setText("Delete");
        btn_del1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_del1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout class_save_hide1Layout = new javax.swing.GroupLayout(class_save_hide1);
        class_save_hide1.setLayout(class_save_hide1Layout);
        class_save_hide1Layout.setHorizontalGroup(
            class_save_hide1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, class_save_hide1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_del1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(si_save4)
                .addContainerGap())
        );
        class_save_hide1Layout.setVerticalGroup(
            class_save_hide1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(class_save_hide1Layout.createSequentialGroup()
                .addGroup(class_save_hide1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(si_save4, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_del1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 13, Short.MAX_VALUE))
        );

        class_num1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                class_num1KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel66Layout = new javax.swing.GroupLayout(jPanel66);
        jPanel66.setLayout(jPanel66Layout);
        jPanel66Layout.setHorizontalGroup(
            jPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel66Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel66Layout.createSequentialGroup()
                        .addComponent(jLabel162, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(class_num1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel66Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(class_save_hide1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel66Layout.createSequentialGroup()
                        .addComponent(jLabel161, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addGroup(jPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(si_isbn2)
                            .addGroup(jPanel66Layout.createSequentialGroup()
                                .addComponent(cl_io, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel66Layout.setVerticalGroup(
            jPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel66Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel162, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(class_num1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel66Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(si_isbn2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel161, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cl_io, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addComponent(class_save_hide1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel73Layout = new javax.swing.GroupLayout(jPanel73);
        jPanel73.setLayout(jPanel73Layout);
        jPanel73Layout.setHorizontalGroup(
            jPanel73Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel73Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel73Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel66, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(si_save3, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel67, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel73Layout.setVerticalGroup(
            jPanel73Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel73Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel73Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel73Layout.createSequentialGroup()
                        .addComponent(si_save3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel66, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel67, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jTabbedPane6.addTab("CLASSIFICATION", jPanel73);

        sup_save2.setBackground(new java.awt.Color(51, 153, 255));
        sup_save2.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        sup_save2.setForeground(new java.awt.Color(255, 255, 255));
        sup_save2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/return.png"))); // NOI18N
        sup_save2.setText("Back");
        sup_save2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sup_save2ActionPerformed(evt);
            }
        });

        jLabel116.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel116.setText("Search");

        sup_search1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                sup_search1KeyReleased(evt);
            }
        });

        jPanel56.setBackground(new java.awt.Color(255, 255, 255));
        jPanel56.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "PUBLISHER LIST"));

        sup_table1.setModel(new javax.swing.table.DefaultTableModel(
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
        sup_table1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        sup_table1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sup_table1MouseClicked(evt);
            }
        });
        jScrollPane23.setViewportView(sup_table1);

        javax.swing.GroupLayout jPanel56Layout = new javax.swing.GroupLayout(jPanel56);
        jPanel56.setLayout(jPanel56Layout);
        jPanel56Layout.setHorizontalGroup(
            jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane23, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
        );
        jPanel56Layout.setVerticalGroup(
            jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane23, javax.swing.GroupLayout.DEFAULT_SIZE, 507, Short.MAX_VALUE)
        );

        jPanel57.setBackground(new java.awt.Color(255, 255, 255));
        jPanel57.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "PUBSLISHER INFO"));

        jLabel117.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel117.setText("Publisher ID:");

        jLabel118.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel118.setText("Publisher Name:");

        sup_name1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                sup_name1KeyReleased(evt);
            }
        });

        sup_add1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                sup_add1KeyReleased(evt);
            }
        });

        sup_save1.setBackground(new java.awt.Color(51, 153, 255));
        sup_save1.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        sup_save1.setForeground(new java.awt.Color(255, 255, 255));
        sup_save1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/save.png"))); // NOI18N
        sup_save1.setText("Save");
        sup_save1.setEnabled(false);
        sup_save1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sup_save1ActionPerformed(evt);
            }
        });

        sup_update1.setBackground(new java.awt.Color(51, 153, 255));
        sup_update1.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        sup_update1.setForeground(new java.awt.Color(255, 255, 255));
        sup_update1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/update.png"))); // NOI18N
        sup_update1.setText("Update");
        sup_update1.setEnabled(false);
        sup_update1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sup_update1ActionPerformed(evt);
            }
        });

        sup_delete1.setBackground(new java.awt.Color(51, 153, 255));
        sup_delete1.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        sup_delete1.setForeground(new java.awt.Color(255, 255, 255));
        sup_delete1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/trash.png"))); // NOI18N
        sup_delete1.setText("Delete");
        sup_delete1.setEnabled(false);
        sup_delete1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sup_delete1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel57Layout = new javax.swing.GroupLayout(jPanel57);
        jPanel57.setLayout(jPanel57Layout);
        jPanel57Layout.setHorizontalGroup(
            jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel57Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(sup_save1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sup_update1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sup_delete1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel57Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel117, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                    .addComponent(jLabel118, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sup_name1, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                    .addComponent(sup_add1))
                .addContainerGap())
        );
        jPanel57Layout.setVerticalGroup(
            jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel57Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel117, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sup_name1, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel118, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sup_add1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 137, Short.MAX_VALUE)
                .addGroup(jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sup_save1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sup_update1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sup_delete1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout jPanel74Layout = new javax.swing.GroupLayout(jPanel74);
        jPanel74.setLayout(jPanel74Layout);
        jPanel74Layout.setHorizontalGroup(
            jPanel74Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel74Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel74Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel57, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel74Layout.createSequentialGroup()
                        .addComponent(sup_save2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGroup(jPanel74Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel74Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel116, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sup_search1, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel74Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel56, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
        );
        jPanel74Layout.setVerticalGroup(
            jPanel74Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel74Layout.createSequentialGroup()
                .addGroup(jPanel74Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel74Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(sup_save2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel57, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel74Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(jPanel74Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(sup_search1, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                            .addComponent(jLabel116, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addComponent(jPanel56, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jTabbedPane6.addTab("PUBLISHER", jPanel74);

        sup_save3.setBackground(new java.awt.Color(51, 153, 255));
        sup_save3.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        sup_save3.setForeground(new java.awt.Color(255, 255, 255));
        sup_save3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/return.png"))); // NOI18N
        sup_save3.setText("Back");
        sup_save3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sup_save3ActionPerformed(evt);
            }
        });

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/supplier.png"))); // NOI18N

        jLabel14.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel14.setText("Search");

        sup_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                sup_searchKeyReleased(evt);
            }
        });

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));
        jPanel21.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "SUPPLIER LIST"));

        sup_table.setModel(new javax.swing.table.DefaultTableModel(
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
        sup_table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        sup_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sup_tableMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(sup_table);

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 515, Short.MAX_VALUE)
        );

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));
        jPanel20.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "SUPPLIER INFO"));

        jLabel13.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel13.setText("Supplier Name/Comp:");

        jLabel40.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel40.setText("Address:");

        sup_name.setEnabled(false);
        sup_name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                sup_nameKeyReleased(evt);
            }
        });

        sup_add.setEnabled(false);
        sup_add.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                sup_addKeyReleased(evt);
            }
        });

        jLabel47.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel47.setText("Email:");

        sup_em.setEnabled(false);

        jLabel48.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel48.setText("Contact No.:");

        sup_con.setEnabled(false);
        sup_con.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                sup_conKeyReleased(evt);
            }
        });

        jLabel49.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel49.setText("Date_Encoded:");

        sup_enc.setDateFormatString("MM/dd/yyyy");
        sup_enc.setEnabled(false);

        jLabel98.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel98.setText("Contact Person:");

        sup_cp.setEnabled(false);

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sup_name)
                            .addComponent(sup_add)))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(73, 73, 73)
                        .addComponent(sup_em))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                        .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(73, 73, 73)
                        .addComponent(sup_con))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(sup_enc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                        .addComponent(jLabel98, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(sup_cp)))
                .addContainerGap())
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sup_name, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sup_add, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sup_con, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sup_em, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel98, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sup_cp, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(sup_enc, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel22.setBackground(new java.awt.Color(255, 255, 255));
        jPanel22.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "OPTION"));

        sup_new.setBackground(new java.awt.Color(51, 153, 255));
        sup_new.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        sup_new.setForeground(new java.awt.Color(255, 255, 255));
        sup_new.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/new.png"))); // NOI18N
        sup_new.setText("New");
        sup_new.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sup_newActionPerformed(evt);
            }
        });

        sup_save.setBackground(new java.awt.Color(51, 153, 255));
        sup_save.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        sup_save.setForeground(new java.awt.Color(255, 255, 255));
        sup_save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/save.png"))); // NOI18N
        sup_save.setText("Save");
        sup_save.setEnabled(false);
        sup_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sup_saveActionPerformed(evt);
            }
        });

        sup_update.setBackground(new java.awt.Color(51, 153, 255));
        sup_update.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        sup_update.setForeground(new java.awt.Color(255, 255, 255));
        sup_update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/update.png"))); // NOI18N
        sup_update.setText("Update");
        sup_update.setEnabled(false);
        sup_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sup_updateActionPerformed(evt);
            }
        });

        sup_delete.setBackground(new java.awt.Color(51, 153, 255));
        sup_delete.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        sup_delete.setForeground(new java.awt.Color(255, 255, 255));
        sup_delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/trash.png"))); // NOI18N
        sup_delete.setText("Delete");
        sup_delete.setEnabled(false);
        sup_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sup_deleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addComponent(sup_new)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sup_save)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sup_update)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sup_delete)
                .addGap(0, 11, Short.MAX_VALUE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sup_new, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sup_save, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sup_update, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sup_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel75Layout = new javax.swing.GroupLayout(jPanel75);
        jPanel75.setLayout(jPanel75Layout);
        jPanel75Layout.setHorizontalGroup(
            jPanel75Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel75Layout.createSequentialGroup()
                .addGroup(jPanel75Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel75Layout.createSequentialGroup()
                        .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel75Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(sup_save3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addGap(36, 36, 36)))
                .addGroup(jPanel75Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel75Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sup_search, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel75Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(55, 55, 55))
        );
        jPanel75Layout.setVerticalGroup(
            jPanel75Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel75Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel75Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel75Layout.createSequentialGroup()
                        .addGroup(jPanel75Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(sup_search, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel75Layout.createSequentialGroup()
                        .addGroup(jPanel75Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel75Layout.createSequentialGroup()
                                .addComponent(sup_save3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel75Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jTabbedPane6.addTab("SUPPLIER", jPanel75);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "BOOK INFO"));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel8.setText("ISBN No.");
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 86, 24));

        jLabel16.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel16.setText("Book Title:");
        jPanel4.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 86, 24));

        jLabel17.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel17.setText("Call Number:");
        jPanel4.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 99, 24));

        jLabel18.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel18.setText("Classification:");
        jPanel4.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 99, 24));

        jLabel20.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel20.setText("Price:");
        jPanel4.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 470, 99, 25));

        nb_is.setEnabled(false);
        nb_is.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nb_isKeyReleased(evt);
            }
        });
        jPanel4.add(nb_is, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, 219, 31));

        nb_bt.setEditable(false);
        nb_bt.setEnabled(false);
        nb_bt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nb_btKeyReleased(evt);
            }
        });
        jPanel4.add(nb_bt, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 110, 219, 31));

        nb_cn.setEnabled(false);
        nb_cn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nb_cnKeyReleased(evt);
            }
        });
        jPanel4.add(nb_cn, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 240, 219, 31));

        jLabel19.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel19.setText("Edition:");
        jPanel4.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, 99, 24));

        jLabel22.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel22.setText("Copy Right Year:");
        jPanel4.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 420, 130, 25));

        nb_class.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Choose Book Classification--" }));
        nb_class.setEnabled(false);
        jPanel4.add(nb_class, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 280, 181, 34));

        nb_edi.setEnabled(false);
        jPanel4.add(nb_edi, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 370, 219, 31));

        jLabel23.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel23.setText("Quantiy:");
        jPanel4.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 86, 24));

        nb_qty.setEditable(false);
        nb_qty.setText("0");
        nb_qty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nb_qtyActionPerformed(evt);
            }
        });
        jPanel4.add(nb_qty, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 190, 219, 31));

        jLabel38.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel38.setText("Author:");
        jPanel4.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 86, 24));

        nb_aut.setEnabled(false);
        nb_aut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nb_autKeyReleased(evt);
            }
        });
        jPanel4.add(nb_aut, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 150, 219, 31));

        nb_prc.setEditable(false);
        nb_prc.setEnabled(false);
        jPanel4.add(nb_prc, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 460, 219, 31));

        jLabel39.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel39.setText("Date_Arrival:");
        jPanel4.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 510, 99, 25));

        nb_da.setDateFormatString("MM/dd/yyyy");
        nb_da.setEnabled(false);
        jPanel4.add(nb_da, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 500, 219, 33));

        class_add.setBackground(new java.awt.Color(255, 255, 255));
        class_add.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        class_add.setForeground(new java.awt.Color(255, 255, 255));
        class_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/add.png"))); // NOI18N
        class_add.setEnabled(false);
        class_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                class_addActionPerformed(evt);
            }
        });
        jPanel4.add(class_add, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 280, 32, 34));

        jLabel119.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel119.setText("Publisher:");
        jPanel4.add(jLabel119, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 99, 24));

        nb_cpub.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Choose Book Classification--" }));
        nb_cpub.setEnabled(false);
        jPanel4.add(nb_cpub, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 330, 181, 34));

        class_add2.setBackground(new java.awt.Color(255, 255, 255));
        class_add2.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        class_add2.setForeground(new java.awt.Color(255, 255, 255));
        class_add2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/add.png"))); // NOI18N
        class_add2.setEnabled(false);
        class_add2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                class_add2ActionPerformed(evt);
            }
        });
        jPanel4.add(class_add2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 330, 32, 34));

        nb_cry.setDateFormatString("yyyy");
        jPanel4.add(nb_cry, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 410, 219, 37));

        jLabel187.setForeground(new java.awt.Color(51, 255, 0));
        jPanel4.add(jLabel187, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 50, 220, 20));

        jLabel188.setForeground(new java.awt.Color(255, 255, 255));
        jLabel188.setText("jLabel188");
        jPanel4.add(jLabel188, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 140, -1));

        jLabel189.setForeground(new java.awt.Color(51, 255, 0));
        jPanel4.add(jLabel189, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 220, 220, 20));

        jLabel11.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel11.setText("Search");

        nb_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nb_searchKeyReleased(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "BOOK INFO"));

        nb_table1.setModel(new javax.swing.table.DefaultTableModel(
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
        nb_table1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        nb_table1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nb_table1MouseClicked(evt);
            }
        });
        jScrollPane24.setViewportView(nb_table1);

        javax.swing.GroupLayout jPanel58Layout = new javax.swing.GroupLayout(jPanel58);
        jPanel58.setLayout(jPanel58Layout);
        jPanel58Layout.setHorizontalGroup(
            jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane24, javax.swing.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE)
        );
        jPanel58Layout.setVerticalGroup(
            jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane24, javax.swing.GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
        );

        jTabbedPane5.addTab("Purchased Book", jPanel58);

        jPanel59.setBackground(new java.awt.Color(255, 255, 255));

        nb_table.setModel(new javax.swing.table.DefaultTableModel(
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
        nb_table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        nb_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nb_tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(nb_table);

        javax.swing.GroupLayout jPanel59Layout = new javax.swing.GroupLayout(jPanel59);
        jPanel59.setLayout(jPanel59Layout);
        jPanel59Layout.setHorizontalGroup(
            jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE)
        );
        jPanel59Layout.setVerticalGroup(
            jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
        );

        jTabbedPane5.addTab("Library Books Onhand", jPanel59);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane5)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane5)
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "OPTION"));

        nb_new.setBackground(new java.awt.Color(51, 153, 255));
        nb_new.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        nb_new.setForeground(new java.awt.Color(255, 255, 255));
        nb_new.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/new.png"))); // NOI18N
        nb_new.setText("New");
        nb_new.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nb_newActionPerformed(evt);
            }
        });

        nb_save.setBackground(new java.awt.Color(51, 153, 255));
        nb_save.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        nb_save.setForeground(new java.awt.Color(255, 255, 255));
        nb_save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/save.png"))); // NOI18N
        nb_save.setText("Save");
        nb_save.setEnabled(false);
        nb_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nb_saveActionPerformed(evt);
            }
        });

        nb_update.setBackground(new java.awt.Color(51, 153, 255));
        nb_update.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        nb_update.setForeground(new java.awt.Color(255, 255, 255));
        nb_update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/update.png"))); // NOI18N
        nb_update.setText("Update");
        nb_update.setEnabled(false);
        nb_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nb_updateActionPerformed(evt);
            }
        });

        nb_delete.setBackground(new java.awt.Color(51, 153, 255));
        nb_delete.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        nb_delete.setForeground(new java.awt.Color(255, 255, 255));
        nb_delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/trash.png"))); // NOI18N
        nb_delete.setText("Delete");
        nb_delete.setEnabled(false);
        nb_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nb_deleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addComponent(nb_new)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nb_save)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nb_update)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nb_delete)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nb_new, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nb_save, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nb_update, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nb_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 8, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel76Layout = new javax.swing.GroupLayout(jPanel76);
        jPanel76.setLayout(jPanel76Layout);
        jPanel76Layout.setHorizontalGroup(
            jPanel76Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel76Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel76Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel76Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nb_search, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        jPanel76Layout.setVerticalGroup(
            jPanel76Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel76Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel76Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nb_search)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(jPanel76Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel76Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)))
        );

        jTabbedPane6.addTab("NEW BOOK", jPanel76);

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "ACCOUNT INFORMATION"));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel10.setText("First Name:");
        jPanel10.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 86, 24));

        jLabel30.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel30.setText("Username:");
        jPanel10.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 86, 24));

        jLabel32.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel32.setText("Confirm:");
        jPanel10.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 99, 24));

        jLabel33.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel33.setText("Level:");
        jPanel10.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 99, 24));

        jLabel34.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel34.setText("Photo:");
        jPanel10.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, 99, 25));

        acc_fn.setEnabled(false);
        jPanel10.add(acc_fn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 140, 31));

        jLabel36.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel36.setText("Mobile Number:");
        jPanel10.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 70, 99, 24));

        acc_lvl.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Please Select--", "Admin", "User", "Faculty", "Student" }));
        acc_lvl.setEnabled(false);
        jPanel10.add(acc_lvl, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 300, 283, 34));

        jLabel37.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel37.setText("Password:");
        jPanel10.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 86, 24));

        acc_mn.setEnabled(false);
        jPanel10.add(acc_mn, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 90, 140, 31));

        acc_image.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        acc_image.setText("Select Image");
        acc_image.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel10.add(acc_image, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 350, 220, 160));

        acc_browse.setBackground(new java.awt.Color(51, 153, 255));
        acc_browse.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        acc_browse.setForeground(new java.awt.Color(255, 255, 255));
        acc_browse.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/browsing.png"))); // NOI18N
        acc_browse.setText("Browse");
        acc_browse.setEnabled(false);
        acc_browse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acc_browseActionPerformed(evt);
            }
        });
        jPanel10.add(acc_browse, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 460, -1, 44));

        acc_con.setEnabled(false);
        jPanel10.add(acc_con, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 260, 280, 30));

        acc_pass.setEnabled(false);
        jPanel10.add(acc_pass, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 220, 280, 30));

        acc_user.setEnabled(false);
        jPanel10.add(acc_user, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 180, 280, 31));

        jLabel175.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel175.setText("Surname:");
        jPanel10.add(jLabel175, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 20, 86, 24));

        acc_ffsn.setEnabled(false);
        jPanel10.add(acc_ffsn, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 40, 140, 31));

        acc_ffmn.setEnabled(false);
        jPanel10.add(acc_ffmn, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, 140, 31));

        jLabel176.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel176.setText("Middle Name:");
        jPanel10.add(jLabel176, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, 86, 24));

        jLabel177.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel177.setText("Employee ID");
        jPanel10.add(jLabel177, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 86, 24));

        jLabel178.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel178.setText("Position");
        jPanel10.add(jLabel178, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 86, 24));

        acc_mid.setEnabled(false);
        jPanel10.add(acc_mid, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 140, 31));

        acc_pos.setEnabled(false);
        jPanel10.add(acc_pos, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, 140, 31));
        jPanel10.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 420, -1));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "OPTION"));

        acc_new.setBackground(new java.awt.Color(51, 153, 255));
        acc_new.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        acc_new.setForeground(new java.awt.Color(255, 255, 255));
        acc_new.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/new.png"))); // NOI18N
        acc_new.setText("New");
        acc_new.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acc_newActionPerformed(evt);
            }
        });

        acc_save.setBackground(new java.awt.Color(51, 153, 255));
        acc_save.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        acc_save.setForeground(new java.awt.Color(255, 255, 255));
        acc_save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/save.png"))); // NOI18N
        acc_save.setText("Save");
        acc_save.setEnabled(false);
        acc_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acc_saveActionPerformed(evt);
            }
        });

        acc_update.setBackground(new java.awt.Color(51, 153, 255));
        acc_update.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        acc_update.setForeground(new java.awt.Color(255, 255, 255));
        acc_update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/update.png"))); // NOI18N
        acc_update.setText("Update");
        acc_update.setEnabled(false);
        acc_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acc_updateActionPerformed(evt);
            }
        });

        acc_delete.setBackground(new java.awt.Color(51, 153, 255));
        acc_delete.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        acc_delete.setForeground(new java.awt.Color(255, 255, 255));
        acc_delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/trash.png"))); // NOI18N
        acc_delete.setText("Delete");
        acc_delete.setEnabled(false);
        acc_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acc_deleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(acc_new)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(acc_save)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(acc_update)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(acc_delete)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(acc_new, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(acc_save, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(acc_update, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(acc_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "ACCOUNT INFO"));

        acc_table.setModel(new javax.swing.table.DefaultTableModel(
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
        acc_table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        acc_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                acc_tableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(acc_table);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
        );

        javax.swing.GroupLayout jPanel77Layout = new javax.swing.GroupLayout(jPanel77);
        jPanel77.setLayout(jPanel77Layout);
        jPanel77Layout.setHorizontalGroup(
            jPanel77Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel77Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel77Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );
        jPanel77Layout.setVerticalGroup(
            jPanel77Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel77Layout.createSequentialGroup()
                .addGroup(jPanel77Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel77Layout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 519, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane6.addTab("USER ACCOUNT", jPanel77);

        jPanel78.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "PERSONAL INFORMATION"));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel24.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel24.setText("ID TYPE:");
        jPanel8.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 86, 24));

        jLabel25.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel25.setText("Course:");
        jPanel8.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 99, 24));

        jLabel26.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel26.setText("Address:");
        jPanel8.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 99, 24));

        jLabel27.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel27.setText("Photo:");
        jPanel8.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 99, 25));

        brr_fn.setEnabled(false);
        jPanel8.add(brr_fn, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 140, 31));

        brr_stat.setEnabled(false);
        jPanel8.add(brr_stat, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 210, 30));

        jLabel28.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel28.setText("Birth Date:");
        jPanel8.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 260, 99, 24));

        jLabel29.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel29.setText("Status:");
        jPanel8.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 99, 24));

        brr_bd.setDateFormatString("MM-dd-yyyy");
        brr_bd.setEnabled(false);
        jPanel8.add(brr_bd, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 280, 210, 30));

        jLabel31.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel31.setText("ID Number:");
        jPanel8.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 80, 86, 24));

        brr_idn.setEnabled(false);
        jPanel8.add(brr_idn, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 100, 210, 30));

        brr_image.setForeground(new java.awt.Color(0, 0, 204));
        brr_image.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        brr_image.setText("No Image!");
        brr_image.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel8.add(brr_image, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, 250, 160));

        brr_browse.setBackground(new java.awt.Color(51, 153, 255));
        brr_browse.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        brr_browse.setForeground(new java.awt.Color(255, 255, 255));
        brr_browse.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/browsing.png"))); // NOI18N
        brr_browse.setText("Browse");
        brr_browse.setEnabled(false);
        brr_browse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                brr_browseActionPerformed(evt);
            }
        });
        jPanel8.add(brr_browse, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 460, -1, 44));

        brr_yr.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Choose Year Level--", "1st Year", "2nd Year", "3rd Year", "4th Year", "5th Year", "6th Year", "7th Year and Up" }));
        brr_yr.setEnabled(false);
        jPanel8.add(brr_yr, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 160, 200, 30));

        brr_add.setEnabled(false);
        jPanel8.add(brr_add, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 420, 31));

        brr_mn.setEnabled(false);
        jPanel8.add(brr_mn, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 40, 140, 31));

        jLabel181.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel181.setText("First Name");
        jPanel8.add(jLabel181, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 86, 24));

        jLabel182.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel182.setText("Middle Name");
        jPanel8.add(jLabel182, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, 86, 24));

        brr_idt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Choose Valid ID--", "Employee ID", "Student ID" }));
        brr_idt.setEnabled(false);
        brr_idt.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                brr_idtItemStateChanged(evt);
            }
        });
        jPanel8.add(brr_idt, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 210, 30));

        jLabel183.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jPanel8.add(jLabel183, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 190, 130, 24));

        brr_cr.setEnabled(false);
        jPanel8.add(brr_cr, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 190, 30));

        brr_sn.setEnabled(false);
        jPanel8.add(brr_sn, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 40, 140, 31));

        jLabel184.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel184.setText("Surname");
        jPanel8.add(jLabel184, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 20, 86, 24));

        course_add.setBackground(new java.awt.Color(255, 255, 255));
        course_add.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        course_add.setForeground(new java.awt.Color(255, 255, 255));
        course_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/add.png"))); // NOI18N
        course_add.setEnabled(false);
        course_add.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                course_addMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                course_addMouseExited(evt);
            }
        });
        course_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                course_addActionPerformed(evt);
            }
        });
        jPanel8.add(course_add, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 160, 30, 30));

        jLabel186.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel186.setText("Year:");
        jPanel8.add(jLabel186, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 140, 99, 24));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "OPTION"));

        brr_new.setBackground(new java.awt.Color(51, 153, 255));
        brr_new.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        brr_new.setForeground(new java.awt.Color(255, 255, 255));
        brr_new.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/new.png"))); // NOI18N
        brr_new.setText("New");
        brr_new.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                brr_newActionPerformed(evt);
            }
        });

        brr_save.setBackground(new java.awt.Color(51, 153, 255));
        brr_save.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        brr_save.setForeground(new java.awt.Color(255, 255, 255));
        brr_save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/save.png"))); // NOI18N
        brr_save.setText("Save");
        brr_save.setEnabled(false);
        brr_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                brr_saveActionPerformed(evt);
            }
        });

        brr_update.setBackground(new java.awt.Color(51, 153, 255));
        brr_update.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        brr_update.setForeground(new java.awt.Color(255, 255, 255));
        brr_update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/update.png"))); // NOI18N
        brr_update.setText("Update");
        brr_update.setEnabled(false);
        brr_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                brr_updateActionPerformed(evt);
            }
        });

        brr_delete.setBackground(new java.awt.Color(51, 153, 255));
        brr_delete.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        brr_delete.setForeground(new java.awt.Color(255, 255, 255));
        brr_delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/trash.png"))); // NOI18N
        brr_delete.setText("Delete");
        brr_delete.setEnabled(false);
        brr_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                brr_deleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(brr_new)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(brr_save)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(brr_update)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(brr_delete)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(brr_new, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(brr_save, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(brr_update, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(brr_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "BORROWERS INFO"));

        brr_table.setModel(new javax.swing.table.DefaultTableModel(
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
        brr_table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        brr_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                brr_tableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(brr_table);

        brr_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                brr_searchKeyReleased(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel9.setText("Search:");

        jLabel185.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel185.setText("Count:");

        brr_count.setEditable(false);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jLabel185, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(brr_count, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(brr_search, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(brr_search, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel185, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(brr_count, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        javax.swing.GroupLayout jPanel78Layout = new javax.swing.GroupLayout(jPanel78);
        jPanel78.setLayout(jPanel78Layout);
        jPanel78Layout.setHorizontalGroup(
            jPanel78Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel78Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel78Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
        jPanel78Layout.setVerticalGroup(
            jPanel78Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel78Layout.createSequentialGroup()
                .addGroup(jPanel78Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel78Layout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 519, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane6.addTab("BORROWER", jPanel78);

        javax.swing.GroupLayout RecordsLayout = new javax.swing.GroupLayout(Records);
        Records.setLayout(RecordsLayout);
        RecordsLayout.setHorizontalGroup(
            RecordsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RecordsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 881, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(456, Short.MAX_VALUE))
        );
        RecordsLayout.setVerticalGroup(
            RecordsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RecordsLayout.createSequentialGroup()
                .addComponent(jTabbedPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 628, Short.MAX_VALUE)
                .addContainerGap())
        );

        stock.add(Records, "card14");

        Bookloan.setBackground(new java.awt.Color(255, 255, 255));

        jPanel30.setBackground(new java.awt.Color(255, 255, 255));
        jPanel30.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "PROCESS DETAILS"));
        jPanel30.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel62.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel62.setText("Full Name:");
        jPanel30.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 86, 24));

        jLabel66.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel66.setText("Status:");
        jPanel30.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 86, 24));

        jLabel67.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel67.setText("Book Price:");
        jPanel30.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 99, 24));

        jLabel68.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel68.setText("Classification:");
        jPanel30.add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 99, 24));

        brrd_bp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                brrd_bpKeyReleased(evt);
            }
        });
        jPanel30.add(brrd_bp, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 240, 200, 31));

        brrd_rem.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        brrd_rem.setText("Return Date:");
        jPanel30.add(brrd_rem, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 460, 99, 24));

        jLabel71.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel71.setText("Fines /Day:");
        jPanel30.add(jLabel71, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 99, 24));

        brrd_class.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Select Classification--" }));
        brrd_class.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                brrd_classPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        jPanel30.add(brrd_class, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 280, 200, 34));

        jLabel72.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel72.setText("Book Title:");
        jPanel30.add(jLabel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 86, 24));

        brrd_bt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                brrd_btKeyReleased(evt);
            }
        });
        jPanel30.add(brrd_bt, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 200, 200, 31));

        brrd_qty.setEditable(false);
        brrd_qty.setText("1");
        brrd_qty.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                brrd_qtyMouseClicked(evt);
            }
        });
        jPanel30.add(brrd_qty, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 380, 140, 31));

        brrd_fn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                brrd_fnKeyReleased(evt);
            }
        });
        jPanel30.add(brrd_fn, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 110, 200, 31));

        jLabel73.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel73.setText("Quantity:");
        jPanel30.add(jLabel73, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, 99, 24));

        brrd_stat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Choose Status--", "Faculty", "Student" }));
        brrd_stat.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                brrd_statPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        jPanel30.add(brrd_stat, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 150, 200, 34));
        jPanel30.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, 360, 10));
        jPanel30.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 360, 10));

        jLabel74.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel74.setText("Borrowed Date:");
        jPanel30.add(jLabel74, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 420, 99, 24));

        brrd_add.setBackground(new java.awt.Color(255, 255, 255));
        brrd_add.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        brrd_add.setForeground(new java.awt.Color(255, 255, 255));
        brrd_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/add.png"))); // NOI18N
        brrd_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                brrd_addActionPerformed(evt);
            }
        });
        jPanel30.add(brrd_add, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 380, 30, 30));

        brrd_min.setBackground(new java.awt.Color(255, 255, 255));
        brrd_min.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        brrd_min.setForeground(new java.awt.Color(255, 255, 255));
        brrd_min.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/minus.png"))); // NOI18N
        brrd_min.setEnabled(false);
        brrd_min.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                brrd_minActionPerformed(evt);
            }
        });
        jPanel30.add(brrd_min, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 380, 30, 30));

        brrd_remcb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Borrowed", "Loss", "Damage" }));
        jPanel30.add(brrd_remcb, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 500, 200, 34));

        brrd_fd.setEditable(false);
        jPanel30.add(brrd_fd, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 320, 200, 31));

        jLabel78.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel78.setText("Library ID:");
        jPanel30.add(jLabel78, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 86, 24));

        borroweddate.setEditable(false);
        jPanel30.add(borroweddate, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 420, 200, 30));

        brrd_rem1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        brrd_rem1.setText("Remark:");
        jPanel30.add(brrd_rem1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 500, 99, 24));

        returndate.setEditable(false);
        jPanel30.add(returndate, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 460, 200, 30));
        jPanel30.add(brrd_libid1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 70, 200, 31));

        jLabel190.setForeground(new java.awt.Color(255, 255, 255));
        jLabel190.setText("jLabel190");
        jPanel30.add(jLabel190, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 40, -1, -1));

        jLabel191.setForeground(new java.awt.Color(255, 255, 255));
        jLabel191.setText("jLabel191");
        jPanel30.add(jLabel191, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 40, -1, -1));

        jPanel31.setBackground(new java.awt.Color(255, 255, 255));
        jPanel31.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "TABLES"));
        jPanel31.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane2.setFont(new java.awt.Font("Arial Black", 1, 11)); // NOI18N

        brrd_rb_table.setModel(new javax.swing.table.DefaultTableModel(
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
        brrd_rb_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                brrd_rb_tableMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(brrd_rb_table);

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("REGISTERED BORROWER", jPanel32);

        brrd_bl_table.setModel(new javax.swing.table.DefaultTableModel(
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
        brrd_bl_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                brrd_bl_tableMouseClicked(evt);
            }
        });
        jScrollPane11.setViewportView(brrd_bl_table);

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("BOOK LIST", jPanel33);

        jPanel34.setBackground(new java.awt.Color(255, 255, 255));
        jPanel34.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        brrd_log_table.setModel(new javax.swing.table.DefaultTableModel(
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
        brrd_log_table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        brrd_log_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                brrd_log_tableMouseClicked(evt);
            }
        });
        jScrollPane12.setViewportView(brrd_log_table);

        jPanel34.add(jScrollPane12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 44, -1, 396));

        brrd_uprem.setBackground(new java.awt.Color(51, 153, 255));
        brrd_uprem.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        brrd_uprem.setForeground(new java.awt.Color(255, 255, 255));
        brrd_uprem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/update.png"))); // NOI18N
        brrd_uprem.setText("Update Remark");
        brrd_uprem.setEnabled(false);
        brrd_uprem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                brrd_upremActionPerformed(evt);
            }
        });
        jPanel34.add(brrd_uprem, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 495, -1, 44));
        jPanel34.add(brrd_fd1, new org.netbeans.lib.awtextra.AbsoluteConstraints(296, 446, 149, 31));

        jLabel75.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel75.setText("Total Payable:");
        jPanel34.add(jLabel75, new org.netbeans.lib.awtextra.AbsoluteConstraints(187, 452, 99, -1));

        brrd_return.setBackground(new java.awt.Color(51, 153, 255));
        brrd_return.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        brrd_return.setForeground(new java.awt.Color(255, 255, 255));
        brrd_return.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/return.png"))); // NOI18N
        brrd_return.setText("Return");
        brrd_return.setEnabled(false);
        brrd_return.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                brrd_returnActionPerformed(evt);
            }
        });
        jPanel34.add(brrd_return, new org.netbeans.lib.awtextra.AbsoluteConstraints(195, 495, -1, 44));

        jLabel77.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel77.setText("Fines:");
        jPanel34.add(jLabel77, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 452, 47, -1));
        jPanel34.add(brrd_fd2, new org.netbeans.lib.awtextra.AbsoluteConstraints(61, 446, 122, 31));

        brrd_clear1.setBackground(new java.awt.Color(51, 153, 255));
        brrd_clear1.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        brrd_clear1.setForeground(new java.awt.Color(255, 255, 255));
        brrd_clear1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/payment.png"))); // NOI18N
        brrd_clear1.setText("Payment");
        brrd_clear1.setEnabled(false);
        brrd_clear1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                brrd_clear1ActionPerformed(evt);
            }
        });
        jPanel34.add(brrd_clear1, new org.netbeans.lib.awtextra.AbsoluteConstraints(314, 495, -1, 44));

        jTextField11.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField11KeyReleased(evt);
            }
        });
        jPanel34.add(jTextField11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 224, 38));

        jLabel192.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/canchu.png"))); // NOI18N
        jLabel192.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel192MouseClicked(evt);
            }
        });
        jPanel34.add(jLabel192, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 0, -1, 34));

        jTabbedPane2.addTab("ACTIVE BORROWER", jPanel34);

        jPanel36.setBackground(new java.awt.Color(255, 255, 255));

        borrowedlog_table.setModel(new javax.swing.table.DefaultTableModel(
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
        borrowedlog_table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane14.setViewportView(borrowedlog_table);

        brrd_fn2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Choose Borrower--" }));
        brrd_fn2.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                brrd_fn2PopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jLabel69.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel69.setText("Search:");

        brrd_fd3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                brrd_fd3KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel36Layout = new javax.swing.GroupLayout(jPanel36);
        jPanel36.setLayout(jPanel36Layout);
        jPanel36Layout.setHorizontalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel36Layout.createSequentialGroup()
                .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(brrd_fd3, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(brrd_fn2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel36Layout.createSequentialGroup()
                    .addGap(1, 1, 1)
                    .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel36Layout.setVerticalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(brrd_fn2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(brrd_fd3))
                .addGap(0, 516, Short.MAX_VALUE))
            .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel36Layout.createSequentialGroup()
                    .addGap(0, 37, Short.MAX_VALUE)
                    .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 513, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jTabbedPane2.addTab("BORROWED LOG", jPanel36);

        jPanel31.add(jTabbedPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 460, 600));

        jPanel35.setBackground(new java.awt.Color(255, 255, 255));
        jPanel35.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "OPTION"));

        si_calc1.setBackground(new java.awt.Color(51, 153, 255));
        si_calc1.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        si_calc1.setForeground(new java.awt.Color(255, 255, 255));
        si_calc1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/process.png"))); // NOI18N
        si_calc1.setText("Process Request");
        si_calc1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                si_calc1ActionPerformed(evt);
            }
        });

        brrd_clear.setBackground(new java.awt.Color(51, 153, 255));
        brrd_clear.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        brrd_clear.setForeground(new java.awt.Color(255, 255, 255));
        brrd_clear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/clear.png"))); // NOI18N
        brrd_clear.setText("Clear");
        brrd_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                brrd_clearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
        jPanel35.setLayout(jPanel35Layout);
        jPanel35Layout.setHorizontalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(si_calc1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(brrd_clear, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel35Layout.setVerticalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(si_calc1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(brrd_clear, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout BookloanLayout = new javax.swing.GroupLayout(Bookloan);
        Bookloan.setLayout(BookloanLayout);
        BookloanLayout.setHorizontalGroup(
            BookloanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BookloanLayout.createSequentialGroup()
                .addGroup(BookloanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
                    .addComponent(jPanel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(458, Short.MAX_VALUE))
        );
        BookloanLayout.setVerticalGroup(
            BookloanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BookloanLayout.createSequentialGroup()
                .addGroup(BookloanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BookloanLayout.createSequentialGroup()
                        .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, 546, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        stock.add(Bookloan, "card5");

        inventory.setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane1.setFont(new java.awt.Font("Arial Black", 1, 11)); // NOI18N

        jPanel49.setBackground(new java.awt.Color(255, 255, 255));
        jPanel49.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "PURCHASE DETAILS"));
        jPanel49.setLayout(null);

        jLabel21.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel21.setText("Purchase Order #:");
        jPanel49.add(jLabel21);
        jLabel21.setBounds(15, 29, 130, 24);

        po_qt.setText("1");
        po_qt.setEnabled(false);
        jPanel49.add(po_qt);
        po_qt.setBounds(160, 380, 120, 31);

        jLabel103.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel103.setText("Date:");
        jPanel49.add(jLabel103);
        jLabel103.setBounds(15, 80, 99, 24);

        jLabel104.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel104.setText("Supplier:");
        jPanel49.add(jLabel104);
        jLabel104.setBounds(15, 135, 99, 24);

        po_sup.setEnabled(false);
        jPanel49.add(po_sup);
        po_sup.setBounds(160, 130, 170, 34);

        po_date.setDateFormatString("MM-dd-yyyy");
        po_date.setEnabled(false);
        jPanel49.add(po_date);
        po_date.setBounds(160, 80, 212, 33);

        po_or.setEditable(false);
        po_or.setText("Generate PO Number");
        jPanel49.add(po_or);
        po_or.setBounds(160, 30, 159, 31);

        jLabel105.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel105.setText("Book:");
        jPanel49.add(jLabel105);
        jLabel105.setBounds(15, 171, 99, 24);

        po_il.setText("In Library");
        po_il.setEnabled(false);
        po_il.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                po_ilActionPerformed(evt);
            }
        });
        jPanel49.add(po_il);
        po_il.setBounds(160, 170, 105, 33);

        po_ne.setText("New");
        po_ne.setEnabled(false);
        po_ne.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                po_neActionPerformed(evt);
            }
        });
        jPanel49.add(po_ne);
        po_ne.setBounds(270, 170, 97, 33);

        po_bt.setEditable(false);
        jPanel49.add(po_bt);
        po_bt.setBounds(160, 210, 212, 31);

        po_au.setEditable(false);
        jPanel49.add(po_au);
        po_au.setBounds(160, 250, 212, 31);

        jLabel100.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel100.setText("Price:");
        jPanel49.add(jLabel100);
        jLabel100.setBounds(15, 339, 86, 24);

        po_pr.setEnabled(false);
        jPanel49.add(po_pr);
        po_pr.setBounds(160, 340, 212, 31);

        nb_new7.setBackground(new java.awt.Color(51, 153, 255));
        nb_new7.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        nb_new7.setForeground(new java.awt.Color(255, 255, 255));
        nb_new7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/new.png"))); // NOI18N
        nb_new7.setText("New");
        nb_new7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nb_new7ActionPerformed(evt);
            }
        });
        jPanel49.add(nb_new7);
        nb_new7.setBounds(20, 460, 120, 44);

        nb_save1.setBackground(new java.awt.Color(51, 153, 255));
        nb_save1.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        nb_save1.setForeground(new java.awt.Color(255, 255, 255));
        nb_save1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/add.png"))); // NOI18N
        nb_save1.setText("Add");
        nb_save1.setEnabled(false);
        nb_save1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nb_save1ActionPerformed(evt);
            }
        });
        jPanel49.add(nb_save1);
        nb_save1.setBounds(20, 510, 120, 44);

        nb_update1.setBackground(new java.awt.Color(51, 153, 255));
        nb_update1.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        nb_update1.setForeground(new java.awt.Color(255, 255, 255));
        nb_update1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/pencil.png"))); // NOI18N
        nb_update1.setText("Edit");
        nb_update1.setEnabled(false);
        nb_update1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nb_update1ActionPerformed(evt);
            }
        });
        jPanel49.add(nb_update1);
        nb_update1.setBounds(140, 510, 120, 44);

        jLabel101.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel101.setText("Quantity:");
        jPanel49.add(jLabel101);
        jLabel101.setBounds(15, 385, 86, 24);

        po_add.setBackground(new java.awt.Color(255, 255, 255));
        po_add.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        po_add.setForeground(new java.awt.Color(255, 255, 255));
        po_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/add.png"))); // NOI18N
        po_add.setEnabled(false);
        po_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                po_addActionPerformed(evt);
            }
        });
        jPanel49.add(po_add);
        po_add.setBounds(290, 380, 35, 35);

        po_min.setBackground(new java.awt.Color(255, 255, 255));
        po_min.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        po_min.setForeground(new java.awt.Color(255, 255, 255));
        po_min.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/minus.png"))); // NOI18N
        po_min.setEnabled(false);
        po_min.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                po_minActionPerformed(evt);
            }
        });
        jPanel49.add(po_min);
        po_min.setBounds(330, 380, 35, 35);

        nb_update2.setBackground(new java.awt.Color(51, 153, 255));
        nb_update2.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        nb_update2.setForeground(new java.awt.Color(255, 255, 255));
        nb_update2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/trash.png"))); // NOI18N
        nb_update2.setText("Delete");
        nb_update2.setEnabled(false);
        nb_update2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nb_update2ActionPerformed(evt);
            }
        });
        jPanel49.add(nb_update2);
        nb_update2.setBounds(260, 510, 120, 44);

        nb_update4.setBackground(new java.awt.Color(51, 153, 255));
        nb_update4.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        nb_update4.setForeground(new java.awt.Color(255, 255, 255));
        nb_update4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/circuit.png"))); // NOI18N
        nb_update4.setEnabled(false);
        nb_update4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nb_update4ActionPerformed(evt);
            }
        });
        jPanel49.add(nb_update4);
        nb_update4.setBounds(330, 20, 47, 44);

        po_cl.setEnabled(false);
        jPanel49.add(po_cl);
        po_cl.setBounds(160, 290, 174, 34);

        class_add1.setBackground(new java.awt.Color(255, 255, 255));
        class_add1.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        class_add1.setForeground(new java.awt.Color(255, 255, 255));
        class_add1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/add.png"))); // NOI18N
        class_add1.setEnabled(false);
        class_add1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                class_add1ActionPerformed(evt);
            }
        });
        jPanel49.add(class_add1);
        class_add1.setBounds(340, 290, 32, 35);

        nb_update5.setBackground(new java.awt.Color(51, 153, 255));
        nb_update5.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        nb_update5.setForeground(new java.awt.Color(255, 255, 255));
        nb_update5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/clear.png"))); // NOI18N
        nb_update5.setText("Cancel");
        nb_update5.setEnabled(false);
        nb_update5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nb_update5ActionPerformed(evt);
            }
        });
        jPanel49.add(nb_update5);
        nb_update5.setBounds(140, 460, 120, 44);

        jLabel110.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel110.setText("Title:");
        jPanel49.add(jLabel110);
        jLabel110.setBounds(15, 212, 99, 24);

        jLabel113.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel113.setText("Author:");
        jPanel49.add(jLabel113);
        jLabel113.setBounds(15, 249, 99, 24);

        jLabel114.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel114.setText("Classification:");
        jPanel49.add(jLabel114);
        jLabel114.setBounds(15, 288, 99, 24);

        nb_update7.setBackground(new java.awt.Color(51, 153, 255));
        nb_update7.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        nb_update7.setForeground(new java.awt.Color(255, 255, 255));
        nb_update7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/printer.png"))); // NOI18N
        nb_update7.setText("Print");
        nb_update7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nb_update7ActionPerformed(evt);
            }
        });
        jPanel49.add(nb_update7);
        nb_update7.setBounds(260, 460, 120, 44);

        class_add3.setBackground(new java.awt.Color(255, 255, 255));
        class_add3.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        class_add3.setForeground(new java.awt.Color(255, 255, 255));
        class_add3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/add.png"))); // NOI18N
        class_add3.setEnabled(false);
        class_add3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                class_add3ActionPerformed(evt);
            }
        });
        jPanel49.add(class_add3);
        class_add3.setBounds(340, 130, 32, 34);

        jPanel50.setBackground(new java.awt.Color(255, 255, 255));
        jPanel50.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "PURCHASE LIST"));

        po_table.setModel(new javax.swing.table.DefaultTableModel(
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
        po_table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        po_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                po_tableMouseClicked(evt);
            }
        });
        jScrollPane19.setViewportView(po_table);

        brr_fn7.setEditable(false);
        brr_fn7.setText("0");

        jLabel99.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel99.setText("Total Item:");

        jLabel102.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel102.setText("Total Books:");

        brr_fn8.setEditable(false);

        jLabel106.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel106.setText("Total Cost:");

        brr_fn9.setEditable(false);

        jLabel107.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel107.setText("Prepared by:");

        javax.swing.GroupLayout jPanel50Layout = new javax.swing.GroupLayout(jPanel50);
        jPanel50.setLayout(jPanel50Layout);
        jPanel50Layout.setHorizontalGroup(
            jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel50Layout.createSequentialGroup()
                .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel50Layout.createSequentialGroup()
                        .addComponent(jLabel99, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(brr_fn7, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                        .addComponent(jLabel106, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(brr_fn9, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel50Layout.createSequentialGroup()
                        .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel50Layout.createSequentialGroup()
                                .addComponent(jLabel102)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(brr_fn8, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel107, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel50Layout.setVerticalGroup(
            jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel50Layout.createSequentialGroup()
                .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(brr_fn7, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel99, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel106, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(brr_fn9, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel102, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(brr_fn8, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel107, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jPanel49, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel50, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 565, Short.MAX_VALUE)
                    .addComponent(jPanel49, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 24, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("PURCHASE ORDER", jPanel19);

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));

        sum_table.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(sum_table);

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "FILTER"));

        jLabel12.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel12.setText("Classification:");

        sum_class.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Choose Book Classification--", "General", "Philippine Literature", "Fiction", "Non-Fiction", "Filipiniana", "Circulation", "Others", "" }));
        sum_class.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                sum_classPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        sum_bt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                sum_btKeyReleased(evt);
            }
        });

        jLabel46.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel46.setText("Book Title:");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(sum_bt)
                    .addComponent(sum_class, 0, 181, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(sum_class, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sum_bt)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "OPTION"));

        nb_new1.setBackground(new java.awt.Color(51, 153, 255));
        nb_new1.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        nb_new1.setForeground(new java.awt.Color(255, 255, 255));
        nb_new1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/printer.png"))); // NOI18N
        nb_new1.setText("Print");
        nb_new1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nb_new1ActionPerformed(evt);
            }
        });

        nb_new3.setBackground(new java.awt.Color(51, 153, 255));
        nb_new3.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        nb_new3.setForeground(new java.awt.Color(255, 255, 255));
        nb_new3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/pdf.png"))); // NOI18N
        nb_new3.setText("PDF");
        nb_new3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nb_new3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nb_new1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(nb_new3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nb_new1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nb_new3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 532, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 567, Short.MAX_VALUE)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("BOOK STOCK SUMMARY", jPanel13);

        jPanel47.setBackground(new java.awt.Color(255, 255, 255));

        jPanel48.setBackground(new java.awt.Color(255, 255, 255));
        jPanel48.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "PURCHASE LIST"));

        si_table1.setModel(new javax.swing.table.DefaultTableModel(
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
        si_table1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        si_table1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                si_table1MouseClicked(evt);
            }
        });
        jScrollPane18.setViewportView(si_table1);

        jLabel139.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel139.setText("Search");

        so_search1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                so_search1KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel48Layout = new javax.swing.GroupLayout(jPanel48);
        jPanel48.setLayout(jPanel48Layout);
        jPanel48Layout.setHorizontalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane18, javax.swing.GroupLayout.DEFAULT_SIZE, 853, Short.MAX_VALUE)
            .addGroup(jPanel48Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel139, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(so_search1, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel48Layout.setVerticalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel48Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(so_search1, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(jLabel139, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel47Layout = new javax.swing.GroupLayout(jPanel47);
        jPanel47.setLayout(jPanel47Layout);
        jPanel47Layout.setHorizontalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel47Layout.createSequentialGroup()
                .addComponent(jPanel48, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel47Layout.setVerticalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel47Layout.createSequentialGroup()
                .addComponent(jPanel48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("PURCHASE ORDER LOG", jPanel47);

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));

        jPanel26.setBackground(new java.awt.Color(255, 255, 255));
        jPanel26.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "BOOK STATUS"));

        jLabel44.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel44.setText("Book Title:");

        so_isbn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                so_isbnKeyReleased(evt);
            }
        });

        so_bt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                so_btKeyReleased(evt);
            }
        });

        jLabel51.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel51.setText("Classification");

        so_qty.setEditable(false);
        so_qty.setText("0");
        so_qty.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                so_qtyMouseClicked(evt);
            }
        });

        so_class.setEditable(false);

        jLabel59.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel59.setText("Status");

        jLabel61.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel61.setText("Quantity");

        so_add.setBackground(new java.awt.Color(255, 255, 255));
        so_add.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        so_add.setForeground(new java.awt.Color(255, 255, 255));
        so_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/add.png"))); // NOI18N
        so_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                so_addActionPerformed(evt);
            }
        });

        so_minus.setBackground(new java.awt.Color(255, 255, 255));
        so_minus.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        so_minus.setForeground(new java.awt.Color(255, 255, 255));
        so_minus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/minus.png"))); // NOI18N
        so_minus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                so_minusActionPerformed(evt);
            }
        });

        so_save.setBackground(new java.awt.Color(51, 153, 255));
        so_save.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        so_save.setForeground(new java.awt.Color(255, 255, 255));
        so_save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/save.png"))); // NOI18N
        so_save.setText("Save");
        so_save.setEnabled(false);
        so_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                so_saveActionPerformed(evt);
            }
        });

        so_stat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Select Status--", "Damage", "Loss" }));
        so_stat.setEnabled(false);

        jLabel45.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel45.setText("ISBN_No:");

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(44, 44, 44)
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(so_isbn)
                            .addComponent(so_bt)))
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(so_save))
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                        .addComponent(so_qty, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(so_add, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(so_minus, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(60, 60, 60)
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(so_class)
                            .addGroup(jPanel26Layout.createSequentialGroup()
                                .addComponent(so_stat, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(2, 2, 2)))))
                .addContainerGap())
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(so_isbn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel26Layout.createSequentialGroup()
                        .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(so_bt, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(so_class, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel51, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(so_stat, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(so_qty, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(so_add, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(so_minus, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(so_save, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel27.setBackground(new java.awt.Color(255, 255, 255));
        jPanel27.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "BOOK LIST"));

        so_table.setModel(new javax.swing.table.DefaultTableModel(
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
        so_table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        so_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                so_tableMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(so_table);

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE)
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7)
        );

        jPanel28.setBackground(new java.awt.Color(255, 255, 255));
        jPanel28.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "LAST UPDATE"));

        jLabel63.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel63.setText("Encoder:");

        so_enc.setEditable(false);

        jLabel64.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel64.setText("Date:");

        jLabel65.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel65.setText("Time:");

        so_date.setEditable(false);

        so_time.setEditable(false);

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(so_enc))
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(so_time))
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(so_date)))
                .addContainerGap())
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(so_enc, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(so_date, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(so_time, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel43.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel43.setText("Search");

        so_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                so_searchKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(so_search))
                    .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(so_search, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(34, 34, 34))
        );

        jTabbedPane1.addTab("STOCK OUT", jPanel15);

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));

        jPanel29.setBackground(new java.awt.Color(255, 255, 255));
        jPanel29.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "LOGS"));

        log_table.setModel(new javax.swing.table.DefaultTableModel(
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
        log_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                log_tableMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(log_table);

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 843, Short.MAX_VALUE)
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
        );

        jLabel60.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel60.setText("Search");

        invlog_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                invlog_searchKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(invlog_search, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(invlog_search, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("INVENTORY LOG", jPanel16);

        jPanel79.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel79Layout = new javax.swing.GroupLayout(jPanel79);
        jPanel79.setLayout(jPanel79Layout);
        jPanel79Layout.setHorizontalGroup(
            jPanel79Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 873, Short.MAX_VALUE)
        );
        jPanel79Layout.setVerticalGroup(
            jPanel79Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 589, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("AUTHOR", jPanel79);

        jPanel81.setBackground(new java.awt.Color(255, 255, 255));

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane33.setViewportView(jTable3);

        jPanel80.setBackground(new java.awt.Color(255, 255, 255));
        jPanel80.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "LAST UPDATE"));
        jPanel80.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel193.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel193.setText("Course Title:");
        jPanel80.add(jLabel193, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 33, 99, 25));
        jPanel80.add(so_enc1, new org.netbeans.lib.awtextra.AbsoluteConstraints(174, 27, 206, 31));

        jLabel194.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel194.setText("Course Abv.:");
        jPanel80.add(jLabel194, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 70, 99, 25));

        jLabel195.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel195.setText("Years:");
        jPanel80.add(jLabel195, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 144, 99, 25));
        jPanel80.add(so_date1, new org.netbeans.lib.awtextra.AbsoluteConstraints(174, 64, 206, 31));
        jPanel80.add(so_time1, new org.netbeans.lib.awtextra.AbsoluteConstraints(174, 101, 206, 31));

        jLabel196.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel196.setText("Description:");
        jPanel80.add(jLabel196, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 107, 99, 25));

        jLabel197.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel197.setText("Status:");
        jPanel80.add(jLabel197, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 182, 99, 25));
        jPanel80.add(so_time2, new org.netbeans.lib.awtextra.AbsoluteConstraints(174, 138, 206, 31));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--Select Status--", "Active", "Inactive" }));
        jPanel80.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(174, 175, 206, 32));

        so_save1.setBackground(new java.awt.Color(51, 153, 255));
        so_save1.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        so_save1.setForeground(new java.awt.Color(255, 255, 255));
        so_save1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/save.png"))); // NOI18N
        so_save1.setText("Save");
        so_save1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                so_save1ActionPerformed(evt);
            }
        });
        jPanel80.add(so_save1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 260, 180, 44));

        so_save2.setBackground(new java.awt.Color(51, 153, 255));
        so_save2.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        so_save2.setForeground(new java.awt.Color(255, 255, 255));
        so_save2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/update.png"))); // NOI18N
        so_save2.setText("Update");
        so_save2.setEnabled(false);
        so_save2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                so_save2ActionPerformed(evt);
            }
        });
        jPanel80.add(so_save2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 180, 44));

        so_save3.setBackground(new java.awt.Color(51, 153, 255));
        so_save3.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        so_save3.setForeground(new java.awt.Color(255, 255, 255));
        so_save3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/trash.png"))); // NOI18N
        so_save3.setText("Delete");
        so_save3.setEnabled(false);
        so_save3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                so_save3ActionPerformed(evt);
            }
        });
        jPanel80.add(so_save3, new org.netbeans.lib.awtextra.AbsoluteConstraints(201, 310, 180, 44));

        jLabel198.setForeground(new java.awt.Color(255, 255, 255));
        jPanel80.add(jLabel198, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 14, 90, 20));

        so_save4.setBackground(new java.awt.Color(51, 153, 255));
        so_save4.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        so_save4.setForeground(new java.awt.Color(255, 255, 255));
        so_save4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/new.png"))); // NOI18N
        so_save4.setText("New");
        so_save4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                so_save4ActionPerformed(evt);
            }
        });
        jPanel80.add(so_save4, new org.netbeans.lib.awtextra.AbsoluteConstraints(9, 260, 180, 44));

        javax.swing.GroupLayout jPanel81Layout = new javax.swing.GroupLayout(jPanel81);
        jPanel81.setLayout(jPanel81Layout);
        jPanel81Layout.setHorizontalGroup(
            jPanel81Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel81Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel80, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        jPanel81Layout.setVerticalGroup(
            jPanel81Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel81Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel81Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane33, javax.swing.GroupLayout.DEFAULT_SIZE, 578, Short.MAX_VALUE)
                    .addGroup(jPanel81Layout.createSequentialGroup()
                        .addComponent(jPanel80, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        jTabbedPane1.addTab("COURSE", jPanel81);

        javax.swing.GroupLayout inventoryLayout = new javax.swing.GroupLayout(inventory);
        inventory.setLayout(inventoryLayout);
        inventoryLayout.setHorizontalGroup(
            inventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inventoryLayout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 878, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 469, Short.MAX_VALUE))
        );
        inventoryLayout.setVerticalGroup(
            inventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inventoryLayout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );

        stock.add(inventory, "card4");

        user_logo.setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane7.setFont(new java.awt.Font("Arial Black", 1, 11)); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane30.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel55Layout = new javax.swing.GroupLayout(jPanel55);
        jPanel55.setLayout(jPanel55Layout);
        jPanel55Layout.setHorizontalGroup(
            jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane30, javax.swing.GroupLayout.DEFAULT_SIZE, 883, Short.MAX_VALUE)
        );
        jPanel55Layout.setVerticalGroup(
            jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel55Layout.createSequentialGroup()
                .addGap(0, 79, Short.MAX_VALUE)
                .addComponent(jScrollPane30, javax.swing.GroupLayout.PREFERRED_SIZE, 511, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane7.addTab("USER LOG", jPanel55);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane32.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel64Layout = new javax.swing.GroupLayout(jPanel64);
        jPanel64.setLayout(jPanel64Layout);
        jPanel64Layout.setHorizontalGroup(
            jPanel64Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane32, javax.swing.GroupLayout.DEFAULT_SIZE, 883, Short.MAX_VALUE)
        );
        jPanel64Layout.setVerticalGroup(
            jPanel64Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel64Layout.createSequentialGroup()
                .addGap(0, 79, Short.MAX_VALUE)
                .addComponent(jScrollPane32, javax.swing.GroupLayout.PREFERRED_SIZE, 511, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane7.addTab("ACCOUNT LOG", jPanel64);

        javax.swing.GroupLayout user_logoLayout = new javax.swing.GroupLayout(user_logo);
        user_logo.setLayout(user_logoLayout);
        user_logoLayout.setHorizontalGroup(
            user_logoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(user_logoLayout.createSequentialGroup()
                .addComponent(jTabbedPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 888, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 459, Short.MAX_VALUE))
        );
        user_logoLayout.setVerticalGroup(
            user_logoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(user_logoLayout.createSequentialGroup()
                .addComponent(jTabbedPane7)
                .addContainerGap())
        );

        stock.add(user_logo, "card9");

        test.setBackground(new java.awt.Color(204, 204, 204));

        po_test.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane21.setViewportView(po_test);

        jLabel109.setText("ID");

        dsc_id.setText("0");

        jLabel111.setText("Rc");

        rc_po.setText("0");

        jLabel112.setText("tot");

        rc_tot.setText("0");

        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTextField1.setText("jTextField1");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane25.setViewportView(jTextArea1);

        txt_name.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        txt_name.setForeground(new java.awt.Color(255, 255, 255));
        txt_name.setText("Name");
        txt_name.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_nameMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txt_nameMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txt_nameMouseExited(evt);
            }
        });

        txt_level.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        txt_level.setForeground(new java.awt.Color(255, 255, 255));
        txt_level.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txt_level.setText("Level");

        dddd3.setDateFormatString("MM/dd/yyyy");

        dddd4.setDateFormatString("MM/dd/yyyy");

        dddd1.setDateFormatString("MM/dd/yyyy");

        dddd2.setDateFormatString("MM/dd/yyyy");

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));

        jPanel23.setBackground(new java.awt.Color(255, 255, 255));
        jPanel23.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "BOOK STATUS"));

        jLabel41.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel41.setText("ISBN_No:");

        jLabel42.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel42.setText("Book Title:");

        si_isbn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                si_isbnKeyReleased(evt);
            }
        });

        si_bt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                si_btKeyReleased(evt);
            }
        });

        jLabel50.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel50.setText("Classification");

        si_oh.setEditable(false);
        si_oh.setText("0");
        si_oh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                si_ohMouseClicked(evt);
            }
        });

        si_class.setEditable(false);

        jLabel52.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel52.setText("On Hand:");

        jLabel53.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel53.setText("Damage:");

        jLabel54.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel54.setText("Borrowed:");

        jLabel55.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel55.setText("Total Holdings:");

        si_brrd.setEditable(false);
        si_brrd.setText("0");

        si_dmg.setEditable(false);
        si_dmg.setText("0");

        si_th.setEditable(false);
        si_th.setText("0");

        si_add.setBackground(new java.awt.Color(255, 255, 255));
        si_add.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        si_add.setForeground(new java.awt.Color(255, 255, 255));
        si_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/add.png"))); // NOI18N
        si_add.setEnabled(false);
        si_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                si_addActionPerformed(evt);
            }
        });

        si_minus.setBackground(new java.awt.Color(255, 255, 255));
        si_minus.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        si_minus.setForeground(new java.awt.Color(255, 255, 255));
        si_minus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/minus.png"))); // NOI18N
        si_minus.setEnabled(false);
        si_minus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                si_minusActionPerformed(evt);
            }
        });

        si_calc.setBackground(new java.awt.Color(51, 153, 255));
        si_calc.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        si_calc.setForeground(new java.awt.Color(255, 255, 255));
        si_calc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/calculator.png"))); // NOI18N
        si_calc.setText("Calculate");
        si_calc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                si_calcActionPerformed(evt);
            }
        });

        minus_test.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        minus_test.setText("4");

        si_save.setBackground(new java.awt.Color(51, 153, 255));
        si_save.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        si_save.setForeground(new java.awt.Color(255, 255, 255));
        si_save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/save.png"))); // NOI18N
        si_save.setText("Save");
        si_save.setEnabled(false);
        si_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                si_saveActionPerformed(evt);
            }
        });

        si_id.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        si_id.setText("id");

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(44, 44, 44)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(si_isbn)
                            .addComponent(si_bt)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                        .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(73, 73, 73)
                        .addComponent(si_class))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(si_oh, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(si_add, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(si_minus, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(si_brrd))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(si_dmg))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(si_th))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(minus_test, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(si_id, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(si_calc, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(si_save)))
                .addContainerGap())
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(si_isbn, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(si_bt, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(si_class, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(si_oh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(jLabel52, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(si_add, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(si_minus, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(22, 22, 22)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(si_brrd, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(si_dmg, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(si_th, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(si_calc, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(minus_test, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(si_save, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(si_id, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel24.setBackground(new java.awt.Color(255, 255, 255));
        jPanel24.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "BOOK LIST"));

        si_table.setModel(new javax.swing.table.DefaultTableModel(
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
        si_table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        si_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                si_tableMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(si_table);

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(136, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        jPanel25.setBackground(new java.awt.Color(255, 255, 255));
        jPanel25.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "LAST UPDATE"));

        jLabel56.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel56.setText("Encoder:");

        si_enc.setEditable(false);

        jLabel57.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel57.setText("Date:");

        jLabel58.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel58.setText("Time:");

        si_date.setEditable(false);

        si_time.setEditable(false);

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(si_enc))
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(si_time))
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(si_date)))
                .addContainerGap())
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(si_enc, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(si_date, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(si_time, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        idd.setText("jLabel179");

        brr_q.setText("jLabel186");

        brr_rid.setText("jLabel186");

        javax.swing.GroupLayout testLayout = new javax.swing.GroupLayout(test);
        test.setLayout(testLayout);
        testLayout.setHorizontalGroup(
            testLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(testLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(testLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(testLayout.createSequentialGroup()
                        .addComponent(jLabel112, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rc_tot, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(testLayout.createSequentialGroup()
                        .addGroup(testLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(testLayout.createSequentialGroup()
                                .addGroup(testLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(testLayout.createSequentialGroup()
                                        .addComponent(jLabel111, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(rc_po, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(testLayout.createSequentialGroup()
                                        .addComponent(brr_q)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(brr_rid)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 130, Short.MAX_VALUE)
                                        .addGroup(testLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, testLayout.createSequentialGroup()
                                                .addComponent(jLabel109, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(dsc_id, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(18, 18, 18))
                            .addGroup(testLayout.createSequentialGroup()
                                .addGap(116, 116, 116)
                                .addComponent(idd)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(testLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dddd4, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dddd3, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dddd1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dddd2, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(testLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jScrollPane25)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton2))
                        .addContainerGap(828, Short.MAX_VALUE))))
            .addGroup(testLayout.createSequentialGroup()
                .addComponent(txt_name)
                .addGap(6, 6, 6)
                .addComponent(txt_level)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(testLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, testLayout.createSequentialGroup()
                    .addContainerGap(744, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 593, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );
        testLayout.setVerticalGroup(
            testLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(testLayout.createSequentialGroup()
                .addGroup(testLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(testLayout.createSequentialGroup()
                        .addGroup(testLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_name, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_level, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(idd, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGroup(testLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(testLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(testLayout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addGroup(testLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(brr_q)
                                    .addComponent(brr_rid)))))
                    .addGroup(testLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(dddd4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(dddd3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dddd1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(dddd2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(jScrollPane25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(testLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel109)
                    .addComponent(dsc_id))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(testLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel111)
                    .addComponent(rc_po))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(testLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel112)
                    .addComponent(rc_tot))
                .addContainerGap(78, Short.MAX_VALUE))
            .addGroup(testLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, testLayout.createSequentialGroup()
                    .addContainerGap(22, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(21, Short.MAX_VALUE)))
        );

        stock.add(test, "card12");

        jLabel35.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel35.setText("Access:");

        acc_nb.setText("New Book");
        acc_nb.setEnabled(false);
        acc_nb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                acc_nbMouseClicked(evt);
            }
        });

        acc_inv.setText("Inventory");
        acc_inv.setEnabled(false);
        acc_inv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                acc_invMouseClicked(evt);
            }
        });

        acc_brrd.setText("Borrowed");
        acc_brrd.setEnabled(false);
        acc_brrd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                acc_brrdMouseClicked(evt);
            }
        });

        acc_rep.setText("Report");
        acc_rep.setEnabled(false);
        acc_rep.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                acc_repMouseClicked(evt);
            }
        });

        acc_brrr.setText("Borrower");
        acc_brrr.setEnabled(false);
        acc_brrr.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                acc_brrrMouseClicked(evt);
            }
        });

        acc_acc.setText("User Account");
        acc_acc.setEnabled(false);
        acc_acc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                acc_accMouseClicked(evt);
            }
        });

        nb.setText("0");

        brrd.setText("0");

        rep.setText("0");

        inv.setText("0");

        brrr.setText("0");

        ua.setText("0");

        acc_id.setText("jLabel7");

        javax.swing.GroupLayout jPanel69Layout = new javax.swing.GroupLayout(jPanel69);
        jPanel69.setLayout(jPanel69Layout);
        jPanel69Layout.setHorizontalGroup(
            jPanel69Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel69Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel69Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel69Layout.createSequentialGroup()
                        .addGroup(jPanel69Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(acc_nb)
                            .addComponent(acc_brrd)
                            .addComponent(acc_rep)
                            .addComponent(acc_brrr)
                            .addComponent(acc_acc)
                            .addComponent(acc_inv))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel69Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel69Layout.createSequentialGroup()
                                .addComponent(nb)
                                .addGap(44, 44, 44)
                                .addComponent(inv))
                            .addGroup(jPanel69Layout.createSequentialGroup()
                                .addComponent(brrd)
                                .addGap(44, 44, 44)
                                .addComponent(brrr))
                            .addGroup(jPanel69Layout.createSequentialGroup()
                                .addComponent(rep)
                                .addGap(44, 44, 44)
                                .addComponent(ua))
                            .addComponent(acc_id))))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel69Layout.setVerticalGroup(
            jPanel69Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel69Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel69Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel69Layout.createSequentialGroup()
                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(acc_nb)
                        .addGap(7, 7, 7)
                        .addComponent(acc_inv)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(acc_brrd))
                    .addGroup(jPanel69Layout.createSequentialGroup()
                        .addGroup(jPanel69Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nb)
                            .addComponent(inv))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel69Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(brrd)
                            .addComponent(brrr))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel69Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rep)
                            .addComponent(ua))
                        .addGap(18, 18, 18)
                        .addComponent(acc_id, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(7, 7, 7)
                .addComponent(acc_rep)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(acc_brrr)
                .addGap(7, 7, 7)
                .addComponent(acc_acc)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jLabel128.setText("0");

        jLabel120.setText("jLabel120");

        jLabel133.setText("jLabel133");

        sup_id.setText("jLabel7");

        so_id.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        so_id.setText("id");

        sominus_test.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        sominus_test.setText("5");

        frm_tbl.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        frm_tbl.setText("0");

        remain.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        remain.setText("total");

        sandh.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        sandh.setText("S&H");

        brr_id.setText("jLabel7");

        nb_id.setText("jLabel7");

        javax.swing.GroupLayout jPanel70Layout = new javax.swing.GroupLayout(jPanel70);
        jPanel70.setLayout(jPanel70Layout);
        jPanel70Layout.setHorizontalGroup(
            jPanel70Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel70Layout.createSequentialGroup()
                .addGroup(jPanel70Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel70Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel70Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel70Layout.createSequentialGroup()
                                .addGroup(jPanel70Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel70Layout.createSequentialGroup()
                                        .addComponent(jLabel120)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel128, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel133))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sandh, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel70Layout.createSequentialGroup()
                                .addGroup(jPanel70Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(sup_id)
                                    .addGroup(jPanel70Layout.createSequentialGroup()
                                        .addGap(21, 21, 21)
                                        .addComponent(so_id, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(31, 31, 31)
                                .addComponent(remain, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel70Layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addGroup(jPanel70Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel70Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(sominus_test, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel70Layout.createSequentialGroup()
                                .addComponent(brr_id)
                                .addGap(18, 18, 18)
                                .addComponent(frm_tbl, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel70Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel70Layout.createSequentialGroup()
                    .addGap(109, 109, 109)
                    .addComponent(nb_id)
                    .addContainerGap(109, Short.MAX_VALUE)))
        );
        jPanel70Layout.setVerticalGroup(
            jPanel70Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel70Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel70Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel70Layout.createSequentialGroup()
                        .addGroup(jPanel70Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel120)
                            .addComponent(jLabel128))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel133))
                    .addComponent(sandh, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel70Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sup_id)
                    .addComponent(remain, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(so_id, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel70Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(brr_id, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(frm_tbl, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sominus_test, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
            .addGroup(jPanel70Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel70Layout.createSequentialGroup()
                    .addGap(94, 94, 94)
                    .addComponent(nb_id)
                    .addContainerGap(95, Short.MAX_VALUE)))
        );

        jLabel130.setText("jLabel130");

        brrd_bqty.setText("qty");

        brrd_total.setText("qty");

        jLabel151.setText("jLabel151");

        jLabel152.setText("jLabel152");

        brrd_id.setText("jLabel78");

        date1.setText("jLabel78");

        test3.setText("hldng");

        test6.setText("hldng");

        test_tot1.setText("tot");

        jLabel129.setText("0");

        test_tot.setText("tot");

        test1.setText("onhand");

        jLabel70.setText("tot");

        test2.setText("brrd");

        test5.setText("brrd");

        test4.setText("onhand");

        javax.swing.GroupLayout jPanel71Layout = new javax.swing.GroupLayout(jPanel71);
        jPanel71.setLayout(jPanel71Layout);
        jPanel71Layout.setHorizontalGroup(
            jPanel71Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel71Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel71Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel151)
                    .addComponent(jLabel152))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                .addComponent(brrd_bqty, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(brrd_total, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
            .addGroup(jPanel71Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel71Layout.createSequentialGroup()
                    .addGap(80, 80, Short.MAX_VALUE)
                    .addComponent(jLabel130)
                    .addGap(0, 0, Short.MAX_VALUE)))
            .addGroup(jPanel71Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel71Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel71Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel71Layout.createSequentialGroup()
                            .addComponent(test4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(test1))
                        .addGroup(jPanel71Layout.createSequentialGroup()
                            .addComponent(test5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(test2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel70)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(test_tot)))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel71Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel71Layout.createSequentialGroup()
                            .addComponent(test_tot1)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel129, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(brrd_id))
                        .addGroup(jPanel71Layout.createSequentialGroup()
                            .addComponent(test6)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(test3)
                            .addGap(18, 18, 18)
                            .addComponent(date1)))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel71Layout.setVerticalGroup(
            jPanel71Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel71Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel71Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel151)
                    .addGroup(jPanel71Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(jPanel71Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel152)
                            .addComponent(brrd_bqty, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(brrd_total, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel71Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel71Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel130)
                    .addGap(0, 189, Short.MAX_VALUE)))
            .addGroup(jPanel71Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel71Layout.createSequentialGroup()
                    .addGap(84, 84, 84)
                    .addGroup(jPanel71Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(test1)
                        .addComponent(test3)
                        .addComponent(test4)
                        .addComponent(test6)
                        .addComponent(date1))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel71Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(test2)
                        .addComponent(test5)
                        .addComponent(jLabel70)
                        .addComponent(test_tot)
                        .addComponent(test_tot1)
                        .addComponent(jLabel129)
                        .addComponent(brrd_id))
                    .addContainerGap(85, Short.MAX_VALUE)))
        );

        jLabel124.setText("jLabel124");

        jLabel136.setText("jLabel136");

        jLabel3.setText("jLabel3");

        jLabel137.setText("jLabel137");

        jLabel134.setText("jLabel134");

        jLabel127.setText("jLabel127");

        jLabel135.setText("jLabel135");

        jLabel142.setText("id");

        jLabel143.setText("bt");

        jLabel144.setText("aut");

        jLabel145.setText("class");

        jLabel146.setText("qty");

        jLabel147.setText("prc");

        jLabel148.setText("po");

        jLabel149.setText("date");

        jLabel150.setText("sta");

        jLabel126.setText("jLabel124");

        jLabel141.setText("jLabel141");

        jLabel140.setText("jLabel124");

        javax.swing.GroupLayout jPanel72Layout = new javax.swing.GroupLayout(jPanel72);
        jPanel72.setLayout(jPanel72Layout);
        jPanel72Layout.setHorizontalGroup(
            jPanel72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 464, Short.MAX_VALUE)
            .addGroup(jPanel72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel72Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel72Layout.createSequentialGroup()
                                .addComponent(jLabel136)
                                .addGap(62, 62, 62)
                                .addComponent(jLabel137))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel72Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(62, 62, 62)))
                        .addGroup(jPanel72Layout.createSequentialGroup()
                            .addComponent(jLabel124)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel127)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel134)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel135)))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(jPanel72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel72Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel72Layout.createSequentialGroup()
                            .addGap(127, 127, 127)
                            .addComponent(jLabel126))
                        .addGroup(jPanel72Layout.createSequentialGroup()
                            .addGroup(jPanel72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel141)
                                .addComponent(jLabel142, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel143, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel144, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel145, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel146, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel147, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel148, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel149, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel150, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(jPanel72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel72Layout.createSequentialGroup()
                    .addGap(209, 209, 209)
                    .addComponent(jLabel140)
                    .addContainerGap(209, Short.MAX_VALUE)))
        );
        jPanel72Layout.setVerticalGroup(
            jPanel72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 273, Short.MAX_VALUE)
            .addGroup(jPanel72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel72Layout.createSequentialGroup()
                    .addGap(103, 103, 103)
                    .addComponent(jLabel3)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel136)
                        .addComponent(jLabel137))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(jPanel72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel124)
                        .addComponent(jLabel127)
                        .addComponent(jLabel134)
                        .addComponent(jLabel135))
                    .addContainerGap(104, Short.MAX_VALUE)))
            .addGroup(jPanel72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel72Layout.createSequentialGroup()
                    .addGap(102, 102, 102)
                    .addGroup(jPanel72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel142)
                        .addComponent(jLabel143)
                        .addComponent(jLabel144)
                        .addComponent(jLabel145)
                        .addComponent(jLabel146)
                        .addComponent(jLabel147)
                        .addComponent(jLabel148)
                        .addComponent(jLabel149)
                        .addComponent(jLabel150))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel141)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel126)
                    .addGap(102, 102, 102)))
            .addGroup(jPanel72Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel72Layout.createSequentialGroup()
                    .addGap(129, 129, 129)
                    .addComponent(jLabel140)
                    .addContainerGap(130, Short.MAX_VALUE)))
        );

        jLabel121.setText("jLabel121");

        jLabel122.setText("jLabel122");

        ttt.setText("0.00");

        code.setText("jLabel122");

        jLabel115.setText("0");

        jLabel76.setText("jLabel76");

        javax.swing.GroupLayout account_wasteLayout = new javax.swing.GroupLayout(account_waste);
        account_waste.setLayout(account_wasteLayout);
        account_wasteLayout.setHorizontalGroup(
            account_wasteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(account_wasteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(account_wasteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel72, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(account_wasteLayout.createSequentialGroup()
                        .addComponent(jPanel69, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel70, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel71, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(account_wasteLayout.createSequentialGroup()
                        .addGroup(account_wasteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel121)
                            .addComponent(jLabel122))
                        .addGap(113, 113, 113)
                        .addGroup(account_wasteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(account_wasteLayout.createSequentialGroup()
                                .addComponent(jLabel76)
                                .addGap(10, 10, 10)
                                .addComponent(jLabel115, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(account_wasteLayout.createSequentialGroup()
                                .addComponent(ttt, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(code)))))
                .addContainerGap(684, Short.MAX_VALUE))
        );
        account_wasteLayout.setVerticalGroup(
            account_wasteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(account_wasteLayout.createSequentialGroup()
                .addGroup(account_wasteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(account_wasteLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(account_wasteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel69, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel71, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jPanel70, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel72, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(account_wasteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel121)
                    .addComponent(ttt)
                    .addComponent(code))
                .addGroup(account_wasteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(account_wasteLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel122))
                    .addGroup(account_wasteLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(account_wasteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel76)
                            .addComponent(jLabel115))))
                .addContainerGap(62, Short.MAX_VALUE))
        );

        stock.add(account_waste, "card13");

        booklist.setBackground(new java.awt.Color(255, 255, 255));

        jPanel51.setBackground(new java.awt.Color(255, 255, 255));
        jPanel51.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "BOOK INFO"));

        pobl_table.setModel(new javax.swing.table.DefaultTableModel(
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
        pobl_table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
        pobl_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pobl_tableMouseClicked(evt);
            }
        });
        jScrollPane20.setViewportView(pobl_table);

        javax.swing.GroupLayout jPanel51Layout = new javax.swing.GroupLayout(jPanel51);
        jPanel51.setLayout(jPanel51Layout);
        jPanel51Layout.setHorizontalGroup(
            jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1317, Short.MAX_VALUE)
        );
        jPanel51Layout.setVerticalGroup(
            jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane20, javax.swing.GroupLayout.DEFAULT_SIZE, 517, Short.MAX_VALUE)
        );

        nb_update3.setBackground(new java.awt.Color(51, 153, 255));
        nb_update3.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        nb_update3.setForeground(new java.awt.Color(255, 255, 255));
        nb_update3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/click.png"))); // NOI18N
        nb_update3.setText("Choose");
        nb_update3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nb_update3ActionPerformed(evt);
            }
        });

        po_or1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                po_or1KeyReleased(evt);
            }
        });

        jLabel108.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel108.setText("Search Book:");

        javax.swing.GroupLayout booklistLayout = new javax.swing.GroupLayout(booklist);
        booklist.setLayout(booklistLayout);
        booklistLayout.setHorizontalGroup(
            booklistLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(booklistLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(booklistLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel51, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(booklistLayout.createSequentialGroup()
                        .addComponent(nb_update3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel108, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(po_or1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        booklistLayout.setVerticalGroup(
            booklistLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, booklistLayout.createSequentialGroup()
                .addGap(0, 53, Short.MAX_VALUE)
                .addGroup(booklistLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nb_update3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, booklistLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(po_or1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel108, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel51, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        stock.add(booklist, "card11");

        classification.setBackground(new java.awt.Color(255, 255, 255));

        jPanel45.setBackground(new java.awt.Color(255, 255, 255));
        jPanel45.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "CLASSIFICATION LIST"));

        class_table.setModel(new javax.swing.table.DefaultTableModel(
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
        class_table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);
        class_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                class_tableMouseClicked(evt);
            }
        });
        jScrollPane17.setViewportView(class_table);

        javax.swing.GroupLayout jPanel45Layout = new javax.swing.GroupLayout(jPanel45);
        jPanel45.setLayout(jPanel45Layout);
        jPanel45Layout.setHorizontalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane17, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel45Layout.setVerticalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane17, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );

        jPanel46.setBackground(new java.awt.Color(255, 255, 255));
        jPanel46.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "CLASSIFICATION DETAILS"));

        jLabel85.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel85.setText("Classification Name:");

        si_isbn1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                si_isbn1KeyReleased(evt);
            }
        });

        jLabel87.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel87.setText("Classification Number:");

        class_num.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        class_save_hide.setBackground(new java.awt.Color(255, 255, 255));

        si_save1.setBackground(new java.awt.Color(51, 153, 255));
        si_save1.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        si_save1.setForeground(new java.awt.Color(255, 255, 255));
        si_save1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/save.png"))); // NOI18N
        si_save1.setText("Save");
        si_save1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                si_save1ActionPerformed(evt);
            }
        });

        btn_del.setBackground(new java.awt.Color(51, 153, 255));
        btn_del.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        btn_del.setForeground(new java.awt.Color(255, 255, 255));
        btn_del.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/trash.png"))); // NOI18N
        btn_del.setText("Delete");
        btn_del.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_delActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout class_save_hideLayout = new javax.swing.GroupLayout(class_save_hide);
        class_save_hide.setLayout(class_save_hideLayout);
        class_save_hideLayout.setHorizontalGroup(
            class_save_hideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, class_save_hideLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_del)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(si_save1)
                .addContainerGap())
        );
        class_save_hideLayout.setVerticalGroup(
            class_save_hideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(class_save_hideLayout.createSequentialGroup()
                .addGroup(class_save_hideLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(si_save1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_del, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel46Layout = new javax.swing.GroupLayout(jPanel46);
        jPanel46.setLayout(jPanel46Layout);
        jPanel46Layout.setHorizontalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel46Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel46Layout.createSequentialGroup()
                        .addComponent(jLabel87, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(class_num, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel46Layout.createSequentialGroup()
                        .addComponent(jLabel85, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(si_isbn1, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel46Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(class_save_hide, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel46Layout.setVerticalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel46Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel87, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(class_num, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(si_isbn1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel85, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 97, Short.MAX_VALUE)
                .addComponent(class_save_hide, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        si_save2.setBackground(new java.awt.Color(51, 153, 255));
        si_save2.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        si_save2.setForeground(new java.awt.Color(255, 255, 255));
        si_save2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/back.png"))); // NOI18N
        si_save2.setText("Back");
        si_save2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                si_save2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout classificationLayout = new javax.swing.GroupLayout(classification);
        classification.setLayout(classificationLayout);
        classificationLayout.setHorizontalGroup(
            classificationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, classificationLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(classificationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(si_save2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 465, Short.MAX_VALUE)
                .addComponent(jPanel45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        classificationLayout.setVerticalGroup(
            classificationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(classificationLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(classificationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(classificationLayout.createSequentialGroup()
                        .addComponent(si_save2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        stock.add(classification, "card10");

        useraccount.setBackground(new java.awt.Color(255, 255, 255));

        men_brrr.setBackground(new java.awt.Color(255, 204, 204));
        men_brrr.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        men_brrr.setForeground(new java.awt.Color(255, 255, 255));
        men_brrr.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        men_brrr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/borrower.png"))); // NOI18N
        men_brrr.setText("BORROWER");
        men_brrr.setOpaque(true);
        men_brrr.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                men_brrrMouseClicked(evt);
            }
        });

        men_ua.setBackground(new java.awt.Color(255, 204, 204));
        men_ua.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        men_ua.setForeground(new java.awt.Color(255, 255, 255));
        men_ua.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        men_ua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/user.png"))); // NOI18N
        men_ua.setText("USER ACCOUNT");
        men_ua.setOpaque(true);
        men_ua.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                men_uaMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout useraccountLayout = new javax.swing.GroupLayout(useraccount);
        useraccount.setLayout(useraccountLayout);
        useraccountLayout.setHorizontalGroup(
            useraccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, useraccountLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(useraccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(men_brrr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(men_ua, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE))
                .addGap(1120, 1120, 1120))
        );
        useraccountLayout.setVerticalGroup(
            useraccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(useraccountLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(men_ua, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(men_brrr, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(538, Short.MAX_VALUE))
        );

        stock.add(useraccount, "card7");

        borrower.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout borrowerLayout = new javax.swing.GroupLayout(borrower);
        borrower.setLayout(borrowerLayout);
        borrowerLayout.setHorizontalGroup(
            borrowerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1347, Short.MAX_VALUE)
        );
        borrowerLayout.setVerticalGroup(
            borrowerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 632, Short.MAX_VALUE)
        );

        stock.add(borrower, "card6");

        newbook.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout newbookLayout = new javax.swing.GroupLayout(newbook);
        newbook.setLayout(newbookLayout);
        newbookLayout.setHorizontalGroup(
            newbookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1347, Short.MAX_VALUE)
        );
        newbookLayout.setVerticalGroup(
            newbookLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 632, Short.MAX_VALUE)
        );

        stock.add(newbook, "card3");

        javax.swing.GroupLayout MenuLayout = new javax.swing.GroupLayout(Menu);
        Menu.setLayout(MenuLayout);
        MenuLayout.setHorizontalGroup(
            MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuLayout.createSequentialGroup()
                .addComponent(mmm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(6, 6, 6)
                .addGroup(MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(head, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(stock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        MenuLayout.setVerticalGroup(
            MenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuLayout.createSequentialGroup()
                .addComponent(head, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(stock, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
            .addComponent(mmm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.add(Menu, "card3");

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1150, 700));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/loginbkg.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 30, 600, 620));

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/bkg.png"))); // NOI18N
        getContentPane().add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1145, 700));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void men_logoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_men_logoutMouseClicked
        men_dash.setBackground(new Color(96,96,96));
        men_rec.setBackground(new Color(96,96,96));
        men_inv.setBackground(new Color(96,96,96));
        men_brrd.setBackground(new Color(96,96,96));
        men_brrr.setBackground(new Color(96,96,96));
        men_ua.setBackground(new Color(96,96,96));
        men_logout.setBackground(new Color(255,255,255));
        menu_title.setText("LOGOUT");
        men_po.setForeground(new Color(255,255,255));
        men_po.setBackground(new Color(96,96,96));
        men_dash.setForeground(new Color(255,255,255));
        men_rec.setForeground(new Color(255,255,255));
        men_inv.setForeground(new Color(255,255,255));
        men_brrd.setForeground(new Color(255,255,255));
        men_brrr.setForeground(new Color(255,255,255));
        men_ua.setForeground(new Color(255,255,255));
        men_logout.setForeground(new Color(96,96,96));
        
        dashboard.setVisible(true);
        Records.setVisible(false);
        inventory.setVisible(false);
        Bookloan.setVisible(false);
        borrower.setVisible(false);
        useraccount.setVisible(false);
        user_logo.setVisible(false);
        classification.setVisible(false);
        booklist.setVisible(false);

        menu_title.setText("DASHBOARD");
        if(menu_title.getText().equals("DASHBOARD")){
        jPanel1.setVisible(true);
        Login.setVisible(true);
            Menu.setVisible(false);
            mmm.setVisible(false);
            head.setVisible(false);
            stock.setVisible(false);
            men_logout.setForeground(new Color(96,96,96));
            men_logout.setBackground(new Color(255,255,255));
            menu_title.setText("LOGOUT");
        }
        
    }//GEN-LAST:event_men_logoutMouseClicked

    private void minimizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minimizeMouseClicked
        this.setState(this.ICONIFIED);
    }//GEN-LAST:event_minimizeMouseClicked

    private void exitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitMouseClicked
        System.exit(0);
    }//GEN-LAST:event_exitMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        login();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void userKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_userKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            login();
        }
    }//GEN-LAST:event_userKeyPressed

    private void passKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            login();
        }
    }//GEN-LAST:event_passKeyPressed

    private void jButton1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton1KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            login();
        }
    }//GEN-LAST:event_jButton1KeyPressed

    private void showpassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showpassMouseClicked
        if (showpass.isSelected()) {
      pass.setEchoChar((char)0); //password = JPasswordField
   } else {
      pass.setEchoChar('*');
   }
    }//GEN-LAST:event_showpassMouseClicked

    private void men_dashMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_men_dashMouseClicked
        men_dash.setBackground(new Color(255,255,255));
        men_rec.setBackground(new Color(96,96,96));
        men_inv.setBackground(new Color(96,96,96));
        men_brrd.setBackground(new Color(96,96,96));
        men_brrr.setBackground(new Color(96,96,96));
        men_ua.setBackground(new Color(96,96,96));
        men_logout.setBackground(new Color(96,96,96));
        men_po.setBackground(new Color(96,96,96));
        menu_title.setText("MY ACCOUNT");
        men_po.setForeground(new Color(255,255,255));
        men_rep.setBackground(new Color(96,96,96));
        men_rep.setForeground(new Color(255,255,255));
        men_dash.setForeground(new Color(96,96,96));
        men_rec.setForeground(new Color(255,255,255));
        men_inv.setForeground(new Color(255,255,255));
        men_brrd.setForeground(new Color(255,255,255));
        men_brrr.setForeground(new Color(255,255,255));
        men_ua.setForeground(new Color(255,255,255));
        men_logout.setForeground(new Color(255,255,255));
        
        dashboard.setVisible(true);
        Records.setVisible(false);
        inventory.setVisible(false);
        Bookloan.setVisible(false);
        borrower.setVisible(false);
        useraccount.setVisible(false);
        report.setVisible(false);
        user_logo.setVisible(false);
        classification.setVisible(false);
        booklist.setVisible(false);
        
    }//GEN-LAST:event_men_dashMouseClicked

    private void men_recMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_men_recMouseClicked
        jTabbedPane6.setEnabledAt(0, true);
        jTabbedPane6.setEnabledAt(1, true);
        jTabbedPane6.setEnabledAt(2, true);
        jTabbedPane6.setEnabledAt(3, true);
        jTabbedPane6.setEnabledAt(4, true);
        jTabbedPane6.setEnabledAt(5, true);
        jTabbedPane6.setSelectedIndex(0);
        
        men_dash.setBackground(new Color(96,96,96));
        men_rec.setBackground(new Color(255,255,255));
        men_inv.setBackground(new Color(96,96,96));
        men_brrd.setBackground(new Color(96,96,96));
        men_brrr.setBackground(new Color(96,96,96));
        men_ua.setBackground(new Color(96,96,96));
        men_logout.setBackground(new Color(96,96,96));
        menu_title.setText("RECORDS");
        men_po.setForeground(new Color(255,255,255));
        men_po.setBackground(new Color(96,96,96));
        men_rep.setBackground(new Color(96,96,96));
        men_rep.setForeground(new Color(255,255,255));
        men_dash.setForeground(new Color(255,255,255));
        men_rec.setForeground(new Color(96,96,96));
        men_inv.setForeground(new Color(255,255,255));
        men_brrd.setForeground(new Color(255,255,255));
        men_brrr.setForeground(new Color(255,255,255));
        men_ua.setForeground(new Color(255,255,255));
        men_logout.setForeground(new Color(255,255,255));
        
        dashboard.setVisible(false);
        Records.setVisible(true);
        inventory.setVisible(false);
        Bookloan.setVisible(false);
        borrower.setVisible(false);
        useraccount.setVisible(false);
        report.setVisible(false);
        user_logo.setVisible(false);
        classification.setVisible(false);
        booklist.setVisible(false);
        si_save3.setVisible(false);
        sup_save3.setVisible(false);
        
        nb_id.setText("");
          nb_bt.setText("");
          nb_aut.setText("");
          nb_class.setSelectedItem("--Choose Book Classification--");
          nb_qty.setText("");
          nb_prc.setText("");
    }//GEN-LAST:event_men_recMouseClicked

    private void men_invMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_men_invMouseClicked
        sup_save3.setVisible(false);
        si_save3.setText("Back");
        si_save3.setVisible(false);
        jLabel133.setText("0");
        
//        jTabbedPane1.setEnabledAt(0, true);
//        jTabbedPane1.setEnabledAt(1, true);
//        jTabbedPane1.setEnabledAt(2, true);
//        jTabbedPane1.setEnabledAt(3, true);
//        jTabbedPane1.setEnabledAt(4, true);
//        jTabbedPane1.setEnabledAt(5, true);
//        jTabbedPane1.setEnabledAt(6, true);
//        jTabbedPane1.setSelectedIndex(0);
        
        men_dash.setBackground(new Color(96,96,96));
        men_rec.setBackground(new Color(96,96,96));
        men_inv.setBackground(new Color(255,255,255));
        men_brrd.setBackground(new Color(96,96,96));
        men_brrr.setBackground(new Color(96,96,96));
        men_ua.setBackground(new Color(96,96,96));
        men_logout.setBackground(new Color(96,96,96));
        menu_title.setText("INVENTORY");
        men_po.setForeground(new Color(255,255,255));
        men_po.setBackground(new Color(96,96,96));
        men_rep.setBackground(new Color(96,96,96));
        men_rep.setForeground(new Color(255,255,255));
        men_dash.setForeground(new Color(255,255,255));
        men_rec.setForeground(new Color(255,255,255));
        men_inv.setForeground(new Color(96,96,96));
        men_brrd.setForeground(new Color(255,255,255));
        men_brrr.setForeground(new Color(255,255,255));
        men_ua.setForeground(new Color(255,255,255));
        men_logout.setForeground(new Color(255,255,255));
        
        dashboard.setVisible(false);
        Records.setVisible(false);
        inventory.setVisible(true);
        Bookloan.setVisible(false);
        borrower.setVisible(false);
        useraccount.setVisible(false);
        report.setVisible(false);
        user_logo.setVisible(false);
        classification.setVisible(false);
        booklist.setVisible(false);
    }//GEN-LAST:event_men_invMouseClicked

    private void men_brrdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_men_brrdMouseClicked
        men_dash.setBackground(new Color(96,96,96));
        men_rec.setBackground(new Color(96,96,96));
        men_inv.setBackground(new Color(96,96,96));
        men_brrd.setBackground(new Color(255,255,255));
        men_brrr.setBackground(new Color(96,96,96));
        men_ua.setBackground(new Color(96,96,96));
        men_logout.setBackground(new Color(96,96,96));
        menu_title.setText("BORROWED");
        men_po.setForeground(new Color(255,255,255));
        men_po.setBackground(new Color(96,96,96));
        men_rep.setBackground(new Color(96,96,96));
        men_rep.setForeground(new Color(255,255,255));
        men_dash.setForeground(new Color(255,255,255));
        men_rec.setForeground(new Color(255,255,255));
        men_inv.setForeground(new Color(255,255,255));
        men_brrd.setForeground(new Color(96,96,96));
        men_brrr.setForeground(new Color(255,255,255));
        men_ua.setForeground(new Color(255,255,255));
        men_logout.setForeground(new Color(255,255,255));
        
        dashboard.setVisible(false);
        Records.setVisible(false);
        inventory.setVisible(false);
        Bookloan.setVisible(true);
        borrower.setVisible(false);
        useraccount.setVisible(false);
        report.setVisible(false);
        user_logo.setVisible(false);
        classification.setVisible(false);
        booklist.setVisible(false);
    }//GEN-LAST:event_men_brrdMouseClicked

    private void men_brrrMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_men_brrrMouseClicked
        men_dash.setBackground(new Color(96,96,96));
        men_rec.setBackground(new Color(96,96,96));
        men_inv.setBackground(new Color(96,96,96));
        men_brrd.setBackground(new Color(96,96,96));
        men_brrr.setBackground(new Color(255,255,255));
        men_ua.setBackground(new Color(96,96,96));
        men_logout.setBackground(new Color(96,96,96));
        menu_title.setText("BORROWER");
        men_po.setForeground(new Color(255,255,255));
        men_po.setBackground(new Color(96,96,96));
        men_rep.setBackground(new Color(96,96,96));
        men_rep.setForeground(new Color(255,255,255));
        men_dash.setForeground(new Color(255,255,255));
        men_rec.setForeground(new Color(255,255,255));
        men_inv.setForeground(new Color(255,255,255));
        men_brrd.setForeground(new Color(255,255,255));
        men_brrr.setForeground(new Color(96,96,96));
        men_ua.setForeground(new Color(255,255,255));
        men_logout.setForeground(new Color(255,255,255));
        
        dashboard.setVisible(false);
        newbook.setVisible(false);
        inventory.setVisible(false);
        Bookloan.setVisible(false);
        borrower.setVisible(true);
        useraccount.setVisible(false);
        report.setVisible(false);
        user_logo.setVisible(false);
        classification.setVisible(false);
        booklist.setVisible(false);
    }//GEN-LAST:event_men_brrrMouseClicked

    private void men_uaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_men_uaMouseClicked
        men_dash.setBackground(new Color(96,96,96));
        men_rec.setBackground(new Color(96,96,96));
        men_inv.setBackground(new Color(96,96,96));
        men_brrd.setBackground(new Color(96,96,96));
        men_brrr.setBackground(new Color(96,96,96));
        men_ua.setBackground(new Color(255,255,255));
        men_logout.setBackground(new Color(96,96,96));
        menu_title.setText("USER ACCOUNT");
        men_po.setForeground(new Color(255,255,255));
        men_po.setBackground(new Color(96,96,96));
        men_rep.setBackground(new Color(96,96,96));
        men_rep.setForeground(new Color(255,255,255));
        men_dash.setForeground(new Color(255,255,255));
        men_rec.setForeground(new Color(255,255,255));
        men_inv.setForeground(new Color(255,255,255));
        men_brrd.setForeground(new Color(255,255,255));
        men_brrr.setForeground(new Color(255,255,255));
        men_ua.setForeground(new Color(96,96,96));
        men_logout.setForeground(new Color(255,255,255));
        
        dashboard.setVisible(false);
        newbook.setVisible(false);
        inventory.setVisible(false);
        Bookloan.setVisible(false);
        borrower.setVisible(false);
        useraccount.setVisible(true);
        report.setVisible(false);
        user_logo.setVisible(false);
        classification.setVisible(false);
        booklist.setVisible(false);
    }//GEN-LAST:event_men_uaMouseClicked

    private void nb_newActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nb_newActionPerformed
        nb_new.setEnabled(false);
        nb_update.setEnabled(false);
        nb_delete.setEnabled(false);
        nb_save.setEnabled(true);
        
        class_add.setEnabled(true);
        nb_is.setEnabled(true);
        nb_bt.setEnabled(true);
        nb_bt.setEditable(true);
        nb_aut.setEnabled(true);
        nb_qty.setEnabled(true);
        nb_qty.setEditable(true);
        nb_cn.setEnabled(true);
        nb_class.setEnabled(true);
        nb_cpub.setEnabled(true);
        class_add2.setEnabled(true);
        nb_edi.setEnabled(true);
        nb_cry.setEnabled(true);
        nb_prc.setEnabled(true);
        nb_prc.setEditable(true);
        nb_da.setEnabled(true);
        nb_clr();
        nb_aut.setEnabled(true);
        nb_aut.setEditable(true);
        
    }//GEN-LAST:event_nb_newActionPerformed

    private void nb_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nb_saveActionPerformed
        if(jLabel187.getText().equals("ISBN Not Available") || jLabel187.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Enter Valid ISBN Number!");
        }
        else{
            if(jLabel189.getText().equals("Call Number Not Available") || jLabel189.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Enter Valid Call Number!");
            }
            else {
                if(nb_bt.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Input Book Title!");
                }
                else {
                    int i = Integer.parseInt(nb_qty.getText());
                    if(i<=0){
                    JOptionPane.showMessageDialog(null, "Quantity must not lessthan 1!");
                    }
                    else {
                        if(nb_prc.getText().equals("")){
                        JOptionPane.showMessageDialog(null, "Input Book Price!");
                        }
                        else {
        if(nb_da.getDate().equals(null)){    
        JOptionPane.showMessageDialog(null, "Please Fill the book information!!");
        }
        else{
           try {
            String sql = "Insert into stockin_tbl (ISBN_No, Book_title, Author, Quantity, Call_Number, "
                    + " Classification, Publisher, Edition,"
                    + " Copy_Right_Year, Price, Encoder, Date_Arrival) values (?,?,?,?,?,?,?,?,?,?,?,?)";

            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement(sql);
            
            pst.setString(1, nb_is.getText());
            pst.setString(2, nb_bt.getText());
            pst.setString(3, nb_aut.getText());
            pst.setString(4, nb_qty.getText());
            pst.setString(5, nb_cn.getText());
            pst.setString(6, (String) nb_class.getSelectedItem());
            pst.setString(7, (String) nb_cpub.getSelectedItem());
            pst.setString(8, nb_edi.getText());
            pst.setString(9, ((JTextField)nb_cry.getDateEditor().getUiComponent()).getText());
            pst.setString(10, nb_prc.getText());
            pst.setString(11, txt_name.getText());
            pst.setString(12, ((JTextField)nb_da.getDateEditor().getUiComponent()).getText());

            pst.execute(); 
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        try {
            String sql11 = "Insert into holding_tbl (ISBN_No, Book_title, Classification, On_Hand, Borrowed, Damage, Total_Holding, Encoder, Date, Time) "
                    + "values (?,?,?,?,?,?,?,?,?,?)";

            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement(sql11);
            
            pst.setString(1, nb_is.getText());
            pst.setString(2, nb_bt.getText());
            pst.setString(3, (String) nb_class.getSelectedItem());
            pst.setString(4, nb_qty.getText());
            pst.setString(5, "0");
            pst.setString(6, "0");
            pst.setString(7, nb_qty.getText());
            pst.setString(8, "");
            pst.setString(9, "");
            pst.setString(10, "");

            pst.execute(); 
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }    
        try {
            String sql1 = "Insert into damage_tbl (ISBN_No, Book_title, Classification, Status, Quantity, Encoder, Date, Time) "
                    + "values (?,?,?,?,?,?,?,?)";

            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement(sql1);
            
            pst.setString(1, nb_is.getText());
            pst.setString(2, nb_bt.getText());
            pst.setString(3, (String) nb_class.getSelectedItem());
            pst.setString(4, "--Select Status--");
            pst.setString(5, "0");
            pst.setString(6, "");
            pst.setString(7, "");
            pst.setString(8, "");

            pst.execute(); 
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }   
        
        try {

            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("DELETE FROM purchased_tbl  WHERE ID = '" + nb_id.getText() + "'");
            int del = pst.executeUpdate();
            if (del > 0) {
                JOptionPane.showMessageDialog(null, "Book Entry Deleted!");
                
            }else{
                //JOptionPane.showMessageDialog(null, "Please Check Item!!");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
       
       
        all_ref();
        nb_clr();
        nb_disable();
        nb_save.setEnabled(false);
        nb_new.setEnabled(true);
        }
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_nb_saveActionPerformed

    private void nb_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nb_tableMouseClicked
        int z = nb_table.getSelectedRow();

            TableModel model = (TableModel)nb_table.getModel();
          nb_id.setText(model.getValueAt(z, 0).toString());
          nb_is.setText(model.getValueAt(z, 1).toString());
          nb_bt.setText(model.getValueAt(z, 2).toString());
          nb_aut.setText(model.getValueAt(z, 3).toString());
          nb_qty.setText(model.getValueAt(z, 4).toString());
          nb_cn.setText(model.getValueAt(z, 5).toString());
          nb_class.setSelectedItem(model.getValueAt(z, 6).toString());
          nb_cpub.setSelectedItem(model.getValueAt(z, 7).toString());
          nb_edi.setText(model.getValueAt(z, 8).toString());
          ((JTextField)nb_cry.getDateEditor().getUiComponent()).setText(model.getValueAt(z, 9).toString());
          nb_prc.setText(model.getValueAt(z, 10).toString());
          ((JTextField)nb_da.getDateEditor().getUiComponent()).setText(model.getValueAt(z, 11).toString());
          
          nb_able();
          //nb_qty.setEnabled(false);
          nb_save.setEnabled(false);
          nb_update.setEnabled(true);
          nb_delete.setEnabled(true);
          nb_new.setEnabled(true);
    }//GEN-LAST:event_nb_tableMouseClicked

    private void nb_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nb_updateActionPerformed
        try {
            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("UPDATE stockin_tbl SET ISBN_No=?, "
                    + "Call_Number=?, Classification=?, Publisher=?, Edition=?, Copy_Right_Year=?, Date_Arrival=?, "
                    + "Author=? WHERE Book_ID='" + nb_id.getText() + "'");
            
            pst.setString(1, nb_is.getText());
            pst.setString(2, nb_cn.getText());
            pst.setString(3, (String) nb_class.getSelectedItem());
            pst.setString(4, (String) nb_cpub.getSelectedItem());
            pst.setString(5, nb_edi.getText());
            pst.setString(6, ((JTextField)nb_cry.getDateEditor().getUiComponent()).getText());
            pst.setString(7, ((JTextField)nb_da.getDateEditor().getUiComponent()).getText());
            pst.setString(8, nb_aut.getText());
            
            pst.execute();
            JOptionPane.showMessageDialog(null, "Data Updated");
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        
        all_ref();
        nb_clr();
        nb_disable();
        nb_update.setEnabled(false);
        nb_delete.setEnabled(false);
        nb_new.setEnabled(true);
    }//GEN-LAST:event_nb_updateActionPerformed

    private void nb_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nb_deleteActionPerformed
        try {

            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("DELETE FROM stockin_tbl  WHERE Book_ID = '" + nb_id.getText() + "'");
            int del = pst.executeUpdate();
            if (del > 0) {
                JOptionPane.showMessageDialog(null, "Book Entry Deleted!");
                
            }else{
                JOptionPane.showMessageDialog(null, "Please Check Item!!");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        try {

            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("DELETE FROM holding_tbl  WHERE ID = '" + nb_id.getText() + "'");
            int del = pst.executeUpdate();
            if (del > 0) {
                JOptionPane.showMessageDialog(null, "Book Entry Deleted!");
                
            }else{
                JOptionPane.showMessageDialog(null, "Please Check Item!!");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        try {

            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("DELETE FROM damage_tbl  WHERE ID = '" + nb_id.getText() + "'");
            int del = pst.executeUpdate();
            if (del > 0) {
                JOptionPane.showMessageDialog(null, "Book Entry Deleted!");
                
            }else{
                JOptionPane.showMessageDialog(null, "Please Check Item!!");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        try {

            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("DELETE FROM borrowed_tbl  WHERE Book_title = '" + nb_bt.getText() + "'");
            int del = pst.executeUpdate();
            if (del > 0) {
                JOptionPane.showMessageDialog(null, "Book Entry Deleted!");
                
            }else{
                JOptionPane.showMessageDialog(null, "Please Check Item!!");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        all_ref();
        nb_clr();
        nb_disable();
        nb_update.setEnabled(false);
        nb_delete.setEnabled(false);
        nb_new.setEnabled(true);
    }//GEN-LAST:event_nb_deleteActionPerformed

    private void brr_browseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brr_browseActionPerformed
        JFileChooser file = new JFileChooser(jFileChooser1.getCurrentDirectory());
        file.setCurrentDirectory(new File(System.getProperty("user.home")));
        //filter the files'
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Image*","jpg","gif","png");
        file.addChoosableFileFilter(filter);
        int result = file.showSaveDialog(null);
        //if the user click on save jfilechooser
        if(result == JFileChooser.APPROVE_OPTION){
            File selectedFile = file.getSelectedFile();
            String path = selectedFile.getAbsolutePath();
            // image.setIcon(ResizeImage(path,null));
            brr_image.setIcon(new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(brr_image.getWidth(), brr_image.getHeight(), Image.SCALE_DEFAULT)));
            imgpath = path;

        }else if(result  == JFileChooser.CANCEL_OPTION){
            JOptionPane.showMessageDialog(null,"No File Selected");
        }
    }//GEN-LAST:event_brr_browseActionPerformed

    private void brr_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_brr_tableMouseClicked
        int z = brr_table.getSelectedRow();

        TableModel model = (TableModel)brr_table.getModel();
        brr_id.setText(model.getValueAt(z, 0).toString());
        ((JTextField)brr_bd.getDateEditor().getUiComponent()).setText(model.getValueAt(z, 10).toString());

        try{

            String sql="SELECT First_Name, Middle_Name, Surname, ID_Type, ID_No, Course, Year, Address, Status, "
                    + " Photo "
            + "  FROM borrower_tbl where Library_ID = '" + (String) brr_id.getText() + "' ";
            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();

            if(rs.next()){
                String name1 =rs.getString("First_Name");
                String name2 =rs.getString("Middle_Name");
                String name3 =rs.getString("Surname");
                String name4 =rs.getString("ID_Type");
                String name5 =rs.getString("ID_No");
                String name6 =rs.getString("Course");
                String name7 =rs.getString("Year");
                String name8 =rs.getString("Address");
                String name9 =rs.getString("Status");

                brr_fn.setText(name1);
                brr_mn.setText(name2);
                brr_sn.setText(name3);
                brr_idt.setSelectedItem(name4);
                brr_idn.setText(name5);
                brr_cr.setSelectedItem(name6);
                brr_yr.setSelectedItem(name7);
                brr_add.setText(name8);
                brr_stat.setText(name9);
                //brr_stat.setSelectedItem(name6);

                byte[] img = rs.getBytes("Photo");
                ImageIcon image1 =  new ImageIcon(img);
                Image im = image1.getImage();
                Image myImage = im.getScaledInstance(brr_image.getWidth(), brr_image.getHeight(), Image.SCALE_SMOOTH );
                ImageIcon newImage = new ImageIcon(myImage);
                brr_image.setIcon(newImage);

            }
        }
        catch (Exception e) {
           // JOptionPane.showMessageDialog(null, e);
        }

        brr_save.setEnabled(false);
        brr_update.setEnabled(true);
        brr_delete.setEnabled(true);
        brr_browse.setEnabled(true);
        brr_able();
        brr_fn.setEditable(true);
    }//GEN-LAST:event_brr_tableMouseClicked

    private void brr_newActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brr_newActionPerformed
        brr_browse.setEnabled(true);
        brr_save.setEnabled(true);
        brr_update.setEnabled(false);
        brr_delete.setEnabled(false);
        brr_new.setEnabled(true);
        brr_fn.setEditable(true);
        brr_clr();
        brr_able();
    }//GEN-LAST:event_brr_newActionPerformed

    private void brr_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brr_saveActionPerformed
        if (imgpath != null){
            int a = brr_table.getRowCount();
            if(a <= 0){    
            brr_q.setText("1");
            }
            else {
            int total;
            total = a + 1;
            brr_q.setText(Integer.toString(total));
            }
            try{
                String sql = "INSERT INTO borrower_tbl (Library_ID, First_Name, Middle_Name, Surname, ID_Type, ID_No, "
                        + "Course, Year, Address, Status, Birth_Date, Photo) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
                pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement(sql);
                InputStream img = new FileInputStream(new File(imgpath));
                pst.setString(1, "LIB-ID-"+brr_q.getText());
                pst.setString(2, brr_fn.getText());
                pst.setString(3, brr_mn.getText());
                pst.setString(4, brr_sn.getText());
                pst.setString(5, (String)brr_idt.getSelectedItem());
                pst.setString(6, brr_idn.getText());
                pst.setString(7, (String)brr_cr.getSelectedItem());
                pst.setString(8, (String)brr_yr.getSelectedItem());
                pst.setString(9, brr_add.getText());
                pst.setString(10, brr_stat.getText());
                pst.setString(11, ((JTextField)brr_bd.getDateEditor().getUiComponent()).getText());
                pst.setBlob(12, img);
                
                pst.execute();

                String sql1 = "INSERT INTO useraccount_tbl (First_Name, Middle_Name, Surname, Username, Employee_ID, "
                        + "Position, Mobile_Number, Password, Level, Photo) VALUES (?,?,?,?,?,?,?,?,?,?)";
                pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement(sql1);
                //InputStream img1 = new FileInputStream(new File(imgpath1));
                pst.setString(1, brr_fn.getText());
                pst.setString(2, brr_mn.getText());
                pst.setString(3, brr_sn.getText());
                pst.setString(4, brr_sn.getText());
                pst.setString(5, "");
                pst.setString(6, "");
                pst.setString(7, "");
                pst.setString(8, "1234");
                pst.setString(9, brr_stat.getText());
                pst.setBlob(10, img);
                
                pst.execute( );
                
                 JOptionPane.showMessageDialog(null,"Account Created");
                    all_ref();
                    brr_clr();
                    brr_disable();
                    brr_save.setEnabled(false);
                    brr_update.setEnabled(false);
                    brr_delete.setEnabled(false);
                    brr_browse.setEnabled(false);
                    brr_new.setEnabled(true);
                
            }catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        else{
            int a = brr_table.getRowCount();
            if(a <= 0){    
            brr_q.setText("1");
            }
            else {
            int total;
            total = a + 1;
            brr_q.setText(Integer.toString(total));
            }
            try{
                String sql = "INSERT INTO borrower_tbl (Library_ID, First_Name, Middle_Name, Surname, ID_Type, ID_No, "
                        + "Course, Year, Address, Status, Birth_Date) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
                pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement(sql);
                pst.setString(1, "LIB-ID-"+brr_q.getText());
                pst.setString(2, brr_fn.getText());
                pst.setString(3, brr_mn.getText());
                pst.setString(4, brr_sn.getText());
                pst.setString(5, (String)brr_idt.getSelectedItem());
                pst.setString(6, brr_idn.getText());
                pst.setString(7, (String)brr_cr.getSelectedItem());
                pst.setString(8, (String)brr_yr.getSelectedItem());
                pst.setString(9, brr_add.getText());
                pst.setString(10, brr_stat.getText());
                pst.setString(11, ((JTextField)brr_bd.getDateEditor().getUiComponent()).getText());
                
                pst.execute(); 
                
                String sql1 = "INSERT INTO useraccount_tbl (First_Name, Middle_Name, Surname, Username, Employee_ID, "
                        + "Position, Mobile_Number, Password, Level) VALUES (?,?,?,?,?,?,?,?,?)";
                pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement(sql1);
                //InputStream img1 = new FileInputStream(new File(imgpath1));
                pst.setString(1, brr_fn.getText());
                pst.setString(2, brr_mn.getText());
                pst.setString(3, brr_sn.getText());
                pst.setString(4, brr_sn.getText());
                pst.setString(5, "");
                pst.setString(6, "");
                pst.setString(7, "");
                pst.setString(8, "1234");
                pst.setString(9, brr_stat.getText());
                
                pst.execute();
                
                JOptionPane.showMessageDialog(null,"Account Created");
                    all_ref();
                    brr_clr();
                    brr_disable();
                    brr_save.setEnabled(false);
                    brr_update.setEnabled(false);
                    brr_delete.setEnabled(false);
                    brr_browse.setEnabled(false);
                    brr_new.setEnabled(true);
            }catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        imgpath=null;
        all_ref();
    }//GEN-LAST:event_brr_saveActionPerformed

    private void brr_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brr_updateActionPerformed
        if (imgpath != null){
            try{
                pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("UPDATE borrower_tbl SET "
                    + " First_Name=?, Middle_Name=?, Surname=?, ID_Type=?, ID_No=?, Course=?, "
                        + "Year=?, Address=?, Status=?, Birth_Date=?, Photo=? "
                    + " WHERE Library_ID ='" + brr_id.getText() + "'");
                InputStream img = new FileInputStream(new File(imgpath));
                pst.setString(1, brr_fn.getText());
                pst.setString(2, brr_mn.getText());
                pst.setString(3, brr_sn.getText());
                pst.setString(4, (String) brr_idt.getSelectedItem());
                pst.setString(5, brr_idn.getText());
                pst.setString(6, (String) brr_cr.getSelectedItem());
                pst.setString(7, (String) brr_yr.getSelectedItem());
                pst.setString(8, brr_add.getText());
                pst.setString(9, brr_stat.getText());
                pst.setString(10, ((JTextField)brr_bd.getDateEditor().getUiComponent()).getText());
                pst.setBlob(11, img);
                
                pst.executeUpdate();
                
                pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("UPDATE useraccount_tbl SET "
                    + " First_Name=?, Middle_Name=?, Surname=?, "
                        + "Photo=? "
                    + " WHERE ID ='" + brr_id.getText() + "'");
                InputStream img1 = new FileInputStream(new File(imgpath));
                pst.setString(1, acc_fn.getText());
                pst.setString(2, acc_ffmn.getText());
                pst.setString(3, acc_ffsn.getText());
                pst.setBlob(4, img1);
                
                pst.executeUpdate();
                
                JOptionPane.showMessageDialog(null,"Account Info Updated");
                
                    all_ref();
                    brr_clr();
                    brr_disable();
                    brr_save.setEnabled(false);
                    brr_update.setEnabled(false);
                    brr_delete.setEnabled(false);
                    brr_browse.setEnabled(false);
                    brr_new.setEnabled(true);

            }catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }else{
            try{
                pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("UPDATE borrower_tbl SET "
                    + " First_Name=?, Middle_Name=?, Surname=?, ID_Type=?, ID_No=?, Course=?, "
                        + "Year=?, Address=?, Status=?, Birth_Date=? "
                    + " WHERE Library_ID ='" + brr_id.getText() + "'");
                
                pst.setString(1, brr_fn.getText());
                pst.setString(2, brr_mn.getText());
                pst.setString(3, brr_sn.getText());
                pst.setString(4, (String) brr_idt.getSelectedItem());
                pst.setString(5, brr_idn.getText());
                pst.setString(6, (String) brr_cr.getSelectedItem());
                pst.setString(7, (String) brr_yr.getSelectedItem());
                pst.setString(8, brr_add.getText());
                pst.setString(9, brr_stat.getText());
                pst.setString(10, ((JTextField)brr_bd.getDateEditor().getUiComponent()).getText());
                
                pst.executeUpdate();
                
                pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("UPDATE useraccount_tbl SET"
                        + " First_Name=?, Middle_Name=?, Surname=? WHERE Surname ='" + acc_ffsn.getText() + "'");
                pst.setString(1, acc_fn.getText());
                pst.setString(2, acc_ffmn.getText());
                pst.setString(3, acc_ffsn.getText());
                pst.executeUpdate();
                
                JOptionPane.showMessageDialog(null,"Account Info Updated");
                
                    all_ref();
                    brr_clr();
                    brr_disable();
                    brr_save.setEnabled(false);
                    brr_update.setEnabled(false);
                    brr_delete.setEnabled(false);
                    brr_browse.setEnabled(false);
                    brr_new.setEnabled(true);

            }catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
            JOptionPane.showMessageDialog(null,"Please Check Entry!!!");
        }
        imgpath1=null;
        all_ref();
    }//GEN-LAST:event_brr_updateActionPerformed

    private void brr_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brr_deleteActionPerformed
        try {

            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("DELETE FROM borrower_tbl  WHERE Library_ID = '" + brr_id.getText() + "'");
            int del = pst.executeUpdate();
            
            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("DELETE FROM useraccount_tbl  WHERE First_Name = '" + brr_fn.getText() + "'");
            int del1 = pst.executeUpdate();
            
            if (del > 0 || del1 > 0) {
                JOptionPane.showMessageDialog(null, "Data Removed");
            }else{
                JOptionPane.showMessageDialog(null, "No ID Exist on the Database!!");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        all_ref();
        brr_clr();
        brr_disable();
        brr_save.setEnabled(false);
        brr_new.setEnabled(true);
        brr_browse.setEnabled(false);
        brr_update.setEnabled(false);
        brr_delete.setEnabled(false);
    }//GEN-LAST:event_brr_deleteActionPerformed

    private void acc_browseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acc_browseActionPerformed
        JFileChooser file = new JFileChooser(jFileChooser2.getCurrentDirectory());
        file.setCurrentDirectory(new File(System.getProperty("user.home")));
        //filter the files'
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Image*","jpg","gif","png");
        file.addChoosableFileFilter(filter);
        int result = file.showSaveDialog(null);
        //if the user click on save jfilechooser
        if(result == JFileChooser.APPROVE_OPTION){
            File selectedFile = file.getSelectedFile();
            String path = selectedFile.getAbsolutePath();
            // image.setIcon(ResizeImage(path,null));
            acc_image.setIcon(new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(acc_image.getWidth(), acc_image.getHeight(), Image.SCALE_DEFAULT)));
            imgpath1 = path;

        }else if(result  == JFileChooser.CANCEL_OPTION){
            JOptionPane.showMessageDialog(null,"No File Selected");
        }
    }//GEN-LAST:event_acc_browseActionPerformed

    private void acc_brrdMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_acc_brrdMouseClicked
        if(acc_brrd.isSelected()){
            brrd.setText("1");
        }else{
            brrd.setText("0");
        }
    }//GEN-LAST:event_acc_brrdMouseClicked

    private void acc_accMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_acc_accMouseClicked
        if(acc_acc.isSelected()){
            ua.setText("1");
        }else{
            ua.setText("0");
        }
    }//GEN-LAST:event_acc_accMouseClicked

    private void acc_invMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_acc_invMouseClicked
        if(acc_inv.isSelected()){
            inv.setText("1");
        }else{
            inv.setText("0");
        }
    }//GEN-LAST:event_acc_invMouseClicked

    private void acc_nbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_acc_nbMouseClicked
        if(acc_nb.isSelected()){
            nb.setText("1");
        }else{
            nb.setText("0");
        }
    }//GEN-LAST:event_acc_nbMouseClicked

    private void acc_repMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_acc_repMouseClicked
        if(acc_rep.isSelected()){
            rep.setText("1");
        }else{
            rep.setText("0");
        }
    }//GEN-LAST:event_acc_repMouseClicked

    private void acc_brrrMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_acc_brrrMouseClicked
        if(acc_brrr.isSelected()){
            brrr.setText("1");
        }else{
            brrr.setText("0");
        }
    }//GEN-LAST:event_acc_brrrMouseClicked

    private void acc_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_acc_tableMouseClicked
        int z = acc_table.getSelectedRow();

        TableModel model = (TableModel)acc_table.getModel();
        acc_id.setText(model.getValueAt(z, 0).toString());

        try{

            String sql="SELECT First_Name, Middle_Name, Surname, Employee_ID, Position,"
                    + " Username, Password, Level, Mobile_Number, photo "
            + "  FROM useraccount_tbl where ID = '" + (String) acc_id.getText() + "' ";
            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();

            if(rs.next()){
                String name1 =rs.getString("First_Name");
                String name2 =rs.getString("Username");
                String name3 =rs.getString("Password");
                String name4 =rs.getString("Level");
                String name5 =rs.getString("Mobile_Number");
                String name6 =rs.getString("Middle_Name");
                String name7 =rs.getString("Surname");
                String name8 =rs.getString("Employee_ID");
                String name9 =rs.getString("Position");

                acc_fn.setText(name1);
                acc_user.setText(name2);
                acc_pass.setText(name3);
                acc_lvl.setSelectedItem(name4);
                acc_mn.setText(name5);
                acc_ffmn.setText(name6);
                acc_ffsn.setText(name7);
                acc_mid.setText(name8);
                acc_pos.setText(name9);
                

                byte[] img = rs.getBytes("Photo");
                ImageIcon image1 =  new ImageIcon(img);
                Image im = image1.getImage();
                Image myImage = im.getScaledInstance(acc_image.getWidth(), acc_image.getHeight(), Image.SCALE_SMOOTH );
                ImageIcon newImage = new ImageIcon(myImage);
                acc_image.setIcon(newImage);

            }
        }
        catch (Exception e) {
            //JOptionPane.showMessageDialog(null, "No Photo");
            acc_image.setIcon(new ImageIcon(new ImageIcon("").getImage().getScaledInstance(acc_image.getWidth(), acc_image.getHeight(), Image.SCALE_DEFAULT)));
            acc_image.setText("Select Image");
        }
        
        acc_new.setEnabled(true);
        acc_save.setEnabled(false);
        acc_update.setEnabled(true);
        acc_delete.setEnabled(true);
        acc_browse.setEnabled(true);
        acc_able();
    }//GEN-LAST:event_acc_tableMouseClicked

    private void acc_newActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acc_newActionPerformed
        acc_able();
        acc_clr();
        acc_new.setEnabled(false);
        acc_save.setEnabled(true);
        acc_browse.setEnabled(true);
        acc_update.setEnabled(false);
        acc_delete.setEnabled(false);
    }//GEN-LAST:event_acc_newActionPerformed

    private void acc_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acc_saveActionPerformed
        if (imgpath1 != null){
            try{
                String sql = "INSERT INTO useraccount_tbl (First_Name, Middle_Name, Surname, Username, Employee_ID, "
                        + "Position, Mobile_Number, Password, Level, Photo) VALUES (?,?,?,?,?,?,?,?,?,?)";
                pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement(sql);
                InputStream img = new FileInputStream(new File(imgpath1));
                pst.setString(1, acc_fn.getText());
                pst.setString(2, acc_ffmn.getText());
                pst.setString(3, acc_ffsn.getText());
                pst.setString(4, acc_user.getText());
                pst.setString(5, acc_mid.getText());
                pst.setString(6, acc_pos.getText());
                pst.setString(7, acc_mn.getText());
                pst.setString(8, acc_pass.getText());
                pst.setString(9, (String)acc_lvl.getSelectedItem());
                pst.setBlob(10, img);
                
                if(pst.executeUpdate()==1){
                    JOptionPane.showMessageDialog(null,"Account Created");
                    acc_clr();
                    acc_disable();
                    all_ref();
                    acc_browse.setEnabled(false);
                    acc_save.setEnabled(false);
                    acc_new.setEnabled(true);
                    acc_update.setEnabled(false);
                    acc_delete.setEnabled(false);

                }else{
                    JOptionPane.showMessageDialog(null,"Error 101");
                }
            }catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }else{
            try{
                String sql = "INSERT INTO useraccount_tbl (First_Name, Middle_Name, Surname, Username, Employee_ID, "
                        + "Position, Mobile_Number, Password, Level) VALUES (?,?,?,?,?,?,?,?,?)";
                pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement(sql);

                pst.setString(1, acc_fn.getText());
                pst.setString(2, acc_ffmn.getText());
                pst.setString(3, acc_ffsn.getText());
                pst.setString(4, acc_user.getText());
                pst.setString(5, acc_mid.getText());
                pst.setString(6, acc_pos.getText());
                pst.setString(7, acc_mn.getText());
                pst.setString(8, acc_pass.getText());
                pst.setString(9, (String)acc_lvl.getSelectedItem());
                
                if(pst.executeUpdate()==1){
                    JOptionPane.showMessageDialog(null,"Account Created");
                    acc_clr();
                    acc_disable();
                    all_ref();
                    acc_browse.setEnabled(false);
                    acc_save.setEnabled(false);
                    acc_new.setEnabled(true);
                    acc_update.setEnabled(false);
                    acc_delete.setEnabled(false);

                }else{
                    JOptionPane.showMessageDialog(null,"Error 102");
                }
            }catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        imgpath1 = null;
    }//GEN-LAST:event_acc_saveActionPerformed

    private void acc_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acc_updateActionPerformed
        if(acc_pass.getText().equals(acc_con.getText())){
        
        if (imgpath1 != null){
            try{
                pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("UPDATE useraccount_tbl SET "
                    + " First_Name=?, Middle_Name=?, Surname=?, Username=?, Employee_ID=?, Position=?, "
                        + "Mobile_Number=?, Password=?, Level=?, Photo=? "
                    + " WHERE ID ='" + acc_id.getText() + "'");
                InputStream img = new FileInputStream(new File(imgpath1));
                pst.setString(1, acc_fn.getText());
                pst.setString(2, acc_ffmn.getText());
                pst.setString(3, acc_ffsn.getText());
                pst.setString(4, acc_user.getText());
                pst.setString(5, acc_mid.getText());
                pst.setString(6, acc_pos.getText());
                pst.setString(7, acc_mn.getText());
                pst.setString(8, acc_pass.getText());
                pst.setString(9, (String) acc_lvl.getSelectedItem());
                pst.setBlob(10, img);
                
                int update = pst.executeUpdate();
                JOptionPane.showMessageDialog(null,"Account Info Updated");
                
                all_ref();
                acc_clr();
                acc_disable();
                acc_save.setEnabled(false);
                acc_new.setEnabled(true);
                acc_browse.setEnabled(false);
                acc_update.setEnabled(false);
                acc_delete.setEnabled(false);

            }catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }else{
            try{
                pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("UPDATE useraccount_tbl SET "
                    + " First_Name=?, Middle_Name=?, Surname=?, Username=?, Employee_ID=?, Position=?, "
                        + "Mobile_Number=?, Password=?, Level=? "
                    + " WHERE ID ='" + acc_id.getText() + "'");
                pst.setString(1, acc_fn.getText());
                pst.setString(2, acc_ffmn.getText());
                pst.setString(3, acc_ffsn.getText());
                pst.setString(4, acc_user.getText());
                pst.setString(5, acc_mid.getText());
                pst.setString(6, acc_pos.getText());
                pst.setString(7, acc_mn.getText());
                pst.setString(8, acc_pass.getText());
                pst.setString(9, (String) acc_lvl.getSelectedItem());
                
                int update = pst.executeUpdate();
                JOptionPane.showMessageDialog(null,"Account Info Updated");
                
                all_ref();
                acc_clr();
                acc_disable();
                acc_save.setEnabled(false);
                acc_new.setEnabled(true);
                acc_browse.setEnabled(false);
                acc_update.setEnabled(false);
                acc_delete.setEnabled(false);

            }catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
            JOptionPane.showMessageDialog(null,"Please Check Entry!!!");
        }
        imgpath1=null;
        }
        else{
        JOptionPane.showMessageDialog(null,"Password Don't Match!!!");
        }
    }//GEN-LAST:event_acc_updateActionPerformed

    private void acc_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acc_deleteActionPerformed
        try {

            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("DELETE FROM useraccount_tbl  WHERE ID = '" + acc_id.getText() + "'");
            int del = pst.executeUpdate();
            
            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("DELETE FROM borrower_tbl  WHERE First_Name = '" + acc_fn.getText() + "'");
            int del1 = pst.executeUpdate();
            
            if (del > 0 || del > 0) {
                JOptionPane.showMessageDialog(null, "Data Removed");
            }else{
                JOptionPane.showMessageDialog(null, "No ID Exist on the Database!!");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        all_ref();
        acc_clr();
        acc_disable();
        acc_save.setEnabled(false);
        acc_new.setEnabled(true);
        acc_browse.setEnabled(false);
        acc_update.setEnabled(false);
        acc_delete.setEnabled(false);
    }//GEN-LAST:event_acc_deleteActionPerformed

    private void men_repMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_men_repMouseClicked
        men_dash.setBackground(new Color(96,96,96));
        men_rec.setBackground(new Color(96,96,96));
        men_inv.setBackground(new Color(96,96,96));
        men_brrd.setBackground(new Color(96,96,96));
        men_brrr.setBackground(new Color(96,96,96));
        men_ua.setBackground(new Color(96,96,96));
        men_logout.setBackground(new Color(96,96,96));
        menu_title.setText("REPORT");
        men_po.setForeground(new Color(255,255,255));
        men_po.setBackground(new Color(96,96,96));
        men_rep.setBackground(new Color(255,255,255));
        men_rep.setForeground(new Color(96,96,96));
        men_dash.setForeground(new Color(255,255,255));
        men_rec.setForeground(new Color(255,255,255));
        men_inv.setForeground(new Color(255,255,255));
        men_brrd.setForeground(new Color(255,255,255));
        men_brrr.setForeground(new Color(255,255,255));
        men_ua.setForeground(new Color(255,255,255));
        men_logout.setForeground(new Color(255,255,255));
        
        dashboard.setVisible(false);
        Records.setVisible(false);
        inventory.setVisible(false);
        Bookloan.setVisible(false);
        borrower.setVisible(false);
        useraccount.setVisible(false);
        report.setVisible(true);
        user_logo.setVisible(false);
        classification.setVisible(false);
        booklist.setVisible(false);
    }//GEN-LAST:event_men_repMouseClicked

    private void nb_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nb_searchKeyReleased
        try {
           String sql = "SELECT *"
                    + " FROM stockin_tbl WHERE "
                    + "Book_ID like? or ISBN_No like ? or Book_title like ? or Author like ? or Quantity like ? or "
                   + "Call_Number like ? or Classification like ? or "
                    + "Edition like ? or Copy_Right_Year like ? or Price like ? or Encoder like ? or "
                   + "Date_Arrival like ?";

            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, "%" + nb_search.getText() + "%");
            pst.setString(2, "%" + nb_search.getText() + "%");
            pst.setString(3, "%" + nb_search.getText() + "%");
            pst.setString(4, "%" + nb_search.getText() + "%");
            pst.setString(5, "%" + nb_search.getText() + "%");
            pst.setString(6, "%" + nb_search.getText() + "%");
            pst.setString(7, "%" + nb_search.getText() + "%");
            pst.setString(8, "%" + nb_search.getText() + "%");
            pst.setString(9, "%" + nb_search.getText() + "%");
            pst.setString(10, "%" + nb_search.getText() + "%");
            pst.setString(11, "%" + nb_search.getText() + "%");
            pst.setString(12, "%" + nb_search.getText() + "%");

            rs = (ResultSet) pst.executeQuery();
            nb_table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, ex);
        }
    }//GEN-LAST:event_nb_searchKeyReleased

    private void nb_isKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nb_isKeyReleased
        try {
            String sql = "SELECT *"
                    + " FROM stockin_tbl WHERE "
                    + "ISBN_No like ? ";

            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, "%" + nb_is.getText() + "%");

            rs = (ResultSet) pst.executeQuery();
            nb_table.setModel(DbUtils.resultSetToTableModel(rs));
            rc_nb();
        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, ex);
        }
        if(jLabel188.getText().equals("0")){
        jLabel187.setText("ISBN Available");
        }
        else{
        jLabel187.setText("ISBN Not Available");
        }
    }//GEN-LAST:event_nb_isKeyReleased

    private void nb_btKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nb_btKeyReleased
        try {
            String sql = "SELECT *"
                    + " FROM stockin_tbl WHERE "
                    + "Book_title like ?";

            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, "%" + nb_bt .getText() + "%");

            rs = (ResultSet) pst.executeQuery();
            nb_table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, ex);
        }
    }//GEN-LAST:event_nb_btKeyReleased

    private void nb_new1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nb_new1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nb_new1ActionPerformed

    private void nb_autKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nb_autKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_nb_autKeyReleased

    private void sup_nameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sup_nameKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_sup_nameKeyReleased

    private void sup_addKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sup_addKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_sup_addKeyReleased

    private void sup_conKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sup_conKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_sup_conKeyReleased

    private void sup_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sup_tableMouseClicked
        int z = sup_table.getSelectedRow();

            TableModel model = (TableModel)sup_table.getModel();
          sup_id.setText(model.getValueAt(z, 0).toString());
          sup_name.setText(model.getValueAt(z, 1).toString());
          sup_add.setText(model.getValueAt(z, 2).toString());
          sup_con.setText(model.getValueAt(z, 3).toString());
          sup_em.setText(model.getValueAt(z, 4).toString());
          sup_cp.setText(model.getValueAt(z, 5).toString());
          ((JTextField)sup_enc.getDateEditor().getUiComponent()).setText(model.getValueAt(z, 6).toString());
          
          supp_able();
          sup_save.setEnabled(false);
          sup_update.setEnabled(true);
          sup_delete.setEnabled(true);
          sup_new.setEnabled(true);
    }//GEN-LAST:event_sup_tableMouseClicked

    private void sup_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sup_searchKeyReleased
        try {
           String sql = "SELECT *"
                    + " FROM supplier_tbl WHERE "
                    + "Supplier_ID like ? or Supplier_Name like ? or Address like ? or Contact like ? or Email like ?"
                   + " or Date_Encoded like ? or Encoder like ?";

            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, "%" + sup_search.getText() + "%");
            pst.setString(2, "%" + sup_search.getText() + "%");
            pst.setString(3, "%" + sup_search.getText() + "%");
            pst.setString(4, "%" + sup_search.getText() + "%");
            pst.setString(5, "%" + sup_search.getText() + "%");
            pst.setString(6, "%" + sup_search.getText() + "%");
            pst.setString(7, "%" + sup_search.getText() + "%");

            rs = (ResultSet) pst.executeQuery();
            sup_table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, ex);
        }
    }//GEN-LAST:event_sup_searchKeyReleased

    private void sup_newActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sup_newActionPerformed
        supp_able();
        supp_clr();
        sup_new.setEnabled(false);
        sup_save.setEnabled(true);
        sup_update.setEnabled(false);
        sup_delete.setEnabled(false);
    }//GEN-LAST:event_sup_newActionPerformed

    private void sup_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sup_saveActionPerformed
        try {
            String sql = "Insert into supplier_tbl (Supplier_Name, Address, Contact, Email, Contact_Person, Date_Encoded, Encoder) "
                    + " values (?,?,?,?,?,?,?)";

            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement(sql);
            
            pst.setString(1, sup_name.getText());
            pst.setString(2, sup_add.getText());
            pst.setString(3, sup_con.getText());
            pst.setString(4, sup_em.getText());
            pst.setString(5, sup_cp.getText());
            pst.setString(6, ((JTextField)sup_enc.getDateEditor().getUiComponent()).getText());
            pst.setString(7, txt_name.getText());

            pst.execute(); 
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        supp_list();
        all_ref();
        supp_clr();
        supp_disable();
        sup_save.setEnabled(false);
        sup_new.setEnabled(true);
    }//GEN-LAST:event_sup_saveActionPerformed

    private void sup_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sup_updateActionPerformed
        try {
            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("UPDATE supplier_tbl SET Supplier_Name=?, "
                    + "Address=?, Contact=?, Email=?, Contact_Person=?, Date_Encoded=?, Encoder=? WHERE Supplier_ID='" + sup_id.getText() + "'");
            
            pst.setString(1, sup_name.getText());
            pst.setString(2, sup_add.getText());
            pst.setString(3, sup_con.getText());
            pst.setString(4, sup_em.getText());
            pst.setString(5, sup_cp.getText());
            pst.setString(6, ((JTextField)sup_enc.getDateEditor().getUiComponent()).getText());
            pst.setString(7, txt_name.getText());
            
            pst.execute();
            JOptionPane.showMessageDialog(null, "Data Updated");
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        supp_list();
        all_ref();
        supp_clr();
        supp_disable();
        sup_update.setEnabled(false);
        sup_delete.setEnabled(false);
        sup_new.setEnabled(true);
    }//GEN-LAST:event_sup_updateActionPerformed

    private void sup_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sup_deleteActionPerformed
        try {

            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("DELETE FROM supplier_tbl  WHERE Supplier_ID = '" + sup_id.getText() + "'");
            int del = pst.executeUpdate();
            if (del > 0) {
                JOptionPane.showMessageDialog(null, "Data Deleted!");
                
            }else{
                JOptionPane.showMessageDialog(null, "Please Check Item!!");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        supp_list();
        all_ref();
        supp_clr();
        supp_disable();
        sup_update.setEnabled(false);
        sup_delete.setEnabled(false);
        sup_new.setEnabled(true);
    }//GEN-LAST:event_sup_deleteActionPerformed

    private void si_isbnKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_si_isbnKeyReleased
        try {
           String sql = "SELECT *"
                    + " FROM holding_tbl WHERE "
                    + "ISBN_No like ? ";

            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, "%" + si_isbn.getText() + "%");

            rs = (ResultSet) pst.executeQuery();
            si_table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, ex);
        }
    }//GEN-LAST:event_si_isbnKeyReleased

    private void si_btKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_si_btKeyReleased
        try {
           String sql = "SELECT *"
                    + " FROM holding_tbl WHERE "
                    + "Book_title like ? ";

            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, "%" + si_bt.getText() + "%");

            rs = (ResultSet) pst.executeQuery();
            si_table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, ex);
        }
    }//GEN-LAST:event_si_btKeyReleased

    private void si_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_si_addActionPerformed
            int a = Integer.parseInt(si_oh.getText());
            int total;
            
            total = a + 1;
            
            si_oh.setText(Integer.toString(total));
            si_oh.setForeground(Color.red);
            si_minus.setEnabled(true);
            si_save.setEnabled(false);
    }//GEN-LAST:event_si_addActionPerformed

    private void si_minusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_si_minusActionPerformed
        int a, b;
        a = Integer.parseInt(minus_test.getText());
        b = Integer.parseInt(si_oh.getText());
        
        if(b == a){
            si_minus.setEnabled(false);
            JOptionPane.showMessageDialog(null, "Limit Reach!");
        }
        else{
            si_minus.setEnabled(true);
            if(b <= 0){    
            JOptionPane.showMessageDialog(null, "Already Zero!");
            }
            else {
            int aa = Integer.parseInt(si_oh.getText());
            int total;
            
            total = aa - 1;
            
            si_oh.setText(Integer.toString(total));
            si_oh.setForeground(Color.red);
            }
        }
        si_save.setEnabled(false);
    }//GEN-LAST:event_si_minusActionPerformed

    private void si_calcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_si_calcActionPerformed
        int a, b, c, d;
        a = Integer.parseInt(si_oh.getText());
        b = Integer.parseInt(si_brrd.getText());
        c = Integer.parseInt(si_dmg.getText());
        
        d = a + b + c;
        d = Math.round(d*100)/100;
        si_th.setText(Integer.toString(d));
        
        si_save.setEnabled(true);
    }//GEN-LAST:event_si_calcActionPerformed

    private void si_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_si_tableMouseClicked
        int z = si_table.getSelectedRow();

            TableModel model = (TableModel)si_table.getModel();
          si_id.setText(model.getValueAt(z, 0).toString());
          si_isbn.setText(model.getValueAt(z, 1).toString());
          si_bt.setText(model.getValueAt(z, 2).toString());
          si_class.setText(model.getValueAt(z, 3).toString());
          si_oh.setText(model.getValueAt(z, 4).toString());
          minus_test.setText(model.getValueAt(z, 4).toString());
          si_brrd.setText(model.getValueAt(z, 5).toString());
          si_dmg.setText(model.getValueAt(z, 6).toString());
          si_th.setText(model.getValueAt(z, 7).toString());
          si_enc.setText(model.getValueAt(z, 8).toString());
          si_date.setText(model.getValueAt(z, 9).toString());
          si_time.setText(model.getValueAt(z, 10).toString());

          si_add.setEnabled(true);
    }//GEN-LAST:event_si_tableMouseClicked

    private void si_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_si_saveActionPerformed
        try {
            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("UPDATE holding_tbl SET On_Hand=?, "
                    + "Total_Holding=?, Encoder=?, Date=?, Time=? WHERE ID='" + si_id.getText() + "'");
            
            pst.setString(1, si_oh.getText());
            pst.setString(2, si_th.getText());
            pst.setString(3, txt_name.getText());
            pst.setString(4, txt_date1.getText());
            pst.setString(5, txt_time.getText());
            
            pst.execute();
            JOptionPane.showMessageDialog(null, "Data Updated");
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        try {
            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("UPDATE stockin_tbl SET  "
                    + "Quantity=? WHERE Book_ID='" + si_id.getText() + "'");
            
            pst.setString(1, si_oh.getText());
            
            pst.execute();
            JOptionPane.showMessageDialog(null, "Data Updated");
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        
        try {
            String sql = "Insert into invlog_tbl (ISBN_No, Book_title, Classification, Encoder, Date, Time, Action) "
                    + " values (?,?,?,?,?,?,?)";

            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement(sql);
            
            pst.setString(1, si_isbn.getText());
            pst.setString(2, si_bt.getText());
            pst.setString(3, si_class.getText());
            pst.setString(4, txt_name.getText());
            pst.setString(5, txt_date1.getText());
            pst.setString(6, txt_time.getText());
            pst.setString(7, "Stock-in");

            pst.execute(); 
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
        si_isbn.setText("");
        si_bt.setText("");
        si_class.setText("");
        si_oh.setText("0");
        si_brrd.setText("0");
        si_dmg.setText("0");
        si_th.setText("0");
        si_enc.setText("0");
        si_date.setText("0");
        si_time.setText("0");
        si_add.setEnabled(false);
        
        all_ref();
    }//GEN-LAST:event_si_saveActionPerformed

    private void so_isbnKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_so_isbnKeyReleased
        try {
           String sql = "SELECT *"
                    + " FROM damage_tbl WHERE "
                    + "ISBN_No like ? ";

            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, "%" + so_isbn.getText() + "%");

            rs = (ResultSet) pst.executeQuery();
            so_table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, ex);
        }
    }//GEN-LAST:event_so_isbnKeyReleased

    private void so_btKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_so_btKeyReleased
        try {
           String sql = "SELECT *"
                    + " FROM damage_tbl WHERE "
                    + "Book_title like ? ";

            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, "%" + so_bt.getText() + "%");

            rs = (ResultSet) pst.executeQuery();
            so_table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, ex);
        }
    }//GEN-LAST:event_so_btKeyReleased

    private void so_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_so_addActionPerformed
        int a = Integer.parseInt(so_qty.getText());
            int total ,aa;
            aa = Integer.parseInt(sominus_test.getText());
        
            if(a == aa){
            JOptionPane.showMessageDialog(null, "Limit Reach!");
            }
            else {
            total = a + 1;
            
            so_qty.setText(Integer.toString(total));
            so_qty.setForeground(Color.red);
            }
    }//GEN-LAST:event_so_addActionPerformed

    private void so_minusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_so_minusActionPerformed
        int qty = Integer.parseInt(so_qty.getText());
        int total;
        
        if(qty == 0){
        JOptionPane.showMessageDialog(null, "Already Zero!");
        }
        else{
        total = qty - 1;
        
        so_qty.setText(Integer.toString(total));
        so_qty.setForeground(Color.red);
        }
        
    }//GEN-LAST:event_so_minusActionPerformed

    private void so_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_so_saveActionPerformed
        int qty = Integer.parseInt(sominus_test.getText());
        int dmg = Integer.parseInt(so_qty.getText());
        int tbl = Integer.parseInt(frm_tbl.getText());
        int total , total2;
        
        total = dmg + tbl;
        total2 = qty - dmg;
        
        remain.setText(Integer.toString(total));
        sandh.setText(Integer.toString(total2));
        
        try {
            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("UPDATE damage_tbl SET Status=?, Quantity=?, "
                    + "Encoder=?, Date=?, Time=? WHERE ID='" + so_id.getText() + "'");
            
            pst.setString(1, (String) so_stat.getSelectedItem());
            pst.setString(2, remain.getText());
            pst.setString(3, txt_name.getText());
            pst.setString(4, txt_date1.getText());
            pst.setString(5, txt_time.getText());
            
            pst.execute();
            JOptionPane.showMessageDialog(null, "Data Updated");
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        
        try {
            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("UPDATE holding_tbl SET On_Hand=?, "
                    + " Damage=? WHERE ID='" + so_id.getText() + "'");
            
            pst.setString(1, sandh.getText());;
            pst.setString(2, remain.getText());;
            
            pst.execute();
            JOptionPane.showMessageDialog(null, "Data Updated");
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        
        try {
            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("UPDATE stockin_tbl SET Quantity=? "
                    + " WHERE Book_ID='" + so_id.getText() + "'");
            
            pst.setString(1, sandh.getText());;
            
            pst.execute();
            JOptionPane.showMessageDialog(null, "Data Updated");
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        
        try {
            String sql = "Insert into invlog_tbl (ISBN_No, Book_title, Classification, Encoder, Date, Time, Action) "
                    + " values (?,?,?,?,?,?,?)";

            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement(sql);
            
            pst.setString(1, so_isbn.getText());
            pst.setString(2, so_bt.getText());
            pst.setString(3, so_class.getText());
            pst.setString(4, txt_name.getText());
            pst.setString(5, txt_date1.getText());
            pst.setString(6, txt_time.getText());
            pst.setString(7, "Stock-out");

            pst.execute(); 
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        all_ref();
        
        so_isbn.setText("");
        so_bt.setText("");
        so_class.setText("");
        so_stat.setSelectedItem("--Select Status--");
        so_qty.setText("0");
        so_id.setText("");
        sominus_test.setText("0");
        frm_tbl.setText("0");
        remain.setText("");
        sandh.setText("");
    }//GEN-LAST:event_so_saveActionPerformed

    private void so_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_so_tableMouseClicked
        int z = so_table.getSelectedRow();

            TableModel model = (TableModel)so_table.getModel();
          so_id.setText(model.getValueAt(z, 0).toString());
          so_isbn.setText(model.getValueAt(z, 1).toString());
          so_bt.setText(model.getValueAt(z, 2).toString());
          so_class.setText(model.getValueAt(z, 3).toString());
          so_stat.setSelectedItem(model.getValueAt(z, 4).toString());
          //so_qty.setText(model.getValueAt(z, 5).toString());
          frm_tbl.setText(model.getValueAt(z, 5).toString());
          so_enc.setText(model.getValueAt(z, 6).toString());
          so_date.setText(model.getValueAt(z, 7).toString());
          so_time.setText(model.getValueAt(z, 8).toString());
          
          so_stat.setEnabled(true);
          so_save.setEnabled(true);
          
          try{
            String sql="SELECT Quantity FROM stockin_tbl where Book_ID = '" + (String) so_id.getText() + "' ";
            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();
        
            if(rs.next()){
            String name =rs.getString("Quantity");
            sominus_test.setText(name);
            }
            }
            catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            }
    }//GEN-LAST:event_so_tableMouseClicked

    private void so_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_so_searchKeyReleased
        try {
           String sql = "SELECT *"
                    + " FROM damage_tbl WHERE "
                    + "ID like ? or ISBN_No like ? or Book_title like ? or Classification like ? or Status like ?"
                   + " or Quantity like ? or Encoder like ? or Date like ? or Time like?";

            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, "%" + so_search.getText() + "%");
            pst.setString(2, "%" + so_search.getText() + "%");
            pst.setString(3, "%" + so_search.getText() + "%");
            pst.setString(4, "%" + so_search.getText() + "%");
            pst.setString(5, "%" + so_search.getText() + "%");
            pst.setString(6, "%" + so_search.getText() + "%");
            pst.setString(7, "%" + so_search.getText() + "%");
            pst.setString(8, "%" + so_search.getText() + "%");
            pst.setString(9, "%" + so_search.getText() + "%");

            rs = (ResultSet) pst.executeQuery();
            so_table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, ex);
        }
    }//GEN-LAST:event_so_searchKeyReleased

    private void nb_new3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nb_new3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nb_new3ActionPerformed

    private void sum_classPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_sum_classPopupMenuWillBecomeInvisible
        drp_list();
    }//GEN-LAST:event_sum_classPopupMenuWillBecomeInvisible

    private void sum_btKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sum_btKeyReleased
        
        try {
            
           String sql = "SELECT ISBN_No, Book_title, Classification, On_Hand, Borrowed, Damage, Total_Holding"
                    + " FROM holding_tbl WHERE "
                    + "Book_title like ? ";

            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, "%" + sum_bt.getText() + "%");
            
            rs = (ResultSet) pst.executeQuery();
            sum_table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, ex);
        }
        //drp_list();
    }//GEN-LAST:event_sum_btKeyReleased

    private void log_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_log_tableMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_log_tableMouseClicked

    private void invlog_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_invlog_searchKeyReleased
        try {
           String sql = "SELECT *"
                    + " FROM invlog_tbl WHERE "
                    + "ISBN_No like ? or Book_title like ? or Classification like ? or Encoder like ? or Date like ?"
                   + " or Time like ? or Action like ?";

            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, "%" + invlog_search.getText() + "%");
            pst.setString(2, "%" + invlog_search.getText() + "%");
            pst.setString(3, "%" + invlog_search.getText() + "%");
            pst.setString(4, "%" + invlog_search.getText() + "%");
            pst.setString(5, "%" + invlog_search.getText() + "%");
            pst.setString(6, "%" + invlog_search.getText() + "%");
            pst.setString(7, "%" + invlog_search.getText() + "%");

            rs = (ResultSet) pst.executeQuery();
            log_table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, ex);
        }
    }//GEN-LAST:event_invlog_searchKeyReleased

    private void brrd_fnKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_brrd_fnKeyReleased
//        if(brrd_fn.getText().equals("")){
//        brrd_fn.setEnabled(true);
//        brrd_stat.setEnabled(true);
//        }
//        else{
//        brrd_fn.setEnabled(false);
//        brrd_stat.setEnabled(false);
//        }
//
//        try{
//            String sql = "SELECT Library_ID, Full_Name, Status"
//                    + " FROM borrower_tbl WHERE "
//                    + "Library_ID like ?";
//
//            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
//            pst.setString(1, "%" + brrd_fn.getText() + "%");
//
//            rs = (ResultSet) pst.executeQuery();
//            brrd_rb_table.setModel(DbUtils.resultSetToTableModel(rs));
//            }
//            catch (SQLException ex) {
//            JOptionPane.showConfirmDialog(null, ex);
//            }
//        
//        try{
//            String sql="SELECT Library_ID, Full_Name, Status FROM borrower_tbl where Library_ID = '" + (String) brrd_fn.getText() + "' ";
//            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
//            rs = pst.executeQuery();
//        
//            if(rs.next()){
//            String name =rs.getString("Full_Name");
//            String name3 =rs.getString("Status");
//            
//            brrd_fn.setSelectedItem(name);
//            brrd_stat.setSelectedItem(name3);
//
//            }
//            }
//            catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e);
//            }
        
    }//GEN-LAST:event_brrd_fnKeyReleased

    private void brrd_rb_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_brrd_rb_tableMouseClicked
        int z = brrd_rb_table.getSelectedRow();

        TableModel model = (TableModel)brrd_rb_table.getModel();
        brrd_libid1.setText(model.getValueAt(z, 0).toString());
        jLabel190.setText(model.getValueAt(z, 1).toString());
        jLabel191.setText(model.getValueAt(z, 2).toString());
        brrd_stat.setSelectedItem(model.getValueAt(z, 3).toString());
        jLabel130.setText(model.getValueAt(z, 2).toString());
        
        brrd_fn.setText(jLabel191.getText()+", "+jLabel190.getText());
        if(brrd_stat.getSelectedItem().equals("Faculty")){
        brrd_fd.setText("5");
        }
        else if(brrd_stat.getSelectedItem().equals("Student")){
        brrd_fd.setText("5");
        }
    }//GEN-LAST:event_brrd_rb_tableMouseClicked

    private void brrd_btKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_brrd_btKeyReleased
        try{
            String sql = "SELECT Book_title, Price, Classification, Quantity"
                    + " FROM stockin_tbl WHERE "
                    + "Book_title like ?";

            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, "%" + brrd_bt.getText() + "%");

            rs = (ResultSet) pst.executeQuery();
            brrd_bl_table.setModel(DbUtils.resultSetToTableModel(rs));
            }
            catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, ex);
            }
        
         try{
            String sql="SELECT Book_title, Price, Classification, Quantity FROM stockin_tbl where Book_title = '" + (String) brrd_bt.getText() + "' ";
            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();
        
            if(rs.next()){
            String name =rs.getString("Price");
            String name3 =rs.getString("Classification");
            String name4 =rs.getString("Quantity");
            
            brrd_bp.setText(name);
            brrd_bqty.setText(name4);
            brrd_class.setSelectedItem(name3);

            }
            }
            catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            }
    }//GEN-LAST:event_brrd_btKeyReleased

    private void brrd_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brrd_addActionPerformed
        int a = Integer.parseInt(brrd_qty.getText());
        int aa = Integer.parseInt(brrd_bqty.getText());
            int total;
            
            if(a > aa){
            JOptionPane.showMessageDialog(null, "Limit Reach!");
            }
            else{
            total = a + 1;
            
            brrd_qty.setText(Integer.toString(total));
            brrd_qty.setForeground(Color.red);
            brrd_min.setEnabled(true);
            }
    }//GEN-LAST:event_brrd_addActionPerformed

    private void brrd_minActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brrd_minActionPerformed
        int b = Integer.parseInt(brrd_qty.getText());
        if(b <= 1){    
            JOptionPane.showMessageDialog(null, "Quantity Can't Be Zero!");
            }
            else {
            int aa = Integer.parseInt(brrd_qty.getText());
            int total;
            
            total = aa - 1;
            
            brrd_qty.setText(Integer.toString(total));
            brrd_qty.setForeground(Color.red);
            }
    }//GEN-LAST:event_brrd_minActionPerformed

    private void brrd_statPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_brrd_statPopupMenuWillBecomeInvisible
//        try {
//            String sql = "SELECT Library_ID, Full_Name, Status"
//                    + " FROM borrower_tbl WHERE "
//                    + "Status like ? ";
//
//            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
//            pst.setString(1, "%" + brrd_stat.getSelectedItem() + "%");
//
//            rs = (ResultSet) pst.executeQuery();
//            brrd_rb_table.setModel(DbUtils.resultSetToTableModel(rs));
//        } catch (SQLException ex) {
//            JOptionPane.showConfirmDialog(null, ex);
//        }
//        if(brrd_stat.getSelectedItem().equals("--Choose Status--")){
//        rb_ref();
//        }
    }//GEN-LAST:event_brrd_statPopupMenuWillBecomeInvisible

    private void brrd_bpKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_brrd_bpKeyReleased
        try{
            String sql = "SELECT Book_title, Price, Classification, Quantity"
                    + " FROM stockin_tbl WHERE "
                    + "Price like ?";

            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, "%" + brrd_bp.getText() + "%");

            rs = (ResultSet) pst.executeQuery();
            brrd_bl_table.setModel(DbUtils.resultSetToTableModel(rs));
            }
            catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, ex);
            }
    }//GEN-LAST:event_brrd_bpKeyReleased

    private void brrd_classPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_brrd_classPopupMenuWillBecomeInvisible
        try {
            String sql = "SELECT Book_title, Price, Classification, Quantity"
                    + " FROM stockin_tbl WHERE "
                    + "Classification like ?";

            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, "%" + brrd_class.getSelectedItem() + "%");

            rs = (ResultSet) pst.executeQuery();
            brrd_bl_table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, ex);
        }
        if(brrd_class.getSelectedItem().equals("--Select Classification--")){
        booklist_ref();
        }
    }//GEN-LAST:event_brrd_classPopupMenuWillBecomeInvisible

    private void si_calc1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_si_calc1ActionPerformed
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("GMT"));
        
        borroweddate.setText((String) getDate(cal));
        
        if(brrd_stat.getSelectedItem().equals("Faculty")){
            cal.add(Calendar.DATE, 7);
            returndate.setText((String) getDate(cal));
        }
        else if(brrd_stat.getSelectedItem().equals("Student")){
            cal.add(Calendar.DATE, 3);
            returndate.setText((String) getDate(cal));
        }
        
        int a = Integer.parseInt(brrd_bqty.getText()); 
        int b = Integer.parseInt(brrd_qty.getText());
        int total;
        
        total = a - b;
       
        brrd_total.setText(Integer.toString(total));
        
        try {
            String sql = "Insert into borrowed_tbl (Library_ID, Full_Name, Status, Book_title, Book_Price, Classification, "
                    + "Fines, Quantity, Borrowed_Date, Return_Date, Remarks, Payable) "
                    + " values (?,?,?,?,?,?,?,?,?,?,?,?)";

            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement(sql);
            
            pst.setString(1, brrd_libid1.getText());
            pst.setString(2, brrd_fn.getText());
            pst.setString(3, (String) brrd_stat.getSelectedItem());
            pst.setString(4, brrd_bt.getText());
            pst.setString(5, brrd_bp.getText());
            pst.setString(6, (String) brrd_class.getSelectedItem());
            pst.setString(7, brrd_fd.getText());
            pst.setString(8, brrd_qty.getText());
            pst.setString(9, borroweddate.getText());
            pst.setString(10, returndate.getText());
            pst.setString(11, (String) brrd_remcb.getSelectedItem());
            pst.setString(12, "0");

            pst.execute(); 
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
        try {
            String sql1 = "Insert into brrdlog_tbl (Full_Name, Status, Book_title, Classification, "
                    + " Borrowed_Date, Encoder, Date) "
                    + " values (?,?,?,?,?,?,?)";

            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement(sql1);
            
            pst.setString(1, brrd_fn.getText());
            pst.setString(2, (String) brrd_stat.getSelectedItem());
            pst.setString(3, brrd_bt.getText());
            pst.setString(4, (String) brrd_class.getSelectedItem());
            pst.setString(5, borroweddate.getText());
            pst.setString(6, txt_name.getText());
            pst.setString(7, txt_date1.getText());

            pst.execute(); 
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
        try {
            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("UPDATE stockin_tbl SET  "
                    + " Quantity=? WHERE Book_title='" + brrd_bt.getText() + "'");
            
            pst.setString(1, brrd_total.getText());
            
            pst.execute();
            JOptionPane.showMessageDialog(null, "Data Updated");
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        
        
        all_ref();
        brrd_clr();
    }//GEN-LAST:event_si_calc1ActionPerformed

    private void brrd_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brrd_clearActionPerformed
        brrd_clr();
        all_ref();
    }//GEN-LAST:event_brrd_clearActionPerformed

    private void brrd_bl_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_brrd_bl_tableMouseClicked
        int z = brrd_bl_table.getSelectedRow();

        TableModel model = (TableModel)brrd_bl_table.getModel();
        brrd_bt.setText(model.getValueAt(z, 0).toString());
        brrd_bp.setText(model.getValueAt(z, 1).toString());
        brrd_class.setSelectedItem(model.getValueAt(z, 2).toString());
        brrd_bqty.setText(model.getValueAt(z, 3).toString());
    }//GEN-LAST:event_brrd_bl_tableMouseClicked

    private void brrd_log_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_brrd_log_tableMouseClicked
        brrd_rem.setVisible(true);
        brrd_remcb.setVisible(true);
        brrd_uprem.setEnabled(true);
        
        int z = brrd_log_table.getSelectedRow();

        TableModel model = (TableModel)brrd_log_table.getModel();
        
        brrd_id.setText(model.getValueAt(z, 0).toString());
        brrd_fn.setText(model.getValueAt(z, 1).toString());
        brrd_fn.setText(model.getValueAt(z, 2).toString());
        brrd_stat.setSelectedItem(model.getValueAt(z, 3).toString());
        brrd_bt.setText(model.getValueAt(z, 4).toString());
        brrd_bp.setText(model.getValueAt(z, 5).toString());
        brrd_class.setSelectedItem(model.getValueAt(z, 6).toString());
        brrd_fd.setText(model.getValueAt(z, 7).toString());
        brrd_qty.setText(model.getValueAt(z, 8).toString());
        borroweddate.setText(model.getValueAt(z, 9).toString());
        returndate.setText(model.getValueAt(z, 10).toString());
        brrd_remcb.setSelectedItem(model.getValueAt(z, 11).toString());
        date1.setText(model.getValueAt(z, 12).toString());
        
        ((JTextField)dddd1.getDateEditor().getUiComponent()).setText(model.getValueAt(z, 9).toString());
        ((JTextField)dddd2.getDateEditor().getUiComponent()).setText(model.getValueAt(z, 10).toString());
        
        try{
            String sql="SELECT Book_title, On_Hand, Borrowed, Total_Holding FROM holding_tbl where Book_title= '" + (String) brrd_bt.getText() + "' ";
            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();
             if(rs.next()){
            String name =rs.getString("Book_title");
            String name3 =rs.getString("On_hand");
            String name4 =rs.getString("Borrowed");
            String name5 =rs.getString("Total_Holding");
            
            brrd_bt.setText(name);
            test1.setText(name3);
            test2.setText(name4);
            test3.setText(name5);
             }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            } 
        
        try{
        
            String sqll="SELECT Quantity FROM damage_tbl where Book_title= '" + (String) brrd_bt.getText() + "' ";
            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sqll);
            rs = pst.executeQuery();
             if(rs.next()){
            String name =rs.getString("Quantity");
            
            jLabel151.setText(name);
             }
        }
        
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            }  
        
        if(brrd_remcb.getSelectedItem().equals("Borrowed")){
            brrd_return.setEnabled(true);
            brrd_clear1.setEnabled(false);
            
            
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("M/dd/yyyy");
            LocalDate start = LocalDate.parse(borroweddate.getText(),dateTimeFormatter);
            LocalDate  end = LocalDate.parse(txt_date1.getText(),dateTimeFormatter);

            long daysElapsed = ChronoUnit.DAYS.between(start, end);
            jLabel129.setText(Long.toString(daysElapsed));
            
            int a = Integer.parseInt(jLabel129.getText());
            int b = Integer.parseInt(brrd_fd.getText());
            int total;
            
            total = a * b;
            
            jLabel129.setText(Double.toString(total));
                
                try {
            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("UPDATE borrowed_tbl SET  "
                    + " Remarks=?, Payable=? WHERE ID='" + brrd_id.getText() + "'");
            
            pst.setString(1, (String) brrd_remcb.getSelectedItem());
            pst.setString(2, jLabel129.getText());
            
            pst.execute();
            //JOptionPane.showMessageDialog(null, "Data Updated");
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }   
            if(date1.getText().equals("0") || date1.getText().equals("0.00")){
            brrd_return.setEnabled(true);
            brrd_clear1.setEnabled(false);
            }
            else {
            brrd_return.setEnabled(false);
            brrd_clear1.setEnabled(true);
            }   
        }
        
        else if(brrd_remcb.getSelectedItem().equals("Loss") || brrd_remcb.getSelectedItem().equals("Damage")){
            int b = Integer.parseInt(brrd_qty.getText());
            int bb = Integer.parseInt(brrd_bp.getText());
                int totalz;
                
                totalz = b * bb;
                
                brrd_fd2.setText(Double.toString(totalz));
            
        try {
            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("UPDATE borrowed_tbl SET  "
                    + " Remarks=?, Payable=? WHERE ID='" + brrd_id.getText() + "'");
            
            pst.setString(1, (String) brrd_remcb.getSelectedItem());
            pst.setString(2, brrd_fd2.getText());
            
            pst.execute();
            //JOptionPane.showMessageDialog(null, "Data Updated");
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
         
        if(date1.getText().equals("0") || date1.getText().equals("0.00")){
        brrd_clear1.setEnabled(false);
        }
        else {
        brrd_clear1.setEnabled(true);
        }
        }
        
        if(brrd_remcb.getSelectedItem().equals("Loss") || brrd_remcb.getSelectedItem().equals("Damage")){
            
            brrd_return.setEnabled(false);
        }
        
        brrd_rem.setVisible(true);
        brrd_remcb.setVisible(true);
        brrd_uprem.setEnabled(true);
        
    }//GEN-LAST:event_brrd_log_tableMouseClicked

    private void brrd_qtyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_brrd_qtyMouseClicked
        String res = JOptionPane.showInputDialog(this, "Input Quantity");
                brrd_qty.setText(res);
                 int a = Integer.parseInt(brrd_qty.getText()); 
               int aa = Integer.parseInt(brrd_bqty.getText());
            if(a > aa){
            JOptionPane.showMessageDialog(null, "Limit Reach!");
            brrd_qty.setText("0");
            }  
            else if (a <= aa){
            brrd_qty.setText(res);
            }
            else if (a <= 0){
            JOptionPane.showMessageDialog(null, "Variable Invalid!");
            brrd.setText("0");
            }
            else if (res.isEmpty()){
            brrd.setText("0");
            }
    }//GEN-LAST:event_brrd_qtyMouseClicked

    private void si_ohMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_si_ohMouseClicked
        String res = JOptionPane.showInputDialog(this, "Input Quantity","Continue",JOptionPane.OK_CANCEL_OPTION);
                si_oh.setText(res);
//            if(res.){
//            }
    }//GEN-LAST:event_si_ohMouseClicked

    private void so_qtyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_so_qtyMouseClicked
        String res = JOptionPane.showInputDialog(this, "Input Quantity");
                so_qty.setText(res);
               int a = Integer.parseInt(so_qty.getText()); 
               int aa = Integer.parseInt(sominus_test.getText());
            if(a > aa){
            JOptionPane.showMessageDialog(null, "Limit Reach!");
            so_qty.setText("0");
            }  
            else if (a <= aa){
            so_qty.setText(res);
            }
            else if (a <= 0){
            JOptionPane.showMessageDialog(null, "Variable Invalid!");
            so_qty.setText("0");
            }
            else if (res.isEmpty()){
            so_qty.setText("0");
            }
    }//GEN-LAST:event_so_qtyMouseClicked

    private void brrd_upremActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brrd_upremActionPerformed
        String remarks[] = {"Loss","Damage"};
        JComboBox cb = new JComboBox(remarks);
        
        int input;
        input = JOptionPane.showConfirmDialog(this,cb, "Select Remarks",JOptionPane.DEFAULT_OPTION);
        
        if(input == JOptionPane.OK_OPTION){
        String str = (String) cb.getSelectedItem();
        brrd_remcb.setSelectedItem(str);
        }
        
        
        if(brrd_remcb.getSelectedItem().equals("Loss") || brrd_remcb.getSelectedItem().equals("Damage")){
            int b = Integer.parseInt(brrd_qty.getText());
            int bb = Integer.parseInt(brrd_bp.getText());
                int totalz;
                
                totalz = b * bb;
                
                brrd_fd2.setText(Integer.toString(totalz));
            
        try {
            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("UPDATE borrowed_tbl SET  "
                    + " Remarks=?, Payable=? WHERE ID='" + brrd_id.getText() + "'");
            
            pst.setString(1, (String) brrd_remcb.getSelectedItem());
            pst.setString(2, brrd_fd2.getText());
            
            pst.execute();
            JOptionPane.showMessageDialog(null, "Data Updated");
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        }
        else {
        JOptionPane.showMessageDialog(null, "Please Check Return Date");
        }
        all_ref();
    }//GEN-LAST:event_brrd_upremActionPerformed

    private void brrd_returnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brrd_returnActionPerformed
    returnbor();
        all_ref();
        brrd_clr();
    }//GEN-LAST:event_brrd_returnActionPerformed

    private void men_poMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_men_poMouseClicked
        men_dash.setBackground(new Color(96,96,96));
        men_rec.setBackground(new Color(96,96,96));
        men_inv.setBackground(new Color(96,96,96));
        men_brrd.setBackground(new Color(96,96,96));
        men_brrr.setBackground(new Color(96,96,96));
        men_ua.setBackground(new Color(96,96,96));
        men_logout.setBackground(new Color(96,96,96));
        men_rep.setBackground(new Color(96,96,96));
        men_po.setBackground(new Color(255,255,255));
        menu_title.setText("PURCHASE ORDER");
        
        men_po.setForeground(new Color(96,96,96));
        men_rep.setForeground(new Color(255,255,255));
        men_dash.setForeground(new Color(255,255,255));
        men_rec.setForeground(new Color(255,255,255));
        men_inv.setForeground(new Color(255,255,255));
        men_brrd.setForeground(new Color(255,255,255));
        men_brrr.setForeground(new Color(255,255,255));
        men_ua.setForeground(new Color(255,255,255));
        men_logout.setForeground(new Color(255,255,255));
        
        dashboard.setVisible(false);
        newbook.setVisible(false);
        inventory.setVisible(false);
        Bookloan.setVisible(false);
        borrower.setVisible(false);
        useraccount.setVisible(false);
        report.setVisible(false);
        user_logo.setVisible(true);
        classification.setVisible(false);
        booklist.setVisible(false);
    }//GEN-LAST:event_men_poMouseClicked

    private void brrd_fn2PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_brrd_fn2PopupMenuWillBecomeInvisible
        if(brrd_fn2.getSelectedItem().equals("--Choose Borrower--")){
        all_ref();
        }
        else{
        try{
            String sql = "SELECT *"
                    + " FROM brrdlog_tbl WHERE "
                    + "Full_Name like ? ";

            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, "%" + brrd_fn2.getSelectedItem() + "%");

            rs = (ResultSet) pst.executeQuery();
            borrowedlog_table.setModel(DbUtils.resultSetToTableModel(rs));
            }
            catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, ex);
            }
        }
        payable_total();
    }//GEN-LAST:event_brrd_fn2PopupMenuWillBecomeInvisible

    private void txt_nameMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_nameMouseEntered
        txt_name.setForeground(new Color(57,167,252,225));
    }//GEN-LAST:event_txt_nameMouseEntered

    private void txt_nameMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_nameMouseExited
        txt_name.setForeground(Color.WHITE);
    }//GEN-LAST:event_txt_nameMouseExited

    private void txt_nameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_nameMouseClicked
        men_dash.setBackground(new Color(255,255,255));
        men_rec.setBackground(new Color(96,96,96));
        men_inv.setBackground(new Color(96,96,96));
        men_brrd.setBackground(new Color(96,96,96));
        men_brrr.setBackground(new Color(96,96,96));
        men_ua.setBackground(new Color(96,96,96));
        men_logout.setBackground(new Color(96,96,96));
        men_po.setBackground(new Color(96,96,96));
        menu_title.setText("MY ACCOUNT");
        men_po.setForeground(new Color(255,255,255));
        men_rep.setBackground(new Color(96,96,96));
        men_rep.setForeground(new Color(255,255,255));
        men_dash.setForeground(new Color(96,96,96));
        men_rec.setForeground(new Color(255,255,255));
        men_inv.setForeground(new Color(255,255,255));
        men_brrd.setForeground(new Color(255,255,255));
        men_brrr.setForeground(new Color(255,255,255));
        men_ua.setForeground(new Color(255,255,255));
        men_logout.setForeground(new Color(255,255,255));
        
        dashboard.setVisible(true);
        newbook.setVisible(false);
        inventory.setVisible(false);
        Bookloan.setVisible(false);
        borrower.setVisible(false);
        useraccount.setVisible(false);
        report.setVisible(false);
        user_logo.setVisible(false);
        classification.setVisible(false);
        
        booksummary.setVisible(false);
        account.setVisible(true);
        findbook.setVisible(false);
    }//GEN-LAST:event_txt_nameMouseClicked

    private void nb_new4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nb_new4ActionPerformed
        booksummary.setVisible(true);
        account.setVisible(false);
        findbook.setVisible(false);
    }//GEN-LAST:event_nb_new4ActionPerformed

    private void nb_new5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nb_new5ActionPerformed
        booksummary.setVisible(false);
        account.setVisible(false);
        findbook.setVisible(true);
    }//GEN-LAST:event_nb_new5ActionPerformed

    private void nb_new6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nb_new6ActionPerformed
        booksummary.setVisible(true);
        account.setVisible(false);
        findbook.setVisible(false);
    }//GEN-LAST:event_nb_new6ActionPerformed

    private void ohb_btKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ohb_btKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_ohb_btKeyReleased

    private void ohb_classKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ohb_classKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_ohb_classKeyReleased

    private void ohb_finesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ohb_finesKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_ohb_finesKeyReleased

    private void ohb_bdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ohb_bdKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_ohb_bdKeyReleased

    private void ohb_qtyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ohb_qtyKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_ohb_qtyKeyReleased

    private void ohb_rdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ohb_rdKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_ohb_rdKeyReleased

    private void ohn_remKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ohn_remKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_ohn_remKeyReleased

    private void ohb_payKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ohb_payKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_ohb_payKeyReleased

    private void ohb_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ohb_tableMouseClicked
        int z = ohb_table.getSelectedRow();

            TableModel model = (TableModel)ohb_table.getModel();
          ohb_bt.setText(model.getValueAt(z, 1).toString());
          ohb_class.setText(model.getValueAt(z, 2).toString());
          ohb_fines.setText(model.getValueAt(z, 3).toString());
          ohb_qty.setText(model.getValueAt(z, 4).toString());
          ohb_bd.setText(model.getValueAt(z, 5).toString());
          ohb_rd.setText(model.getValueAt(z, 6).toString());
          ohn_rem.setText(model.getValueAt(z, 7).toString());
          ohb_pay.setText(model.getValueAt(z, 8).toString());
          
          
    }//GEN-LAST:event_ohb_tableMouseClicked

    private void brrd_fd3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_brrd_fd3KeyReleased
        try{
            String sql = "SELECT *"
                    + " FROM brrdlog_tbl WHERE "
                    + "ID like ? or Full_Name like ? or Status like ? or Book_title like ? or Classification like ? or"
                    + " Borrowed_Date like ? or Encoder like ? or Date like ?";

            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, "%" + brrd_fd3.getText() + "%");
            pst.setString(2, "%" + brrd_fd3.getText() + "%");
            pst.setString(3, "%" + brrd_fd3.getText() + "%");
            pst.setString(4, "%" + brrd_fd3.getText() + "%");
            pst.setString(5, "%" + brrd_fd3.getText() + "%");
            pst.setString(6, "%" + brrd_fd3.getText() + "%");
            pst.setString(7, "%" + brrd_fd3.getText() + "%");
            pst.setString(8, "%" + brrd_fd3.getText() + "%");

            rs = (ResultSet) pst.executeQuery();
            borrowedlog_table.setModel(DbUtils.resultSetToTableModel(rs));
            }
            catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, ex);
            }
    }//GEN-LAST:event_brrd_fd3KeyReleased

    private void masb_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_masb_searchKeyReleased
        try{
            String sql = "SELECT ISBN_No, Book_title, Author, Quantity, Call_Number, Classification, Edition, Copy_Right_Year, Price"
                    + " FROM stockin_tbl WHERE "
                    + "ISBN_No like ? or Book_title like ? or Author like ? or Quantity like ? or Call_Number like ? or"
                    + " Classification like ? or Edition like ? or Copy_Right_Year like ? or Price like ?";

            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, "%" + masb_search.getText() + "%");
            pst.setString(2, "%" + masb_search.getText() + "%");
            pst.setString(3, "%" + masb_search.getText() + "%");
            pst.setString(4, "%" + masb_search.getText() + "%");
            pst.setString(5, "%" + masb_search.getText() + "%");
            pst.setString(6, "%" + masb_search.getText() + "%");
            pst.setString(7, "%" + masb_search.getText() + "%");
            pst.setString(8, "%" + masb_search.getText() + "%");
            pst.setString(9, "%" + masb_search.getText() + "%");

            rs = (ResultSet) pst.executeQuery();
            masb_table.setModel(DbUtils.resultSetToTableModel(rs));
            }
            catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, ex);
            }
    }//GEN-LAST:event_masb_searchKeyReleased

    private void acc_browse1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acc_browse1ActionPerformed
        JFileChooser file = new JFileChooser(jFileChooser1.getCurrentDirectory());
        file.setCurrentDirectory(new File(System.getProperty("user.home")));
        //filter the files'
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Image*","jpg","gif","png");
        file.addChoosableFileFilter(filter);
        int result = file.showSaveDialog(null);
        //if the user click on save jfilechooser
        if(result == JFileChooser.APPROVE_OPTION){
            File selectedFile = file.getSelectedFile();
            String path = selectedFile.getAbsolutePath();
            // image.setIcon(ResizeImage(path,null));
            my_image.setIcon(new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(my_image.getWidth(), my_image.getHeight(), Image.SCALE_DEFAULT)));
            imgpath = path;

        }else if(result  == JFileChooser.CANCEL_OPTION){
            JOptionPane.showMessageDialog(null,"No File Selected");
        }
    }//GEN-LAST:event_acc_browse1ActionPerformed

    private void acc_update1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acc_update1ActionPerformed
        if (imgpath1 != null){
            try{
                pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("UPDATE useraccount_tbl SET "
                    + " First_Name=?, Middle_Name=?, Surname=?, Username=?, "
                        + "Mobile_Number=?, Password=?, Photo=? "
                    + " WHERE ID ='" + idd.getText() + "'");
                InputStream img = new FileInputStream(new File(imgpath1));
                pst.setString(1, my_fn.getText());
                pst.setString(2, my_mnn.getText());
                pst.setString(3, my_sn.getText());
                pst.setString(4, my_user.getText());
                pst.setString(5, my_mn.getText());
                pst.setString(6, my_pass.getText());
                pst.setBlob(7, img);
                
                int update = pst.executeUpdate();
                JOptionPane.showMessageDialog(null,"Account Info Updated");
                
                all_ref();

            }catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }else{
            try{
                pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("UPDATE useraccount_tbl SET "
                    + " First_Name=?, Middle_Name=?, Surname=?, Username=?, "
                        + "Mobile_Number=?, Password=? "
                    + " WHERE ID ='" + idd.getText() + "'");
                pst.setString(1, my_fn.getText());
                pst.setString(2, my_mnn.getText());
                pst.setString(3, my_sn.getText());
                pst.setString(4, my_user.getText());
                pst.setString(5, my_mn.getText());
                pst.setString(6, my_pass.getText());
                
                int update = pst.executeUpdate();
                JOptionPane.showMessageDialog(null,"Account Info Updated");
                
                all_ref();

            }catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
            JOptionPane.showMessageDialog(null,"Please Check Entry!!!");
        }
        imgpath1=null;
    }//GEN-LAST:event_acc_update1ActionPerformed

    private void class_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_class_addActionPerformed
        jTabbedPane6.setEnabledAt(0, true);
        jTabbedPane6.setEnabledAt(1, false);
        jTabbedPane6.setEnabledAt(2, false);
        jTabbedPane6.setEnabledAt(3, false);
        jTabbedPane6.setEnabledAt(4, false);
        jTabbedPane6.setEnabledAt(5, false);
        jTabbedPane6.setSelectedIndex(0);
        si_save3.setVisible(true);

        menu_title.setText("ADD CLASSIFICATION");
        
        si_save3.setText("Back To New Book");
    }//GEN-LAST:event_class_addActionPerformed

    private void class_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_class_tableMouseClicked
        int z = class_table.getSelectedRow();

            TableModel model = (TableModel)class_table.getModel();
          class_num.setText(model.getValueAt(z, 0).toString());
          si_isbn1.setText(model.getValueAt(z, 1).toString());
          
          class_save_hide.setVisible(true);
          btn_del.setVisible(true);
    }//GEN-LAST:event_class_tableMouseClicked

    private void si_isbn1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_si_isbn1KeyReleased
        if(si_isbn1.getText().equals("")){
        class_num.setText("");
        class_save_hide.setVisible(false);
        btn_del.setVisible(false);
        }
        else{
        class_save_hide.setVisible(true);
        btn_del.setVisible(true);
        }
        
        try {
            String sql = "SELECT *"
                    + " FROM classification_tbl WHERE "
                    + "Classname like ?";

            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, "%" + si_isbn1.getText() + "%");


            rs = (ResultSet) pst.executeQuery();
            class_table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, ex);
        }
    }//GEN-LAST:event_si_isbn1KeyReleased

    private void si_save1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_si_save1ActionPerformed
        if(si_save2.getText().equals("Back To New Book")){
        if(class_num.getText().equals("")){
            try {
            String sql = "Insert into classification_tbl (Classname) values (?)";

            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement(sql);

            pst.setString(1, si_isbn1.getText());
            
            pst.execute();
            
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
                } 
            all_ref();
            class_save_hide.setVisible(false);
            classif_list();
            nb_class.setSelectedItem(si_isbn1.getText());
            si_isbn1.setText("");
        }
        else{
        try {
            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("UPDATE classification_tbl SET "
                    + "Classname=? WHERE Class_Number='" + class_num.getText() + "'");
            pst.setString(1, si_isbn1.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Data Updated");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
                } 
            all_ref();
            class_save_hide.setVisible(false);
            classif_list();
            nb_class.setSelectedItem(si_isbn1.getText());
            si_isbn1.setText("");
        }
        
        men_dash.setBackground(new Color(96,96,96));
        men_rec.setBackground(new Color(255,255,255));
        men_inv.setBackground(new Color(96,96,96));
        men_brrd.setBackground(new Color(96,96,96));
        men_brrr.setBackground(new Color(96,96,96));
        men_ua.setBackground(new Color(96,96,96));
        men_logout.setBackground(new Color(96,96,96));
        menu_title.setText("NEW BOOK");
        men_po.setForeground(new Color(255,255,255));
        men_po.setBackground(new Color(96,96,96));
        men_rep.setBackground(new Color(96,96,96));
        men_rep.setForeground(new Color(255,255,255));
        men_dash.setForeground(new Color(255,255,255));
        men_rec.setForeground(new Color(96,96,96));
        men_inv.setForeground(new Color(255,255,255));
        men_brrd.setForeground(new Color(255,255,255));
        men_brrr.setForeground(new Color(255,255,255));
        men_ua.setForeground(new Color(255,255,255));
        men_logout.setForeground(new Color(255,255,255));
        
        dashboard.setVisible(false);
        newbook.setVisible(true);
        inventory.setVisible(false);
        Bookloan.setVisible(false);
        borrower.setVisible(false);
        useraccount.setVisible(false);
        report.setVisible(false);
        user_logo.setVisible(false);
        classification.setVisible(false);
        }
    else if(si_save2.getText().equals("Back To P.O.")){
        if(class_num.getText().equals("")){
            try {
            String sql = "Insert into classification_tbl (Classname) values (?)";

            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement(sql);

            pst.setString(1, si_isbn1.getText());
            
            pst.execute();
            
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
                } 
            all_ref();
            class_save_hide.setVisible(false);
            classif_list();
            po_cl.setSelectedItem(si_isbn1.getText());
            si_isbn1.setText("");
        }
        else{
        try {
            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("UPDATE classification_tbl SET "
                    + "Classname=? WHERE Class_Number='" + class_num.getText() + "'");
            pst.setString(1, si_isbn1.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Data Updated");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
                } 
            all_ref();
            class_save_hide.setVisible(false);
            classif_list();
            po_cl.setSelectedItem(si_isbn1.getText());
            si_isbn1.setText("");
        }
            men_dash.setBackground(new Color(96,96,96));
            men_rec.setBackground(new Color(96,96,96));
            men_inv.setBackground(new Color(96,96,96));
            men_brrd.setBackground(new Color(96,96,96));
            men_brrr.setBackground(new Color(96,96,96));
            men_ua.setBackground(new Color(96,96,96));
            men_logout.setBackground(new Color(96,96,96));
            men_rep.setBackground(new Color(96,96,96));
            men_po.setBackground(new Color(255,255,255));
            menu_title.setText("PURCHASE ORDER");

            men_po.setForeground(new Color(96,96,96));
            men_rep.setForeground(new Color(255,255,255));
            men_dash.setForeground(new Color(255,255,255));
            men_rec.setForeground(new Color(255,255,255));
            men_inv.setForeground(new Color(255,255,255));
            men_brrd.setForeground(new Color(255,255,255));
            men_brrr.setForeground(new Color(255,255,255));
            men_ua.setForeground(new Color(255,255,255));
            men_logout.setForeground(new Color(255,255,255));

            dashboard.setVisible(false);
            newbook.setVisible(false);
            inventory.setVisible(false);
            Bookloan.setVisible(false);
            borrower.setVisible(false);
            useraccount.setVisible(false);
            report.setVisible(false);
            user_logo.setVisible(true);
            classification.setVisible(false);
            booklist.setVisible(false);
    }
    }//GEN-LAST:event_si_save1ActionPerformed

    private void si_save2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_si_save2ActionPerformed
        if(si_save2.getText().equals("Back To New Book")){
            men_dash.setBackground(new Color(96,96,96));
            men_rec.setBackground(new Color(255,255,255));
            men_inv.setBackground(new Color(96,96,96));
            men_brrd.setBackground(new Color(96,96,96));
            men_brrr.setBackground(new Color(96,96,96));
            men_ua.setBackground(new Color(96,96,96));
            men_logout.setBackground(new Color(96,96,96));
            menu_title.setText("NEW BOOK");
            men_po.setForeground(new Color(255,255,255));
            men_po.setBackground(new Color(96,96,96));
            men_rep.setBackground(new Color(96,96,96));
            men_rep.setForeground(new Color(255,255,255));
            men_dash.setForeground(new Color(255,255,255));
            men_rec.setForeground(new Color(96,96,96));
            men_inv.setForeground(new Color(255,255,255));
            men_brrd.setForeground(new Color(255,255,255));
            men_brrr.setForeground(new Color(255,255,255));
            men_ua.setForeground(new Color(255,255,255));
            men_logout.setForeground(new Color(255,255,255));

            dashboard.setVisible(false);
            newbook.setVisible(true);
            inventory.setVisible(false);
            Bookloan.setVisible(false);
            borrower.setVisible(false);
            useraccount.setVisible(false);
            report.setVisible(false);
            user_logo.setVisible(false);
            classification.setVisible(false);
        }
        else if(si_save2.getText().equals("Back To P.O.")){
            men_dash.setBackground(new Color(96,96,96));
            men_rec.setBackground(new Color(96,96,96));
            men_inv.setBackground(new Color(96,96,96));
            men_brrd.setBackground(new Color(96,96,96));
            men_brrr.setBackground(new Color(96,96,96));
            men_ua.setBackground(new Color(96,96,96));
            men_logout.setBackground(new Color(96,96,96));
            men_rep.setBackground(new Color(96,96,96));
            men_po.setBackground(new Color(255,255,255));
            menu_title.setText("PURCHASE ORDER");

            men_po.setForeground(new Color(96,96,96));
            men_rep.setForeground(new Color(255,255,255));
            men_dash.setForeground(new Color(255,255,255));
            men_rec.setForeground(new Color(255,255,255));
            men_inv.setForeground(new Color(255,255,255));
            men_brrd.setForeground(new Color(255,255,255));
            men_brrr.setForeground(new Color(255,255,255));
            men_ua.setForeground(new Color(255,255,255));
            men_logout.setForeground(new Color(255,255,255));

            dashboard.setVisible(false);
            newbook.setVisible(false);
            inventory.setVisible(false);
            Bookloan.setVisible(false);
            borrower.setVisible(false);
            useraccount.setVisible(false);
            report.setVisible(false);
            user_logo.setVisible(true);
            classification.setVisible(false);
            booklist.setVisible(false);
        }
        
    }//GEN-LAST:event_si_save2ActionPerformed

    private void si_table1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_si_table1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_si_table1MouseClicked

    private void nb_new7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nb_new7ActionPerformed
        if(brr_fn7.getText().equals("") || brr_fn7.getText().equals("0")){
        nb_update4.setEnabled(true);
        po_date.setEnabled(true);
        po_sup.setEnabled(true);
        po_il.setEnabled(true);
        po_ne.setEnabled(true);
        po_cl.setEnabled(true);
        class_add1.setEnabled(true);
        po_pr.setEnabled(true);
        po_qt.setEnabled(true);
        po_add.setEnabled(true);
        po_min.setEnabled(true);
        nb_new7.setEnabled(false);
        nb_update5.setEnabled(true);
        class_add3.setEnabled(true);
        
        po_clr();
        
        po_or.setText("Generate PO Number");
        po_date.setDate(null);
        }
        
        else{
//        int p = JOptionPane.showConfirmDialog(null, "Do you want to save PO transaction?","Save",JOptionPane.YES_NO_OPTION);
//        if(p==0){
       JOptionPane.showMessageDialog(null, "Save Current Transaction first!!!");
//        }
        }
        
        
    }//GEN-LAST:event_nb_new7ActionPerformed

    private void nb_save1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nb_save1ActionPerformed
        int i = Integer.parseInt(po_qt.getText());
        if(po_bt.getText().equals("") && i<=0){
        JOptionPane.showMessageDialog(null, "Please Fill Properly");
        }
        else{
        if(jLabel115.getText().equals("1")){
        po_order();
        polog();
        try {
                String sql = "Insert into requested_tbl (Book_title, Author, Classification, Quantity, Price, "
                        + "Purchase_No, Date, Station)"
                        + " values (?,?,?,?,?,?,?,?)";

                pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement(sql);

                pst.setString(1, po_bt.getText());
                pst.setString(2, po_au.getText());
                pst.setString(3, (String) po_cl.getSelectedItem());
                pst.setString(4, po_qt.getText());
                pst.setString(5, po_pr.getText());
                pst.setString(6, po_or.getText());
                pst.setString(7, txt_date1.getText());
                pst.setString(8, jLabel76.getText());
                
                pst.execute();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
            }
        po2_clr();
        }
        else if(jLabel115.getText().equals("2")){
        po_order();
        polog();
        try {
                String sql = "Insert into requested_tbl (Book_title, Author, Classification, Quantity, Price, "
                        + "Purchase_No, Date, Station)"
                        + " values (?,?,?,?,?,?,?,?)";

                pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement(sql);

                pst.setString(1, po_bt.getText());
                pst.setString(2, po_au.getText());
                pst.setString(3, (String) po_cl.getSelectedItem());
                pst.setString(4, po_qt.getText());
                pst.setString(5, po_pr.getText());
                pst.setString(6, po_or.getText());
                pst.setString(7, txt_date1.getText());
                pst.setString(8, jLabel76.getText());
                
                pst.execute();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
            }
        po2_clr();
        }
        else {
            JOptionPane.showMessageDialog(null, "Check Input(s)");
        }
        all_ref();
        tb_total();
        tc_total();
        po2_clr();
        po_sup.setEnabled(false);
        class_add3.setEnabled(false);
        po_date.setEnabled(false);
        }
    }//GEN-LAST:event_nb_save1ActionPerformed

    private void nb_update1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nb_update1ActionPerformed
        int i = Integer.parseInt(po_qt.getText());
        if(po_bt.getText().equals("") && i<=0){
        JOptionPane.showMessageDialog(null, "Please Fill Properly");
        }
        else{
        
        double a = Double.parseDouble(po_pr.getText());
        double b = Double.parseDouble(po_qt.getText());
        double c;
        if(b<=0){
        ttt.setText(Double.toString(a));
        }
        else{
        c = a * b;
        ttt.setText(Double.toString(c));
        }
        try {
            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("UPDATE order_tbl SET "
                    + "Supplier=?, Book_Title=?, Book_Author=?, Classification=?, Price=?, Quantity=?, Total=?"
                    + " WHERE No='" + jLabel121.getText() + "'");

            pst.setString(1, (String) po_sup.getSelectedItem());
            pst.setString(2, po_bt.getText());
            pst.setString(3, po_au.getText());
            pst.setString(4, (String) po_cl.getSelectedItem());
            pst.setString(5, po_pr.getText());
            pst.setString(6, po_qt.getText());
            pst.setString(7, ttt.getText());
            
            pst.execute();
            JOptionPane.showMessageDialog(null, "Data Updated");
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        
        try {
            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("UPDATE po_log_tbl SET "
                    + "Supplier=?, Book_Title=?, Book_Author=?, Classification=?, Price=?, Quantity=?, Total=?"
                    + " WHERE Purchase_code='" + code.getText() + "'");

            pst.setString(1, (String) po_sup.getSelectedItem());
            pst.setString(2, po_bt.getText());
            pst.setString(3, po_au.getText());
            pst.setString(4, (String) po_cl.getSelectedItem());
            pst.setString(5, po_pr.getText());
            pst.setString(6, po_qt.getText());
            pst.setString(7, ttt.getText());
            
            pst.execute();
            JOptionPane.showMessageDialog(null, "Data Updated");
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        
        
        all_ref();
        tb_total();
        tc_total();
        po_clr();
        }
    }//GEN-LAST:event_nb_update1ActionPerformed

    private void po_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_po_tableMouseClicked
        int z = po_table.getSelectedRow();

            TableModel model = (TableModel)po_table.getModel();
          jLabel121.setText(model.getValueAt(z, 0).toString());
          jLabel122.setText(model.getValueAt(z, 1).toString());
          po_sup.setSelectedItem(model.getValueAt(z, 4).toString());
          po_bt.setText(model.getValueAt(z, 5).toString());
          po_au.setText(model.getValueAt(z, 6).toString());
          po_cl.setSelectedItem(model.getValueAt(z, 7).toString());
          po_pr.setText(model.getValueAt(z, 8).toString());
          po_qt.setText(model.getValueAt(z, 9).toString());
          ttt.setText(model.getValueAt(z, 10).toString());
          
          code.setText(jLabel121.getText()+jLabel122.getText());
          
          po_sup.setEnabled(true);
          po_il.setEnabled(true);
          po_ne.setEnabled(true);
          po_bt.setEnabled(true);
          po_au.setEnabled(true);
          po_cl.setEnabled(true);
          class_add1.setEnabled(true);
          po_pr.setEnabled(true);
          po_qt.setEnabled(true);
          po_add.setEnabled(true);
          po_min.setEnabled(true);
          nb_update1.setEnabled(true);
          nb_update2.setEnabled(true);
    }//GEN-LAST:event_po_tableMouseClicked

    private void nb_update2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nb_update2ActionPerformed
        try {

            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("DELETE FROM order_tbl  WHERE No = '" + jLabel121.getText() + "'");
            int del = pst.executeUpdate();
            if (del > 0) {
                JOptionPane.showMessageDialog(null, "Data Deleted!");
                
            }else{
                JOptionPane.showMessageDialog(null, "Please Check Item!!");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        
        try {

            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("DELETE FROM po_log_tbl  WHERE Purchase_code = '" + code.getText() + "'");
            int del = pst.executeUpdate();
            if (del > 0) {
                JOptionPane.showMessageDialog(null, "Data Deleted!");
                
            }else{
                JOptionPane.showMessageDialog(null, "Please Check Item!!");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        
        all_ref();
        tb_total();
        tc_total();
        po_clr();
    }//GEN-LAST:event_nb_update2ActionPerformed

    private void pobl_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pobl_tableMouseClicked
        int z = pobl_table.getSelectedRow();

        TableModel model = (TableModel)pobl_table.getModel();
        po_bt.setText(model.getValueAt(z, 0).toString());
        po_au.setText(model.getValueAt(z, 2).toString());
        po_cl.setSelectedItem(model.getValueAt(z, 1).toString());
        po_pr.setText(model.getValueAt(z, 3).toString());
        
        jLabel115.setText("2");
    }//GEN-LAST:event_pobl_tableMouseClicked

    private void nb_update3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nb_update3ActionPerformed
        men_dash.setBackground(new Color(96,96,96));
        men_rec.setBackground(new Color(96,96,96));
        men_inv.setBackground(new Color(255,255,255));
        men_brrd.setBackground(new Color(96,96,96));
        men_brrr.setBackground(new Color(96,96,96));
        men_ua.setBackground(new Color(96,96,96));
        men_logout.setBackground(new Color(96,96,96));
        men_rep.setBackground(new Color(96,96,96));
        men_po.setBackground(new Color(96,96,96));
        menu_title.setText("INVENTORY");
        
        men_po.setForeground(new Color(255,255,255));
        men_rep.setForeground(new Color(255,255,255));
        men_dash.setForeground(new Color(255,255,255));
        men_rec.setForeground(new Color(255,255,255));
        men_inv.setForeground(new Color(96,96,96));
        men_brrd.setForeground(new Color(255,255,255));
        men_brrr.setForeground(new Color(255,255,255));
        men_ua.setForeground(new Color(255,255,255));
        men_logout.setForeground(new Color(255,255,255));
        
        dashboard.setVisible(false);
        newbook.setVisible(false);
        inventory.setVisible(true);
        Bookloan.setVisible(false);
        borrower.setVisible(false);
        useraccount.setVisible(false);
        report.setVisible(false);
        user_logo.setVisible(false);
        classification.setVisible(false);
        booklist.setVisible(false);
    }//GEN-LAST:event_nb_update3ActionPerformed

    private void po_ilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_po_ilActionPerformed
        men_dash.setBackground(new Color(96,96,96));
        men_rec.setBackground(new Color(96,96,96));
        men_inv.setBackground(new Color(255,255,255));
        men_brrd.setBackground(new Color(96,96,96));
        men_brrr.setBackground(new Color(96,96,96));
        men_ua.setBackground(new Color(96,96,96));
        men_logout.setBackground(new Color(96,96,96));
        men_rep.setBackground(new Color(96,96,96));
        men_po.setBackground(new Color(96,96,96));
        menu_title.setText("BOOK LIST");
        
        men_po.setForeground(new Color(255,255,255));
        men_rep.setForeground(new Color(255,255,255));
        men_dash.setForeground(new Color(255,255,255));
        men_rec.setForeground(new Color(255,255,255));
        men_inv.setForeground(new Color(96,96,96));
        men_brrd.setForeground(new Color(255,255,255));
        men_brrr.setForeground(new Color(255,255,255));
        men_ua.setForeground(new Color(255,255,255));
        men_logout.setForeground(new Color(255,255,255));
        
        dashboard.setVisible(false);
        newbook.setVisible(false);
        inventory.setVisible(false);
        Bookloan.setVisible(false);
        borrower.setVisible(false);
        useraccount.setVisible(false);
        report.setVisible(false);
        user_logo.setVisible(false);
        classification.setVisible(false);
        booklist.setVisible(true);
        
        jLabel76.setText("Existing");
        po_cl.setEnabled(false);
        class_add1.setEnabled(false);
        po_pr.setEditable(false);
    }//GEN-LAST:event_po_ilActionPerformed

    private void nb_update4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nb_update4ActionPerformed
        if(rc_po.getText().equals("0")){
            all_ref();
            try {
                String sql = "alter table po_num_tbl AUTO_INCREMENT = 1";
                pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement(sql);
                pst.execute();
            
                } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
                } 
            try {
                String sql = "Insert into po_num_tbl (Purchase_No) values (?)";

                pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement(sql);

                pst.setString(1, "PO-1");
                
                pst.execute();
                po_or.setText("PO-1");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
            }
            all_ref();
        }
        else{
            all_ref();
            termd();
        int b;
        int a = Integer.parseInt(dsc_id.getText());
        b = a + 1;
        rc_tot.setText(Integer.toString(b));
        po_or.setText("PO-" + rc_tot.getText());
        try {
                String sql = "Insert into po_num_tbl (Purchase_No) values (?)";

                pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement(sql);

                pst.setString(1, po_or.getText());
                
                pst.execute();
                //po_or.setText("PO-1");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
            }
        all_ref();
        }
        nb_update4.setEnabled(false);
        nb_save1.setEnabled(true);
    }//GEN-LAST:event_nb_update4ActionPerformed

    private void po_neActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_po_neActionPerformed
        String res = JOptionPane.showInputDialog(this, "Book Title");
        po_bt.setText(res);

        String res2 = JOptionPane.showInputDialog(this, "Author");
        po_au.setText(res2);
        
        if(po_bt.getText().equals("") && po_au.getText().equals("")){
        jLabel115.setText("0");
        }
        else if(po_bt.getText().equals("")){
        jLabel115.setText("0");
        }
        else{
        jLabel115.setText("1");
        }
        jLabel76.setText("New");
        po_cl.setEnabled(true);
        class_add1.setEnabled(true);
        po_pr.setEditable(true);
        po_pr.setText("");
    }//GEN-LAST:event_po_neActionPerformed

    private void class_add1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_class_add1ActionPerformed
        jTabbedPane6.setEnabledAt(0, true);
        jTabbedPane6.setEnabledAt(1, false);
        jTabbedPane6.setEnabledAt(2, false);
        jTabbedPane6.setEnabledAt(3, false);
        jTabbedPane6.setEnabledAt(4, false);
        jTabbedPane6.setEnabledAt(5, false);
        jTabbedPane6.setSelectedIndex(0);
        si_save3.setVisible(true);
        
        menu_title.setText("ADD CLASSIFICATION");
        
        dashboard.setVisible(false);
        Records.setVisible(true);
        inventory.setVisible(false);
        Bookloan.setVisible(false);
        borrower.setVisible(false);
        report.setVisible(false);
        
        si_save3.setText("Back To P.O.");
    }//GEN-LAST:event_class_add1ActionPerformed

    private void btn_delActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_delActionPerformed
         try {

            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("DELETE FROM classification_tbl  WHERE Class_Number = '" + class_num.getText() + "'");
            int del = pst.executeUpdate();
            if (del > 0) {
                JOptionPane.showMessageDialog(null, "Data Deleted!");
                
            }else{
                JOptionPane.showMessageDialog(null, "Please Check Item!!");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
         all_ref();
         class_num.setText("");
         si_isbn1.setText("");
    }//GEN-LAST:event_btn_delActionPerformed

    private void nb_update5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nb_update5ActionPerformed
        po_clr();
        nb_save1.setEnabled(false);
    }//GEN-LAST:event_nb_update5ActionPerformed

    private void po_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_po_addActionPerformed
            int c = Integer.parseInt(po_qt.getText());
            int tot;
            tot = c + 1;
            
            po_qt.setText(Integer.toString(tot));
    }//GEN-LAST:event_po_addActionPerformed

    private void po_minActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_po_minActionPerformed
            if(po_qt.getText().equals("0")){
            JOptionPane.showMessageDialog(null, "Quantity Can't Be Zero!");
            po_qt.setText("1");
            }
            else{
            int c = Integer.parseInt(po_qt.getText());
            int tot;
            tot = c - 1;
            po_qt.setText(Integer.toString(tot));
            }
    }//GEN-LAST:event_po_minActionPerformed

    private void po_or1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_po_or1KeyReleased
        try {
           String sql = "SELECT Book_title, Classification, Author "
                    + " FROM stockin_tbl WHERE "
                    + "Book_title like ? or Classification like ? or Author like ?";

            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, "%" + po_or1.getText() + "%");
            pst.setString(2, "%" + po_or1.getText() + "%");
            pst.setString(3, "%" + po_or1.getText() + "%");

            rs = (ResultSet) pst.executeQuery();
            pobl_table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, ex);
        }
    }//GEN-LAST:event_po_or1KeyReleased

    private void sup_table1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sup_table1MouseClicked
        int z = sup_table1.getSelectedRow();

            TableModel model = (TableModel)sup_table1.getModel();
          //jLabel120.setText(model.getValueAt(z, 0).toString());
          sup_name1.setText(model.getValueAt(z, 0).toString());
          sup_add1.setText(model.getValueAt(z, 1).toString());
          
          try {
            pst = (PreparedStatement) conn.prepareStatement("SELECT ID FROM publisher_tbl where Publisher_ID= '" + sup_name1.getText() + "' and Publisher= '" + sup_add1.getText() + "' ");
            rs = pst.executeQuery();
            if(rs.next()){
                String add = rs.getString("ID");
                jLabel120.setText(add);
		}
            }
            catch (SQLException x) {
        
            }
          
          sup_update1.setEnabled(true);
          sup_delete1.setEnabled(true);
          sup_save1.setEnabled(false);
    }//GEN-LAST:event_sup_table1MouseClicked

    private void sup_search1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sup_search1KeyReleased
        try {
           String sql = "SELECT Publisher_ID, Publisher"
                    + " FROM publisher_tbl WHERE "
                    + "Publisher_ID like ? or Publisher like ?";

            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, "%" + sup_search1.getText() + "%");
            pst.setString(2, "%" + sup_search1.getText() + "%");

            rs = (ResultSet) pst.executeQuery();
            sup_table1.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, ex);
        }
        
        if(sup_search1.getText().equals("")){
        all_ref();
        }
    }//GEN-LAST:event_sup_search1KeyReleased

    private void sup_name1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sup_name1KeyReleased
        if(sup_name1.getText().equals("") && sup_add1.getText().equals("")){
        sup_save1.setEnabled(false);
        }
        else{
        sup_save1.setEnabled(true);
        }
    }//GEN-LAST:event_sup_name1KeyReleased

    private void sup_add1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_sup_add1KeyReleased
        if(sup_name1.getText().equals("") && sup_add1.getText().equals("")){
        sup_save1.setEnabled(false);
        }
        else{
        sup_save1.setEnabled(true);
        }
    }//GEN-LAST:event_sup_add1KeyReleased

    private void class_add2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_class_add2ActionPerformed
        jTabbedPane6.setEnabledAt(0, false);
        jTabbedPane6.setEnabledAt(1, true);
        jTabbedPane6.setEnabledAt(2, false);
        jTabbedPane6.setEnabledAt(3, false);
        jTabbedPane6.setEnabledAt(4, false);
        jTabbedPane6.setEnabledAt(5, false);
        jTabbedPane6.setSelectedIndex(1);
        
        sup_save2.setVisible(true);
        jLabel128.setText("1");

        menu_title.setText("NEW PUBLISHER");

    }//GEN-LAST:event_class_add2ActionPerformed

    private void sup_save1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sup_save1ActionPerformed
        try {
            String sql = "Insert into publisher_tbl (Publisher_ID, Publisher) "
                    + " values (?,?)";

            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement(sql);
            
            pst.setString(1, sup_name1.getText());
            pst.setString(2, sup_add1.getText());

            pst.execute(); 
            all_ref();
        pub_list();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
        if(jLabel128.getText().equals("1")){
            nb_cpub.setSelectedItem(sup_add1.getText());
            pub_ret();
        } 

        all_ref();
        //pub_list();
        sup_name1.setText("");
        sup_add1.setText("");
    }//GEN-LAST:event_sup_save1ActionPerformed

    private void sup_update1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sup_update1ActionPerformed
        try {
            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("UPDATE publisher_tbl SET Publisher_ID=?, Publisher=? WHERE ID='" + jLabel120.getText() + "'");

            pst.setString(1, sup_name1.getText());
            pst.setString(2, sup_add1.getText());
            
            pst.execute();
            JOptionPane.showMessageDialog(null, "Data Updated");
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        all_ref();
        pub_list();
        sup_name1.setText("");
        sup_add1.setText("");
        sup_update1.setEnabled(false);
        sup_delete1.setEnabled(false);
        sup_save1.setEnabled(false);
    }//GEN-LAST:event_sup_update1ActionPerformed

    private void sup_delete1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sup_delete1ActionPerformed
        try {

            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("DELETE FROM publisher_tbl  WHERE ID = '" + jLabel120.getText() + "'");
            int del = pst.executeUpdate();
            if (del > 0) {
                JOptionPane.showMessageDialog(null, "Data Deleted!");
                
            }else{
                JOptionPane.showMessageDialog(null, "Please Check Item!!");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        all_ref();
        pub_list();
        sup_name1.setText("");
        sup_add1.setText("");
        sup_update1.setEnabled(false);
        sup_delete1.setEnabled(false);
        sup_save1.setEnabled(false);
    }//GEN-LAST:event_sup_delete1ActionPerformed

    private void nb_update7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nb_update7ActionPerformed
  
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
        all_ref();
        tb_total();
        tc_total();
        po_clr();
        nb_update7.setEnabled(false);
        nb_update2.setEnabled(false);
        nb_update5.setEnabled(false);
        nb_save1.setEnabled(false);
        nb_update1.setEnabled(false);
        po_sup.setEnabled(false);
        po_il.setEnabled(false);
        po_ne.setEnabled(false);
        po_cl.setEnabled(false);
        class_add1.setEnabled(false);
        po_pr.setEnabled(false);
        po_qt.setEnabled(false);
        po_add.setEnabled(false);
        po_min.setEnabled(false);
        nb_new7.setEnabled(true);
        brr_fn7.setText("");
        brr_fn8.setText("");
        brr_fn9.setText("");
        po_qt.setText("1");
    }//GEN-LAST:event_nb_update7ActionPerformed

    private void nb_table1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nb_table1MouseClicked
        int z = nb_table1.getSelectedRow();

            TableModel model = (TableModel)nb_table1.getModel();
          nb_id.setText(model.getValueAt(z, 0).toString());
          nb_bt.setText(model.getValueAt(z, 1).toString());
          nb_aut.setText(model.getValueAt(z, 2).toString());
          nb_class.setSelectedItem(model.getValueAt(z, 3).toString());
          nb_qty.setText(model.getValueAt(z, 4).toString());
          nb_prc.setText(model.getValueAt(z, 5).toString());
          
          nb_save.setEnabled(true);
          nb_class.setEnabled(false);
          class_add.setEnabled(false);
          nb_new.setEnabled(false);
          
          nb_is.setEnabled(true);
          nb_cn.setEnabled(true);
          nb_edi.setEnabled(true);
          nb_cry.setEnabled(true);
          nb_da.setEnabled(true);
          nb_bt.setEnabled(true);
          nb_aut.setEnabled(true);
          nb_qty.setEnabled(true);
          nb_class.setEnabled(true);
          class_add.setEnabled(true);
          nb_cpub.setEnabled(true);
          class_add2.setEnabled(true);
          nb_prc.setEnabled(true);
    }//GEN-LAST:event_nb_table1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try{
            String str = ""+jTextArea1.getText()+"";
            String str2 = str.replaceAll("\\s", "+");
            URL url = new URL ("http://192.168.0.15:8080/v1/sms/send/?phone="+jTextField1.getText()+"&message="+str2+"");
            InputStream i = null;
            JOptionPane.showMessageDialog(null, "Message Successfully Sent");

            try{
                i = url.openStream();
            }
            catch (Exception ex){
            }
            if(i != null){

            }
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void sup_save2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sup_save2ActionPerformed
        pub_ret();
    }//GEN-LAST:event_sup_save2ActionPerformed

    private void menu_title1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu_title1MouseEntered
        menu_title1.setForeground(new Color(57,167,252,225));
    }//GEN-LAST:event_menu_title1MouseEntered

    private void menu_title1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu_title1MouseExited
        menu_title1.setForeground(Color.WHITE);
    }//GEN-LAST:event_menu_title1MouseExited

    private void menu_title1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu_title1MouseClicked
        men_dash.setBackground(new Color(255,255,255));
        men_rec.setBackground(new Color(96,96,96));
        men_inv.setBackground(new Color(96,96,96));
        men_brrd.setBackground(new Color(96,96,96));
        men_brrr.setBackground(new Color(96,96,96));
        men_ua.setBackground(new Color(96,96,96));
        men_logout.setBackground(new Color(96,96,96));
        men_po.setBackground(new Color(96,96,96));
        menu_title.setText("MY ACCOUNT");
        men_po.setForeground(new Color(255,255,255));
        men_rep.setBackground(new Color(96,96,96));
        men_rep.setForeground(new Color(255,255,255));
        men_dash.setForeground(new Color(96,96,96));
        men_rec.setForeground(new Color(255,255,255));
        men_inv.setForeground(new Color(255,255,255));
        men_brrd.setForeground(new Color(255,255,255));
        men_brrr.setForeground(new Color(255,255,255));
        men_ua.setForeground(new Color(255,255,255));
        men_logout.setForeground(new Color(255,255,255));
        
        dashboard.setVisible(true);
        Records.setVisible(false);
        inventory.setVisible(false);
        Bookloan.setVisible(false);
        borrower.setVisible(false);
        useraccount.setVisible(false);
        report.setVisible(false);
        user_logo.setVisible(false);
        classification.setVisible(false);
        
        booksummary.setVisible(false);
        account.setVisible(true);
        findbook.setVisible(false);
    }//GEN-LAST:event_menu_title1MouseClicked

    private void brrd_clear1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brrd_clear1ActionPerformed
        if(brrd_remcb.getSelectedItem().equals("Loss") || brrd_remcb.getSelectedItem().equals("Damage")){
            payment();
            
            int a = Integer.parseInt(jLabel151.getText());
            int b = Integer.parseInt(test2.getText());
            int c ;
            
            c = a + b;
            
            jLabel152.setText(Integer.toString(c));
            
            try {
            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("UPDATE damage_tbl SET  "
                    + "Status=?, Quantity=?, Encoder=?, Date=?, Time=? WHERE Book_title='" + brrd_bt.getText() + "'");
            
            pst.setString(1, (String) brrd_remcb.getSelectedItem());
            pst.setString(2, jLabel152.getText());//damage
            pst.setString(3, txt_name.getText());
            pst.setString(4, txt_date1.getText());
            pst.setString(5, txt_time.getText());
            
            pst.execute();
            JOptionPane.showMessageDialog(null, "Data Updated");
            }
            catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
            
            try {

            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("DELETE FROM borrowed_tbl  WHERE ID = '" + brrd_id.getText() + "'");
            int del = pst.executeUpdate();
            if (del > 0) {
                JOptionPane.showMessageDialog(null, "Item Returned!");
                
            }else{
                JOptionPane.showMessageDialog(null, "Please Check Item!!");
            }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            } 
        }
        else if(brrd_remcb.getSelectedItem().equals("Borrowed")){
            payment();

            returnbor();
        }
        all_ref();
        brrd_clr();
    }//GEN-LAST:event_brrd_clear1ActionPerformed

    private void class_add3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_class_add3ActionPerformed
        jTabbedPane6.setEnabledAt(0, false);
        jTabbedPane6.setEnabledAt(1, false);
        jTabbedPane6.setEnabledAt(2, true);
        jTabbedPane6.setEnabledAt(3, false);
        jTabbedPane6.setEnabledAt(4, false);
        jTabbedPane6.setEnabledAt(5, false);
        jTabbedPane6.setSelectedIndex(2);
//        jTabbedPane1.setEnabledAt(6, false);
        sup_save3.setEnabled(true);
        sup_save3.setVisible(true);
        jLabel133.setText("1");
        
        men_dash.setBackground(new Color(96,96,96));
        men_rec.setBackground(new Color(96,96,96));
        men_inv.setBackground(new Color(255,255,255));
        men_brrd.setBackground(new Color(96,96,96));
        men_brrr.setBackground(new Color(96,96,96));
        men_ua.setBackground(new Color(96,96,96));
        men_logout.setBackground(new Color(96,96,96));
        men_po.setBackground(new Color(96,96,96));
        men_rep.setBackground(new Color(96,96,96));
        menu_title.setText("ADD SUPPLIER");
        men_po.setForeground(new Color(255,255,255));
        men_rep.setForeground(new Color(255,255,255));
        men_dash.setForeground(new Color(255,255,255));
        men_rec.setForeground(new Color(255,255,255));
        men_inv.setForeground(new Color(96,96,96));
        men_brrd.setForeground(new Color(255,255,255));
        men_brrr.setForeground(new Color(255,255,255));
        men_ua.setForeground(new Color(255,255,255));
        men_logout.setForeground(new Color(255,255,255));
        
        dashboard.setVisible(false);
        inventory.setVisible(false);
        Records.setVisible(true);
        Bookloan.setVisible(false);
        borrower.setVisible(false);
        useraccount.setVisible(false);
        report.setVisible(false);

    }//GEN-LAST:event_class_add3ActionPerformed

    private void sup_save3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sup_save3ActionPerformed
        sup();
    }//GEN-LAST:event_sup_save3ActionPerformed

    private void nb_update12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nb_update12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nb_update12ActionPerformed

    private void rep_bo_table4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rep_bo_table4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_rep_bo_table4MouseClicked

    private void po_or4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_po_or4KeyReleased
        try {
           String sql = "SELECT * "
                    + " FROM sales_tbl WHERE "
                    + "ID like ? or Book_title like ? or Borrower like ? or Status like ? or Quantity like ? or"
                   + " Price like ? or Date like ?";

            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, "%" + po_or4.getText() + "%");
            pst.setString(2, "%" + po_or4.getText() + "%");
            pst.setString(3, "%" + po_or4.getText() + "%");
            pst.setString(4, "%" + po_or4.getText() + "%");
            pst.setString(5, "%" + po_or4.getText() + "%");
            pst.setString(6, "%" + po_or4.getText() + "%");
            pst.setString(7, "%" + po_or4.getText() + "%");

            rs = (ResultSet) pst.executeQuery();
            rep_bo_table2.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, ex);
        }
        
        patqty_total();
        pay_total();
    }//GEN-LAST:event_po_or4KeyReleased

    private void rep_bo_table2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rep_bo_table2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_rep_bo_table2MouseClicked

    private void nb_update9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nb_update9ActionPerformed
        String remarks[] = {"Return","Void"};
        JComboBox cb = new JComboBox(remarks);

        int input;
        input = JOptionPane.showConfirmDialog(this,cb, "Select Remarks",JOptionPane.DEFAULT_OPTION);

        if(input == JOptionPane.OK_OPTION){
            String str = (String) cb.getSelectedItem();
            jLabel141.setText(str);
            
            if(jLabel141.getText().equals("Return")){
            try {
                    String sql = "Insert into requested_tbl (Book_title, Author, Classification, "
                    + "Quantity, Price, Purchase_No, Date, Station) values (?,?,?,?,?,?,?,?)";

                    pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement(sql);

                    pst.setString(1, jLabel143.getText());
                    pst.setString(2, jLabel144.getText());
                    pst.setString(3, jLabel145.getText());
                    pst.setString(4, jLabel146.getText());
                    pst.setString(5, jLabel147.getText());
                    pst.setString(6, jLabel148.getText());
                    pst.setString(7, jLabel149.getText());
                    pst.setString(8, jLabel150.getText());

                    pst.execute();

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            cancel_del();
            JOptionPane.showMessageDialog(null, "ENTRY RETURNED");
            nb_update9.setEnabled(false);
            }
            else if(jLabel141.getText().equals("Void")){
            int yes_no=JOptionPane.showConfirmDialog(this, "Are you sure to void this?", "Confirm",JOptionPane.YES_NO_OPTION);
                if(yes_no==JOptionPane.YES_OPTION){
                    cancel_del();
                    JOptionPane.showMessageDialog(null, "VOID ENTRY");
                    nb_update9.setEnabled(false);
                }
                else if(yes_no==JOptionPane.NO_OPTION){

                }
            }
        }
        all_ref();
    }//GEN-LAST:event_nb_update9ActionPerformed

    private void po_or3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_po_or3KeyReleased
        try {
           String sql = "SELECT * "
                    + " FROM cancel_tbl WHERE "
                    + "ID like ? or Book_title like ? or Author like ? or Classification like ? or Quantity like ? or"
                   + " Price like ? or Purchase_No like ? or Date like ? or Station like ?";

            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, "%" + po_or3.getText() + "%");
            pst.setString(2, "%" + po_or3.getText() + "%");
            pst.setString(3, "%" + po_or3.getText() + "%");
            pst.setString(4, "%" + po_or3.getText() + "%");
            pst.setString(5, "%" + po_or3.getText() + "%");
            pst.setString(6, "%" + po_or3.getText() + "%");
            pst.setString(7, "%" + po_or3.getText() + "%");
            pst.setString(8, "%" + po_or3.getText() + "%");
            pst.setString(9, "%" + po_or3.getText() + "%");

            rs = (ResultSet) pst.executeQuery();
            rep_bo_table1.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, ex);
        }
    }//GEN-LAST:event_po_or3KeyReleased

    private void rep_bo_table1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rep_bo_table1MouseClicked
        int z = rep_bo_table1.getSelectedRow();

        TableModel model = (TableModel)rep_bo_table1.getModel();
        jLabel142.setText(model.getValueAt(z, 0).toString());
        jLabel143.setText(model.getValueAt(z, 1).toString());
        jLabel144.setText(model.getValueAt(z, 2).toString());
        jLabel145.setText(model.getValueAt(z, 3).toString());
        jLabel146.setText(model.getValueAt(z, 4).toString());
        jLabel147.setText(model.getValueAt(z, 5).toString());
        jLabel148.setText(model.getValueAt(z, 6).toString());
        jLabel149.setText(model.getValueAt(z, 7).toString());
        jLabel150.setText(model.getValueAt(z, 8).toString());


        nb_update9.setEnabled(true);
    }//GEN-LAST:event_rep_bo_table1MouseClicked

    private void nb_update8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nb_update8ActionPerformed
        String remarks[] = {"Received","Cancel","Void"};
        JComboBox cb = new JComboBox(remarks);

        int input;
        input = JOptionPane.showConfirmDialog(this,cb, "Select Remarks",JOptionPane.DEFAULT_OPTION);

        if(input == JOptionPane.OK_OPTION){
            String str = (String) cb.getSelectedItem();
            jLabel124.setText(str);
            if(jLabel124.getText().equals("Received")){
                String res = JOptionPane.showInputDialog(this, "Input Delivery Receipt Number");
                jLabel138.setText(res);
                if(jLabel138.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Please Input DR Number First");
                }
                else{
                try {
                    String sql = "Insert into delivery_tbl (Delivery_No, Book_title, Author, Classification, "
                    + "Quantity, Price, Purchase_No, Purchase_Date, Delivery_Date) values (?,?,?,?,?,?,?,?,?)";

                    pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement(sql);

                    pst.setString(1, jLabel138.getText());
                    pst.setString(2, jLabel136.getText());
                    pst.setString(3, nb_aut.getText());
                    pst.setString(4, (String)nb_class.getSelectedItem());
                    pst.setString(5, nb_qty.getText());
                    pst.setString(6, nb_prc.getText());
                    pst.setString(7, jLabel3.getText());
                    pst.setString(8, jLabel137.getText());
                    pst.setString(9, txt_date1.getText());

                    pst.execute();

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }

                if(jLabel127.getText().equals("Existing")){
                    upbookqty();
                }
                else{
                    try {
                    String sql = "Insert into purchased_tbl (Book_title, Author, Classification, "
                    + "Quantity, Price) values (?,?,?,?,?)";

                    pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement(sql);

                    pst.setString(1, jLabel136.getText());
                    pst.setString(2, nb_aut.getText());
                    pst.setString(3, (String)nb_class.getSelectedItem());
                    pst.setString(4, nb_qty.getText());
                    pst.setString(5, nb_prc.getText());

                    pst.execute();

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
                }
                purchase_del();
                }
                JOptionPane.showMessageDialog(null, "SAVE ENTRY");
            }

            else if(jLabel124.getText().equals("Cancel")){

                try {
                    String sql = "Insert into cancel_tbl (ID, Book_title, Author, Classification, "
                    + "Quantity, Price, Purchase_No, Date, Station) values (?,?,?,?,?,?,?,?,?)";

                    pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement(sql);

                    pst.setString(1, nb_id.getText());
                    pst.setString(2, nb_bt.getText());
                    pst.setString(3, nb_aut.getText());
                    pst.setString(4, (String)nb_class.getSelectedItem());
                    pst.setString(5, nb_qty.getText());
                    pst.setString(6, nb_prc.getText());
                    pst.setString(7, jLabel127.getText());
                    pst.setString(8, txt_date1.getText());
                    pst.setString(9, jLabel127.getText());

                    pst.execute();

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
                purchase_del();

                JOptionPane.showMessageDialog(null, "CANCEL ENTRY");
            }
            else if(jLabel124.getText().equals("Void")){

                int yes_no=JOptionPane.showConfirmDialog(this, "Are you sure to void this?", "Confirm",JOptionPane.YES_NO_OPTION);
                if(yes_no==JOptionPane.YES_OPTION){
                    purchase_del();
                    JOptionPane.showMessageDialog(null, "VOID ENTRY");
                }
                else if(yes_no==JOptionPane.NO_OPTION){

                }

            }

            nb_id.setText("");
            nb_bt.setText("");
            nb_aut.setText("");
            nb_class.setSelectedItem("--Choose Book Classification--");
            nb_qty.setText("");
            nb_prc.setText("");

            nb_update8.setEnabled(false);
            all_ref();
        }
        all_ref();
    }//GEN-LAST:event_nb_update8ActionPerformed

    private void po_or2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_po_or2KeyReleased
        try {
           String sql = "SELECT * "
                    + " FROM requested_tbl WHERE "
                    + "ID like ? or Book_title like ? or Author like ? or Classification like ? or Quantity like ? or"
                   + " Price like ? or Purchase_No like ? or Date like ? or Station like ?";

            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, "%" + po_or2.getText() + "%");
            pst.setString(2, "%" + po_or2.getText() + "%");
            pst.setString(3, "%" + po_or2.getText() + "%");
            pst.setString(4, "%" + po_or2.getText() + "%");
            pst.setString(5, "%" + po_or2.getText() + "%");
            pst.setString(6, "%" + po_or2.getText() + "%");
            pst.setString(7, "%" + po_or2.getText() + "%");
            pst.setString(8, "%" + po_or2.getText() + "%");
            pst.setString(9, "%" + po_or2.getText() + "%");

            rs = (ResultSet) pst.executeQuery();
            rep_bo_table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, ex);
        }
    }//GEN-LAST:event_po_or2KeyReleased

    private void rep_bo_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rep_bo_tableMouseClicked
        int z = rep_bo_table.getSelectedRow();

        TableModel model = (TableModel)rep_bo_table.getModel();
        nb_id.setText(model.getValueAt(z, 0).toString());
        nb_bt.setText(model.getValueAt(z, 1).toString());
        jLabel136.setText(model.getValueAt(z, 1).toString());
        nb_aut.setText(model.getValueAt(z, 2).toString());
        nb_class.setSelectedItem(model.getValueAt(z, 3).toString());
        nb_qty.setText(model.getValueAt(z, 4).toString());
        nb_prc.setText(model.getValueAt(z, 5).toString());
        jLabel3.setText(model.getValueAt(z, 6).toString());
        jLabel137.setText(model.getValueAt(z, 7).toString());
        jLabel127.setText(model.getValueAt(z, 8).toString());

        nb_update8.setEnabled(true);
    }//GEN-LAST:event_rep_bo_tableMouseClicked

    private void nb_update11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nb_update11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nb_update11ActionPerformed

    private void nb_update13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nb_update13ActionPerformed
        jDateChooser2.setDate(null);
        jDateChooser1.setDate(null);
        po_or4.setText("");
        all_ref();
    }//GEN-LAST:event_nb_update13ActionPerformed

    private void nb_update14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nb_update14ActionPerformed
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            try {
                String sql = "select * from  sales_tbl where Date "
                        + "between '" + (String) sdf.format(jDateChooser2.getDate()) + "' "
                        + "and '" + (String) sdf.format(jDateChooser1.getDate()) + "' ";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                rep_bo_table2.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
  JOptionPane.showConfirmDialog(null, e);
            }
    }//GEN-LAST:event_nb_update14ActionPerformed

    private void po_or5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_po_or5KeyReleased
        try {
           String sql = "SELECT * "
                    + " FROM delivery_tbl WHERE "
                    + "ID like ? or Delivery_No like ? or Book_title like ? or Author like ? or Classification like ? or"
                   + " Quantity like ? or Price like ? or Purchase_No like ? or Purchase_Date like ? or Delivery_Date like ?";

            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, "%" + po_or5.getText() + "%");
            pst.setString(2, "%" + po_or5.getText() + "%");
            pst.setString(3, "%" + po_or5.getText() + "%");
            pst.setString(4, "%" + po_or5.getText() + "%");
            pst.setString(5, "%" + po_or5.getText() + "%");
            pst.setString(6, "%" + po_or5.getText() + "%");
            pst.setString(7, "%" + po_or5.getText() + "%");
            pst.setString(8, "%" + po_or5.getText() + "%");
            pst.setString(9, "%" + po_or5.getText() + "%");
            pst.setString(10, "%" + po_or5.getText() + "%");

            rs = (ResultSet) pst.executeQuery();
            rep_bo_table4.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, ex);
        }
    }//GEN-LAST:event_po_or5KeyReleased

    private void nb_update15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nb_update15ActionPerformed
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            try {
                String sql = "select * from  delivery_tbl where Date "
                        + "between '" + (String) sdf.format(jDateChooser3.getDate()) + "' "
                        + "and '" + (String) sdf.format(jDateChooser4.getDate()) + "' ";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                rep_bo_table4.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
  JOptionPane.showConfirmDialog(null, e);
            }
    }//GEN-LAST:event_nb_update15ActionPerformed

    private void nb_update16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nb_update16ActionPerformed
        all_ref();
        po_or5.setText("");
        jDateChooser3.setDate(null);
        jDateChooser4.setDate(null);
    }//GEN-LAST:event_nb_update16ActionPerformed

    private void so_search1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_so_search1KeyReleased
        try {
           String sql = "SELECT * "
                    + " FROM po_log_tbl WHERE "
                    + "ID like ? or Purchase_code like ? or Purchase_Date like ? or Supplier like ? or Book_Title like ? or"
                   + " Book_Author like ? or Classification like ? or Price like ? or Quantity like ? or Total like ?";

            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, "%" + so_search1.getText() + "%");
            pst.setString(2, "%" + so_search1.getText() + "%");
            pst.setString(3, "%" + so_search1.getText() + "%");
            pst.setString(4, "%" + so_search1.getText() + "%");
            pst.setString(5, "%" + so_search1.getText() + "%");
            pst.setString(6, "%" + so_search1.getText() + "%");
            pst.setString(7, "%" + so_search1.getText() + "%");
            pst.setString(8, "%" + so_search1.getText() + "%");
            pst.setString(9, "%" + so_search1.getText() + "%");
            pst.setString(10, "%" + so_search1.getText() + "%");

            rs = (ResultSet) pst.executeQuery();
            si_table1.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, ex);
        }
    }//GEN-LAST:event_so_search1KeyReleased

    private void si_save3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_si_save3ActionPerformed
        if(si_save3.getText().equals("Back To New Book")){
            menu_title.setText("RECORDS");

        jTabbedPane6.setEnabledAt(0, true);
        jTabbedPane6.setEnabledAt(1, true);
        jTabbedPane6.setEnabledAt(2, true);
        jTabbedPane6.setEnabledAt(3, true);
        jTabbedPane6.setEnabledAt(4, true);
        jTabbedPane6.setEnabledAt(5, true);
        jTabbedPane6.setSelectedIndex(3);
        }
        else if(si_save3.getText().equals("Back To P.O.")){
            
            jTabbedPane1.setEnabledAt(0, true);
            jTabbedPane1.setEnabledAt(1, true);
            jTabbedPane1.setEnabledAt(2, true);
            jTabbedPane1.setEnabledAt(3, true);
            jTabbedPane1.setEnabledAt(4, true);
            jTabbedPane6.setSelectedIndex(0);

            menu_title.setText("INVENTORY");

            dashboard.setVisible(false);
            Records.setVisible(false);
            inventory.setVisible(true);
            Bookloan.setVisible(false);
            report.setVisible(false);
        }
        si_save3.setVisible(false);
    }//GEN-LAST:event_si_save3ActionPerformed

    private void si_isbn2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_si_isbn2KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_si_isbn2KeyReleased

    private void si_save4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_si_save4ActionPerformed
        if(si_save3.getText().equals("Back To New Book")){
        
        classinv();
        men_dash.setBackground(new Color(96,96,96));
        men_rec.setBackground(new Color(255,255,255));
        men_inv.setBackground(new Color(96,96,96));
        men_brrd.setBackground(new Color(96,96,96));
        men_brrr.setBackground(new Color(96,96,96));
        men_ua.setBackground(new Color(96,96,96));
        men_logout.setBackground(new Color(96,96,96));
        menu_title.setText("NEW BOOK");
        men_po.setForeground(new Color(255,255,255));
        men_po.setBackground(new Color(96,96,96));
        men_rep.setBackground(new Color(96,96,96));
        men_rep.setForeground(new Color(255,255,255));
        men_dash.setForeground(new Color(255,255,255));
        men_rec.setForeground(new Color(96,96,96));
        men_inv.setForeground(new Color(255,255,255));
        men_brrd.setForeground(new Color(255,255,255));
        men_brrr.setForeground(new Color(255,255,255));
        men_ua.setForeground(new Color(255,255,255));
        men_logout.setForeground(new Color(255,255,255));
        
        dashboard.setVisible(false);
        newbook.setVisible(true);
        inventory.setVisible(false);
        Bookloan.setVisible(false);
        borrower.setVisible(false);
        useraccount.setVisible(false);
        report.setVisible(false);
        user_logo.setVisible(false);
        classification.setVisible(false);
        
        po_cl.setSelectedItem(si_isbn2.getText());
        all_ref();
            class_save_hide.setVisible(false);
            classif_list();
            nb_class.setSelectedItem(si_isbn2.getText());
            si_isbn2.setText("");
            class_num1.setText("");
            cl_io.setText("");
        }
    else if(si_save3.getText().equals("Back To P.O.")){
        
        classinv();
        men_dash.setBackground(new Color(96,96,96));
        men_rec.setBackground(new Color(96,96,96));
        men_inv.setBackground(new Color(96,96,96));
        men_brrd.setBackground(new Color(96,96,96));
        men_brrr.setBackground(new Color(96,96,96));
        men_ua.setBackground(new Color(96,96,96));
        men_logout.setBackground(new Color(96,96,96));
        men_rep.setBackground(new Color(96,96,96));
        men_po.setBackground(new Color(255,255,255));
        menu_title.setText("PURCHASE ORDER");

        men_po.setForeground(new Color(96,96,96));
        men_rep.setForeground(new Color(255,255,255));
        men_dash.setForeground(new Color(255,255,255));
        men_rec.setForeground(new Color(255,255,255));
        men_inv.setForeground(new Color(255,255,255));
        men_brrd.setForeground(new Color(255,255,255));
        men_brrr.setForeground(new Color(255,255,255));
        men_ua.setForeground(new Color(255,255,255));
        men_logout.setForeground(new Color(255,255,255));

        dashboard.setVisible(false);
        newbook.setVisible(false);
        inventory.setVisible(false);
        Bookloan.setVisible(false);
        borrower.setVisible(false);
        useraccount.setVisible(false);
        report.setVisible(false);
        user_logo.setVisible(true);
        classification.setVisible(false);
        booklist.setVisible(false);
        
        all_ref();
            class_save_hide.setVisible(false);
            classif_list();
            po_cl.setSelectedItem(si_isbn2.getText());
            si_isbn2.setText("");
            class_num1.setText("");
            cl_io.setText("");
    }
    else if(si_save3.getText().equals("Back")){

        classinv();
        all_ref();
            class_save_hide.setVisible(false);
            classif_list();
        si_isbn2.setText("");
            class_num1.setText("");
            cl_io.setText("");
    }
        
    }//GEN-LAST:event_si_save4ActionPerformed

    private void btn_del1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_del1ActionPerformed
        try {

            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("DELETE FROM classification_tbl  WHERE ID = '" + cl_io.getText() + "'");
            int del = pst.executeUpdate();
            if (del > 0) {
                JOptionPane.showMessageDialog(null, "Data Deleted!");
                
            }else{
                JOptionPane.showMessageDialog(null, "Please Check Item!!");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
         all_ref();
         class_num1.setText("");
         si_isbn2.setText("");
         cl_io.setText("");
    }//GEN-LAST:event_btn_del1ActionPerformed

    private void class_table1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_class_table1MouseClicked
        int z = class_table1.getSelectedRow();

          TableModel model = (TableModel)class_table1.getModel();
          //cl_io.setText(model.getValueAt(z, 0).toString());
          class_num1.setText(model.getValueAt(z, 0).toString());
          si_isbn2.setText(model.getValueAt(z, 1).toString());
          
          try {
            pst = (PreparedStatement) conn.prepareStatement("SELECT ID FROM classification_tbl where Class_Number= '" + class_num1.getText() + "' and Classname= '" + si_isbn2.getText() + "' ");
            rs = pst.executeQuery();
            if(rs.next()){
                String add = rs.getString("ID");
                cl_io.setText(add);
		}
            }
            catch (SQLException x) {
        
            }
          
          class_save_hide1.setVisible(true);
          btn_del1.setVisible(true);
    }//GEN-LAST:event_class_table1MouseClicked

    private void class_num1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_class_num1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_class_num1KeyReleased

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
            Login.setVisible(false);
            Menu.setVisible(false);
            mmm.setVisible(false);
            head.setVisible(false);
            stock.setVisible(false);
            publicsearch.setVisible(true);
            jPanel68.setVisible(true);
            jPanel2.setVisible(true);
            jPanel3.setVisible(true);
            nb_new8.setVisible(true);
            jLabel163.setVisible(true);
            jTextField2.setVisible(true);
            jComboBox1.setVisible(true);
            //pub_table.setVisible(false);
            public_ref();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3KeyPressed

    private void nb_new8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nb_new8ActionPerformed
            Login.setVisible(true);
            publicsearch.setVisible(false);
            Menu.setVisible(false);
            mmm.setVisible(false);
            head.setVisible(false);
            stock.setVisible(false);
            publicsearch.setVisible(false);
            jPanel68.setVisible(false);
            jPanel2.setVisible(false);
            jPanel3.setVisible(false);
            nb_new8.setVisible(false);
            jLabel163.setVisible(false);
            jTextField2.setVisible(false);
            jComboBox1.setVisible(false);
            //pub_table.setVisible(false);
            
            start();
    }//GEN-LAST:event_nb_new8ActionPerformed

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
        try {
           String sql = "SELECT ISBN_No, Book_title, Author, Call_Number, Classification, Publisher, Edition, Copy_Right_Year"
                    + " FROM stockin_tbl WHERE "
                    + "ISBN_No like ? or Book_title like ? or Author like ? or Call_Number like ? or Classification like ? or "
                    + "Publisher like ? or Edition like ? or Copy_Right_Year like ?";

            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, "%" + jTextField2.getText() + "%");
            pst.setString(2, "%" + jTextField2.getText() + "%");
            pst.setString(3, "%" + jTextField2.getText() + "%");
            pst.setString(4, "%" + jTextField2.getText() + "%");
            pst.setString(5, "%" + jTextField2.getText() + "%");
            pst.setString(6, "%" + jTextField2.getText() + "%");
            pst.setString(7, "%" + jTextField2.getText() + "%");
            pst.setString(8, "%" + jTextField2.getText() + "%");

            rs = (ResultSet) pst.executeQuery();
            sumpatable.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, ex);
        }
    }//GEN-LAST:event_jTextField2KeyReleased

    private void jComboBox1PopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jComboBox1PopupMenuWillBecomeInvisible
        try {
            String sql = "SELECT ISBN_No, Book_title, Author, Call_Number, Classification, Publisher, Edition, Copy_Right_Year"
                    + " FROM stockin_tbl WHERE "
                    + "Classification like ? ";

            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, "%" + jComboBox1.getSelectedItem() + "%");

            rs = (ResultSet) pst.executeQuery();
            sumpatable.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, ex);
        }
    }//GEN-LAST:event_jComboBox1PopupMenuWillBecomeInvisible

    private void sumpatableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sumpatableMouseClicked
        int z = sumpatable.getSelectedRow();

            TableModel model = (TableModel)sumpatable.getModel();
          jTextField3.setText(model.getValueAt(z, 0).toString());
          jTextField4.setText(model.getValueAt(z, 1).toString());
          jTextField5.setText(model.getValueAt(z, 2).toString());
          jTextField6.setText(model.getValueAt(z, 3).toString());
          jTextField7.setText(model.getValueAt(z, 4).toString());
          jTextField8.setText(model.getValueAt(z, 5).toString());
          jTextField9.setText(model.getValueAt(z, 6).toString());
          jTextField10.setText(model.getValueAt(z, 7).toString());
    }//GEN-LAST:event_sumpatableMouseClicked

    private void nb_new9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nb_new9ActionPerformed
        all_ref();
        jTextField3.setText("");
        jTextField4.setText("");
        jTextField5.setText("");
        jTextField6.setText("");
        jTextField7.setText("");
        jTextField8.setText("");
        jTextField9.setText("");
        jTextField10.setText("");
        jTextField2.setText("");
        jComboBox1.setSelectedIndex(0);
    }//GEN-LAST:event_nb_new9ActionPerformed

    private void brr_idtItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_brr_idtItemStateChanged
        if(brr_idt.getSelectedItem().equals("Employee ID")){
        brr_stat.setText("Faculty");
        brr_stat.setEditable(false);
        }
        else if(brr_idt.getSelectedItem().equals("Student ID")){
        brr_stat.setText("Student");
        brr_stat.setEditable(false);
        }
        else{
        brr_stat.setText("");
        brr_stat.setEditable(true);
        }
    }//GEN-LAST:event_brr_idtItemStateChanged

    private void brr_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_brr_searchKeyReleased
        try {
           String sql = "SELECT * "
                    + " FROM borrower_tbl WHERE "
                    + "Library_ID like ? or First_Name like ? or Middle_Name like ? or Surname like ? or ID_Type like ? or"
                   + " ID_No like ? or Course like ? or Year like ? or Address like ? or Status like ? or Birth_Date like ? ";

            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, "%" + brr_search.getText() + "%");
            pst.setString(2, "%" + brr_search.getText() + "%");
            pst.setString(3, "%" + brr_search.getText() + "%");
            pst.setString(4, "%" + brr_search.getText() + "%");
            pst.setString(5, "%" + brr_search.getText() + "%");
            pst.setString(6, "%" + brr_search.getText() + "%");
            pst.setString(7, "%" + brr_search.getText() + "%");
            pst.setString(8, "%" + brr_search.getText() + "%");
            pst.setString(9, "%" + brr_search.getText() + "%");
            pst.setString(10, "%" + brr_search.getText() + "%");
            pst.setString(11, "%" + brr_search.getText() + "%");

            rs = (ResultSet) pst.executeQuery();
            brr_table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, ex);
        }
    }//GEN-LAST:event_brr_searchKeyReleased

    private void course_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_course_addActionPerformed
        String res = JOptionPane.showInputDialog(this, "Input New Course","");
        jLabel183.setText(res);
            if ((res != null) && (res.length() > 0)) {
            try{
                String sql = "INSERT INTO course_tbl (Course_Title, Course, Description, Years, Status) VALUES (?,?,?,?,?)";
                pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement(sql);
                pst.setString(1, "Please Update");
                pst.setString(2, jLabel183.getText());
                pst.setString(3, "Please Update");
                pst.setString(4, "Please Update");
                pst.setString(5, "--Select Status--");
                pst.execute(); 
            }catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }    
    }
        all_ref();
        course_list();
        brr_cr.setSelectedItem(jLabel183.getText());
    }//GEN-LAST:event_course_addActionPerformed

    private void course_addMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_course_addMouseEntered
        jLabel183.setText("Add New Course");
    }//GEN-LAST:event_course_addMouseEntered

    private void course_addMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_course_addMouseExited
        jLabel183.setText("");
    }//GEN-LAST:event_course_addMouseExited

    private void nb_qtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nb_qtyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nb_qtyActionPerformed

    private void nb_cnKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nb_cnKeyReleased
        try {
            String sql = "SELECT *"
                    + " FROM stockin_tbl WHERE "
                    + "Call_Number like ? ";

            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, "%" + nb_cn.getText() + "%");

            rs = (ResultSet) pst.executeQuery();
            nb_table.setModel(DbUtils.resultSetToTableModel(rs));
            rc_nb();
        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, ex);
        }
        if(jLabel188.getText().equals("0")){
        jLabel189.setText("Call Number Available");
        }
        else{
        jLabel189.setText("Call Number Not Available");
        }
    }//GEN-LAST:event_nb_cnKeyReleased

    private void jTextField11KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField11KeyReleased
        try{
            String sql = "SELECT *"
                    + " FROM borrowed_tbl WHERE "
                    + "Full_Name like ?";

            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, "%" + jTextField11.getText() + "%");

            rs = (ResultSet) pst.executeQuery();
            brrd_log_table.setModel(DbUtils.resultSetToTableModel(rs));
            }
            catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, ex);
            }
        if(jTextField11.getText().equals("")){
        jLabel192.setVisible(false);
        }
        else{
        jLabel192.setVisible(true);
        }
    }//GEN-LAST:event_jTextField11KeyReleased

    private void jLabel192MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel192MouseClicked
        jTextField11.setText("");
        jLabel192.setVisible(false);
    }//GEN-LAST:event_jLabel192MouseClicked

    private void so_save1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_so_save1ActionPerformed
        try{
                String sql = "INSERT INTO course_tbl (Course_Title, Course, Description, Years, Status) VALUES (?,?,?,?,?)";
                pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement(sql);
                pst.setString(1, so_enc1.getText());
                pst.setString(2, so_date1.getText());
                pst.setString(3, so_time1.getText());
                pst.setString(4, so_time2.getText());
                pst.setString(5, (String) jComboBox2.getSelectedItem());
                pst.execute(); 
            }catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }  
        all_ref();
        course_clr();
    }//GEN-LAST:event_so_save1ActionPerformed

    private void so_save2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_so_save2ActionPerformed
        try {
            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("UPDATE course_tbl SET "
                    + "Course_Title=?, Course=?, Description=?, Years=?, Status=?"
                    + " WHERE ID='" + jLabel198.getText() + "'");

            pst.setString(1, so_enc1.getText());
            pst.setString(2, so_date1.getText());
            pst.setString(3, so_time1.getText());
            pst.setString(4, so_time2.getText());
            pst.setString(5, (String) jComboBox2.getSelectedItem());
            
            pst.execute();
            JOptionPane.showMessageDialog(null, "Data Updated");
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        all_ref();
        course_clr();
        so_save2.setEnabled(false);
        so_save3.setEnabled(false);
    }//GEN-LAST:event_so_save2ActionPerformed

    private void so_save3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_so_save3ActionPerformed
        try {

            pst = (com.mysql.jdbc.PreparedStatement) conn
                    .prepareStatement("DELETE FROM course_tbl  WHERE ID = '" + jLabel198.getText() + "'");
            int del = pst.executeUpdate();
            if (del > 0) {
                JOptionPane.showMessageDialog(null, "Data Deleted!");
                
            }else{
                JOptionPane.showMessageDialog(null, "Please Check Item!!");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        all_ref();
        course_clr();
        so_save2.setEnabled(false);
        so_save3.setEnabled(false);
    }//GEN-LAST:event_so_save3ActionPerformed

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
        int z = jTable3.getSelectedRow();

            TableModel model = (TableModel)jTable3.getModel();
          jLabel198.setText(model.getValueAt(z, 0).toString());
          so_enc1.setText(model.getValueAt(z, 1).toString());
          so_date1.setText(model.getValueAt(z, 2).toString());
          so_time1.setText(model.getValueAt(z, 3).toString());
          so_time2.setText(model.getValueAt(z, 4).toString());
          jComboBox2.setSelectedItem(model.getValueAt(z, 5).toString());

          so_save1.setEnabled(false);
          so_save2.setEnabled(true);
          so_save3.setEnabled(true);
    }//GEN-LAST:event_jTable3MouseClicked

    private void so_save4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_so_save4ActionPerformed
        course_clr();
          so_save1.setEnabled(true);
          so_save2.setEnabled(false);
          so_save3.setEnabled(false);
    }//GEN-LAST:event_so_save4ActionPerformed

    public void sup(){
        sup_save3.setVisible(false);
        
        jLabel133.setText("0");
        
        jTabbedPane1.setEnabledAt(0, true);
        jTabbedPane1.setEnabledAt(1, true);
        jTabbedPane1.setEnabledAt(2, true);
        jTabbedPane1.setEnabledAt(3, true);
        jTabbedPane1.setEnabledAt(4, true);
        jTabbedPane1.setSelectedIndex(0);
        
        menu_title.setText("INVENTORY");
        
        dashboard.setVisible(false);
        Records.setVisible(false);
        inventory.setVisible(true);
        Bookloan.setVisible(false);
        report.setVisible(false);

    }
    
    public void pub_ret(){
        sup_save2.setVisible(false);
        
        jLabel128.setText("0");
        
        jTabbedPane6.setEnabledAt(0, true);
        jTabbedPane6.setEnabledAt(1, true);
        jTabbedPane6.setEnabledAt(2, true);
        jTabbedPane6.setEnabledAt(3, true);
        jTabbedPane6.setEnabledAt(4, true);
        jTabbedPane6.setEnabledAt(5, true);
        jTabbedPane6.setSelectedIndex(3);
        
        menu_title.setText("RECORDS");
    }
    
    public void purchase_del(){
    try {
            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("DELETE FROM requested_tbl  WHERE ID = '" + nb_id.getText() + "'");
            int del = pst.executeUpdate();
            if (del > 0) {
                JOptionPane.showMessageDialog(null, "Data Deleted!");
                
            }else{
                JOptionPane.showMessageDialog(null, "Please Check Item!!");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } 
    }
    
    public void cancel_del(){
    try {
            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("DELETE FROM cancel_tbl  WHERE ID = '" + jLabel142.getText() + "'");
            int del = pst.executeUpdate();
            if (del > 0) {
                JOptionPane.showMessageDialog(null, "Data Deleted!");
                
            }else{
                JOptionPane.showMessageDialog(null, "Please Check Item!!");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } 
    }
    
    public void po_order(){
        int a = Integer.parseInt(po_pr.getText());
        int b = Integer.parseInt(po_qt.getText());
        int c;
        if(b<=0){
        ttt.setText(Integer.toString(a));
        }
        else{
        c = a * b;
        ttt.setText(Integer.toString(c));
        }
        
        try {
            String sql = "Insert into order_tbl (Purchase_No, Purchase_Date, Request_Date, Supplier, Book_Title, "
                    + " Book_Author, Classification, Price, Quantity, Total, Status) values (?,?,?,?,?,?,?,?,?,?,?)";

            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement(sql);
            
            pst.setString(1, po_or.getText());
            pst.setString(2, ((JTextField)po_date.getDateEditor().getUiComponent()).getText());
            pst.setString(3, txt_date1.getText());
            pst.setString(4, (String)po_sup.getSelectedItem());
            pst.setString(5, po_bt.getText());
            pst.setString(6, po_au.getText());
            pst.setString(7, (String) po_cl.getSelectedItem());
            pst.setString(8, po_pr.getText());
            pst.setString(9, po_qt.getText());
            pst.setString(10, ttt.getText());
            pst.setString(11, jLabel76.getText());

            pst.execute(); 
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
        all_ref();
    }
    
    public static String getDate(Calendar cal){
        
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
//LocalDate date1 = LocalDate.parse(startDate, formatter);
//LocalDate date2 = LocalDate.parse(passedDate, formatter);
//Calendar cal 
        
        return "" + (cal.get(Calendar.MONTH)+1) +"/" +
                cal.get(Calendar.DATE) + "/" + cal.get(Calendar.YEAR);
    }
     public static String getTime(Calendar cal){
        return "" + cal.get(Calendar.HOUR_OF_DAY) +":" +
                (cal.get(Calendar.MINUTE)) + ":" + cal.get(Calendar.SECOND);
    }
     
    public void returnbor(){
    int a = Integer.parseInt(test2.getText());//borrowed 
    int aa = Integer.parseInt(brrd_qty.getText());//qty
    int aaa = Integer.parseInt(test1.getText());//holdings
    int total, total2;
    
    total = aa - a;
    total2 = aaa + aa;
    test_tot.setText(Integer.toString(total));
    test_tot1.setText(Integer.toString(total2));
        
        try {
            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("UPDATE holding_tbl SET  "
                    + " Borrowed=?, On_Hand=? WHERE Book_title='" + brrd_bt.getText() + "'");
            
            pst.setString(1, test_tot.getText());
            pst.setString(2, test_tot1.getText());
            
            pst.execute();
            JOptionPane.showMessageDialog(null, "Data Updated");
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        
        try {
            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("UPDATE stockin_tbl SET  "
                    + " Quantity=? WHERE Book_title='" + brrd_bt.getText() + "'");
            
            pst.setString(1, test_tot1.getText());
            
            pst.execute();
            JOptionPane.showMessageDialog(null, "Data Updated");
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        
        try {

            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("DELETE FROM borrowed_tbl  WHERE ID = '" + brrd_id.getText() + "'");
            int del = pst.executeUpdate();
            if (del > 0) {
                JOptionPane.showMessageDialog(null, "Item Returned!");
                
            }else{
                JOptionPane.showMessageDialog(null, "Please Check Item!!");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
     }
     
     public void payment(){
     try {
            String sql = "Insert into sales_tbl (Book_Title, Borrower, Status, Quantity, Price, Date)"
                    + "  values (?,?,?,?,?,?)";

            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement(sql);
            
            pst.setString(1, brrd_bt.getText());
            pst.setString(2, brrd_fn.getText());
            pst.setString(3, (String)brrd_remcb.getSelectedItem());
            pst.setString(4, brrd_qty.getText());
            pst.setString(5, date1.getText());
            pst.setString(6, txt_date1.getText());

            pst.execute(); 
            
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
     }
     
    public void polog(){
    try {
                String sql = "Insert into po_log_tbl (Purchase_code, Purchase_Date, Supplier, Book_Title, "
                        + "Book_Author, Classification, Price, Quantity, Total)"
                        + " values (?,?,?,?,?,?,?,?,?)";

                pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement(sql);

                pst.setString(1, brr_fn7.getText() + po_or.getText());
                pst.setString(2, ((JTextField)po_date.getDateEditor().getUiComponent()).getText());
                pst.setString(3, (String) po_sup.getSelectedItem());
                pst.setString(4, po_bt.getText());
                pst.setString(5, po_au.getText());
                pst.setString(6, (String) po_cl.getSelectedItem());
                pst.setString(7, po_pr.getText());
                pst.setString(8, po_qt.getText());
                pst.setString(9, ttt.getText());
                
                pst.execute();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
            }
    } 
    
    public void upbookqty(){
        try{
            String sql="SELECT On_Hand FROM holding_tbl where Book_title = '" + (String) jLabel136.getText() + "' ";
            pst = (com.mysql.jdbc.PreparedStatement) (java.sql.PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();
            if(rs.next()){
            String name =rs.getString("On_Hand");
            jLabel134.setText(name);
            }
            }
            catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            }
        int a = Integer.parseInt(jLabel134.getText());
        int b = Integer.parseInt(nb_qty.getText());
        int c;
        
        c = a + b;
        
        jLabel135.setText(Integer.toString(c));
        
    try {
            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("UPDATE holding_tbl SET On_Hand=? "
                    + " WHERE Book_title='" + jLabel136.getText() + "'");
            
            pst.setString(1, jLabel135.getText());
            
            pst.execute();
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        try {
            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("UPDATE stockin_tbl SET  "
                    + "Quantity=? WHERE Book_title='" + jLabel136.getText() + "'");
            
            pst.setString(1, jLabel135.getText());
            
            pst.execute();
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        
        try {
            String sql = "Insert into invlog_tbl (ISBN_No, Book_title, Classification, Encoder, Date, Time, Action) "
                    + " values (?,?,?,?,?,?,?)";

            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement(sql);
            
            pst.setString(1, si_isbn.getText());
            pst.setString(2, si_bt.getText());
            pst.setString(3, si_class.getText());
            pst.setString(4, txt_name.getText());
            pst.setString(5, txt_date1.getText());
            pst.setString(6, txt_time.getText());
            pst.setString(7, "Stock-in");

            pst.execute(); 
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        all_ref();
    }
    
    public void classinv(){
        if(cl_io.getText().equals("")){
            try {
            String sql = "Insert into classification_tbl (Class_Number, Classname) values (?,?)";

            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement(sql);

            pst.setString(1, class_num1.getText());
            pst.setString(2, si_isbn2.getText());
            
            pst.execute();
            
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
                } 
            
        }
        else{
        try {
            pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement("UPDATE classification_tbl SET "
                    + "Class_Number=?, Classname=? WHERE ID='" + cl_io.getText() + "'");
            pst.setString(1, class_num1.getText());
            pst.setString(2, si_isbn2.getText());
            pst.execute();
            JOptionPane.showMessageDialog(null, "Data Updated");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
                } 
            
        }
    }
    
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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Bookloan;
    private javax.swing.JPanel Login;
    private javax.swing.JPanel Menu;
    private javax.swing.JPanel Records;
    private javax.swing.JCheckBox acc_acc;
    private javax.swing.JButton acc_browse;
    private javax.swing.JButton acc_browse1;
    private javax.swing.JCheckBox acc_brrd;
    private javax.swing.JCheckBox acc_brrr;
    private javax.swing.JPasswordField acc_con;
    private javax.swing.JButton acc_delete;
    private javax.swing.JTextField acc_ffmn;
    private javax.swing.JTextField acc_ffsn;
    private javax.swing.JTextField acc_fn;
    private javax.swing.JLabel acc_id;
    private javax.swing.JLabel acc_image;
    private javax.swing.JCheckBox acc_inv;
    private javax.swing.JComboBox<String> acc_lvl;
    private javax.swing.JTextField acc_mid;
    private javax.swing.JTextField acc_mn;
    private javax.swing.JCheckBox acc_nb;
    private javax.swing.JButton acc_new;
    private javax.swing.JPasswordField acc_pass;
    private javax.swing.JTextField acc_pos;
    private javax.swing.JCheckBox acc_rep;
    private javax.swing.JButton acc_save;
    private javax.swing.JTable acc_table;
    private javax.swing.JButton acc_update;
    private javax.swing.JButton acc_update1;
    private javax.swing.JTextField acc_user;
    private javax.swing.JPanel account;
    private javax.swing.JPanel account_waste;
    private javax.swing.JLabel background;
    private javax.swing.JTable bl_table;
    private javax.swing.JPanel booklist;
    private javax.swing.JPanel booksummary;
    private javax.swing.JTextField borroweddate;
    private javax.swing.JTable borrowedlog_table;
    private javax.swing.JPanel borrower;
    private javax.swing.JTextField brr_add;
    private com.toedter.calendar.JDateChooser brr_bd;
    private javax.swing.JButton brr_browse;
    private javax.swing.JTextField brr_count;
    private javax.swing.JComboBox<String> brr_cr;
    private javax.swing.JButton brr_delete;
    private javax.swing.JTextField brr_fn;
    private javax.swing.JTextField brr_fn7;
    private javax.swing.JTextField brr_fn8;
    private javax.swing.JTextField brr_fn9;
    private javax.swing.JLabel brr_id;
    private javax.swing.JTextField brr_idn;
    private javax.swing.JComboBox<String> brr_idt;
    private javax.swing.JLabel brr_image;
    private javax.swing.JTextField brr_mn;
    private javax.swing.JButton brr_new;
    private javax.swing.JLabel brr_q;
    private javax.swing.JLabel brr_rid;
    private javax.swing.JButton brr_save;
    private javax.swing.JTextField brr_search;
    private javax.swing.JTextField brr_sn;
    private javax.swing.JTextField brr_stat;
    private javax.swing.JTable brr_table;
    private javax.swing.JButton brr_update;
    private javax.swing.JComboBox<String> brr_yr;
    private javax.swing.JLabel brrd;
    private javax.swing.JButton brrd_add;
    private javax.swing.JTable brrd_bl_table;
    private javax.swing.JTextField brrd_bp;
    private javax.swing.JLabel brrd_bqty;
    private javax.swing.JTextField brrd_bt;
    private javax.swing.JComboBox<String> brrd_class;
    private javax.swing.JButton brrd_clear;
    private javax.swing.JButton brrd_clear1;
    private javax.swing.JTextField brrd_fd;
    private javax.swing.JTextField brrd_fd1;
    private javax.swing.JTextField brrd_fd2;
    private javax.swing.JTextField brrd_fd3;
    private javax.swing.JTextField brrd_fn;
    private javax.swing.JComboBox<String> brrd_fn2;
    private javax.swing.JLabel brrd_id;
    private javax.swing.JTextField brrd_libid1;
    private javax.swing.JTable brrd_log_table;
    private javax.swing.JButton brrd_min;
    private javax.swing.JTextField brrd_qty;
    private javax.swing.JTable brrd_rb_table;
    private javax.swing.JLabel brrd_rem;
    private javax.swing.JLabel brrd_rem1;
    private javax.swing.JComboBox<String> brrd_remcb;
    private javax.swing.JButton brrd_return;
    private javax.swing.JComboBox<String> brrd_stat;
    private javax.swing.JLabel brrd_total;
    private javax.swing.JButton brrd_uprem;
    private javax.swing.JLabel brrr;
    private javax.swing.JButton btn_del;
    private javax.swing.JButton btn_del1;
    private javax.swing.JLabel cl_io;
    private javax.swing.JButton class_add;
    private javax.swing.JButton class_add1;
    private javax.swing.JButton class_add2;
    private javax.swing.JButton class_add3;
    private javax.swing.JLabel class_num;
    private javax.swing.JTextField class_num1;
    private javax.swing.JPanel class_save_hide;
    private javax.swing.JPanel class_save_hide1;
    private javax.swing.JTable class_table;
    private javax.swing.JTable class_table1;
    private javax.swing.JPanel classification;
    private javax.swing.JLabel code;
    private javax.swing.JButton course_add;
    private javax.swing.JPanel dashboard;
    private javax.swing.JLabel date1;
    private com.toedter.calendar.JDateChooser dddd1;
    private com.toedter.calendar.JDateChooser dddd2;
    private com.toedter.calendar.JDateChooser dddd3;
    private com.toedter.calendar.JDateChooser dddd4;
    private javax.swing.JLabel dsc_id;
    private javax.swing.JLabel exit;
    private javax.swing.JPanel findbook;
    private javax.swing.JLabel frm_tbl;
    private javax.swing.JPanel head;
    private javax.swing.JLabel idd;
    private javax.swing.JLabel inv;
    private javax.swing.JPanel inventory;
    private javax.swing.JTextField invlog_search;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private com.toedter.calendar.JDateChooser jDateChooser3;
    private com.toedter.calendar.JDateChooser jDateChooser4;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JFileChooser jFileChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel116;
    private javax.swing.JLabel jLabel117;
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel119;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel120;
    private javax.swing.JLabel jLabel121;
    private javax.swing.JLabel jLabel122;
    private javax.swing.JLabel jLabel123;
    private javax.swing.JLabel jLabel124;
    private javax.swing.JLabel jLabel125;
    private javax.swing.JLabel jLabel126;
    private javax.swing.JLabel jLabel127;
    private javax.swing.JLabel jLabel128;
    private javax.swing.JLabel jLabel129;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel130;
    private javax.swing.JLabel jLabel131;
    private javax.swing.JLabel jLabel132;
    private javax.swing.JLabel jLabel133;
    private javax.swing.JLabel jLabel134;
    private javax.swing.JLabel jLabel135;
    private javax.swing.JLabel jLabel136;
    private javax.swing.JLabel jLabel137;
    private javax.swing.JLabel jLabel138;
    private javax.swing.JLabel jLabel139;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel140;
    private javax.swing.JLabel jLabel141;
    private javax.swing.JLabel jLabel142;
    private javax.swing.JLabel jLabel143;
    private javax.swing.JLabel jLabel144;
    private javax.swing.JLabel jLabel145;
    private javax.swing.JLabel jLabel146;
    private javax.swing.JLabel jLabel147;
    private javax.swing.JLabel jLabel148;
    private javax.swing.JLabel jLabel149;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel150;
    private javax.swing.JLabel jLabel151;
    private javax.swing.JLabel jLabel152;
    private javax.swing.JLabel jLabel153;
    private javax.swing.JLabel jLabel154;
    private javax.swing.JLabel jLabel155;
    private javax.swing.JLabel jLabel156;
    private javax.swing.JLabel jLabel157;
    private javax.swing.JLabel jLabel158;
    private javax.swing.JLabel jLabel159;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel160;
    private javax.swing.JLabel jLabel161;
    private javax.swing.JLabel jLabel162;
    private javax.swing.JLabel jLabel163;
    private javax.swing.JLabel jLabel164;
    private javax.swing.JLabel jLabel165;
    private javax.swing.JLabel jLabel166;
    private javax.swing.JLabel jLabel167;
    private javax.swing.JLabel jLabel168;
    private javax.swing.JLabel jLabel169;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel170;
    private javax.swing.JLabel jLabel171;
    private javax.swing.JLabel jLabel172;
    private javax.swing.JLabel jLabel173;
    private javax.swing.JLabel jLabel174;
    private javax.swing.JLabel jLabel175;
    private javax.swing.JLabel jLabel176;
    private javax.swing.JLabel jLabel177;
    private javax.swing.JLabel jLabel178;
    private javax.swing.JLabel jLabel179;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel180;
    private javax.swing.JLabel jLabel181;
    private javax.swing.JLabel jLabel182;
    private javax.swing.JLabel jLabel183;
    private javax.swing.JLabel jLabel184;
    private javax.swing.JLabel jLabel185;
    private javax.swing.JLabel jLabel186;
    private javax.swing.JLabel jLabel187;
    private javax.swing.JLabel jLabel188;
    private javax.swing.JLabel jLabel189;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel190;
    private javax.swing.JLabel jLabel191;
    private javax.swing.JLabel jLabel192;
    private javax.swing.JLabel jLabel193;
    private javax.swing.JLabel jLabel194;
    private javax.swing.JLabel jLabel195;
    private javax.swing.JLabel jLabel196;
    private javax.swing.JLabel jLabel197;
    private javax.swing.JLabel jLabel198;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
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
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel45;
    private javax.swing.JPanel jPanel46;
    private javax.swing.JPanel jPanel47;
    private javax.swing.JPanel jPanel48;
    private javax.swing.JPanel jPanel49;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel50;
    private javax.swing.JPanel jPanel51;
    private javax.swing.JPanel jPanel52;
    private javax.swing.JPanel jPanel53;
    private javax.swing.JPanel jPanel54;
    private javax.swing.JPanel jPanel55;
    private javax.swing.JPanel jPanel56;
    private javax.swing.JPanel jPanel57;
    private javax.swing.JPanel jPanel58;
    private javax.swing.JPanel jPanel59;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel60;
    private javax.swing.JPanel jPanel61;
    private javax.swing.JPanel jPanel62;
    private javax.swing.JPanel jPanel63;
    private javax.swing.JPanel jPanel64;
    private javax.swing.JPanel jPanel65;
    private javax.swing.JPanel jPanel66;
    private javax.swing.JPanel jPanel67;
    private javax.swing.JPanel jPanel68;
    private javax.swing.JPanel jPanel69;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel70;
    private javax.swing.JPanel jPanel71;
    private javax.swing.JPanel jPanel72;
    private javax.swing.JPanel jPanel73;
    private javax.swing.JPanel jPanel74;
    private javax.swing.JPanel jPanel75;
    private javax.swing.JPanel jPanel76;
    private javax.swing.JPanel jPanel77;
    private javax.swing.JPanel jPanel78;
    private javax.swing.JPanel jPanel79;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel80;
    private javax.swing.JPanel jPanel81;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JScrollPane jScrollPane23;
    private javax.swing.JScrollPane jScrollPane24;
    private javax.swing.JScrollPane jScrollPane25;
    private javax.swing.JScrollPane jScrollPane26;
    private javax.swing.JScrollPane jScrollPane27;
    private javax.swing.JScrollPane jScrollPane28;
    private javax.swing.JScrollPane jScrollPane29;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane30;
    private javax.swing.JScrollPane jScrollPane31;
    private javax.swing.JScrollPane jScrollPane32;
    private javax.swing.JScrollPane jScrollPane33;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTabbedPane jTabbedPane5;
    private javax.swing.JTabbedPane jTabbedPane6;
    private javax.swing.JTabbedPane jTabbedPane7;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JTable log_table;
    private javax.swing.JPanel loginpanel;
    private javax.swing.JTextField masb_search;
    private javax.swing.JTable masb_table;
    private javax.swing.JLabel men_brrd;
    private javax.swing.JLabel men_brrr;
    private javax.swing.JLabel men_dash;
    private javax.swing.JLabel men_inv;
    private javax.swing.JLabel men_logout;
    private javax.swing.JLabel men_po;
    private javax.swing.JLabel men_rec;
    private javax.swing.JLabel men_rep;
    private javax.swing.JLabel men_ua;
    private javax.swing.JLabel menu_title;
    private javax.swing.JLabel menu_title1;
    private javax.swing.JLabel minimize;
    private javax.swing.JLabel minus_test;
    private javax.swing.JPanel mmm;
    private javax.swing.JPasswordField my_con;
    private javax.swing.JTextField my_fn;
    private javax.swing.JLabel my_id;
    private javax.swing.JLabel my_image;
    private javax.swing.JTextField my_mn;
    private javax.swing.JTextField my_mnn;
    private javax.swing.JPasswordField my_pass;
    private javax.swing.JTextField my_sn;
    private javax.swing.JTextField my_user;
    private javax.swing.JLabel nb;
    private javax.swing.JTextField nb_aut;
    private javax.swing.JTextField nb_bt;
    private javax.swing.JComboBox<String> nb_class;
    private javax.swing.JTextField nb_cn;
    private javax.swing.JComboBox<String> nb_cpub;
    private com.toedter.calendar.JDateChooser nb_cry;
    private com.toedter.calendar.JDateChooser nb_da;
    private javax.swing.JButton nb_delete;
    private javax.swing.JTextField nb_edi;
    private javax.swing.JLabel nb_id;
    private javax.swing.JTextField nb_is;
    private javax.swing.JTextField nb_is1;
    private javax.swing.JTextField nb_is2;
    private javax.swing.JButton nb_new;
    private javax.swing.JButton nb_new1;
    private javax.swing.JButton nb_new3;
    private javax.swing.JButton nb_new4;
    private javax.swing.JButton nb_new5;
    private javax.swing.JButton nb_new6;
    private javax.swing.JButton nb_new7;
    private javax.swing.JButton nb_new8;
    private javax.swing.JButton nb_new9;
    private javax.swing.JTextField nb_prc;
    private javax.swing.JTextField nb_qty;
    private javax.swing.JButton nb_save;
    private javax.swing.JButton nb_save1;
    private javax.swing.JTextField nb_search;
    private javax.swing.JTable nb_table;
    private javax.swing.JTable nb_table1;
    private javax.swing.JButton nb_update;
    private javax.swing.JButton nb_update1;
    private javax.swing.JButton nb_update11;
    private javax.swing.JButton nb_update12;
    private javax.swing.JButton nb_update13;
    private javax.swing.JButton nb_update14;
    private javax.swing.JButton nb_update15;
    private javax.swing.JButton nb_update16;
    private javax.swing.JButton nb_update2;
    private javax.swing.JButton nb_update3;
    private javax.swing.JButton nb_update4;
    private javax.swing.JButton nb_update5;
    private javax.swing.JButton nb_update7;
    private javax.swing.JButton nb_update8;
    private javax.swing.JButton nb_update9;
    private javax.swing.JPanel newbook;
    private javax.swing.JTextField ohb_bd;
    private javax.swing.JTextField ohb_bt;
    private javax.swing.JTextField ohb_class;
    private javax.swing.JTextField ohb_fines;
    private javax.swing.JTextField ohb_pay;
    private javax.swing.JTextField ohb_qty;
    private javax.swing.JTextField ohb_rd;
    private javax.swing.JTable ohb_table;
    private javax.swing.JTextField ohn_rem;
    private javax.swing.JPasswordField pass;
    private javax.swing.JButton po_add;
    private javax.swing.JTextField po_au;
    private javax.swing.JTextField po_bt;
    private javax.swing.JComboBox<String> po_cl;
    private com.toedter.calendar.JDateChooser po_date;
    private javax.swing.JButton po_il;
    private javax.swing.JButton po_min;
    private javax.swing.JButton po_ne;
    private javax.swing.JTextField po_or;
    private javax.swing.JTextField po_or1;
    private javax.swing.JTextField po_or2;
    private javax.swing.JTextField po_or3;
    private javax.swing.JTextField po_or4;
    private javax.swing.JTextField po_or5;
    private javax.swing.JTextField po_pr;
    private javax.swing.JTextField po_qt;
    private javax.swing.JComboBox<String> po_sup;
    private javax.swing.JTable po_table;
    private javax.swing.JTable po_test;
    private javax.swing.JTable pobl_table;
    private javax.swing.JPanel publicsearch;
    private javax.swing.JLabel rc_po;
    private javax.swing.JLabel rc_tot;
    private javax.swing.JLabel remain;
    private javax.swing.JLabel rep;
    private javax.swing.JTable rep_bo_table;
    private javax.swing.JTable rep_bo_table1;
    private javax.swing.JTable rep_bo_table2;
    private javax.swing.JTable rep_bo_table4;
    private javax.swing.JPanel report;
    private javax.swing.JTextField returndate;
    private javax.swing.JLabel sandh;
    private javax.swing.JCheckBox showpass;
    private javax.swing.JButton si_add;
    private javax.swing.JTextField si_brrd;
    private javax.swing.JTextField si_bt;
    private javax.swing.JButton si_calc;
    private javax.swing.JButton si_calc1;
    private javax.swing.JTextField si_class;
    private javax.swing.JTextField si_date;
    private javax.swing.JTextField si_dmg;
    private javax.swing.JTextField si_enc;
    private javax.swing.JLabel si_id;
    private javax.swing.JTextField si_isbn;
    private javax.swing.JTextField si_isbn1;
    private javax.swing.JTextField si_isbn2;
    private javax.swing.JButton si_minus;
    private javax.swing.JTextField si_oh;
    private javax.swing.JButton si_save;
    private javax.swing.JButton si_save1;
    private javax.swing.JButton si_save2;
    private javax.swing.JButton si_save3;
    private javax.swing.JButton si_save4;
    private javax.swing.JTable si_table;
    private javax.swing.JTable si_table1;
    private javax.swing.JTextField si_th;
    private javax.swing.JTextField si_time;
    private javax.swing.JButton so_add;
    private javax.swing.JTextField so_bt;
    private javax.swing.JTextField so_class;
    private javax.swing.JTextField so_date;
    private javax.swing.JTextField so_date1;
    private javax.swing.JTextField so_enc;
    private javax.swing.JTextField so_enc1;
    private javax.swing.JLabel so_id;
    private javax.swing.JTextField so_isbn;
    private javax.swing.JButton so_minus;
    private javax.swing.JTextField so_qty;
    private javax.swing.JButton so_save;
    private javax.swing.JButton so_save1;
    private javax.swing.JButton so_save2;
    private javax.swing.JButton so_save3;
    private javax.swing.JButton so_save4;
    private javax.swing.JTextField so_search;
    private javax.swing.JTextField so_search1;
    private javax.swing.JComboBox<String> so_stat;
    private javax.swing.JTable so_table;
    private javax.swing.JTextField so_time;
    private javax.swing.JTextField so_time1;
    private javax.swing.JTextField so_time2;
    private javax.swing.JLabel sominus_test;
    private javax.swing.JPanel ssss;
    private javax.swing.JPanel stock;
    private javax.swing.JTextField sum_bt;
    private javax.swing.JComboBox<String> sum_class;
    private javax.swing.JTable sum_table;
    private javax.swing.JTable sumpatable;
    private javax.swing.JLabel sumup;
    private javax.swing.JTextField sup_add;
    private javax.swing.JTextField sup_add1;
    private javax.swing.JTextField sup_con;
    private javax.swing.JTextField sup_cp;
    private javax.swing.JButton sup_delete;
    private javax.swing.JButton sup_delete1;
    private javax.swing.JTextField sup_em;
    private com.toedter.calendar.JDateChooser sup_enc;
    private javax.swing.JLabel sup_id;
    private javax.swing.JTextField sup_name;
    private javax.swing.JTextField sup_name1;
    private javax.swing.JButton sup_new;
    private javax.swing.JButton sup_save;
    private javax.swing.JButton sup_save1;
    private javax.swing.JButton sup_save2;
    private javax.swing.JButton sup_save3;
    private javax.swing.JTextField sup_search;
    private javax.swing.JTextField sup_search1;
    private javax.swing.JTable sup_table;
    private javax.swing.JTable sup_table1;
    private javax.swing.JButton sup_update;
    private javax.swing.JButton sup_update1;
    private javax.swing.JPanel test;
    private javax.swing.JLabel test1;
    private javax.swing.JLabel test2;
    private javax.swing.JLabel test3;
    private javax.swing.JLabel test4;
    private javax.swing.JLabel test5;
    private javax.swing.JLabel test6;
    private javax.swing.JLabel test_tot;
    private javax.swing.JLabel test_tot1;
    private javax.swing.JLabel ttt;
    private javax.swing.JLabel txt_date1;
    private javax.swing.JLabel txt_level;
    private javax.swing.JLabel txt_name;
    private javax.swing.JLabel txt_name1;
    private javax.swing.JLabel txt_time;
    private javax.swing.JLabel ua;
    private javax.swing.JTextField user;
    private javax.swing.JPanel user_logo;
    private javax.swing.JPanel useraccount;
    // End of variables declaration//GEN-END:variables

//    private String Double(double total) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
}
