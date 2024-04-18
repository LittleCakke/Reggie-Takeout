package com.itheima.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.itheima.reggie.common.R;
import com.itheima.reggie.entity.Employee;
import com.itheima.reggie.service.EmployeeService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @author GRS
 * @since 2024/4/18 下午2:30
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController
{
    @Resource
    private EmployeeService employeeService;

    /**
     * 员工登录
     */
    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee)
    {
        // 1. 将页面提交的密码password进行MD5加密
        String password = DigestUtils.md5DigestAsHex(employee.getPassword().getBytes());

        // 2. 根据页面提交的用户名username查询数据库
        LambdaQueryWrapper<Employee> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Employee::getUsername, employee.getUsername());
        Employee emp = employeeService.getOne(wrapper);

        // 3. 如果没有查询到则返回登录失败结果
        if (emp == null) return R.error("登录失败");

        // 4. 密码比对，如果不一致则返回登录失败结果
        if (!emp.getPassword().equals(password)) return R.error("登录失败");

        // 5. 查看员工状态，如果为已禁用状态，则返回员工已禁用结果
        if (emp.getStatus() == 0) return R.error("账号已禁用");

        // 6. 登录成功，将员工ID存入Session并返回登录成功结果
        request.getSession().setAttribute("employee", emp.getId());
        return R.success(emp);
    }
}