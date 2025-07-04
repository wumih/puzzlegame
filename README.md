# PuzzleGame – Java 版 15 格拼图

> **版本**：v0.1（首发预览）  
> **运行平台**：Windows 64-bit  
> **下载方式**：直接点击 [`setup.exe`](./releases) 安装即可，无需另外安装 JDK/JRE  

---

## 一键安装

1. 前往仓库 **[Releases](https://github.com/your-name/puzzle-game/releases/latest)**  
2. 下载 `PuzzleGame-setup.exe`  
3. 双击执行，安装完成后桌面会生成 **PuzzleGame** 快捷方式  
4. 启动即可游玩  

> *安装包内已捆绑 JRE 21，不依赖系统 Java*  

---

## 快速上手

| 键盘操作 | 功能 |
|----------|------|
| ↑ ↓ ← → | 移动空白块（数字随之滑动） |
| **A**    | 快速查看完整原图 |
| **Shift**| 一键通关（作弊/演示用） |

---

## 本项目的“微创新”

- **空白块左右移动逻辑重新优化**  
  - 参考网上多款经典实现，重写了边界检测与数组交换算法  
  - 让左右滑动手感更顺畅，避免旧版偶发的“卡格”现象

---

## TODO（计划功能）

- [ ] 计步 & 计时统计  
- [ ] 胜利面板美化，支持截图分享  
- [ ] 自定义图片导入  
- [ ] 声效 / BGM  
- [ ] Mac & Linux 版本（`jpackage`）  

---

## 致谢

- **原型参考**：搜索到的多篇 Java 拼图教学文章及开源 Demo  
- **打包工具**：exe4j + Inno Setup  
- **JDK**：Eclipse Temurin 21  

> *本仓库仅做学习与交流，仍处于初版阶段，欢迎 Issue & PR！*
