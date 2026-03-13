import { createTheme } from "@mui/material/styles";

const theme = createTheme({
  palette: {
    mode: "dark",
    primary: {
      main: "#ff6a00", // premium orange
    },
    background: {
      default: "#0f0f0f",
      paper: "#1a1a1a",
    },
  },
  typography: {
    fontFamily: "Poppins, sans-serif",
  },
});

export default theme;
