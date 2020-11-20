package com.hr_manage.controller;

import com.hr_manage.dto.ApiJson;
import com.hr_manage.entity.Salary;
import com.hr_manage.service.ISalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * 薪酬信息接口Controller类
 */
@RestController
@RequestMapping("/api/salary")
public class SalaryController {

    @Autowired
    private ISalaryService salaryService;

    @Autowired
    private ApiJson apiJson;

    /**
     * 根据条件查询薪酬信息
     *
     * @param id
     * @return
     */
    @RequestMapping("/select.do")
    public ApiJson list(Integer id, int index, String date, @RequestParam(value = "eids") List<Integer> eIds) {
        int startIndex = (index - 1) * 10;
        Date dd = null;
        if (date != null && date != "") {
            dd = StringToSqlDate(date);
        }
        List<Salary> salaries = salaryService.findByCondition(id, eIds, dd, startIndex);
        if (salaries != null) {
            int total = salaryService.selectTotalByCondition(id, eIds, dd);
            apiJson.setCode(1);
            apiJson.setList(salaries);
            apiJson.setTotal(total);
            apiJson.setMessage("查询成功");
        }
        return apiJson;

    }

    /**
     * 添加薪酬信息
     *
     * @param salary
     * @param date
     * @param status
     * @param eids
     * @return
     */
    @RequestMapping("/add.do")
    public ApiJson add(double salary, String date, int status, @RequestParam(value = "eids", required = false) List<String> eids) {
        Salary addSalary = new Salary();
        ArrayList<Integer> ids = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date cDate;
        Date dd = null;
        if (date != null && date != "") {
            dd = StringToSqlDate(date);
        }
        for (String eid : eids) {
            addSalary.setSalary(salary);
            addSalary.setEid(Integer.parseInt(eid));
            addSalary.setDate(dd);
            addSalary.setStatus(status);
            if (addSalary != null) {
                salaryService.add(addSalary);
                ids.add(addSalary.getId());
            }
        }
        apiJson.setIds(ids);
        apiJson.setCode(1);
        apiJson.setMessage("添加成功");
        return apiJson;
    }


    /**
     * 根据ID删除薪酬信息
     *
     * @param id
     * @return
     */
    @RequestMapping("/delete.do")
    public ApiJson delete(Integer id) {
        if (id == null) {
            apiJson.setCode(0);
            apiJson.setMessage("id为空");
        }
        salaryService.deleteById(id);
        apiJson.setCode(1);
        apiJson.setMessage("删除成功");
        return apiJson;
    }

    /**
     * 修改薪酬信息
     *
     * @param id
     * @param salary
     * @param date
     * @param status
     * @return
     */
    @RequestMapping("/update.do")
    public ApiJson update(int id, double salary, String date, int status) {
        Salary updateSalary = new Salary();
        Date dd = null;
        if (date != null && date != "") {
            dd = StringToSqlDate(date);
        }
        updateSalary.setId(id);
        updateSalary.setSalary(salary);
        updateSalary.setDate(dd);
        updateSalary.setStatus(status);
        if (updateSalary == null) {
            apiJson.setCode(0);
            apiJson.setMessage("信息为空");
        }
        salaryService.update(updateSalary);
        apiJson.setCode(1);
        apiJson.setMessage("修改成功");
        return apiJson;
    }


    //将date从String类型转换为SqlDate类型
    public Date StringToSqlDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date cDate;
        Date dd = null;
        try {
            cDate = sdf.parse(date);
            dd = new Date(cDate.getTime());

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return dd;
    }

}
