import java.io.IOException;

import validator.CompileResult;
import validator.Compiler;
import validator.ExecuteResult;
import validator.Executor;

public class Application {
    public static void main(String[] args) throws IOException {
        CompileResult result = Compiler.compileString(
            "public class %s {" + "\n" +
            "   public static void main(String[] args) {" + "\n" +
            "       System.out.println(\"Hello World! \");" + "\n" +
            "   }" + "\n" +
            "}"
        );
        System.out.println(result);
        System.out.println(result.getCompiledFiles().get(0));

        ExecuteResult executeResult = Executor.executeFile(result.getCompiledFiles().get(0));
        System.out.println(executeResult);
    }
}
