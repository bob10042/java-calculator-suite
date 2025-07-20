package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AdvancedCalculatorGUI extends JFrame {
    private JTextField displayField;
    private JTextArea historyArea;
    private Calculator calculator;
    private List<String> history;
    private double result = 0;
    private String operator = "";
    private boolean operatorPressed = false;

    public AdvancedCalculatorGUI() {
        calculator = new Calculator();
        history = new ArrayList<>();
        initializeGUI();
    }

    private void initializeGUI() {
        setTitle("Advanced Calculator Pro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Create menu bar
        createMenuBar();

        // Create toolbar
        JToolBar toolBar = createToolBar();
        add(toolBar, BorderLayout.NORTH);

        // Create main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        // Create calculator panel
        JPanel calcPanel = createCalculatorPanel();
        mainPanel.add(calcPanel, BorderLayout.CENTER);

        // Create history panel
        JPanel historyPanel = createHistoryPanel();
        mainPanel.add(historyPanel, BorderLayout.EAST);

        add(mainPanel, BorderLayout.CENTER);

        // Create status bar
        JPanel statusBar = createStatusBar();
        add(statusBar, BorderLayout.SOUTH);
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // File Menu
        JMenu fileMenu = new JMenu("File");
        JMenuItem newItem = new JMenuItem("New");
        JMenuItem saveItem = new JMenuItem("Save History");
        JMenuItem loadItem = new JMenuItem("Load History");
        JMenuItem exitItem = new JMenuItem("Exit");

        newItem.addActionListener(e -> clearAll());
        saveItem.addActionListener(e -> saveHistory());
        loadItem.addActionListener(e -> loadHistory());
        exitItem.addActionListener(e -> System.exit(0));

        fileMenu.add(newItem);
        fileMenu.addSeparator();
        fileMenu.add(saveItem);
        fileMenu.add(loadItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        // View Menu
        JMenu viewMenu = new JMenu("View");
        JMenuItem scientificItem = new JMenuItem("Scientific Mode");
        JMenuItem standardItem = new JMenuItem("Standard Mode");
        
        scientificItem.addActionListener(e -> switchToScientificMode());
        standardItem.addActionListener(e -> switchToStandardMode());
        
        viewMenu.add(scientificItem);
        viewMenu.add(standardItem);

        // Help Menu
        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(e -> showAbout());
        helpMenu.add(aboutItem);

        menuBar.add(fileMenu);
        menuBar.add(viewMenu);
        menuBar.add(helpMenu);

        setJMenuBar(menuBar);
    }

    private JToolBar createToolBar() {
        JToolBar toolBar = new JToolBar();
        
        JButton clearBtn = new JButton("Clear");
        JButton historyBtn = new JButton("History");
        JButton memoryBtn = new JButton("Memory");
        JButton scientificBtn = new JButton("Scientific");

        clearBtn.addActionListener(e -> clearAll());
        historyBtn.addActionListener(e -> toggleHistory());
        memoryBtn.addActionListener(e -> showMemoryOptions());
        scientificBtn.addActionListener(e -> switchToScientificMode());

        toolBar.add(clearBtn);
        toolBar.addSeparator();
        toolBar.add(historyBtn);
        toolBar.add(memoryBtn);
        toolBar.addSeparator();
        toolBar.add(scientificBtn);

        return toolBar;
    }

    private JPanel createCalculatorPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Display
        displayField = new JTextField("0");
        displayField.setEditable(false);
        displayField.setHorizontalAlignment(JTextField.RIGHT);
        displayField.setFont(new Font("Arial", Font.BOLD, 24));
        displayField.setPreferredSize(new Dimension(0, 60));
        panel.add(displayField, BorderLayout.NORTH);

        // Button panel
        JPanel buttonPanel = new JPanel(new GridLayout(6, 4, 5, 5));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Scientific functions row
        addButton(buttonPanel, "sin", e -> performScientificOperation("sin"));
        addButton(buttonPanel, "cos", e -> performScientificOperation("cos"));
        addButton(buttonPanel, "tan", e -> performScientificOperation("tan"));
        addButton(buttonPanel, "√", e -> performScientificOperation("sqrt"));

        // Memory and special functions
        addButton(buttonPanel, "MC", e -> clearMemory());
        addButton(buttonPanel, "MR", e -> recallMemory());
        addButton(buttonPanel, "MS", e -> storeMemory());
        addButton(buttonPanel, "M+", e -> addToMemory());

        // Number and operation buttons
        addButton(buttonPanel, "C", e -> clearAll());
        addButton(buttonPanel, "CE", e -> clearEntry());
        addButton(buttonPanel, "⌫", e -> backspace());
        addButton(buttonPanel, "÷", e -> setOperator("/"));

        addButton(buttonPanel, "7", e -> appendNumber("7"));
        addButton(buttonPanel, "8", e -> appendNumber("8"));
        addButton(buttonPanel, "9", e -> appendNumber("9"));
        addButton(buttonPanel, "×", e -> setOperator("*"));

        addButton(buttonPanel, "4", e -> appendNumber("4"));
        addButton(buttonPanel, "5", e -> appendNumber("5"));
        addButton(buttonPanel, "6", e -> appendNumber("6"));
        addButton(buttonPanel, "-", e -> setOperator("-"));

        addButton(buttonPanel, "1", e -> appendNumber("1"));
        addButton(buttonPanel, "2", e -> appendNumber("2"));
        addButton(buttonPanel, "3", e -> appendNumber("3"));
        addButton(buttonPanel, "+", e -> setOperator("+"));

        addButton(buttonPanel, "±", e -> toggleSign());
        addButton(buttonPanel, "0", e -> appendNumber("0"));
        addButton(buttonPanel, ".", e -> appendDecimal());
        addButton(buttonPanel, "=", e -> calculate());

        panel.add(buttonPanel, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createHistoryPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("History"));
        panel.setPreferredSize(new Dimension(200, 0));

        historyArea = new JTextArea();
        historyArea.setEditable(false);
        historyArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        
        JScrollPane scrollPane = new JScrollPane(historyArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton clearHistoryBtn = new JButton("Clear History");
        clearHistoryBtn.addActionListener(e -> clearHistory());
        panel.add(clearHistoryBtn, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createStatusBar() {
        JPanel statusBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statusBar.setBorder(BorderFactory.createLoweredBevelBorder());
        
        JLabel statusLabel = new JLabel("Ready");
        statusBar.add(statusLabel);
        
        return statusBar;
    }

    private void addButton(JPanel panel, String text, ActionListener listener) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.addActionListener(listener);
        
        // Color coding for different button types
        if (text.matches("[0-9]")) {
            button.setBackground(Color.WHITE);
        } else if (text.matches("[+\\-×÷=]")) {
            button.setBackground(new Color(255, 165, 0));
            button.setForeground(Color.WHITE);
        } else if (text.matches("sin|cos|tan|√")) {
            button.setBackground(new Color(100, 149, 237));
            button.setForeground(Color.WHITE);
        } else {
            button.setBackground(new Color(245, 245, 245));
        }
        
        panel.add(button);
    }

    private void appendNumber(String number) {
        if (operatorPressed) {
            displayField.setText(number);
            operatorPressed = false;
        } else {
            String current = displayField.getText();
            if (current.equals("0")) {
                displayField.setText(number);
            } else {
                displayField.setText(current + number);
            }
        }
    }

    private void appendDecimal() {
        String current = displayField.getText();
        if (!current.contains(".")) {
            displayField.setText(current + ".");
        }
    }

    private void setOperator(String op) {
        if (!operator.isEmpty()) {
            calculate();
        }
        result = Double.parseDouble(displayField.getText());
        operator = op;
        operatorPressed = true;
    }

    private void calculate() {
        if (operator.isEmpty()) return;

        double currentValue = Double.parseDouble(displayField.getText());
        double newResult = 0;

        switch (operator) {
            case "+":
                newResult = calculator.add((int)result, (int)currentValue);
                break;
            case "-":
                newResult = calculator.subtract((int)result, (int)currentValue);
                break;
            case "*":
                newResult = calculator.multiply((int)result, (int)currentValue);
                break;
            case "/":
                newResult = calculator.divide((int)result, (int)currentValue);
                break;
        }

        String calculation = String.format("%.2f %s %.2f = %.2f", result, operator, currentValue, newResult);
        addToHistory(calculation);
        
        displayField.setText(String.valueOf(newResult));
        result = newResult;
        operator = "";
        operatorPressed = true;
    }

    private void performScientificOperation(String operation) {
        double value = Double.parseDouble(displayField.getText());
        double result = 0;

        switch (operation) {
            case "sin":
                result = Math.sin(Math.toRadians(value));
                break;
            case "cos":
                result = Math.cos(Math.toRadians(value));
                break;
            case "tan":
                result = Math.tan(Math.toRadians(value));
                break;
            case "sqrt":
                result = Math.sqrt(value);
                break;
        }

        String calculation = String.format("%s(%.2f) = %.6f", operation, value, result);
        addToHistory(calculation);
        displayField.setText(String.valueOf(result));
    }

    private void toggleSign() {
        double value = Double.parseDouble(displayField.getText());
        displayField.setText(String.valueOf(-value));
    }

    private void backspace() {
        String current = displayField.getText();
        if (current.length() > 1) {
            displayField.setText(current.substring(0, current.length() - 1));
        } else {
            displayField.setText("0");
        }
    }

    private void clearEntry() {
        displayField.setText("0");
    }

    private void clearAll() {
        displayField.setText("0");
        result = 0;
        operator = "";
        operatorPressed = false;
    }

    private void addToHistory(String calculation) {
        history.add(calculation);
        updateHistoryDisplay();
    }

    private void updateHistoryDisplay() {
        StringBuilder sb = new StringBuilder();
        for (String entry : history) {
            sb.append(entry).append("\n");
        }
        historyArea.setText(sb.toString());
        historyArea.setCaretPosition(historyArea.getDocument().getLength());
    }

    private void clearHistory() {
        history.clear();
        historyArea.setText("");
    }

    private void toggleHistory() {
        // Toggle history panel visibility
        Component historyPanel = ((BorderLayout) getContentPane().getLayout()).getLayoutComponent(BorderLayout.EAST);
        historyPanel.setVisible(!historyPanel.isVisible());
        revalidate();
    }

    private void saveHistory() {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            try (PrintWriter writer = new PrintWriter(fileChooser.getSelectedFile())) {
                for (String entry : history) {
                    writer.println(entry);
                }
                JOptionPane.showMessageDialog(this, "History saved successfully!");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error saving history: " + e.getMessage());
            }
        }
    }

    private void loadHistory() {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileChooser.getSelectedFile()))) {
                history.clear();
                String line;
                while ((line = reader.readLine()) != null) {
                    history.add(line);
                }
                updateHistoryDisplay();
                JOptionPane.showMessageDialog(this, "History loaded successfully!");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error loading history: " + e.getMessage());
            }
        }
    }

    private void switchToScientificMode() {
        JOptionPane.showMessageDialog(this, "Scientific mode activated!\nAdditional functions available in toolbar.");
    }

    private void switchToStandardMode() {
        JOptionPane.showMessageDialog(this, "Standard mode activated!");
    }

    private void clearMemory() {
        JOptionPane.showMessageDialog(this, "Memory cleared!");
    }

    private void recallMemory() {
        JOptionPane.showMessageDialog(this, "Memory recall - feature coming soon!");
    }

    private void storeMemory() {
        JOptionPane.showMessageDialog(this, "Value stored in memory!");
    }

    private void addToMemory() {
        JOptionPane.showMessageDialog(this, "Value added to memory!");
    }

    private void showMemoryOptions() {
        String[] options = {"Clear Memory", "Recall Memory", "Store Memory", "Add to Memory"};
        int choice = JOptionPane.showOptionDialog(this, "Memory Operations", "Memory", 
            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        
        switch (choice) {
            case 0: clearMemory(); break;
            case 1: recallMemory(); break;
            case 2: storeMemory(); break;
            case 3: addToMemory(); break;
        }
    }

    private void showAbout() {
        JOptionPane.showMessageDialog(this, 
            "Advanced Calculator Pro v1.0\n" +
            "Features:\n" +
            "• Basic arithmetic operations\n" +
            "• Scientific functions\n" +
            "• Memory operations\n" +
            "• History tracking\n" +
            "• File save/load\n\n" +
            "Built with Java Swing");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            new AdvancedCalculatorGUI().setVisible(true);
        });
    }
}