 import React from "react";
// import ReactDOM from "react-dom/client";
// import App from "./App.jsx";
// import { BrowserRouter } from "react-router-dom";
// import { AuthProvider } from "./context/AuthContext.jsx";
// import { ThemeProvider } from "@mui/material/styles";
// import CssBaseline from "@mui/material/CssBaseline";
// import theme from "./theme/muiThemes.js";
// import "./index.css";

// ReactDOM.createRoot(document.getElementById("root")).render(
//   <React.StrictMode>
//     <ThemeProvider theme={theme}>
//       <CssBaseline />
//       <AuthProvider>
//         <BrowserRouter>
//           <App />
//         </BrowserRouter>
//       </AuthProvider>
//     </ThemeProvider>
//   </React.StrictMode>
// );


import ReactDOM from "react-dom/client";
import { BrowserRouter } from "react-router-dom";
import App from "./App.jsx";
import { AuthProvider } from "./context/AuthContext.jsx";
import CssBaseline from "@mui/material/CssBaseline";
import { ThemeProvider } from "@mui/material/styles";
import theme from "./theme/muiThemes.js";
import "./index.css";

ReactDOM.createRoot(document.getElementById("root")).render(
  <React.StrictMode>
    <BrowserRouter>
      <AuthProvider>
        <ThemeProvider theme={theme}>
          <CssBaseline />
          <App />
        </ThemeProvider>
      </AuthProvider>
    </BrowserRouter>
  </React.StrictMode>
);

