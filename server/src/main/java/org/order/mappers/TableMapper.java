package org.order.mappers;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.order.annotations.FillUpdate;
import org.order.pojo.entity.Table;
import org.order.pojo.vo.TableVO;
import java.util.List;

/**
 * This interface provides methods for performing operations on the restaurant_table in the
 * database. It uses MyBatis annotations to map Java methods to SQL statements.
 */
@Mapper
public interface TableMapper {

  /**
   * Retrieves all tables from the restaurant_table.
   *
   * @param id       the ID of the table to retrieve.
   * @param status   the status of the table to retrieve.
   * @param numSeats the number of seats at the table to retrieve.
   * @return a list of all tables.
   */
  List<TableVO> listTables(Long id, String status, Integer numSeats);

  /**
   * Updates the status of a table in the restaurant_table.
   *
   * @param table the table to update.
   */
  @Update("UPDATE restaurant_table SET status = #{status}, updated_at = #{updatedAt}, updated_by = #{updatedBy} WHERE id = #{id}")
  @FillUpdate
  void updateTable(Table table);
}
