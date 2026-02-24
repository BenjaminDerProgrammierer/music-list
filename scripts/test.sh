#!/usr/bin/env bash
# Compile and run all JUnit tests without Maven.
# Usage: ./scripts/test.sh
set -euo pipefail

ROOT="$(cd "$(dirname "$0")/.." && pwd)"
JAR="$ROOT/lib/junit-platform-console-standalone-1.10.1.jar"
OUT_MAIN="$ROOT/out/main"
OUT_TEST="$ROOT/out/test"

mkdir -p "$OUT_MAIN" "$OUT_TEST"

echo "==> Compiling main sources..."
javac -d "$OUT_MAIN" \
  "$ROOT/src/main/java/com/musiclist/Track.java" \
  "$ROOT/src/main/java/com/musiclist/TrackNode.java" \
  "$ROOT/src/main/java/com/musiclist/Playlist.java"

echo "==> Compiling test sources..."
javac -cp "$JAR:$OUT_MAIN" -d "$OUT_TEST" \
  "$ROOT/src/test/java/com/musiclist/TrackTest.java" \
  "$ROOT/src/test/java/com/musiclist/TrackNodeTest.java" \
  "$ROOT/src/test/java/com/musiclist/PlaylistTest.java"

echo "==> Running tests..."
java -jar "$JAR" \
  --class-path "$OUT_MAIN:$OUT_TEST" \
  --scan-class-path
