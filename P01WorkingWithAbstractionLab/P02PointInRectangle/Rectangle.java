package P01WorkingWithAbstractionLab.P02PointInRectangle;

public class Rectangle {

    private Point bottomLeft;
    private Point topRight;

    public Rectangle(Point bottomLeft, Point topRight){
        this.bottomLeft = bottomLeft;
        this.topRight = topRight;
    }
    public boolean contains(Point newPoint){
        return newPoint.greaterOrEqual(bottomLeft) && newPoint.lessOrEqual(topRight);
    }
}