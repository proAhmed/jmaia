package droidahmed.com.jm3eia.model;

/**
 * Created by ahmed on 5/28/2016.
 */
public class PosCheck {
    private int pos;
    private int check;

    public PosCheck(int pos, int check) {
        this.pos = pos;
        this.check = check;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public int getCheck() {
        return check;
    }

    public void setCheck(int check) {
        this.check = check;
    }
}
