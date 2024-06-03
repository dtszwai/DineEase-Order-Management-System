import Image from "next/image";
import Link from "next/link";
import logoImg from "public/logo.jpg";

export default function Header({ token }: { token: string }) {
  return (
    <header className="bg-gray-800 text-white p-4 flex justify-between items-center">
      <Link className="flex items-center" href="1">
        <Image
          src={logoImg}
          alt="Restaurant Logo"
          className="h-10 mr-3"
          width={40}
          height={40}
        />
        <h1 className="text-xl">Restaurant</h1>
      </Link>
      <nav>
        <Link href={`/client/${token}/cart`} className="mx-2" passHref>
          Cart
        </Link>
        <Link href={`/client/${token}/history`} className="mx-2">
          History
        </Link>
      </nav>
    </header>
  );
}
