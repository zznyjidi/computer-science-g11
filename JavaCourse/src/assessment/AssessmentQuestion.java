package assessment;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import validator.Executor;
import validator.Executor.OutputLine;

public class AssessmentQuestion {
    String question;
    File templateFile;
    List<Executor.OutputLine> expectedOutput;

    public AssessmentQuestion(String question, File templateFile, OutputLine[] expectedOutput) {
        this.question = question;
        this.templateFile = templateFile;
        this.expectedOutput = Arrays.asList(expectedOutput);
    }

    public AssessmentQuestion(String question, File templateFile, List<OutputLine> expectedOutput) {
        this.question = question;
        this.templateFile = templateFile;
        this.expectedOutput = expectedOutput;
    }
    public String getQuestion() {
        return question;
    }
    public File getTemplateFile() {
        return templateFile;
    }
    public List<Executor.OutputLine> getExpectedOutput() {
        return expectedOutput;
    }
    @Override
    public String toString() {
        return "AssessmentQuestion [question=" + question + ", templateFile=" + templateFile + ", expectedOutput="
                + expectedOutput + "]";
    }

    public boolean validateOutput(List<Executor.OutputLine> programOutput) {
        if (programOutput.size() != expectedOutput.size())
            return false;
        for (int i = 0; i < programOutput.size(); i++) {
            if (!programOutput.get(i).equals(expectedOutput.get(i)))
                return false;
        }
        return true;
    }
}
