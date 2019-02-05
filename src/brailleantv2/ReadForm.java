/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brailleantv2;

import com.fazecast.jSerialComm.*;
import java.awt.Color;

/*
 * @author Zildjian
 */
public class ReadForm extends javax.swing.JFrame {

    /**
     * Creates new form ReadForm
     */
    String originalString = "";
    SerialPort port;
    TTS tts;
    int bufferIndex = 0;
    private int totalFiles;
    private int currentFileIndex;
    private String[] filenames;
    public void init() {
        
        this.getContentPane().setBackground(Color.cyan);
        System.out.println(SerialPort.getCommPorts()[0].getSystemPortName());
        try {
            java.util.Scanner sc = new java.util.Scanner(new java.io.File("/home/pi/Documents/Brailleant/out.txt"));
            while (sc.hasNext()) {
                originalString = originalString.concat(sc.nextLine() + "\n");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        for(SerialPort port : SerialPort.getCommPorts()){
            System.out.println("Port Name: "+port.getDescriptivePortName());
            System.out.println("Baud Rate: "+port.getBaudRate());
            System.out.println("===============================");
        }
        
        port = SerialPort.getCommPort("ttyACM0");
        
        port.openPort();
        port.addDataListener(new SerialPortDataListener() {
            @Override
            public int getListeningEvents() {
                return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
            }

            @Override
            public void serialEvent(SerialPortEvent event) {
                if (event.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE) {
                    return;
                }
                byte[] in = new byte[1];
                int numRead = port.readBytes(in, in.length);
                if ((char) in[0] == 'n') {
                    bufferIndex += 8;
                    String toSendString = "";
                    try {
                        toSendString = txt_content.getText().substring(bufferIndex, bufferIndex + 8);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        bufferIndex = txt_content.getText().length() - 9;
                        toSendString = txt_content.getText().substring(bufferIndex, txt_content.getText().length() - 1);
                    }
                    lbl_buffer.setText(toSendString);
                    byte[] toWriteBytes = new byte[8];
                    for (int ctr = 0; ctr < 8; ctr++) {
                        toWriteBytes[ctr] = (byte) toSendString.charAt(ctr);
                    }
                    port.writeBytes(toWriteBytes, 8);
                } else if ((char) in[0] == 'p') {
                    bufferIndex -= 8;
                    if (bufferIndex < 0) {
                        bufferIndex = 0;
                    }
                    String toSendString = "";
                    try {
                        toSendString = txt_content.getText().substring(bufferIndex, bufferIndex + 8);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        bufferIndex = txt_content.getText().length() - 9;
                        toSendString = txt_content.getText().substring(bufferIndex, txt_content.getText().length() - 1);
                    }
                    lbl_buffer.setText(toSendString);
                    byte[] toWriteBytes = new byte[8];
                    for (int ctr = 0; ctr < 8; ctr++) {
                        toWriteBytes[ctr] = (byte) toSendString.charAt(ctr);
                    }
                    port.writeBytes(toWriteBytes, 8);
                } else {
                    System.out.println("Unhandled : " + (char) in[0]);
                }
            }
        });
        txt_content.setText(originalString);
    }

    public void init(String filename) {
        //System.out.println(SerialPort.getCommPorts()[0].getSystemPortName());
        
        this.getContentPane().setBackground(Color.cyan);
        originalString="";
        lbl_filename.setText(filename);
        tts.speak("File "+filename+" opened");
        java.io.File file=new java.io.File(Global.FILES_DIR);
        filenames=file.list();
        this.totalFiles=filenames.length;
        
        for(int ctr=0; ctr<filenames.length; ctr++){
            if(filenames[ctr].equals(filename)){
                this.currentFileIndex=ctr;
            }
        }
        try {
            java.util.Scanner sc = new java.util.Scanner(new java.io.File("/home/pi/Documents/Brailleant/Files/"+filename));
            while (sc.hasNext()) {
                originalString = originalString.concat(sc.nextLine() + "\n");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        txt_content.setText(originalString);
    }
    
    public void initPort(){
        
        for(SerialPort port : SerialPort.getCommPorts()){
            System.out.println("Port Name: "+port.getDescriptivePortName());
            System.out.println("Baud Rate: "+port.getBaudRate());
            System.out.println("===============================");
        }
        port = SerialPort.getCommPort("ttyACM0");
        port.openPort();
        port.addDataListener(new SerialPortDataListener() {
            @Override
            public int getListeningEvents() {
                return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
            }

            @Override
            public void serialEvent(SerialPortEvent event) {
                if (event.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE) {
                    return;
                }
                byte[] in = new byte[1];
                int numRead = port.readBytes(in, in.length);
                if ((char) in[0] == 'n') {
                    bufferIndex += 8;
                    String toSendString = "";
                    try {
                        toSendString = txt_content.getText().substring(bufferIndex, bufferIndex + 8);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        bufferIndex = txt_content.getText().length() - 9;
                        toSendString = txt_content.getText().substring(bufferIndex, txt_content.getText().length() - 1);
                    }
                    lbl_buffer.setText(toSendString);
                    byte[] toWriteBytes = new byte[8];
                    for (int ctr = 0; ctr < 8; ctr++) {
                        toWriteBytes[ctr] = (byte) toSendString.charAt(ctr);
                    }
                    port.writeBytes(toWriteBytes, 8);
                } else if ((char) in[0] == 'p') {
                    bufferIndex -= 8;
                    if (bufferIndex < 0) {
                        bufferIndex = 0;
                    }
                    String toSendString = "";
                    try {
                        toSendString = txt_content.getText().substring(bufferIndex, bufferIndex + 8);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        bufferIndex = txt_content.getText().length() - 9;
                        toSendString = txt_content.getText().substring(bufferIndex, txt_content.getText().length() - 1);
                    }
                    lbl_buffer.setText(toSendString);
                    byte[] toWriteBytes = new byte[8];
                    for (int ctr = 0; ctr < 8; ctr++) {
                        toWriteBytes[ctr] = (byte) toSendString.charAt(ctr);
                    }
                    port.writeBytes(toWriteBytes, 8);
                } else {
                    System.out.println("Unhandled : " + (char) in[0]);
                }
            }
        });
    }
    public ReadForm() {
        initComponents();
        init();
        initPort();
    }

    public ReadForm(TTS tts) {
        this.tts = tts;
        initComponents();
        init();
        initPort();
    }

    public ReadForm(TTS tts, String filename) {
        this.tts = tts;
        initComponents();
        init(filename);
        initPort();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_Read = new javax.swing.JButton();
        btn_MainMenu = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lbl_buffer = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_content = new javax.swing.JTextArea();
        btn_edit = new javax.swing.JButton();
        btn_prev = new javax.swing.JButton();
        btn_next = new javax.swing.JButton();
        btn_files = new javax.swing.JButton();
        lbl_filename = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setLocationByPlatform(true);
        setMaximumSize(new java.awt.Dimension(480, 320));
        setMinimumSize(new java.awt.Dimension(480, 320));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(480, 320));
        setResizable(false);

        btn_Read.setText("READ");
        btn_Read.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_ReadMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_ReadMouseEntered(evt);
            }
        });
        btn_Read.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ReadActionPerformed(evt);
            }
        });

        btn_MainMenu.setText("Main Menu");
        btn_MainMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_MainMenuMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_MainMenuMouseEntered(evt);
            }
        });

        jLabel1.setText("Braille Out");

        lbl_buffer.setToolTipText("");

        txt_content.setEditable(false);
        txt_content.setColumns(20);
        txt_content.setRows(5);
        txt_content.setMaximumSize(null);
        txt_content.setMinimumSize(null);
        txt_content.setName(""); // NOI18N
        txt_content.setPreferredSize(null);
        jScrollPane1.setViewportView(txt_content);

        btn_edit.setText("Edit");
        btn_edit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_editMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_editMouseEntered(evt);
            }
        });

        btn_prev.setText("<<");
        btn_prev.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_prevMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_prevMouseEntered(evt);
            }
        });

        btn_next.setText(">>");
        btn_next.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_nextMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_nextMouseEntered(evt);
            }
        });

        btn_files.setText("Files");
        btn_files.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_filesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_filesMouseEntered(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_prev, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_next, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_filename, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                        .addGap(231, 231, 231))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lbl_buffer, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btn_Read, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
                                    .addComponent(btn_edit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(btn_files, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btn_MainMenu, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)))))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_next, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_prev, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbl_filename, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_Read, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_files, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                            .addComponent(btn_edit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(btn_MainMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(13, 13, 13)
                .addComponent(lbl_buffer)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_ReadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ReadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_ReadActionPerformed

    private void btn_ReadMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ReadMouseEntered
        // TODO add your handling code here:
        tts.speak("Reed");
    }//GEN-LAST:event_btn_ReadMouseEntered

    private void btn_MainMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_MainMenuMouseClicked
        // TODO add your handling code here:
        new MainForm(this.tts).setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_MainMenuMouseClicked

    private void btn_MainMenuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_MainMenuMouseEntered
        // TODO add your handling code here:
        tts.speak("Main Menu");

    }//GEN-LAST:event_btn_MainMenuMouseEntered

    private void btn_ReadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ReadMouseClicked
        // TODO add your handling code here:
        tts.speak(txt_content.getText());
    }//GEN-LAST:event_btn_ReadMouseClicked

    private void btn_prevMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_prevMouseEntered
        // TODO add your handling code here:
        tts.speak("Previous File");
    }//GEN-LAST:event_btn_prevMouseEntered

    private void btn_nextMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_nextMouseEntered
        // TODO add your handling code here:
        tts.speak("Next File");
    }//GEN-LAST:event_btn_nextMouseEntered

    private void btn_prevMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_prevMouseClicked
        // TODO add your handling code here:
        if(currentFileIndex==0) currentFileIndex=totalFiles-1;
        else currentFileIndex--;
        init(filenames[currentFileIndex]);
    }//GEN-LAST:event_btn_prevMouseClicked

    private void btn_nextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_nextMouseClicked
        // TODO add your handling code here:
        if(currentFileIndex+1==totalFiles) currentFileIndex=0;
        else currentFileIndex++;
        init(filenames[currentFileIndex]);
    }//GEN-LAST:event_btn_nextMouseClicked

    private void btn_editMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_editMouseEntered
        // TODO add your handling code here:
        tts.speak("edit file");
    }//GEN-LAST:event_btn_editMouseEntered

    private void btn_editMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_editMouseClicked
        // TODO add your handling code here:
        new EditForm(this.tts,this.filenames[currentFileIndex]).setVisible(true);
        dispose();
    }//GEN-LAST:event_btn_editMouseClicked

    private void btn_filesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_filesMouseEntered
        // TODO add your handling code here:
        tts.speak("Files");
    }//GEN-LAST:event_btn_filesMouseEntered

    private void btn_filesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_filesMouseClicked
        // TODO add your handling code here:
        new FilesForm(this.tts).setVisible(true);
        dispose();
    }//GEN-LAST:event_btn_filesMouseClicked

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
            java.util.logging.Logger.getLogger(ReadForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReadForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReadForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReadForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReadForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_MainMenu;
    private javax.swing.JButton btn_Read;
    private javax.swing.JButton btn_edit;
    private javax.swing.JButton btn_files;
    private javax.swing.JButton btn_next;
    private javax.swing.JButton btn_prev;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_buffer;
    private javax.swing.JLabel lbl_filename;
    private javax.swing.JTextArea txt_content;
    // End of variables declaration//GEN-END:variables
}
