import BreadCrumb from "@/components/breadcrumb";
import { columns } from "@/components/tables/category-tables/columns";
import { CategoryTable } from "@/components/tables/category-tables/category-table";
import { Button } from "@/components/ui/button";
import { Heading } from "@/components/ui/heading";
import { Separator } from "@/components/ui/separator";
import { Plus } from "lucide-react";
import Link from "next/link";
import { get } from "@/components/api/admin";
import paths from "@/paths";
import { Category } from "@/types/ApiResponse";

const breadcrumbItems = [{ title: "Category", link: `${paths.category}` }];

type paramsProps = {
  searchParams: {
    [key: string]: string | string[] | undefined;
  };
};

export default async function page({ searchParams }: paramsProps) {
  const page = Number(searchParams.page) || 1;
  const pageLimit = Number(searchParams.limit) || 10;
  const searchValue = searchParams.search || null;
  const offset = (page - 1) * pageLimit;

  const url =
    `/categories?offset=${offset}&limit=${pageLimit}` +
    (searchValue ? `&search=${searchValue}` : "");

  const categoryRes = await get<Category[]>(url);
  const totalCategories = categoryRes.length;
  const pageCount = Math.ceil(totalCategories / pageLimit);
  return (
    <>
      <div className="flex-1 space-y-4  p-4 pt-6 md:p-8">
        <BreadCrumb items={breadcrumbItems} />

        <div className="flex items-start justify-between">
          <Heading
            title={`Category (${totalCategories})`}
            description="Manage categories here."
          />
          <Link href={`${paths.category}/new`}>
            <Button>
              <Plus className="mr-2 h-4 w-4" /> Add New
            </Button>
          </Link>
        </div>
        <Separator />

        <CategoryTable
          searchKey="name"
          pageNo={page}
          columns={columns}
          totalCategories={totalCategories}
          data={categoryRes}
          pageCount={pageCount}
        />
      </div>
    </>
  );
}
