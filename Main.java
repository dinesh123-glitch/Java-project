import java.awt.*;
import java.awt.event.*;

public class ScientificCalculator extends Frame implements ActionListener {
    // Declare components
    TextField display;
    Button[] numberButtons;
    Button[] functionButtons;
    Button addButton, subButton, mulButton, divButton, equalsButton, clearButton;
    Button sqrtButton, sinButton, cosButton, tanButton, logButton, expButton;
    String displayText = "";

    // Constructor to setup the calculator
    public ScientificCalculator() {
        // Set up frame
        setTitle("Scientific Calculator");
        setSize(400, 600);
        setLayout(null);

        // Create text field for display
        display = new TextField();
        display.setBounds(20, 50, 350, 40);
        display.setFont(new Font("Arial", Font.PLAIN, 20));
        display.setEditable(false);
        add(display);

        // Create number buttons
        numberButtons = new Button[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new Button(String.valueOf(i));
            numberButtons[i].addActionListener(this);
        }

        // Create function buttons (+, -, *, /, etc.)
        addButton = new Button("+");
        subButton = new Button("-");
        mulButton = new Button("*");
        divButton = new Button("/");
        equalsButton = new Button("=");
        clearButton = new Button("C");

        sqrtButton = new Button("√");
        sinButton = new Button("sin");
        cosButton = new Button("cos");
        tanButton = new Button("tan");
        logButton = new Button("log");
        expButton = new Button("exp");

        functionButtons = new Button[]{addButton, subButton, mulButton, divButton, equalsButton, clearButton,
                                       sqrtButton, sinButton, cosButton, tanButton, logButton, expButton};

        // Set bounds for the number buttons
        int x = 20, y = 100;
        for (int i = 0; i < 10; i++) {
            numberButtons[i].setBounds(x, y, 60, 40);
            add(numberButtons[i]);
            x += 70;
            if ((i + 1) % 3 == 0) {
                x = 20;
                y += 50;
            }
        }

        // Set bounds for the function buttons
        x = 230;
        y = 100;
        for (Button btn : functionButtons) {
            btn.setBounds(x, y, 60, 40);
            add(btn);
            y += 50;
        }

        // Add action listeners for function buttons
        for (Button btn : functionButtons) {
            btn.addActionListener(this);
        }

        // Set frame visibility and closing behavior
        setVisible(true);
        setResizable(false);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }

    // Handle button clicks
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.charAt(0) >= '0' && command.charAt(0) <= '9') {
            // Append number to the display
            displayText += command;
        } else if (command.equals("C")) {
            // Clear display
            displayText = "";
        } else if (command.equals("=")) {
            try {
                // Evaluate the expression (basic evaluation for demonstration)
                displayText = evaluateExpression(displayText);
            } catch (Exception ex) {
                displayText = "Error";
            }
        } else {
            // Append operator or function to the display
            displayText += " " + command + " ";
        }

        // Update display
        display.setText(displayText);
    }

    // Simple expression evaluator (only handles basic operations)
    private String evaluateExpression(String expression) {
        // Add handling of scientific operations like sqrt, sin, etc.
        try {
            // Handle sqrt (square root)
            if (expression.contains("√")) {
                expression = expression.replace("√", "Math.sqrt");
            }
            // Handle sin, cos, tan, etc.
            expression = expression.replace("sin", "Math.sin");
            expression = expression.replace("cos", "Math.cos");
            expression = expression.replace("tan", "Math.tan");
            expression = expression.replace("log", "Math.log");
            expression = expression.replace("exp", "Math.exp");

            // Simple evaluation using script engine for advanced expressions
            ScriptEngineManager mgr = new ScriptEngineManager();
            ScriptEngine engine = mgr.getEngineByName("JavaScript");
            return engine.eval(expression).toString();
        } catch (Exception e) {
            return "Error";
        }
    }

    public static void main(String[] args) {
        new ScientificCalculator();
    }
}
