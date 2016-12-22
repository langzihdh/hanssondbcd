package com.hansson.dybalcalc.data;

import com.hansson.dybalcalc.domain.User;

/**
 * QuickTickets Dashboard backend API.
 */
public interface DataProvider {

    /**
     * @param userName
     * @param password
     * @return Authenticated used.
     */
    User authenticate(String userName, String password);

}
