package com.musiclist;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrackNodeTest {

    private final Track sampleTrack = new Track("Bohemian Rhapsody", "Queen", 354);
    private final Track otherTrack  = new Track("Imagine", "John Lennon", 187);

    // -------------------------------------------------------------------------
    // Construction with Track only
    // -------------------------------------------------------------------------

    @Test
    void testConstructionWithTrackOnly() {
        TrackNode node = new TrackNode(sampleTrack);
        assertEquals(sampleTrack, node.getTrack());
        assertNull(node.getNext());
    }

    // -------------------------------------------------------------------------
    // Construction with Track and next node
    // -------------------------------------------------------------------------

    @Test
    void testConstructionWithTrackAndNext() {
        TrackNode nextNode = new TrackNode(otherTrack);
        TrackNode node = new TrackNode(sampleTrack, nextNode);
        assertEquals(sampleTrack, node.getTrack());
        assertEquals(nextNode, node.getNext());
    }

    // -------------------------------------------------------------------------
    // Null track throws exception
    // -------------------------------------------------------------------------

    @Test
    void testNullTrackInSingleArgConstructorThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new TrackNode(null));
    }

    @Test
    void testNullTrackInTwoArgConstructorThrowsException() {
        TrackNode nextNode = new TrackNode(otherTrack);
        assertThrows(IllegalArgumentException.class,
                () -> new TrackNode(null, nextNode));
    }

    // -------------------------------------------------------------------------
    // Getters and setters
    // -------------------------------------------------------------------------

    @Test
    void testSetTrack() {
        TrackNode node = new TrackNode(sampleTrack);
        node.setTrack(otherTrack);
        assertEquals(otherTrack, node.getTrack());
    }

    @Test
    void testSetNullTrackThrowsException() {
        TrackNode node = new TrackNode(sampleTrack);
        assertThrows(IllegalArgumentException.class,
                () -> node.setTrack(null));
    }

    @Test
    void testSetNextToNull() {
        TrackNode nextNode = new TrackNode(otherTrack);
        TrackNode node = new TrackNode(sampleTrack, nextNode);
        node.setNext(null);
        assertNull(node.getNext());
    }

    @Test
    void testSetNextToAnotherNode() {
        TrackNode node = new TrackNode(sampleTrack);
        TrackNode nextNode = new TrackNode(otherTrack);
        node.setNext(nextNode);
        assertEquals(nextNode, node.getNext());
    }

    // -------------------------------------------------------------------------
    // Chaining multiple nodes
    // -------------------------------------------------------------------------

    @Test
    void testChainingMultipleNodes() {
        Track track3 = new Track("Yesterday", "The Beatles", 125);

        TrackNode node3 = new TrackNode(track3);
        TrackNode node2 = new TrackNode(otherTrack, node3);
        TrackNode node1 = new TrackNode(sampleTrack, node2);

        assertEquals(sampleTrack, node1.getTrack());
        assertEquals(otherTrack, node1.getNext().getTrack());
        assertEquals(track3, node1.getNext().getNext().getTrack());
        assertNull(node1.getNext().getNext().getNext());
    }
}
