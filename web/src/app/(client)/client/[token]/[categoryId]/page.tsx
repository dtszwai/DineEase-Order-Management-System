import Sidebar from "@/components/client/layout/sidebar";
import ProductList from "@/components/client/product-list";
import { getCategories, getProducts } from "@/components/api/client";

export default async function Home({
  params,
}: {
  params: { token: string; categoryId: string };
}) {
  const categoryId = parseInt(params.categoryId) || 1;
  const token = params.token;

  const categories = await getCategories(token);
  const products = await getProducts(token, categoryId);

  return (
    <div className="flex">
      <Sidebar categories={categories} />
      <ProductList products={products} />
    </div>
  );
}
