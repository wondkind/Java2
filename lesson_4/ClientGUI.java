package Java2.lesson_4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.nio.file.Paths;


public class ClientGUI extends JFrame  implements ActionListener, Thread.UncaughtExceptionHandler, KeyListener, WindowListener {

    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private final String logFile = "log.txt";

    private final JTextArea log = new JTextArea();
    private final JPanel panelTop = new JPanel(new GridLayout(2, 3));
    private final JTextField tfIPAddress = new JTextField("127.0.0.1");
    private final JTextField tfPort = new JTextField("8189");
    private final JCheckBox cbAlwaysOnTop = new JCheckBox("Always on top");
    private final JTextField tfLogin = new JTextField("ivan");
    private final JPasswordField tfPassword = new JPasswordField("123");
    private final JButton btnLogin = new JButton("Login");

    private final JPanel panelBottom = new JPanel(new BorderLayout());
    private final JButton btnDisconnect = new JButton("<html><b>Disconnect</b></html>");
    private final JTextField tfMessage = new JTextField();
    private final JButton btnSend = new JButton("Send");

    private final JList<String> userList = new JList<>();


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ClientGUI();
            }
        });
    }

    private ClientGUI() {

        Thread.setDefaultUncaughtExceptionHandler(this);

        try {
            log.setText(new String(Files.readAllBytes(Paths.get(logFile))));
        }
        catch (IOException e){
            JOptionPane.showMessageDialog(this, e.getMessage(), "Exception", JOptionPane.INFORMATION_MESSAGE);
        }

        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(WIDTH, HEIGHT);
        setTitle("Chat Client");
        log.setEditable(false);
        JScrollPane scrollLog = new JScrollPane(log);
        JScrollPane scrollUsers = new JScrollPane(userList);
        String[] users = {"user1", "user2", "user3", "user4", "user5", "User_with_a_very_long_name" };
        userList.setListData(users);
        scrollUsers.setPreferredSize(new Dimension(100, 0));

        panelTop.add(tfIPAddress);
        panelTop.add(tfPort);
        panelTop.add(cbAlwaysOnTop);
        panelTop.add(tfLogin);
        panelTop.add(tfPassword);
        panelTop.add(btnLogin);
        panelBottom.add(btnDisconnect, BorderLayout.WEST);
        panelBottom.add(tfMessage, BorderLayout.CENTER);
        panelBottom.add(btnSend, BorderLayout.EAST);

        cbAlwaysOnTop.addActionListener(this);
        btnSend.addActionListener(this);
        tfMessage.addKeyListener(this);
        addWindowListener(this);

        add(panelTop, BorderLayout.NORTH);
        add(panelBottom, BorderLayout.SOUTH);
        add(scrollLog, BorderLayout.CENTER);
        add(scrollUsers, BorderLayout.EAST);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src == cbAlwaysOnTop) {
            setAlwaysOnTop(cbAlwaysOnTop.isSelected());
        } else if (src == btnSend) {
            sendMessage();
        } else {
            throw new RuntimeException("Unknown source: " + src);
        }
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        e.printStackTrace();
        String msg;
        StackTraceElement[] ste = e.getStackTrace();
        if (ste.length == 0) {
            msg = "Empty Stacktrace";
        } else {
            msg = e.getClass().getCanonicalName() + ": " +
                    e.getMessage() + "\n\t at " + ste[0];
        }
        JOptionPane.showMessageDialog(this, msg, "Exception", JOptionPane.ERROR_MESSAGE);
        System.exit(1);
    }

    @Override
    public void keyTyped(KeyEvent k){

    }

    @Override
    public void keyPressed(KeyEvent k){

        if(k.getKeyCode() == KeyEvent.VK_ENTER){
            sendMessage();
        }

    }

    @Override
    public void keyReleased(KeyEvent k){

    }

    public void sendMessage(){
        if (!tfMessage.getText().equals("")) {
            log.append(dateFormat.format(new Date()) + ": " + tfMessage.getText() + '\n');
            tfMessage.setText("");
        }
    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent event) {

        try {
            PrintStream ps = new PrintStream(new FileOutputStream(logFile));
            String txt = log.getText();
            ps.println(txt);
        } catch (IOException except) {

        }
        event.getWindow().setVisible(false);
        System.exit(0);

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowOpened(WindowEvent e) {

    }
}
