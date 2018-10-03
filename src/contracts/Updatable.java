package contracts;

import java.awt.*;

public interface Updatable {

    public abstract void tick();
    public abstract void render(Graphics graphics);
}
