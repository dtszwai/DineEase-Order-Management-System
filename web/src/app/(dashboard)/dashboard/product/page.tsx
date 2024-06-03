import BreadCrumb from "@/components/breadcrumb";
import { columns } from "@/components/tables/product-tables/columns";
import { ProductTable } from "@/components/tables/product-tables/product-table";
import { Button } from "@/components/ui/button";
import { Heading } from "@/components/ui/heading";
import { Separator } from "@/components/ui/separator";
import { Plus } from "lucide-react";
import Link from "next/link";
import { get } from "@/components/api/admin";
import paths from "@/paths";
import { Product } from "@/types/ApiResponse";

const breadcrumbItems = [{ title: "Product", link: "/dashboard/product" }];

type paramsProps = {
  searchParams: {
    [key: string]: string | string[] | undefined;
  };
};

export default async function Page({ searchParams }: paramsProps) {
  const page = Number(searchParams.page) || 1;
  const pageLimit = Number(searchParams.limit) || 10;
  const searchValue = searchParams.search || null;
  const offset = (page - 1) * pageLimit;

  const url =
    `/products?offset=${offset}&limit=${pageLimit}` +
    (searchValue ? `&search=${searchValue}` : "");

  const productResult = await get<Product[]>(url);

  const totalProducts = (
    await get<Product[]>(
      "/products" + (searchValue ? `?search=${searchValue}` : "")
    )
  ).length;
  // const totalProducts = productResult.length;
  const pageCount = Math.ceil(totalProducts / pageLimit);

  return (
    <>
      <div className="flex-1 space-y-4  p-4 pt-6 md:p-8">
        <BreadCrumb items={breadcrumbItems} />
        <div className="flex items-start justify-between">
          <Heading
            title={`Product (${totalProducts})`}
            description="Manage products here."
          />
          <Link href={`${paths.product}/new`}>
            <Button>
              <Plus className="mr-2 h-4 w-4" /> Add New
            </Button>
          </Link>
        </div>
        <Separator />

        <ProductTable
          searchKey="name"
          pageNo={page}
          columns={columns}
          // totalProducts={totalProducts}
          data={productResult}
          pageCount={pageCount}
        />
      </div>
    </>
  );
}
