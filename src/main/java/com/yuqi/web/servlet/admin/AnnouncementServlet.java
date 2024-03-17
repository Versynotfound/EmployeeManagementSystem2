package com.yuqi.web.servlet.admin;/**
 * @author yuqi
 */

import com.alibaba.fastjson.JSON;
import com.yuqi.pojo.PageBean;
import com.yuqi.pojo.Announcement;
import com.yuqi.service.AnnouncementService;
import com.yuqi.service.impl.AnnouncementServiceImpl;
import com.yuqi.web.servlet.BaseServlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 公告servlet
 * @author yuqi
 */
@WebServlet("/announcement/*")
public class AnnouncementServlet extends BaseServlet {
    private AnnouncementService announcementService = new AnnouncementServiceImpl();

    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader br = request.getReader();
        String params = br.readLine();
        Announcement announcement = JSON.parseObject(params, Announcement.class);
        // 获取当前日期和时间
        LocalDateTime currentDateTime = LocalDateTime.now();

        // 格式化
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);

        announcement.setCreatedTime(formattedDateTime);
        announcement.setLastUpdateTime(formattedDateTime);

        announcementService.add(announcement);
        response.getWriter().write("success");
    }

    public void updateAnnouncement(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader br = request.getReader();
        String params = br.readLine();

        Announcement announcement = JSON.parseObject(params, Announcement.class);

        LocalDateTime currentDateTime = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);

        announcement.setLastUpdateTime(formattedDateTime);
        announcementService.updateAnnouncement(announcement);

        response.getWriter().write("success");
    }

    public void deleteByIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader br = request.getReader();
        String params = br.readLine();
        int[] ids = JSON.parseObject(params, int[].class);
        announcementService.deleteByIds(ids);
        response.getWriter().write("success");
    }

    public void deleteById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader br = request.getReader();
        String params = br.readLine();
        Announcement announcement = JSON.parseObject(params, Announcement.class);
        announcementService.deleteById(announcement);
        response.getWriter().write("success");
    }

    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");

        int currentPage = Integer.parseInt(currentPageStr);
        int pageSize = Integer.parseInt(pageSizeStr);

        BufferedReader br = request.getReader();
        String params = br.readLine();
        Announcement announcement = JSON.parseObject(params,Announcement.class);
        PageBean<Announcement> pageBean = announcementService.selectByPageAndCondition(currentPage, pageSize, announcement);

        String jsonString = JSON.toJSONString(pageBean);
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

}
