package net.minecraft.client;

import net.minecraft.src.CanvasMinecraftApplet;
import net.minecraft.src.MinecraftAppletImpl;
import net.minecraft.src.Session;

import java.applet.Applet;
import java.awt.*;

public class MinecraftApplet extends Applet {

    private Canvas mcCanvas;
    private Minecraft mc;
    private Thread mcThread;

    public MinecraftApplet() {
        mcThread = null;
    }

    @Override
    public void init() {
        mcCanvas = new CanvasMinecraftApplet(this);
        boolean isFullscreen = getParameter("fullscreen") != null && getParameter("fullscreen").equalsIgnoreCase("true");

        mc = new MinecraftAppletImpl(this, this, mcCanvas, this, getWidth(), getHeight(), isFullscreen);
        mc.minecraftUri = getDocumentBase().getHost();

        if (getDocumentBase().getPort() > 0) {
            mc.minecraftUri += ":" + getDocumentBase().getPort();
        }

        String username = getParameter("username");
        String sessionId = getParameter("sessionid");
        if (username != null && sessionId != null) {
            mc.session = new Session(username, sessionId);
            System.out.println("Setting user: " + mc.session.username + ", " + mc.session.sessionId);

            String mpPass = getParameter("mppass");
            if (mpPass != null) {
                mc.session.mpPassParameter = mpPass;
            }
        } else {
            mc.session = new Session("Player", "");
        }

        String server = getParameter("server");
        String port = getParameter("port");
        if (server != null && port != null) {
            mc.setServer(server, Integer.parseInt(port));
        }

        mc.hideQuitButton = true;
        setLayout(new BorderLayout());
        add(mcCanvas, BorderLayout.CENTER);
        mcCanvas.setFocusable(true);
        validate();
    }

    public void startMainThread() {
        if (mcThread == null) {
            mcThread = new Thread(mc, "Minecraft main thread");
            mcThread.start();
        }
    }

    @Override
    public void start() {
        if (mc != null) {
            mc.isGamePaused = false;
        }
    }

    @Override
    public void stop() {
        if (mc != null) {
            mc.isGamePaused = true;
        }
    }

    @Override
    public void destroy() {
        shutdown();
    }

    public void shutdown() {
        if (mcThread == null) {
            return;
        }
        mc.shutdown();
        try {
            mcThread.join(10000L);
        } catch (InterruptedException e) {
            try {
                mc.shutdownMinecraftApplet();
            } catch (Exception ex) {
                System.out.println("Failed to shutdown");
            }
        }
        mcThread = null;
    }

    public void clearApplet() {
        mcCanvas = null;
        mc = null;
        mcThread = null;
        try {
            removeAll();
            validate();
        } catch (Exception exception) {
            System.out.println("Error removing canvas");
        }
    }
}