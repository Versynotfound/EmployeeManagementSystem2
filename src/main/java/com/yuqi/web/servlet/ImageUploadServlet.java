package com.yuqi.web.servlet;
import com.alibaba.fastjson.JSON;
import com.yuqi.common.ResultDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 文件上传接口,设置大小不超过15M
 * @author yuqi
 */
@WebServlet("/imgUpload")
@MultipartConfig(maxFileSize = 16177215)
public class ImageUploadServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String resultUrl = "http://localhost:8080/EmployeeManagementSystem2";
        Part filePart = req.getPart("file");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        // 添加时间毫秒值以确保文件名的唯一性
        String uniqueFileName =System.currentTimeMillis() + "_" + fileName;

        InputStream fileContent = filePart.getInputStream();
        String resultPath = "";
        try {
            Path path = Paths.get(getServletContext().getRealPath("/img/"), uniqueFileName);
            resultPath = path.toString();
            Files.copy(fileContent, path);
        }catch (Exception e){
            System.out.println(e);
        }finally {
            if(null != fileContent) {
                fileContent.close();
            }
        }
        resultUrl = resultUrl + "/img/"+uniqueFileName;
        ResultDTO<String> result = ResultDTO.success(resultUrl);
        String resultString = JSON.toJSONString(result);
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(resultString);
    }
}
