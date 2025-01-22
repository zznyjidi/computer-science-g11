package validator;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

public class Compiler {
    static JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

    /**
     * Compile java source code
     * @param sourceFile java source file
     * @return CompileResult object
     */
    public static CompileResult compileFile(File sourceFile) {
        // Compile source file
        // https://docs.oracle.com/en/java/javase/21/docs/api/java.compiler/javax/tools/JavaCompiler.html
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnostics, null, null);
        Iterable<? extends JavaFileObject> sources = fileManager.getJavaFileObjects(sourceFile);
        // Writer to String 
        // https://stackoverflow.com/questions/19181206/how-to-convert-writer-to-string
        StringWriter stdError = new StringWriter();
        compiler.getTask(stdError, fileManager, diagnostics, null, null, sources).call();

        return new CompileResult(stdError.toString(), diagnostics.getDiagnostics(), sourceFile);
    }

    public static CompileResult compileString(String source) {
        File sourceFile = null;
        try {
            // Create Temp File
            // https://stackoverflow.com/questions/26860167/what-is-a-safe-way-to-create-a-temp-file-in-java
            sourceFile = File.createTempFile("Source", ".java");
            PrintWriter sourceWriter = new PrintWriter(sourceFile);
            sourceWriter.println(String.format(
                source, 
                // Get Filename without extension
                // https://stackoverflow.com/questions/924394/how-to-get-the-filename-without-the-extension-in-java
                sourceFile.getName().replaceFirst("[.][^.]+$", ""))
            );
            sourceWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return compileFile(sourceFile);
    }
}
