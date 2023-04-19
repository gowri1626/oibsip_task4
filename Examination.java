import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

class Login extends JFrame implements ActionListener {
    private JButton submitButton;
    private JPanel newPanel;
    private JLabel userLabel, passLabel;
    private final JTextField tf1;
    private final JPasswordField tf2;

    Login() {
        userLabel = new JLabel("Username: ");
        tf1 = new JTextField(10);
        passLabel = new JLabel("Password: ");
        tf2 = new JPasswordField(10);
        submitButton = new JButton("SUBMIT");
        newPanel = new JPanel(new GridLayout(3, 3));
        newPanel.add(userLabel);
        newPanel.add(tf1);
        newPanel.add(passLabel);
        newPanel.add(tf2);
        newPanel.add(new JLabel());
        newPanel.add(submitButton);
        add(newPanel, BorderLayout.CENTER);
        submitButton.addActionListener(this);
        setTitle("Login Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // center the window on the screen
        setResizable(false); // prevent resizing the window
        pack(); // adjust the size of the window to fit the components
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        String user = tf1.getText();
        String passValue = new String(tf2.getPassword());
        if (passValue.isEmpty()) {
            tf2.setText("Enter Password");
        } else {
            new choose(user);
            dispose(); // close the login window
        }
    }
}

// to choose update profile and start test

class choose extends JFrame implements ActionListener{
       JPanel option;
       JButton q1,q2;
       String user;

       choose(String user)
           {  super(user);
              this.user = user;
              q1 = new JButton("Update Profile");
              q2 = new JButton("Start Test");
              option = new JPanel(new GridLayout(1,2,20,30));
              option.add(q1);
              option.add(q2);
              add(option,BorderLayout.CENTER);
              q1.addActionListener(this);
              q2.addActionListener(this);
              setTitle("Portal");
              setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
              setLocationRelativeTo(null); // center the window on the screen
              setResizable(false); // prevent resizing the window
              pack(); // adjust the size of the window to fit the components
              setVisible(true);
          }

      public void actionPerformed(ActionEvent a)
       {
             if(a.getSource() == q2){
                new OnlineExam(user);
                dispose();
              }
             else {
                   new update(user);
                   //dispose();
              }
        }
  }

//To update profile

class update extends JFrame implements ActionListener{
      JPanel opt;
      JButton b1;
      JTextField text1,text2;
      JLabel l1,l2;
      update(String u)
           {  super (u);
              l1 = new JLabel("Username: ");
              text1 = new JTextField(10);
              l2 = new JLabel("Password: ");
              text2 = new JPasswordField(10);
              b1 = new JButton("CONFIRM");
              opt = new JPanel(new GridLayout(3, 3));
              opt.add(l1);
              opt.add(text1);
              opt.add(l2);
              opt.add(text2);
              opt.add(new JLabel());
              opt.add(b1);
              add(opt, BorderLayout.CENTER);
              b1.addActionListener(this);
              setTitle("Update Form");
              setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
              setLocationRelativeTo(null); 
              setResizable(false); 
              pack(); 
              setVisible(true);
           }

  public void actionPerformed(ActionEvent ac)
       {
             if(ac.getSource() == b1){
                JOptionPane.showMessageDialog(this, "updated successfully");
                dispose();
              }
        }
   }

class OnlineExam extends JFrame implements ActionListener {
    JLabel label1, label2;
    JRadioButton[] jb = new JRadioButton[6];
    JButton b1, b2;
    ButtonGroup buttonGroup;
    int count = 0, current = 0, x = 1, y = 1, now = 0;
    int m[] = new int[10];
    Timer timer;

    OnlineExam(String s) {
        super(s);
        label1 = new JLabel();
        label2 = new JLabel();
        add(label1);
        add(label2);
        buttonGroup = new ButtonGroup();
        for (int i = 0; i < 5; i++) {
            jb[i] = new JRadioButton();
            add(jb[i]);
            buttonGroup.add(jb[i]);
        }
        b1 = new JButton("Save and Next");
        b2 = new JButton("Save for later");
        b1.addActionListener(this);
        b2.addActionListener(this);
        add(b1);
        add(b2);
        set();
        label1.setBounds(30, 40, 450, 20);
        label2.setBounds(20, 20, 450, 20);
        jb[0].setBounds(50, 80, 100, 20);
        jb[1].setBounds(50, 110, 100, 20);
        jb[2].setBounds(50, 140, 100, 20);
        jb[3].setBounds(50, 170, 100, 20);
        b1.setBounds(95, 240, 140, 30);
        b2.setBounds(270, 240, 150, 30);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocation(250, 100);
        setVisible(true);
        setSize(600, 350);

        final int[] timeLeft = {60}; // 1 minute in seconds

        TimerTask task = new TimerTask() {
            public void run() {
                timeLeft[0]--;
                if (timeLeft[0] >= 0) {
                    label2.setText("Time left: " + timeLeft[0] + " sec.");
                } else {
                    timer.cancel();
                    label2.setText("Time Out");
                }
            }
        };

        java.util.Timer timer = new Timer();
        timer.scheduleAtFixedRate(task, 0, 1000);

    }

