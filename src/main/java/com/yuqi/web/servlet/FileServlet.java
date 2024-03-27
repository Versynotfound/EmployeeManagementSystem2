package com.yuqi.web.servlet;

import com.alibaba.fastjson2.JSON;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 用于头像文件上传的servlet
 * @author yuqi
 */
@WebServlet("/upload")
public class FileServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 这里获取前端传过来的头像文件？
        FileInputStream fis = new FileInputStream("e:\\photo\\pink.jpg");
        ServletOutputStream os = response.getOutputStream();
        IOUtils.copy(fis,os);
        fis.close();
    }
//        // 获取上传的文件
//        Part filePart = request.getPart("file");
//        String fileName = getSubmittedFileName(filePart);
//        // 保存文件到指定路径
//        String uploadPath = "webapp/imgs/" + fileName;
//        filePart.write(uploadPath);
//        // 返回上传成功的响应
//        response.getWriter().write("successful: " + fileName);

//    private static String getSubmittedFileName(Part part) {
//        for (String cd : part.getHeader("content-disposition").split(";")) {
//            if (cd.trim().startsWith("filename")) {
//                String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
//                return fileName.substring(fileName.lastIndexOf('/') + 1).substring(fileName.lastIndexOf('\\') + 1);
//            }
//        }
//        return null;
//    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
