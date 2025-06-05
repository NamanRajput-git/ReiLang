package rei;

public class Token {
    public enum Type {
        IDENTIFIER, NUMBER,
        EQUAL, PLUS, MINUS, STAR, SLASH,
        GT, LT, IF, ELSE, PRINT,
        LBRACE, RBRACE,
        NEWLINE, EOF
    }

    public final Type type;
    public final String lexeme;
    public final Integer literal;

    public Token(Type type, String lexeme) {
        this(type, lexeme, null);
    }

    public Token(Type type, String lexeme, Integer literal) {
        this.type = type;
        this.lexeme = lexeme;
        this.literal = literal;
    }

    public String toString() {
        return type + "('" + lexeme + "')";
    }
}