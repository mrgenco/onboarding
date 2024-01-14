"use client";

import { Flowbite, ThemeModeScript } from "flowbite-react";
import { Inter } from "next/font/google";
import { twMerge } from "tailwind-merge";
import "./globals.css";
import { flowbiteTheme } from "./theme";
import { SidebarProvider } from "./context/SidebarContext";
import { useSidebarContext } from "./context/SidebarContext";
import type { FC, PropsWithChildren } from "react";
import { NavBar } from "./ui/navbar";
import { SideBar } from "./ui/sidebar";

const inter = Inter({ subsets: ["latin"] });

const RootLayout: FC<PropsWithChildren> = function ({ children }) {
  return (
    <html lang="en">
      <head>
        <ThemeModeScript />
      </head>
      <body className={twMerge("bg-gray-50 dark:bg-gray-900", inter.className)}>
        <Flowbite theme={{ theme: flowbiteTheme }}>
          <SidebarProvider>
            <Content>{children}</Content>
          </SidebarProvider>
        </Flowbite>
      </body>
    </html>
  );
};

const Content: FC<PropsWithChildren> = function ({ children }) {
  const { isCollapsed } = useSidebarContext();

  return (
    <>
      <NavBar />
      <div className="mt-16 flex items-start">
        <SideBar />
        <div
          id="main-content"
          className={twMerge(
            "relative grow p-6 md:overflow-y-auto md:p-12 overflow-y-auto bg-gray-50 dark:bg-gray-900",
            isCollapsed ? "lg:ml-[4.5rem]" : "lg:ml-64",
          )}
        >
          {children}
        </div>
      </div>
    </>
  );
};


export default RootLayout;