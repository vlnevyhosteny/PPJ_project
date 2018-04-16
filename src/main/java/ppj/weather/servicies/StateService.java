package ppj.weather.servicies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ppj.weather.model.State;
import ppj.weather.repositories.StateRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class StateService {

    @Autowired
    private StateRepository stateRepository;

    public void create(State user) {
        stateRepository.save(user);
    }

    public List<State> getAllStates() {
        return StreamSupport.stream(stateRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public void deleteStates() {
        stateRepository.deleteAll();
    }

    public boolean exists(Integer id) {
        return stateRepository.existsById(id);
    }

}
