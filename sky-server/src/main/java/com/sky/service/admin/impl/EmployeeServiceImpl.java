package com.sky.service.admin.impl;

import com.sky.constant.MessageConstant;
import com.sky.constant.PasswordConstant;
import com.sky.constant.StatusConstant;
import com.sky.context.BaseContext;
import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.exception.AccountLockedException;
import com.sky.exception.AccountNotFoundException;
import com.sky.exception.EmployeeNameExistException;
import com.sky.exception.PasswordErrorException;
import com.sky.mapper.admin.EmployeeMapper;
import com.sky.pojo.Employee;
import com.sky.result.Result;
import com.sky.service.admin.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@Service
public class EmployeeServiceImpl  implements EmployeeService {

    @Resource
    private EmployeeMapper employeeMapper;

    /**
     * 员工登录
     *
     * @param employeeLoginDTO
     * @return
     */
    @Override
    public Employee login(EmployeeLoginDTO employeeLoginDTO) {
        String username = employeeLoginDTO.getUsername();
        String password = employeeLoginDTO.getPassword();//

        //1、根据用户名查询数据库中的数据  select * from employee where username = #{username}
        Employee employee = employeeMapper.getByUsername(username); //

        //2、处理各种异常情况（用户名不存在、密码不对、账号被锁定）
        if (employee == null) {
            //账号不存在
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        //密码比对
        // TODO 后期需要进行md5加密，然后再进行比对
        if (!password.equals(employee.getPassword())) {
            //密码错误
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        if (employee.getStatus() == StatusConstant.DISABLE) {
            //账号被锁定
            throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
        }
        //3、返回实体对象
        return employee;
    }

    @Override
    @Transactional
    public Result<String> add(EmployeeDTO employeeDTO) {
        // 一.校验参数

        //判断用户名是否存在
        Employee dbEmployee = employeeMapper.getByUsername(employeeDTO.getUsername());
        if(dbEmployee!=null){
            throw new EmployeeNameExistException(dbEmployee.getName()+"已存在");
        }
        // 二.处理业务
        Employee employee = new Employee();
        //只有数据类型跟属性名都相同的情况下，才能拷贝成功
        BeanUtils.copyProperties(employeeDTO, employee);
        // 1.补全实体
        employee.setPassword(DigestUtils.md5DigestAsHex(PasswordConstant.DEFAULT_PASSWORD.getBytes()));
        employee.setStatus(StatusConstant.ENABLE);
        LocalDateTime now = LocalDateTime.now();
        employee.setCreateTime(now);
        employee.setUpdateTime(now);

        //从ThreadLocal中获取登录的员工ID
        Long empId = BaseContext.getCurrentId();
        employee.setCreateUser(empId);
        employee.setUpdateUser(empId);
        // 2.保存到数据库
        employeeMapper.insert(employee);
        // 三.封装数据
        return Result.success();
    }



}
