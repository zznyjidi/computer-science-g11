package interfaces;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

import assessment.AssessmentQuestion;
import validator.CompileResult;
import validator.Compiler;
import validator.ExecuteResult;
import validator.Executor;

public class AssessmentQuestionPanel extends JPanel implements ActionListener, DocumentListener {
    JLabel questionLabel;
    JTextArea editorPane;
    JTextPane outputTextPane;
    JPanel buttonPanel;
    List<JButton> buttons = new ArrayList<>();

    String lastRunSource;

    public AssessmentQuestionPanel(AssessmentQuestion question) {
        // Set Layout Manager
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        // Question
        questionLabel = new JLabel(question.getQuestion());
        add(questionLabel);
        add(Box.createRigidArea(new Dimension(0, 5)));

        // Program Source
        editorPane = new JTextArea();
        try {
            editorPane.setText(Files.readString(Paths.get(question.getTemplateFile().toURI())));
        } catch (IOException e) {
            e.printStackTrace();
        }
        editorPane.getDocument().addDocumentListener(this);
        add(editorPane);
        add(Box.createRigidArea(new Dimension(0, 5)));

        // Program Output
        outputTextPane = new JTextPane();
        outputTextPane.setEditable(false);
        add(outputTextPane);
        add(Box.createRigidArea(new Dimension(0, 5)));

        // Buttons
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
        buttonPanel.add(Box.createHorizontalGlue());
        for (int i = 0; i < 3; i++) {
            JButton button = new JButton(
                switch (i) {
                    case 0 -> "Back";
                    case 1 -> "Validate";
                    case 2 -> "Next";
                    default -> "INVALID";
                }
            );
            button.addActionListener(this);
            buttons.add(button);
            buttonPanel.add(button);
            buttonPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        }
        buttonPanel.add(Box.createHorizontalGlue());
        add(buttonPanel);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        switch (buttons.indexOf(event.getSource())) {
            // Validate button
            case 1 -> {
                // Check if Source Code Changed
                if (lastRunSource == editorPane.getText())
                    return;
                // Clear Output
                outputTextPane.setText("");
                // Compile Source Code
                CompileResult compileResult = Compiler.compileString(editorPane.getText());
                if (compileResult.isSuccess()) {
                    // Run and Output if Successfully compiled
                    ExecuteResult executeResult = Executor.executeFile(compileResult.getCompiledFiles().get(0));
                    outputTextPane.setEditable(true);
                    for (Executor.OutputLine outputLine : executeResult.getOutput()) {
                        outputTextPane.setCaretPosition(outputTextPane.getDocument().getLength());
                        outputTextPane.replaceSelection(outputLine.getText() + "\n");
                    }
                    outputTextPane.setEditable(false);
                } else {
                    // Output Error Message if compile failed
                    outputTextPane.setEditable(true);
                    for (Diagnostic<? extends JavaFileObject> diagnostic : compileResult.getDiagnostic()) {
                        outputTextPane.setCaretPosition(outputTextPane.getDocument().getLength());
                        outputTextPane.replaceSelection(diagnostic.toString() + "\n");
                    }
                    outputTextPane.setEditable(false);
                }
            }
        }
    }

    @Override
    public void insertUpdate(DocumentEvent event) {
        if (event.getDocument().equals(editorPane.getDocument())) {
            // Replace tab with 4 spaces to match the template
            if (editorPane.getText().contains("\t")) {
                SwingUtilities.invokeLater(
                    () -> {
                        // Replace tab with space
                        editorPane.setText(editorPane.getText().replaceAll("\t", "    "));
                        // Set New Cursor Position
                        editorPane.setCaretPosition(event.getOffset() + 3);
                    }
                );
            }
        }
    }

    @Override
    public void removeUpdate(DocumentEvent event) {
    }

    @Override
    public void changedUpdate(DocumentEvent event) {
    }
}
