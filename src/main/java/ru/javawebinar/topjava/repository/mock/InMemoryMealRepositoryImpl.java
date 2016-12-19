package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * GKislin
 * 15.09.2015.
 */
@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private static final Logger LOG = LoggerFactory.getLogger(InMemoryMealRepositoryImpl.class);
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(meal -> save(meal, meal.getId()));
    }

    @Override
    public Meal save(Meal meal, int userId) {
        LOG.info("save " + meal);
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            meal.setUserId(userId);
        }
        repository.put(meal.getId(), meal);
        return meal;
    }

    @Override
    public boolean delete(int id, int userId) {
        LOG.info("delete " + id);
        if (repository.get(id).getUserId() != userId)
        {
            throw new NotFoundException("cannot delete meal of another user");
        }
        repository.remove(id);
        return true;
    }

    @Override
    public Meal get(int id, int userId) {
        LOG.info("get " + id);
        if (repository.get(id).getUserId() != userId)
            throw new NotFoundException("cannot get meal of another user");
        return repository.get(id);
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        LOG.info("getAll");
        return repository.values().stream().filter(meal -> meal.getUserId() == userId).collect(Collectors.toList());
    }

    @Override
    public Collection<Meal> getBetween(LocalDate startDate, LocalDate endDate, int userId)
    {
        return repository.values().stream()
                .filter(meal -> DateTimeUtil.isBetween(meal.getDate(), startDate, endDate))
                .collect(Collectors.toList());
    }
}

