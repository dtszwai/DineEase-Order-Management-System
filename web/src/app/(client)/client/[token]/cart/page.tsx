"use client";

import { CartContext } from "@/components/client/CartContext";
import { useToast } from "@/components/ui/use-toast";
import { postOrder } from "@/components/api/client";
import { useContext, useState } from "react";

export default function Cart({ params }: { params: { token: string } }) {
  const { cart, updateQuantity, removeFromCart, clearCart } =
    useContext(CartContext);
  const { toast } = useToast();

  const submitOrder = () => {
    const orders = Object.keys(cart).map((productId) => {
      const { quantity, data } = cart[Number(productId)];
      return {
        productId: data.id,
        quantity,
      };
    });

    postOrder(params.token, orders);
    clearCart();
    toast({
      title: "Order submitted",
      description: "Your order has been submitted successfully",
    });
  };

  return (
    <div className="p-4">
      <h2 className="text-2xl font-bold mb-4">Your Cart</h2>
      {Object.keys(cart).length === 0 ? (
        <p>Your cart is empty.</p>
      ) : (
        <>
          <table className="w-full border-collapse">
            <thead>
              <tr>
                <th className="border p-2">Item</th>
                <th className="border p-2">Quantity</th>
                <th className="border p-2">Price</th>
                <th className="border p-2">Total</th>
                <th className="border p-2">Actions</th>
              </tr>
            </thead>
            <tbody>
              {Object.keys(cart).map((productId) => {
                const { quantity, data } = cart[Number(productId)];
                return (
                  <tr key={productId}>
                    <td className="border p-2">{data.name}</td>
                    <td className="border p-2">
                      <input
                        type="number"
                        value={quantity}
                        min="1"
                        onChange={(e) =>
                          updateQuantity(
                            Number(productId),
                            cart[Number(productId)].data,
                            Number(e.target.value) - quantity // get the diff to update quantity
                          )
                        }
                        className="w-16 p-1 border rounded"
                      />
                    </td>
                    <td className="border p-2">${data.price.toFixed(2)}</td>
                    <td className="border p-2">
                      ${(data.price * quantity).toFixed(2)}
                    </td>
                    <td className="border p-2">
                      <button
                        onClick={() => removeFromCart(Number(productId))}
                        className="bg-red-500 text-white p-1 rounded hover:bg-red-600"
                      >
                        Remove
                      </button>
                    </td>
                  </tr>
                );
              })}
            </tbody>
            <tfoot>
              <tr>
                <td colSpan={3} className="border p-2 font-bold">
                  Total
                </td>
                <td className="border p-2 font-bold">
                  $
                  {Object.keys(cart)
                    .reduce(
                      (acc, productId) =>
                        acc +
                        cart[Number(productId)].data.price *
                          cart[Number(productId)].quantity,
                      0
                    )
                    .toFixed(2)}
                </td>
                <td className="border p-2"></td>
              </tr>
            </tfoot>
          </table>
          <button
            onClick={submitOrder}
            className="bg-green-500 text-white p-2 rounded mt-4 hover:bg-green-600"
          >
            Submit Order
          </button>
        </>
      )}
    </div>
  );
}
