package rei;

import java.util.*;

public class ReiParser {
    private final List<Token> tokens;
    private int current = 0;

    public ReiParser(List<Token> tokens) {
        this.tokens = tokens;
    }

    public List<Stmt> parse() {
        List<Stmt> statements = new ArrayList<>();
        while (!isAtEnd()) {
            if (match(Token.Type.NEWLINE)) continue;
            statements.add(statement());
        }
        return statements;
    }

    private Stmt statement() {
        if (match(Token.Type.IF)) return ifStatement();
        if (match(Token.Type.PRINT)) return printStatement();
        if (check(Token.Type.IDENTIFIER) && checkNext(Token.Type.EQUAL)) return assignment();
        throw error(peek(), "Expected statement.");
    }

    private Stmt assignment() {
        Token name = consume(Token.Type.IDENTIFIER, "Expected variable name.");
        consume(Token.Type.EQUAL, "Expected '='.");
        Expr value = expression();
        match(Token.Type.NEWLINE);
        return new Stmt.Assign(name, value);
    }

    private Stmt printStatement() {
        Expr value = expression();
        match(Token.Type.NEWLINE);
        return new Stmt.Print(value);
    }

    private Stmt ifStatement() {
        Expr condition = expression();
        Stmt thenBranch = block();
        Stmt elseBranch = null;
        if (match(Token.Type.ELSE)) {
            elseBranch = block();
        }
        return new Stmt.If(condition, thenBranch, elseBranch);
    }

    private Stmt block() {
        consume(Token.Type.LBRACE, "Expected '{'.");
        List<Stmt> statements = new ArrayList<>();
        while (!check(Token.Type.RBRACE) && !isAtEnd()) {
            if (match(Token.Type.NEWLINE)) continue;
            statements.add(statement());
        }
        consume(Token.Type.RBRACE, "Expected '}'.");
        match(Token.Type.NEWLINE);
        return new Stmt.Block(statements);
    }

    private Expr expression() { return equality(); }
    private Expr equality() {
        Expr expr = comparison();
        return expr;
    }
    private Expr comparison() {
        Expr expr = term();
        while (match(Token.Type.GT, Token.Type.LT)) {
            Token operator = previous();
            Expr right = term();
            expr = new Expr.Binary(expr, operator.lexeme, right);
        }
        return expr;
    }
    private Expr term() {
        Expr expr = factor();
        while (match(Token.Type.PLUS, Token.Type.MINUS)) {
            Token operator = previous();
            Expr right = factor();
            expr = new Expr.Binary(expr, operator.lexeme, right);
        }
        return expr;
    }
    private Expr factor() {
        Expr expr = unary();
        while (match(Token.Type.STAR, Token.Type.SLASH)) {
            Token operator = previous();
            Expr right = unary();
            expr = new Expr.Binary(expr, operator.lexeme, right);
        }
        return expr;
    }
    private Expr unary() {
        if (match(Token.Type.MINUS)) {
            Token operator = previous();
            Expr right = unary();
            return new Expr.Unary(operator, right);
        }
        return primary();
    }
    private Expr primary() {
        if (match(Token.Type.NUMBER)) {
            return new Expr.Literal(previous().literal);
        }
        if (match(Token.Type.IDENTIFIER)) {
            return new Expr.Variable(previous());
        }
        throw error(peek(), "Expected expression.");
    }

    private boolean match(Token.Type... types) {
        for (Token.Type type : types) {
            if (check(type)) {
                advance();
                return true;
            }
        }
        return false;
    }

    private boolean check(Token.Type type) {
        if (isAtEnd()) return false;
        return peek().type == type;
    }

    private boolean checkNext(Token.Type type) {
        if (current + 1 >= tokens.size()) return false;
        return tokens.get(current + 1).type == type;
    }

    private Token advance() {
        if (!isAtEnd()) current++;
        return previous();
    }

    private boolean isAtEnd() {
        return peek().type == Token.Type.EOF;
    }

    private Token peek() {
        return tokens.get(current);
    }

    private Token previous() {
        return tokens.get(current - 1);
    }

    private Token consume(Token.Type type, String message) {
        if (check(type)) return advance();
        throw error(peek(), message);
    }

    private RuntimeException error(Token token, String message) {
        return new RuntimeException("Parse error at " + token.lexeme + ": " + message);
    }
}