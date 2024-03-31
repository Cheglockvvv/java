package com.vorobey.servlet;

import com.vorobey.dao.ItemDao;
import com.vorobey.entity.ItemEntity;
import lombok.SneakyThrows;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ReceiptServlet extends HttpServlet {
    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var itemDao = ItemDao.getInstance();
        if (req.getParameterValues("goods") == null) {
            req.setAttribute("total", 0.0);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/final.jsp");
            dispatcher.forward(req, resp);
        }
        List<Long> chosenId = Arrays.stream(req.getParameterValues("goods"))
                .map(Long::parseLong)
                .collect(Collectors.toList());


        List<ItemEntity> byId = itemDao.findById(chosenId);
        Double total = byId.stream()
                .map(ItemEntity::getCost)
                .reduce(0.0, Double::sum);

        req.setAttribute("total", total);
        req.setAttribute("chosen", byId);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/final.jsp");
        dispatcher.forward(req, resp);
    }
}
