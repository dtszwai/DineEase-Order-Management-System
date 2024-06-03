"use client";
import { ColumnDef } from "@tanstack/react-table";
import { Badge } from "@/components/ui/badge";

export const columns: ColumnDef<any>[] = [
  {
    accessorKey: "customerId",
    header: "Customer",
  },
  {
    accessorKey: "tableId",
    header: "Table",
  },
  {
    accessorKey: "status",
    header: "Status",
    cell: ({ row }) => {
      const status = row.original.status;
      return <Badge variant="outline">{status}</Badge>;
    },
  },
  {
    accessorKey: "numPeople",
    header: "Number of People",
  },
  {
    accessorKey: "totalPrice",
    header: "Amount",
    cell: ({ row }) => {
      const amount = row.original.totalPrice;
      return <span className="text-right">${amount}</span>;
    },
  },
  {
    accessorKey: "updatedAt",
    header: "Date",
    cell: ({ row }) => {
      const date = row.original.updatedAt;
      return (
        <span className="hidden md:table-cell">
          {new Date(date).toDateString()}
        </span>
      );
    },
  },
];
