package model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ppj.weather.Main;
import ppj.weather.model.State;
import ppj.weather.servicies.StateService;

import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Main.class)
@ActiveProfiles({"test"})
public class StateServiceTest {

    @Autowired
    private StateService stateService;

    private State state1 = new State( "Liberec");
    private State state2 = new State( "Praha");
    private State state3 = new State( "Plzen");
    private State state4 = new State( "Brno");

    @Before
    public void init() {
        stateService.deleteStates();
    }

    @Test
    public void getStateService() {
        Assert.assertTrue(stateService != null);
    }

    @Test
    public void testCreateRetrieve() {
        stateService.create(state1);

        List<State> states1 = stateService.getAllStates();

        System.out.println(states1);

        assertEquals("One user should have been created and retrieved", 1, states1.size());

        assertEquals("Inserted user should match retrieved", state1, states1.get(0));

        stateService.create(state2);
        stateService.create(state3);
        stateService.create(state4);

        List<State> states2 = stateService.getAllStates();

        assertEquals("Should be four retrieved users.", 4, states2.size());
    }

}
