package interfaces;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.HighlightPainter;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

import assessment.AssessmentQuestion;
import assessment.QuestionList;
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

    String lastRunSource = "";
    boolean lastRunSuccess = false;
    AssessmentQuestion questionObject;

    static Color stdOutColor = Color.BLACK;
    static Color stdErrColor = Color.RED;
    static Color promptColor = Color.BLUE;

    public AssessmentQuestionPanel(AssessmentQuestion question) {
        questionObject = question;
        // Set Layout Manager
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        // Question
        questionLabel = new JLabel(question.getQuestion(), SwingConstants.CENTER);
        add(questionLabel);
        add(Box.createRigidArea(new Dimension(0, 5)));

        // Program Source
        editorPane = new JTextArea();
        try {
            // Read File to String
            // https://stackoverflow.com/questions/326390/how-do-i-create-a-java-string-from-the-contents-of-a-file
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

    public boolean isLastRunSuccess() {
        return lastRunSuccess;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        switch (buttons.indexOf(event.getSource())) {
            // Back Button
            case 0 -> QuestionList.pageManager.lastPage();
            // Next Button
            case 2 -> QuestionList.pageManager.nextPage();
            // Validate button
            case 1 -> {
                // Check if Source Code Changed
                if (lastRunSource == editorPane.getText())
                    return;
                // Clear Output
                outputTextPane.setText("");
                // Setup Highlight and Color
                // https://stackoverflow.com/questions/20341719/how-to-highlight-a-single-word-in-a-jtextarea
                // https://stackoverflow.com/questions/10191723/highlight-one-specific-row-line-in-jtextarea
                // https://stackoverflow.com/questions/18380350/removing-highlight-from-specific-word-java
                // https://stackoverflow.com/questions/9650992/how-to-change-text-color-in-the-jtextarea
                Highlighter highlighter = editorPane.getHighlighter();
                highlighter.removeAllHighlights();
                // Compile Source Code
                lastRunSource = editorPane.getText();
                CompileResult compileResult = Compiler.compileString(lastRunSource);
                if (compileResult.isSuccess()) {
                    // Run and Output if Successfully compiled
                    ExecuteResult executeResult = Executor.executeFile(compileResult.getCompiledFiles().get(0));
                    outputTextPane.setEditable(true);
                    for (Executor.OutputLine outputLine : executeResult.getOutput()) {
                        addToOutputTextPane(
                            outputLine.getText() + "\n", 
                            outputTextPane.getDocument().getLength(), 
                            outputLine.getStream() == 0 ? stdOutColor : stdErrColor
                        );
                    }

                    if (questionObject.validateOutput(executeResult.getOutput())) {
                        addToOutputTextPane("Valid Answer, Congratulation! \n\n", 0, promptColor);
                        lastRunSuccess = true;
                    } else {
                        addToOutputTextPane("Invalid Answer, Try Again! \n\n", 0, promptColor);
                        lastRunSuccess = false;
                    }

                    outputTextPane.setEditable(false);
                } else {
                    // Output Error Message if compile failed
                    lastRunSuccess = false;
                    outputTextPane.setEditable(true);
                    for (Diagnostic<? extends JavaFileObject> diagnostic : compileResult.getDiagnostic()) {
                        int lineNumber = (int) diagnostic.getLineNumber();
                        try {
                            int highlightStartIndex = editorPane.getLineStartOffset(lineNumber - 1);
                            int highlightEndIndex = editorPane.getLineEndOffset(lineNumber - 1);

                            HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(Color.RED);
                            highlighter.addHighlight(highlightStartIndex, highlightEndIndex, painter);
                        } catch (BadLocationException e) {
                            e.printStackTrace();
                        }

                        addToOutputTextPane(
                            diagnostic.toString() + "\n",  
                            outputTextPane.getDocument().getLength(), 
                            stdErrColor
                        );
                    }
                    addToOutputTextPane("Failed to Compile the Code! \n\n", 0, promptColor);
                    lastRunSuccess = false;
                    outputTextPane.setEditable(false);
                }
                revalidate();
                repaint();
            }
        }
    }

    private void addToOutputTextPane(String text, int position, Color color) {
        StyleContext styleContext = StyleContext.getDefaultStyleContext();

        AttributeSet attributeSet = styleContext.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, color);
        attributeSet = styleContext.addAttribute(attributeSet, StyleConstants.FontFamily, "Consolas");
        attributeSet = styleContext.addAttribute(attributeSet, StyleConstants.Alignment, StyleConstants.ALIGN_LEFT);

        outputTextPane.setCaretPosition(position);
        outputTextPane.setCharacterAttributes(attributeSet, false);
        outputTextPane.replaceSelection(text);
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
                        editorPane.setCaretPosition(event.getOffset() + 4);
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
