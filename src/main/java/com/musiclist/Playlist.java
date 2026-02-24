package com.musiclist;

/**
 * A singly linked list that represents a music playlist.
 */
public class Playlist {

    private final String name;
    private TrackNode head;
    private int size;

    /**
     * Creates an empty playlist with the given name.
     *
     * @param name the name of the playlist
     */
    public Playlist(String name) {
        this.name = name;
        this.head = null;
        this.size = 0;
    }

    public String getName() {
        return name;
    }

    /**
     * Adds a track to the end of the playlist.
     *
     * @param track the track to add (must not be null)
     * @throws IllegalArgumentException if track is null
     */
    public void addTrack(Track track) {
        if (track == null) {
            throw new IllegalArgumentException("Track must not be null");
        }
        TrackNode newNode = new TrackNode(track);
        if (head == null) {
            head = newNode;
        } else {
            TrackNode current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }
        size++;
    }

    /**
     * Adds a track at the beginning of the playlist.
     *
     * @param track the track to add (must not be null)
     * @throws IllegalArgumentException if track is null
     */
    public void addTrackAtBeginning(Track track) {
        if (track == null) {
            throw new IllegalArgumentException("Track must not be null");
        }
        head = new TrackNode(track, head);
        size++;
    }

    /**
     * Inserts a track at the specified index (0-based).
     *
     * @param index the position at which to insert
     * @param track the track to add (must not be null)
     * @throws IllegalArgumentException      if track is null
     * @throws IndexOutOfBoundsException     if index is negative or greater than size
     */
    public void addTrackAtIndex(int index, Track track) {
        if (track == null) {
            throw new IllegalArgumentException("Track must not be null");
        }
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        if (index == 0) {
            addTrackAtBeginning(track);
            return;
        }
        TrackNode current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.getNext();
        }
        current.setNext(new TrackNode(track, current.getNext()));
        size++;
    }

    /**
     * Removes the first occurrence of the given track from the playlist.
     *
     * @param track the track to remove
     * @return true if the track was found and removed, false otherwise
     */
    public boolean removeTrack(Track track) {
        if (track == null || head == null) {
            return false;
        }
        if (head.getTrack().equals(track)) {
            head = head.getNext();
            size--;
            return true;
        }
        TrackNode current = head;
        while (current.getNext() != null) {
            if (current.getNext().getTrack().equals(track)) {
                current.setNext(current.getNext().getNext());
                size--;
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    /**
     * Removes the track at the specified index.
     *
     * @param index the 0-based index of the track to remove
     * @return the removed Track
     * @throws IndexOutOfBoundsException if the index is out of bounds
     */
    public Track removeTrackAtIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        Track removed;
        if (index == 0) {
            removed = head.getTrack();
            head = head.getNext();
        } else {
            TrackNode current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.getNext();
            }
            removed = current.getNext().getTrack();
            current.setNext(current.getNext().getNext());
        }
        size--;
        return removed;
    }

    /**
     * Returns the track at the specified index.
     *
     * @param index the 0-based index
     * @return the Track at the given index
     * @throws IndexOutOfBoundsException if the index is out of bounds
     */
    public Track getTrack(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        TrackNode current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current.getTrack();
    }

    /**
     * Returns true if the playlist contains the given track.
     *
     * @param track the track to search for
     * @return true if found, false otherwise
     */
    public boolean contains(Track track) {
        return indexOf(track) != -1;
    }

    /**
     * Returns the index of the first occurrence of the given track, or -1 if not found.
     *
     * @param track the track to search for
     * @return the index, or -1
     */
    public int indexOf(Track track) {
        TrackNode current = head;
        int index = 0;
        while (current != null) {
            if (current.getTrack().equals(track)) {
                return index;
            }
            current = current.getNext();
            index++;
        }
        return -1;
    }

    /**
     * Returns the number of tracks in the playlist.
     *
     * @return the size
     */
    public int size() {
        return size;
    }

    /**
     * Returns true if the playlist contains no tracks.
     *
     * @return true if empty
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the total duration of all tracks in seconds.
     *
     * @return total duration in seconds
     */
    public int getTotalDuration() {
        int total = 0;
        TrackNode current = head;
        while (current != null) {
            total += current.getTrack().getDurationInSeconds();
            current = current.getNext();
        }
        return total;
    }

    /**
     * Returns the total duration formatted as "hh:mm:ss" if one hour or more, otherwise "mm:ss".
     *
     * @return formatted total duration
     */
    public String getFormattedTotalDuration() {
        int total = getTotalDuration();
        int hours = total / 3600;
        int minutes = (total % 3600) / 60;
        int seconds = total % 60;
        if (hours > 0) {
            return String.format("%02d:%02d:%02d", hours, minutes, seconds);
        }
        return String.format("%02d:%02d", minutes, seconds);
    }

    /**
     * Removes all tracks from the playlist.
     */
    public void clear() {
        head = null;
        size = 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Playlist '").append(name).append("' [").append(size).append(" tracks]:\n");
        TrackNode current = head;
        int index = 0;
        while (current != null) {
            sb.append("  ").append(index).append(". ").append(current.getTrack()).append("\n");
            current = current.getNext();
            index++;
        }
        return sb.toString();
    }
}
