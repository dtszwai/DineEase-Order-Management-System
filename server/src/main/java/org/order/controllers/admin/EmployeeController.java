package org.order.controllers.admin;

import org.order.constants.JwtClaimsConstant;
import org.order.pojo.dto.query.EmployeeQueryDTO;
import org.order.pojo.entity.Employee;
import org.order.pojo.dto.EmployeeDTO;
import org.order.pojo.dto.EmployeeLoginDTO;
import org.order.pojo.vo.EmployeeLoginVO;
import org.order.pojo.vo.EmployeeVO;
import org.order.properties.JwtProperties;
import org.order.services.EmployeeService;
import org.order.utils.JwtUtil;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

/**
 * Controller for handling employee related requests.
 */
@RestController
@RequestMapping("/admin/employees")
public class EmployeeController {

  private final EmployeeService employeeService;

  private final JwtProperties jwtProperties;

  /**
   * Constructor for EmployeeController.
   *
   * @param employeeService the service to handle employee operations
   * @param jwtProperties   the properties for JWT token generation
   */
  public EmployeeController(EmployeeService employeeService, JwtProperties jwtProperties) {
    this.employeeService = employeeService;
    this.jwtProperties = jwtProperties;
  }

  /**
   * Login an employee.
   *
   * @param employeeLoginDTO the login data of the employee
   * @return a EmployeeLoginVO object
   */
  @PostMapping("/login")
  public EmployeeLoginVO login(@RequestBody EmployeeLoginDTO employeeLoginDTO) {
    Employee employee = employeeService.login(employeeLoginDTO);

    Map<String, Object> claim = Map.of(JwtClaimsConstant.CLAIM_KEY_USER_ID, employee.getId());
    String token = JwtUtil.createToken(
        jwtProperties.getAdminSecretKey(),
        jwtProperties.getAdminTtl(),
        claim);

    return EmployeeLoginVO.builder()
        .id(employee.getId())
        .username(employee.getUsername())
        .name(employee.getName())
        .token(token)
        .build();
  }

  /**
   * Get a list of employees.
   *
   * @param query the query parameters for fetching employees
   * @return a list of EmployeeVO
   */
  @GetMapping
  public List<EmployeeVO> listEmployees(EmployeeQueryDTO query) {
    return employeeService.listEmployees(query);
  }

  /**
   * Get a specific employee by id.
   *
   * @param id the id of the employee
   * @return a EmployeeVO object
   */
  @GetMapping("/{id}")
  public EmployeeVO getEmployee(@PathVariable Long id) {
    return employeeService.getEmployee(id);
  }

  /**
   * Create a new employee.
   *
   * @param employeeDTO the new employee data
   */
  @PostMapping
  public void createEmployee(@RequestBody EmployeeDTO employeeDTO) {
    employeeService.createEmployee(employeeDTO);
  }

  /**
   * Update a specific employee by id.
   *
   * @param id          the id of the employee
   * @param employeeDTO the new employee data
   */
  @PutMapping("/{id}")
  public void updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO) {
    employeeService.updateEmployee(id, employeeDTO);
  }

  /**
   * Delete a specific employee by id.
   *
   * @param id the id of the employee
   */
  @DeleteMapping("/{id}")
  public void deleteEmployee(@PathVariable Long id) {
    employeeService.deleteEmployee(id);
  }
}
