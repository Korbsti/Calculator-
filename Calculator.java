import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
 
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
 
public class Calculator extends Frame implements KeyListener, ActionListener {
 
    private static final long serialVersionUID = 1L; // serial versio adn aqwdbadwa
 
    public static JTextArea textArea = new JTextArea(3, 25);
    public static JTextArea operationFields = new JTextArea(5, 25);
 
    public static JButton buttonOne = new JButton("1");
    public static JButton buttonTwo = new JButton("2");
    public static JButton buttonThree = new JButton("3");
    public static JButton buttonFour = new JButton("4");
    public static JButton buttonFive = new JButton("5");
    public static JButton buttonSix = new JButton("6");
    public static JButton buttonSeven = new JButton("7");
    public static JButton buttonEight = new JButton("8");
    public static JButton buttonNine = new JButton("9");
    public static JButton buttonZero = new JButton("0");
    public static JButton decimal = new JButton(".");
    public static JButton plusOperator = new JButton("+");
    public static JButton subtractOperator = new JButton("-");
    public static JButton multipleOperator = new JButton("*");
    public static JButton divideOperator = new JButton("/");
    public static JButton squareRoot = new JButton("√");
    public static JButton toThePowerOf = new JButton("^");
    public static JButton pi = new JButton("π");
    public static JButton abs = new JButton("abs");
 
    public static JButton equals = new JButton("=");
    public static JButton clear = new JButton("clear");
    public static ArrayList<Double> previousNum = new ArrayList<Double>();
    public static ArrayList<Integer> operationType = new ArrayList<Integer>();
    public static boolean set = false;
    public static int typeOfOperation;
    public static boolean isInvalid = false;
    public static boolean didOperation = false;
    public static ArrayList<String> allowedChars = new ArrayList<String>();
    public static StringBuilder operationFieldsTotalText = new StringBuilder();
 
