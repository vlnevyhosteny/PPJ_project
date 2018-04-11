package model;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import ppj.weather.model.StateDao;

public class StateDaoTest {

    private StateDao stateDao = new StateDao();

    @Test
    void getStateTest() {
        Assert.assertTrue(stateDao.getStates().size() > 0);
    }

}
