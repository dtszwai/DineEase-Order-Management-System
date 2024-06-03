"use client";

import { ColumnDef } from "@tanstack/react-table";
import { CellAction } from "./cell-action";
import { Badge } from "@/components/ui/badge";
import Link from "next/link";
import paths from "@/paths";
import Image from "next/image";
import { Product } from "@/types/ApiResponse";

export const columns: ColumnDef<Product>[] = [
  {
    accessorKey: "img",
    header: () => <span className="sr-only">Image</span>,
    cell: ({ row }) => {
      return (
        row.original.image && (
          <Image
            src={row.original.image}
            alt={row.original.name}
            className="aspect-square rounded-md object-cover"
            height={64}
            width={64}
          />
        )
      );
      // );
    },
    enableSorting: false,
  },
  {
    accessorKey: "name",
    header: "Name",
    cell: ({ row }) => {
      return (
        <Link href={`${paths.product}/${row.original.id}`}>
          {row.original.name}
        </Link>
      );
    },
  },
  {
    accessorKey: "displayOrder",
    header: "Order",
  },
  {
    accessorKey: "description",
    header: "Description",
  },
  {
    accessorKey: "status",
    header: "Status",
    cell: ({ getValue }) => {
      const status = getValue() as string;
      return <Badge variant="outline">{status}</Badge>;
    },
    enableSorting: false,
  },
  {
    accessorKey: "price",
    header: "Price",
    cell: ({ row }) => {
      const price = parseFloat(row.getValue("price"));
      return new Intl.NumberFormat("en-US", {
        style: "currency",
        currency: "USD",
      }).format(price);
    },
  },
  {
    accessorKey: "categoryName",
    header: "Category",
    cell: ({ row }) => {
      const categoryName = row.original.categoryName;
      const categoryId = row.original.categoryId;
      return (
        <Link href={`${paths.category}/${categoryId}`}>{categoryName}</Link>
      );
    },
    enableSorting: false,
  },
  {
    accessorKey: "updatedAt",
    header: "Updated At",
    cell: ({ getValue }) => {
      const updatedAt = getValue() as Date;
      return new Date(updatedAt).toDateString();
    },
    enableSorting: false,
  },
  {
    accessorKey: "updatedBy",
    header: "Updated By",
    enableSorting: false,
  },
  {
    id: "actions",
    cell: ({ row }) => <CellAction data={row.original} />,
    enableSorting: false,
  },
];
