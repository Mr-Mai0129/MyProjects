package com.hr_manage.service.impl;

import com.hr_manage.entity.File;
import com.hr_manage.mapper.IEmployeeMapper;
import com.hr_manage.mapper.IFileMapper;
import com.hr_manage.service.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FileServiceImpl implements IFileService {

    @Autowired
    private IFileMapper fileMapper;

    @Autowired
    private IEmployeeMapper employeeMapper;

    @Override
    public int add(File file) {
        return fileMapper.add(file);
    }

    @Override
    public int deleteById(Integer id) {
        employeeMapper.updateFID(id);
        return fileMapper.deleteById(id);
    }
}
