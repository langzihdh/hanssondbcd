package com.hansson.dybalcalc.data.dummy;

import java.util.Calendar;
import java.util.Date;

import com.hansson.dybalcalc.data.DataProvider;
import com.hansson.dybalcalc.domain.User;

/**
 * A dummy implementation for the backend API.
 */
public class DummyDataProvider implements DataProvider {
    private static Date lastDataUpdate;
  
    /**
     * Initialize the data for this application.
     */
    public DummyDataProvider() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, -1);
        if (lastDataUpdate == null || lastDataUpdate.before(cal.getTime())) {
           // refreshStaticData();
            lastDataUpdate = new Date();
        }
    }

    @Override
    public User authenticate(String userName, String password) {
        User user = new User();
        user.setName("Hansson");
        user.setPassword(DummyDataGenerator.randomLastName());
        user.setRole("admin");
        return user;
    }

}
