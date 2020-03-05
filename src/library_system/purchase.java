/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library_system;

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
import static library_system.login.iddlog;
import static library_system.login.jLabel2log;
import static library_system.login.jLabel3log;
import static library_system.login.txt_namelog;
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
        jLabel1.setText(Main.jLabel78.getText());
        conn = (Connection) MySqlConnect.ConnectDB();
        CurrentDate();
        table_ref();
        phdash();
        pocount();
        po();
        ifelse();
    }
    public void ifelse(){
        if(jLabel1.getText().equals("purchase")){
        print.setVisible(true);
        print2.setVisible(false);
        starter.setVisible(false);
        }
        else if(jLabel1.getText().equals("payment")){
        print.setVisible(false);
        print2.setVisible(true);
        starter.setVisible(false);
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
    public void CurrentDate() {//date and time to toolbar running
        new Timer(0, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Date d = new Date();
                SimpleDateFormat s = new SimpleDateFormat("h:mm aa");
                jLabel2log.setText(s.format(d));

                SimpleDateFormat st = new SimpleDateFormat("M/d/yyyy");
                menu_title10.setText(st.format(d));   
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
        new Main().setVisible(true);
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
        menu_title20 = new javax.swing.JLabel();
        menu_title21 = new javax.swing.JLabel();
        menu_title22 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        menu_title23 = new javax.swing.JLabel();
        menu_title26 = new javax.swing.JLabel();
        menu_title27 = new javax.swing.JLabel();
        jScrollPane38 = new javax.swing.JScrollPane();
        jTable8 = new javax.swing.JTable();
        menu_title28 = new javax.swing.JLabel();
        menu_title29 = new javax.swing.JLabel();
        menu_title30 = new javax.swing.JLabel();
        menu_title31 = new javax.swing.JLabel();
        menu_title32 = new javax.swing.JLabel();
        menu_title33 = new javax.swing.JLabel();
        menu_title34 = new javax.swing.JLabel();
        menu_title35 = new javax.swing.JLabel();
        menu_title36 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setLayout(new java.awt.CardLayout());

        jLabel1.setText("jLabel1");

        jLabel2.setText("date1");

        jLabel3.setText("date2");

        javax.swing.GroupLayout starterLayout = new javax.swing.GroupLayout(starter);
        starter.setLayout(starterLayout);
        starterLayout.setHorizontalGroup(
            starterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(starterLayout.createSequentialGroup()
                .addGap(267, 267, 267)
                .addGroup(starterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                .addContainerGap(554, Short.MAX_VALUE))
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
                        .addGap(256, 256, 256)
                        .addComponent(jLabel78)
                        .addGap(18, 18, 18)
                        .addGroup(printLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(menu_title3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(menu_title4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(menu_title5)
                            .addComponent(menu_title19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(printLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(printLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(menu_title11)
                            .addComponent(menu_title13))
                        .addGap(18, 18, 18)
                        .addGroup(printLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(menu_title14)
                            .addGroup(printLayout.createSequentialGroup()
                                .addComponent(menu_title12)
                                .addGap(426, 426, 426)
                                .addComponent(menu_title15)
                                .addGap(18, 18, 18)
                                .addComponent(menu_title16))))
                    .addGroup(printLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(printLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(printLayout.createSequentialGroup()
                                .addComponent(jScrollPane37, javax.swing.GroupLayout.PREFERRED_SIZE, 834, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(printLayout.createSequentialGroup()
                                .addComponent(menu_title7)
                                .addGap(18, 18, 18)
                                .addComponent(menu_title8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(menu_title9)
                                .addGap(18, 18, 18)
                                .addComponent(menu_title10, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(menu_title6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(printLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(menu_title17)
                .addGap(18, 18, 18)
                .addComponent(menu_title18)
                .addContainerGap(645, Short.MAX_VALUE))
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

        menu_title20.setBackground(new java.awt.Color(96, 96, 96));
        menu_title20.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        menu_title20.setForeground(new java.awt.Color(102, 102, 102));
        menu_title20.setText("CRONASIA");

        menu_title21.setBackground(new java.awt.Color(96, 96, 96));
        menu_title21.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        menu_title21.setForeground(new java.awt.Color(102, 102, 102));
        menu_title21.setText("COLLEGE");

        menu_title22.setBackground(new java.awt.Color(96, 96, 96));
        menu_title22.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title22.setForeground(new java.awt.Color(102, 102, 102));
        menu_title22.setText("General Santos City");

        jLabel79.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/cronasia.png"))); // NOI18N

        menu_title23.setBackground(new java.awt.Color(96, 96, 96));
        menu_title23.setFont(new java.awt.Font("Arial Black", 0, 36)); // NOI18N
        menu_title23.setForeground(new java.awt.Color(102, 102, 102));
        menu_title23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        menu_title23.setText("Payment Collection");

        menu_title26.setBackground(new java.awt.Color(96, 96, 96));
        menu_title26.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title26.setForeground(new java.awt.Color(102, 102, 102));
        menu_title26.setText("Date:");

        menu_title27.setBackground(new java.awt.Color(96, 96, 96));
        menu_title27.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title27.setForeground(new java.awt.Color(102, 102, 102));
        menu_title27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        menu_title27.setText("Date:");

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

        menu_title28.setBackground(new java.awt.Color(96, 96, 96));
        menu_title28.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title28.setForeground(new java.awt.Color(102, 102, 102));
        menu_title28.setText("Total Item:");

        menu_title29.setBackground(new java.awt.Color(96, 96, 96));
        menu_title29.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title29.setForeground(new java.awt.Color(102, 102, 102));
        menu_title29.setText("Total Item:");

        menu_title30.setBackground(new java.awt.Color(96, 96, 96));
        menu_title30.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title30.setForeground(new java.awt.Color(102, 102, 102));
        menu_title30.setText("Total Book:");

        menu_title31.setBackground(new java.awt.Color(96, 96, 96));
        menu_title31.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title31.setForeground(new java.awt.Color(102, 102, 102));
        menu_title31.setText("Total Book:");

        menu_title32.setBackground(new java.awt.Color(96, 96, 96));
        menu_title32.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title32.setForeground(new java.awt.Color(102, 102, 102));
        menu_title32.setText("Total Cost:");

        menu_title33.setBackground(new java.awt.Color(96, 96, 96));
        menu_title33.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title33.setForeground(new java.awt.Color(102, 102, 102));
        menu_title33.setText("Total Cost:");

        menu_title34.setBackground(new java.awt.Color(96, 96, 96));
        menu_title34.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        menu_title34.setForeground(new java.awt.Color(102, 102, 102));
        menu_title34.setText("FOUNDATION");

        menu_title35.setBackground(new java.awt.Color(96, 96, 96));
        menu_title35.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title35.setForeground(new java.awt.Color(102, 102, 102));
        menu_title35.setText("Prepared by:");

        menu_title36.setBackground(new java.awt.Color(96, 96, 96));
        menu_title36.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        menu_title36.setForeground(new java.awt.Color(102, 102, 102));
        menu_title36.setText("Prepared by:");

        javax.swing.GroupLayout print2Layout = new javax.swing.GroupLayout(print2);
        print2.setLayout(print2Layout);
        print2Layout.setHorizontalGroup(
            print2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(print2Layout.createSequentialGroup()
                .addGroup(print2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(menu_title23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(print2Layout.createSequentialGroup()
                        .addGroup(print2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(print2Layout.createSequentialGroup()
                                .addGap(256, 256, 256)
                                .addComponent(jLabel79)
                                .addGap(18, 18, 18)
                                .addGroup(print2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(menu_title20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(menu_title21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(menu_title22)
                                    .addComponent(menu_title34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(print2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(print2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(menu_title28)
                                    .addComponent(menu_title30))
                                .addGap(18, 18, 18)
                                .addGroup(print2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(menu_title31)
                                    .addGroup(print2Layout.createSequentialGroup()
                                        .addComponent(menu_title29)
                                        .addGap(426, 426, 426)
                                        .addComponent(menu_title32)
                                        .addGap(18, 18, 18)
                                        .addComponent(menu_title33))))
                            .addGroup(print2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane38, javax.swing.GroupLayout.PREFERRED_SIZE, 834, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(print2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(menu_title35)
                                .addGap(18, 18, 18)
                                .addComponent(menu_title36))
                            .addGroup(print2Layout.createSequentialGroup()
                                .addGap(68, 68, 68)
                                .addComponent(menu_title26)))
                        .addGap(0, 9, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(print2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(menu_title27, javax.swing.GroupLayout.PREFERRED_SIZE, 834, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        print2Layout.setVerticalGroup(
            print2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(print2Layout.createSequentialGroup()
                .addGroup(print2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(print2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel79))
                    .addGroup(print2Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(menu_title20, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(menu_title34, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(menu_title21, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(menu_title22, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(menu_title23, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(menu_title27, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(menu_title26, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane38, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(print2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(menu_title28, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menu_title29, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menu_title32, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menu_title33, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(print2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(menu_title30, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menu_title31, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                .addGroup(print2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(menu_title35, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menu_title36, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47))
        );

        jPanel1.add(print2, "card11");

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
            this.dispose();
        }
        else if(evt.getKeyCode()==KeyEvent.VK_ESCAPE){
            this.dispose();
        }
    }//GEN-LAST:event_jTable7KeyPressed

    private void jTable8KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable8KeyPressed
         if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            //printop();
            this.dispose();
        }
        else if(evt.getKeyCode()==KeyEvent.VK_ESCAPE){
            this.dispose();
        }
    }//GEN-LAST:event_jTable8KeyPressed

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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane37;
    private javax.swing.JScrollPane jScrollPane38;
    private javax.swing.JTable jTable7;
    private javax.swing.JTable jTable8;
    private javax.swing.JLabel menu_title10;
    private javax.swing.JLabel menu_title11;
    private javax.swing.JLabel menu_title12;
    private javax.swing.JLabel menu_title13;
    private javax.swing.JLabel menu_title14;
    private javax.swing.JLabel menu_title15;
    private javax.swing.JLabel menu_title16;
    private javax.swing.JLabel menu_title17;
    private javax.swing.JLabel menu_title18;
    private javax.swing.JLabel menu_title19;
    private javax.swing.JLabel menu_title20;
    private javax.swing.JLabel menu_title21;
    private javax.swing.JLabel menu_title22;
    private javax.swing.JLabel menu_title23;
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
    private javax.swing.JLabel menu_title4;
    private javax.swing.JLabel menu_title5;
    private javax.swing.JLabel menu_title6;
    private javax.swing.JLabel menu_title7;
    private javax.swing.JLabel menu_title8;
    private javax.swing.JLabel menu_title9;
    private javax.swing.JPanel print;
    private javax.swing.JPanel print2;
    private javax.swing.JPanel starter;
    // End of variables declaration//GEN-END:variables
}
