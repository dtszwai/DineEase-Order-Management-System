package org.order.mappers;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.order.annotations.FillUpdate;
import org.order.enums.FillType;
import org.order.pojo.dto.EmployeeLoginDTO;
import org.order.pojo.dto.query.EmployeeQueryDTO;
import org.order.pojo.entity.Employee;
import org.order.pojo.vo.EmployeeVO;
import java.util.List;

/**
 * Mapper for handling employee related operations.
 */
@Mapper
public interface EmployeeMapper {

  /**
   * Create a new employee.
   *
   * @param employee the new employee data
   */
  @Insert("INSERT INTO employee (name, username, password, created_at, created_by, updated_at, updated_by)" +
          " VALUES (#{name}, #{username}, #{password}, #{createdAt}, #{createdBy}, #{updatedAt}, #{updatedBy})")
  @FillUpdate(FillType.INSERT)
  void createEmployee(Employee employee);

  /**
   * Get a list of employees.
   *
   * @param query the query parameters for fetching employees
   * @return a list of EmployeeVO
   */
  List<EmployeeVO> listEmployees(EmployeeQueryDTO query);

  /**
   * Login an employee.
   *
   * @param employeeLoginDTO the login data of the employee
   * @return a Employee object
   */
  @Select("SELECT * FROM employee WHERE username = #{username} AND password = #{password}")
  Employee loginEmployee(EmployeeLoginDTO employeeLoginDTO);

  /**
   * Update a specific employee.
   *
   * @param employee the new employee data
   */
  @FillUpdate(FillType.UPDATE)
  void updateEmployee(Employee employee);

  /**
   * Delete a specific employee by id.
   *
   * @param id the id of the employee
   */
  @Delete("DELETE FROM employee WHERE id = #{id}")
  void deleteEmployee(Long id);

  /**
   * Get a specific employee by id.
   *
   * @param id the id of the employee
   * @return a EmployeeVO object
   */
  @Select("SELECT e.id, e.username, e.name, e.status, e.created_at, e.updated_at, ce.name AS created_by, ue.name AS updated_by FROM employee e LEFT JOIN employee ce ON e.created_by = ce.id LEFT JOIN employee ue ON e.updated_by = ue.id WHERE e.id = #{id}")
  EmployeeVO getEmployee(Long id);
}