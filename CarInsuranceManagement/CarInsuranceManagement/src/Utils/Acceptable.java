/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

/**
 *
 * @author Wang
 */
public interface Acceptable {

    public final String NAME_VALID = "^.{2,20}$";
    public final String PHONE_VALID = "^(0[3|5|7|8|9])+([0-9]{8})$";
    public final String DATE_VALID = "^(0[1-9]|[1|2][0-9]|3[01])/(0[1-9]|1[0-2])/(\\d{4})$";
    public final String BRAND_VALID = "^.{2,15}$";
    public final String ID_VALID = "^[0-9]{4}$";
    public final String PLATE_VALID = "^5[0-9][XxTtFfCcHhKkLlUuMmGgDdEeNnPpSsVvYyZz]-\\d{3}\\.\\d{2}$";
    public final String YEAR_VALID = "^[1-2][0-9][0-9][0-9]$";
}