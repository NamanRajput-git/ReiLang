# ReiLang

ReiLang is a simple, Python-inspired interpreted programming language built in Java. It supports basic constructs such as variable assignment, arithmetic operations, print statements, and conditional logic.

---

## ✨ Features

- Variable Assignment
- Arithmetic Operations (`+`, `-`, `*`, `/`)
- Print Statements
- If-Else Conditionals

---
## 🚀 Getting Started

### 1. Clone the Repo

```bash
git clone https://github.com/NamanRajput-git/ReiLang.git
cd rei_lang
```

### 2. Build and Run

Ensure you have **Java 11+**:

```bash
javac -d out src/rei/*.java
java -cp out rei.Main
```
## ✍ Writing ReiLang Code in Java

You can write ReiLang programs using:

```java
String source = String.join("\n",
    "x = 10",
    "y = 5",
    "if x > y",
    "print x",
    "else",
    "print y"
);
```

Or, use Java 15+ text blocks:

```java
String source = """
x = 10
y = 5
if x > y
print x
else
print y
""";
```

Then interpret:

```java
ReiLexer lexer = new ReiLexer(source);
List<Token> tokens = lexer.tokenize();
ReiParser parser = new ReiParser(tokens);
List<Stmt> stmts = parser.parse();
ReiInterpreter interpreter = new ReiInterpreter();
for (Stmt stmt : stmts) {
    interpreter.execute(stmt);
}
```


## 🧠 Syntax & Examples

### ✅ Variable Assignment
```rei
x = 10
y = x + 5
```

### 🧮 Arithmetic
```rei
a = 5 + 3
b = 10 - 2
c = 4 * 6
d = 20 / 4
```

### 📤 Print Statements
```rei
x = 10
print x
print 42
print x + 5
```

### 🔁 Conditional Logic
#### If Statement
```rei
x = 10
y = 5
if x > y
print x
```

#### If-Else Statement
```rei
x = 3
y = 5
if x > y
print x
else
print y
```

---

## 🔧 Project Structure
```
rei_lang/
├── rei/
│   ├── Expr.java
│   ├── Stmt.java
│   ├── ReiLexer.java
│   ├── ReiParser.java
│   ├── ReiInterpreter.java
│   ├── Token.java
│   └── TokenType.java
├── Main.java
```



## 🔮 Future Work

- Loops (`while`, `for`)
- User-defined functions
- Code blocks and indentation
- Strings and input/output

---

## 👨‍💻 Author

Passionate about language design, compilers, and clean syntax, Naman developed ReiLang to explore how simple interpreted languages work under the hood — focusing on readability, expression parsing, and core logic constructs like arithmetic, variable assignments, printing, and conditional statements.

 Contributions and suggestions are welcome!

