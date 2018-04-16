package model;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ppj.weather.Main;
import ppj.weather.model.StateDao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Main.class)
@ActiveProfiles({"test"})
public class StateDaoTest {

    @Autowired
    private StateDao stateDao;

    @Test
    public void getStateTest() {
        Assert.assertTrue(stateDao.getStates().size() > 0);
    }

}
