package de.itemis.albums;

public class Albums {
    private final AlbumRepository albumRepository;

    public Albums(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    public Integer add(Album album) {

        return albumRepository.save(album);
    }

}
