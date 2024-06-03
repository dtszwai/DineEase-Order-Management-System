"use client";

import { Product } from "@/types/ApiResponse";
import { createContext, useState } from "react";

type ItemType = {
  quantity: number;
  data: Product;
};

type CartContextType = {
  cart: { [productId: number]: ItemType };
  removeFromCart: (productId: number) => void;
  updateQuantity: (productId: number, product: Product, diff?: number) => void;
  clearCart: () => void;
};

const CartContext = createContext<CartContextType>({
  cart: {},
  removeFromCart: () => {},
  updateQuantity: () => {},
  clearCart: () => {},
});

const CartContextProvider = ({ children }: { children: React.ReactNode }) => {
  const [cart, setCart] = useState<{ [productId: number]: ItemType }>({});

  const removeFromCart = (productId: number) => {
    setCart((prevCart) => {
      const updatedCart = { ...prevCart };
      delete updatedCart[productId];
      return updatedCart;
    });
  };

  const clearCart = () => {
    setCart({});
  };

  const updateQuantity = (
    productId: number,
    product: Product,
    diff: number = 1
  ) => {
    if (!cart[productId]) {
      setCart((prevCart) => ({
        ...prevCart,
        [productId]: {
          quantity: 1,
          data: product,
        },
      }));
      return;
    } else {
      setCart((prevCart) => ({
        ...prevCart,
        [productId]: {
          ...prevCart[productId],
          quantity: prevCart[productId].quantity + diff,
        },
      }));
    }
  };

  return (
    <CartContext.Provider value={{ cart, removeFromCart, updateQuantity, clearCart }}>
      {children}
    </CartContext.Provider>
  );
};

export { CartContextProvider, CartContext };
