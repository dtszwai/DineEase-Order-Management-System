import NextAuth from "next-auth";
import CredentialsProvider from "next-auth/providers/credentials";
import { z } from "zod";

interface User {
  id: string;
  username: string;
  token: string;
  name: string;
}

async function getUser(username: string, password: string): Promise<User> {
  try {
    const res = await fetch(`${process.env.API_URL}/employees/login`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ username, password }),
    });
    if (!res.ok) {
      throw new Error("Failed to fetch user");
    }
    return await res.json();
  } catch (error) {
    throw new Error("Error getting user");
  }
}

export const {
  handlers: { GET, POST },
  auth,
  signIn,
  signOut,
} = NextAuth({
  pages: {
    signIn: "/",
  },
  session: {
    strategy: "jwt",
    maxAge: 60 * 60 * 1.5, // 1.5 hours
  },
  providers: [
    CredentialsProvider({
      name: "Credentials",
      credentials: {
        username: { label: "Username", type: "text" },
        password: { label: "Password", type: "password" },
      },
      async authorize(credentials) {
        const parsedCredentials = z
          .object({ username: z.string(), password: z.string().min(6) })
          .safeParse(credentials);

        if (parsedCredentials.success) {
          const { username, password } = parsedCredentials.data;
          const user = await getUser(username, password);
          return user;
        }
        return null;
      },
    }),
  ],
  callbacks: {
    async jwt({ token, user }) {
      if (user) {
        const userData = user as User;
        token.id = userData.id;
        token.username = userData.username;
        token.token = userData.token;
      }
      return token;
    },
    async session({ session, token }) {
      // save user id, name, username and token in the session
      session.user.id = token.id as string;
      // @ts-ignore
      session.user.username = token.username as string;
      // @ts-ignore
      session.user.token = token.token as string;
      return session;
    },
  },
});
