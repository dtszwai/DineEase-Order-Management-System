import BreadCrumb from "@/components/breadcrumb";
import { ProductForm } from "@/components/forms/product-form";
import { get } from "@/components/api/admin";
import paths from "@/paths";
import { Category, Product } from "@/types/ApiResponse";
import { Metadata } from "next/types";
import React from "react";

export const metadata: Metadata = {
  title: "Product",
};

export default async function Page({
  params,
}: {
  params: { productId: string };
}) {
  const productId = parseInt(params.productId);
  const product = (await get<Product[]>(`/products?id=${productId}`))[0];

  const categories = (await get<Category[]>("/categories")) || [];

  let title: string;
  title = product ? "Edit" : "Create";

  const breadcrumbItems = [
    { title: "Product", link: `${paths.product}` },
    { title: title, link: `${paths.product}/${productId}` },
  ];
  return (
    <div className="flex-1 space-y-4 p-8">
      <BreadCrumb items={breadcrumbItems} />
      <ProductForm categories={categories} initialData={product} />
    </div>
  );
}
