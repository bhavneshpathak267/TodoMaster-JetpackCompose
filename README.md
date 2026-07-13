# 📱 TodoMaster

A modern Android To-Do application built using **Kotlin**, **Jetpack Compose**, **MVVM Architecture**, **Room Database**, and **Firebase**. TodoMaster helps users organize daily tasks with offline support, cloud synchronization, reminders, notifications, and a beautiful Material 3 interface.

---

## ✨ Features

### 🔐 Authentication
- Firebase Authentication
- Email Verification
- Login & Register
- Forgot Password
- Secure Logout

### ✅ Task Management
- Add Task
- Edit Task
- Delete Task
- Mark Complete / Pending
- Search Tasks
- Task Categories
- Task Priority
- Notes Support
- Due Date
- Reminder Time

### ☁️ Cloud Sync
- Firestore Synchronization
- Offline Room Database
- Auto Sync
- Persistent Data

### 🔔 Notifications
- Reminder Notifications
- Scheduled Task Alerts
- Background Notification Support

### 👤 Profile
- User Profile
- Edit Name
- Edit Bio
- Firebase Profile Sync

### 📊 Dashboard
- Total Tasks
- Completed Tasks
- Pending Tasks
- Progress Statistics

### 🎨 UI
- Material 3 Design
- Jetpack Compose
- Dark Mode
- Responsive Layout
- Modern Dashboard
- Beautiful Task Cards

---

# 🏗️ Architecture

MVVM (Model–View–ViewModel)

```
UI
│
▼
ViewModel
│
▼
UseCases
│
▼
Repository
│
├──────── Room Database
│
└──────── Firebase Firestore
```

---

# 📂 Project Structure

```
app
│
├── data
│   ├── local
│   ├── remote
│   ├── mapper
│   └── repository
│
├── domain
│   ├── model
│   ├── repository
│   └── usecase
│
├── ui
│   ├── screens
│   ├── components
│   └── theme
│
├── navigation
│
├── viewmodel
│
└── worker
```

---

# 🛠 Tech Stack

- Kotlin
- Jetpack Compose
- MVVM Architecture
- Clean Architecture
- Room Database
- Firebase Authentication
- Firebase Firestore
- Kotlin Coroutines
- StateFlow
- Navigation Compose
- Material 3
- WorkManager

---

# 📸 Screens

- Splash Screen
- Login
- Register
- Forgot Password
- Home Dashboard
- Add Task
- Edit Task
- Profile
- Settings

---

# 🚀 Future Improvements

- Calendar View
- Task Sharing
- Attachments
- Voice Notes
- Google Calendar Integration
- Widgets
- Multiple Themes

---

# ⚙️ Installation

```bash
git clone https://github.com/YOUR_USERNAME/TodoMaster.git
```

Open in Android Studio.

Connect Firebase.

Run the application.

---

# 👨‍💻 Developer

**Bhavnesh Pathak**

B.Tech (Artificial Intelligence & Machine Learning)

Android Developer

---

# ⭐ If you like this project

Give this repository a ⭐ on GitHub.
