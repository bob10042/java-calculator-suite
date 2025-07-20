package com.example;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Advanced Programmable Console for Scientific Computing
 * Supports Java expressions, Python script execution, and physics calculations
 */
public class ProgrammableConsole extends JFrame {
    private JTextArea consoleArea;
    private JTextField inputField;
    private JScrollPane scrollPane;
    private Calculator calculator;
    private PhysicsConstants physics;
    private List<String> commandHistory;
    private int historyIndex;
    private Map<String, Double> variables;
    private boolean pythonMode;

    public ProgrammableConsole() {
        calculator = new Calculator();
        physics = new PhysicsConstants();
        commandHistory = new ArrayList<>();
        historyIndex = -1;
        variables = new HashMap<>();
        pythonMode = false;
        
        initializeConsole();
        setupPhysicsConstants();
        showWelcomeMessage();
    }

    private void initializeConsole() {
        setTitle("Scientific Programmable Console");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null);

        // Create main layout
        setLayout(new BorderLayout());

        // Console output area
        consoleArea = new JTextArea();
        consoleArea.setEditable(false);
        consoleArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        consoleArea.setBackground(new Color(20, 20, 20));
        consoleArea.setForeground(new Color(0, 255, 0));
        consoleArea.setCaretColor(new Color(0, 255, 0));

        scrollPane = new JScrollPane(consoleArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER);

        // Input panel
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.setBackground(new Color(20, 20, 20));

        JLabel promptLabel = new JLabel(">>> ");
        promptLabel.setFont(new Font("Monospaced", Font.BOLD, 14));
        promptLabel.setForeground(new Color(0, 255, 255));
        inputPanel.add(promptLabel, BorderLayout.WEST);

