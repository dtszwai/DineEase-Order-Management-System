package org.order.controllers.admin;

import org.order.pojo.vo.TableVO;
import org.order.services.TableService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * Controller for handling table related requests.
 */
@RestController
@RequestMapping("/admin/tables")
public class TableController {

  private final TableService tableService;

  /**
   * Constructor for TableController.
   *
   * @param tableService the service to handle table operations
   */
  public TableController(TableService tableService) {
    this.tableService = tableService;
  }

  /**
   * Get a list of tables.
   *
   * @param id       the id of the table
   * @param status   the status of the table
   * @param numSeats the number of seats at the table
   * @return a list of TableVO
   */
  @GetMapping
  public List<TableVO> listTables(Long id, String status, Integer numSeats) {
    return tableService.listTables(id, status, numSeats);
  }
}