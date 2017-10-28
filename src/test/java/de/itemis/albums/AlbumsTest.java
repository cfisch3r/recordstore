package de.itemis.albums;

import org.junit.Assert;
import org.junit.Test;

public class AlbumsTest {

    @Test
    public void album_can_be_saved() {
        Albums albums = new Albums();
        Album album = new Album();
        Integer albumId = albums.add(album);
        Assert.assertEquals("4711",albumId);
    }
}
