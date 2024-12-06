import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

public class runProgram extends JFrame{
    private JPanel backgroundPanel,
                   topPanel, 
                   bottomPanel, 
                   loginFormPanel, 
                   loginFormTopPadding, 
                   loginFormBottomPadding, 
                   loginFormLeftPadding, 
                   loginFormRightPadding,
                   mainContentPanel,
                   textBackgroundPanel;

    private JLabel title,
                   actionTextLabel;

    private JTextField usernameTextField;
    private JPasswordField passwordField;
    private JButton loginButton,
                    exitButton;

    final Dimension top_bottom_border_size = new Dimension(640, 66);
    final Dimension background_size = new Dimension(640, 349);
    final Dimension login_form_size = new Dimension(326, 242);
    final Dimension text_field = new Dimension(267, 28);
    final Dimension debit_credit_button_size = new Dimension(350, 42);
    final Dimension exit_button_size = new Dimension(100, 40);

    // ========================= LOGIN WINDOW =====================
    public runProgram(){
        setTitle("Banking System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(640, 480);
        setResizable(false);
        setLocationRelativeTo(null);

        topPanel = new JPanel();
        topPanel.setPreferredSize(top_bottom_border_size);
        topPanel.setBackground(new Color(0x032F30));
        topPanel.setFocusable(true);

        backgroundPanel = new JPanel();
        backgroundPanel.setPreferredSize(background_size);
        backgroundPanel.setBackground(new Color(0x0C969C));
        backgroundPanel.setLayout(new BorderLayout());

        loginFormPanel = new JPanel();
        loginFormPanel.setPreferredSize(login_form_size);
        loginFormPanel.setBackground(Color.WHITE);
        loginFormPanel.setLayout(null);

        title = new JLabel("Banking System");
        title.setBounds(40, 10, 640, 50);
        title.setFont(new Font("Arial", Font.BOLD, 30));
        loginFormPanel.add(title);

        usernameTextField = new JTextField("Username");
        usernameTextField.setBounds(20, title.getY() + 55, 267, 28);
        usernameTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(usernameTextField.getText().equals("Username")){
                    usernameTextField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(usernameTextField.getText().equals("Username") || usernameTextField.getText().isEmpty()){
                    usernameTextField.setText("Username");
                }
            }
        });
        loginFormPanel.add(usernameTextField);

