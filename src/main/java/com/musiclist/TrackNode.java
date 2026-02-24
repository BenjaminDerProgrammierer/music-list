package com.musiclist;

/**
 * A node in a singly linked list that wraps a {@link Track} and points to the next node.
 */
public class TrackNode {

    private Track track;
    private TrackNode next;

    /**
     * Creates a node with the given track and no next node.
     *
     * @param track the track to store (must not be null)
     * @throws IllegalArgumentException if track is null
     */
    public TrackNode(Track track) {
        this(track, null);
    }

    /**
     * Creates a node with the given track and a reference to the next node.
     *
     * @param track the track to store (must not be null)
     * @param next  the next node (may be null)
     * @throws IllegalArgumentException if track is null
     */
    public TrackNode(Track track, TrackNode next) {
        if (track == null) {
            throw new IllegalArgumentException("Track must not be null");
        }
        this.track = track;
        this.next = next;
    }

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        if (track == null) {
            throw new IllegalArgumentException("Track must not be null");
        }
        this.track = track;
    }

    public TrackNode getNext() {
        return next;
    }

    public void setNext(TrackNode next) {
        this.next = next;
    }
}
