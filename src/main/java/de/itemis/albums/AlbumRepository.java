package de.itemis.albums;

public class AlbumRepository {

    private int newAlbumID;

    public AlbumRepository(int newAlbumID) {
        this.newAlbumID = newAlbumID;
    }

    int save(Album album) {
        return newAlbumID;
    }
}