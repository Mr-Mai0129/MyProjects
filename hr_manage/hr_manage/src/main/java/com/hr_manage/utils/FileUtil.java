package com.hr_manage.utils;

import java.io.File;
import java.util.UUID;

public class FileUtil {

    //删除之前处理文件
    public  static File dealFileForDelete(String filePath,String tergetPath){
        String fileName  = filePath.substring(filePath.lastIndexOf("/")+1);
        return  new File(tergetPath+fileName);
    }


    //文件上传之前的处理
    public  static  String dealFileForUpload(String fileName){
        //获取后缀
        String exts =fileName.substring(fileName.lastIndexOf(".")+1);
        //拼接文件名
        String name = UUID.randomUUID().toString() + "." + exts;
        return  name;
    }


}
