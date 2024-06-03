import BreadCrumb from "@/components/breadcrumb";
import OrderTable from "@/components/tables/order-tables/order-table";
import OrderDetail from "@/components/tables/order-tables/order-detail";
import { get } from "@/components/api/admin";
import EarningSummary from "@/components/tables/order-tables/earning-card";

const breadcrumbItems = [{ title: "Order", link: "/dashboard/order" }];

export default async function OrderPage() {
  const orders = await get(`/orders`);

  return (
    <div className="flex-1 space-y-4 p-4 pt-6 md:p-8">
      <BreadCrumb items={breadcrumbItems} />
      <main className="grid flex-1 items-start gap-4 p-4 sm:px-6 sm:py-0 md:gap-8 lg:grid-cols-3 xl:grid-cols-3">
        <div className="grid auto-rows-max items-start gap-4 md:gap-8 lg:col-span-2">
          <EarningSummary />
          <OrderTable orders={orders} />
        </div>
        <OrderDetail />
      </main>
    </div>
  );
}
