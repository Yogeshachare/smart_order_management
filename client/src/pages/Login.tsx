import React from "react";

const Login = () => {
  return (
    <div className="w-screen h-screen flex justify-center items-center">
      <div className="md:w-[60%] md:h-[70%] bg-white w-full h-full rounded-3xl flex">
        <div className="h-full flex-1 flex justify-center items-center rounded-3xl bg-[url('./assets/ecomm.jpg')] bg-cover bg-center">
          <h1 className="text-5xl font-bold text-[#23395b]">Order System</h1>
        </div>
        <div className="flex-1 flex flex-col justify-center items-center">
          <input
            type="text"
            placeholder="Email"
            className="h-10 p-2 border rounded-md mb-4"
          />
          <input type="password" placeholder="Password" />
          <button>Login</button>
        </div>
      </div>
    </div>
  );
};

export default Login;
