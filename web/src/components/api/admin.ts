"use server";

import { auth } from "@/auth";

const fetchData = async <T>(
  url: string,
  method: string,
  body?: {}
): Promise<T> => {
  const API_URL = process.env.API_URL;
  const session = await auth();

  const options: any = {
    method: method,
    headers: {
      "Content-Type": "application/json",
      Authorization: `${session?.user.token}`,
    },
  };

  if (body && !["GET", "DELETE"].includes(method)) {
    options.body = JSON.stringify(body);
  }

  const res = await fetch(API_URL + url, options);
  // If the content type is JSON, return the JSON response
  const contentType = res.headers.get("content-type");
  if (contentType && contentType.indexOf("application/json") !== -1) {
    return res.json();
  } else {
    // If there is no content or not JSON, return an empty object or handle accordingly
    return {} as T;
  }
};

const get = async <T>(url: string): Promise<T> => fetchData<T>(url, "GET");

const post = async (url: string, body?: {}) => fetchData(url, "POST", body);

const put = async (url: string, body?: {}) => fetchData(url, "PUT", body);

const del = async (url: string) => fetchData(url, "DELETE");

export { get, post, put, del };
