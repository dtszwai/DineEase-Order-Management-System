import BreadCrumb from "@/components/breadcrumb";
import Tables from "@/components/customers/table-list";
import { ScrollArea } from "@/components/ui/scroll-area";
import { get } from "@/components/api/admin";
import paths from "@/paths";
import { Table } from "@/types/ApiResponse";
import { Metadata } from "next/types";

export const metadata: Metadata = {
  title: "Table",
};

const breadcrumbItems = [{ title: "Table", link: paths.table }];

export default async function page() {
  const tables = await get<Table[]>("/tables");
  return (
    <ScrollArea className="h-full">
      <div className="flex-1 space-y-4 p-8">
        <BreadCrumb items={breadcrumbItems} />
        <div>
          <Tables tables={tables} />
        </div>
      </div>
    </ScrollArea>
  );
}
