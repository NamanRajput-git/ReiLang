package rei;

import java.util.*;

public class ReiLexer {
    private final String source;
    private final List<Token> tokens = new ArrayList<>();
    private int start = 0, current = 0;

    public ReiLexer(String source) {
        this.source = source + "\n";
    }

    public List<Token> tokenize() {
        while (!isAtEnd()) {
            start = current;
            scanToken();
        }
        tokens.add(new Token(Token.Type.EOF, ""));
        return tokens;
    }

    private void scanToken() {
        char c = advance();
        switch (c) {
            case '+': addToken(Token.Type.PLUS); break;
            case '-': addToken(Token.Type.MINUS); break;
            case '*': addToken(Token.Type.STAR); break;
            case '/': addToken(Token.Type.SLASH); break;
            case '=': addToken(Token.Type.EQUAL); break;
            case '>': addToken(Token.Type.GT); break;
            case '<': addToken(Token.Type.LT); break;
            case '\n': addToken(Token.Type.NEWLINE); break;
            case '{': addToken(Token.Type.LBRACE); break;
            case '}': addToken(Token.Type.RBRACE); break;
            case ' ':
            case '\r':
            case '\t':
                break;
            default:
                if (isDigit(c)) {
                    number();
                } else if (isAlpha(c)) {
                    identifier();
                }
        }
    }

    private void number() {
        while (isDigit(peek())) advance();
        String text = source.substring(start, current);
        addToken(Token.Type.NUMBER, Integer.parseInt(text));
    }

    private void identifier() {
        while (isAlphaNumeric(peek())) advance();
        String text = source.substring(start, current);
        switch (text) {
            case "if": addToken(Token.Type.IF); break;
            case "else": addToken(Token.Type.ELSE); break;
            case "print": addToken(Token.Type.PRINT); break;
            default: addToken(Token.Type.IDENTIFIER);
        }
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    private boolean isAlpha(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    private boolean isAlphaNumeric(char c) {
        return isAlpha(c) || isDigit(c);
    }

    private char advance() {
        return source.charAt(current++);
    }

    private char peek() {
        if (isAtEnd()) return '\0';
        return source.charAt(current);
    }

    private boolean isAtEnd() {
        return current >= source.length();
    }

    private void addToken(Token.Type type) {
        addToken(type, null);
    }

    private void addToken(Token.Type type, Integer literal) {
        String text = source.substring(start, current);
        tokens.add(new Token(type, text, literal));
    }
}