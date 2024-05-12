import java.awt.HeadlessException;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

class Ticketing extends javax.swing.JFrame {

    private static final Map<String, Map<String, Integer>> fareMap = new HashMap<>();

    static {
        Map<String, Integer> sanJoseFares = new HashMap<>();
        sanJoseFares.put("Iloilo", 200);
        sanJoseFares.put("Anini-y", 50);
        sanJoseFares.put("Culasi", 300);
        sanJoseFares.put("San Remigio", 350);
        sanJoseFares.put("Pandan", 400);
        fareMap.put("San Jose", sanJoseFares);

        Map<String, Integer> iloiloFares = new HashMap<>();
        iloiloFares.put("San Jose", 200);
        iloiloFares.put("Anini-y", 150);
        iloiloFares.put("Culasi", 300);
        iloiloFares.put("San Remigio", 250);
        iloiloFares.put("Pandan", 500);
        fareMap.put("Iloilo", iloiloFares);

        Map<String, Integer> AniniYFares = new HashMap<>();
        AniniYFares.put("San Jose", 50);
        AniniYFares.put("Culasi", 250);
        AniniYFares.put("San Remigio", 200);
        AniniYFares.put("Pandan", 500);
        AniniYFares.put("Iloilo", 150);
        fareMap.put("Anini-y", AniniYFares);

        Map<String, Integer> CulasiFares = new HashMap<>();
        CulasiFares.put("San Jose", 100);
        CulasiFares.put("San Remigio", 50);
        CulasiFares.put("Pandan", 300);
        CulasiFares.put("Iloilo", 400);
        fareMap.put("Culasi", CulasiFares);


        Map<String, Integer> SanRemigioFares = new HashMap<>();
        SanRemigioFares.put("San Jose", 100);
        SanRemigioFares.put("Pandan", 250);
        SanRemigioFares.put("Iloilo", 400);
        SanRemigioFares.put("Anini-y", 200);
        fareMap.put("San Remigio", SanRemigioFares);

        Map<String, Integer> PandanFares = new HashMap<>();
        PandanFares.put("San Jose", 400);
        PandanFares.put("Iloilo", 800);
        PandanFares.put("Anini-y", 300);
        PandanFares.put("Culasi", 300);
        PandanFares.put("San Remigio", 350);
        fareMap.put("Pandan", PandanFares);

        // Add fares for other destinations
    }

    public Ticketing() {
        initComponents();
        Date date = new Date();
        ca1.getDayChooser().setMinSelectableDate(date);
    }

    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        t1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        ca1 = new com.toedter.calendar.JCalendar();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        t2 = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        t3 = new javax.swing.JTextField();
        b3 = new javax.swing.JButton();
        b2 = new javax.swing.JButton();
        b1 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        t = new javax.swing.JTextField();
        c1 = new javax.swing.JComboBox();
        c2 = new javax.swing.JComboBox();
        c3 = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel1.setText(" WELCOME TO BUS TICKETING SYSTEM");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel2.setText("From");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel3.setText("Destination");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel4.setText("No. of Passengers");

        t1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel5.setText("Time");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel6.setText("Departure Date");

        t2.setColumns(20);
        t2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        t2.setRows(5);
        jScrollPane1.setViewportView(t2);

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel7.setText("Ticket Summary");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel8.setText("Payble Amount");

        t3.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N