    public void actionPerformed(ActionEvent e) {
            if (e.getSource() == b1) {
            if (check()) {
                count++;
            }
            current++;
            set();
            if (current == 9) {
                b1.setEnabled(false);
                b2.setText("Result");
            }
        }
        if (e.getActionCommand().equals("Save for later")) {
            JButton bk = new JButton("Review" + x);
            bk.setBounds(480, 20 + 30 * x, 100, 30);
            add(bk);
            bk.addActionListener(this);
            m[x] = current;
            x++;
            current++;
            set();
            if (current == 9) {
                b2.setText("Result");
            }
            setVisible(false);
            setVisible(true);
        }

        for (int i = 0, y = 1; i < x; i++, y++) {

            if (e.getActionCommand().equals("Review" + y)) 
        {
                if (check()) {
                    count++;
                }
                now = current;
                current = m[y];
                set();
                ((JButton) e.getSource()).setEnabled(false);
                current = now;
            }

            if(e.getActionCommand().equals("Result"))
            {
                if(check())
                    count += 1;
                current++;
                JOptionPane.showMessageDialog(this,"Score = "+count);
                System.exit(0);
            }
        }
}
    void set()  
    {  
        jb[4].setSelected(true);  
        if(current==0)  
        {  
            label1.setText("Que1: Which of the following language was developed as the first purely object programming language?");  
            jb[0].setText("SmallTalk");jb[1].setText("C++");jb[2].setText("Kotlin");jb[3].setText("Java");   
        }  
        if(current==1)  
        {  
            label1.setText("Que2: Which of the following is not a Java features?");  
            jb[0].setText("Dynamic");jb[1].setText("Architecture Neutral");jb[2].setText("Use of pointers");jb[3].setText("Object-oriented");  
        }  
        if(current==2)  
        {  
            label1.setText("Que3: The \u0021 article referred to");  
            jb[0].setText("Unicode escape sequence");jb[1].setText("Octal escape");jb[2].setText("Hexadecimal");jb[3].setText("Line feed");  
        }  
        if(current==3)  
        {  
            label1.setText("Que4: Expected created by try block is caught in which block.?");  
            jb[0].setText("catch");jb[1].setText("throw");jb[2].setText("final");jb[3].setText("thrown");  
        }  
        if(current==4)  
        {  
            label1.setText("Que5: _____ is used to find and fix bugs in the Java programs.");  
            jb[0].setText("JVM");jb[1].setText("JRE");jb[2].setText("JDK");jb[3].setText("JDB");  
        }  
        if(current==5)  
        {  
            label1.setText("Que6: Identify the infinite loop?");  
            jb[0].setText("for(;;)");jb[1].setText("for()i=0;j<1;i--");jb[2].setText("for(int=0;i++)");jb[3].setText("if(All of the above)");  
        }  
        if(current==6)  
        {  
            label1.setText("Que7: Who developed object-oriented programming? ");  
            jb[0].setText("Adele Goldberg");jb[1].setText("Dennis Ritchie");jb[2].setText("Alan Kay");  
                        jb[3].setText("Andrea Ferro");  
        }  
        if(current==7)  
        {  
            label1.setText("Que8: Which of the following is not an OOPS concept?");  
            jb[0].setText("Encapsulation");jb[1].setText("Polymorphism");jb[2].setText("Exception");  
                        jb[3].setText("Abstraction");         
        }  
        if(current==8)  
        {  
            label1.setText("Que9: What is the return type of the hashCode() method in the Object class?");  
            jb[0].setText("Object");jb[1].setText("int");jb[2].setText("long");jb[3].setText("void");  
        }  
        if(current==9)  
        {  
            label1.setText("Que10: Which provides runtime enviroment for java byte code to be executed?");  
            jb[0].setText("JDK");jb[1].setText("JVM");jb[2].setText("JRE");  
                        jb[3].setText("JAVAC");  
        }  
        label1.setBounds(30,40,450,20);  
        for(int i=0,j=0;i<=90;i+=30,j++)  
            jb[j].setBounds(50,80+i,200,20);  
    }  
    boolean check()  
    {  
        if(current==0)  
            return(jb[0].isSelected());  
        if(current==1)  
            return(jb[2].isSelected());  
        if(current==2)  
            return(jb[0].isSelected());  
        if(current==3)  
            return(jb[0].isSelected());  
        if(current==4)  
            return(jb[3].isSelected());  
        if(current==5)  
            return(jb[3].isSelected());  
        if(current==6)  
            return(jb[2].isSelected());  
        if(current==7)  
            return(jb[2].isSelected());  
        if(current==8)  
            return(jb[1].isSelected());  
        if(current==9)  
            return(jb[2].isSelected());  
        return false;  
       }    
    }
class Examination  
{  
    public static void main(String[] args)  
    {  
        try  
        {  
            Login form = new Login();  
            form.setSize(400,150);  
            form.setVisible(true);  
        }  
        catch(Exception e)  
        {     
            JOptionPane.showMessageDialog(null, e.getMessage());  
        }  
    }  
     
}

        