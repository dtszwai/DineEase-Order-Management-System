/**
 * Value Object for Category for admin.
 */
export interface Category {
  /**
   * The ID of the category.
   */
  id: number;

  /**
   * The name of the category.
   */
  name: string;

  /**
   * The display order of the category.
   */
  displayOrder: number;

  /**
   * The status of the category.
   */
  status: string;

  /**
   * The timestamp when the category was created.
   */
  createdAt: Date;

  /**
   * The employee who created the category.
   */
  createdBy: string;

  /**
   * The timestamp when the category was last updated.
   */
  updatedAt: Date;

  /**
   * The employee who last updated the category.
   */
  updatedBy: string;

  /**
   * The number of products in the category.
   */
  productCount: number;
}

/**
 * This interface represents a product entity. It is for the admin view.
 */
export interface Product {
  /**
   * The ID of the product.
   */
  id: number;

  /**
   * The name of the product.
   */
  name: string;

  /**
   * The description of the product.
   */
  description: string;

  /**
   * The price of the product.
   */
  price: BigDecimal;

  /**
   * The image of the product.
   */
  image: string | null;

  /**
   * The status of the product.
   */
  status: string;

  /**
   * The status of the category.
   */
  categoryStatus: string;

  /**
   * The category ID of the product.
   */
  categoryId: number;

  /**
   * The category name of the product.
   */
  categoryName: string;

  /**
   * The display order of the product.
   */
  displayOrder: number;

  /**
   * The timestamp when the product was created.
   */
  createdAt: Date;

  /**
   * The employee who created the product.
   */
  createdBy: string;

  /**
   * The timestamp when the product was last updated.
   */
  updatedAt: Date;

  /**
   * The employee who last updated the product.
   */
  updatedBy: string;
}

type TableStatus = "AVAILABLE" | "OCCUPIED";

interface TableWithCustomer {
  id: number;
  status: TableStatus;
  numSeats: number;
  numPeople: number;
  seatedAt: Date;
  customerId: number;
}

export type Table = {
  id: number;
  status: TableStatus;
  numSeats: number;
} & Partial<TableWithCustomer>;

export interface CustomerToken {
  /**
   * The ID of the customer token.
   */
  id: number;

  /**
   * The ID of the customer associated with the token.
   */
  customerId: number;

  /**
   * The expiry time of the token. After this time, the token is considered expired and cannot be
   * used.
   */
  expiryTime: Date;

  /**
   * The token associated with the customer. This token is a unique string that is used to identify
   * the customer.
   */
  token: string;

  /**
   * Indicates whether the token is expired. If true, the token is expired and cannot be used. If
   * false, the token is still valid and can be used.
   */
  isExpired: boolean;

  /**
   * The ID of the employee who created this record.
   */
  createdBy: number;

  /**
   * The timestamp when this record was created.
   */
  createdAt: Date;

  /**
   * The ID of the employee who last updated this record.
   */
  updatedBy: number;

  /**
   * The timestamp when this record was last updated.
   */
  updatedAt: Date;
}

export interface HistoryOrder {
  id: number;
  products: OrderedProductVO[];
  totalPrice: number;
  createdAt: Date;
  status: string;
}

export interface OrderedProduct {
  name: string;
  description: string | null;
  totalPrice: number;
  image: string | null;
  quantity: number;
}
