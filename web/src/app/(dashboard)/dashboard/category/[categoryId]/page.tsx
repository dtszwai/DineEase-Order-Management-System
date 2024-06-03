import BreadCrumb from "@/components/breadcrumb";
import { CategoryForm } from "@/components/forms/category-form";
import { get } from "@/components/api/admin";
import { Category } from "@/types/ApiResponse";
import React from "react";

export default async function Page({
  params,
}: {
  params: { categoryId: string };
}) {
  const categoryId = parseInt(params.categoryId);
  const category = (await get<Category[]>(`/categories?id=${categoryId}`))[0];

  let title: string;
  title = isNaN(categoryId) ? "Create" : "Edit";

  const breadcrumbItems = [
    { title: "Category", link: "/dashboard/category" },
    { title: title, link: `/dashboard/category/${categoryId}` },
  ];
  return (
    <div className="flex-1 space-y-4 p-8">
      <BreadCrumb items={breadcrumbItems} />
      <CategoryForm
        initialData={category}
        key={null}
      />
    </div>
  );
}
