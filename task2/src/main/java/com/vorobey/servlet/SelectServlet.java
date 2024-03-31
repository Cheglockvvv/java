package com.vorobey.servlet;

import com.vorobey.dao.ItemDao;
import com.vorobey.entity.ItemEntity;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SelectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var itemDao = ItemDao.getInstance();
        List<ItemEntity> entityList = itemDao.findAll();
        req.setAttribute("entityList", entityList);
        req.getSession().setAttribute("username", req.getParameter("username"));
        RequestDispatcher dispatcher = req.getRequestDispatcher("/second.jsp");
        dispatcher.forward(req, resp);
    }
}
