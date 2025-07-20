package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PowerCalculatorGUI extends JFrame {
    private Calculator calculator;
    private JTabbedPane tabbedPane;
    
    // DC Power tab components
    private JTextField voltageField, currentField, resistanceField;
    private JTextField powerResultField;
    
    // 3-Phase Power tab components
    private JTextField phaseVoltageField, phaseCurrentField, powerFactorField;
    private JTextField threePhasePowerField, threePhaseApparentField, threePhaseReactiveField;
    
    // Impedance tab components
    private JTextField resistanceZField, reactanceField;
    private JTextField impedanceMagField, impedanceAngleField;

    public PowerCalculatorGUI() {
        calculator = new Calculator();
        initializeGUI();
    }

    private void initializeGUI() {
        setTitle("Electrical Power & 3-Phase Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        tabbedPane = new JTabbedPane();
        
        // Create tabs
        tabbedPane.addTab("DC Power", createDCPowerPanel());
        tabbedPane.addTab("3-Phase Power", createThreePhasePanel());
        tabbedPane.addTab("Impedance", createImpedancePanel());
        tabbedPane.addTab("Math Functions", createMathPanel());

        add(tabbedPane, BorderLayout.CENTER);

        // Create menu bar
        createMenuBar();
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitItem);

        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(e -> showAboutDialog());
        helpMenu.add(aboutItem);

        menuBar.add(fileMenu);
        menuBar.add(helpMenu);

        setJMenuBar(menuBar);
    }

    private JPanel createDCPowerPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Title
        JLabel titleLabel = new JLabel("DC Power Calculations");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 3;
        panel.add(titleLabel, gbc);

        gbc.gridwidth = 1;

        // Voltage input
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Voltage (V):"), gbc);
        gbc.gridx = 1;
        voltageField = new JTextField(10);
        panel.add(voltageField, gbc);

        // Current input
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Current (A):"), gbc);
        gbc.gridx = 1;
        currentField = new JTextField(10);
        panel.add(currentField, gbc);

        // Resistance input
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Resistance (Ω):"), gbc);
        gbc.gridx = 1;
        resistanceField = new JTextField(10);
        panel.add(resistanceField, gbc);

        // Calculate button
        gbc.gridx = 2; gbc.gridy = 2;
        JButton calculatePowerBtn = new JButton("Calculate Power");
        calculatePowerBtn.addActionListener(this::calculateDCPower);
        panel.add(calculatePowerBtn, gbc);

        // Result
        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(new JLabel("Power (W):"), gbc);
        gbc.gridx = 1;
        powerResultField = new JTextField(10);
        powerResultField.setEditable(false);
        powerResultField.setBackground(Color.LIGHT_GRAY);
        panel.add(powerResultField, gbc);

        // Formula information
        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 3;
        JTextArea formulaArea = new JTextArea(
            "Formulas:\n" +
            "P = V × I (Power = Voltage × Current)\n" +
            "P = V² / R (Power = Voltage² / Resistance)\n" +
            "P = I² × R (Power = Current² × Resistance)"
        );
        formulaArea.setEditable(false);
        formulaArea.setBackground(panel.getBackground());
        formulaArea.setBorder(BorderFactory.createTitledBorder("Reference"));
        panel.add(formulaArea, gbc);

        return panel;
    }

    private JPanel createThreePhasePanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Title
        JLabel titleLabel = new JLabel("3-Phase Power Calculations");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 3;
        panel.add(titleLabel, gbc);

        gbc.gridwidth = 1;

        // Phase voltage input
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Line Voltage (V):"), gbc);
        gbc.gridx = 1;
        phaseVoltageField = new JTextField(10);
        panel.add(phaseVoltageField, gbc);

        // Phase current input
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Line Current (A):"), gbc);
        gbc.gridx = 1;
        phaseCurrentField = new JTextField(10);
        panel.add(phaseCurrentField, gbc);

        // Power factor input
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Power Factor:"), gbc);
        gbc.gridx = 1;
        powerFactorField = new JTextField(10);
        powerFactorField.setText("0.8"); // Default power factor
        panel.add(powerFactorField, gbc);

        // Calculate button
        gbc.gridx = 2; gbc.gridy = 2;
        JButton calculateThreePhaseBtn = new JButton("Calculate");
        calculateThreePhaseBtn.addActionListener(this::calculateThreePhasePower);
        panel.add(calculateThreePhaseBtn, gbc);

        // Results
        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(new JLabel("Real Power (W):"), gbc);
        gbc.gridx = 1;
        threePhasePowerField = new JTextField(10);
        threePhasePowerField.setEditable(false);
        threePhasePowerField.setBackground(Color.LIGHT_GRAY);
        panel.add(threePhasePowerField, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
        panel.add(new JLabel("Apparent Power (VA):"), gbc);
        gbc.gridx = 1;
        threePhaseApparentField = new JTextField(10);
        threePhaseApparentField.setEditable(false);
        threePhaseApparentField.setBackground(Color.LIGHT_GRAY);
        panel.add(threePhaseApparentField, gbc);

        gbc.gridx = 0; gbc.gridy = 6;
        panel.add(new JLabel("Reactive Power (VAR):"), gbc);
        gbc.gridx = 1;
        threePhaseReactiveField = new JTextField(10);
        threePhaseReactiveField.setEditable(false);
        threePhaseReactiveField.setBackground(Color.LIGHT_GRAY);
        panel.add(threePhaseReactiveField, gbc);

        // Formula information
        gbc.gridx = 0; gbc.gridy = 7; gbc.gridwidth = 3;
        JTextArea formulaArea = new JTextArea(
            "3-Phase Formulas:\n" +
            "P = √3 × VL × IL × cos(φ) (Real Power)\n" +
            "S = √3 × VL × IL (Apparent Power)\n" +
            "Q = √3 × VL × IL × sin(φ) (Reactive Power)\n" +
            "VLL = √3 × VLN (Line-to-Line Voltage)"
        );
        formulaArea.setEditable(false);
        formulaArea.setBackground(panel.getBackground());
        formulaArea.setBorder(BorderFactory.createTitledBorder("Reference"));
        panel.add(formulaArea, gbc);

        return panel;
    }

    private JPanel createImpedancePanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Title
        JLabel titleLabel = new JLabel("Impedance Calculations");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 3;
        panel.add(titleLabel, gbc);

        gbc.gridwidth = 1;

        // Resistance input
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Resistance (Ω):"), gbc);
        gbc.gridx = 1;
        resistanceZField = new JTextField(10);
        panel.add(resistanceZField, gbc);

        // Reactance input
        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(new JLabel("Reactance (Ω):"), gbc);
        gbc.gridx = 1;
        reactanceField = new JTextField(10);
        panel.add(reactanceField, gbc);

        // Calculate button
        gbc.gridx = 2; gbc.gridy = 1;
        JButton calculateImpedanceBtn = new JButton("Calculate");
        calculateImpedanceBtn.addActionListener(this::calculateImpedance);
        panel.add(calculateImpedanceBtn, gbc);

        // Results
        gbc.gridx = 0; gbc.gridy = 3;
        panel.add(new JLabel("Impedance Magnitude (Ω):"), gbc);
        gbc.gridx = 1;
        impedanceMagField = new JTextField(10);
        impedanceMagField.setEditable(false);
        impedanceMagField.setBackground(Color.LIGHT_GRAY);
        panel.add(impedanceMagField, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        panel.add(new JLabel("Phase Angle (°):"), gbc);
        gbc.gridx = 1;
        impedanceAngleField = new JTextField(10);
        impedanceAngleField.setEditable(false);
        impedanceAngleField.setBackground(Color.LIGHT_GRAY);
        panel.add(impedanceAngleField, gbc);

        // Formula information
        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 3;
        JTextArea formulaArea = new JTextArea(
            "Impedance Formulas:\n" +
            "Z = √(R² + X²) (Impedance Magnitude)\n" +
            "φ = arctan(X/R) (Phase Angle)\n" +
            "Where: R = Resistance, X = Reactance"
        );
        formulaArea.setEditable(false);
        formulaArea.setBackground(panel.getBackground());
        formulaArea.setBorder(BorderFactory.createTitledBorder("Reference"));
        panel.add(formulaArea, gbc);

        return panel;
    }

    private JPanel createMathPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Title
        JLabel titleLabel = new JLabel("Mathematical Functions");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 4;
        panel.add(titleLabel, gbc);

        gbc.gridwidth = 1;

        // Power function
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(new JLabel("Base:"), gbc);
        gbc.gridx = 1;
        JTextField baseField = new JTextField(8);
        panel.add(baseField, gbc);

        gbc.gridx = 2;
        panel.add(new JLabel("Exponent:"), gbc);
        gbc.gridx = 3;
        JTextField exponentField = new JTextField(8);
        panel.add(exponentField, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        JButton powerBtn = new JButton("Calculate Power");
        powerBtn.addActionListener(e -> {
            try {
                double base = Double.parseDouble(baseField.getText());
                double exponent = Double.parseDouble(exponentField.getText());
                double result = calculator.power(base, exponent);
                JOptionPane.showMessageDialog(this, 
                    String.format("%.3f^%.3f = %.6f", base, exponent, result),
                    "Power Result", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid numbers", 
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        panel.add(powerBtn, gbc);

        // Square and Cube functions
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 1;
        panel.add(new JLabel("Value:"), gbc);
        gbc.gridx = 1;
        JTextField valueField = new JTextField(10);
        panel.add(valueField, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        JButton squareBtn = new JButton("Square");
        squareBtn.addActionListener(e -> {
            try {
                double value = Double.parseDouble(valueField.getText());
                double result = calculator.square(value);
                JOptionPane.showMessageDialog(this, 
                    String.format("%.3f² = %.6f", value, result),
                    "Square Result", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number", 
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        panel.add(squareBtn, gbc);

        gbc.gridx = 1;
        JButton cubeBtn = new JButton("Cube");
        cubeBtn.addActionListener(e -> {
            try {
                double value = Double.parseDouble(valueField.getText());
                double result = calculator.cube(value);
                JOptionPane.showMessageDialog(this, 
                    String.format("%.3f³ = %.6f", value, result),
                    "Cube Result", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number", 
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        panel.add(cubeBtn, gbc);

        return panel;
    }

    private void calculateDCPower(ActionEvent e) {
        try {
            String voltageText = voltageField.getText().trim();
            String currentText = currentField.getText().trim();
            String resistanceText = resistanceField.getText().trim();

            double power = 0;

            if (!voltageText.isEmpty() && !currentText.isEmpty()) {
                // Calculate using P = V * I
                double voltage = Double.parseDouble(voltageText);
                double current = Double.parseDouble(currentText);
                power = calculator.calculatePower(voltage, current);
            } else if (!voltageText.isEmpty() && !resistanceText.isEmpty()) {
                // Calculate using P = V² / R
                double voltage = Double.parseDouble(voltageText);
                double resistance = Double.parseDouble(resistanceText);
                power = calculator.calculatePowerFromResistance(voltage, resistance);
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Please enter either (Voltage + Current) or (Voltage + Resistance)", 
                    "Input Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            powerResultField.setText(String.format("%.3f", power));

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers", 
                "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), 
                "Calculation Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void calculateThreePhasePower(ActionEvent e) {
        try {
            double voltage = Double.parseDouble(phaseVoltageField.getText());
            double current = Double.parseDouble(phaseCurrentField.getText());
            double powerFactor = Double.parseDouble(powerFactorField.getText());

            double realPower = calculator.calculateThreePhaseRealPower(voltage, current, powerFactor);
            double apparentPower = calculator.calculateThreePhaseApparentPower(voltage, current);
            double reactivePower = calculator.calculateThreePhaseReactivePower(voltage, current, powerFactor);

            threePhasePowerField.setText(String.format("%.3f", realPower));
            threePhaseApparentField.setText(String.format("%.3f", apparentPower));
            threePhaseReactiveField.setText(String.format("%.3f", reactivePower));

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers", 
                "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), 
                "Calculation Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void calculateImpedance(ActionEvent e) {
        try {
            double resistance = Double.parseDouble(resistanceZField.getText());
            double reactance = Double.parseDouble(reactanceField.getText());

            double magnitude = calculator.calculateImpedanceMagnitude(resistance, reactance);
            double angle = calculator.calculateImpedanceAngle(resistance, reactance);

            impedanceMagField.setText(String.format("%.3f", magnitude));
            impedanceAngleField.setText(String.format("%.2f", angle));

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers", 
                "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), 
                "Calculation Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showAboutDialog() {
        JOptionPane.showMessageDialog(this, 
            "Electrical Power & 3-Phase Calculator v1.0\n\n" +
            "Features:\n" +
            "• DC Power Calculations (P = VI, P = V²/R)\n" +
            "• 3-Phase Power Analysis (Real, Apparent, Reactive)\n" +
            "• Impedance Calculations (Magnitude & Phase)\n" +
            "• Mathematical Functions (Power, Square, Cube)\n\n" +
            "Built with Java Swing\n" +
            "Perfect for electrical engineering calculations!",
            "About", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            new PowerCalculatorGUI().setVisible(true);
        });
    }
}