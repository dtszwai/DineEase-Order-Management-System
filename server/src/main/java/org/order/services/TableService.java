package org.order.services;

import org.order.constants.TableStatus;
import org.order.mappers.TableMapper;
import org.order.pojo.entity.Table;
import org.order.pojo.vo.TableVO;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service for managing tables.
 */
@Service
public class TableService {

  private final TableMapper tableMapper;

  /**
   * Constructor for TableService.
   *
   * @param tableMapper the mapper to handle table operations
   */
  public TableService(TableMapper tableMapper) {
    this.tableMapper = tableMapper;
  }

  /**
   * Updates a table in the database.
   *
   * @param table the table entity to be updated.
   */
  public void updateTable(Table table) {
    tableMapper.updateTable(table);
  }

  /**
   * Retrieves all tables from the database.
   *
   * @param id       the ID of the table to retrieve.
   * @param status   the status of the table to retrieve.
   * @param numSeats the number of seats at the table to retrieve.
   * @return a list of all table view objects.
   */
  public List<TableVO> listTables(Long id, String status, Integer numSeats) {
    if (status == null || !TableStatus.STATUSES.contains(status)) {
      status = null;
    }
    return tableMapper.listTables(id, status, numSeats);
  }
}