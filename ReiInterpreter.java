package rei;

import java.util.HashMap;
import java.util.Map;

public class ReiInterpreter implements Expr.Visitor<Integer>, Stmt.Visitor<Void> {
    private final Map<String, Integer> environment = new HashMap<>();

    public void execute(Stmt stmt) {
        stmt.accept(this);
    }

    @Override
    public Void visitPrintStmt(Stmt.Print stmt) {
        int value = evaluate(stmt.expression);
        System.out.println(value);
        return null;
    }

    @Override
    public Void visitAssignStmt(Stmt.Assign stmt) {
        int value = evaluate(stmt.value);
        environment.put(stmt.name.lexeme, value);
        return null;
    }

    @Override
    public Void visitIfStmt(Stmt.If stmt) {
        int condition = evaluate(stmt.condition);
        if (condition != 0) {
            execute(stmt.thenBranch);
        } else if (stmt.elseBranch != null) {
            execute(stmt.elseBranch);
        }
        return null;
    }

    @Override
    public Void visitBlockStmt(Stmt.Block stmt) {
        for (Stmt s : stmt.statements) {
            execute(s);
        }
        return null;
    }

    private int evaluate(Expr expr) {
        return expr.accept(this);
    }

    @Override
    public Integer visitBinaryExpr(Expr.Binary expr) {
        int left = evaluate(expr.left);
        int right = evaluate(expr.right);
        return switch (expr.operator) {
            case "+" -> left + right;
            case "-" -> left - right;
            case "*" -> left * right;
            case "/" -> left / right;
            case ">" -> (left > right) ? 1 : 0;
            case "<" -> (left < right) ? 1 : 0;
            default -> 0;
        };
    }

    @Override
    public Integer visitLiteralExpr(Expr.Literal expr) {
        return expr.value;
    }

    @Override
    public Integer visitVariableExpr(Expr.Variable expr) {
        return environment.getOrDefault(expr.name.lexeme, 0);
    }

    @Override
    public Integer visitUnaryExpr(Expr.Unary expr) {
        int right = evaluate(expr.right);
        return switch (expr.operator.type) {
            case MINUS -> -right;
            default -> right;
        };
    }
}