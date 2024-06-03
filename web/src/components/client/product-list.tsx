"use client";

import { Product as IProduct } from "@/types/ApiResponse";
import Image from "next/image";
import { useContext } from "react";
import { CartContext } from "./CartContext";
import { useToast } from "../ui/use-toast";
import { Button } from "../ui/button";

const Product = ({ product }: { product: IProduct }) => {
  const { cart, updateQuantity } = useContext(CartContext);
  const { toast } = useToast();
  const isAvailable = ![product.status, product.categoryStatus].includes(
    "INACTIVE"
  );

  function addToCard(product: IProduct): void {
    updateQuantity(product.id, product);
    toast({
      title: "Product added to cart",
      description: `${product.name} has been added to the cart`,
    });
  }

  return (
    <div className="bg-white p-4 shadow rounded flex flex-col justify-between">
      <h3 className="text-lg font-bold">{product.name}</h3>
      <div className="flex flex-row gap-3">
        {product.image && (
          <Image
            src={product.image}
            alt={product.name}
            className="object-cover mb-2"
            height={80}
            width={80}
          />
        )}
        <p className="text-gray-700">{product.description}</p>
      </div>
      <div className="flex justify-between items-center mt-2">
        <span className="text-xl font-bold">${product.price}</span>
        <span>{cart[product.id]?.quantity}</span>
        <Button
          onClick={() => addToCard(product)}
          className="text-white p-2 rounded border"
          disabled={!isAvailable}
        >
          {isAvailable ? `Add to Cart` : `Not Available`}
        </Button>
      </div>
    </div>
  );
};

export default function ProductList({ products }: { products: IProduct[] }) {
  return (
    <div className="p-4 w-3/4">
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
        {products.map((product) => (
          <Product key={product.id} product={product} />
        ))}
      </div>
    </div>
  );
}
