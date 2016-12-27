package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static ru.javawebinar.topjava.MealTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
public class MealServiceTest {

    @Autowired
    private MealService service;

    @Autowired
    private DbPopulator dbPopulator;

    @Before
    public void setUp() throws Exception {
        dbPopulator.execute();
    }

    @Test
    public void testSave() throws Exception {
        Meal newMeal = new Meal(LocalDateTime.now(), "тестовая еда", 500);
        Meal created = service.save(newMeal, USER_ID);
        newMeal.setId(created.getId());
        List<Meal> meals = new ArrayList<Meal>(MEALS_USER);
        meals.add(newMeal);
        MATCHER.assertCollectionEquals(meals, service.getAll(USER_ID));
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(MEALS_ADMIN.get(0).getId(), ADMIN_ID);
        List<Meal> meals =  new ArrayList<>(MEALS_ADMIN);
        meals.remove(0);
        MATCHER.assertCollectionEquals(meals, service.getAll(ADMIN_ID));
    }

    @Test
    public void testGet() throws Exception {
        Meal meal = service.get(MEALS_ADMIN.get(0).getId(), ADMIN_ID);
        MATCHER.assertEquals(MEALS_ADMIN.get(0), meal);
    }

    @Test
    public void testGetAll() throws Exception {
        MATCHER.assertCollectionEquals(MEALS_ADMIN, service.getAll(ADMIN_ID));
    }

    @Test
    public void testUpdate() throws Exception {
        Meal updated = new Meal(MEALS_ADMIN.get(0));
        updated.setCalories(1000);
        updated.setDescription("Test");
        service.update(updated, ADMIN_ID);
        MATCHER.assertEquals(updated, service.get(MEALS_ADMIN.get(0).getId(), ADMIN_ID));
    }

    @Test(expected = NotFoundException.class)
    public void testDeleteByWrongUser() throws Exception {
        service.delete(MEALS_ADMIN.get(0).getId(), USER_ID);
    }

    @Test(expected = NotFoundException.class)
    public void testWrongGetByWrongUser() throws Exception {
        service.get(MEALS_ADMIN.get(0).getId(), USER_ID);
    }

    @Test(expected = NotFoundException.class)
    public void testWrongUpdateByWrongUser() throws Exception {
        Meal updated = new Meal(MEALS_ADMIN.get(1));
        updated.setCalories(700);
        updated.setDescription("WrongUser");
        service.update(updated, USER_ID);
    }
}