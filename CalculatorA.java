import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.event.*;
import java.util.Arrays;

public class CalculatorA {

    int boardwidth = 360;
    int boardheight = 540;

    Color customBlack = new Color(28, 28, 28);
    Color customDarkgreen = new Color(48, 50 ,52);
    Color customOlive  = new Color(60, 76, 36);
    Color customWhite = new Color(255,255,255); 

    String[] buttonValues = {
        "AC", "+/-", "%", "÷", 
        "7", "8", "9", "×", 
        "4", "5", "6", "-",
        "1", "2", "3", "+",
        "0", ".", "√", "=",
              "INFO"
    };

    String[] rightSymbols = {"÷", "×", "-", "+", "="};
    String[] topSymbols = {"AC", "+/-", "%"};

    JFrame frame = new JFrame("---------Ayshi-Calculator----------");
    JLabel displayJLabel = new JLabel();
    JPanel displayPanel = new JPanel();
    JPanel buttonsPanel = new JPanel();

    double num1 = 0;
    double num2 = 0;
    String operator = "";
    boolean startNewNumber = true;

    public CalculatorA() {

        frame.setSize(boardwidth, boardheight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

    
        displayJLabel.setBackground(customBlack);
        displayJLabel.setForeground(customWhite);
        displayJLabel.setFont(new Font("Arial", Font.PLAIN, 60));
        displayJLabel.setHorizontalAlignment(JLabel.RIGHT);
        displayJLabel.setText("0");
        displayJLabel.setOpaque(true);

        displayPanel.setLayout(new BorderLayout());
        displayPanel.add(displayJLabel, BorderLayout.CENTER);
        frame.add(displayPanel, BorderLayout.NORTH);

        buttonsPanel.setLayout(new GridLayout(6, 4));
        buttonsPanel.setBackground(customBlack);

        for (String val : buttonValues) {

            JButton btn = new JButton(val);
              btn.setFont(new Font("Arial", Font.BOLD, 24));
            btn.setFocusable(false);
               btn.setBorder(new LineBorder(customBlack));

          if (Arrays.asList(topSymbols).contains(val)) {
        btn.setBackground(customDarkgreen);
                btn.setForeground(customWhite);
         } else if (Arrays.asList(rightSymbols).contains(val)) {
                btn.setBackground(customOlive);
                btn.setForeground(customBlack);
             } else {
                btn.setBackground(customDarkgreen);
                btn.setForeground(customWhite);
            }

            btn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {

                    String value = btn.getText();

                    if ("0123456789".contains(value)) {
                        if (displayJLabel.getText().equals("0") || startNewNumber) {
                     displayJLabel.setText(value);
                        startNewNumber = false;
                        } else {
                        displayJLabel.setText(displayJLabel.getText() + value);
                        }
                    }

              else if (value.equals(".")) {
                 if (!displayJLabel.getText().contains(".")) {
               displayJLabel.setText(displayJLabel.getText() + ".");
                        }
                    }

                    else if (value.equals("AC")) {
                        displayJLabel.setText("0");
                        num1 = 0;
                        operator = "";
                        displayJLabel.setFont(new Font("Arial", Font.PLAIN, 60));
                       
                    }

            
                    else if (value.equals("+") || value.equals("-") ||
                             value.equals("×") || value.equals("÷")) {
                        num1 = Double.parseDouble(displayJLabel.getText());
                        operator = value;
                        startNewNumber = true;
                    }

                    else if (value.equals("=")) {
                        num2 = Double.parseDouble(displayJLabel.getText());
                        double result = 0;
                        if (operator.equals("+")) result = num1 + num2;
                        else if (operator.equals("-")) result = num1 - num2;
                        else if (operator.equals("×")) result = num1 * num2;
                        else if (operator.equals("÷")) result = num1 / num2;

                        displayJLabel.setText(String.valueOf(result));
                        startNewNumber = true;
                    }

                    else if (value.equals("%")) {
                        double current = Double.parseDouble(displayJLabel.getText());
                          displayJLabel.setText(String.valueOf(current / 100));
                            startNewNumber = true;
                    }

                    else if (value.equals("√")) {
                        double current = Double.parseDouble(displayJLabel.getText());
                          if (current < 0) {
                            displayJLabel.setText("Error");
                        }
                         else {
                            displayJLabel.setText(String.valueOf(Math.sqrt(current)));
                        }
                        startNewNumber = true;
                    }
                    else if (value.equals("INFO")) {
                        displayJLabel.setFont(new Font("Arial", Font.PLAIN, 14));
                        displayJLabel.setText(
                            "<html>Name:Oyshikee<br>" +
                            "ID: 20242123010<br>" +
                            "Course: Software Development Laboratory<br>" +
                            "Code: CSE2200<br>" +
                            "Teacher:Abu Naim Khan<br>" +
                            "Project: Java Calculator</html>"
                        );
                        startNewNumber = true;
                    }
                }
            });

                buttonsPanel.add(btn);
        }

           frame.add(buttonsPanel, BorderLayout.CENTER);
               frame.setVisible(true);
    }

    public static void main(String[] args) {
        new CalculatorA();
    }
}