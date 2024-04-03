import React, { useEffect, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import EmployeeService from '../services/EmployeeService';
import { toast } from 'react-hot-toast';

const AddEmployee = () => {
    const [name, setName] = useState(''); // Employee Name
    const [email, setEmail] = useState(''); // Employee Email
    const [address, setAddress] = useState(''); // Employee Address
    const [phone, setPhone] = useState(''); // Employee Contact Number
    const [password, setPassword] = useState(''); // Employee Password
    const [employees, setEmployees] = useState('');
    const navigate = useNavigate();
    
    useEffect(() => {
        getAllEmployees();
    }, []);

    const getAllEmployees = () => {
        EmployeeService.getAllEmployees().then((response) => {
        // Verify the data in the console
          console.log(response.data); 
          setEmployees(response.data);
        })
        .catch(error => {
          console.log(error);
        });
    };

    const saveEmployee = async (e) => {
      e.preventDefault();

      //validate name field
      if (!name) {
        toast.error('Name field cannot be null');
        return;
      }
    
      //const nameRegex = /^[A-Za-z]+\s[A-Za-z]+$/;
      const nameRegex = /^[A-Z][a-z]+\s[A-Z][a-z]+$/;
      if (!nameRegex.test(name)) {
          toast.error('Name formate : Firstname Lastname');
          return;
      }

      //validate address field
      if (!address) {
        toast.error('Address field cannot be null');
        return;
      }
  
      const specialCharRegex = /^[a-zA-Z0-9\s,.]*$/;
      if (!specialCharRegex.test(address)) {
          toast.error('Please enter a valid address without special characters');
          return;
      }

      const isEmailRegistered = employees.some(employee => employee.email === email);
      if(isEmailRegistered)
      {
            toast.error("Email id is already registered");
            return;
      }

      //validate email field
      if (!email) {
        toast.error('Email field cannot be null');
        return;
      }

      const allowedDomain = 'gmail.com';
      const emailRegex = new RegExp(`^[A-Za-z0-9._%+-]+@${allowedDomain}$`);
      if(!emailRegex.test(email)) {
          toast.error(`Please enter a valid email with the domain ${allowedDomain}`);
          return;
      }

      //validate phone field
      if (!phone) {
          toast.error('Phone field cannot be null');
          return;
      }

      const phoneRegex = /^[6-9]\d{9}$/;
      if (!phoneRegex.test(phone)) {
          toast.error('Please enter a valid phone number starting with 6, 7, 8, or 9');
          return;
      }

      //validate password field
      if (!password) {
          toast.error('Password field cannot be null');
          return;
      }  
  
      const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#$%^&*()])[a-zA-Z\d!@#$%^&*()]{8,}$/;
      if (!passwordRegex.test(password)) {
          toast.error("Password must contain at least one uppercase letter, one lowercase letter, one digit, one special character, and be at least 8 characters long");
          return;
      }
    
      // Validation passed, continue with saving the employee
      const employee = { name :name, address: address, email: email, phone: phone, password: password, accountType: 'employee' };
      console.log(employee);

      //call server API
      await EmployeeService.createEmployee(employee).then((response) => {
          toast.success('Employee Added');
          navigate('/list-employees');
          console.log(response.data);
      })
      .catch((error) => {
          console.log(error);
          toast.error("Employee is not added");
      });
      getAllEmployees();
  };

  return (
    <div className='bg-dark min-h-screen py-4 bg-gray-900'>
      <div className='container mx-auto bg-gray-900'>
        <div className='flex justify-center'>
          <div className='w-full md:w-1/2 lg:w-1/3'>
            <h2 className="text-center text-2xl font-bold mb-4 text-white">Add Employee</h2>
            <div className='bg-white rounded shadow p-6 mt-6'>
              <form>
                <div className='mb-4'>
                  <label className='block mb-2 text-sm font-bold text-gray-700'>Name<sup className='text-pink-200'>*</sup></label>
                  <input
                    type='text'
                    placeholder='Enter Your Name'
                    name='name'
                    className='w-full p-2 border border-gray-300 rounded'
                    value={name}
                    onChange={(e) => setName(e.target.value)}
                  />
                </div>
                <div className='mb-4'>
                  <label className='block mb-2 text-sm font-bold text-gray-700'>Address<sup className='text-pink-200'>*</sup></label>
                  <input
                    type='text'
                    placeholder='Enter Your Address'
                    name='address'
                    className='w-full p-2 border border-gray-300 rounded'
                    value={address}
                    onChange={(e) => setAddress(e.target.value)}
                  />
                </div>
                <div className='mb-4'>
                  <label className='block mb-2 text-sm font-bold text-gray-700'>Email<sup className='text-pink-200'>*</sup></label>
                  <input
                    type='email'
                    placeholder='Enter Your Email'
                    name='email'
                    className='w-full p-2 border border-gray-300 rounded'
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                  />
                </div>
                <div className='mb-4'>
                  <label className='block mb-2 text-sm font-bold text-gray-700'>Phone<sup className='text-pink-200'>*</sup></label>
                  <input
                    type='text'
                    placeholder='Enter Phone Number'
                    name='phone'
                    className='w-full p-2 border border-gray-300 rounded'
                    value={phone}
                    onChange={(e) => setPhone(e.target.value)}
                  />
                </div>
                <div className='mb-4'>
                  <label className='block mb-2 text-sm font-bold text-gray-700'>Password<sup className='text-pink-200'>*</sup></label>
                  <input
                    type='password'
                    placeholder='Enter Password'
                    name='password'
                    className='w-full p-2 border border-gray-300 rounded'
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                  />
                </div>
                <div className='flex items-center justify-between'>
                  <button
                    type='submit'
                    className='mr-2 py-2 px-4 bg-blue-500 hover:bg-blue-600 focus:ring-blue-700 focus:ring-offset-blue-200 text-white w-full transition ease-in duration-200 text-center text-base font-semibold shadow-md focus:outline-none rounded'
                    onClick={saveEmployee}
                  >
                    Submit
                  </button>
                  <Link
                    to='/list-employees'
                    className='py-2 px-4 bg-gray-500 hover:bg-gray-600 focus:ring-gray-700 focus:ring-offset-gray-200 text-white w-full transition ease-in duration-200 text-center text-base font-semibold shadow-md focus:outline-none rounded'
                  >
                    Cancel
                  </Link>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default AddEmployee;