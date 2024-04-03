import React, { useState, useEffect } from 'react'
import { AiOutlineEye, AiOutlineEyeInvisible } from "react-icons/ai";
import {toast} from "react-hot-toast"
import { useNavigate } from 'react-router-dom';
import {signUp} from '../services/UserService';
import EmployeeServices from '../services/EmployeeService';


const SignupForm = () => {
    const navigate = useNavigate();
    const [employees, setEmployees] = useState([]);
    
    useEffect(() => {
        getAllEmployees();
      }, []);
    
      const getAllEmployees = () => {
        EmployeeServices.getAllEmployees()
          .then((response) => {
            //Verify the data in the console
            console.log(response.data); 
            setEmployees(response.data);
          })
          .catch(error => {
            console.log(error);
          });
      };

    const [formData, setFormData] = useState({
        name:"",
        address:"",
        email:"",
        phone:"",
        password:"",
    })

    const [showPassword, setShowPassword] = useState(false);

    const [accountType, setAccountType] = useState("employee");

    function changeHandler(event) {

        setFormData( (prevData) =>(
            {
                ...prevData,
                [event.target.name]:event.target.value
            }
        ))
    }

    function submitHandler(event) {
        event.preventDefault();

        //validate name field
        // const nameRegex = /^[A-Za-z]+\s[A-Za-z]+$/;
        const nameRegex = /^[A-Z][a-z]+\s[A-Z][a-z]+$/;
        if (!nameRegex.test(formData.name)) {
            toast.error('Name formate : Firstname Lastname');
            return;
        }

        //validate address field
        const specialCharRegex = /^[a-zA-Z0-9\s,.]*$/;
        if (!specialCharRegex.test(formData.address)) {
            toast.error('Please enter a valid address without special characters');
            return;
        }

        //validate email field
        const allowedDomain = 'gmail.com';
        const emailRegex = new RegExp(`^[A-Za-z0-9._%+-]+@${allowedDomain}$`);
        if (!emailRegex.test(formData.email)) {
            toast.error(`Please enter a valid email with the domain ${allowedDomain}`);
            return;
        }

        const isEmailRegistered = employees.some(employee => employee.email === formData.email);
        if(isEmailRegistered)
        {
            toast.error("Email Id is already registered");
            return;
        }

        //validate phone field
        const phoneRegex = /^[6-9]\d{9}$/;
        if (!phoneRegex.test(formData.phone)) {
            toast.error('Please enter a valid phone number starting with 6, 7, 8, or 9');
            return;
        }

        //validate password field
        const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*()])[a-zA-Z\d!@#$%^&*()]{8,}$/;
        if (!passwordRegex.test(formData.password)) {
            toast.error("Password must contain at least one uppercase letter, one lowercase letter, one digit, one special character, and be at least 8 characters long");
            return;
        }


        if(accountType === "admin" && formData.password !== "H#1Ga7i@")
        {
            toast.error("Password is incorrect");
            return;
        }
    
        toast.success("Account Created");
        const accountData = {
        ...formData
        };
        
        const finalData = {
            ...accountData,
            accountType
        }

        console.log("printing Final account data ");
        console.log(finalData);

        //call server api for sending data
        signUp(finalData).then((response) => {
            console.log(response)
            console.log("Success log")
        }).catch((error) => {
            console.log(error)
            console.log("Error log")
        } )
    
        navigate("/");
    }

  return (
    <div>
        {/* employee-admin tab */}
        <div
        className='flex bg-richblack-800 p-1 gap-x-1 my-6 rounded-full max-w-max'>

            <button
            className={`${accountType === "employee" 
            ?
              "bg-richblack-900 text-richblack-5"
              :"bg-transparent text-richblack-200"} py-2 px-5 rounded-full transition-all duration-200`}
              onClick={()=> setAccountType("employee")}>
                Employee
            </button>

            <button
            className={`${accountType === "admin" 
            ?
              "bg-richblack-900 text-richblack-5"
              :"bg-transparent text-richblack-200"} py-2 px-5 rounded-full transition-all duration-200`}
              onClick={() => setAccountType("admin")}>
                Admin
            </button>
        </div>

        <form onSubmit={submitHandler} >
        {/* name and address */}
            <div className='flex gap-x-4 mt-[20px]'>
                    <label className='w-full'>
                        <p className='text-[0.875rem] text-richblack-5 mb-1 leading-[1.375rem]'>Name<sup className='text-pink-200'>*</sup></p>
                        <input
                            required
                            type="text"
                            name="name"
                            onChange={changeHandler}
                            placeholder="Enter Your Name"
                            value={formData.name}
                            className='bg-richblack-800 rounded-[0.5rem] text-richblack-5 w-full p-[12px]'
                        />
                    </label>

                    <label className='w-full'>
                        <p className='text-[0.875rem] text-richblack-5 mb-1 leading-[1.375rem]'>Address<sup className='text-pink-200'>*</sup></p>
                        <input
                            required
                            type="text"
                            name="address"
                            onChange={changeHandler}
                            placeholder="Enter Your Address"
                            value={formData.address}
                            className='bg-richblack-800 rounded-[0.5rem] text-richblack-5 w-full p-[12px]'
                        />
                    </label>
            </div>

            {/* email Address */}
            <div className='mt-[20px]'>
            <label className='w-full mt-[20px]'>
                    <p className='text-[0.875rem] text-richblack-5 mb-1 leading-[1.375rem]'>Email Address<sup className='text-pink-200'>*</sup></p>
                    <input
                        required
                        type="email"
                        name="email"
                        onChange={changeHandler}
                        placeholder="Enter Email Address "
                        value={formData.email}
                        className='bg-richblack-800 rounded-[0.5rem] text-richblack-5 w-full p-[12px]'
                    />
            </label>
            </div>

            {/* phone - password*/ }
            <div className='flex gap-x-4 mt-[20px]'>
                    <label className='w-full'>
                        <p className='text-[0.875rem] text-richblack-5 mb-1 leading-[1.375rem]'>Phone No.<sup className='text-pink-200'>*</sup></p>
                        <input
                            required
                            type="text"
                            name="phone"
                            onChange={changeHandler}
                            placeholder="Enter Your Contact Number"
                            value={formData.phone}
                            className='bg-richblack-800 rounded-[0.5rem] text-richblack-5 w-full p-[12px]'
                        />
                    </label>

                    {/* createPassword */}
                <label className='w-full relative'>
                    <p className='text-[0.875rem] text-richblack-5 mb-1 leading-[1.375rem]'>Create Password<sup className='text-pink-200'>*</sup></p>
                    <input
                        required
                        type= {showPassword ? ("text") : ("password")}
                        name="password"
                        onChange={changeHandler}
                        placeholder="Enter Password"
                        value={formData.password}
                        className='bg-richblack-800 rounded-[0.5rem] text-richblack-5 w-full p-[12px]'
                    />
                    <span
                     className='absolute right-3 top-[38px] cursor-pointer' 
                    onClick={() => setShowPassword((prev) => !prev)}>
                        {showPassword ? 

                        (<AiOutlineEyeInvisible fontSize={24} fill='#AFB2BF'/>) : 

                        (<AiOutlineEye fontSize={24} fill='#AFB2BF'/>)}
                    </span>
                </label>        
            </div>
            
        <button className=' w-full bg-yellow-50 rounded-[8px] font-medium text-richblack-900 px-[12px] py-[8px] mt-6'>
            Create Account
        </button>
        </form>
    </div>
  )
}

export default SignupForm;