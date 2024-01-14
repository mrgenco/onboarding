import type { Config } from 'tailwindcss'
import flowbite from "flowbite/plugin";

const config: Config = {
  content: [
    './pages/**/*.{js,ts,jsx,tsx,mdx}',
    './components/**/*.{js,ts,jsx,tsx,mdx}',
    './app/**/*.{js,ts,jsx,tsx,mdx}',
    './node_modules/flowbite-react/lib/**/*.js'
  ],
  plugins: [
    require('@tailwindcss/typography'),
    flowbite
  ],
}
export default config
