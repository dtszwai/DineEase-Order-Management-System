package org.order.constants;


import java.util.Set;

public class TableStatus {
  public static final String AVAILABLE = "AVAILABLE";
  public static final String RESERVED = "RESERVED";
  public static final String OCCUPIED = "OCCUPIED";

  public static final Set<String> STATUSES = Set.of(AVAILABLE, RESERVED, OCCUPIED);
}
