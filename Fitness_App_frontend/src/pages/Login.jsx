import { useState, useContext } from "react";
import { TextField, Button, Paper } from "@mui/material";
import { login } from "../api/authApi.js";
import { AuthContext } from "../context/AuthContext.jsx";
import { Link } from "react-router-dom";

export default function Login() {
  const { loginUser } = useContext(AuthContext);

  const [form, setForm] = useState({ email: "", password: "" });

  const handleSubmit = async () => {
    try {
      const res = await login(form);
      loginUser(res.data);
    } catch (error){
      console.log(error) ;
      alert("Invalid credentials");
    }
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-[#0f0f0f]">
      <Paper className="p-10 w-[400px] bg-[#1a1a1a] rounded-2xl">
        <h2 className="text-3xl text-orange-500 mb-6 text-center">
          Welcome Back
        </h2>
        <div className="flex flex-col gap-5">
          <TextField
            label="Email"
            variant="outlined"
            fullWidth
            onChange={(e) => setForm({ ...form, email: e.target.value })}
          />
          <TextField
            label="Password"
            type="password"
            fullWidth
            onChange={(e) => setForm({ ...form, password: e.target.value })}
          />
          <Button variant="contained" size="large" onClick={handleSubmit}>
            Login
          </Button>
          <p className="text-gray-400 text-sm text-center">
            Don’t have account?{" "}
            <Link to="/register" className="text-orange-500">
              Register
            </Link>
          </p>
        </div>
      </Paper>
    </div>
  );
}




