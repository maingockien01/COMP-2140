public class TrainCar {
    public static final String ENGINE_TYPE = "engine";
    public static final int ENGINE_VALUE = 0;

    public static final String CARGO_DUMMY_TYPE = "CargoDummy!";
    public static final int CARGO_DUMMY_VALUE = 0;

    private String carType;
    private int carValue;

    public TrainCar (String carType, int carValue) {
        this.carType = carType;
        this.carValue = carValue;
    }

    public String getCarType () {
        return this.carType;
    }

    public int getCarValue () {
        return this.carValue;
    }

}
