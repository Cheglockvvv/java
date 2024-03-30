package com.vorobey.servlet;

import com.vorobey.dao.ItemDao;
import com.vorobey.entity.ItemEntity;
import com.vorobey.util.ConnectionUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DataServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var itemDao = ItemDao.getInstance();
        itemDao.createAndFillTable();
        List<ItemEntity> entityList = itemDao.findAll();
        req.setAttribute("entityList", entityList);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/second.jsp");
        dispatcher.forward(req, resp);
    }
}
