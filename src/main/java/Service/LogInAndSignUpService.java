package Service;

import Entity.LogInAndSignUpEntity;

import java.util.List;
import java.util.stream.Collectors;

public class LogInAndSignUpService extends AbstractService<LogInAndSignUpEntity> {

    public LogInAndSignUpService() {
        super(new LogInAndSignUpEntity());
    }

    public List<LogInAndSignUpEntity> findByLogin(String login) {
        List<LogInAndSignUpEntity> listLogins = repository.findAll();
        return listLogins.stream().filter(log -> log.getLogin_().equals(login)).collect(Collectors.toList());
    }
}
