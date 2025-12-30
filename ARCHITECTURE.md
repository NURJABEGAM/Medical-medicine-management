# AX Player - Architecture Documentation

## 🏗️ Architecture Overview

AX Player follows **Clean Architecture** principles with clear separation of concerns across three main layers: Domain, Data, and Presentation. The app uses **MVVM** (Model-View-ViewModel) pattern with **Jetpack Compose** for reactive UI.

## 📐 Layer Structure

```
app/src/main/java/com/axplayer/
├── domain/              # Business Logic Layer
│   ├── entity/          # Domain models
│   ├── repository/      # Repository interfaces
│   └── usecase/         # Business use cases
├── data/                # Data Layer
│   ├── db/              # Room database (entities, DAOs)
│   ├── datasource/      # Data sources
│   ├── repository/      # Repository implementations
│   └── preferences/     # DataStore preferences
├── presentation/        # Presentation Layer
│   ├── screens/         # UI screens
│   ├── components/      # Reusable UI components
│   ├── theme/           # Material 3 theming
│   └── navigation/      # Navigation graph
└── di/                  # Dependency Injection modules
```

## 🎯 Domain Layer

The domain layer contains the core business logic and is completely independent of Android framework and external dependencies.

### Entities

**Core Entities:**
- `Video`: Video metadata and computed properties
- `Playlist`: Playlist with video references
- `Subtitle`: Subtitle track information
- `VideoBookmark`: Saved video positions
- `PlaybackState`: Current playback information

**Configuration Entities:**
- `AppSettings`: All app settings grouped
- `PlaybackSettings`: Playback-specific settings
- `DisplaySettings`: UI and theme settings
- `GestureSettings`: Gesture customization
- `SubtitleSettings`: Subtitle appearance
- `NotificationSettings`: Notification preferences
- `PrivacySettings`: Privacy and analytics settings

**Enums:**
- `PlaybackSpeed`: 0.25x to 2.0x speed options
- `AspectRatio`: Video aspect ratio modes
- `LoopMode`: Repeat modes
- `SubtitleFormat`: Supported subtitle formats
- `SortOrder`: Video sorting options
- `FilterType`: Video filtering options
- `Theme`: Light/Dark/Auto themes
- `ColorScheme`: App color schemes

### Repositories (Interfaces)

```kotlin
interface VideoRepository {
    fun getVideos(): Flow<List<Video>>
    suspend fun getVideoById(id: String): Video?
    suspend fun updateVideo(video: Video)
    // ... more methods
}

interface PlaylistRepository {
    fun getPlaylists(): Flow<List<Playlist>>
    suspend fun insertPlaylist(playlist: Playlist)
    suspend fun exportPlaylist(playlistId: String, format: String): String
    // ... more methods
}

interface SubtitleRepository {
    fun getSubtitlesForVideo(videoId: String): Flow<List<Subtitle>>
    suspend fun downloadSubtitle(videoId: String, language: String): Subtitle
    // ... more methods
}

interface BookmarkRepository {
    fun getBookmarksForVideo(videoId: String): Flow<List<VideoBookmark>>
    suspend fun insertBookmark(bookmark: VideoBookmark)
    // ... more methods
}
```

### Use Cases

Each use case represents a single business operation:

**Video Use Cases:**
- `GetVideosUseCase`: Fetch all videos
- `GetVideoByIdUseCase`: Get single video
- `UpdateVideoUseCase`: Update video metadata
- `ToggleFavoriteUseCase`: Toggle favorite status
- `PlayVideoUseCase`: Start video playback
- `SavePlaybackStateUseCase`: Save playback position

**Playlist Use Cases:**
- `GetPlaylistsUseCase`: Fetch all playlists
- `CreatePlaylistUseCase`: Create new playlist
- `AddVideoToPlaylistUseCase`: Add video to playlist
- `RemoveVideoFromPlaylistUseCase`: Remove video
- `ReorderPlaylistVideosUseCase`: Reorder playlist
- `ExportPlaylistUseCase`: Export to M3U/M3U8
- `ImportPlaylistUseCase`: Import from file
- `GetSmartPlaylistVideosUseCase`: Get smart playlist content

