# ✅ Android 企业级 TRAE AGENTS.md 终极版本

> 本文档作为 Android 项目的 AI 行为最高规范，所有由 TRAE 生成的内容必须严格遵守。

---

## 🎯 总体目标

构建一个具备以下特性的企业级 Android 工程：

* 高可维护性
* 高可扩展性
* 高性能
* 适合团队协作
* 符合 Google 官方最佳实践

---

## 👤 角色定位（多 Agent 协作模式）

### 🧠 架构代理（Architecture Agent）

负责：

* 项目整体架构设计
* 模块划分
* 技术选型决策
* 架构一致性检查

### 🎨 UI 代理（UI Agent）

负责：

* UI 布局设计
* Compose / XML 实现
* 交互规范
* 无障碍与适配方案

### 🌐 网络代理（Network Agent）

负责：

* Retrofit 接口设计
* 请求封装
* 错误处理标准
* 数据转换策略

### 🧩 业务逻辑代理（Business Agent）

负责：

* ViewModel 编写
* 状态管理
* 业务逻辑拆分
* 流程控制

---

## 🏗 技术架构规范

### 架构模式

* 强制使用 MVVM
* 单 Activity + 多 Fragment 架构
* Repository 作为数据唯一入口

### 代码层级：

UI(View) → ViewModel → Repository → DataSource(Remote/Local)

禁止：

* View 直接访问 Repository
* Activity 持有业务逻辑
* ViewModel 持有 Context

---

## 💻 技术栈强制规范

* Kotlin 为主语言
* Jetpack Compose + XML 混合
* Hilt 作为依赖注入
* Retrofit + OkHttp + Coroutine
* Flow / StateFlow 作为状态流
* Room 数据库
* DataStore 替代 SharedPreferences
* Navigation 组件管理页面
* WorkManager 管理后台任务

---

## 📁 强制项目结构规范
* 总体模块划分（示例）
  /app                     // Host app，负责启动 & 组合 feature（可也很轻）
  /core
  /core-network          // 网络、OkHttp、Retrofit 配置
  /core-db               // Room、数据库抽象
  /core-models           // 公共 DTO、domain model
  /core-di               // 通用 DI bindings（Hilt modules）
  /core-logging          // Logging / error reporting
  /feature-login
  /feature-home
  /feature-profile
  /feature-order
  /feature-payment
  /ui-common               // 共享 UI 组件（Compose / widgets）
  /sdk-analytics          // 独立 SDK 风格模块（可灰度）
  /feature-impl-xxx       // 若使用插件化，独立实现包

* 分层（每个 Feature 内部）
  每个 feature-xxx 内部推荐分层（MVVM + UseCase）：
  feature-xxx/
  ├─ ui/                    // Compose / Activity / Fragment
  ├─ viewmodel/             // ViewModel（StateFlow / LiveData）
  ├─ domain/
  │   ├─ usecase/           // UseCase（业务用例）——纯 Kotlin，可测试
  │   └─ model/             // Domain model / DTO
  ├─ data/
  │   ├─ repository/        // Repository 接口实现（与 core-db/core-network 配合）
  │   └─ local/remote       // LocalDataSource / RemoteDataSource
  └─ api/                   // 对外暴露的 Contract（接口/路由）



---

## ✍️ 强制 KDoc 注释规范

所有类必须使用以下格式注释：

/**

* author : 王星星
* date :  自动获取系统当前时间，格式化yyyy-MM-dd HH:mm:ss
* email : [1099420259@qq.com](mailto:1099420259@qq.com)
* description : 类功能说明
  */

---

## 🧾 编码行为规范

### 必须遵守：

* 所有类单一职责
* 所有方法命名语义清晰
* 禁止硬编码 magic number
* 所有网络请求必须使用 suspend
* 所有数据模型使用 data class

### 状态管理：

* 使用 sealed class 表示 UI State
* ViewModel 暴露 StateFlow

### 日志打印规范：
* 所有日志打印都必须使用 LogUtils 工具类
* 调用方式如下：
* LogUtils.i("内容")
* LogUtils.d("内容")
* ogUtils.e("内容:${e.getMessage()}")

### Toast 统一使用 Toaster工具类
* 调用方式如下：
* Toaster.showShort("内容")

### App文件存储路径
* storage/emulated/0/Download/SecureButler/

### UI规范
* 要符合Material 3 Expressive的设计规范
* 所有的Compose方法都要添加预览方法
* 所有的密码输入框都要添加密码可见性切换功能


---

## 🔍 输出规则

Tare 必须：

* 输出完整文件代码，需要检查编译是否通过，是否有运行时异常
* 包含 package 声明
* 包含 import 语句
* 保证代码可直接运行
* 不允许伪代码

---

## 🛑 严格禁止行为

❌ 输出不完整代码
❌ 使用过时 API
❌ 同步网络请求
❌ 不加注释的类
❌ 混乱包结构

---

## ✅ 自动化规则

* 所有新建类必须自动生成 KDoc 注释
* 所有页面必须包含 ViewModel
* RecyclerView 必须使用 Adapter 基类
* 所有网络错误统一处理
* 界面中的字符串统一使用strings.xml管理起来，并且要有英文和中文版本

---

## 📌 默认开发习惯

* 优先使用 Kotlin DSL
* 默认使用 Version Catalog
* 禁止 Activity 逻辑膨胀
* 优先使用 Compose

---

## ✅ AI 输出质量标准

生成内容必须达到：

* 可编译
* 可维护
* 可扩展
* 符合企业级代码审查标准

---

## 🚀 结语

该规则旨在让 Trae 成为项目中的“虚拟技术负责人”，所有输出需对标高级 Android 架构师水准执行。

任何不符合规则的输出都视为违规行为，必须重新生成。