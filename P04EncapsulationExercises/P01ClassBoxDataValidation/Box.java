package P04EncapsulationExercises.P01ClassBoxDataValidation;

public class Box {
    private double length;
    private double width;
    private double height;

    public Box(double length, double width, double height) {
        this.setLength(length);
        this.setWidth(width);
        this.setHeight(height);
    }

    private void setLength(double length) {

        if (length > 0){
            this.length = length;
        }else {
            throw new IllegalArgumentException("Length cannot be zero or negative.");
        }
    }

    private void setWidth(double width) {
        if (width > 0){
            this.width = width;

        }else {
            throw new IllegalArgumentException("Width cannot be zero or negative.");
        }
    }

    private void setHeight(double height) {
        if (height > 0){
            this.height = height;
        }else {
            throw new IllegalArgumentException("Height cannot be zero or negative.");
        }
    }

    public double calculateSurfaceArea(){
        //Surface Area = 2lw + 2lh + 2wh

        return 2 * length * width + 2 * length * height + 2 * width * height;

    }

    public double calculateLateralSurfaceArea(){
        //Lateral Surface Area = 2lh + 2wh

        return  2 * length * height + 2 * width * height;
    }
    public double calculateVolume(){
        //Volume = lwh

        return  this.length * this.width * this.height;
    }
}