        b3.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        b3.setText("PRINT");
        b3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b3ActionPerformed(evt);
            }
        });

        b2.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        b2.setText("RESET");
        b2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b2ActionPerformed(evt);
            }
        });

        b1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        b1.setText("SUBMIT");
        b1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b1ActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel9.setText("Name of Passenger");

        t.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N

        c1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        c1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "San Jose", "Iloilo", "Anini-y", "Culasi", "San Remigio", "Pandan" }));

        c2.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        c2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pandan", "San Remigio", "Culasi", "Anini-y", "Iloilo", "San Jose" }));

        c3.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        c3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "6:00 am","7:00 am","8:00 am","9:00 am","10:00 am","11:00 am", "12:00 pm","1:00 pm", "2:00 pm","3:00 pm", "5:00 pm","6:00 pm", "7:00 pm" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(121, 121, 121)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addComponent(jLabel9)
                                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                .addComponent(ca1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                .addGroup(layout.createSequentialGroup()
                                                                                        .addGap(93, 93, 93)
                                                                                        .addComponent(jLabel2)
                                                                                        .addGap(33, 33, 33)
                                                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                .addComponent(t1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addComponent(c1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addComponent(jLabel6)
                                                                        .addComponent(jLabel4))
                                                                .addGap(204, 204, 204)))
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(87, 87, 87)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(jLabel3))
                                                                                .addGap(18, 18, 18)
                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(c2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(c3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                        .addComponent(jLabel7)
                                                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(47, 47, 47)
                                                                .addComponent(t, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(t3, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(280, 280, 280)))
                                .addGap(0, 154, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(188, 188, 188)
                                                .addComponent(jLabel1))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(392, 392, 392)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(31, 31, 31)
                                                                .addComponent(b2)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(b3))
                                                        .addComponent(jLabel8)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(472, 472, 472)
                                                .addComponent(b1)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel9)
                                        .addComponent(t, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jLabel3)
                                                                .addGap(18, 18, 18))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(c2)
                                                                .addGap(10, 10, 10)))
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel5)
                                                        .addComponent(c3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel7)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(9, 9, 9)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel2)
                                                        .addComponent(c1))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel4)
                                                        .addComponent(t1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel6)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(ca1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(19, 19, 19)))
                                .addComponent(b1)
                                .addGap(12, 12, 12)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel8)
                                        .addComponent(t3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(b2)
                                        .addComponent(b3))
                                .addGap(23, 23, 23))
        );

        pack();
        // Your existing initComponents code here
    }

    private void b1ActionPerformed(java.awt.event.ActionEvent evt) {
        String na = t.getText();
        if (na.equals("")) {
            JOptionPane.showMessageDialog(this, "Please Enter User Name");
            return;
        }

        int a, b, c;
        try {
            a = Integer.parseInt(t1.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid number of passengers");
            return;
        }

        String s1 = (String) c1.getSelectedItem();
        String s2 = (String) c2.getSelectedItem();
        if (s1.equals(s2)) {
            JOptionPane.showMessageDialog(this, "Source and destination cannot be the same");
            return;
        }

        int fare = getFare(s1, s2);
        if (fare == -1) {
            JOptionPane.showMessageDialog(this, "Fare information not available for this destination pair");
            return;
        }

        c = a * fare;
        t3.setText(Integer.toString(c));
        String s6 = (String) t3.getText();
        SimpleDateFormat date_form = new SimpleDateFormat("yyyy-MM-dd");
        String s5 = date_form.format(ca1.getDate());
        t2.setText("*************************************\nName of User: " + na + "\nFrom: " + s1 + "\nTo: "
                + s2 + "\nNo of Passenger: " + a + "\nTime: " + c3.getSelectedItem() + "\nDate of Journey: "
                + s5 + "\nPayable Amount: " + s6 + "\n*************************************");
    }

    private int getFare(String source, String destination) {
        Map<String, Integer> sourceFares = fareMap.get(source);
        if (sourceFares != null) {
            Integer fare = sourceFares.get(destination);
            if (fare != null) {
                return fare;
            }
        }
        return -1; // Fare information not available
    }

    private void b2ActionPerformed(java.awt.event.ActionEvent evt) {
        t.setText(null);
        t1.setText(null);
        t2.setText(null);
        t3.setText(null);
        c1.setSelectedItem("Pune");
        c2.setSelectedItem("Mumbai");
        c3.setSelectedItem("Economy");
    }

    private void b3ActionPerformed(java.awt.event.ActionEvent evt) {
        String na = t.getText();
        String s1 = (String) c1.getSelectedItem();
        String s2 = (String) c2.getSelectedItem();
        String s3 = (String) t1.getText();
        String s4 = (String) c3.getSelectedItem();
        SimpleDateFormat date_form = new SimpleDateFormat("yyyy-MM-dd");
        String s5 = date_form.format(ca1.getDate());
        String s6 = (String) t3.getText();

        try {
            try (FileWriter w = new FileWriter("BusBooking.txt", true)) {
                w.append("\n\nName of User: " + na + "\nTO: " + s1 + "\nFrom: " + s2 + "\nNo of Passenger: " + s3
                        + "\nTime: " + s4 + "\nDate of Journey: " + s5 + "\nPayble Amount: " + s6);
                w.write(System.getProperty("line.separator"));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error: " + e);
            }
            JOptionPane.showMessageDialog(this, "Ticket Successfully Printed");
            t.setText(null);
            t1.setText(null);
            t2.setText(null);
            t3.setText(null);
            c1.setSelectedItem("Pune");
            c2.setSelectedItem("Mumbai");
            c3.setSelectedItem("Economy");
        } catch (Exception ex) {
            Logger.getLogger(Ticketing.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ticketing().setVisible(true);
            }
        });
    }

    private javax.swing.JButton b1;
    private javax.swing.JButton b2;
    private javax.swing.JButton b3;
    private javax.swing.JComboBox c1;
    private javax.swing.JComboBox c2;
    private javax.swing.JComboBox c3;
    private com.toedter.calendar.JCalendar ca1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField t;
    private javax.swing.JTextField t1;
    private javax.swing.JTextArea t2;
    private javax.swing.JTextField t3;
}
