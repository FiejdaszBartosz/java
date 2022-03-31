package point;

public class Point implements IPoint {
    double x, y;

    public Point() {
        this.x = 0;
        this.y = 0;
    }

    @Override
    public double getX() {
        return this.x;
    }

    @Override
    public double getY() {
        return this.y;
    }

    @Override
    public void setX(double x) {
        this.x = x;
    }

    @Override
    public void setY(double y) {
        this.y = y;
    }

    @Override
    public void changeCoordinates(double newX, double newY) {
        if(this.x != newX && this.y != newY) {
            this.x = newX;
            this.y = newY;
        }
        else
            System.out.println("Podano ten sam punkt");
    }
}
