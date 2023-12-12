package P15Exams.P15Exam.viceCity.models.guns;

public class Rifle extends BaseGun{

    private static final int BULLETS_PER_BARREL = 10;
    private static final int TOTAL_BULLETS = 100;

    public Rifle(String name) {
        super(name, BULLETS_PER_BARREL, TOTAL_BULLETS);
    }

    @Override
    public boolean canFire() {
        return super.getTotalBullets() >= 50;
    }

    @Override
    public int fire() {
        this.setTotalBullets(this.getTotalBullets() - 50);
        return 50;
    }
}
