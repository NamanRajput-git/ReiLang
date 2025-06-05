package rei;

public abstract class Expr {
    public interface Visitor<R> {
        R visitBinaryExpr(Binary expr);
        R visitLiteralExpr(Literal expr);
        R visitVariableExpr(Variable expr);
        R visitUnaryExpr(Unary expr);
    }

    public abstract <R> R accept(Visitor<R> visitor);

    public static class Binary extends Expr {
        public final Expr left;
        public final String operator;
        public final Expr right;

        public Binary(Expr left, String operator, Expr right) {
            this.left = left;
            this.operator = operator;
            this.right = right;
        }

        public <R> R accept(Visitor<R> visitor) {
            return visitor.visitBinaryExpr(this);
        }
    }

    public static class Literal extends Expr {
        public final int value;

        public Literal(int value) {
            this.value = value;
        }

        public <R> R accept(Visitor<R> visitor) {
            return visitor.visitLiteralExpr(this);
        }
    }

    public static class Variable extends Expr {
        public final Token name;

        public Variable(Token name) {
            this.name = name;
        }

        public <R> R accept(Visitor<R> visitor) {
            return visitor.visitVariableExpr(this);
        }
    }

    public static class Unary extends Expr {
        public final Token operator;
        public final Expr right;

        public Unary(Token operator, Expr right) {
            this.operator = operator;
            this.right = right;
        }

        public <R> R accept(Visitor<R> visitor) {
            return visitor.visitUnaryExpr(this);
        }
    }
}