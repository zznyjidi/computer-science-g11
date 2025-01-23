package assessment;

import java.io.File;

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
            )
        };
    }
}
