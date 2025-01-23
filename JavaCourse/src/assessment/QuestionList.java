package assessment;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import interfaces.AssessmentQuestionPanel;
import interfaces.PagedPanelManager;
import validator.Executor.OutputLine;;

public class QuestionList {
    public static File genericTemplate = new File("assets/templates/template-generic.java");
    public static AssessmentQuestion[] questions;
    static {
        OutputLine[] from0To9 = new OutputLine[10];
        for (int i = 0; i < from0To9.length; i++) {
            from0To9[i] = new OutputLine(0, String.valueOf(i));
        }

        questions = new AssessmentQuestion[] {
            new AssessmentQuestion(
                "Write a For loop that print 0 to 9. (Each Number on a new line)", 
                genericTemplate, from0To9
            ), 
            new AssessmentQuestion(
                "Write a For loop that calculate the sum from 0 to 9. (Print Only Once)", 
                genericTemplate, new OutputLine[] {new OutputLine(0, "45")}
            )
        };
    }

    public static PagedPanelManager<AssessmentQuestionPanel> pageManager;

    public static PagedPanelManager<AssessmentQuestionPanel> createPagedManager() {
        List<AssessmentQuestionPanel> panelList = 
            Arrays.stream(questions)
            .map(question -> new AssessmentQuestionPanel(question))
            .toList();
        return new PagedPanelManager<>(panelList);
    }
}
