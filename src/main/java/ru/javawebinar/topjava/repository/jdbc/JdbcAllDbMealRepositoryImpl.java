package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.Profiles;

/**
 * Created by ColdDeath&Dummy on 18.01.2017.
 */

@Profile({Profiles.POSTGRES, Profiles.JDBC})
@Component
public class JdbcAllDbMealRepositoryImpl extends JdbcMealRepositoryImpl
{

}
