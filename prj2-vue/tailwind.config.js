module.exports = {
  purge: { content: ["./public/**/*.html", "./src/**/*.vue"] },
  darkMode: false, // or 'media' or 'class'
  theme: {
    extend: {},
    container: {
      center: true,
    },
    maxWidth: {
      '750px': '750px',
    }
  },
  variants: {
    extend: {},
  },
  plugins: [],
};
