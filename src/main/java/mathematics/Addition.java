package mathematics;

public class Addition {

    private double[] numbers = null;

    public Addition() {
    }

    public Addition(double[] numbers) {
        this.numbers = numbers;
    }

    public double Sum() {
        return Sum(this.numbers);
    }

    public double Sum(double[] numbers) {
        if(numbers == null) {
            return 0;
        }

        double result = 0;

        for (double number : numbers) {
            result += number;
        }

        return result;
    }

}
