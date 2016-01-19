/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interceptor;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 *
 * @author Angelo
 */
public class in extends AbstractInterceptor {

    private static final long serialVersionUID = 1L;

    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {

        String result = Action.LOGIN;
        if (actionInvocation.getInvocationContext().getSession().containsKey("user")) {
            result = actionInvocation.invoke();
        }
        return result;
    }
}
