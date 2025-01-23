package validator;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Executor {

    public static ExecuteResult executeFile(File classFile) {
        int processExitValue = -1;
        List<OutputLine> processOutput = new ArrayList<>();
        try {
            String[] processCommand = new String[] {
                "java", "-cp",
                classFile.getParent(), 
                classFile.getName().replaceFirst("[.][^.]+$", "")
            };

            // Run Java File
            // https://stackoverflow.com/questions/10249637/running-a-class-file-from-java-code
            Process validatorProcess = Runtime.getRuntime().exec(processCommand);

            BufferedReader stdOut = new BufferedReader(new InputStreamReader(validatorProcess.getInputStream()));
            BufferedReader stdErr = new BufferedReader(new InputStreamReader(validatorProcess.getErrorStream()));
            String stdOutLine = null;
            String stdErrLine = null;

            while ((stdErrLine = stdErr.readLine()) != null || (stdOutLine = stdOut.readLine()) != null) {
                if (stdErrLine != null) processOutput.add(new OutputLine(1, stdErrLine));
                if (stdOutLine != null) processOutput.add(new OutputLine(0, stdOutLine));
            }
            processExitValue = validatorProcess.exitValue();

            stdOut.close();
            stdErr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ExecuteResult(processExitValue, processOutput);
    }

    public static class OutputLine {
        private int stream;
        private String text;

        public OutputLine(int stream, String text) {
            this.stream = stream;
            this.text = text;
        }

        public int getStream() {
            return stream;
        }
        public String getText() {
            return text;
        }

        @Override
        public String toString() {
            return "OutputLine [stream=" + stream + ", text=" + text + "]";
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + stream;
            result = prime * result + ((text == null) ? 0 : text.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            OutputLine other = (OutputLine) obj;
            if (stream != other.stream)
                return false;
            if (text == null) {
                if (other.text != null)
                    return false;
            } else if (!text.equals(other.text))
                return false;
            return true;
        }
    }
}
