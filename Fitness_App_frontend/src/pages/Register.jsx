
import { useState, useContext } from "react";
import { TextField, Button } from "@mui/material";
import { AuthContext } from "../context/AuthContext.jsx";
import { useNavigate } from "react-router-dom";
import { register } from "../api/authApi.js";



export default function Register() {
  const { loginUser } = useContext(AuthContext);
  const navigate = useNavigate();

  const [form, setForm] = useState({ email: "", password: "", firstName: "", lastName: "" });

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const res = await register({
        email: form.email,
        password: form.password,
        firstName: form.firstName,
        lastName: form.lastName
      });
      loginUser(res.data); 
      navigate("/login");
    } catch (error) {
      console.log(error.response?.data || error.message);
      alert("Registration failed. Check console for details.");
    }
  };

  return (
    <div className="min-h-screen bg-[#0f0f0f] flex justify-center items-center">
      <form className="bg-[#1a1a1a] p-8 rounded-xl w-96 flex flex-col gap-4">
        <h2 className="text-2xl font-bold text-orange-500">Register</h2>
        <TextField
          label="First Name"
          value={form.firstName}
          onChange={(e) => setForm({ ...form, firstName: e.target.value })}
        />
        <TextField
          label="Last Name"
          value={form.lastName}
          onChange={(e) => setForm({ ...form, lastName: e.target.value })}
        />
        <TextField
          label="Email"
          type="email"
          value={form.email}
          onChange={(e) => setForm({ ...form, email: e.target.value })}
        />
        <TextField
          label="Password"
          type="password"
          value={form.password}
          onChange={(e) => setForm({ ...form, password: e.target.value })}
        />
        <Button variant="contained" onClick={handleSubmit}>Register</Button>
      </form>
    </div>
  );
}