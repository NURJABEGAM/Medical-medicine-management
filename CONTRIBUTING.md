# Contributing to AX Player

Thank you for your interest in contributing to AX Player! This document provides guidelines and instructions for contributing.

## 🎯 Ways to Contribute

- **Bug Reports**: Report issues you encounter
- **Feature Requests**: Suggest new features
- **Code Contributions**: Submit pull requests
- **Documentation**: Improve documentation
- **Translations**: Add support for more languages
- **Testing**: Help test new features

## 🐛 Reporting Bugs

Before submitting a bug report:
1. Check existing issues to avoid duplicates
2. Use the latest version of the app
3. Verify the bug is reproducible

### Bug Report Template

```markdown
**Description**
Clear description of the bug

**Steps to Reproduce**
1. Go to '...'
2. Tap on '...'
3. Scroll down to '...'
4. See error

**Expected Behavior**
What you expected to happen

**Actual Behavior**
What actually happened

**Screenshots**
If applicable, add screenshots

**Device Information**
- Device: [e.g., Pixel 6]
- Android Version: [e.g., Android 14]
- App Version: [e.g., 1.0.0]

**Additional Context**
Any other relevant information
```

## 💡 Feature Requests

### Feature Request Template

```markdown
**Feature Description**
Clear description of the feature

**Problem it Solves**
What problem does this feature address?

**Proposed Solution**
Your suggested implementation

**Alternatives Considered**
Other solutions you've thought about

**Additional Context**
Mockups, examples, or references
```

## 🔧 Development Setup

### Prerequisites
- Android Studio Hedgehog | 2023.1.1 or later
- JDK 17
- Android SDK API 35
- Git

### Setup Steps

1. **Fork and Clone**
   ```bash
   git clone https://github.com/YOUR_USERNAME/ax-player.git
   cd ax-player
   ```

2. **Open in Android Studio**
   - Open Android Studio
   - File → Open → Select project directory
   - Wait for Gradle sync

3. **Add Firebase Configuration** (Optional)
   - Create a Firebase project
   - Download `google-services.json`
   - Place in `app/` directory

4. **Build the Project**
   ```bash
   ./gradlew build
   ```

5. **Run on Device/Emulator**
   - Connect device or start emulator
   - Click Run button or use:
   ```bash
   ./gradlew installDebug
   ```

## 📝 Code Style Guidelines

