package aqcompanion.nezbo.dk.arcadiaquestcompanion.model;

/**
 * Created by Emil on 15-11-2015.
 */
public class Icon {

    private final int id;
    private final String name;
    private final String img_file;

    public Icon(int id, String img_file, String name) {
        this.id = id;
        this.img_file = img_file;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getImgFilename() {
        return img_file;
    }

    public String getName() {
        return name;
    }
}
