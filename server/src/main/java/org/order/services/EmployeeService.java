package org.order.services;

import lombok.extern.slf4j.Slf4j;
import org.order.exceptions.AccountLockedException;
import org.order.exceptions.BadRequestException;
import org.order.constants.EmployeeStatus;
import org.order.pojo.dto.query.EmployeeQueryDTO;
import org.order.pojo.entity.Employee;
import org.order.mappers.EmployeeMapper;
import org.order.pojo.dto.EmployeeDTO;
import org.order.pojo.dto.EmployeeLoginDTO;
import org.order.pojo.vo.EmployeeVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import java.util.List;
import java.util.Objects;

/**
 * Service for managing employees.
 */
@Service
@Slf4j
public class EmployeeService {

  private final EmployeeMapper employeeMapper;

  /**
   * Constructor for EmployeeService.
   *
   * @param employeeMapper the mapper to handle employee operations
   */
  public EmployeeService(EmployeeMapper employeeMapper) {
    this.employeeMapper = employeeMapper;
  }

  /**
   * Login an employee.
   *
   * @param employeeLoginDTO the login data of the employee
   * @return a Employee object
   */
  public Employee login(EmployeeLoginDTO employeeLoginDTO) {
    String username = employeeLoginDTO.getUsername();
    String password = employeeLoginDTO.getPassword();

    password = DigestUtils.md5DigestAsHex(password.getBytes());
    employeeLoginDTO.setPassword(password);

    Employee employee = employeeMapper.loginEmployee(employeeLoginDTO);

    // Check if the Employee exists and the password is correct
    if (employee == null || !password.equals(employee.getPassword())) {
      log.warn("Failed login attempt for account: {}", username);
      throw new BadRequestException();
    }

    if (Objects.equals(employee.getStatus(), EmployeeStatus.LOCKED)) {
      log.warn("Failed login attempt for locked account: {}", username);
      throw new AccountLockedException();
    }

    return employee;
  }

  /**
   * Create a new employee.
   *
   * @param employeeDTO the new employee data
   */
  public void createEmployee(EmployeeDTO employeeDTO) {
    Employee employee = new Employee();
    BeanUtils.copyProperties(employeeDTO, employee);

    try {
      employeeMapper.createEmployee(employee);
    } catch (Exception e) {
      log.error("Failed to add employee: {}", employeeDTO);
      throw new BadRequestException();
    }
  }

  /**
   * Get a list of employees.
   *
   * @param query the query parameters for fetching employees
   * @return a list of EmployeeVO
   */
  public List<EmployeeVO> listEmployees(EmployeeQueryDTO query) {
    return employeeMapper.listEmployees(query);
  }

  /**
   * Update a specific employee by id.
   *
   * @param id          the id of the employee
   * @param employeeDTO the new employee data
   */
  public void updateEmployee(Long id, EmployeeDTO employeeDTO) {
    Employee employee = new Employee();
    BeanUtils.copyProperties(employeeDTO, employee);
    employee.setId(id);
    employeeMapper.updateEmployee(employee);
  }

  /**
   * Delete a specific employee by id.
   *
   * @param id the id of the employee
   */
  public void deleteEmployee(Long id) {
    employeeMapper.deleteEmployee(id);
  }

  /**
   * Get a specific employee by id.
   *
   * @param id the id of the employee
   * @return a EmployeeVO object
   */
  public EmployeeVO getEmployee(Long id) {
    return employeeMapper.getEmployee(id);
  }
}
