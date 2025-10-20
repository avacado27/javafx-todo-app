# 📝 JavaFX To-Do List App

A simple desktop to-do list application built with Java 25 and JavaFX, featuring task creation, editing, priority setting, and local JSON storage.

## 🚀 Features

- ✅ Add, remove, and complete tasks
- 📝 Edit task names after creation
- ⚡ Set task priorities (High, Medium, Low)
- 💾 Save and load tasks using a JSON file
- 🖱️ User-friendly GUI built with JavaFX


## 🛠️ Tech Stack

- **Java 25**
- **JavaFX SDK 24.0.1**
- **Gson** (for JSON serialization)
- **VSCode**

## 🧰 How to Run

### Option 1: Compile & Run Manually (Command Line)

1. [Download JavaFX SDK](https://gluonhq.com/products/javafx/) and extract it.
2. Open a terminal in the project directory.
3. Run the app with:

```bash
java --module-path /Users/avapakzad/Personal Projects/MyToDoList/javafx-sdk-24.0.1/lib --add-modules javafx.controls,javafx.fxml -cp . GUI1