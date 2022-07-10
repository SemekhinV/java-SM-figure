package Figure;

import javax.swing.text.rtf.RTFEditorKit;
import java.awt.*;

public class Rectangles implements Figure {

    Rectangle rec;
    int Size;
    int dx;
    int dy;
    Point point;

    Dimension dimension;
    int MinSize;
    Color col;
    int ColorNumber;
    int SpeedX;
    int SpeedY;
    int Move;

    public boolean up = false, down = false, left = false, right = false;

    public int getX(){
        return dx;
    }
    public int getY(){
        return dy;
    }

    public void nextSize(){
        Size -= 5;
        if(Size < MinSize){
            Size = 55;
        }
        dimension = new Dimension(Size, Size);
    }
    public int getSize(){
        return Size;
    }

    public Color getColor() {return col;}

    public int GetColorNumber(){return ColorNumber;}

    public Rectangles(){}

    public Rectangles(Point newPoint, Dimension newDimension, int ColorNumber){
        dx = (int) newPoint.getX();
        dy = (int) newPoint.getY();
        SpeedX = 3;
        SpeedY = 3;
        point = newPoint;
        Size = 50;
        this.dimension = newDimension;
        MinSize = 10;
        this.col = nextColor(ColorNumber);
        rec = new Rectangle(new Point(dx,dy), dimension);
        Move = (int) (Math.random() * 4);
        if(Move ==  0) {
            up = true;
        }
        if(Move == 1){
            down = true;
        }
        if(Move == 2) {
            right = true;
        }
        if(Move == 3) {
            left = true;
        }
    }

    public void FieldCrossCheck(){
        // В зависимости от места пересечения отодвигаем
        // Фигуру на пару пикселей от границы и меняем направление
        if (dy < 0){
            up = false;
            down = true;
        }
        if (dy > 720){
            up = true;
            down = false;
        }
        if (dx > 720){
            left = true;
            right = false;
        }
        if (dx < 0){
            left = false;
            right = true;

        }

    }

    public void moveCurrentPosition(int x, int y){
        dx += x;
        dy += y;
    }


    public void move()
    {
        FieldCrossCheck();
        if (up){
            dy -= SpeedY;
        }
        if(down){
            dy += SpeedY;
        }
        if(left){
            dx -= SpeedX;
        }
        if(right){
            dx += SpeedX;
        }
    }


    public void tick()
    {
        move();
        col = nextColor(ColorNumber);
    }

    public Dimension getDimension(){return dimension;}


    public Color nextColor(int ColorNumber){
        this.ColorNumber = ColorNumber;
        switch (ColorNumber){
            case 1:
                return Color.RED;
            case 2:
                return Color.ORANGE;
            case 3:
                return Color.YELLOW;
            case 4:
                return Color.GREEN;
            case 5:
                return Color.BLUE.brighter();
            case 6:
                return Color.BLUE.darker();
            case 7:
                return Color.PINK.darker();

            default: ColorNumber = 1;
        }
        return null;
    }

    public void newColor(int ColorNumb){
        if (ColorNumb < 8 && ColorNumb > 0){
            this.ColorNumber = ColorNumb;
        }
        else {
            ColorNumber = 1;
        }
    }
}
