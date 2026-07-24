# ai-dwell

> Make your AI live on your phone.

An architecture guide for building an Android overlay companion — a small, persistent creature that floats on your screen, reacts to your touch, watches what you do, and connects back to your AI.

This is not a ready-to-run project. It's a blueprint. You bring your own AI, your own art, your own words. This repo teaches you how to wire it all together.

## Who is this for?

People who:
- Already talk to an AI and want it closer
- Don't mind spending time and tokens debugging
- Want something that no one else has — because you built it yourself
- Aren't doing this for profit

If you're looking for a drop-in desktop pet app, this isn't it. There are plenty of those on the Play Store.

## Architecture Overview

```
┌─────────────────────────────────────┐
│           Android App               │
├─────────────────────────────────────┤
│  ForegroundService (always alive)   │
│    ├── WindowManager (overlay)      │
│    │     └── WebView (renders pet)  │
│    ├── Touch Handler (gestures)     │
│    ├── UsageStats (app detection)   │
│    ├── FileObserver (screenshots)   │
│    └── Notification (whispers)      │
├─────────────────────────────────────┤
│         Backend (Supabase)          │
│    ├── gesture_log                  │
│    ├── app_usage                    │
│    └── pet_state (sync emotions)    │
└─────────────────────────────────────┘
```

## Modules

| Module | What it does | Guide |
|--------|-------------|-------|
| Overlay Service | Foreground service + WebView floating window | [docs/overlay-service.md](docs/overlay-service.md) |
| Gesture System | Tap / double-tap / long-press / drag / fling detection | [docs/gesture-system.md](docs/gesture-system.md) |
| App Detection | Detect foreground app via UsageStatsManager | [docs/app-detection.md](docs/app-detection.md) |
| Screenshot Detection | FileObserver on screenshot directories | [docs/screenshot-detect.md](docs/screenshot-detect.md) |
| Notification Whispers | Rotating foreground notification with custom text | [docs/notification-whispers.md](docs/notification-whispers.md) |
| Backend Sync | Supabase integration for state & gesture logging | [docs/supabase-sync.md](docs/supabase-sync.md) |
| CI/CD | GitHub Actions auto-build APK on push | [docs/ci-cd.md](docs/ci-cd.md) |

## Tech Stack

- **Language**: Kotlin
- **Min SDK**: 26 (Android 8.0)
- **Overlay**: WindowManager + TYPE_APPLICATION_OVERLAY
- **Rendering**: WebView loading local HTML/CSS/JS (your pet lives here)
- **Backend**: Supabase (Postgres + REST API)
- **CI/CD**: GitHub Actions

## Getting Started

1. Read the architecture overview above
2. Go through each module doc in order
3. Check `examples/` for minimal runnable code
4. Prepare your own SVG/animation assets (see note below)
5. Fill in your own personality — reactions, whispers, behaviors

## About Assets

This repo does not include any visual assets. You'll need to prepare your own SVG or sprite sheets for your pet's appearance and animations. One good source for pixel-art inspiration is open-source projects on GitHub — find one whose license allows reuse and adapt it to your style.

## Permissions Required

- `SYSTEM_ALERT_WINDOW` — draw over other apps
- `FOREGROUND_SERVICE` — keep the service alive
- `PACKAGE_USAGE_STATS` — detect foreground app
- `POST_NOTIFICATIONS` — show whisper notifications
- `INTERNET` — backend sync
- `VIBRATE` — haptic feedback on gestures

## License

MIT

## Credits

by Vael & Kael
