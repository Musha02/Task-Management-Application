This is a [Next.js](https://nextjs.org/) project bootstrapped with [`create-next-app`](https://github.com/vercel/next.js/tree/canary/packages/create-next-app).

## Getting Started

1. Install all the dependecies :

```bash
</> terminal

npm install
```

2. Install clerk for user authorization :
   
Clerk's Next.js SDK gives you access to prebuilt components, hooks, and helpers for Next.js Server Components, Route Handlers and Middleware. Install it by running the following command in your terminal:

```bash
</> terminal

npm install @clerk/nextjs
```
3. Set environment keys :
   
In your Next.js project's root folder, you may have an <code> .env.local </code> file alongside <code> package.json </code> and other configuration files. If you don't see it, create it.

Add the following code to your <code> .env.local </code> file to set your public and secret keys.

Pro tip! If you are signed into your Clerk Dashboard, your secret key should become visible by clicking on the eye icon.

```bash
</> .env.local

NEXT_PUBLIC_CLERK_PUBLISHABLE_KEY=pk_test_cmVsYXRlZC1xdWV0emFsLTE3LmNsZXJrLmFjY291bnRzLmRldiQ
CLERK_SECRET_KEY=sk_test_TFloPUHkWCf7gDJb91Amfv5N4AACRPCCRv2IwM83HZ
```
4. Set up Prisma:
   
Before you can introspect your database, you need to set up your Prisma schema and connect Prisma to your database. Run the following command in your terminal to create a basic Prisma schema file:

```bash
</> terminal

npx prisma init --datasource-provider mongodb
```

This command creates:
A new directory called prisma that contains a schema.prisma file; your Prisma schema file specifies your database connection and models

The Prisma schema file currently looks as follows:

```bash
</> schema.prisma

// This is your Prisma schema file,
// learn more about it in the docs: https://pris.ly/d/prisma-schema

datasource db {
  provider = "mongodb"
  url      = env("DATABASE_URL")
}

generator client {
  provider = "prisma-client-js"
}
```
5. Connect your database :
   
Configure your database connection URL in the <code>.env file.</code>

The format of the connection URL that Mongoose uses is similar to the one Prisma uses.

```bash
</> .env

DATABASE_URL="mongodb://alice:myPassword43@localhost:27017/blog-mongoose"
```
In here, I am using MongoDB as a database. If you use any other database the above URL will be changed. Refer Prisma.io

Finally, Run the development server:

```bash
npm run dev
# or
yarn dev
# or
pnpm dev
# or
bun dev
```

Open [http://localhost:3000](http://localhost:3000) with your browser to see the result.

You can start editing the page by modifying `app/page.tsx`. The page auto-updates as you edit the file.


