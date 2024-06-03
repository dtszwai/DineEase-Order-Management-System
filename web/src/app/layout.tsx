import { Toaster } from "@/components/ui/toaster";
import "@uploadthing/react/styles.css";
import NextTopLoader from "nextjs-toploader";
import { Inter } from "next/font/google";
import "./globals.css";
import Providers from "@/components/layout/providers";
import { auth } from "@/auth";

const inter = Inter({ subsets: ["latin"] });

export default async function RootLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  const session = await auth();
  return (
    <html lang="en" suppressHydrationWarning>
      <body className={`${inter.className}`}>
        <Providers session={session}>
          <NextTopLoader />
          <Toaster />
          {children}
        </Providers>
      </body>
    </html>
  );
}
