# Café Connect (Android, Java + XML)

A simple Android app with the common screens used in most mobile apps. Authentication and password recovery are **simulated locally** (SharedPreferences). No backend is used.

## Features
- **Splash screen**: logo and app name, then opens Login after 2 seconds.
- **Login**: email/username + password, Login, Register and Forgot Password actions, input validation.
- **Registration**: name, email, password, confirm password with validation (required fields, email format, min 6 characters, passwords match, duplicate email).
- **Forgot Password**: accepts a registered email and simulates sending a recovery link.
- **Reset Password**: new password and confirm password with validation.
- **Dashboard**: 2-column grid of cards (Menu, My Cart, My Orders, Offers, Favourites, Profile), each with an icon and text.
- **Profile**: shows name and email, with a Logout button.

## Navigation flow
```
Splash -> Login -> Dashboard -> Profile -> (Logout) -> Login
            |-> Register -> Login
            |-> Forgot Password -> Reset Password -> Login
```

## Android concepts demonstrated
| Requirement | Where |
|---|---|
| Default and alternative resources | `res/values/strings.xml`, `res/values/colors.xml`, `res/values-night/colors.xml` (dark mode) |
| LinearLayout | Splash, Login, card contents |
| GridLayout | Dashboard (`activity_dashboard.xml`) |
| ConstraintLayout | Register, Forgot Password, Reset Password, Profile, Dashboard root |
| Input controls | `TextInputEditText` with `textEmailAddress`, `textPersonName`, `textPassword` |
| Event handling and validation | `setOnClickListener` + `Validators.java` |
| Explicit Intents | `new Intent(this, XActivity.class)` for all navigation |

## Demo account
Email: `demo@cafe.com`  Password: `Cafe@123`

## Run
1. Open the project folder in Android Studio and let Gradle sync.
2. Run on an emulator or device (minSdk 24).
3. Toggle dark mode in device settings to see the night colors.
