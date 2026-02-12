import React, { useState } from "react";
import { toast } from "react-toastify";
import { register } from "../api/authApi";
import { useNavigate } from "react-router-dom";

const Registration = () => {
  const [email, setEmail] = useState("");
  const [name, setName] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const navigate = useNavigate();

  const handleRegister = async () => {
    if (password !== confirmPassword) {
      toast.error("Passwords do not match");
      return;
    }
    try {
      await register({ email, name, password });
      toast.success("Registration successful");
      navigate("/login");
    } catch (error: any) {
      toast.error("Registration failed");
    }
  };

  return (
    <div className="w-screen h-screen flex justify-center items-center">
      <div className="md:w-[60%] md:h-[70%] bg-white w-full h-full rounded-3xl flex">
        <div
          className="h-full hidden flex-1 md:flex justify-center items-center rounded-3xl bg-[url('./assets/ecomm.jpg')] 
        bg-cover bg-center"
        >
          <div className="flex flex-col gap-4 justify-center items-center">
            <h1 className="text-5xl font-bold text-[var(--font-darker)] whitespace-wrap">
              Order
            </h1>
            <h1 className="text-5xl font-bold text-[var(--font-darker)] whitespace-wrap">
              System
            </h1>
          </div>
        </div>
        <div className="flex-1 flex flex-col justify-center items-center gap-4">
          <h1 className="text-2xl text-[var(--font-color)] font-bold">
            Register
          </h1>
          <input
            type="email"
            placeholder="Email"
            className="h-10 p-2 border rounded-md mb-4 w-[50%]"
            onChange={(e) => setEmail(e.target.value)}
          />

          <input
            type="text"
            placeholder="Name"
            className="h-10 p-2 border rounded-md mb-4 w-[50%]"
            onChange={(e) => setName(e.target.value)}
          />
          <input
            type="password"
            className="h-10 p-2 border rounded-md mb-4 w-[50%]"
            placeholder="Password"
            onChange={(e) => setPassword(e.target.value)}
          />

          <input
            type="password"
            placeholder="Confirm Password"
            className="h-10 p-2 border rounded-md mb-4 w-[50%]"
            onChange={(e) => setConfirmPassword(e.target.value)}
          />
          <button
            className="border rounded-md h-10 w-[50%] bg-[#cbf7ed]"
            onClick={handleRegister}
          >
            Login
          </button>
          <p>
            Already have an account?
            <a className="text-[14px] text-[var(--font-color)]" href="">
              {" "}
              Login
            </a>
          </p>
        </div>
      </div>
    </div>
  );
};

export default Registration;
