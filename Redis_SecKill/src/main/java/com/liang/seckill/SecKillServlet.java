package com.liang.seckill;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 处理用户的请求
 */
public class SecKillServlet extends HttpServlet{
 
	private static final long serialVersionUID = 1L;

	public SecKillServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user = req.getParameter("user");
        String product = req.getParameter("product");

        boolean if_success = false;
        try {
            if_success = Redis_SecKill.do_secKill(user,product);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        resp.getWriter().print(if_success);
    }

}
