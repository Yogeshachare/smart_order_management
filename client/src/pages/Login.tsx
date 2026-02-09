import { useState } from "react";
import { login } from "../api/authApi";
import { toast } from "react-toastify";

const Login = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const handleLogin = async () => {
    try {
      await login(email, password);
      toast.success("Login successful");
      // localStorage.setItem("token", response.data.token);
      // navigate("/dashboard");
    } catch (error: any) {
      if (error.response) {
        // Backend responded with error status
        toast.error(error.response.data);

        if (error.response.status === 401) {
          toast.error("Invalid email or password");
        } else if (error.response.status === 400) {
          alert(error.response.data); // validation message
        } else {
          alert("Something went wrong. Please try again.");
        }
      } else {
        // Network / server down
        alert("Server not reachable");
      }
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
            <h1 className="text-5xl font-bold text-[#23395b] whitespace-wrap">
              Order
            </h1>
            <h1 className="text-5xl font-bold text-[#23395b] whitespace-wrap">
              System
            </h1>
          </div>
        </div>
        <div className="flex-1 flex flex-col justify-center items-center gap-4">
          <h1 className="text-2xl text-[#406E8E] font-bold">Login</h1>
          <input
            type="email"
            placeholder="Email"
            className="h-10 p-2 border rounded-md mb-4 w-[50%]"
            onChange={(e) => setEmail(e.target.value)}
          />
          <input
            type="password"
            className="h-10 p-2 border rounded-md mb-4 w-[50%]"
            placeholder="Password"
            onChange={(e) => setPassword(e.target.value)}
          />
          <button
            className="border rounded-md h-10 w-[50%] bg-[#cbf7ed]"
            onClick={handleLogin}
          >
            Login
          </button>
          <a className="text-[14px] text-[#406E8E]" href="">
            Forgot Password
          </a>
        </div>
      </div>
    </div>
  );
};

export default Login;
