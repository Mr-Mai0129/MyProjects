package com.hr_manage.controller;

import com.hr_manage.dto.ApiJson;
import com.hr_manage.entity.Employee;
import com.hr_manage.service.IEmployeeService;
import com.hr_manage.service.IFileService;
import com.hr_manage.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 员工信息接口Controller类
 */
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Value("${filePath}")
    private  String filePath;

    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private ApiJson apiJson;

    @Autowired
    private IFileService fileService;

    /**
     * 根据条件查询员工信息
     *
     * @return
     */
    @RequestMapping("/select.do")
    public ApiJson findByCondition(Integer id, String name, Integer departmentId, Integer postId, Integer index) {
        ApiJson<Employee> employeeApiJson = new ApiJson<>();
        Integer startIndex = null;
        if (index != null) {
            startIndex = (index - 1) * 10;
        }
        List<Employee> employees = employeeService.findByCondition(id, name, departmentId, postId, startIndex);
        if (employees != null) {
            int total = employeeService.selectTotalByCondition(id, name, departmentId, postId);
            employeeApiJson.setCode(1);
            employeeApiJson.setList(employees);
            employeeApiJson.setTotal(total);
            employeeApiJson.setMessage("查询成功");
        }
        return employeeApiJson;
    }


    /**
     * 查询员工id、姓名、部门名的接口
     *
     * @return
     */
    @RequestMapping("/select_id_and_name.do")
    public ApiJson selectIdAndName() {
        List<Employee> employees = employeeService.listAll();
        if (employees != null) {
            apiJson.setCode(1);
            apiJson.setList(employees);
        }
        return apiJson;
    }


    /**
     * 根据ID删除员工信息
     *
     * @param id
     * @return
     */
    @RequestMapping("/delete.do")
    public ApiJson delete(Integer id) {
        if (id == null) {
            apiJson.setCode(0);
            apiJson.setMessage("无此用户信息");
            return apiJson;
        }
        //先删除对应的简历
        List<Employee> employees = employeeService.findByCondition(id, null, null, null, null);
        Employee employee = employees.get(0);
        File file = FileUtil.dealFileForDelete(employee.getFilePath(),filePath);
        if (file.exists()) {
            file.delete();
            try {
                fileService.deleteById(employee.getFileId());
                employeeService.delete(id);
            } catch (DataIntegrityViolationException e) {
                e.printStackTrace();
                apiJson.setCode(0);
                apiJson.setMessage("有其他表对其引用,无法删除");
                return apiJson;
            }
            apiJson.setCode(1);
            apiJson.setMessage("删除成功");
            return apiJson;
        }
        apiJson.setCode(0);
        apiJson.setMessage("删除失败");
        return  apiJson;
    }


    /**
     * 添加员工信息
     *
     * @param name
     * @param sex
     * @param status
     * @param did
     * @param pid
     * @param phone
     * @param age
     * @param address
     * @param entryTime
     * @return
     */
    @RequestMapping("/add.do")
    public ApiJson add(String name, int sex, String status, int did, int pid, String phone, int age, String address, String entryTime, int fid) {
        Employee employee = new Employee();
        //将entryTime从String转换为sql类型
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date cDate;
        java.sql.Date dd = null;
        try {
            cDate = sdf.parse(entryTime);
            dd = new java.sql.Date(cDate.getTime());

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        employee.setEntryTime(dd);
        employee.setName(name);
        employee.setSex(sex);
        employee.setStatus(status);
        employee.setDepartmentId(did);
        employee.setPostId(pid);
        employee.setPhone(phone);
        employee.setAddress(address);
        employee.setAge(age);
        employee.setFileId(fid);
        if (employee == null) {
            apiJson.setCode(0);
            apiJson.setMessage("添加失败");
            return apiJson;
        }
        employeeService.add(employee);
        apiJson.setCode(1);
        apiJson.setId(employee.getId());
        apiJson.setMessage("添加成功");
        return apiJson;
    }

    /**
     * 修改员工信息
     *
     * @param id
     * @param name
     * @param sex
     * @param status
     * @param did
     * @param pid
     * @param phone
     * @param age
     * @param address
     * @param entryTime
     * @return
     */
    @RequestMapping("/update.do")
    public ApiJson update(int id, String name, int sex, String status, int did, int pid, String phone, int age, String address, String entryTime, int fid) {
        Employee employee = new Employee();
        //将entryTime从String转换为sql类型
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date cDate;
        java.sql.Date dd = null;
        try {
            cDate = sdf.parse(entryTime);
            dd = new java.sql.Date(cDate.getTime());

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        employee.setEntryTime(dd);
        employee.setId(id);
        employee.setName(name);
        employee.setSex(sex);
        employee.setStatus(status);
        employee.setDepartmentId(did);
        employee.setPostId(pid);
        employee.setPhone(phone);
        employee.setAddress(address);
        employee.setAge(age);
        employee.setFileId(fid);
        if (employee == null) {
            apiJson.setCode(0);
            apiJson.setMessage("修改失败");
            return apiJson;
        }
        try {
            employeeService.update(employee);
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            apiJson.setCode(0);
            apiJson.setMessage("有其他表对其引用,无法修改");
            return apiJson;
        }
        apiJson.setCode(1);
        apiJson.setMessage("修改成功");
        return apiJson;
    }

}
