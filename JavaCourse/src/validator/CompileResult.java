package validator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

public class CompileResult {
    private boolean success;
    private String stdErrors;
    private List<Diagnostic<? extends JavaFileObject>> diagnostic;
    private List<File> compiledFiles = new ArrayList<>();

    public CompileResult(boolean success, String stdErrors, List<Diagnostic<? extends JavaFileObject>> diagnostic, List<File> compiledFiles) {
        this.success = success;
        this.stdErrors = stdErrors;
        this.diagnostic = diagnostic;
        this.compiledFiles = compiledFiles;
    }

    public CompileResult(String stdErrors, List<Diagnostic<? extends JavaFileObject>> diagnostic, File sourceFile) {
        this.stdErrors = stdErrors;
        this.diagnostic = diagnostic;
        this.success = diagnostic.isEmpty();
        if (success) {
            compiledFiles.add(
                new File(sourceFile.getAbsolutePath().replaceFirst("[.][^.]+$", "") + ".class")
            );
        }
    }

    public boolean isSuccess() {
        return success;
    }

    public String getStdErrors() {
        return stdErrors;
    }

    public List<Diagnostic<? extends JavaFileObject>> getDiagnostic() {
        return diagnostic;
    }

    public List<File> getCompiledFiles() {
        return compiledFiles;
    }

    @Override
    public String toString() {
        return "CompileResult [success=" + success + ", stdErrors=" + stdErrors + ", diagnostic=" + diagnostic + "]";
    }
}
