"use client";

import { Card, CardContent } from "@/components/ui/card";
import Link from "next/link";
import { usePathname } from "next/navigation";

export default function Categorylist({ categories }: any) {
  const current_path = usePathname();

  return (
    <div className="flex flex-col flex-wrap gap-2">
      {categories.map((category: any) => (
        <Card key={category.id}>
          <CardContent className="text-center py-5">
            <Link href={`${current_path}/${category.id}`} className="text-2xl">
              {category.name}
            </Link>
          </CardContent>
        </Card>
      ))}
    </div>
  );
}
