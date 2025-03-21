# 🚀 Contact Manager

A dynamic and intuitive contact management application built with **Java 21**, **JDK 17**, and **Spring Boot**. This project simplifies contact organization with a seamless UI, role-based authentication, and a powerful backend.

---

## 🌟 Key Highlights

- **Beautiful Landing Page:** Home, Registration, Login, and About sections.
- **Secure Authentication & Authorization:** Role-based access for users.
- **User Dashboard:**
    - 📇 Add and manage contacts effortlessly.
    - 🖼 Profile customization.
    - 📜 Infinite scrolling for all contacts.
    - ⭐ Favorite contacts for quick access.
    - 🔓 Easy logout feature.

---

## 🛠 Tech Stack

| **Category**   | **Technologies Used** |
|---------------|----------------------|
| 🎨 **Frontend**  | HTML, CSS, JavaScript, Thymeleaf |
| ⚙️ **Backend**  | Java 21, Spring Boot, Spring Data JPA, Spring Security, Hibernate Validator |
| 🗄 **Database** | MySQL |

> **Note:** Update your `application.properties` file with MySQL credentials before running the application.

---

## 🚀 Getting Started

### 🔧 Prerequisites

Ensure you have the following installed:
- **Java 21**
- **JDK 17**
- **MySQL Server**

### 🏗 Installation & Setup

1️⃣ **Clone the Repository:**
   ```bash[]
   git clone https://github.com/dinesh776/ContactManager.git
   cd ContactManager
   ```

2️⃣ **Configure the Database:**
Edit `src/main/resources/application.properties`:
   ```properties[]
   spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

3️⃣ **Build the Application:**
   ```bash[]
   ./mvnw clean package  # For Mac/Linux
   mvnw.cmd clean package  # For Windows
   ```

4️⃣ **Run the Application:**
   ```bash[]
   ./mvnw spring-boot:run
   ```

5️⃣ **Access the Application:**
Open `http://localhost:8080` in your browser.

---

## 📂 Project Structure

```[]
ContactManager/
├── .mvn/                   # Maven Wrapper files
├── src/
│   ├── main/
│   │   ├── java/           # Backend logic (Spring Boot, Controllers, Services)
│   │   ├── resources/      # Static assets and configuration
│   │   │   ├── templates/  # Thymeleaf HTML templates
│   │   │   └── static/     # CSS & JavaScript files
│   └── test/               # Unit tests
├── pom.xml                 # Project dependencies (Maven)
├── .gitignore              # Files to be ignored by Git
└── README.md               # Project documentation
```

---

## 🤝 Contributing

Want to improve this project? Follow these steps:

1️⃣ **Fork the repository** 📌
2️⃣ **Create a feature branch:**
   ```bash[]
   git checkout -b feature/YourFeature
   ```
3️⃣ **Commit your changes:**
   ```bash[]
   git commit -m "🚀 Add some feature"
   ```
4️⃣ **Push to your branch:**
   ```bash[]
   git push origin feature/YourFeature
   ```
5️⃣ **Create a pull request** 🔥

---

## 📜 License

This project is licensed under the **MIT License**. Check the [LICENSE](LICENSE) file for details.

---