**Subtitle Use Cases:**
- `GetSubtitlesForVideoUseCase`: Get video subtitles
- `AddSubtitleUseCase`: Add subtitle track
- `RemoveSubtitleUseCase`: Remove subtitle
- `DownloadSubtitleUseCase`: Download from API
- `GetSubtitleSettingsUseCase`: Get settings
- `UpdateSubtitleSettingsUseCase`: Update settings

**Bookmark Use Cases:**
- `GetBookmarksForVideoUseCase`: Get video bookmarks
- `AddBookmarkUseCase`: Add bookmark
- `UpdateBookmarkUseCase`: Update bookmark
- `DeleteBookmarkUseCase`: Delete bookmark

## 💾 Data Layer

The data layer implements repository interfaces and manages data sources.

### Room Database

**Database Schema Version 2:**

```kotlin
@Database(
    entities = [
        VideoEntity::class,
        PlaylistEntity::class,
        SubtitleEntity::class,
        BookmarkEntity::class
    ],
    version = 2
)
```

**DAOs:**
- `VideoDao`: CRUD operations for videos
- `PlaylistDao`: Playlist management
- `SubtitleDao`: Subtitle track management
- `BookmarkDao`: Bookmark operations

**Indices:**
- Videos: Indexed by `dateModified`, `isFavorite`, `lastPlayedTime`
- Playlists: Indexed by `updatedAt`, `isFavorite`
- Subtitles: Indexed by `videoId`, `addedAt`
- Bookmarks: Indexed by `videoId`, `position`

### DataStore Preferences

Encrypted preference storage using Jetpack DataStore:

```kotlin
@Singleton
class PreferencesManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    val userPreferencesFlow: Flow<UserPreferences>
    val subtitleSettings: Flow<SubtitleSettings>
    
    suspend fun updateXXX(value: Type)
}
```

**Stored Preferences:**
- Playback settings (speed, resume, hardware acceleration)
- Display settings (theme, colors, layout)
- Subtitle customization (font, colors, positioning)
- Gesture settings (sensitivity, actions)
- Privacy settings (analytics, crash reporting)

### Repository Implementations

```kotlin
@Singleton
class VideoRepositoryImpl @Inject constructor(
    private val videoDao: VideoDao,
    private val contentResolver: ContentResolver
) : VideoRepository {
    // Implementation with Room and ContentProvider
}
```

**Features:**
- Flow-based reactive data
- Error handling with Result types
- Coroutine-based async operations
- Efficient querying with pagination
- Smart caching strategies

## 🎨 Presentation Layer

The presentation layer uses Jetpack Compose for declarative UI with MVVM pattern.

### ViewModels

ViewModels manage UI state and handle user interactions:

```kotlin
@HiltViewModel
class PlayerViewModel @Inject constructor(
    private val playVideoUseCase: PlayVideoUseCase,
    private val getSubtitlesUseCase: GetSubtitlesForVideoUseCase,
    private val getBookmarksUseCase: GetBookmarksForVideoUseCase,
    // ... more use cases
) : ViewModel() {
    
    private val _state = MutableStateFlow(PlayerState())
    val state: StateFlow<PlayerState> = _state.asStateFlow()
    
    fun onEvent(event: PlayerEvent) {
        // Handle events
    }
}
```

**ViewModels:**
- `LibraryViewModel`: Video library management
- `PlayerViewModel`: Video playback control
- `SettingsViewModel`: App settings
- `PlaylistViewModel`: Playlist management (planned)

### State Management

**State Pattern:**
```kotlin
data class PlayerState(
    val video: Video? = null,
    val playbackState: PlaybackState = PlaybackState(),
    val playbackSpeed: PlaybackSpeed = PlaybackSpeed.NORMAL,
    val subtitles: List<Subtitle> = emptyList(),
    val selectedSubtitle: Subtitle? = null,
    val bookmarks: List<VideoBookmark> = emptyList(),
    // ... more state properties
)

sealed interface PlayerEvent {
    data class SetSpeed(val speed: PlaybackSpeed) : PlayerEvent
    data class SelectSubtitle(val subtitle: Subtitle) : PlayerEvent
    data class AddBookmark(val title: String) : PlayerEvent
    // ... more events
}
```

