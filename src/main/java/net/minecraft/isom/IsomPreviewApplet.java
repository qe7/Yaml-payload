package net.minecraft.isom;

import net.minecraft.src.CanvasIsomPreview;

import java.applet.Applet;
import java.awt.*;

public class IsomPreviewApplet extends Applet {

    private final CanvasIsomPreview a;

    public IsomPreviewApplet() {
        a = new CanvasIsomPreview();
        setLayout(new BorderLayout());
        add(a, "Center");
    }

    public void start() {
        a.startThreads();
    }

    public void stop() {
        a.exit();
    }
}
