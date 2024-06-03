import BreadCrumb from "@/components/breadcrumb";
import { EmployeeForm } from "@/components/forms/employee-form";
import { get } from "@/components/api/admin";
import React from "react";
import { isNumberObject } from "util/types";

interface PageProps {
  params: { employeeId: string };
}

async function fetchEmployeeData(employeeId: string | undefined) {
  if (employeeId) {
    return await get(`/employees/${employeeId}`);
  }
  return null;
}

function determineTitle(isEdit: boolean) {
  return isEdit ? "Edit" : "Create";
}

function generateBreadcrumbItems(
  title: string,
  employeeId: string | undefined
) {
  return [
    { title: "Employee", link: "/dashboard/employee" },
    {
      title,
      link: `/dashboard/employee/${employeeId ? employeeId : "create"}`,
    },
  ];
}

export default async function Page({ params }: PageProps) {
  const isEdit = isNumberObject(params.employeeId);
  const employeeData = await fetchEmployeeData(
    isEdit ? params.employeeId : undefined
  );
  const title = determineTitle(isEdit);
  const breadcrumbItems = generateBreadcrumbItems(
    title,
    isEdit ? params.employeeId : undefined
  );

  return (
    <div className="flex-1 space-y-4 p-8">
      <BreadCrumb items={breadcrumbItems} />
      <EmployeeForm initialData={employeeData} />
    </div>
  );
}
