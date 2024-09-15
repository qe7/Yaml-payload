package io.github.qe7.events;

public final class KeyPressEvent {

    private final int KeyCode;

    public KeyPressEvent(int KeyCode) {
        this.KeyCode = KeyCode;
    }

    public int getKeyCode() {
        return KeyCode;
    }
}
