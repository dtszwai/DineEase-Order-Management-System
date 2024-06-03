import NextAuth, { DefaultSession } from "next-auth";

declare module "next-auth" {
  type UserSession = DefaultSession["user"];
  interface Session {
    user: {
      id: string;
      username: string;
      name: string;
      token: string;
    };
  }

  interface CredentialsInputs {
    username: string;
    password: string;
  }
}
