package ru.javawebinar.topjava.service.jpa;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.MealServiceTest;

/**
 * Created by ColdDeath&Dummy on 17.01.2017.
 */
@ActiveProfiles({Profiles.ACTIVE_DB, Profiles.JPA})
public class JPAMealServiceTest extends MealServiceTest
{
}
