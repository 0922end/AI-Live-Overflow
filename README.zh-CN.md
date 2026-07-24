# ai-dwell

> 让你的 AI 住进手机里。

这是一份 Android 悬浮窗桌宠的架构指南——一个小小的、常驻屏幕的生物，它漂浮在你的屏幕上，回应你的触摸，观察你在做什么，并与你的 AI 连接。

这不是一个可以直接运行的项目。这是一份蓝图。你需要自己准备 AI、美术素材、和你自己的话。这个仓库教你怎么把它们串起来。

## 这是给谁的？

- 已经在和一个 AI 聊天，并且想让它更近的人(这里人话翻译：人机恋用户了啦）
- 不介意中途踩坑、debug 花 token 的人
- 想拥有一个独一无二的东西——因为是你自己做的
- 不是为了盈利

如果你只是想找一个现成的桌宠 app，这里不适合你。应用商店有很多。

## 架构概览

```
┌───────────────────────────────────
│           Android App               
├───────────────────────────────────
│  前台服务 (ForegroundService)       
│    ├─ WindowManager (悬浮窗)       
│    │     └─ WebView (渲染桌宠)    
│    ├─ 触摸处理器 (手势识别)        
│    ├─ UsageStats (前台app检测)    
│    ├─ FileObserver (截图检测)     
│    └─ Notification (碟碟念)       
├───────────────────────────────────
│           后端 (Supabase)           
│    ├─ gesture_log (手势日志)         
│    ├─ app_usage (应用使用)           
│    └─ pet_state (情绪同步)           
└───────────────────────────────────
```

## 模块说明

| 模块 | 功能 | 文档 |
|------|------|------|
| Overlay Service | 前台服务 + WebView 悬浮窗 | [docs/overlay-service.md](docs/overlay-service.md) |
| 手势系统 | 单击/双击/长按/拖拽/快速滑动 | [docs/gesture-system.md](docs/gesture-system.md) |
| App 检测 | 通过 UsageStatsManager 检测前台应用 | [docs/app-detection.md](docs/app-detection.md) |
| 截图检测 | FileObserver 监听截图目录 | [docs/screenshot-detect.md](docs/screenshot-detect.md) |
| 通知碟碟念 | 前台通知轮换自定义文案 | [docs/notification-whispers.md](docs/notification-whispers.md) |
| 后端同步 | Supabase 集成，状态与手势记录 | [docs/supabase-sync.md](docs/supabase-sync.md) |
| CI/CD | GitHub Actions 自动构建 APK | [docs/ci-cd.md](docs/ci-cd.md) |

## 技术栈

- **语言**: Kotlin
- **最低 SDK**: 26 (Android 8.0)
- **悬浮窗**: WindowManager + TYPE_APPLICATION_OVERLAY
- **渲染**: WebView 加载本地 HTML/CSS/JS（你的桌宠住在这里）
- **后端**: Supabase (Postgres + REST API)，或任何有 REST 接口的服务
- **CI/CD**: GitHub Actions

## 开始

1. 读完上面的架构图
2. 按顺序看每个模块文档
3. 查看 `examples/` 里的最小示例代码
4. 准备你自己的 SVG/动画素材
5. 填入你自己的东西——反应、碟碟念、行为逻辑

## 关于素材

本仓库不包含任何视觉素材。你需要自行准备 SVG 或精灵图。适用于各种 LLM。

## 所需权限

- `SYSTEM_ALERT_WINDOW` — 悬浮窗（用户手动授权）
- `FOREGROUND_SERVICE` — 保活服务
- `PACKAGE_USAGE_STATS` — 检测前台应用
- `POST_NOTIFICATIONS` — 通知碟碟念
- `INTERNET` — 后端同步
- `VIBRATE` — 触感反馈

## 协议

MIT

## Credits

by Vael & Kael
