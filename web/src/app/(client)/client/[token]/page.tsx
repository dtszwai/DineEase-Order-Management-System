import Categorylist from "@/components/client/category-list";
import { getCategories } from "@/components/api/client";

export default async function Page({ params }: { params: { token: string } }) {
  const categories = await getCategories(params.token);

  return <Categorylist categories={categories} />;
}
