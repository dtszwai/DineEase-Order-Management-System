import { Label } from "@/components/ui/label";
import { getHistory } from "@/components/api/client";
import Image from "next/image";

export const revalidate = 0;

export default async function History({
  params,
}: {
  params: { token: string };
}) {
  const orderHistory = await getHistory(params.token);
  const totalPrice = orderHistory.reduce(
    (acc, order) => acc + order.totalPrice,
    0
  );

  return (
    <div>
      <div className="p-4">
        <h2 className="text-2xl font-bold mb-4">Order History</h2>
        <p className="text-lg font-bold">Total Spent: ${totalPrice}</p>
        {orderHistory
          .sort((a, b) => b.id - a.id)
          .map((order) => (
            <div key={order.id} className="bg-white p-4 mb-4 shadow rounded">
              <h3 className="text-xl font-bold">Order #{order.id}</h3>
              <p className="text-gray-600">
                Date: {new Date(order.createdAt).toLocaleTimeString()}
              </p>
              <div className="mt-4">
                {order.products.map((product, index) => (
                  <div key={index} className="flex items-center mb-2">
                    {product.image && (
                      <Image
                        src={product.image}
                        alt={product.name}
                        className="h-16 w-16 object-cover mr-4"
                        height={64}
                        width={64}
                      />
                    )}
                    <div>
                      <Label className="text-lg font-bold">
                        {product.name}
                      </Label>
                      {product.description && (
                        <p className="text-gray-600">{product.description}</p>
                      )}
                      <p className="text-gray-800">
                        Total Price: ${product.totalPrice}
                      </p>
                      <p className="text-gray-800">
                        Quantity: {product.quantity}
                      </p>
                    </div>
                  </div>
                ))}
              </div>
              <p className="text-lg font-bold mt-4">
                Total Price: ${order.totalPrice}
              </p>
            </div>
          ))}
      </div>
    </div>
  );
}
