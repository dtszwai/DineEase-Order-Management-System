import { HistoryOrder } from "@/types/ApiResponse";

const getCategories = async (token: string) => {
  const res = await fetch(`${process.env.CLIENT_URL}/${token}/categories`);
  return res.json();
};

const getProducts = async (token: string, categoryId: number) => {
  const res = await fetch(
    `${process.env.CLIENT_URL}/${token}/products?categoryId=${categoryId}`
  );
  return res.json();
};

type Order = {
  productId: number;
  quantity: number;
};

const postOrder = async (token: string, orders: Order[]) => {
  const CLIENT_URL = "http://localhost:8080/client";
  const orderRequest = {
    products: orders,
  };

  const res = await fetch(`${CLIENT_URL}/${token}/orders/submit`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(orderRequest),
  });

  if (!res.ok) {
    throw new Error("Failed to submit order");
  }
};

const getHistory = async (token: string): Promise<HistoryOrder[]> => {
  const res = await fetch(`${process.env.CLIENT_URL}/${token}/orders`);
  return res.json();
};

export { getCategories, getProducts, postOrder, getHistory };
