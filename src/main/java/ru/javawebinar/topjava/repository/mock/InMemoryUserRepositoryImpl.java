package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * GKislin
 * 15.06.2015.
 */
@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {
    private static final Logger LOG = LoggerFactory.getLogger(InMemoryUserRepositoryImpl.class);
    private Map<Integer, User> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(3);

    {
        save(new User(1, "Admin", "adm@ya.ru", "root", Role.ROLE_ADMIN));
        save(new User(2, "User", "test@ya.ru", "user", Role.ROLE_USER));
    }

    @Override
    public User save(User user) {
        LOG.info("save " + user);
        if (user.isNew())
        {
            user.setId(counter.incrementAndGet());
        }
        repository.put(user.getId(), user);
        return user;
    }

    @Override
    public boolean delete(int id) {
        LOG.info("delete " + id);
        if (!repository.containsKey(id))
            throw new NotFoundException("user with this is not exist");
        repository.remove(id);
        return true;
    }

    @Override
    public User get(int id) {
        LOG.info("get " + id);
        if (!repository.containsKey(id))
            throw new NotFoundException("user with this is not exist");
        return repository.get(id);
    }

    @Override
    public List<User> getAll() {
        LOG.info("getAll");
        return new ArrayList<User>(repository.values().stream()
                .sorted(Comparator.comparing(User::getName))
                .collect(Collectors.toList()));
    }

    @Override
    public User getByEmail(String email) {
        LOG.info("getByEmail " + email);
        return repository.values().stream().filter(user -> user.getEmail().equals(email)).findFirst().get();
    }
}
