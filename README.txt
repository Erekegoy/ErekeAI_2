ErekeAI Full Assistant - local build instructions
================================================

1) Open this folder in Android Studio (File -> Open).
2) Before building, set your GROQ API key securely:
   - Edit local.properties at project root (NOT under app/):
       GROQ_API_KEY=sk_your_real_key_here
   - OR change app/build.gradle to hardcode (not recommended).

3) Sync project. Gradle wrapper is configured for gradle-8.7 and AGP 8.5.2.

4) Build and Run on an Android device/emulator with internet access.

What's included:
- Compose UI (simple chat screen)
- ChatViewModel + ChatRepository (calls GroqApiService.create())
- Retrofit + OkHttp with Authorization header interceptor
- WebSocket helper (placeholder)
- Places to add STT/TTS, wake-word, image generation, offline models.

Notes:
- This project is a template. You need to implement richer parsing of Groq responses and improved error handling.
- Do not commit your local.properties to any public repo.
