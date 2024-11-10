package com.example.sportradarbetask.config;

import com.example.sportradarbetask.daos.event.EventDao;
import com.example.sportradarbetask.daos.event.EventDaoImpl;
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
}
