package com.hr_manage.service;

import com.hr_manage.entity.File;


public interface IFileService {

    /**
     * 增加文件表记录
     * @param file
     * @return
     */
    int add(File file);


    /**
     * 根据ID删除文件表一条记录
     * @param id
     * @return
     */
    int deleteById(Integer id);
}
