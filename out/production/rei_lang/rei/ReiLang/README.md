# ReiLang 

ReiLang is a simple, Python-inspired interpreted programming language built in Java. It supports basic constructs such as variable assignment, arithmetic operations, print statements, and conditional logic.

---

## ✨ Features

- Variable Assignment
- Arithmetic Operations (`+`, `-`, `*`, `/`)
- Print Statements
- If-Else Conditionals

---

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

---

## 🚀 Getting Started

### Compile:
```bash
javac -d . rei/*.java Main.java
```

### Run:
```bash
java Main path/to/script.rei
```


---

## 🔮 Future Work

- Loops (`while`, `for`)
- User-defined functions
- Code blocks and indentation
- Strings and input/output

---

## 👨‍💻 Author
Passionate about language design, compilers, and clean syntax, I developed ReiLang to explore how simple interpreted languages work under the hood — focusing on readability, expression parsing, and core logic constructs like arithmetic, variable assignments, printing, and conditional statements.

 


Contributions and suggestions are welcome!