**Flow-based Reactivity:**
- `StateFlow` for UI state
- `Flow` for data streams
- `collectAsStateWithLifecycle()` in Compose

### Compose Screens

**Screen Structure:**
```kotlin
@Composable
fun LibraryScreen(
    viewModel: LibraryViewModel = hiltViewModel(),
    onNavigateToPlayer: (Video) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    
    LibraryContent(
        state = state,
        onEvent = viewModel::onEvent
    )
}

@Composable
private fun LibraryContent(
    state: LibraryState,
    onEvent: (LibraryEvent) -> Unit
) {
    // UI composition
}
```

**Glassmorphism Components:**
- `GlassCard`: Frosted glass card effect
- `GlassButton`: Glass-styled buttons
- `GlassIconButton`: Icon buttons with glass effect
- `GlassSurface`: Glass background surface
- `GlassProgressBar`: Styled progress indicator

### Navigation

Compose Navigation with type-safe routing:

```kotlin
sealed class Screen(val route: String) {
    object Library : Screen("library")
    object Player : Screen("player/{videoId}")
    object Settings : Screen("settings")
    object Playlists : Screen("playlists")
}

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController, startDestination = Screen.Library.route) {
        composable(Screen.Library.route) { LibraryScreen(...) }
        composable(Screen.Player.route) { PlayerScreen(...) }
        composable(Screen.Settings.route) { SettingsScreen(...) }
    }
}
```

## 🔌 Dependency Injection

Using Hilt (Dagger) for dependency injection:

### Modules

**AppModule:**
```kotlin
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context: Context): Context
    
    @Provides
    fun provideContentResolver(@ApplicationContext context: Context): ContentResolver
}
```

**DatabaseModule:**
```kotlin
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase
    
    @Provides
    fun provideVideoDao(database: AppDatabase): VideoDao
    
    @Provides
    fun providePlaylistDao(database: AppDatabase): PlaylistDao
    
    // ... more DAO providers
}
```

**RepositoryModule:**
```kotlin
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindVideoRepository(impl: VideoRepositoryImpl): VideoRepository
    
    // ... more bindings
}
```

## 🔄 Data Flow

### Unidirectional Data Flow

```
User Action → Event → ViewModel → Use Case → Repository → Data Source
     ↓                                                         ↓
     ←────────────── State Update ←───────────────────────────┘
```

**Example Flow:**
1. User taps "Add to Favorites" button
2. UI sends `ToggleFavorite(videoId)` event to ViewModel
3. ViewModel calls `ToggleFavoriteUseCase`
4. Use case calls `VideoRepository.toggleFavorite()`
5. Repository updates Room database
6. Database emits new data via Flow
7. ViewModel updates state
8. UI recomposes with new state

### Error Handling

```kotlin
sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
    object Loading : Result<Nothing>()
}

// Usage in ViewModel
viewModelScope.launch {
    _state.update { it.copy(isLoading = true) }
    when (val result = useCase()) {
        is Result.Success -> {
            _state.update { it.copy(
                data = result.data,
                isLoading = false
            )}
        }
        is Result.Error -> {
            _state.update { it.copy(
                error = result.exception.message,
                isLoading = false
            )}
        }
    }
}
```

## 🎯 Design Patterns

### Repository Pattern
Abstracts data sources, allowing easy switching between local/remote data.

### Use Case Pattern
Single responsibility principle - one use case per business operation.

### Observer Pattern
Flow-based reactive data streams for automatic UI updates.

### Factory Pattern
Hilt modules use factory pattern for object creation.

### State Pattern
Immutable state objects with copy functions for updates.

### Strategy Pattern
Different strategies for video loading, caching, playback.

## 🚀 Performance Optimizations

