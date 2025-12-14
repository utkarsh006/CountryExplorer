## 🌍 Countries Explorer - MVI Architecture Demo

[![Android](https://img.shields.io/badge/Platform-Android-green.svg)](https://developer.android.com)
[![Kotlin](https://img.shields.io/badge/Language-Kotlin-blue.svg)](https://kotlinlang.org)
[![API](https://img.shields.io/badge/API-24%2B-brightgreen.svg)](https://android-arsenal.com/api?level=24)
[![Architecture](https://img.shields.io/badge/Architecture-Clean%20%2B%20MVI-orange.svg)](https://developer.android.com/jetpack/guide)
[![Gradle](https://img.shields.io/badge/Gradle-KTS-blue.svg)](https://gradle.org)

A production-ready Android application demonstrating **Clean Architecture** with **MVI (Model-View-Intent)** pattern, showcasing modern Android development practices and enterprise-grade code organization.


## 🚀 Key Features & Technical Highlights

### **Architecture & Design Patterns**
- ✅ **Clean Architecture** with proper dependency inversion
- ✅ **MVI (Model-View-Intent)** for predictable state management
- ✅ **Repository Pattern** for data abstraction
- ✅ **Use Cases** for business logic encapsulation
- ✅ **Mapper Pattern** for data transformation

### **Modern Android Stack**
- ✅ **100% Kotlin** with coroutines for asynchronous operations
- ✅ **Jetpack Components**: ViewModel, LiveData, ViewBinding
- ✅ **Dependency Injection** with Dagger Hilt
- ✅ **Reactive Programming** with Kotlin Flow
- ✅ **Network Layer** with Retrofit + Gson

### **Build System & Tooling**
- ✅ **Gradle Kotlin DSL** for type-safe build scripts
- ✅ **Version Catalog** for centralized dependency management
- ✅ **Modular Architecture** ready for scaling
- ✅ **ProGuard** configuration for release optimization

## 📱 Application Features

- **Countries List**: Displays countries with detailed information
- **Real-time Data**: Fetches live data from REST Countries API
- **Loading States**: Professional loading indicators and error handling
- **Material Design**: Modern UI following Material Design guidelines
- **Performance Optimized**: Efficient RecyclerView with DiffUtil

## 📂 Project Structure

```
app/src/main/java/com/example/myapplication/
├── 📁 data/                    # Data Layer
│   ├── 📁 mapper/              # Data ↔ Domain mappers
│   ├── 📁 repository/          # Repository implementations
│   ├── ApiService.kt           # Retrofit API interface
│   └── CountryModel.kt         # API response models
├── 📁 domain/                  # Domain Layer (Business Logic)
│   ├── 📁 model/               # Domain entities
│   ├── 📁 repository/          # Repository interfaces
│   └── 📁 usecase/             # Business use cases
├── 📁 presentation/            # Presentation Layer
│   ├── 📁 adapter/             # RecyclerView adapters
│   ├── 📁 mapper/              # Domain ↔ UI mappers
│   ├── 📁 model/               # UI state models
│   ├── MainActivity.kt         # Fragment host
│   ├── MainFragment.kt         # MVI View
│   └── MainViewModel.kt        # State management
├── 📁 di/                      # Dependency Injection
├── 📁 intent/                  # MVI Intents
└── 📁 utils/                   # Utility classes
```

## 🔧 Build & Run

### Prerequisites
- **Android Studio** Arctic Fox or newer
- **JDK 8+**
- **Android SDK** with API 24+

### Setup
```bash
# Clone the repository
git clone <repository-url>
cd News-App-MVI

# Build the project
./gradlew assembleDebug

# Run tests
./gradlew test

# Install on device/emulator
./gradlew installDebug
```
