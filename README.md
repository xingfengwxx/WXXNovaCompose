# WXXNovaCompose

企业级 Android 项目，基于 Jetpack Compose 和组件化架构构建。

## 🎯 项目目标

构建一个具备以下特性的企业级 Android 工程：
- 高可维护性
- 高可扩展性
- 高性能
- 适合团队协作
- 符合 Google 官方最佳实践

## 🛠 技术栈

### 核心技术
- **开发语言**: Kotlin
- **UI 框架**: Jetpack Compose + XML
- **架构模式**: MVVM
- **依赖注入**: Hilt
- **网络请求**: Retrofit + OkHttp + Coroutine
- **状态管理**: Flow / StateFlow
- **数据库**: Room
- **本地存储**: DataStore
- **导航**: Navigation 组件

### 工具库
- **工具类**: blankj.utilcode
- **图片加载**: Glide + Coil
- **分页**: Paging 3
- **JSON 解析**: Gson

## 📁 项目结构

### 模块划分
```
WXXNovaCompose/
├── app/                         # Host App（仅壳工程，组合 feature）
├── core/                        # 基础能力层
├── core-db/                     # 数据库：Room / DataStore / DAO
├── core-di/                     # Hilt 全局 Module
├── core-logging/                # 日志 & 异常上报
├── core-models/                 # 公共数据模型
├── core-network/                # 网络：OkHttp / Retrofit / Interceptors
├── feature-home/                # 首页模块
├── feature-projectcategory/     # 项目分类模块
├── ui-common/                   # Compose UI 公共组件
├── gradle/                      # Gradle 配置
└── settings.gradle.kts          # 项目设置
```

### Feature 内部结构
每个 feature-xxx 内部采用 MVVM + UseCase 分层架构：
```
feature-xxx/
├─ ui/                    # Compose / Activity / Fragment
├─ viewmodel/             # ViewModel（StateFlow）
├─ domain/
│   ├─ usecase/           # 业务用例（纯 Kotlin，可测试）
│   └─ model/             # Domain model
├─ data/
│   ├─ repository/        # Repository 接口实现
│   └─ local/remote       # LocalDataSource / RemoteDataSource
└─ api/                   # 对外暴露的 Contract
```

## 🏗 架构模式

### 核心架构
- **MVVM**: UI → ViewModel → Repository → DataSource(Remote/Local)
- **单 Activity + 多 Fragment**: 减少 Activity 数量，提高性能
- **Repository Pattern**: 统一数据访问入口，隔离数据来源

### 状态管理
- 使用 sealed class 表示 UI State
- ViewModel 暴露 StateFlow 供 UI 订阅
- 单向数据流：View → ViewModel → State → View

## ✨ 核心特性

### 组件化设计
- 模块间低耦合，高内聚
- 支持独立编译和测试
- 便于团队协作开发

### 网络层
- 统一的请求和响应处理
- 错误处理标准
- 数据转换策略

### UI 层
- Compose 优先的 UI 实现
- Material 3 Expressive 设计规范
- 支持深色模式
- 响应式布局

### 数据层
- Room 数据库支持
- DataStore 替代 SharedPreferences
- 支持数据缓存

## 🚀 构建和运行

### 环境要求
- Android Studio Hedgehog 或更高版本
- Kotlin 1.9.0 或更高版本
- Gradle 8.0 或更高版本

### 构建命令
```bash
# 构建项目
./gradlew build

# 安装 debug 版本
./gradlew installDebug

# 运行测试
./gradlew test
```

## 📝 编码规范

### 命名规范
- 类名：大驼峰命名法（PascalCase）
- 方法名：小驼峰命名法（camelCase）
- 变量名：小驼峰命名法（camelCase）
- 常量名：全大写，下划线分隔（UPPER_CASE_WITH_UNDERSCORES）

### 注释规范
- 所有类必须使用 KDoc 注释
- 方法和属性必须添加注释说明
- 复杂逻辑必须添加注释

## 🔧 开发工具

### Android Studio 插件推荐
- Kotlin
- Compose Multiplatform IDE Support
- Hilt
- Git
- Markdown Support

## 📄 许可协议

MIT License

## 📞 联系方式

- 作者：王星星
- 邮箱：1099420259@qq.com
