/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library_system;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Menu;
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
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Support
 */
public class login extends javax.swing.JFrame {
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;
    /**
     * Creates new form login
     */
    public login() {
        initComponents();
        conn = (Connection) MySqlConnect.ConnectDB();
        CurrentDate();
        all_ref();
        start();
    }
    
    public void print(){
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
    }

    public void start(){
        setBackground(new Color(0,0,0,150));
        loginpanel.setBackground(new Color(0,0,0,150));
        Login.setBackground(new Color(0,0,0,150));
        publicsearch.setBackground(new Color(0,0,0,150));
        jPanel68.setBackground(new Color(0,0,0,150));
        jPanel2.setBackground(new Color(0,0,0,150));
        jPanel3.setBackground(new Color(0,0,0,150));
    }
    public void login(){
    try {
        String sql = "SELECT * FROM useraccount_tbl where Username =? and Password =?";
            pst = (PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, user.getText());
            pst.setString(2, pass.getText());
            
            rs = pst.executeQuery();
            if (rs.next()) {

            pst = (PreparedStatement) conn.prepareStatement("SELECT ID, First_Name, Middle_Name, Surname, Level  FROM useraccount_tbl where Username= '" + user.getText() + "' and Password= '" + pass.getText() + "' ");
            rs = pst.executeQuery();
            if(rs.next()){
                String add = rs.getString("ID");
                String add1 = rs.getString("First_Name");
                String add2 = rs.getString("Middle_Name");
                String add3 = rs.getString("Surname");
                String add4 = rs.getString("Level");
                txt_namelog.setText(add3+", "+add1+" "+add2);
                txt_levellog.setText(add4);
                iddlog.setText(add);
                sumup.setText("0");
                
                menu_titlelog.setText("Hi, "+txt_namelog.getText()+" ("+txt_levellog.getText()+")");
            String msg = txt_namelog.getText();
            String msg1 = txt_levellog.getText();
            String msg2= iddlog.getText();
            String msg3= menu_titlelog.getText();
                
                JOptionPane.showMessageDialog(null, "Acess Granted!");
            Login.setVisible(false);
            publicsearch.setVisible(false);
            new Main().setVisible(true);
            this.setVisible(false);
            }  
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
        }
            
        user.setText("");
        pass.setText("");
        start();
        inlog();
        all_ref();
    }
    public void inlog(){
        try {
                    String sql = "Insert into account_log_tbl (User_Name, Activity, Status, "
                    + "Date, Time) values (?,?,?,?,?)";
                    pst = (com.mysql.jdbc.PreparedStatement) conn.prepareStatement(sql);
                    pst.setString(1, txt_namelog.getText());
                    pst.setString(2, "Log-in");
                    pst.setString(3, txt_levellog.getText());
                    pst.setString(4, jLabel2log.getText());
                    pst.setString(5, jLabel3log.getText());
                    pst.execute();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
    }
    public void all_ref(){
    public_ref();
    classif_list();
    }
    public void CurrentDate() {//date and time to toolbar running

        new Timer(0, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Date d = new Date();
                SimpleDateFormat s = new SimpleDateFormat("h:mm aa");
                jLabel2log.setText(s.format(d));

                SimpleDateFormat st = new SimpleDateFormat("M/d/yyyy");
                jLabel3log.setText(st.format(d));
                
            }
        })
                .start();
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
    public void classif_list(){
        jComboBox1.removeAllItems();
    String sqll = "select * from classification_tbl";
    try{
        pst= conn.prepareStatement(sqll);
        rs = pst.executeQuery();
        while(rs.next()){
            jComboBox1.addItem(rs.getString("Classname"));
        }
    }catch (Exception e) {
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

        jPanel1 = new javax.swing.JPanel();
        publicsearch = new javax.swing.JPanel();
        nb_new8 = new javax.swing.JButton();
        jPanel68 = new javax.swing.JPanel();
        jLabel164 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel165 = new javax.swing.JLabel();
        jLabel166 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
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
        jLabel6 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        txt_namelog = new javax.swing.JLabel();
        txt_levellog = new javax.swing.JLabel();
        iddlog = new javax.swing.JLabel();
        menu_titlelog = new javax.swing.JLabel();
        jLabel2log = new javax.swing.JLabel();
        jLabel3log = new javax.swing.JLabel();
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
        menu_title17 = new javax.swing.JLabel();
        menu_title18 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new java.awt.CardLayout());

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

        jButton2.setText("jButton2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel68Layout = new javax.swing.GroupLayout(jPanel68);
        jPanel68.setLayout(jPanel68Layout);
        jPanel68Layout.setHorizontalGroup(
            jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel68Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel164)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel165))
                .addGap(18, 18, 18)
                .addGroup(jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel166)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(82, Short.MAX_VALUE))
        );
        jPanel68Layout.setVerticalGroup(
            jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel68Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel68Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel165)
                        .addComponent(jButton2))
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

        Login.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        Login.add(loginpanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(325, 65, -1, -1));

        exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/error.png"))); // NOI18N
        exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitMouseClicked(evt);
            }
        });
        Login.add(exit, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 11, -1, -1));

        minimize.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/minimize.png"))); // NOI18N
        minimize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                minimizeMouseClicked(evt);
            }
        });
        Login.add(minimize, new org.netbeans.lib.awtextra.AbsoluteConstraints(1052, 11, -1, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/bkg.png"))); // NOI18N
        Login.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1150, 710));

        jPanel1.add(Login, "card2");

        txt_namelog.setText("jLabel2");

        txt_levellog.setText("jLabel3");

        iddlog.setText("jLabel2");

        menu_titlelog.setText("jLabel2");

        jLabel2log.setText("jLabel2");

        jLabel3log.setText("jLabel3");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(menu_titlelog)
                    .addComponent(iddlog)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_namelog)
                            .addComponent(txt_levellog))
                        .addGap(140, 140, 140)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3log)
                            .addComponent(jLabel2log))))
                .addContainerGap(913, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_namelog)
                    .addComponent(jLabel2log))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_levellog)
                    .addComponent(jLabel3log))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(iddlog)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menu_titlelog)
                .addContainerGap(625, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel4, "card4");

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
                            .addComponent(menu_title5)))
                    .addGroup(printLayout.createSequentialGroup()
                        .addGap(160, 160, 160)
                        .addComponent(menu_title6, javax.swing.GroupLayout.PREFERRED_SIZE, 502, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(menu_title16))))
                    .addGroup(printLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(menu_title17)
                        .addGap(18, 18, 18)
                        .addComponent(menu_title18))
                    .addGroup(printLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(printLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane37, javax.swing.GroupLayout.PREFERRED_SIZE, 834, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(printLayout.createSequentialGroup()
                                .addComponent(menu_title7)
                                .addGap(18, 18, 18)
                                .addComponent(menu_title8)
                                .addGap(311, 311, 311)
                                .addComponent(menu_title9)
                                .addGap(18, 18, 18)
                                .addComponent(menu_title10, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(306, Short.MAX_VALUE))
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
                        .addGap(33, 33, 33)
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
                .addComponent(jScrollPane37, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(printLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(menu_title11, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menu_title12, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menu_title15, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menu_title16, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(printLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(menu_title13, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menu_title14, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 128, Short.MAX_VALUE)
                .addGroup(printLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(menu_title17, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menu_title18, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38))
        );

        jPanel1.add(print, "card11");

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1150, 710));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void nb_new8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nb_new8ActionPerformed
        Login.setVisible(true);
        publicsearch.setVisible(false);
        publicsearch.setVisible(false);
        jPanel68.setVisible(false);
        jPanel2.setVisible(false);
        jPanel3.setVisible(false);
        nb_new8.setVisible(false);
        jLabel163.setVisible(false);
        jTextField2.setVisible(false);
        jComboBox1.setVisible(false);

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

    private void showpassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showpassMouseClicked
        if (showpass.isSelected()) {
            pass.setEchoChar((char)0); //password = JPasswordField
        } else {
            pass.setEchoChar('*');
        }
    }//GEN-LAST:event_showpassMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        login();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton1KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            login();
        }
    }//GEN-LAST:event_jButton1KeyPressed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Login.setVisible(false);
        publicsearch.setVisible(true);
        jPanel68.setVisible(true);
        jPanel2.setVisible(true);
        jPanel3.setVisible(true);
        nb_new8.setVisible(true);
        jLabel163.setVisible(true);
        jTextField2.setVisible(true);
        jComboBox1.setVisible(true);
        public_ref();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3KeyPressed

    private void exitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitMouseClicked
        System.exit(0);
    }//GEN-LAST:event_exitMouseClicked

    private void minimizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minimizeMouseClicked
        this.setState(this.ICONIFIED);
    }//GEN-LAST:event_minimizeMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        print();
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Login;
    private javax.swing.JLabel exit;
    public static javax.swing.JLabel iddlog;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel163;
    private javax.swing.JLabel jLabel164;
    private javax.swing.JLabel jLabel165;
    private javax.swing.JLabel jLabel166;
    private javax.swing.JLabel jLabel167;
    private javax.swing.JLabel jLabel168;
    private javax.swing.JLabel jLabel169;
    private javax.swing.JLabel jLabel170;
    private javax.swing.JLabel jLabel171;
    private javax.swing.JLabel jLabel172;
    private javax.swing.JLabel jLabel173;
    private javax.swing.JLabel jLabel174;
    public static javax.swing.JLabel jLabel2log;
    public static javax.swing.JLabel jLabel3log;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel68;
    private javax.swing.JScrollPane jScrollPane31;
    private javax.swing.JScrollPane jScrollPane37;
    private javax.swing.JTable jTable7;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JPanel loginpanel;
    private javax.swing.JLabel menu_title10;
    private javax.swing.JLabel menu_title11;
    private javax.swing.JLabel menu_title12;
    private javax.swing.JLabel menu_title13;
    private javax.swing.JLabel menu_title14;
    private javax.swing.JLabel menu_title15;
    private javax.swing.JLabel menu_title16;
    private javax.swing.JLabel menu_title17;
    private javax.swing.JLabel menu_title18;
    private javax.swing.JLabel menu_title3;
    private javax.swing.JLabel menu_title4;
    private javax.swing.JLabel menu_title5;
    private javax.swing.JLabel menu_title6;
    private javax.swing.JLabel menu_title7;
    private javax.swing.JLabel menu_title8;
    private javax.swing.JLabel menu_title9;
    public static javax.swing.JLabel menu_titlelog;
    private javax.swing.JLabel minimize;
    private javax.swing.JButton nb_new8;
    private javax.swing.JButton nb_new9;
    private javax.swing.JPasswordField pass;
    private javax.swing.JPanel print;
    private javax.swing.JPanel publicsearch;
    private javax.swing.JCheckBox showpass;
    private javax.swing.JTable sumpatable;
    private javax.swing.JLabel sumup;
    public static javax.swing.JLabel txt_levellog;
    public static javax.swing.JLabel txt_namelog;
    private javax.swing.JTextField user;
    // End of variables declaration//GEN-END:variables
}
