import { NavItem } from "../types";

export type Employee = {
  id: number;
  username: string;
  name: string;
  status: string;
  createdAt: Date;
  updatedAt: Date;
  createdBy: number;
  updatedBy: number;
};

export const navItems: NavItem[] = [
  {
    title: "Dashboard",
    href: "/dashboard",
    icon: "dashboard",
    label: "Dashboard",
  },
  {
    title: "Order",
    href: "/dashboard/order",
    icon: "order",
    label: "order",
  },
  {
    title: "Table",
    href: "/dashboard/table",
    icon: "table",
    label: "table",
  },

  {
    title: "Product",
    href: "/dashboard/product",
    icon: "product",
    label: "product",
  },
  {
    title: "Category",
    href: "/dashboard/category",
    icon: "category",
    label: "category",
  },
];