### Database
- Indexed columns for fast queries
- Connection pooling
- Pagination with LIMIT/OFFSET
- Efficient joins and queries

### Compose
- `remember` for expensive computations
- `derivedStateOf` for computed state
- Stable keys for LazyLists
- Minimal state hoisting

### Memory
- WeakReferences for callbacks
- Proper lifecycle handling
- Image size optimization with Coil
- Cache size limits

### Network
- OkHttp cache interceptor
- Connection pooling
- Request batching
- Timeout management

## 🧪 Testing Strategy

### Unit Tests
Test ViewModels and Use Cases in isolation with mocked dependencies:

```kotlin
@Test
fun `toggle favorite updates state`() = runTest {
    val viewModel = LibraryViewModel(mockUseCase)
    viewModel.onEvent(LibraryEvent.ToggleFavorite(videoId))
    
    val state = viewModel.state.value
    assertTrue(state.videos.first().isFavorite)
}
```

### Integration Tests
Test repository implementations with real Room database:

```kotlin
@Test
fun `playlist repository saves and retrieves playlist`() = runTest {
    val playlist = Playlist(...)
    repository.insertPlaylist(playlist)
    
    val result = repository.getPlaylistById(playlist.id)
    assertEquals(playlist, result)
}
```

### UI Tests
Test Compose screens with Compose Testing:

```kotlin
@Test
fun `clicking video navigates to player`() {
    composeTestRule.setContent {
        LibraryScreen(onNavigateToPlayer = { navigated = true })
    }
    
    composeTestRule.onNodeWithTag("video_item").performClick()
    assertTrue(navigated)
}
```

## 📊 Analytics Integration

### Firebase Analytics
```kotlin
firebaseAnalytics.logEvent("video_played") {
    param("video_id", videoId)
    param("duration", duration)
    param("quality", quality)
}
```

### Crashlytics
```kotlin
try {
    // Risky operation
} catch (e: Exception) {
    FirebaseCrashlytics.getInstance().apply {
        setCustomKey("video_id", videoId)
        setCustomKey("action", "playback")
        recordException(e)
    }
}
```

## 🔐 Security

### Data Encryption
- DataStore with EncryptedSharedPreferences
- AndroidKeyStore for key management
- No hardcoded secrets

### Code Obfuscation
- R8 minification in release
- ProGuard rules for library preservation
- String encryption for sensitive data

### Network Security
- HTTPS only (no cleartext traffic)
- Certificate pinning (optional)
- Network security config

## 📚 Dependencies

### Core
- Kotlin 2.1.0
- Jetpack Compose 2025.01.00
- Material 3

### Architecture
- Hilt 2.53.1
- Room 2.6.1
- DataStore
- Navigation Compose

### Media
- Media3 (ExoPlayer) 1.5.0

### Network
- OkHttp
- Retrofit
- Gson

### Image
- Coil 2.7.0

### Testing
- JUnit
- MockK
- Turbine
- Compose Testing

### Firebase
- Analytics
- Crashlytics
- Performance Monitoring

## 🔄 Migration Strategy

### Database Migrations
```kotlin
val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Add new tables
        database.execSQL("CREATE TABLE playlists (...)")
        database.execSQL("CREATE TABLE subtitles (...)")
        database.execSQL("CREATE TABLE bookmarks (...)")
        
        // Add new columns to videos
        database.execSQL("ALTER TABLE videos ADD COLUMN codec TEXT")
        database.execSQL("ALTER TABLE videos ADD COLUMN playCount INTEGER")
    }
}
```

## 🎓 Best Practices

### Code Style
- Kotlin coding conventions
- Meaningful variable names
- No magic numbers (use constants)
- Proper error handling
- Comprehensive comments for complex logic

### Git Workflow
- Feature branches (`feat-xxx`)
- Descriptive commit messages
- Pull requests with reviews
- CI/CD integration

### Performance
- Lazy initialization
- Avoid memory leaks
- Efficient algorithms
- Profile regularly

### Security
- Validate all inputs
- Use encryption for sensitive data
- Follow Android security guidelines
- Regular security audits
