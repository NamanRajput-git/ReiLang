package rei;

import java.util.List;

public abstract class Stmt {
    public interface Visitor<R> {
        R visitPrintStmt(Print stmt);
        R visitAssignStmt(Assign stmt);
        R visitIfStmt(If stmt);
        R visitBlockStmt(Block stmt);
    }

    public abstract <R> R accept(Visitor<R> visitor);

    public static class Print extends Stmt {
        public final Expr expression;
        public Print(Expr expression) { this.expression = expression; }
        public <R> R accept(Visitor<R> visitor) { return visitor.visitPrintStmt(this); }
    }

    public static class Assign extends Stmt {
        public final Token name;
        public final Expr value;
        public Assign(Token name, Expr value) { this.name = name; this.value = value; }
        public <R> R accept(Visitor<R> visitor) { return visitor.visitAssignStmt(this); }
    }

    public static class If extends Stmt {
        public final Expr condition;
        public final Stmt thenBranch;
        public final Stmt elseBranch;
        public If(Expr condition, Stmt thenBranch, Stmt elseBranch) {
            this.condition = condition;
            this.thenBranch = thenBranch;
            this.elseBranch = elseBranch;
        }
        public <R> R accept(Visitor<R> visitor) { return visitor.visitIfStmt(this); }
    }

    public static class Block extends Stmt {
        public final java.util.List<Stmt> statements;
        public Block(java.util.List<Stmt> statements) { this.statements = statements; }
        public <R> R accept(Visitor<R> visitor) { return visitor.visitBlockStmt(this); }
    }
}