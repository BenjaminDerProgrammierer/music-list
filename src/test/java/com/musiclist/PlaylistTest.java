package com.musiclist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlaylistTest {

    private Playlist playlist;
    private Track track1;
    private Track track2;
    private Track track3;

    @BeforeEach
    void setUp() {
        playlist = new Playlist("My Playlist");
        track1 = new Track("Bohemian Rhapsody", "Queen", 354);
        track2 = new Track("Imagine", "John Lennon", 187);
        track3 = new Track("Yesterday", "The Beatles", 125);
    }

    // -------------------------------------------------------------------------
    // Construction
    // -------------------------------------------------------------------------

    @Test
    void testConstructionName() {
        assertEquals("My Playlist", playlist.getName());
    }

    @Test
    void testConstructionInitiallyEmpty() {
        assertTrue(playlist.isEmpty());
        assertEquals(0, playlist.size());
    }

    // -------------------------------------------------------------------------
    // addTrack
    // -------------------------------------------------------------------------

    @Test
    void testAddSingleTrack() {
        playlist.addTrack(track1);
        assertEquals(1, playlist.size());
        assertEquals(track1, playlist.getTrack(0));
    }

    @Test
    void testAddMultipleTracks() {
        playlist.addTrack(track1);
        playlist.addTrack(track2);
        playlist.addTrack(track3);
        assertEquals(3, playlist.size());
        assertEquals(track1, playlist.getTrack(0));
        assertEquals(track2, playlist.getTrack(1));
        assertEquals(track3, playlist.getTrack(2));
    }

    @Test
    void testAddNullTrackThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> playlist.addTrack(null));
    }

    // -------------------------------------------------------------------------
    // addTrackAtBeginning
    // -------------------------------------------------------------------------

    @Test
    void testAddTrackAtBeginningToEmptyList() {
        playlist.addTrackAtBeginning(track1);
        assertEquals(1, playlist.size());
        assertEquals(track1, playlist.getTrack(0));
    }

    @Test
    void testAddTrackAtBeginningToNonEmptyList() {
        playlist.addTrack(track1);
        playlist.addTrackAtBeginning(track2);
        assertEquals(2, playlist.size());
        assertEquals(track2, playlist.getTrack(0));
        assertEquals(track1, playlist.getTrack(1));
    }

    // -------------------------------------------------------------------------
    // addTrackAtIndex
    // -------------------------------------------------------------------------

    @Test
    void testAddTrackAtIndexZero() {
        playlist.addTrack(track1);
        playlist.addTrackAtIndex(0, track2);
        assertEquals(track2, playlist.getTrack(0));
        assertEquals(track1, playlist.getTrack(1));
    }

    @Test
    void testAddTrackAtIndexEnd() {
        playlist.addTrack(track1);
        playlist.addTrackAtIndex(1, track2);
        assertEquals(track1, playlist.getTrack(0));
        assertEquals(track2, playlist.getTrack(1));
    }

    @Test
    void testAddTrackAtIndexMiddle() {
        playlist.addTrack(track1);
        playlist.addTrack(track3);
        playlist.addTrackAtIndex(1, track2);
        assertEquals(track1, playlist.getTrack(0));
        assertEquals(track2, playlist.getTrack(1));
        assertEquals(track3, playlist.getTrack(2));
    }

    @Test
    void testAddTrackAtNegativeIndexThrowsException() {
        assertThrows(IndexOutOfBoundsException.class,
                () -> playlist.addTrackAtIndex(-1, track1));
    }

    @Test
    void testAddTrackAtTooLargeIndexThrowsException() {
        playlist.addTrack(track1);
        assertThrows(IndexOutOfBoundsException.class,
                () -> playlist.addTrackAtIndex(5, track2));
    }

    // -------------------------------------------------------------------------
    // removeTrack
    // -------------------------------------------------------------------------

    @Test
    void testRemoveExistingTrack() {
        playlist.addTrack(track1);
        playlist.addTrack(track2);
        assertTrue(playlist.removeTrack(track1));
        assertEquals(1, playlist.size());
        assertEquals(track2, playlist.getTrack(0));
    }

    @Test
    void testRemoveNonExistingTrack() {
        playlist.addTrack(track1);
        assertFalse(playlist.removeTrack(track2));
        assertEquals(1, playlist.size());
    }

    @Test
    void testRemoveTrackFromSingleElementList() {
        playlist.addTrack(track1);
        assertTrue(playlist.removeTrack(track1));
        assertTrue(playlist.isEmpty());
    }

    @Test
    void testRemoveHeadTrack() {
        playlist.addTrack(track1);
        playlist.addTrack(track2);
        playlist.addTrack(track3);
        assertTrue(playlist.removeTrack(track1));
        assertEquals(track2, playlist.getTrack(0));
        assertEquals(2, playlist.size());
    }

    @Test
    void testRemoveNullReturnsFalse() {
        playlist.addTrack(track1);
        assertFalse(playlist.removeTrack(null));
    }

    // -------------------------------------------------------------------------
    // removeTrackAtIndex
    // -------------------------------------------------------------------------

    @Test
    void testRemoveTrackAtIndexZero() {
        playlist.addTrack(track1);
        playlist.addTrack(track2);
        Track removed = playlist.removeTrackAtIndex(0);
        assertEquals(track1, removed);
        assertEquals(1, playlist.size());
        assertEquals(track2, playlist.getTrack(0));
    }

    @Test
    void testRemoveTrackAtLastIndex() {
        playlist.addTrack(track1);
        playlist.addTrack(track2);
        Track removed = playlist.removeTrackAtIndex(1);
        assertEquals(track2, removed);
        assertEquals(1, playlist.size());
    }

    @Test
    void testRemoveTrackAtMiddleIndex() {
        playlist.addTrack(track1);
        playlist.addTrack(track2);
        playlist.addTrack(track3);
        Track removed = playlist.removeTrackAtIndex(1);
        assertEquals(track2, removed);
        assertEquals(2, playlist.size());
        assertEquals(track3, playlist.getTrack(1));
    }

    @Test
    void testRemoveTrackAtNegativeIndexThrowsException() {
        playlist.addTrack(track1);
        assertThrows(IndexOutOfBoundsException.class,
                () -> playlist.removeTrackAtIndex(-1));
    }

    @Test
    void testRemoveTrackAtOutOfBoundsIndexThrowsException() {
        playlist.addTrack(track1);
        assertThrows(IndexOutOfBoundsException.class,
                () -> playlist.removeTrackAtIndex(5));
    }

    @Test
    void testRemoveTrackAtIndexOnEmptyListThrowsException() {
        assertThrows(IndexOutOfBoundsException.class,
                () -> playlist.removeTrackAtIndex(0));
    }

    // -------------------------------------------------------------------------
    // getTrack
    // -------------------------------------------------------------------------

    @Test
    void testGetTrackValidIndex() {
        playlist.addTrack(track1);
        playlist.addTrack(track2);
        assertEquals(track2, playlist.getTrack(1));
    }

    @Test
    void testGetTrackNegativeIndexThrowsException() {
        playlist.addTrack(track1);
        assertThrows(IndexOutOfBoundsException.class,
                () -> playlist.getTrack(-1));
    }

    @Test
    void testGetTrackOutOfBoundsIndexThrowsException() {
        playlist.addTrack(track1);
        assertThrows(IndexOutOfBoundsException.class,
                () -> playlist.getTrack(5));
    }

    @Test
    void testGetTrackOnEmptyListThrowsException() {
        assertThrows(IndexOutOfBoundsException.class,
                () -> playlist.getTrack(0));
    }

    // -------------------------------------------------------------------------
    // contains and indexOf
    // -------------------------------------------------------------------------

    @Test
    void testContainsExistingTrack() {
        playlist.addTrack(track1);
        assertTrue(playlist.contains(track1));
    }

    @Test
    void testContainsNonExistingTrack() {
        playlist.addTrack(track1);
        assertFalse(playlist.contains(track2));
    }

    @Test
    void testContainsOnEmptyList() {
        assertFalse(playlist.contains(track1));
    }

    @Test
    void testIndexOfExistingTrack() {
        playlist.addTrack(track1);
        playlist.addTrack(track2);
        assertEquals(1, playlist.indexOf(track2));
    }

    @Test
    void testIndexOfNonExistingTrack() {
        playlist.addTrack(track1);
        assertEquals(-1, playlist.indexOf(track2));
    }

    @Test
    void testIndexOfOnEmptyList() {
        assertEquals(-1, playlist.indexOf(track1));
    }

    // -------------------------------------------------------------------------
    // size and isEmpty
    // -------------------------------------------------------------------------

    @Test
    void testSizeAfterAdds() {
        assertEquals(0, playlist.size());
        playlist.addTrack(track1);
        assertEquals(1, playlist.size());
        playlist.addTrack(track2);
        assertEquals(2, playlist.size());
    }

    @Test
    void testIsEmptyBeforeAndAfterAdd() {
        assertTrue(playlist.isEmpty());
        playlist.addTrack(track1);
        assertFalse(playlist.isEmpty());
    }

    @Test
    void testIsEmptyAfterRemove() {
        playlist.addTrack(track1);
        playlist.removeTrack(track1);
        assertTrue(playlist.isEmpty());
    }

    // -------------------------------------------------------------------------
    // getTotalDuration and getFormattedTotalDuration
    // -------------------------------------------------------------------------

    @Test
    void testGetTotalDurationEmpty() {
        assertEquals(0, playlist.getTotalDuration());
    }

    @Test
    void testGetTotalDurationSingleTrack() {
        playlist.addTrack(track1); // 354 seconds
        assertEquals(354, playlist.getTotalDuration());
    }

    @Test
    void testGetTotalDurationMultipleTracks() {
        playlist.addTrack(track1); // 354
        playlist.addTrack(track2); // 187
        playlist.addTrack(track3); // 125
        assertEquals(666, playlist.getTotalDuration());
    }

    @Test
    void testGetFormattedTotalDurationEmpty() {
        assertEquals("00:00", playlist.getFormattedTotalDuration());
    }

    @Test
    void testGetFormattedTotalDurationUnderOneHour() {
        playlist.addTrack(track1); // 354 seconds = 5:54
        playlist.addTrack(track2); // 187 seconds
        // total = 541 seconds = 9 minutes 1 second
        assertEquals("09:01", playlist.getFormattedTotalDuration());
    }

    @Test
    void testGetFormattedTotalDurationOverOneHour() {
        // 3601 seconds = 1 hour, 0 minutes, 1 second
        Track longTrack = new Track("Long Song", "Artist", 3601);
        playlist.addTrack(longTrack);
        assertEquals("01:00:01", playlist.getFormattedTotalDuration());
    }

    @Test
    void testGetFormattedTotalDurationExactlyOneHour() {
        Track hourTrack = new Track("Hour Song", "Artist", 3600);
        playlist.addTrack(hourTrack);
        assertEquals("01:00:00", playlist.getFormattedTotalDuration());
    }

    // -------------------------------------------------------------------------
    // clear
    // -------------------------------------------------------------------------

    @Test
    void testClear() {
        playlist.addTrack(track1);
        playlist.addTrack(track2);
        playlist.clear();
        assertTrue(playlist.isEmpty());
        assertEquals(0, playlist.size());
    }

    @Test
    void testClearOnEmptyList() {
        playlist.clear();
        assertTrue(playlist.isEmpty());
    }

    @Test
    void testClearThenAddTrack() {
        playlist.addTrack(track1);
        playlist.clear();
        playlist.addTrack(track2);
        assertEquals(1, playlist.size());
        assertEquals(track2, playlist.getTrack(0));
    }

    // -------------------------------------------------------------------------
    // toString
    // -------------------------------------------------------------------------

    @Test
    void testToString() {
        playlist.addTrack(track1);
        playlist.addTrack(track2);
        String result = playlist.toString();
        assertTrue(result.contains("My Playlist"));
        assertTrue(result.contains("Bohemian Rhapsody"));
        assertTrue(result.contains("Imagine"));
    }

    @Test
    void testToStringEmptyPlaylist() {
        String result = playlist.toString();
        assertTrue(result.contains("My Playlist"));
        assertTrue(result.contains("0"));
    }

    // -------------------------------------------------------------------------
    // Edge cases: operations on empty lists and single-element lists
    // -------------------------------------------------------------------------

    @Test
    void testAddTrackAtIndexOnEmptyList() {
        playlist.addTrackAtIndex(0, track1);
        assertEquals(1, playlist.size());
        assertEquals(track1, playlist.getTrack(0));
    }

    @Test
    void testRemoveOnlyElementLeavesEmptyList() {
        playlist.addTrack(track1);
        playlist.removeTrackAtIndex(0);
        assertTrue(playlist.isEmpty());
    }

    @Test
    void testBoundaryIndexAtSize() {
        playlist.addTrack(track1);
        // index == size (1) is valid for addTrackAtIndex (appends to end)
        playlist.addTrackAtIndex(1, track2);
        assertEquals(track2, playlist.getTrack(1));
    }
}
