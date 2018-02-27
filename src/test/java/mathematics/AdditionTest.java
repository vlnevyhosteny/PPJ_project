package mathematics;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdditionTest {

    @Test
    void sum() {
        double[] numbers = { 1, 2, 3, 4 };
        double expectedResult = 10;
        double precisionLoss = 0.001;

        Addition addition = new Addition(numbers);

        Assert.assertEquals(addition.Sum(), expectedResult, precisionLoss);

        Assert.assertEquals(addition.Sum(null), 0, precisionLoss);

        double[] numbers2 = { -1, 2, -5};
        Assert.assertEquals(addition.Sum(numbers2), -4, precisionLoss);
    }
}