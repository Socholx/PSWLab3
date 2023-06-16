class RGB {
    private int R_value;
    private int G_value;
    private int B_value;

    public RGB(int R, int G, int B) {
        this.R_value = R;
        this.G_value = G;
        this.B_value = B;
    }

    public int getR_value() {
        return R_value;
    }

    public void setR_value(int R) {
        this.R_value = R;
    }

    public int getG_value() {
        return G_value;
    }

    public void setG_value(int G) {
        this.G_value = G;
    }

    public int getB_value() {
        return B_value;
    }

    public void setB_value(int B) {
        this.B_value = B;
    }
}

class RGBController {
    public void initializeColors(RGB color, int R, int G, int B) {
        color.setR_value(R);
        color.setG_value(G);
        color.setB_value(B);
    }

    public void displayColors(RGB color) {
        System.out.println("[" + color.getR_value() + ", " + color.getG_value() + ", " + color.getB_value() + "]");
    }

    public RGB mixColors(RGB color1, RGB color2) {
        int mixedR = (color1.getR_value() + color2.getR_value()) / 2;
        int mixedG = (color1.getG_value() + color2.getG_value()) / 2;
        int mixedB = (color1.getB_value() + color2.getB_value()) / 2;

        return new RGB(mixedR, mixedG, mixedB);
    }
}

public class zad1 {
    public static void main(String[] args) {
        RGBController controller = new RGBController();

        RGB color1 = new RGB(100, 100, 0);
        RGB color2 = new RGB(0, 253, 0);

        controller.displayColors(color1);
        controller.displayColors(color2);

        RGB mixedColor = controller.mixColors(color1, color2);
        controller.displayColors(mixedColor);
    }
}