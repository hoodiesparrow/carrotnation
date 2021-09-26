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
    },
    width: {
      '180px': '180px',
      '750px': '750px',
    },
    minWidth: {
      '2000px': '2000px',
    },
  },
  variants: {
    extend: {},
  },
  plugins: [],
};
