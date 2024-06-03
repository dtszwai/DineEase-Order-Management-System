"use client";

import { Table as ITable, TableWithCustomer } from "@/types/ApiResponse";
import * as React from "react";
import { useState } from "react";
import { Button } from "@/components/ui/button";
import {
  Card,
  CardContent,
  CardDescription,
  CardFooter,
  CardHeader,
  CardTitle,
} from "@/components/ui/card";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";

import { createCustomer, checkout, getToken } from "./actions";
import {
  Dialog,
  DialogContent,
  DialogFooter,
  DialogHeader,
  DialogTitle,
  DialogTrigger,
} from "../ui/dialog";
import Link from "next/link";
import { QRCodeSVG } from "qrcode.react";
import { UtensilsCrossed } from "lucide-react";
import { Separator } from "../ui/separator";
import paths from "@/paths";
import { useRouter } from "next/navigation";

const TableCard = ({ table }: { table: ITable }) => {
  const router = useRouter();
  const [token, setToken] = useState<string | null>(null);

  React.useEffect(() => {
    (async function () {
      if (table.status === "OCCUPIED") {
        const token = await getToken(table.customerId as number);
        setToken(token.token);
      }
    })();
  }, [table]);

  const isOccupiedTable = (
    table: ITable
  ): table is ITable & TableWithCustomer => {
    return table.status === "OCCUPIED";
  };

  return (
    <Card className="w-[350px]">
      <CardHeader>
        <CardTitle>Table {table.id}</CardTitle>
        <CardDescription>{table.status}</CardDescription>
      </CardHeader>
      <CardContent>
        {isOccupiedTable(table) && (
          <div className="flex flex-col gap-3">
            <p>Customer ID: {table.customerId}</p>
            <p suppressHydrationWarning>
              Seated at:{" "}
              {table.seatedAt
                ? new Date(table.seatedAt).toLocaleTimeString()
                : "N/A"}
            </p>
            <p>Number of People: {table.numPeople}</p>
            <Dialog>
              <DialogTrigger asChild>
                <Button>Table Order Sheet</Button>
              </DialogTrigger>
              <DialogContent className="justify-items-center">
                <DialogHeader>
                  <DialogTitle>Restaurant</DialogTitle>
                  <UtensilsCrossed className="h-8 w-8 text-primary self-center" />
                  <Separator />
                </DialogHeader>
                Table Information: Table ID: {table.id}
                <Separator />
                Number of people: {table.numPeople}
                <Separator />
                Seat Time: {new Date(table.seatedAt).toLocaleTimeString()}
                <Separator />
                Scan to Order
                <Link href={`${paths.client}/${token}`}>
                  <QRCodeSVG
                    value={`${paths.domain}${paths.client}/${token}`}
                  />
                </Link>
                <DialogFooter>
                  <Button>Print</Button>
                </DialogFooter>
              </DialogContent>
            </Dialog>
            <Button
              onClick={() => {
                checkout(table.customerId);
                router.refresh();
              }}
            >
              Checkout
            </Button>
          </div>
        )}
        {table.status === "AVAILABLE" && (
          <div className="flex flex-col gap-3">
            <p className="pb-2">Number of seats: {table.numSeats}</p>
            <Label>Number of people</Label>
            <form
              action={(formData) => {
                createCustomer(table.id, formData);
                router.refresh();
              }}
              className="flex gap-2 flex-col"
            >
              <Input
                name="numPeople"
                type="number"
                defaultValue={1}
                min={1}
                max={table.numSeats}
                step={1}
              />
              <Button>Add Customer</Button>
            </form>
          </div>
        )}
      </CardContent>
      <CardFooter className="flex justify-between"></CardFooter>
    </Card>
  );
};

const Tables = ({ tables }: { tables: ITable[] }) => {
  return (
    <div className="flex flex-row flex-wrap gap-3">
      {tables.map((table) => (
        <TableCard key={table.id} table={table} />
      ))}
    </div>
  );
};

export default Tables;