        inputField = new JTextField();
        inputField.setFont(new Font("Monospaced", Font.PLAIN, 14));
        inputField.setBackground(new Color(30, 30, 30));
        inputField.setForeground(Color.WHITE);
        inputField.setCaretColor(Color.WHITE);
        inputField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        inputField.addActionListener(this::processCommand);
        inputField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyPress(e);
            }
        });
        inputPanel.add(inputField, BorderLayout.CENTER);

        add(inputPanel, BorderLayout.SOUTH);

        // Create menu bar
        createMenuBar();

        // Focus on input field
        inputField.requestFocus();
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // File menu
        JMenu fileMenu = new JMenu("File");
        JMenuItem loadScript = new JMenuItem("Load Script");
        JMenuItem saveOutput = new JMenuItem("Save Output");
        JMenuItem clearConsole = new JMenuItem("Clear Console");
        JMenuItem exit = new JMenuItem("Exit");

        loadScript.addActionListener(e -> loadScript());
        saveOutput.addActionListener(e -> saveOutput());
        clearConsole.addActionListener(e -> clearConsole());
        exit.addActionListener(e -> System.exit(0));

        fileMenu.add(loadScript);
        fileMenu.add(saveOutput);
        fileMenu.addSeparator();
        fileMenu.add(clearConsole);
        fileMenu.addSeparator();
        fileMenu.add(exit);

        // Mode menu
        JMenu modeMenu = new JMenu("Mode");
        JMenuItem javaMode = new JMenuItem("Java Expression Mode");
        JMenuItem pythonModeItem = new JMenuItem("Python Script Mode");
        JMenuItem physicsMode = new JMenuItem("Physics Calculator Mode");

        javaMode.addActionListener(e -> setJavaMode());
        pythonModeItem.addActionListener(e -> setPythonMode());
        physicsMode.addActionListener(e -> setPhysicsMode());

        modeMenu.add(javaMode);
        modeMenu.add(pythonModeItem);
        modeMenu.add(physicsMode);

        // Help menu
        JMenu helpMenu = new JMenu("Help");
        JMenuItem commands = new JMenuItem("Commands");
        JMenuItem constants = new JMenuItem("Physics Constants");
        JMenuItem examples = new JMenuItem("Examples");
        JMenuItem runExamples = new JMenuItem("Run Physics Examples");
        JMenuItem about = new JMenuItem("About");

        commands.addActionListener(e -> showCommands());
        constants.addActionListener(e -> showConstants());
        examples.addActionListener(e -> showExamples());
        runExamples.addActionListener(e -> runPhysicsExamples());
        about.addActionListener(e -> showAbout());

        helpMenu.add(commands);
        helpMenu.add(constants);
        helpMenu.add(examples);
        helpMenu.add(runExamples);
        helpMenu.addSeparator();
        helpMenu.add(about);

        menuBar.add(fileMenu);
        menuBar.add(modeMenu);
        menuBar.add(helpMenu);

        setJMenuBar(menuBar);
    }

    private void setupPhysicsConstants() {
        // Load physics constants into variables
        variables.put("c", PhysicsConstants.c);
        variables.put("h", PhysicsConstants.h);
        variables.put("e", PhysicsConstants.e);
        variables.put("m_e", PhysicsConstants.m_e);
        variables.put("m_p", PhysicsConstants.m_p);
        variables.put("k_B", PhysicsConstants.k_B);
        variables.put("G", PhysicsConstants.G);
        variables.put("g", PhysicsConstants.g);
        variables.put("alpha", PhysicsConstants.alpha);
        variables.put("epsilon_0", PhysicsConstants.epsilon_0);
        variables.put("mu_0", PhysicsConstants.mu_0);
        variables.put("eV", PhysicsConstants.eV);
        variables.put("AU", PhysicsConstants.AU);
        variables.put("ly", PhysicsConstants.ly);
        variables.put("pi", Math.PI);
        variables.put("E", Math.E);
    }

    private void showWelcomeMessage() {
        appendToConsole("=== SCIENTIFIC PROGRAMMABLE CONSOLE ===\n");
        appendToConsole("Version 1.0 - Advanced Physics & Engineering Calculator\n\n");
        appendToConsole("Features:\n");
        appendToConsole("• Java Expression Evaluation\n");
        appendToConsole("• Python Script Execution\n");
        appendToConsole("• Physics Constants Library\n");
        appendToConsole("• Variable Storage\n");
        appendToConsole("• Mathematical Functions\n");
        appendToConsole("• Electrical Engineering Calculations\n\n");
        appendToConsole("Quick Start Examples:\n");
        appendToConsole(">>> 2 + 3 * 4                 (basic math)\n");
        appendToConsole(">>> sqrt(16) + sin(pi/2)      (math functions)\n");
        appendToConsole(">>> c                         (speed of light)\n");
        appendToConsole(">>> E = m_e * c^2             (electron rest energy)\n");
        appendToConsole(">>> photon_energy(500e-9)/eV  (green light photon)\n");
        appendToConsole(">>> python                    (switch to Python mode)\n\n");
        appendToConsole("Type 'help' for commands, 'examples' for more samples\n");
        appendToConsole("Current mode: Java Expression Mode\n\n");
    }

    private void processCommand(ActionEvent e) {
        String command = inputField.getText().trim();
        if (command.isEmpty()) return;

        // Add to history
        commandHistory.add(command);
        historyIndex = commandHistory.size();

        // Display command
        appendToConsole(">>> " + command + "\n");

        // Clear input
        inputField.setText("");

        // Process command
        try {
            String result = executeCommand(command);
            if (!result.isEmpty()) {
                appendToConsole(result + "\n");
            }
        } catch (Exception ex) {
            appendToConsole("ERROR: " + ex.getMessage() + "\n");
        }

        appendToConsole("\n");
    }

    private String executeCommand(String command) throws Exception {
        // Handle special commands
        if (command.equals("help")) {
            return getHelpText();
        } else if (command.equals("constants")) {
            return PhysicsConstants.getAllConstants();
        } else if (command.equals("variables") || command.equals("vars")) {
            return getVariables();
        } else if (command.equals("clear")) {
            clearConsole();
            return "";
        } else if (command.startsWith("save ")) {
            String varName = command.substring(5);
            return saveVariable(varName);
        } else if (command.equals("python")) {
            setPythonMode();
            return "Switched to Python mode";
        } else if (command.equals("java")) {
            setJavaMode();
            return "Switched to Java mode";
        } else if (command.equals("examples")) {
            return getExamplesText();
        } else if (command.startsWith("exec ")) {
            return executePythonScript(command.substring(5));
        }

        // Process based on current mode
        if (pythonMode) {
            return executePythonScript(command);
        } else {
            return evaluateJavaExpression(command);
        }
    }

    private String evaluateJavaExpression(String expression) {
        try {
            // Handle variable assignments
            if (expression.contains("=") && !expression.contains("==")) {
                return handleAssignment(expression);
            }

            // Replace variables
            String processedExpression = replaceVariables(expression);

            // Handle mathematical functions
            processedExpression = replaceMathFunctions(processedExpression);

            // Handle physics functions
            processedExpression = replacePhysicsFunctions(processedExpression);

            // Evaluate expression
            double result = evaluateExpression(processedExpression);
            
            // Store result in 'ans' variable
            variables.put("ans", result);

            return formatResult(result);

        } catch (Exception e) {
            throw new RuntimeException("Expression evaluation failed: " + e.getMessage());
        }
    }

    private String handleAssignment(String expression) {
        String[] parts = expression.split("=", 2);
        if (parts.length != 2) {
            throw new RuntimeException("Invalid assignment syntax");
        }

        String varName = parts[0].trim();
        String valueExpr = parts[1].trim();

        // Evaluate the right side
        String processedExpr = replaceVariables(valueExpr);
        processedExpr = replaceMathFunctions(processedExpr);
        processedExpr = replacePhysicsFunctions(processedExpr);

        double value = evaluateExpression(processedExpr);
        variables.put(varName, value);

        return varName + " = " + formatResult(value);
    }

    private String replaceVariables(String expression) {
        for (Map.Entry<String, Double> entry : variables.entrySet()) {
            String varName = entry.getKey();
            String value = entry.getValue().toString();
            
            // Use word boundaries to avoid partial replacements
            expression = expression.replaceAll("\\b" + Pattern.quote(varName) + "\\b", value);
        }
        return expression;
    }

    private String replaceMathFunctions(String expression) {
        // Replace mathematical functions
        expression = expression.replaceAll("\\bsin\\((.*?)\\)", "Math.sin($1)");
        expression = expression.replaceAll("\\bcos\\((.*?)\\)", "Math.cos($1)");
        expression = expression.replaceAll("\\btan\\((.*?)\\)", "Math.tan($1)");
        expression = expression.replaceAll("\\bsqrt\\((.*?)\\)", "Math.sqrt($1)");
        expression = expression.replaceAll("\\blog\\((.*?)\\)", "Math.log10($1)");
        expression = expression.replaceAll("\\bln\\((.*?)\\)", "Math.log($1)");
        expression = expression.replaceAll("\\bexp\\((.*?)\\)", "Math.exp($1)");
        expression = expression.replaceAll("\\babs\\((.*?)\\)", "Math.abs($1)");
        expression = expression.replaceAll("\\bpow\\((.*?),(.*?)\\)", "Math.pow($1,$2)");
        
        return expression;
    }

    private String replacePhysicsFunctions(String expression) {
        // Handle special physics functions
        if (expression.contains("photon_energy(")) {
            Pattern pattern = Pattern.compile("photon_energy\\((.*?)\\)");
            Matcher matcher = pattern.matcher(expression);
            StringBuffer sb = new StringBuffer();
            while (matcher.find()) {
                double wavelength = Double.parseDouble(matcher.group(1));
                double energy = PhysicsConstants.photonEnergy(wavelength);
                matcher.appendReplacement(sb, String.valueOf(energy));
            }
            matcher.appendTail(sb);
            expression = sb.toString();
        }

        if (expression.contains("de_broglie(")) {
            Pattern pattern = Pattern.compile("de_broglie\\((.*?),(.*?)\\)");
            Matcher matcher = pattern.matcher(expression);
            StringBuffer sb = new StringBuffer();
            while (matcher.find()) {
                double mass = Double.parseDouble(matcher.group(1));
                double velocity = Double.parseDouble(matcher.group(2));
                double wavelength = PhysicsConstants.deBroglieWavelength(mass, velocity);
                matcher.appendReplacement(sb, String.valueOf(wavelength));
            }
            matcher.appendTail(sb);
            expression = sb.toString();
        }

        return expression;
    }

    private double evaluateExpression(String expression) {
        // Simple expression evaluator using JavaScript engine
        try {
            javax.script.ScriptEngineManager manager = new javax.script.ScriptEngineManager();
            javax.script.ScriptEngine engine = manager.getEngineByName("JavaScript");
            Object result = engine.eval(expression);
            return ((Number) result).doubleValue();
        } catch (Exception e) {
            throw new RuntimeException("Cannot evaluate expression: " + expression);
        }
    }

    private String executePythonScript(String script) {
        try {
            // Create temporary Python file
            File tempFile = File.createTempFile("console_script", ".py");
            tempFile.deleteOnExit();

            // Add physics constants to script
            StringBuilder fullScript = new StringBuilder();
            fullScript.append("import math\n");
            fullScript.append("import numpy as np\n");
            fullScript.append("# Physics constants\n");
            fullScript.append("c = ").append(PhysicsConstants.c).append("\n");
            fullScript.append("h = ").append(PhysicsConstants.h).append("\n");
            fullScript.append("e = ").append(PhysicsConstants.e).append("\n");
            fullScript.append("m_e = ").append(PhysicsConstants.m_e).append("\n");
            fullScript.append("k_B = ").append(PhysicsConstants.k_B).append("\n");
            fullScript.append("G = ").append(PhysicsConstants.G).append("\n");
            fullScript.append("# Add more constants as needed\n\n");
            fullScript.append(script).append("\n");

            // Write script to file
            try (FileWriter writer = new FileWriter(tempFile)) {
                writer.write(fullScript.toString());
            }

            // Execute Python script
            ProcessBuilder pb = new ProcessBuilder("python3", tempFile.getAbsolutePath());
            pb.redirectErrorStream(true);
            Process process = pb.start();

            // Read output
            StringBuilder output = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    output.append(line).append("\n");
                }
            }

            int exitCode = process.waitFor();
            if (exitCode != 0) {
                return "Python execution failed with exit code: " + exitCode + "\n" + output.toString();
            }

            return output.toString();

        } catch (Exception e) {
            return "Python execution error: " + e.getMessage();
        }
    }

    private void handleKeyPress(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            // Previous command
            if (historyIndex > 0) {
                historyIndex--;
                inputField.setText(commandHistory.get(historyIndex));
            }
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            // Next command
            if (historyIndex < commandHistory.size() - 1) {
                historyIndex++;
                inputField.setText(commandHistory.get(historyIndex));
            } else {
                historyIndex = commandHistory.size();
                inputField.setText("");
            }
        }
    }

    private void appendToConsole(String text) {
        SwingUtilities.invokeLater(() -> {
            consoleArea.append(text);
            consoleArea.setCaretPosition(consoleArea.getDocument().getLength());
        });
    }

    private String formatResult(double result) {
        if (Math.abs(result) > 1e6 || (Math.abs(result) < 1e-3 && result != 0)) {
            return String.format("%.6e", result);
        } else {
            return String.format("%.6f", result);
        }
    }

    private String getVariables() {
        StringBuilder sb = new StringBuilder();
        sb.append("Current Variables:\n");
        for (Map.Entry<String, Double> entry : variables.entrySet()) {
            sb.append(String.format("  %s = %s\n", entry.getKey(), formatResult(entry.getValue())));
        }
        return sb.toString();
    }

    private String saveVariable(String varName) {
        if (variables.containsKey("ans")) {
            variables.put(varName, variables.get("ans"));
            return "Saved last result to variable: " + varName;
        } else {
            return "No result to save";
        }
    }

    private String getHelpText() {
        return "COMMANDS:\n" +
               "  help          - Show this help\n" +
               "  examples      - Show example calculations\n" +
               "  constants     - Show physics constants\n" +
               "  variables     - Show current variables\n" +
               "  clear         - Clear console\n" +
               "  python        - Switch to Python mode\n" +
               "  java          - Switch to Java mode\n" +
               "  exec <script> - Execute Python script\n" +
               "  save <name>   - Save last result to variable\n\n" +
               "EXPRESSIONS:\n" +
               "  x = 5 * c     - Variable assignment\n" +
               "  sin(pi/2)     - Trigonometric functions\n" +
               "  sqrt(16)      - Mathematical functions\n" +
               "  photon_energy(500e-9) - Physics functions\n\n" +
               "CONSTANTS:\n" +
               "  c, h, e, m_e, k_B, G, pi, E, etc.\n";
    }

    private String getExamplesText() {
        return "EXAMPLE CALCULATIONS:\n\n" +
               "BASIC MATH:\n" +
               ">>> 2 + 3 * 4\n" +
               ">>> (5 + 3) / 2\n" +
               ">>> 2^8\n" +
               ">>> sqrt(144)\n\n" +
               "TRIGONOMETRY:\n" +
               ">>> sin(pi/2)\n" +
               ">>> cos(0)\n" +
               ">>> tan(pi/4)\n\n" +
               "PHYSICS CONSTANTS:\n" +
               ">>> c                    (speed of light)\n" +
               ">>> h                    (Planck constant)\n" +
               ">>> e                    (elementary charge)\n" +
               ">>> m_e                  (electron mass)\n" +
               ">>> k_B                  (Boltzmann constant)\n\n" +
               "PHYSICS CALCULATIONS:\n" +
               ">>> E = m_e * c^2        (electron rest energy in Joules)\n" +
               ">>> E / eV               (convert to electron volts)\n" +
               ">>> photon_energy(500e-9) (green light photon energy)\n" +
               ">>> photon_energy(500e-9) / eV (in eV)\n" +
               ">>> de_broglie(m_e, 1e6) (electron de Broglie wavelength)\n\n" +
               "VARIABLES:\n" +
               ">>> wavelength = 500e-9\n" +
               ">>> energy = photon_energy(wavelength)\n" +
               ">>> energy_eV = energy / eV\n\n" +
               "PYTHON MODE:\n" +
               ">>> python\n" +
               ">>> import numpy as np\n" +
               ">>> result = np.sqrt(16)\n" +
               ">>> print(f'Square root of 16: {result}')\n" +
               ">>> print(f'Speed of light: {c:.0f} m/s')\n" +
               ">>> java                 (switch back to Java mode)\n\n";
    }

    private void setJavaMode() {
        pythonMode = false;
        appendToConsole("Switched to Java Expression Mode\n");
    }

    private void setPythonMode() {
        pythonMode = true;
        appendToConsole("Switched to Python Script Mode\n");
        appendToConsole("Physics constants are automatically imported\n");
    }

    private void setPhysicsMode() {
        setJavaMode();
        appendToConsole("Physics Calculator Mode - Use physics functions and constants\n");
    }

    // Menu actions
    private void loadScript() {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                File file = fileChooser.getSelectedFile();
                String content = new String(java.nio.file.Files.readAllBytes(file.toPath()));
                appendToConsole("Executing script: " + file.getName() + "\n");
                String result = executePythonScript(content);
                appendToConsole(result + "\n");
            } catch (Exception e) {
                appendToConsole("Error loading script: " + e.getMessage() + "\n");
            }
        }
    }

    private void saveOutput() {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                File file = fileChooser.getSelectedFile();
                try (FileWriter writer = new FileWriter(file)) {
                    writer.write(consoleArea.getText());
                }
                appendToConsole("Output saved to: " + file.getName() + "\n");
            } catch (Exception e) {
                appendToConsole("Error saving output: " + e.getMessage() + "\n");
            }
        }
    }

    private void clearConsole() {
        consoleArea.setText("");
        showWelcomeMessage();
    }

    private void showCommands() {
        appendToConsole(getHelpText() + "\n");
    }

    private void showConstants() {
        appendToConsole(PhysicsConstants.getAllConstants() + "\n");
    }

    private void showExamples() {
        appendToConsole(getExamplesText());
    }

    private void runPhysicsExamples() {
        appendToConsole("=== RUNNING PHYSICS EXAMPLES ===\n\n");
        
        // Run some example calculations automatically
        String[] examples = {
            "c",
            "h",
            "m_e * c^2 / eV",
            "photon_energy(500e-9) / eV",
            "sqrt(16) + sin(pi/2)",
            "de_broglie(m_e, 1e6)"
        };
        
        String[] descriptions = {
            "Speed of light (m/s)",
            "Planck constant (J⋅s)",
            "Electron rest energy (MeV)",
            "Green photon energy (eV)",
            "Math example",
            "Electron de Broglie wavelength (m)"
        };
        
        for (int i = 0; i < examples.length; i++) {
            appendToConsole(">>> " + examples[i] + " // " + descriptions[i] + "\n");
            try {
                String result = evaluateJavaExpression(examples[i]);
                appendToConsole(result + "\n\n");
            } catch (Exception e) {
                appendToConsole("ERROR: " + e.getMessage() + "\n\n");
            }
        }
        
        appendToConsole("=== EXAMPLES COMPLETE ===\n");
        appendToConsole("Try typing your own calculations!\n\n");
    }

    private void showAbout() {
        String about = "Scientific Programmable Console v1.0\n\n" +
                "A powerful calculator for physics and engineering\n" +
                "with support for:\n" +
                "• Mathematical expressions\n" +
                "• Physics constants library\n" +
                "• Python script execution\n" +
                "• Variable storage\n" +
                "• Scientific functions\n\n" +
                "Built with Java Swing\n";
        
        JOptionPane.showMessageDialog(this, about, "About", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            new ProgrammableConsole().setVisible(true);
        });
    }
}