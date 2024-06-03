"use client";

import { Category } from "@/types/ApiResponse";
import Link from "next/link";

export default function Sidebar({ categories }: { categories: Category[] }) {
  return (
    <aside className="bg-gray-100 p-4 w-1/6 h-screen">
      <h2 className="text-xl font-bold mb-4">Categories</h2>
      <ul>
        {categories.map((category) => (
          <li key={category.id} className="mb-2">
            <Link
              href={`${category.id}`}
              className="block w-full text-left p-2 hover:bg-gray-200 rounded"
            >
              {category.name}
            </Link>
          </li>
        ))}
      </ul>
    </aside>
  );
}