        passwordField = new JPasswordField("Password");
        passwordField.setEchoChar((char) 0);
        passwordField.setBounds(20, usernameTextField.getY() + 40, 267, 28);
        passwordField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(passwordField.getText().equals("Password")){
                    passwordField.setText("");
                    passwordField.setEchoChar('*');
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(passwordField.getText().equals("Password") || passwordField.getText().isEmpty()){
                    passwordField.setText("Password");
                    passwordField.setEchoChar((char) 0);
                }
            }
        });
        loginFormPanel.add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBackground(new Color(0x0C969C));
        loginButton.setBounds(passwordField.getX(), passwordField.getY() + 41, passwordField.getWidth(), 30);
        loginButton.setFont(new Font("Arial", Font.PLAIN, 10));
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login login = new Login();
                ArrayList<UserAccount> userAccountList = login.getAllUserAccounts();
                for (UserAccount useraccount : userAccountList) {
                    if(usernameTextField.getText().equalsIgnoreCase(useraccount.getUsername()) && passwordField.getText().equals(useraccount.getPassword())){
                        System.out.println("login success");
                        runPreSelect(useraccount);
                        break;
                    }
                    else{
                        System.out.println("login failed");
                    }
                }          
            }
        });
        loginFormPanel.add(loginButton);

        backgroundPanel.add(loginFormPanel, BorderLayout.CENTER);

        loginFormTopPadding = new JPanel();
        loginFormTopPadding.setPreferredSize(new Dimension(213, 54));
        loginFormTopPadding.setBackground(new Color(0x0C969C));
        backgroundPanel.add(loginFormTopPadding, BorderLayout.NORTH);

        loginFormBottomPadding = new JPanel();
        loginFormBottomPadding.setPreferredSize(new Dimension(213, 54));
        loginFormBottomPadding.setBackground(new Color(0x0C969C));
        backgroundPanel.add(loginFormBottomPadding, BorderLayout.SOUTH);

        loginFormLeftPadding = new JPanel();
        loginFormLeftPadding.setPreferredSize(new Dimension(157, 349));
        loginFormLeftPadding.setBackground(new Color(0x0C969C));
        backgroundPanel.add(loginFormLeftPadding, BorderLayout.WEST);

        loginFormRightPadding = new JPanel();
        loginFormRightPadding.setPreferredSize(new Dimension(157, 349));
        loginFormRightPadding.setBackground(new Color(0x0C969C));
        backgroundPanel.add(loginFormRightPadding, BorderLayout.EAST);

        bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(0x032F30));
        bottomPanel.setPreferredSize(top_bottom_border_size);

        // ____________________ EXIT BUTTON ____________________
        exitButton = new JButton("Exit");
        exitButton.setBounds(500, 250, exit_button_size.width, exit_button_size.height);
        exitButton.setFont(new Font("Arial", Font.PLAIN, 20));
        exitButton.setForeground(Color.WHITE);
        exitButton.setBackground(new Color(0x032F30));
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        // _________________^^^ EXIT BUTTON ^^^_________________


        // ____________________ ACTION TEXT AND BACKGROUND ____________________
        textBackgroundPanel = new JPanel();
        textBackgroundPanel.setBounds(0, 3, 309, 44);
        textBackgroundPanel.setPreferredSize(new Dimension(309, 44));
        textBackgroundPanel.setBackground(new Color(0x274D60));

        actionTextLabel = new JLabel();
        actionTextLabel.setFont(new Font("Arial", Font.PLAIN, 25));
        actionTextLabel.setForeground(Color.WHITE);
        textBackgroundPanel.add(actionTextLabel);
        // _________________^^^ ACTION TEXT AND BACKGROUND ^^^_________________


        // ____________________ MAIN CONTENT PANEL ____________________
        // Magbutang bago panel para ma change ang layout sa mga shit
        mainContentPanel = new JPanel();
        mainContentPanel.setSize(background_size);
        mainContentPanel.setBackground(backgroundPanel.getBackground());
        mainContentPanel.setLayout(null);
        // _________________^^^ MAIN CONTENT PANEL ^^^_________________


        add(topPanel, BorderLayout.NORTH);
        add(backgroundPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    
    // ========================= PRESELECT WINDOW =========================  
    public void runPreSelect(UserAccount useraccount){
        backgroundPanel.removeAll();
        // change ang sulod sa setText() according action na buhaton, basta sunda ang sa figma nga design.
        // kani man tung rectangle nga naay text sa top left
        // ____________________ ACTION TEXT ____________________
        actionTextLabel.setText("Choose Transaction");
        // _________________^^^ ACTION TEXT ^^^_________________


        JButton debitButton = new JButton("Debit");
        debitButton.setBounds(130, 110, debit_credit_button_size.width, debit_credit_button_size.height);
        debitButton.setFont(new Font("Arial", Font.PLAIN, 20));
        debitButton.setBackground(Color.WHITE);
        debitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                runDebitSelection(useraccount);
            }
        });

        JButton creditButton = new JButton("Credit");
        creditButton.setBounds(130, debitButton.getY() + 60, debit_credit_button_size.width, debit_credit_button_size.height);
        creditButton.setFont(new Font("Arial", Font.PLAIN, 20));
        creditButton.setBackground(Color.WHITE);
        creditButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                runCreditSelection(useraccount);
            }
        });


        // add tanan NEW COMPONENTS sa MAIN CONTENT PANEL para ma kita sa bagong window
        // _____________________ NEW COMPOENENTS _____________________
        mainContentPanel.add(textBackgroundPanel);
        mainContentPanel.add(exitButton);
        mainContentPanel.add(debitButton);
        mainContentPanel.add(creditButton);
        // __________________^^^ NEW COMPOENENTS ^^^__________________

        backgroundPanel.add(mainContentPanel);
        repaint();
        setVisible(true);
    }


    // ========================= CREDIT SELECTION WINDOW =====================
    public void runCreditSelection(UserAccount useraccount){
        backgroundPanel.removeAll();
        mainContentPanel.removeAll();
        
        // ____________________ ACTION TEXT ____________________
        // Set setText()
        // actionTextLabel.setText();
        // _________________^^^ ACTION TEXT ^^^_________________

        JButton button = new JButton("TEST BUTTON");
        button.setBounds(130, 110, debit_credit_button_size.width, debit_credit_button_size.height);


        // add tanan NEW COMPONENTS sa MAIN CONTENT PANEL para ma kita sa bagong window
        // _____________________ NEW COMPOENENTS _____________________
        mainContentPanel.add(button);
        mainContentPanel.add(textBackgroundPanel);
        mainContentPanel.add(exitButton);
        // __________________^^^ NEW COMPOENENTS ^^^__________________

        backgroundPanel.add(mainContentPanel);
        repaint();
        setVisible(true);
    } 


    // ========================= DEBIT SELECTION WINDOW =====================
    public void runDebitSelection(UserAccount useraccount){
        backgroundPanel.removeAll();
        mainContentPanel.removeAll();
        
        // ____________________ ACTION TEXT ____________________
        actionTextLabel.setText("Debit");
        // _________________^^^ ACTION TEXT ^^^_________________

        JLabel accountNumberLabel = new JLabel("Account number: " + useraccount.getDebitAccountNum());
        accountNumberLabel.setBounds(actionTextLabel.getX(), actionTextLabel.getY() + 60, 250, 30);
        accountNumberLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        accountNumberLabel.setForeground(Color.WHITE);

        JPanel buttonGridPanel = new JPanel();
        buttonGridPanel.setOpaque(false);
        buttonGridPanel.setBounds(30, 110, 560, 107);
        buttonGridPanel.setLayout(new GridLayout(2, 2, 10, 10));

        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.setFont(new Font("Arial", Font.PLAIN, 20));
        withdrawButton.setForeground(Color.BLACK);
        withdrawButton.setBackground(Color.WHITE);
        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                runWithdraw(useraccount);
            }
        });
        buttonGridPanel.add(withdrawButton);

        JButton depositButton = new JButton("Deposit");
        depositButton.setFont(new Font("Arial", Font.PLAIN, 20));
        depositButton.setForeground(Color.BLACK);
        depositButton.setBackground(Color.WHITE);
        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                runDeposit(useraccount);
            }
        });
        buttonGridPanel.add(depositButton);

        JButton transferButton = new JButton("Transfer");
        transferButton.setFont(new Font("Arial", Font.PLAIN, 20));
        transferButton.setForeground(Color.BLACK);
        transferButton.setBackground(Color.WHITE);
        transferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                runTransfer(useraccount);
            }
        });
        buttonGridPanel.add(transferButton);

        JButton showBalanceButton = new JButton("Show Balance");
        showBalanceButton.setFont(new Font("Arial", Font.PLAIN, 20));
        showBalanceButton.setForeground(Color.BLACK);
        showBalanceButton.setBackground(Color.WHITE);
        showBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                runBalace(useraccount);
            }
        });
        buttonGridPanel.add(showBalanceButton);

        // add tanan NEW COMPONENTS sa MAIN CONTENT PANEL para ma kita sa bagong window
        // _____________________ NEW COMPOENENTS _____________________
        mainContentPanel.add(textBackgroundPanel);
        mainContentPanel.add(exitButton);
        mainContentPanel.add(accountNumberLabel);
        mainContentPanel.add(buttonGridPanel);
        // __________________^^^ NEW COMPOENENTS ^^^__________________

        backgroundPanel.add(mainContentPanel);
        repaint();
        setVisible(true);
    } 


    // ========================= WITHDRAW WINDOW =====================
    public void runWithdraw (UserAccount useraccount){
        backgroundPanel.removeAll();
        mainContentPanel.removeAll();
        
        // ____________________ ACTION TEXT ____________________
        // Set setText()
        // actionTextLabel.setText();
        // _________________^^^ ACTION TEXT ^^^_________________


        // add tanan NEW COMPONENTS sa MAIN CONTENT PANEL para ma kita sa bagong window
        // _____________________ NEW COMPOENENTS _____________________
        mainContentPanel.add(textBackgroundPanel);
        mainContentPanel.add(exitButton);
        // __________________^^^ NEW COMPOENENTS ^^^__________________

        backgroundPanel.add(mainContentPanel);
        repaint();
        setVisible(true);
    } 


    // ========================= PAYMENT WINDOW =====================
    public void runPayement(UserAccount useraccount){
        backgroundPanel.removeAll();
        mainContentPanel.removeAll();
        
        // ____________________ ACTION TEXT ____________________
        // Set setText()
        // actionTextLabel.setText();
        // _________________^^^ ACTION TEXT ^^^_________________


        // add tanan NEW COMPONENTS sa MAIN CONTENT PANEL para ma kita sa bagong window
        // _____________________ NEW COMPOENENTS _____________________
        mainContentPanel.add(textBackgroundPanel);
        mainContentPanel.add(exitButton);
        // __________________^^^ NEW COMPOENENTS ^^^__________________

        backgroundPanel.add(mainContentPanel);
        repaint();
        setVisible(true);
    } 


    // ========================= DEPOSIT WINDOW =====================
    public void runDeposit(UserAccount useraccount){
        backgroundPanel.removeAll();
        mainContentPanel.removeAll();
        
        // ____________________ ACTION TEXT ____________________
        // Set setText()
        // actionTextLabel.setText();
        // _________________^^^ ACTION TEXT ^^^_________________


        // add tanan NEW COMPONENTS sa MAIN CONTENT PANEL para ma kita sa bagong window
        // _____________________ NEW COMPOENENTS _____________________
        mainContentPanel.add(textBackgroundPanel);
        mainContentPanel.add(exitButton);
        // __________________^^^ NEW COMPOENENTS ^^^__________________

        backgroundPanel.add(mainContentPanel);
        repaint();
        setVisible(true);
    } 


    // ========================= TRANSFER WINDOW =====================
    public void runTransfer(UserAccount useraccount){
        backgroundPanel.removeAll();
        mainContentPanel.removeAll();
        
        // ____________________ ACTION TEXT ____________________
        // Set setText()
        // actionTextLabel.setText();
        // _________________^^^ ACTION TEXT ^^^_________________


        // add tanan NEW COMPONENTS sa MAIN CONTENT PANEL para ma kita sa bagong window
        // _____________________ NEW COMPOENENTS _____________________
        mainContentPanel.add(textBackgroundPanel);
        mainContentPanel.add(exitButton);
        // __________________^^^ NEW COMPOENENTS ^^^__________________

        backgroundPanel.add(mainContentPanel);
        repaint();
        setVisible(true);
    } 


    // ========================= BALANCE WINDOW =====================
    public void runBalace (UserAccount useraccount){ 
        backgroundPanel.removeAll();
        mainContentPanel.removeAll();

        // TO DO: 
        //  add condition para mag check sa acconunt type 
        //  para mu display ug limit kung credit account 
        
        // ____________________ ACTION TEXT ____________________
        // Set setText()
        // actionTextLabel.setText();
        // _________________^^^ ACTION TEXT ^^^_________________


        // add tanan NEW COMPONENTS sa MAIN CONTENT PANEL para ma kita sa bagong window
        // _____________________ NEW COMPOENENTS _____________________
        mainContentPanel.add(textBackgroundPanel);
        mainContentPanel.add(exitButton);
        // __________________^^^ NEW COMPOENENTS ^^^__________________

        backgroundPanel.add(mainContentPanel);
        repaint();
        setVisible(true);
    }
}
