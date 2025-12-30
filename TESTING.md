# Testing Guide for AX Player

This document provides comprehensive guidance on testing AX Player at all levels.

## 📋 Table of Contents

- [Test Strategy](#test-strategy)
- [Unit Tests](#unit-tests)
- [Integration Tests](#integration-tests)
- [UI Tests](#ui-tests)
- [Performance Tests](#performance-tests)
- [Manual Testing](#manual-testing)
- [CI/CD Testing](#cicd-testing)

## 🎯 Test Strategy

### Testing Pyramid

```
         /\
        /UI\         (10% - End-to-end UI tests)
       /----\
      /Integ\        (20% - Integration tests)
     /-------\
    /  Unit   \      (70% - Unit tests)
   /-----------\
```

### Coverage Goals
- **Unit Tests**: 80%+ coverage
- **Integration Tests**: Critical paths covered
- **UI Tests**: Key user journeys covered
- **Manual Tests**: Edge cases and UX validation

## 🧪 Unit Tests

### ViewModel Tests

Test ViewModels in isolation with mocked dependencies:

```kotlin
class LibraryViewModelTest {
    
    private lateinit var viewModel: LibraryViewModel
    private val mockGetVideosUseCase = mockk<GetVideosUseCase>()
    private val mockToggleFavoriteUseCase = mockk<ToggleFavoriteUseCase>()
    
    @Before
    fun setup() {
        viewModel = LibraryViewModel(
            getVideosUseCase = mockGetVideosUseCase,
            toggleFavoriteUseCase = mockToggleFavoriteUseCase
        )
    }
    
    @Test
    fun `initial state is correct`() {
        val state = viewModel.state.value
        assertFalse(state.isLoading)
        assertTrue(state.videos.isEmpty())
        assertNull(state.error)
    }
    
    @Test
    fun `loading videos updates state`() = runTest {
        val mockVideos = listOf(createMockVideo())
        coEvery { mockGetVideosUseCase() } returns flowOf(mockVideos)
        
        viewModel.loadVideos()
        
        advanceUntilIdle()
        
        val state = viewModel.state.value
        assertEquals(mockVideos, state.videos)
        assertFalse(state.isLoading)
    }
    
    @Test
    fun `toggle favorite calls use case`() = runTest {
        val videoId = "123"
        coEvery { mockToggleFavoriteUseCase(videoId) } returns Result.success(Unit)
        
        viewModel.onEvent(LibraryEvent.ToggleFavorite(videoId))
        
        advanceUntilIdle()
        
        coVerify { mockToggleFavoriteUseCase(videoId) }
    }
    
    @Test
    fun `error is handled gracefully`() = runTest {
        val exception = Exception("Network error")
        coEvery { mockGetVideosUseCase() } throws exception
        
        viewModel.loadVideos()
        
        advanceUntilIdle()
        
        val state = viewModel.state.value
        assertNotNull(state.error)
        assertFalse(state.isLoading)
    }
}
```

### UseCase Tests

Test business logic in use cases:

```kotlin
class CreatePlaylistUseCaseTest {
    
    private lateinit var useCase: CreatePlaylistUseCase
    private val mockRepository = mockk<PlaylistRepository>()
    
    @Before
    fun setup() {
        useCase = CreatePlaylistUseCase(mockRepository)
    }
    
    @Test
    fun `creates playlist successfully`() = runTest {
        val name = "My Playlist"
        val description = "Test description"
        
        coEvery { mockRepository.insertPlaylist(any()) } just Runs
        
        val result = useCase(name, description)
        
        assertTrue(result.isSuccess)
        result.getOrNull()?.let { playlist ->
            assertEquals(name, playlist.name)
            assertEquals(description, playlist.description)
        }
        
        coVerify { mockRepository.insertPlaylist(any()) }
    }
    
    @Test
    fun `handles repository error`() = runTest {
        val exception = Exception("Database error")
        coEvery { mockRepository.insertPlaylist(any()) } throws exception
        
        val result = useCase("Test", "")
        
        assertTrue(result.isFailure)
        assertEquals(exception, result.exceptionOrNull())
    }
}
```

### Repository Tests

Test repository implementations:

```kotlin
class PlaylistRepositoryTest {
    
    private lateinit var repository: PlaylistRepositoryImpl
    private val mockDao = mockk<PlaylistDao>()
    private val mockVideoDao = mockk<VideoDao>()
    
    @Before
    fun setup() {
        repository = PlaylistRepositoryImpl(mockDao, mockVideoDao)
    }
    
    @Test
    fun `getPlaylists returns flow of playlists`() = runTest {
        val entities = listOf(createMockPlaylistEntity())
        every { mockDao.getAllPlaylists() } returns flowOf(entities)
        
        val result = repository.getPlaylists().first()
        
        assertEquals(1, result.size)
        assertEquals(entities[0].id, result[0].id)
    }
    
    @Test
    fun `insertPlaylist saves to database`() = runTest {
        val playlist = createMockPlaylist()
        coEvery { mockDao.insertPlaylist(any()) } just Runs
        
        repository.insertPlaylist(playlist)
        
        coVerify { mockDao.insertPlaylist(any()) }
    }
}
```

### Running Unit Tests

```bash
# Run all unit tests
./gradlew test

# Run specific test class
./gradlew test --tests LibraryViewModelTest

# Run with coverage
./gradlew testDebugUnitTest jacocoTestReport

# View coverage report
open app/build/reports/jacoco/jacocoTestReport/html/index.html
```

## 🔗 Integration Tests

### Database Integration Tests

Test Room database with real database instance:

```kotlin
@RunWith(AndroidJUnit4::class)
class PlaylistDaoTest {
    
    private lateinit var database: AppDatabase
    private lateinit var playlistDao: PlaylistDao
    
    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(
            context,
            AppDatabase::class.java
        ).build()
        playlistDao = database.playlistDao()
    }
    
    @After
    fun closeDb() {
        database.close()
    }
    
    @Test
    fun insertAndGetPlaylist() = runTest {
        val playlist = PlaylistEntity(
            id = "1",
            name = "Test Playlist",
            description = "Test",
            videoIds = "[]",
            createdAt = System.currentTimeMillis(),
            updatedAt = System.currentTimeMillis(),
            isSmartPlaylist = false,
            smartPlaylistType = null,
            thumbnailUri = null,
            isFavorite = false
        )
        
        playlistDao.insertPlaylist(playlist)
        
        val result = playlistDao.getPlaylistById("1")
        
        assertNotNull(result)
        assertEquals(playlist.name, result?.name)
    }
    
    @Test
    fun getAllPlaylistsReturnsAll() = runTest {
        val playlists = listOf(
            createPlaylistEntity("1", "Playlist 1"),
            createPlaylistEntity("2", "Playlist 2")
        )
        
        playlists.forEach { playlistDao.insertPlaylist(it) }
        
        val result = playlistDao.getAllPlaylists().first()
        
        assertEquals(2, result.size)
    }
    
    @Test
    fun deletePlaylistRemovesFromDatabase() = runTest {
        val playlist = createPlaylistEntity("1", "Test")
        playlistDao.insertPlaylist(playlist)
        
        playlistDao.deletePlaylist("1")
        
        val result = playlistDao.getPlaylistById("1")
        assertNull(result)
    }
}
```

### Preferences Integration Tests

Test DataStore preferences:

```kotlin
@RunWith(AndroidJUnit4::class)
class PreferencesManagerTest {
    
    private lateinit var preferencesManager: PreferencesManager
    private lateinit var testContext: Context
    
    @Before
    fun setup() {
        testContext = ApplicationProvider.getApplicationContext()
        preferencesManager = PreferencesManager(testContext)
    }
    
    @Test
    fun updateThemeUpdatesPreference() = runTest {
        preferencesManager.updateTheme(UserPreferences.Theme.DARK)
        
        val preferences = preferencesManager.userPreferencesFlow.first()
        
        assertEquals(UserPreferences.Theme.DARK, preferences.theme)
    }
    
    @Test
    fun subtitleSettingsArePersisted() = runTest {
        val settings = SubtitleSettings(
            fontSize = 20,
            textColor = 0xFF00FF00
        )
        
        preferencesManager.updateSubtitleSettings(settings)
        
        val result = preferencesManager.subtitleSettings.first()
        
        assertEquals(20, result.fontSize)
        assertEquals(0xFF00FF00, result.textColor)
    }
}
```

### Running Integration Tests

```bash
# Run all instrumented tests
./gradlew connectedAndroidTest

# Run specific test class
./gradlew connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.class=com.axplayer.data.db.PlaylistDaoTest

# Run on specific device
adb devices
./gradlew connectedAndroidTest -Pandroid.testInstrumentationRunnerArguments.device=DEVICE_ID
```

## 🎨 UI Tests

### Compose UI Tests

Test Compose screens:

```kotlin
@RunWith(AndroidJUnit4::class)
class LibraryScreenTest {
    
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()
    
    @Test
    fun libraryScreen_displaysVideos() {
        val mockVideos = listOf(
            createMockVideo(title = "Video 1"),
            createMockVideo(title = "Video 2")
        )
        
        composeTestRule.setContent {
            AXPlayerTheme {
                LibraryScreen(
                    viewModel = FakeLibraryViewModel(mockVideos)
                )
            }
        }
        
        composeTestRule
            .onNodeWithText("Video 1")
            .assertIsDisplayed()
        
        composeTestRule
            .onNodeWithText("Video 2")
            .assertIsDisplayed()
    }
    
    @Test
    fun libraryScreen_searchFiltersVideos() {
        val mockVideos = listOf(
            createMockVideo(title = "Action Movie"),
            createMockVideo(title = "Comedy Show")
        )
        
        composeTestRule.setContent {
            AXPlayerTheme {
                LibraryScreen(
                    viewModel = FakeLibraryViewModel(mockVideos)
                )
            }
        }
        
        composeTestRule
            .onNodeWithTag("search_field")
            .performTextInput("Action")
        
        composeTestRule
            .onNodeWithText("Action Movie")
            .assertIsDisplayed()
        
        composeTestRule
            .onNodeWithText("Comedy Show")
            .assertDoesNotExist()
    }
    
    @Test
    fun libraryScreen_clickingVideo_navigatesToPlayer() {
        var navigated = false
        var navigatedVideoId = ""
        
        composeTestRule.setContent {
            AXPlayerTheme {
                LibraryScreen(
                    onNavigateToPlayer = { video ->
                        navigated = true
                        navigatedVideoId = video.id
                    }
                )
            }
        }
        
        composeTestRule
            .onNodeWithTag("video_item_0")
            .performClick()
        
        assertTrue(navigated)
        assertNotEquals("", navigatedVideoId)
    }
    
    @Test
    fun libraryScreen_favoriteButton_togglesFavorite() {
        composeTestRule.setContent {
            AXPlayerTheme {
                LibraryScreen()
            }
        }
        
        composeTestRule
            .onNodeWithTag("favorite_button_0")
            .performClick()
        
        composeTestRule
            .onNodeWithTag("favorite_icon_filled_0")
            .assertIsDisplayed()
    }
}
```

### Navigation Tests

Test navigation flows:

```kotlin
@Test
fun navigation_fromLibraryToPlayer() {
    composeTestRule.setContent {
        AXPlayerApp()
    }
    
    // Start at library
    composeTestRule
        .onNodeWithText("Library")
        .assertIsDisplayed()
    
    // Click video
    composeTestRule
        .onNodeWithTag("video_item_0")
        .performClick()
    
    // Verify navigation to player
    composeTestRule
        .onNodeWithTag("player_screen")
        .assertIsDisplayed()
}
```

### Running UI Tests

```bash
# Run all UI tests
./gradlew connectedAndroidTest

# Run with coverage
./gradlew createDebugCoverageReport

# View coverage
open app/build/reports/coverage/androidTest/debug/index.html
```

## ⚡ Performance Tests

### Memory Leak Tests

```kotlin
@Test
fun viewModel_doesNotLeakMemory() {
    val scenario = ActivityScenario.launch(MainActivity::class.java)
    
    scenario.onActivity { activity ->
        val viewModel = ViewModelProvider(activity)[LibraryViewModel::class.java]
        
        // Use ViewModel
        viewModel.loadVideos()
        
        // Verify no leaks
        LeakCanary.assertNoLeaks()
    }
}
```

### Performance Benchmarks

```kotlin
@RunWith(AndroidJUnit4::class)
class LibraryPerformanceTest {
    
    @get:Rule
    val benchmarkRule = BenchmarkRule()
    
    @Test
    fun benchmark_loadLargeVideoList() {
        val videos = generateLargeVideoList(1000)
        
        benchmarkRule.measureRepeated {
            // Measure performance
            val filtered = videos.filter { it.duration > 1000 }
            val sorted = filtered.sortedBy { it.title }
        }
    }
}
```

## 🎯 Manual Testing

### Test Checklist

#### Player Features
- [ ] Play/pause works correctly
- [ ] Seek bar responds accurately
- [ ] Speed control (0.25x to 2.0x) works
- [ ] Audio track switching works
- [ ] Subtitle loading and display works
- [ ] Subtitle customization applies correctly
- [ ] Video filters (brightness, contrast, etc.) work
- [ ] Equalizer presets apply correctly
- [ ] Bookmarks save and navigate correctly
- [ ] Sleep timer pauses at correct time
- [ ] Loop modes work as expected
- [ ] Picture-in-Picture functions correctly
- [ ] Resume playback at correct position

#### Library Features
- [ ] Videos load correctly
- [ ] Search filters videos accurately
- [ ] Sort options work correctly
- [ ] Favorites toggle works
- [ ] Bulk select and delete works
- [ ] Swipe actions function correctly
- [ ] Video info modal displays correctly
- [ ] Folder filtering works
- [ ] Grid/List view switching works

#### Playlist Features
- [ ] Create playlist works
- [ ] Add videos to playlist works
- [ ] Remove videos from playlist works
- [ ] Reorder videos in playlist works
- [ ] Smart playlists populate correctly
- [ ] Export playlist to M3U works
- [ ] Import playlist from M3U works
- [ ] Playlist shuffle works
- [ ] Playlist repeat modes work

#### Settings
- [ ] All settings save correctly
- [ ] Theme changes apply immediately
- [ ] Color scheme changes work
- [ ] Gesture settings apply correctly
- [ ] Privacy settings work as expected
- [ ] Import/export settings works

#### UI/UX
- [ ] Glassmorphism effects render correctly
- [ ] Animations are smooth
- [ ] Haptic feedback works
- [ ] Loading states display correctly
- [ ] Empty states show helpful messages
- [ ] Error dialogs display and allow retry

#### Accessibility
- [ ] TalkBack reads all elements
- [ ] High contrast mode improves visibility
- [ ] Large text mode scales correctly
- [ ] Keyboard navigation works
- [ ] Focus indicators are visible

### Device Testing Matrix

Test on various device types:

| Device Type | Screen Size | Android Version | Status |
|------------|-------------|----------------|--------|
| Phone | Small (5.5") | Android 8 | ✅ |
| Phone | Medium (6.1") | Android 12 | ✅ |
| Phone | Large (6.7") | Android 14 | ✅ |
| Tablet | 10" | Android 13 | ⏳ |
| Foldable | Variable | Android 14 | ⏳ |

### Edge Cases

- [ ] No videos in library
- [ ] Very large video library (1000+ videos)
- [ ] Very long video (2+ hours)
- [ ] Corrupted video file
- [ ] Missing thumbnail
- [ ] No internet connection (for streaming)
- [ ] Low storage space
- [ ] Background playback
- [ ] App killed during playback
- [ ] Device rotation during playback
- [ ] Headphone connect/disconnect
- [ ] Bluetooth audio device connection

## 🚀 CI/CD Testing

### GitHub Actions Workflow

The CI/CD pipeline runs automatically on:
- Push to main/develop branches
- Pull requests
- Feature branches

Tests run in parallel:
```yaml
- Lint checks
- Unit tests
- Build debug APK
- Build release APK
- Code quality checks
```

### Pre-commit Hooks

Set up pre-commit hooks:

```bash
# .git/hooks/pre-commit
#!/bin/sh

echo "Running pre-commit checks..."

# Run lint
./gradlew lintDebug
if [ $? -ne 0 ]; then
    echo "Lint failed. Please fix errors."
    exit 1
fi

# Run unit tests
./gradlew testDebugUnitTest
if [ $? -ne 0 ]; then
    echo "Tests failed. Please fix tests."
    exit 1
fi

echo "All checks passed!"
```

## 📊 Test Reports

### Viewing Test Results

After running tests, view reports at:

**Unit Test Results:**
```
app/build/reports/tests/testDebugUnitTest/index.html
```

**Code Coverage:**
```
app/build/reports/jacoco/jacocoTestReport/html/index.html
```

**Lint Results:**
```
app/build/reports/lint-results-debug.html
```

**UI Test Results:**
```
app/build/reports/androidTests/connected/index.html
```

## 🎓 Best Practices

### Test Naming
```kotlin
// Good
@Test
fun `given empty playlist when adding video then playlist contains video`()

// Bad
@Test
fun test1()
```

### Test Structure (Arrange-Act-Assert)
```kotlin
@Test
fun exampleTest() {
    // Arrange: Set up test data
    val expected = "result"
    
    // Act: Perform action
    val actual = performAction()
    
    // Assert: Verify result
    assertEquals(expected, actual)
}
```

### Mock Only External Dependencies
```kotlin
// Good: Mock repository (external dependency)
private val mockRepository = mockk<VideoRepository>()

// Bad: Mock domain model (internal)
private val mockVideo = mockk<Video>() // Use real instance instead
```

### Use Test Doubles Appropriately
- **Mock**: For verifying interactions
- **Stub**: For providing canned answers
- **Fake**: For lightweight implementations
- **Spy**: For partial mocking

## 📚 Resources

- [Android Testing Fundamentals](https://developer.android.com/training/testing/fundamentals)
- [Compose Testing](https://developer.android.com/jetpack/compose/testing)
- [MockK Documentation](https://mockk.io/)
- [Turbine for Flow Testing](https://github.com/cashapp/turbine)

---

Happy Testing! 🧪✨
