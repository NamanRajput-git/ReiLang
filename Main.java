package rei;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        if(args.length ==0){
            System.out.println("Usage: java Main <input file>");
            return;
        }
        // Read the input file
        String inputFile = args[0];
        String source = "";
        try {
            source = new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(inputFile)));
        } catch (java.io.IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return;
        }
        ReiLexer lexer = new ReiLexer(source);
        List<Token> tokens = lexer.tokenize();
        ReiParser parser = new ReiParser(tokens);
        List<Stmt> statements = parser.parse();
        ReiInterpreter interpreter = new ReiInterpreter();
        for (Stmt stmt : statements) {
            interpreter.execute(stmt);
        }
    }
}