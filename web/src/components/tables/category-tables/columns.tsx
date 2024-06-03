"use client";
import { Checkbox } from "@/components/ui/checkbox";
import { ColumnDef } from "@tanstack/react-table";
import { CellAction } from "./cell-action";
import { Badge } from "@/components/ui/badge";
import Link from "next/link";
import { Category } from "@/types/ApiResponse";

export const columns: ColumnDef<Category>[] = [
  {
    accessorKey: "displayOrder",
    header: "Order",
  },
  {
    accessorKey: "name",
    header: "Name",
    cell: ({ row }) => {
      return (
        <Link href={`/dashboard/category/${row.original.id}`}>
          {row.original.name}
        </Link>
      );
    },
  },
  {
    accessorKey: "status",
    header: "Status",
    cell: ({ getValue }) => {
      const status = getValue() as string;
      return <Badge variant="outline">{status}</Badge>;
    },
  },
  {
    accessorKey: "productCount",
    header: "Total Products",
  },
  {
    accessorKey: "updatedAt",
    header: "Updated At",
    cell: ({ getValue }) => {
      const updatedAt = getValue() as Date;
      return new Date(updatedAt).toDateString();
    },
  },
  {
    accessorKey: "updatedBy",
    header: "Updated By",
  },
  {
    id: "actions",
    cell: ({ row }) => <CellAction data={row.original} />,
  },
];
