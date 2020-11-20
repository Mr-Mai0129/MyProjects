package com.hr_manage.controller;

import com.hr_manage.dto.FileJson;
import com.hr_manage.service.IFileService;
import com.hr_manage.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RequestMapping("/api/file")
@RestController
public class FileController {

    @Value("${filePath}")
    private  String filePath;

    @Autowired
    private IFileService fileService;

    @Autowired
    private FileJson fileJson;

    //上传文件
    @RequestMapping("/uploadfile.do")
    public FileJson uploadFile(MultipartFile file) {
        String url = "https://localhost:8000/api/files/";
        com.hr_manage.entity.File f = new com.hr_manage.entity.File();
        // 判断文件是否有内容
            if (file.isEmpty()) {
               fileJson.setMessage("文件为空,上传文件失败");
                fileJson.setCode(0);
                return  fileJson;
            }
            String fileName = file.getOriginalFilename();
        String targetName = FileUtil.dealFileForUpload(fileName);
        java.io.File dest = new File(filePath +targetName);
        // 如果pathAll路径不存在，则创建相关该路径涉及的文件夹;
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            try {
                file.transferTo(dest);
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            f.setPath(url + targetName);
            f.setName(targetName);
            fileService.add(f);
            fileJson.setPath(url + targetName);
            fileJson.setName(targetName);
            fileJson.setId(f.getId());
            fileJson.setCode(1);
            fileJson.setMessage("上传成功");
            return fileJson;
    }


    //删除文件
    @RequestMapping("/remove.do")
    public FileJson removeFile(@RequestParam("id") Integer fid,String path) {
        File file = FileUtil.dealFileForDelete(path,filePath);
        if (file.exists()) {
            file.delete();
            try {
                fileService.deleteById(fid);
            } catch (DataIntegrityViolationException e) {
                e.printStackTrace();
                fileJson.setCode(0);
                fileJson.setMessage("有其他表对其引用，无法删除");
                return fileJson;
            }
            fileJson.setCode(1);
            fileJson.setMessage("文件删除成功");
            return fileJson;
        }
        fileJson.setCode(0);
        fileJson.setMessage("文件不存在,文件删除失败");
        return fileJson;
    }

    }

