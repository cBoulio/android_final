package com.example.gateway.bby;

import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.sql.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Gateway on 9/22/2016.
 */
public class RegisterRequest extends StringRequest {


    private static final String REGISTER_REQUEST_URL="http://cboulio.netne.net/Register.php";

    private Map<String, String> params;
                                                        /*String email, Boolean gender, Date birthday, String ethnicity, String country, Date registerDate,*/
    public RegisterRequest(String username, String password,  Response.Listener<String> listener){
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);

        params = new HashMap<>();
        params.put("username",username);
        params.put("password",password);
        checkParams(params);
        /*
        params.put("email",email);
        params.put("gender", String.valueOf(gender));
        params.put("birthday", String.valueOf(birthday));
        params.put("ethnicity",ethnicity);
        params.put("country",country);
        params.put("registerDate", String.valueOf(registerDate));
    */

    }

    private Map<String, String> checkParams(Map<String, String> map){
        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> pairs = (Map.Entry<String, String>)it.next();
            if(pairs.getValue()==null){
                map.put(pairs.getKey(), "");
            }
        }
        return map;
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }


}
