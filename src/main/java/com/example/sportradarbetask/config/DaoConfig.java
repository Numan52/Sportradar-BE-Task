package com.example.sportradarbetask.config;

import com.example.sportradarbetask.daos.event.EventDao;
import com.example.sportradarbetask.daos.event.EventDaoImpl;
import com.example.sportradarbetask.daos.sport.SportDao;
import com.example.sportradarbetask.daos.sport.SportDaoImpl;
import com.example.sportradarbetask.daos.team.TeamDao;
import com.example.sportradarbetask.daos.team.TeamDaoImpl;
import com.example.sportradarbetask.daos.utility.UtilityDao;
import com.example.sportradarbetask.daos.venue.VenueDao;
import com.example.sportradarbetask.daos.venue.VenueDaoImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoConfig {
    @Bean
    public EventDao getEventDao() {
        return new EventDaoImpl();
    }

    @Bean
    public VenueDao getVenueDao() {
        return new VenueDaoImpl();
    }
    @Bean
    public TeamDao getTeamDao() {
        return new TeamDaoImpl();
    }
    @Bean
    public SportDao getSportDao() {
        return new SportDaoImpl();
    }
    @Bean
    public UtilityDao getUtilityDao() {
        return new UtilityDao();
    }
}
