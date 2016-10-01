package com.example.gateway.bby;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Gateway on 9/30/2016.
 */
public class LoginRequest extends StringRequest {

    private static final String LOGIN_REQUEST_URL="http://cboulio.netne.net/Login.php";

    private Map<String, String> params;
    public LoginRequest(String username, String password,  Response.Listener<String> listener){
        super(Request.Method.POST, LOGIN_REQUEST_URL, listener, null);

        params = new HashMap<>();
        params.put("username",username);
        params.put("password",password);
        checkParams(params);

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
