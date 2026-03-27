import type { NextConfig } from "next";

const nextConfig: NextConfig = {
  reactStrictMode:true, // 개발모드에서 검수를 도와주는것
  async redirects() {
      return [
        {
          source: '/',
          destination: '/bookmarks',
          permanent: true, // 영구적으로 적용
        }
      ]
  },
};

export default nextConfig;
