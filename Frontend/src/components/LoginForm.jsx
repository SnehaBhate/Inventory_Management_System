import React, { useState, useEffect } from 'react';
import { AiOutlineEye, AiOutlineEyeInvisible } from 'react-icons/ai';
import { useNavigate, useLocation } from 'react-router-dom';
// import { ToastContainer, toast } from 'react-toastify';
// import 'react-toastify/dist/ReactToastify.css';
import { toast } from 'react-hot-toast';
import {login} from '../services/UserService';
const LoginForm = ({ setIsLoggedIn }) => {
  const navigate = useNavigate();
  const location = useLocation();
  const queryParams = new URLSearchParams(location.search);
  const verified = queryParams.get('verified');

  const [formData, setFormData] = useState({
    email: "",
    password: "",
  });

  const [accountType, setAccountType] = useState("employee");
  const [showPassword, setShowPassword] = useState(false);

  useEffect(() => {
    if (verified === 'true') {
      toast.success('User verified');
    } else if (verified === 'false') {
      toast.error('Verification failed or Clicked multiple times');
    }
  }, [verified]);

  function changeHandler (event)  {
    setFormData((prevData) => ({
      ...prevData,
      [event.target.name]: event.target.value,
    }));
  };

  const submitHandler = (event) => {
        event.preventDefault();

        const finalData = {
            ...formData,
            accountType
        }
        console.log(finalData);

        //call server api for sending data
        login(finalData).then((response) => {
            console.log(response)
            console.log(response.id)
            console.log(response.name)
            console.log(response.address)
            console.log(response.email)
            console.log(response.phone)
            console.log(response.accountType)

            console.log("Success log")
            setIsLoggedIn(true);
            toast.success("login successful");
            // Redirect to the desired page after successful login
            if(formData.password === "H#1Ga7i@")
            {
                navigate("/admindashboard");
            }
            else
            {
                navigate("/employeedashboard", { state: response });
            }
            }).catch((error) => {
            console.log(error)
            console.log("Error log")
            toast.error("Invalid Credentials");
        })  
    };

  return (
    <>
      <form
        onSubmit={submitHandler}
        className="flex flex-col w-full gap-y-4 mt-6"
      >
        {/* employee-admin tab */}
        <div className="flex bg-richblack-800 p-1 gap-x-1 my-6 rounded-full max-w-max">
          <button
            className={`${accountType === 'employee'
              ? 'bg-richblack-900 text-richblack-5'
              : 'bg-transparent text-richblack-200'
            } py-2 px-5 rounded-full transition-all duration-200`}
            onClick={() => setAccountType('employee')}
          >
            Employee
          </button>

          <button
            className={`${accountType === 'admin'
              ? 'bg-richblack-900 text-richblack-5'
              : 'bg-transparent text-richblack-200'
            } py-2 px-5 rounded-full transition-all duration-200`}
            onClick={() => setAccountType('admin')}
          >
            Admin
          </button>
        </div>

        <label className="w-full">
          <p className="text-[0.875rem] text-richblack-5 mb-1 leading-[1.375rem]">
            Email Address<sup className="text-pink-200">*</sup>
          </p>
          <input
            required
            type="email"
            value={formData.email}
            onChange={changeHandler}
            placeholder="Enter email address"
            name="email"
            className="bg-richblack-800 rounded-[0.5rem] text-richblack-5 w-full p-[12px]"
          />
        </label>

        <label className="w-full relative">
          <p className="text-[0.875rem] text-richblack-5 mb-1 leading-[1.375rem]">
            Password<sup className="text-pink-200">*</sup>
          </p>
          <input
            required
            type={showPassword ? 'text' : 'password'}
            value={formData.password}
            onChange={changeHandler}
            placeholder="Enter Password"
            name="password"
            className="bg-richblack-800 rounded-[0.5rem] text-richblack-5 w-full p-[12px]"
          />

          <span
            className="absolute right-3 top-[38px] cursor-pointer"
            onClick={() => setShowPassword((prev) => !prev)}
          >
            {showPassword ? (
              <AiOutlineEyeInvisible fontSize={24} fill="#AFB2BF" />
            ) : (
              <AiOutlineEye fontSize={24} fill="#AFB2BF" />
            )}
          </span>
        </label>

        <button className="bg-yellow-50 rounded-[8px] font-medium text-richblack-900 px-[12px] py-[8px] mt-6">
          Sign In
        </button>
      </form>
    </>
  );
};

