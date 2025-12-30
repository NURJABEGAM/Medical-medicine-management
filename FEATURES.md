# AX Player - Feature Documentation

## 🎬 Advanced Player Features

### Playback Controls
- **Speed Control**: Adjust playback speed from 0.25x to 2.0x with smooth transitions
- **Audio Track Selection**: Choose from multiple audio tracks with language labels
- **Subtitle Management**: Add, remove, and customize subtitle tracks
- **Aspect Ratio Control**: Fit, Fill, Zoom, 4:3, 16:9, 21:9, Custom
- **Frame Navigation**: Step through video frame-by-frame
- **Video Bookmarks**: Mark and navigate to important moments
- **Sleep Timer**: Auto-pause after 5, 10, 15, 30, or 60 minutes
- **Loop Modes**: None, Loop One, Loop All

### Video Effects & Filters
- **Real-time Filters**: Adjust brightness, contrast, saturation, and hue
- **10-Band Equalizer**: Professional audio enhancement with presets
  - Normal, Bass Boost, Treble Boost, Vocal, Rock, Jazz, Classical, Pop, Flat
- **Quick Reset**: Reset all effects to default with one tap
- **Live Preview**: See changes in real-time

### Advanced Playback
- **Picture-in-Picture**: Floating window with custom sizing
- **Smart Resume**: Resume playback at exact timestamp even after app restart
- **Playback Queue**: Manage upcoming videos
- **Gesture Customization**: Remap all gesture actions
- **Double-Tap Actions**: Configurable quick actions

## 📚 Library Management

### Smart Organization
- **Auto-Grouping**: By folder, date added, duration
- **Advanced Search**: Full-text search across all metadata
- **Multiple Sorting**: Name, Date, Size, Duration (ascending/descending)
- **Advanced Filtering**:
  - Duration range
  - Date range
  - Size range
  - Quality (4K, 1080p, 720p, etc.)
  - Folder-based filtering

### Bulk Operations
- **Multi-Select**: Select multiple videos at once
- **Batch Actions**: Delete, add to playlist, favorite
- **Swipe Actions**: Quick favorite, delete, or share

### Video Information
- **Detailed Info Modal**:
  - Resolution and quality badge
  - Video codec and audio codec
  - Bitrate and FPS
  - File size and duration
  - Creation and modification dates
  - Storage path

### Performance
- **Pagination**: Smooth infinite scroll for large libraries
- **Thumbnail Caching**: 3-tier caching with Coil
- **Loading Skeletons**: Beautiful placeholder animations
- **Empty States**: Helpful guidance when library is empty

## 📝 Subtitle System

### Format Support
- SRT (SubRip)
- VTT (WebVTT)
- ASS (Advanced SubStation)
- SSA (SubStation Alpha)
- SUB (MicroDVD)
- Embedded subtitles (MKV, MP4, WebM)

### Subtitle Features
- **Auto-Download**: Search and download from OpenSubtitles API
- **Multiple Tracks**: Support for multiple subtitle tracks simultaneously
- **Timing Adjustment**: Shift subtitle timing ±5 seconds in 100ms increments
- **External File Support**: Load external subtitle files

### Customization Panel
- **Font Options**:
  - Font family selection (10+ options)
  - Font size (8pt to 32pt)
  - Letter spacing
  - Line spacing
- **Colors & Effects**:
  - Text color picker
  - Outline/border color and width
  - Background color and opacity
  - Shadow effects (offset, blur, color)
- **Positioning**:
  - Position (top, center, bottom)
  - Alignment (left, center, right)
- **Live Preview**: See changes instantly

## 📋 Playlist System

### Playlist Types
- **Custom Playlists**: Create your own playlists with drag-to-reorder
- **Smart Playlists**:
  - Recently Added
  - Recently Played
  - Favorites
  - Unwatched
  - Most Played
  - Long Videos (>1 hour)
  - Short Videos (<10 minutes)

### Playlist Management
- **Create & Edit**: Full CRUD operations
- **Drag to Reorder**: Intuitive video reordering
- **Shuffle Mode**: Randomize playback order
- **Repeat Modes**: Off, All, One

### Import/Export
- **M3U/M3U8 Support**: Export to standard playlist formats
- **Import from URL**: Load playlists from web URLs
- **Share Playlists**: Share via multiple channels
- **Playlist Metadata**: Name, description, thumbnail

## 🎨 UI/UX Features

### Accessibility
- **TalkBack Support**: Full screen reader compatibility
- **High Contrast Mode**: Enhanced visibility
- **Large Text Mode**: Configurable text sizes
- **Reduced Motion**: Option to disable animations
- **Keyboard Navigation**: Complete keyboard control
- **Haptic Feedback**: Customizable intensity

### Visual Design
- **Glassmorphism UI**: Beautiful frosted glass effects throughout
- **Material You**: Dynamic color theming (Android 12+)
- **Multiple Color Schemes**: Cyan, Purple, Red, Blue, Green, Orange, Pink
- **OLED Optimization**: True black theme for OLED screens
- **Adaptive Layouts**: Optimized for phones, tablets, foldables

