import { AppBar, Toolbar, Button } from "@mui/material";
import HomeIcon from "@mui/icons-material/Home";
import FitnessCenterIcon from "@mui/icons-material/FitnessCenter";
import DirectionsRunIcon from '@mui/icons-material/DirectionsRun';
import LightbulbIcon from "@mui/icons-material/Lightbulb";
import LogoutIcon from "@mui/icons-material/Logout";
import { Link,useLocation } from "react-router-dom";
import { useContext } from "react";
import { AuthContext } from "../context/AuthContext.jsx";

export default function Navbar() {
  const { user, logout } = useContext(AuthContext); 
   const location = useLocation();
   const navButtonStyle=(Active) => ({
  color:Active? "#ff6b00":"#fff",
  textTransform: "none",
  fontWeight: 500,
  position: "relative",
  "&:hover": {
    backgroundColor: "transparent",
    color: "#ff6b00", // text becomes orange
  },
  "&::after": {
    content: '""',
    position: "absolute",
    width:Active?"100%": "0%",
    height: "3px",
    bottom: "0",
    left: "0",
    backgroundColor: "#ff6b00",
    transition: "width 0.3s ease",
  },
  "&:hover::after": {
    width: "100%",
  },
});

  return (
    <AppBar position="static" sx={{ background: "#111" }}>
      <Toolbar className="flex justify-between">
        <div className="flex items-center gap-2 text-orange-500">
          <FitnessCenterIcon />
          <span className="font-bold text-lg">FIT TRACK</span>
        </div>

        <div className="flex gap-4">
          <Button component={Link} to="/" startIcon={<HomeIcon />}  sx={navButtonStyle(location.pathname=="/")}>Home</Button>
          {user && <Button component={Link} to="/activity" startIcon={<DirectionsRunIcon/>}  sx={navButtonStyle(location.pathname=="/activity")}>Activity</Button>}
          {user && <Button component={Link} to="/recommendation" startIcon={<LightbulbIcon />}  sx={navButtonStyle(location.pathname=="/recommendation")}>Recommendation</Button>}
          {user ? (
            <Button color="primary" onClick={logout} startIcon={<LogoutIcon />}>Logout</Button>
          ) : (
            <Button component={Link} to="/login" color="primary">Login</Button>
          )}
        </div>
      </Toolbar>
    </AppBar>
  );
}