export default LoginForm;

// import React, { useState } from 'react'
// import { AiOutlineEye, AiOutlineEyeInvisible } from "react-icons/ai";
// import { useNavigate } from 'react-router-dom';
// import { toast } from 'react-hot-toast';
// import {login} from '../services/UserService';

// const LoginForm = ({setIsLoggedIn}) => {

//     const navigate = useNavigate();

//     const [formData, setFormData] = useState( {
//         email:"", password:""
//     })

//     const [accountType, setAccountType] = useState("employee");

//     const[showPassword, setShowPassword] = useState(false);

//     function changeHandler(event) {

//         setFormData( (prevData) =>(
//             {
//                 ...prevData,
//                 [event.target.name]:event.target.value
//             }
//         ) )

//     }

//     const submitHandler = (event) => {
//         event.preventDefault();

//         const finalData = {
//             ...formData,
//             accountType
//         }
//         console.log(finalData);

//         //call server api for sending data
//         login(finalData).then((response) => {
//             console.log(response)
//             console.log(response.id)
//             console.log(response.name)
//             console.log(response.address)
//             console.log(response.email)
//             console.log(response.phone)
//             console.log(response.accountType)

//             console.log("Success log")
//             setIsLoggedIn(true);
//             toast.success("login successful");
//             // Redirect to the desired page after successful login
//             if(formData.password === "H#1Ga7i@")
//             {
//                 navigate("/admindashboard");
//             }
//             else
//             {
//                 navigate("/employeedashboard", { state: response });
//             }
//         }).catch((error) => {
//             console.log(error)
//             console.log("Error log")
//             toast.error("Invalid Credentials");
//         } )
          
      
        
//     }

//   return (
//     <form onSubmit={submitHandler}
//     className="flex flex-col w-full gap-y-4 mt-6">

//     {/* employee-admin tab */}
//     <div
//         className='flex bg-richblack-800 p-1 gap-x-1 my-6 rounded-full max-w-max'>

//             <button
//             className={`${accountType === "employee" 
//             ?
//               "bg-richblack-900 text-richblack-5"
//               :"bg-transparent text-richblack-200"} py-2 px-5 rounded-full transition-all duration-200`}
//               onClick={()=> setAccountType("employee")}>
//                 Employee
//             </button>

//             <button
//             className={`${accountType === "admin" 
//             ?
//               "bg-richblack-900 text-richblack-5"
//               :"bg-transparent text-richblack-200"} py-2 px-5 rounded-full transition-all duration-200`}
//               onClick={() => setAccountType("admin")}>
//                 Admin
//             </button>
//         </div>

//         <label className='w-full'>
//             <p className='text-[0.875rem] text-richblack-5 mb-1 leading-[1.375rem]'>
//                 Email Address<sup className='text-pink-200'>*</sup>
//             </p>
//             <input 
//                 required
//                 type="email"
//                 value = {formData.email}
//                 onChange={changeHandler}
//                 placeholder="Enter email address"
//                 name="email"
//                 className='bg-richblack-800 rounded-[0.5rem] text-richblack-5 w-full p-[12px]'
//             />
//         </label>

//         <label className='w-full relative'>
//             <p className='text-[0.875rem] text-richblack-5 mb-1 leading-[1.375rem]'>
//                 Password<sup className='text-pink-200'>*</sup>
//             </p>
//             <input 
//                 required
//                 type= {showPassword ? ("text") : ("password")}
//                 value = {formData.password}
//                 onChange={changeHandler}
//                 placeholder="Enter Password"
//                 name="password"
//                 className='bg-richblack-800 rounded-[0.5rem] text-richblack-5 w-full p-[12px]'
//             />

//             <span 
//             className='absolute right-3 top-[38px] cursor-pointer'
//             onClick={() => setShowPassword((prev) => !prev)}>
//                 {showPassword ? 

//                 (<AiOutlineEyeInvisible fontSize={24} fill='#AFB2BF'/>) : 

//                 (<AiOutlineEye fontSize={24} fill='#AFB2BF'/>)}
//             </span>

//         </label>

//         <button className='bg-yellow-50 rounded-[8px] font-medium text-richblack-900 px-[12px] py-[8px] mt-6'>
//             Sign In
//         </button>

//     </form>
//   )
// }

// export default LoginForm;