### Kotlin Conventions
- Follow [Kotlin Coding Conventions](https://kotlinlang.org/docs/coding-conventions.html)
- Use 4 spaces for indentation
- Line length: 120 characters max
- Use meaningful variable names

### Naming Conventions
- **Classes**: PascalCase (`VideoPlayer`, `LibraryViewModel`)
- **Functions**: camelCase (`playVideo()`, `toggleFavorite()`)
- **Constants**: UPPER_SNAKE_CASE (`MAX_CACHE_SIZE`, `DEFAULT_SPEED`)
- **Variables**: camelCase (`videoList`, `isPlaying`)
- **Private properties**: prefix with underscore (`_state`, `_videos`)

### Compose Best Practices
```kotlin
@Composable
fun MyScreen(
    viewModel: MyViewModel = hiltViewModel(),
    onNavigate: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    
    MyScreenContent(
        state = state,
        onEvent = viewModel::onEvent
    )
}

@Composable
private fun MyScreenContent(
    state: MyState,
    onEvent: (MyEvent) -> Unit
) {
    // UI implementation
}
```

### Architecture Guidelines
- Follow Clean Architecture principles
- Use MVVM pattern
- Keep ViewModels platform-independent
- Use Flow for reactive data
- Handle errors gracefully

### Comments
```kotlin
// Single-line comments for brief explanations

/**
 * Multi-line comments for:
 * - Class documentation
 * - Function documentation
 * - Complex logic explanation
 */
```

## 🧪 Testing

### Running Tests
```bash
# Unit tests
./gradlew test

# Instrumented tests
./gradlew connectedAndroidTest

# All tests
./gradlew check
```

### Writing Tests

**Unit Tests**
```kotlin
@Test
fun `test description`() = runTest {
    // Arrange
    val viewModel = MyViewModel(mockUseCase)
    
    // Act
    viewModel.onEvent(MyEvent.DoSomething)
    
    // Assert
    assertEquals(expected, viewModel.state.value.property)
}
```

**UI Tests**
```kotlin
@Test
fun testFeature() {
    composeTestRule.setContent {
        MyScreen()
    }
    
    composeTestRule
        .onNodeWithText("Button")
        .performClick()
    
    composeTestRule
        .onNodeWithText("Result")
        .assertIsDisplayed()
}
```

## 🔀 Git Workflow

### Branch Naming
- Feature: `feat-video-bookmarks`
- Bug fix: `fix-playback-crash`
- Documentation: `docs-architecture`
- Refactor: `refactor-player-state`

### Commit Messages
Follow [Conventional Commits](https://www.conventionalcommits.org/):

```
feat: add video bookmark functionality
fix: resolve crash when loading subtitles
docs: update architecture documentation
refactor: simplify player state management
test: add unit tests for playlist repository
chore: update dependencies
```

### Pull Request Process

1. **Create Feature Branch**
   ```bash
   git checkout -b feat-my-feature
   ```

2. **Make Changes**
   - Write code
   - Add tests
   - Update documentation

3. **Commit Changes**
   ```bash
   git add .
   git commit -m "feat: add my feature"
   ```

4. **Push to Fork**
   ```bash
   git push origin feat-my-feature
   ```

5. **Create Pull Request**
   - Go to GitHub repository
   - Click "New Pull Request"
   - Fill in template
   - Request review

### Pull Request Template

```markdown
## Description
Brief description of changes

## Type of Change
- [ ] Bug fix
- [ ] New feature
- [ ] Breaking change
- [ ] Documentation update

## Testing
- [ ] Unit tests pass
- [ ] UI tests pass
- [ ] Manual testing completed

## Screenshots
If applicable, add screenshots

## Checklist
- [ ] Code follows style guidelines
- [ ] Self-review completed
- [ ] Comments added for complex code
- [ ] Documentation updated
- [ ] No new warnings introduced
- [ ] Tests added/updated
```

## 🌍 Translations

### Adding a New Language

1. **Create String Resources**
   ```bash
   mkdir app/src/main/res/values-{language-code}
   cp app/src/main/res/values/strings.xml app/src/main/res/values-{language-code}/
   ```

2. **Translate Strings**
   - Open `values-{language-code}/strings.xml`
   - Translate all strings
   - Keep placeholders intact (e.g., `%s`, `%d`)

3. **Test Translation**
   - Change device language
   - Verify all strings display correctly
   - Check for layout issues

4. **Submit PR**
   - Create PR with translation
   - Include language name in description

## 📚 Documentation

### Types of Documentation
- **Code Comments**: Inline explanations
- **README**: Project overview
- **ARCHITECTURE**: Technical details
- **FEATURES**: Feature documentation
- **API Docs**: Public API documentation

### Documentation Style
- Use clear, concise language
- Include code examples
- Add screenshots for UI features
- Keep documentation up-to-date

## 🏆 Recognition

Contributors will be:
- Listed in CONTRIBUTORS.md
- Mentioned in release notes
- Credited in app's About section

## 📞 Communication

- **GitHub Issues**: Bug reports and feature requests
- **Pull Requests**: Code contributions
- **Discussions**: General questions and ideas

## ⚖️ Code of Conduct

### Our Standards
- Be respectful and inclusive
- Accept constructive criticism
- Focus on what's best for the project
- Show empathy towards others

### Unacceptable Behavior
- Harassment or discrimination
- Trolling or insulting comments
- Publishing others' private information
- Other unprofessional conduct

### Enforcement
Project maintainers have the right to remove, edit, or reject comments, commits, code, and other contributions that don't align with this Code of Conduct.

## 📄 License

By contributing to AX Player, you agree that your contributions will be licensed under the MIT License.

## 🙏 Thank You!

Your contributions make AX Player better for everyone. We appreciate your time and effort!

---

**Happy Coding! 🚀**