    public Calculator() throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException,
            IllegalAccessException, IOException { // the class constructor and stuff
 
        for (int x = 0; x != 10; x++) {
            allowedChars.add(String.valueOf(x));
        }
        allowedChars.add("+");
        allowedChars.add("/");
        allowedChars.add("-");
        allowedChars.add("*");
        allowedChars.add(".");
        allowedChars.add("√");
        allowedChars.add("^");
        UIManager.LookAndFeelInfo[] looks = UIManager.getInstalledLookAndFeels();
        for (UIManager.LookAndFeelInfo look : looks) {
            System.out.println(look.getClassName());
        }
        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        JFrame f = new JFrame("Calculator");
        JPanel panel = new JPanel();
        JPanel viewPanel = new JPanel();
        
        textArea.setEditable(false);
        panel.setLayout(new GridLayout(6, 6, 6, 6));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10 ,10));
        operationFields.setEditable(true);
        viewPanel.add(textArea);
        viewPanel.add(operationFields);
        viewPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 20, 20));
        operationFields.setLineWrap(true);
        textArea.setLineWrap(true);
        operationFields.setEditable(false);
        operationFields.addKeyListener(this);
        viewPanel.setBackground(Color.black);
        panel.add(Box.createHorizontalGlue());
        panel.add(Box.createHorizontalGlue());
        panel.add(Box.createHorizontalGlue());
        panel.add(clear);
        panel.add(buttonOne);
        panel.add(buttonTwo);
        panel.add(buttonThree);
        panel.add(plusOperator);
        panel.add(buttonFour);
        panel.add(buttonFive);
        panel.add(buttonSix);
        panel.add(subtractOperator);
        panel.add(buttonSeven);
        panel.add(buttonEight);
        panel.add(buttonNine);
        panel.add(multipleOperator);
        panel.add(decimal);
        panel.add(buttonZero);
        panel.add(equals);
        panel.add(divideOperator);
        panel.add(abs);
        panel.add(pi);
        panel.add(toThePowerOf);
        panel.add(squareRoot);
        panel.setBackground(Color.black);
        f.setBackground(Color.black);
        f.add(viewPanel, BorderLayout.NORTH);
        f.add(panel);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setTitle("Calculator");
        f.pack();
        f.setSize(325, 600);
        f.setVisible(true);
    }
 
    /*
     * public static void GUI() { }
     */
 
    public static void previousNum() { // adding nuawmber onto rarary
        textArea.selectAll();
        String str = textArea.getSelectedText();
        try {
            previousNum.add(Double.parseDouble(str));
        } catch (Exception e) {
 
        }
    }
 
    public static void clearFirst() {
        previousNum.remove(0);
        operationType.remove(0);
    }
 
    public static String calculate() { // caluclaitng and stugfa
        double result = previousNum.get(0);
        int x = 0;
        System.out.println(previousNum.toString());
        System.out.println(operationType.toString());
        do {
            x = 0;
            try {
 
                if (operationType.isEmpty() || previousNum.isEmpty()) {
                    didOperation = true;
                    return String.valueOf(result);
                }
                switch (operationType.get(x)) {
                case 1:
                    result += previousNum.get(x + 1);
                    clearFirst();
                    break;
                case 2:
                    result -= previousNum.get(x + 1);
                    clearFirst();
                    break;
                case 3:
                    result *= previousNum.get(x + 1);
                    clearFirst();
                    break;
                case 4:
                    result /= previousNum.get(x + 1);
                    clearFirst();
                    break;
                case 5:
                    double num = previousNum.get(0);
                    previousNum.set(x, Math.sqrt(num));
                    result = Math.sqrt(num);
                    operationType.remove(0);
                    break;
                case 6:
                    result = Math.pow(previousNum.get(0), previousNum.get(x + 1));
                    clearFirst();
                    break;
                case 7:
                    result = Math.abs(previousNum.get(0));
                    clearFirst();
                    break;
                }
                x = 0;
 
            } catch (Exception stack) {
                stack.printStackTrace();
                operationFields.selectAll();
                operationFields.replaceSelection("");
                return "Error";
            }
            x++;
            System.out.println("Result: " + result);
            System.out.println("Iterator: " + x);
            System.out.println("previousNum: " + previousNum.toString());
            System.out.println("operationType: " + operationType.toString());
        } while (x < previousNum.size());
        return String.valueOf(result);
    }
 
    public static void startCalculating(String l) { // stuff happems
        try {
            String type = l;
            operationFieldsTotalText.append(l);
            textArea.selectAll();
            operationFields.selectAll();
            if (isInvalid) {
                clearBars();
                return;
            }
            if (didOperation) {
                clearBars();
                didOperation = false;
            }
            operationFields.append(type);
 
            if (type.equals("+")) {
                previousNum();
                textArea.replaceSelection("+");
                operationType.add(1);
                set = true;
            } else if (type.equals("-")) {
                previousNum();
                textArea.replaceSelection("-");
                operationType.add(2);
                set = true;
            } else if (type.equals("*")) {
                previousNum();
                textArea.replaceSelection("*");
                operationType.add(3);
                set = true;
            } else if (type.equals("/")) {
                previousNum();
                textArea.replaceSelection("/");
                operationType.add(4);
                set = true;
            } else if (type.equals(".")) {
                textArea.append(".");
 
            } else if (type.equals("√")) {
                previousNum();
                textArea.replaceSelection("√");
                operationType.add(5);
                set = true;
            } else if (type.equals("^")) {
                previousNum();
                textArea.replaceSelection("^");
                operationType.add(6);
                set = true;
            } else if (type.equals("π")) {
                previousNum.add(Math.PI);
                textArea.replaceSelection("π");
                set = true;
            } else if (type.equals("abs")) {
                previousNum();
                textArea.replaceSelection("abs");
                operationType.add(7);
                set = true;
                
            } else if (type.equals("=")) {
                System.out.println("Calculated");
                previousNum.add(Double.valueOf(textArea.getSelectedText()));
                String str = calculate();
                textArea.replaceSelection(str);
                operationFields.append(str);
                didOperation = true;
            } else {
                if (set) {
                    textArea.replaceSelection("");
                    set = false;
                }
                textArea.append(String.valueOf(l));
 
            }
        } catch (Exception eee) {
            eee.printStackTrace();
            isInvalid = true;
            textArea.replaceSelection("Invalid Operation Sequence, Click Clear");
            operationFields.replaceSelection("Invalid Operation Sequence, Click Clear");
            previousNum.clear();
            operationType.clear();
            set = false;
 
        }
    }
 
    public static void action(JButton button, String l) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startCalculating(l);
            }
        });
    }
 
    public static void clearBar() { // clearing both bars and stuff
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.selectAll();
                textArea.replaceSelection("");
                previousNum.clear();
                operationType.clear();
                operationFields.selectAll();
                operationFields.replaceSelection("");
                System.out.println("Cleared");
                isInvalid = false;
                didOperation = false;
                operationFieldsTotalText = new StringBuilder();
            }
        });
    }
 
    public static void clearBars() { // fucnton to clear abarase
        operationFields.selectAll();
        textArea.selectAll();
        textArea.replaceSelection("");
        previousNum.clear();
        operationType.clear();
        operationFields.replaceSelection("");
        System.out.println("Cleared");
        isInvalid = false;
        didOperation = false;
        operationFieldsTotalText = new StringBuilder();
 
    }
 
    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException,
            InstantiationException, IllegalAccessException, IOException { // adding buttons and initilizing the constructor
        new Calculator();
        // GUI();
        action(buttonOne, "1");
        action(buttonTwo, "2");
        action(buttonThree, "3");
        action(buttonFour, "4");
        action(buttonFive, "5");
        action(buttonSix, "6");
        action(buttonSeven, "7");
        action(buttonEight, "8");
        action(buttonNine, "9");
        action(buttonZero, "0");
        action(plusOperator, "+");
        action(multipleOperator, "*");
        action(subtractOperator, "-");
        action(divideOperator, "/");
        action(decimal, ".");
        action(equals, "=");
        action(squareRoot, "√");
        action(toThePowerOf, "^");
        action(pi, "π");
        action(abs, "abs");
        clearBar();
    }
 
    @Override
    public void keyTyped(KeyEvent e) { // key event and stuff
        String charValue = String.valueOf(e.getKeyChar()).split("")[0];
        operationFields.selectAll();
        for (String str : allowedChars) {
            if (str.equals(charValue)) {
                operationFields.replaceSelection(operationFieldsTotalText.toString());
                startCalculating(charValue);
            }
        }
    }
 
    @Override
    public void keyPressed(KeyEvent e) {
 
    }
 
    @Override
    public void keyReleased(KeyEvent e) {
    }
 
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
 
    }
 
}