### Animations
- **Smooth Transitions**: Professional screen transitions
- **Content Animations**: Staggered and cascading effects
- **Loading States**: Skeleton screens and shimmer effects
- **Gesture Feedback**: Visual and haptic responses

### Themes
- **Light/Dark/Auto**: System-aware theming
- **Custom Colors**: Choose your preferred color scheme
- **UI Layouts**: Compact, Normal, Spacious
- **Status Bar Adaptation**: Colors match app theme

## 🌐 Network & Streaming

### Streaming Protocols
- **HTTP/HTTPS**: Standard web video streaming
- **HLS**: HTTP Live Streaming
- **DASH**: Dynamic Adaptive Streaming
- **M3U8 Playlists**: Internet radio and TV

### Network Features
- **Adaptive Bitrate**: Automatic quality adjustment
- **Manual Quality Selection**: Choose your preferred quality
- **Stream Caching**: Save bandwidth with smart caching
- **Connection Monitoring**: Detect and handle connection changes
- **Bandwidth-Aware**: Optimize for your connection speed

## ⚙️ Settings System

### Playback Settings
- Default playback speed
- Auto-resume toggle
- Hardware acceleration
- Preferred audio/subtitle language
- Auto-load subtitles
- Buffer size configuration
- Loop mode
- Sleep timer

### Display Settings
- Theme selector (Light/Dark/Auto)
- Color scheme picker
- Font selection
- OLED optimization
- Dynamic theming (Android 12+)
- UI layout (Compact/Normal/Spacious)
- Status bar behavior
- Fullscreen mode options

### Gesture & Controls
- Enable/disable gesture controls
- Gesture sensitivity slider
- Double-tap action mapper
- Long-press action mapper
- Swipe action customization
- Haptic feedback intensity

### Privacy & Security
- Analytics opt-in/out
- Crash reporting opt-in/out
- Clear watch history
- Clear search history
- Data deletion
- Export/import settings

## 🔐 Security Features

### Data Protection
- **Encrypted DataStore**: Secure preference storage
- **AndroidKeyStore**: Hardware-backed key storage
- **Scoped Storage**: Privacy-compliant file access
- **HTTPS Only**: Secure network communication

### Code Security
- **R8 Obfuscation**: Minified and obfuscated release builds
- **ProGuard Rules**: Optimized code protection
- **Secure Logging**: No sensitive data in logs
- **Input Validation**: All user inputs validated

## 📊 Analytics & Logging

### Firebase Analytics
- Video playback events
- Feature usage tracking
- Performance monitoring
- Crash reporting with context
- Custom event tracking

### Local Analytics
- Watch history with timestamps
- Most watched videos
- Total playback duration
- Feature usage statistics
- Storage usage tracking

## 🚀 Performance Optimizations

### Caching
- **3-Tier Image Cache**: Memory, Disk, Network (Coil)
- **Database Optimization**: Indexed queries, connection pooling
- **Lazy Loading**: On-demand resource loading
- **Pagination**: Efficient large list handling

### Memory Management
- **Lifecycle Awareness**: Proper component lifecycle handling
- **Memory Leak Prevention**: WeakReferences and proper cleanup
- **Image Optimization**: Resolution-appropriate thumbnail loading
- **Cache Limits**: Configurable cache size limits

### Compose Optimization
- **Remember**: Proper state preservation
- **DerivedStateOf**: Computed state optimization
- **Key Management**: Stable keys for lists
- **Minimal Recomposition**: Efficient state updates

## 🧪 Testing

### Test Coverage
- **Unit Tests**: ViewModels, UseCases, Repositories
- **Integration Tests**: Database, Preferences, End-to-end flows
- **UI Tests**: Compose screens, Navigation, Gestures
- **Performance Tests**: Memory, Battery, Network efficiency

## 🌍 Internationalization

### Supported Languages
- English (primary)
- Hindi
- Bengali
- Tamil
- Telugu
- Marathi
- Gujarati
- Kannada
- Malayalam

### Localization Features
- **All strings externalized**: Easy translation workflow
- **Pluralization support**: Proper grammar for all languages
- **RTL support ready**: Right-to-left language support
- **Locale-specific formatting**: Dates, times, numbers

## 📱 System Integration

### Android Features
- **App Shortcuts**: Quick access to common actions
- **Share Targets**: Receive videos from other apps
- **File Associations**: Default handler for video files
- **Media Controls**: System media session integration
- **Notification Actions**: Control playback from notification shade

## 🎯 Premium Features (Future)

### In-App Purchases
- Ad-free experience
- Cloud backup
- Advanced themes
- Priority support
- Early access features

## 📖 Version History

### Version 1.0.0
- Initial release with all core features
- Advanced player controls
- Comprehensive subtitle system
- Playlist management
- Beautiful glassmorphism UI
- Multi-language support
- Firebase integration
- Production-ready performance
