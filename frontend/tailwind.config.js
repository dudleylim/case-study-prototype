/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/**/*.{html,ts}",
    "./node_modules/flowbite/**/*.js"
  ],
  theme: {
    extend: {
      colors: {
        text: "#41463F",
        primary: "#FCAE1D", 
        secondary: "#41463F",
        background: "#FBFBFE",
        error: "#ff0000"
      },

      // font details:
        // base size: 16px
        // scale: major third
        // font: montserrat
        // weight: 500
      fontSize: {
        title: "3.052rem",
        subtitle: "1.953rem",
        body: "1.25rem",
        small: "1rem"
      },
      fontWeight: {
        title: "600",
        subtitle: "500",
        body: "400",
        small: "400"
      }
    },
  },
  plugins: [
    require('flowbite/plugin')
  ],
}

