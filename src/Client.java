import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java .text.*;
import java.net.*;
import java.io.*;
public class Client implements ActionListener {
    JTextField Txt;
   static JPanel A1;
   static JFrame f=new JFrame();
    static Box vertical=Box.createVerticalBox();
   static DataOutputStream dout;
    Client(){
        f.setLayout(null);
        JPanel p1=new JPanel();
        //   p1.setBackground(Color.GREEN);                //this is not the wrong way but will not use this method because it will provide the default green
        // color.so will make object of the color class set the color manually.
        p1.setBackground(new Color(7,94,84));
        p1.setBounds(0,0,450,70);        //because we set the layout to null so ,now we have to tell our desired layout
        p1.setLayout(null);                                //to adjust arrow to the desired corner and to carry image setBounds() method on line no.17
        f.add(p1);
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/icons/3.png"));        //ImageIcon and ClassLoader are the classes .
        Image i2=i1.getImage().getScaledInstance(25,25,Image.SCALE_DEFAULT);             //now the image is scaled according to the desired size .(using Image class of the awt package)
        ImageIcon i3=new ImageIcon(i2);                                                              //but i2 cannot pass to jLabel directly so, we created i3
        // of the ImageIcon class and passed to the Jlabel
        JLabel back=new JLabel(i3);
        back.setBounds(5,20,25,25);
        p1.add(back);

        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });
        //***** profile picture *****
        ImageIcon i4=new ImageIcon(ClassLoader.getSystemResource("icons/icons/NASA.png"));
        Image i5=i4.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT);
        ImageIcon i6=new ImageIcon(i5);
        JLabel Profile=new JLabel(i6);
        Profile.setBounds(40,10,50,50);
        p1.add(Profile);
        //*****video call icon****
        ImageIcon i7=new ImageIcon(ClassLoader.getSystemResource("icons/icons/video.png"));
        Image i8=i7.getImage().getScaledInstance(30,30,Image.SCALE_DEFAULT);
        ImageIcon i9=new ImageIcon(i8);
        JLabel video=new JLabel(i9);
        video.setBounds(300,20,30,30);
        p1.add(video);
        // *****Audio Call icon*****
        ImageIcon i10=new ImageIcon(ClassLoader.getSystemResource("icons/icons/phone.png"));
        Image i11=i10.getImage().getScaledInstance(35,30,Image.SCALE_DEFAULT);
        ImageIcon i12=new ImageIcon(i11);
        JLabel phone=new JLabel(i12);
        phone.setBounds(360,20,35,30);
        p1.add(phone);
        //*******more option icon****
        ImageIcon i13=new ImageIcon(ClassLoader.getSystemResource("icons/icons/3icon.png"));
        Image i14=i13.getImage().getScaledInstance(10,25,Image.SCALE_DEFAULT);
        ImageIcon i15=new ImageIcon(i14);
        JLabel MoreOpt=new JLabel(i15);
        MoreOpt.setBounds(420,20,10,25);
        p1.add(MoreOpt);
        //*****sender name ****
        JLabel name=new JLabel("NASA ADMIN ");
        name.setBounds(110,15,100,18);
        name.setForeground(Color.white);
        name.setFont(new Font("SAN_SERIF",Font.BOLD,15));   
        p1.add(name);
        //***active status*****
        JLabel status=new JLabel("Active Now");
        status.setBounds(110,35,100,18);
        status.setForeground(Color.white);
        name.setFont(new Font("SAN_SERIF",Font.BOLD,12));
        p1.add(status);
        //****chat Area***
        //JPanel A1=new JPanel();                                                   Also the JPanel is declared globally to access it anywhere
        A1=new JPanel();
        A1.setBounds(5,75,450,570);
        f.add(A1);
        //***Text Field***                                                          //now the working is that the text is not displaying in the chat area
        //JTextField Txt=new JTextField();                                           // so,we have to use the addAction Listener(this) method of the interface Action Listener
        Txt=new JTextField();
        Txt.setBounds(5,655,310,40);                               //on line no.89
        Txt.setFont(new Font("SAN_SERIF",Font.PLAIN,16));                  //and to tell which function should it perform after the click we will override the abstract
        f.add(Txt);                                                                 //method of Action listener
        // for this purpose we have to JTextfield globally to access in the method of Action listener
        //***Send Button***
        JButton Button=new JButton("Send");
        Button.setBounds(320,655,123,40);
        Button.setBackground(new Color(7,94,84));
        Button.setForeground(Color.white);
        Txt.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
        Button.addActionListener(this);
        f.add(Button);

        f.setSize(450,700);
        f.setLocation(900,30);
        f.setUndecorated(true);
        f.getContentPane().setBackground(Color.white);
        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String out=Txt.getText();

            JLabel output=new JLabel(out);

            //JPanel p2=new JPanel();                                                as we are using method formatLabel() to return the output so we will ignore the line no.109 and 111
            JPanel p2=formatLable(out);
            //p2.add(output);
            A1.setLayout(new BorderLayout());
            JPanel right=new JPanel(new BorderLayout());
            right.add(p2,BorderLayout.LINE_END);                                    //to send the message on end of the horizontal line
            vertical.add(right);
            vertical.add(Box.createVerticalStrut(15));                         //15 is the vertical spacing b/w the messages
            A1.add(vertical,BorderLayout.PAGE_START);                                  //the start point
            dout.writeUTF(out);
            Txt.setText("");

        }catch (Exception ae){
            ae.printStackTrace();
        }

        f.repaint();                  //to repaint the chat Area we use these three functions
        f.invalidate();
        f.validate();


    }
    public static JPanel formatLable(String out){
        JPanel panel=new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        JLabel output=new JLabel(out);
        output.setFont(new Font("Tahoma",Font.PLAIN,20));
        output.setBackground(new Color(37,211,102));
        output.setOpaque(true);
        output.setBorder(new EmptyBorder(15,15,15,50));
        panel.add(output);
        Calendar cal=Calendar.getInstance();
        SimpleDateFormat sdf=new SimpleDateFormat("HH:mm");
        JLabel time=new JLabel();
        time.setText(sdf.format(cal.getTime()));
        panel.add(time);

        return panel;
    }
    public static void main(String[] args) {

        new Client();
        try {
            A1.setLayout(new BorderLayout());
            Socket s=new Socket("127.0.0.1",6001);
            DataInputStream din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());
            while (true) {
                String msg = din.readUTF();
                JPanel panel = formatLable(msg);
                JPanel left = new JPanel(new BorderLayout());
                left.add(panel, BorderLayout.LINE_START);
                vertical.add(left);
                vertical.add(Box.createVerticalStrut(15));
                A1.add(vertical,BorderLayout.PAGE_START);
                f.validate();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
