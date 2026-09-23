# Black Pulse

### A free and open-source music player for Android

Black Pulse is a fork of [Metrolist](https://github.com/MetrolistGroup/Metrolist), which is built on [InnerTune](https://github.com/z-huang/InnerTune).

---

## Features

#### Playback
- Stream any song or video from YouTube Music
- Background playback
- Download & cache for offline use
- Skip silence
- Sleep timer

#### Audio
- Audio normalization
- Tempo & pitch control
- Equalizer
- Crossfade

#### Lyrics & Discovery
- Live synced lyrics
- AI-powered lyrics translation
- Personalized quick picks
- Search songs, albums, artists, videos, and playlists

#### Library
- Full library management
- Local playlists
- Import playlists
- Reorder songs in playlist or queue

#### Interface
- Light / Dark / Black / Dynamic theme modes
- Dynamic color + 19 preset color palettes
- Built with Material 3

---

## Download

Grab the latest APK from the [Releases page](https://github.com/callix-tamilvanan/blackpulse/releases).

---

## Build from source

You need Java 17 and the Android SDK.

```bash
git clone https://github.com/callix-tamilvanan/blackpulse.git
cd blackpulse
export JAVA_HOME=$(/usr/libexec/java_home -v 17)
./gradlew assembleFossDebug
The APK will be at app/build/outputs/apk/foss/debug/.

Credits
Black Pulse stands on the shoulders of incredible open-source work.

Based on:

Metrolist by Mo Agamy and contributors

InnerTune by Zion Huang (Hong) and contributors

Libraries & integrations used:

Better Lyrics — time-synced lyrics

metroserver — listen-together backend

MusicRecognizer — music recognition

zemer-cipher — YouTube cipher deobfuscation

Blacksmith — GitHub Actions runners

Thank you to the entire open-source community.

License
Licensed under the GNU General Public License v3.0. See LICENSE for details.

This means you are free to use, modify, and redistribute Black Pulse, as long as you keep the same license and credit the original authors.

Disclaimer
This project is not affiliated with, funded, authorized, endorsed by, or in any way associated with YouTube, Google LLC, Metrolist Group LLC, or any of their affiliates and subsidiaries.

All trademarks, service marks, and intellectual property rights referenced in this project belong to their respective owners.

Made by Callix Tamilvanan
