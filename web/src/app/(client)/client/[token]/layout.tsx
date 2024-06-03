import { Toaster } from "@/components/ui/toaster";
import NextTopLoader from "nextjs-toploader";
import { Inter } from "next/font/google";
import { CartContextProvider } from "@/components/client/CartContext";
import Header from "@/components/client/layout/header";

const inter = Inter({ subsets: ["latin"] });

export default function RootLayout({
  children,
  params,
}: {
  children: React.ReactNode;
  params: { token: string };
}) {
  return (
    <CartContextProvider>
      <div className="flex flex-col min-h-screen font-sans font-light text-gray-800 bg-gray-100">
        <Toaster />
        <NextTopLoader />
        <Header token={params.token} />
        <main>{children}</main>
      </div>
    </CartContextProvider>
  );
}
