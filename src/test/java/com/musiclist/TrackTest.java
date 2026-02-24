package com.musiclist;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrackTest {

    // -------------------------------------------------------------------------
    // Construction – valid
    // -------------------------------------------------------------------------

    @Test
    void testValidConstruction() {
        Track track = new Track("Bohemian Rhapsody", "Queen", 354);
        assertEquals("Bohemian Rhapsody", track.getTitle());
        assertEquals("Queen", track.getArtist());
        assertEquals(354, track.getDurationInSeconds());
    }

    // -------------------------------------------------------------------------
    // Construction – invalid title
    // -------------------------------------------------------------------------

    @Test
    void testNullTitleThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Track(null, "Queen", 354));
    }

    @Test
    void testEmptyTitleThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Track("", "Queen", 354));
    }

    // -------------------------------------------------------------------------
    // Construction – invalid artist
    // -------------------------------------------------------------------------

    @Test
    void testNullArtistThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Track("Bohemian Rhapsody", null, 354));
    }

    @Test
    void testEmptyArtistThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Track("Bohemian Rhapsody", "", 354));
    }

    // -------------------------------------------------------------------------
    // Construction – invalid duration
    // -------------------------------------------------------------------------

    @Test
    void testZeroDurationThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Track("Bohemian Rhapsody", "Queen", 0));
    }

    @Test
    void testNegativeDurationThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Track("Bohemian Rhapsody", "Queen", -1));
    }

    // -------------------------------------------------------------------------
    // Getters
    // -------------------------------------------------------------------------

    @Test
    void testGetters() {
        Track track = new Track("Yesterday", "The Beatles", 125);
        assertEquals("Yesterday", track.getTitle());
        assertEquals("The Beatles", track.getArtist());
        assertEquals(125, track.getDurationInSeconds());
    }

    // -------------------------------------------------------------------------
    // getFormattedDuration
    // -------------------------------------------------------------------------

    @Test
    void testFormattedDuration_65seconds() {
        Track track = new Track("Song", "Artist", 65);
        assertEquals("01:05", track.getFormattedDuration());
    }

    @Test
    void testFormattedDuration_3600seconds() {
        // 3600 seconds = 60 minutes 0 seconds → "60:00"
        Track track = new Track("Song", "Artist", 3600);
        assertEquals("60:00", track.getFormattedDuration());
    }

    @Test
    void testFormattedDuration_5seconds() {
        Track track = new Track("Song", "Artist", 5);
        assertEquals("00:05", track.getFormattedDuration());
    }

    @Test
    void testFormattedDuration_exactMinutes() {
        Track track = new Track("Song", "Artist", 120);
        assertEquals("02:00", track.getFormattedDuration());
    }

    // -------------------------------------------------------------------------
    // equals and hashCode
    // -------------------------------------------------------------------------

    @Test
    void testEqualsSameObject() {
        Track track = new Track("Imagine", "John Lennon", 187);
        assertEquals(track, track);
    }

    @Test
    void testEqualsIdenticalTracks() {
        Track t1 = new Track("Imagine", "John Lennon", 187);
        Track t2 = new Track("Imagine", "John Lennon", 187);
        assertEquals(t1, t2);
    }

    @Test
    void testEqualsSameTitleArtistDifferentDuration() {
        // equals is based only on title and artist
        Track t1 = new Track("Imagine", "John Lennon", 187);
        Track t2 = new Track("Imagine", "John Lennon", 200);
        assertEquals(t1, t2);
    }

    @Test
    void testEqualsDifferentTitle() {
        Track t1 = new Track("Imagine", "John Lennon", 187);
        Track t2 = new Track("Other Song", "John Lennon", 187);
        assertNotEquals(t1, t2);
    }

    @Test
    void testEqualsDifferentArtist() {
        Track t1 = new Track("Imagine", "John Lennon", 187);
        Track t2 = new Track("Imagine", "Other Artist", 187);
        assertNotEquals(t1, t2);
    }

    @Test
    void testEqualsNull() {
        Track track = new Track("Imagine", "John Lennon", 187);
        assertNotEquals(null, track);
    }

    @Test
    void testEqualsDifferentClass() {
        Track track = new Track("Imagine", "John Lennon", 187);
        assertNotEquals("not a track", track);
    }

    @Test
    void testHashCodeConsistency() {
        Track t1 = new Track("Imagine", "John Lennon", 187);
        Track t2 = new Track("Imagine", "John Lennon", 200);
        assertEquals(t1.hashCode(), t2.hashCode());
    }

    @Test
    void testHashCodeDifferentTracks() {
        Track t1 = new Track("Imagine", "John Lennon", 187);
        Track t2 = new Track("Yesterday", "The Beatles", 125);
        assertNotEquals(t1.hashCode(), t2.hashCode());
    }

    // -------------------------------------------------------------------------
    // toString
    // -------------------------------------------------------------------------

    @Test
    void testToString() {
        Track track = new Track("Imagine", "John Lennon", 187);
        String result = track.toString();
        assertTrue(result.contains("Imagine"));
        assertTrue(result.contains("John Lennon"));
        assertTrue(result.contains("03:07"));
    }
}
