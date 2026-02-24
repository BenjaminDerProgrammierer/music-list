package com.musiclist;

import java.util.Objects;

/**
 * Represents a single music track with a title, artist and duration.
 */
public class Track {

    private final String title;
    private final String artist;
    private final int durationInSeconds;

    /**
     * Creates a new Track.
     *
     * @param title             the track title (must not be null or empty)
     * @param artist            the artist name (must not be null or empty)
     * @param durationInSeconds the duration in seconds (must be positive)
     * @throws IllegalArgumentException if any argument is invalid
     */
    public Track(String title, String artist, int durationInSeconds) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Title must not be null or empty");
        }
        if (artist == null || artist.isEmpty()) {
            throw new IllegalArgumentException("Artist must not be null or empty");
        }
        if (durationInSeconds <= 0) {
            throw new IllegalArgumentException("Duration must be positive");
        }
        this.title = title;
        this.artist = artist;
        this.durationInSeconds = durationInSeconds;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public int getDurationInSeconds() {
        return durationInSeconds;
    }

    /**
     * Returns the duration formatted as "mm:ss".
     *
     * @return formatted duration string
     */
    public String getFormattedDuration() {
        int minutes = durationInSeconds / 60;
        int seconds = durationInSeconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Track)) return false;
        Track other = (Track) obj;
        return Objects.equals(title, other.title) && Objects.equals(artist, other.artist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, artist);
    }

    @Override
    public String toString() {
        return String.format("Track{title='%s', artist='%s', duration=%s}",
                title, artist, getFormattedDuration());
    }
}
