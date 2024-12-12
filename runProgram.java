import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class runProgram extends JFrame{
    private JPanel backgroundPanel,
                   topPanel, 
                   bottomPanel, 
                   mainContentPanel,
                   textBackgroundPanel;

    private JLabel actionTextLabel;

    private JButton exitButton;

    public DecimalFormat decimalFormat = new DecimalFormat();
    final Dimension top_bottom_border_size = new Dimension(640, 66);
    final Dimension background_size = new Dimension(640, 349);
    final Dimension login_form_size = new Dimension(326, 242);
    final Dimension text_field = new Dimension(267, 28);
    final Dimension debit_credit_button_size = new Dimension(350, 42);
    final Dimension exit_button_size = new Dimension(100, 40);

    // ========================= MAIN FRAME =====================
    public runProgram(){
        decimalFormat.setMaximumFractionDigits(2);
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
                runLogin();
            }
        });
        
        // _________________^^^ EXIT BUTTON ^^^_________________


        // ____________________ ACTION TEXT AND BACKGROUND ____________________
        textBackgroundPanel = new JPanel();
        textBackgroundPanel.setBounds(0, 3, 309, 44);
        textBackgroundPanel.setPreferredSize(new Dimension(309, 44));
        textBackgroundPanel.setBackground(new Color(0x274D60));

        actionTextLabel = new JLabel();
        actionTextLabel.setSize(new Dimension(309, 40));
        actionTextLabel.setFont(new Font("Arial", Font.BOLD, 25));
        actionTextLabel.setForeground(Color.WHITE);
        textBackgroundPanel.add(actionTextLabel);
        // _________________^^^ ACTION TEXT AND BACKGROUND ^^^_________________


        // ____________________ MAIN CONTENT PANEL ____________________
        mainContentPanel = new JPanel();
        mainContentPanel.setSize(background_size);
        mainContentPanel.setBackground(backgroundPanel.getBackground());
        mainContentPanel.setLayout(null);
        // _________________^^^ MAIN CONTENT PANEL ^^^_________________


        add(topPanel, BorderLayout.NORTH);
        add(backgroundPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
        setVisible(true);
        runLogin();
    }
    // ========================= MAIN FRAME =====================


    // ========================= LOGIN WINDOW =========================  
    public void runLogin(){
        backgroundPanel.removeAll();

        JPanel loginPanel = new JPanel();
        loginPanel.setSize(background_size);
        loginPanel.setBackground(backgroundPanel.getBackground());
        loginPanel.setLayout(new BorderLayout());

        JPanel loginFormPanel = new JPanel();
        loginFormPanel.setPreferredSize(login_form_size);
        loginFormPanel.setBackground(Color.WHITE);
        loginFormPanel.setLayout(null);

        JLabel title = new JLabel("Banking System");
        title.setBounds(40, 10, 640, 50);
        title.setFont(new Font("Arial", Font.BOLD, 30));
        loginFormPanel.add(title);

        JTextField accountNumTextField = new JTextField("Account Number");
        accountNumTextField.setBounds(20, title.getY() + 50, 267, 28);
        accountNumTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(accountNumTextField.getText().equals("Account Number")){
                    accountNumTextField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(accountNumTextField.getText().equals("Account Number") || accountNumTextField.getText().isEmpty()){
                    accountNumTextField.setText("Account Number");
                }
            }
        });
        loginFormPanel.add(accountNumTextField);

        JPasswordField passwordField = new JPasswordField("Password");
        passwordField.setEchoChar((char) 0);
        passwordField.setBounds(20, accountNumTextField.getY() + 40, 267, 28);
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

        JLabel loginStatus = new JLabel();
        loginStatus.setBounds(passwordField.getX() + 40, passwordField.getY() + 71, passwordField.getWidth(), 30);
        loginStatus.setFont(new Font("Arial", Font.PLAIN, 13));
        loginFormPanel.add(loginStatus);

        JButton loginButton = new JButton("Login");
        loginButton.setBackground(new Color(0x0C969C));
        loginButton.setBounds(passwordField.getX(), passwordField.getY() + 41, passwordField.getWidth(), 30);
        loginButton.setFont(new Font("Arial", Font.PLAIN, 15));
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { 
                //=====debuger
                if(accountNumTextField.getText().trim().equals("debug")){
                    accountNumTextField.setText("Account Number");
                    runDebug();
                }
                //=====debuger
                Login login = new Login();
                ArrayList<Account> accountList = login.getAllAccounts();
                try{
                    for (Account account : accountList) {
                        if(Integer.parseInt(accountNumTextField.getText().trim()) == account.getAccountNum() && Integer.parseInt(passwordField.getText()) == account.getPin()){
                            System.out.println("login success");
                            if(account.getType().equals("c")){
                                runCreditSelection(account);
                            }
                            else{
                                runDebitSelection(account);
                            }
                            break;
                        }
                        else{
                            loginStatus.setText("Login failed. Please Try again.");
                            System.out.println("login failed");
                        }
                    }          
                }
                catch(Exception err){
                    err.printStackTrace();
                    loginStatus.setText("Login failed. Please Try again.");
                    System.out.println("login failed");
                }
            }
        });
        loginFormPanel.add(loginButton);

        JPanel loginFormTopPadding = new JPanel();
        loginFormTopPadding.setPreferredSize(new Dimension(213, 54));
        loginFormTopPadding.setBackground(backgroundPanel.getBackground());

        JPanel loginFormBottomPadding = new JPanel();
        loginFormBottomPadding.setPreferredSize(new Dimension(213, 54));
        loginFormBottomPadding.setBackground(backgroundPanel.getBackground());

        JPanel loginFormLeftPadding = new JPanel();
        loginFormLeftPadding.setPreferredSize(new Dimension(157, 349));
        loginFormLeftPadding.setBackground(backgroundPanel.getBackground());

        JPanel loginFormRightPadding = new JPanel();
        loginFormRightPadding.setPreferredSize(new Dimension(157, 349));
        loginFormRightPadding.setBackground(backgroundPanel.getBackground());

        loginPanel.add(loginFormPanel, BorderLayout.CENTER);
        loginPanel.add(loginFormTopPadding, BorderLayout.NORTH);
        loginPanel.add(loginFormBottomPadding, BorderLayout.SOUTH);
        loginPanel.add(loginFormLeftPadding, BorderLayout.WEST);
        loginPanel.add(loginFormRightPadding, BorderLayout.EAST);

        backgroundPanel.add(loginPanel);
        repaint();
        setVisible(true);
    }
    // ========================= LOGIN WINDOW =========================  


    // ========================= CREDIT SELECTION WINDOW =====================
    public void runCreditSelection(Account account){
        String accountType = account.getType();
        backgroundPanel.removeAll();
        mainContentPanel.removeAll();
        
        actionTextLabel.setText("Credit");

        JLabel accountNumberLabel = new JLabel("Account number: " + account.getAccountNum());
        accountNumberLabel.setBounds(textBackgroundPanel.getX() + 30, textBackgroundPanel.getY() + 60, mainContentPanel.getWidth(), 30);
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
                runWithdraw(account, accountType);
            }
        });
        buttonGridPanel.add(withdrawButton);

        JButton paymentButton = new JButton("Payment");
        paymentButton.setFont(new Font("Arial", Font.PLAIN, 20));
        paymentButton.setForeground(Color.BLACK);
        paymentButton.setBackground(Color.WHITE);
        paymentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                runPayment(account);
            }
        });
        buttonGridPanel.add(paymentButton);

        JButton transferButton = new JButton("Transfer");
        transferButton.setFont(new Font("Arial", Font.PLAIN, 20));
        transferButton.setForeground(Color.BLACK);
        transferButton.setBackground(Color.WHITE);
        transferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                runTransfer(account, accountType);
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
                runBalance(account, accountType);
            }
        });
        buttonGridPanel.add(showBalanceButton);

        mainContentPanel.add(textBackgroundPanel);
        mainContentPanel.add(exitButton);
        mainContentPanel.add(accountNumberLabel);
        mainContentPanel.add(buttonGridPanel);

        backgroundPanel.add(mainContentPanel);
        repaint();
        setVisible(true);
    } 
    // ========================= CREDIT SELECTION WINDOW =====================


    // ========================= DEBIT SELECTION WINDOW =====================
    public void runDebitSelection(Account account){
        String accountType = account.getType();
        backgroundPanel.removeAll();
        mainContentPanel.removeAll();
        
        actionTextLabel.setText("Debit");

        JLabel accountNumberLabel = new JLabel("Account number: " + account.getAccountNum());
        accountNumberLabel.setBounds(textBackgroundPanel.getX() + 30, textBackgroundPanel.getY() + 60, mainContentPanel.getWidth(), 30);
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
                runWithdraw(account, accountType);
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
                runDeposit(account);
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
                runTransfer(account, accountType);
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
                runBalance(account, accountType);
            }
        });
        buttonGridPanel.add(showBalanceButton);

        mainContentPanel.add(textBackgroundPanel);
        mainContentPanel.add(exitButton);
        mainContentPanel.add(accountNumberLabel);
        mainContentPanel.add(buttonGridPanel);

        backgroundPanel.add(mainContentPanel);
        repaint();
        setVisible(true);
    } 
    // ========================= DEBIT SELECTION WINDOW =====================


    // ========================= WITHDRAW WINDOW =====================
    public void runWithdraw (Account account, String accountType){
        backgroundPanel.removeAll();
        mainContentPanel.removeAll();
        actionTextLabel.setText("Withdraw");

        Balance balanceManager = new Balance();
        Withdraw withdraw;
        if(accountType.equals("c")){
            withdraw = new Withdraw(account.getAccountNum());
        }
        else{
            withdraw = new Withdraw(account.getAccountNum());
        }

        JLabel BalanceLabel = new JLabel();
        if(accountType.equals("c")){
            BalanceLabel.setText("Outstanding Balance: " + decimalFormat.format(balanceManager.getCurrentBalance(account.getAccountNum())));
        }
        else{
            BalanceLabel.setText("Current Balance: " + decimalFormat.format(balanceManager.getCurrentBalance(account.getAccountNum())));
        }
        BalanceLabel.setBounds(actionTextLabel.getX() - 25, actionTextLabel.getY() + 50, 300, 30);
        BalanceLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        BalanceLabel.setForeground(Color.WHITE);

        JLabel AmountLabel = new JLabel("Enter amount to withdraw");
        AmountLabel.setBounds(BalanceLabel.getX(), BalanceLabel.getY() + 30, 250, 30);
        AmountLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        AmountLabel.setForeground(Color.WHITE);

        JTextField amounttTextField = new JTextField("Amount");
        amounttTextField.setBounds(AmountLabel.getX(),  AmountLabel.getY() + 35 , debit_credit_button_size.width + 90,48);
        amounttTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(amounttTextField.getText().equals("Amount")){
                    amounttTextField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(amounttTextField.getText().equals("Amount") || amounttTextField.getText().isEmpty()){
                    amounttTextField.setText("Amount");
                }
            }
        });
        
        JLabel statusLabel = new JLabel("");
        statusLabel.setBounds(amounttTextField.getX(), amounttTextField.getY() + 115, 400, 30);
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        statusLabel.setForeground(Color.WHITE);

        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.setBounds(amounttTextField.getX() + 70, amounttTextField.getY() + 60, 300,42);
        withdrawButton.setFont(new Font("Arial", Font.PLAIN, 20));
        withdrawButton.setBackground(new Color(0x032F30));
        withdrawButton.setForeground(Color.WHITE);
        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { 
                try{
                    double amount = Double.parseDouble(amounttTextField.getText().trim());
                    boolean isAccountLimitExeeded = amount > account.getLimit() || amount + balanceManager.getCurrentBalance(account.getAccountNum()) > account.getLimit();

                    if(amount <= 0){
                        statusLabel.setText("Withdraw failed. Amount must be greater than 0.");
                    }
                    else if(accountType.equals("c")){
                        if(isAccountLimitExeeded){
                            statusLabel.setText("Withdraw failed. Limit exeeded.");
                        }
                        else{
                            withdraw.amountWithdraw(amount);
                            JOptionPane.showMessageDialog(backgroundPanel, "     Withdraw successful.", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                            runLogin();
                        }
                    }
                    else if(accountType.equals("d")){
                        if(amount > balanceManager.getCurrentBalance(account.getAccountNum())){
                            statusLabel.setText("Withdraw failed. Insufficient funds.");
                        }
                        else{
                            withdraw.amountWithdraw(amount);
                            JOptionPane.showMessageDialog(backgroundPanel, "     Withdraw successful.", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                            runLogin();
                        }
                    }
                }
                catch(Exception err){
                    statusLabel.setText("Withdraw failed. Amount must be a number.");
                    err.printStackTrace();
                }
            }
        });


        mainContentPanel.add(statusLabel);
        mainContentPanel.add(AmountLabel);
        mainContentPanel.add(BalanceLabel);
        mainContentPanel.add(amounttTextField);
        mainContentPanel.add(withdrawButton);
        mainContentPanel.add(textBackgroundPanel);
        mainContentPanel.add(exitButton);
        backgroundPanel.add(mainContentPanel);
        repaint();
        setVisible(true);
    } 
    // ========================= WITHDRAW WINDOW =====================


    // ========================= PAYMENT WINDOW =====================
    public void runPayment(Account account){ 
        backgroundPanel.removeAll();
        mainContentPanel.removeAll();
        actionTextLabel.setText("Payment");
        Balance balanceManager = new Balance();
        Payment payment = new Payment(account.getAccountNum());

        JLabel BalanceLabel = new JLabel();
        BalanceLabel.setText("Outstanding Balance: " + decimalFormat.format(balanceManager.getCurrentBalance(account.getAccountNum())));
        BalanceLabel.setBounds(actionTextLabel.getX() - 25, actionTextLabel.getY() + 50, 300, 30);
        BalanceLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        BalanceLabel.setForeground(Color.WHITE);

        JLabel amountJLabel = new JLabel("Enter Amount");
        amountJLabel.setBounds(BalanceLabel.getX(),  BalanceLabel.getY() + 25, debit_credit_button_size.width + 90,20);
        amountJLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        amountJLabel.setForeground(Color.WHITE);

        JTextField amounttTextField = new JTextField("Amount");
        amounttTextField.setBounds(BalanceLabel.getX(),  BalanceLabel.getY() + 50 , debit_credit_button_size.width + 90,30); 
        amounttTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(amounttTextField.getText().equals("Amount")){
                    amounttTextField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(amounttTextField.getText().equals("Amount") || amounttTextField.getText().isEmpty()){
                    amounttTextField.setText("Amount");
                }
            }
        });

        JLabel pamentLabel = new JLabel("Payment from");
        pamentLabel.setBounds(BalanceLabel.getX(),  amounttTextField.getY() + 35, debit_credit_button_size.width + 90,20);
        pamentLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        pamentLabel.setForeground(Color.WHITE);

        JTextField accountNumberTextField = new JTextField("Account Number");
        accountNumberTextField.setBounds(BalanceLabel.getX(),  pamentLabel.getY() + 25 , debit_credit_button_size.width + 90,30); 
        accountNumberTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(accountNumberTextField.getText().equals("Account Number")){
                    accountNumberTextField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(accountNumberTextField.getText().equals("Account Number") || accountNumberTextField.getText().isEmpty()){
                    accountNumberTextField.setText("Account Number");
                }
            }

        });

        JLabel statusLabel = new JLabel();
        statusLabel.setBounds(amounttTextField.getX(), amounttTextField.getY() + 145, 400, 30);
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        statusLabel.setForeground(Color.WHITE);

        JButton paymentButton = new JButton("Pay");
        paymentButton.setBounds(accountNumberTextField.getX() + 70, accountNumberTextField.getY() + 40, 300,30);
        paymentButton.setFont(new Font("Arial", Font.PLAIN, 20));
        paymentButton.setBackground(new Color(0x032F30));
        paymentButton.setForeground(Color.WHITE);
        paymentButton.addActionListener(new ActionListener() { 
        @Override
            public void actionPerformed(ActionEvent e){ 
                try{
                    Login login = new Login();
                    double amount = Double.parseDouble(amounttTextField.getText().trim());
                    boolean isAccountLimitExeeded = amount > account.getLimit() || amount + balanceManager.getCurrentBalance(account.getAccountNum()) > account.getLimit();
                    int payerAccountNum = Integer.parseInt(accountNumberTextField.getText().trim());
                    Account payerAccount = login.retrieveAccount(payerAccountNum);
                    double payerAccountCurrentBalance = balanceManager.getCurrentBalance(payerAccountNum);
                    double payerAccountLimit = payerAccount.getLimit();
                    boolean isPayerAccountLimitExeeded = amount > payerAccountLimit || amount + payerAccountCurrentBalance > payerAccountLimit;

                    if(amount <= 0){
                        System.out.println("AMOUNT INVALID.");
                        statusLabel.setText("Amount must be greater than 0.");
                    }
                    else if(account.getAccountNum() == payerAccountNum){
                        System.out.println("ACCOUNT NUMBER INVALID. CANT USE OWN ACCOUNT NUMBER");
                    }
                    else if(isAccountLimitExeeded){
                        System.out.println("PAYMENT ERROR. ACCOUNT LIMIT EXEEDED");
                        statusLabel.setText("Payment failed. Amount is greater than limit.");
                    }
                    else if(amount > balanceManager.getCurrentBalance(account.getAccountNum())){
                        System.out.println("PAYMENT ERROR. OVERPAY");
                        statusLabel.setText("Payment failed. Overpayment not allowed.");
                    }
                    else if(payerAccount.getType().equals("c") && isPayerAccountLimitExeeded){
                        System.out.println("PAYER ERROR. PAYER LIMIT EXEEDED");
                        statusLabel.setText("Payment failed. Limit exeeded in payer account.");
                    }
                    else{
                        payment.amountPayment(amount, payerAccountNum);
                        JOptionPane.showMessageDialog(backgroundPanel, "     Payment successful.", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                        runLogin();
                    }
                }
                catch(NullPointerException err){
                    System.out.println("ACCOUNT NUMBER INVALID. ACCOUNT NUMBER NOT FOUND");
                    statusLabel.setText("Payment failed. Account number invalid.");
                }
                catch(Exception err){
                    statusLabel.setText("Amount and account number must be a number.");
                    err.printStackTrace();
                }
            }
        });


        mainContentPanel.add(statusLabel);
        mainContentPanel.add(paymentButton);
        mainContentPanel.add(accountNumberTextField);
        mainContentPanel.add(pamentLabel);
        mainContentPanel.add(amountJLabel);
        mainContentPanel.add(amounttTextField);
        mainContentPanel.add(BalanceLabel);
        mainContentPanel.add(textBackgroundPanel);
        mainContentPanel.add(exitButton);
        
        backgroundPanel.add(mainContentPanel);
        repaint();
        setVisible(true);
    } 
    // ========================= PAYMENT WINDOW =====================


    // ========================= DEPOSIT WINDOW =====================
    public void runDeposit(Account account){
        backgroundPanel.removeAll();
        mainContentPanel.removeAll();
        actionTextLabel.setText("Deposit");

        Balance balanceManager = new Balance();
        Deposit deposit = new Deposit(account.getAccountNum());

        JLabel BalanceLabel = new JLabel();
        BalanceLabel.setText("Current Balance: " + decimalFormat.format(balanceManager.getCurrentBalance(account.getAccountNum())));
        BalanceLabel.setBounds(actionTextLabel.getX() - 35, actionTextLabel.getY() + 60, 300, 30);
        BalanceLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        BalanceLabel.setForeground(Color.WHITE);

        JLabel AmountLabel = new JLabel("Enter amount to deposit");
        AmountLabel.setBounds(BalanceLabel.getX(), BalanceLabel.getY() + 25, 250, 30);
        AmountLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        AmountLabel.setForeground(Color.WHITE);

        JTextField amounttTextField = new JTextField("Amount");
        amounttTextField.setBounds(AmountLabel.getX(),  AmountLabel.getY() + 40 , debit_credit_button_size.width + 90,48);
        amounttTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(amounttTextField.getText().equals("Amount")){
                    amounttTextField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(amounttTextField.getText().equals("Amount") || amounttTextField.getText().isEmpty()){
                    amounttTextField.setText("Amount");
                }
            }
        });

        JLabel statusLabel = new JLabel();
        statusLabel.setBounds(amounttTextField.getX(), amounttTextField.getY() + 115, 400, 30);
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        statusLabel.setForeground(Color.WHITE);

        JButton depositButton = new JButton("Deposit");
        depositButton.setBounds(amounttTextField.getX() + 70, amounttTextField.getY() + 65, 300,42);
        depositButton.setFont(new Font("Arial", Font.PLAIN, 20));
        depositButton.setBackground(new Color(0x032F30));
        depositButton.setForeground(Color.WHITE);
        depositButton.addActionListener(new ActionListener() { 
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    double amount = Double.parseDouble(amounttTextField.getText().trim());
                    if(amount <= 0){
                        statusLabel.setText("Deposit failed. Amount must be greater than 0.");
                    }
                    else{
                        deposit.amountDeposit(amount);
                        JOptionPane.showMessageDialog(backgroundPanel, "     Deposit successful.", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                        runLogin();
                    }
                }
                catch(Exception err){
                    statusLabel.setText("Deposit failed. Amount must be a number.");
                    err.printStackTrace();
                }
            }
        });

        mainContentPanel.add(statusLabel);
        mainContentPanel.add(AmountLabel);
        mainContentPanel.add(BalanceLabel);
        mainContentPanel.add(amounttTextField);
        mainContentPanel.add(depositButton);
        mainContentPanel.add(textBackgroundPanel);
        mainContentPanel.add(exitButton);
        backgroundPanel.add(mainContentPanel);

        backgroundPanel.add(mainContentPanel);
        repaint();
        setVisible(true);
    } 
    // ========================= DEPOSIT WINDOW =====================


    // ========================= TRANSFER WINDOW =====================
    public void runTransfer(Account account, String accountType){
        backgroundPanel.removeAll();
        mainContentPanel.removeAll();
        actionTextLabel.setText("Transfer");
        Balance balanceManager = new Balance();
        Transfer transfer = new Transfer(account.getAccountNum());

        JLabel BalanceLabel = new JLabel();
        if(accountType.equals("c")){
            BalanceLabel.setText("Outstanding Balance: " + decimalFormat.format(balanceManager.getCurrentBalance(account.getAccountNum())));
        }
        else{
            BalanceLabel.setText("Current Balance: " + decimalFormat.format(balanceManager.getCurrentBalance(account.getAccountNum())));
        }
        BalanceLabel.setBounds(actionTextLabel.getX() - 25, actionTextLabel.getY() + 50, 300, 30);
        BalanceLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        BalanceLabel.setForeground(Color.WHITE);

        JLabel amountJLabel = new JLabel("Enter Amount");
        amountJLabel.setBounds(BalanceLabel.getX(),  BalanceLabel.getY() + 25, debit_credit_button_size.width + 90,20);
        amountJLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        amountJLabel.setForeground(Color.WHITE);

        JTextField amounttTextField = new JTextField("Amount");
        amounttTextField.setBounds(BalanceLabel.getX(),  BalanceLabel.getY() + 50 , debit_credit_button_size.width + 90,30); 
        amounttTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(amounttTextField.getText().equals("Amount")){
                    amounttTextField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(amounttTextField.getText().equals("Amount") || amounttTextField.getText().isEmpty()){
                    amounttTextField.setText("Amount");
                }
            }
        });

        JLabel AccounJLabel = new JLabel("Transfer to");
        AccounJLabel.setBounds(BalanceLabel.getX(),  amounttTextField.getY() + 35, debit_credit_button_size.width + 90,20);
        AccounJLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        AccounJLabel.setForeground(Color.WHITE);

        JTextField accountJTextField = new JTextField("Account Number");
        accountJTextField.setBounds(BalanceLabel.getX(),  AccounJLabel.getY() + 25 , debit_credit_button_size.width + 90,30); 
        accountJTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(accountJTextField.getText().equals("Account Number")){
                    accountJTextField.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(accountJTextField.getText().equals("Account Number") || accountJTextField.getText().isEmpty()){
                    accountJTextField.setText("Account Number");
                }
            }

        });

        JLabel statusLabel = new JLabel();
        statusLabel.setBounds(amounttTextField.getX(), amounttTextField.getY() + 145, 400, 30);
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        statusLabel.setForeground(Color.WHITE);

        JButton transferButton = new JButton("Transfer");
        transferButton.setBounds(accountJTextField.getX() + 70, accountJTextField.getY() + 40, 300,30);
        transferButton.setFont(new Font("Arial", Font.PLAIN, 20));
        transferButton.setBackground(new Color(0x032F30));
        transferButton.setForeground(Color.WHITE);
        transferButton.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e){ 
                try{
                    Login login = new Login();
                    double amount = Double.parseDouble(amounttTextField.getText().trim());
                    boolean isAccountLimitExeeded = amount > account.getLimit() || amount + balanceManager.getCurrentBalance(account.getAccountNum()) > account.getLimit();
                    int receiverAccountNum = Integer.parseInt(accountJTextField.getText().trim());
                    Account receiverAccount = login.retrieveAccount(receiverAccountNum);
                    double receiverAccountCurrentBalance = balanceManager.getCurrentBalance(receiverAccountNum);
                    double receiverAccountLimit = receiverAccount.getLimit();
                    boolean isReceiverAccountLimitExeeded = amount > receiverAccountLimit || amount + receiverAccountCurrentBalance > receiverAccountLimit;
                    boolean isReceiverAccountOverPayed = amount > receiverAccountLimit || amount > receiverAccountCurrentBalance;

                    if(amount <= 0){
                        statusLabel.setText("Amount must be greater than 0.");
                    }
                    else if(account.getAccountNum() == receiverAccountNum){
                        System.out.println("ACCOUNT NUMBER INVALID.");
                        statusLabel.setText("Transfer failed. Account number invalid.");
                    }
                    else if(accountType.equals("d")){
                        if (amount > balanceManager.getCurrentBalance(account.getAccountNum())) {
                            System.out.println("DEBIT ERROR");
                            statusLabel.setText("Transfer failed. Insufficient funds.");
                        }
                        else if(receiverAccount.getType().equals("c") && isReceiverAccountLimitExeeded){
                            System.out.println("RECEIVER ERROR. RECEIVER LIMIT EXEEDED");
                            statusLabel.setText("Transfer failed. Limit exeeded in receiver account.");
                        }
                        else if(receiverAccount.getType().equals("c") && isReceiverAccountOverPayed){
                            System.out.println("RECEIVER ERROR. RECEIVER OVERPAYED");
                            statusLabel.setText("Transfer failed. Limit exeeded in receiver account.");
                        }
                        else{
                            transfer.amountTransfer(amount, receiverAccountNum, account.getAccountNum());
                            JOptionPane.showMessageDialog(backgroundPanel, "     Transfer successful.", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                            runLogin();
                        }
                    }
                    else if(accountType.equals("c")){
                        if(isAccountLimitExeeded){
                            System.out.println("CREDIT ERROR");
                            statusLabel.setText("Transfer failed. Limit exeeded.");
                        }
                        else if(receiverAccount.getType().equals("c") && isReceiverAccountLimitExeeded){
                            System.out.println("RECEIVER ERROR. RECEIVER LIMIT EXEEDED");
                            statusLabel.setText("Transfer failed. Limit exeeded in receiver account.");
                        }
                        else if(receiverAccount.getType().equals("c") && isReceiverAccountOverPayed){
                            System.out.println("RECEIVER ERROR. OVERPAYED IN RECEIVER ACCOUNT");
                            statusLabel.setText("Transfer failed. Limit exeeded in receiver account.");
                        }
                        else{
                            transfer.amountTransfer(amount, receiverAccountNum, account.getAccountNum());
                            JOptionPane.showMessageDialog(backgroundPanel, "     Transfer successful.", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                            runLogin();
                        }
                    }
                }
                catch(NullPointerException err){
                    System.out.println("ACCOUNT NUMBER INVALID. ACCOUNT NUMBER NOT FOUND");
                    statusLabel.setText("Transfer failed. Account number invalid.");
                }
                catch(Exception err){
                    statusLabel.setText("Amount and account number must be a number.");
                    err.printStackTrace();
                }
            }
        });


        mainContentPanel.add(statusLabel);
        mainContentPanel.add(transferButton);
        mainContentPanel.add(accountJTextField);
        mainContentPanel.add(AccounJLabel);
        mainContentPanel.add(amountJLabel);
        mainContentPanel.add(amounttTextField);
        mainContentPanel.add(BalanceLabel);
        mainContentPanel.add(textBackgroundPanel);
        mainContentPanel.add(exitButton);
        
        backgroundPanel.add(mainContentPanel);
        repaint();
        setVisible(true);
    } 
    // ========================= TRANSFER WINDOW =====================


    // ========================= BALANCE WINDOW =====================
    public void runBalance (Account account, String accountType){ 
        backgroundPanel.removeAll();
        mainContentPanel.removeAll();
        actionTextLabel.setText("Balance");

        Balance balanceManager = new Balance();

        JLabel BalanceLabel = new JLabel();
        BalanceLabel.setBounds(actionTextLabel.getX() - 8, actionTextLabel.getY() + 50, 300, 30);
        BalanceLabel.setFont(new Font("Arial", Font.BOLD, 20));
        BalanceLabel.setForeground(Color.WHITE);

        JLabel AccounJLabel = new JLabel("");
        AccounJLabel.setBounds(BalanceLabel.getX(),  BalanceLabel.getY() + 35, debit_credit_button_size.width + 90,20);
        AccounJLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        AccounJLabel.setForeground(Color.WHITE);

        JLabel Acc_LimitJLabel = new JLabel("");
        Acc_LimitJLabel.setBounds(BalanceLabel.getX(),  AccounJLabel.getY() + 35, debit_credit_button_size.width + 90,20);
        Acc_LimitJLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        Acc_LimitJLabel.setForeground(Color.WHITE);

        JButton BalanceButton = new JButton("");
        BalanceButton.setBounds(Acc_LimitJLabel.getX(), Acc_LimitJLabel.getY() + 40, 300,42);
        BalanceButton.setFont(new Font("Arial", Font.PLAIN, 20));
        BalanceButton.setBackground(Color.WHITE);
        BalanceButton.setForeground(Color.BLACK);
        BalanceButton.setEnabled(false);
        BalanceButton.setHorizontalAlignment(SwingConstants.LEFT);

        if(accountType.equals("c")){
            BalanceLabel.setText("Credit Account");
            AccounJLabel.setText("Account Number: " + account.getAccountNum());
            Acc_LimitJLabel.setText("Account Limit: " + decimalFormat.format(account.getLimit()));
            BalanceButton.setText("Outstanding Balance: " + decimalFormat.format(balanceManager.getCurrentBalance(account.getAccountNum())));
        }
        else{
            BalanceLabel.setText("Debit Account");
            AccounJLabel.setText("Account Number: " + account.getAccountNum());
            AccounJLabel.setBounds(BalanceLabel.getX(),BalanceLabel.getY()+65,300, 42);
            BalanceButton.setText("Current Balance: " + decimalFormat.format(balanceManager.getCurrentBalance(account.getAccountNum())));
        }   


        mainContentPanel.add(BalanceButton);   
        mainContentPanel.add(BalanceLabel);
        mainContentPanel.add(AccounJLabel);
        mainContentPanel.add(Acc_LimitJLabel);
        mainContentPanel.add(textBackgroundPanel);
        mainContentPanel.add(exitButton);

        backgroundPanel.add(mainContentPanel);
        repaint();
        setVisible(true);
    }

    // ========================= BALANCE WINDOW =====================


    // ========================= DEBUG WINDOW =====================
    public void runDebug(){
        Account credAcc = new Account();credAcc.setAccountNum(111111111);credAcc.setBalance(1000000000.99);credAcc.setLimit(9999999999999.99);credAcc.setType("c");
        Account debAcc = new Account();debAcc.setAccountNum(222222);debAcc.setBalance(100000.99);debAcc.setType("d");
        String accountType = "c";

        JFrame debugFrame = new JFrame("Debug Buttons");
        debugFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        debugFrame.setSize(login_form_size);
        debugFrame.setLayout(new GridLayout(3, 3, 2, 2));
        debugFrame.setResizable(false);
        debugFrame.setLocationRelativeTo(null);

        JButton loginWindowButton = new JButton("Login");
        loginWindowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                runLogin();
            }
        });

        JButton cSelectWindowButton = new JButton("C Select");
        cSelectWindowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backgroundPanel.removeAll();
                runCreditSelection(credAcc);
            }
        });

        JButton dSelectWindowButton = new JButton("D Select");
        dSelectWindowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backgroundPanel.removeAll();
                runDebitSelection(debAcc);
            }
        });

        JButton withdrawWindowButton = new JButton("Withdraw");
        withdrawWindowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backgroundPanel.removeAll();
                runWithdraw(credAcc, accountType);
            }
        });

        JButton paymentWindowButton = new JButton("Payment");
        paymentWindowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backgroundPanel.removeAll();
                runPayment(credAcc);
            }
        });

        JButton depositWindowButton = new JButton("Deposit");
        depositWindowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backgroundPanel.removeAll();
                runDeposit(debAcc);
            }
        });
        
        JButton transferWindowButton = new JButton("Transfer");
        transferWindowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backgroundPanel.removeAll();
                runTransfer(debAcc, accountType);
            }
        });

        JButton balanceWindowButton = new JButton("Balance");
        balanceWindowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backgroundPanel.removeAll();
                runBalance(credAcc, accountType);
            }
        });

        debugFrame.add(loginWindowButton);
        debugFrame.add(cSelectWindowButton);
        debugFrame.add(dSelectWindowButton);
        debugFrame.add(withdrawWindowButton);
        debugFrame.add(paymentWindowButton);
        debugFrame.add(depositWindowButton);
        debugFrame.add(transferWindowButton);
        debugFrame.add(balanceWindowButton);
        debugFrame.setVisible(true);
    }
    // ========================= DEBUG WINDOW =====================

}
