package max.project.notes.paint;

import android.graphics.Path;

public class FingerPath {

    public final int color;
    public final boolean emboss;
    public final boolean blur;
    public final int strokeWidth;
    public final Path path;

    public FingerPath(int color, boolean emboss, boolean blur, int strokeWidth, Path path) {
        this.color = color;
        this.emboss = emboss;
        this.blur = blur;
        this.strokeWidth = strokeWidth;
        this.path = path;
    }

}
