package validator;

import java.util.List;

public class ExecuteResult {
    int statusCode;
    List<Executor.OutputLine> output;

    public ExecuteResult(int statusCode, List<Executor.OutputLine> output) {
        this.statusCode = statusCode;
        this.output = output;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public List<Executor.OutputLine> getOutput() {
        return output;
    }

    @Override
    public String toString() {
        return "ExecutorResult [statusCode=" + statusCode + ", output=" + output + "]";
    }
}
