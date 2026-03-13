import { Button } from "@mui/material";
import { Link } from "react-router-dom";
import ErrorOutlineIcon from "@mui/icons-material/ErrorOutline";

export default function NotFound() {
  return (
    <div className="min-h-screen flex flex-col items-center justify-center bg-[#0f0f0f] text-white">
      <ErrorOutlineIcon sx={{ fontSize: 80, color: "#ff6a00" }} />
      <h1 className="text-6xl font-bold text-orange-500 mt-4">404</h1>
      <p className="text-gray-400 mt-2 text-lg">
        Oops! Page not found.
      </p>
      <Button
        component={Link}
        to="/"
        variant="contained"
        sx={{ mt: 4 }}
      >
        Go Back Home
      </Button>
    </div>
  );
}


 
