"use server";

import { CustomerToken } from "@/types/ApiResponse";
import { get, post } from "@/components/api/admin";

export const createCustomer = async (tableId: number, formData: FormData) => {
  const numPeople = formData.get("numPeople");
  await post("/customers", { tableId: tableId, numPeople });
};

export const getToken = async (customerId: number) => {
  const token = await get<CustomerToken>(`/customers/${customerId}/token`);
  return token;
};

export const checkout = async (customerId: number) => {
  await post(`/customers/${customerId}/checkout`);
};
