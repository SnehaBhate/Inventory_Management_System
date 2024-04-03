import React, { useState } from 'react';
import {toast} from "react-hot-toast"
import {contact} from '../services/UserService';
import { useNavigate } from 'react-router-dom';

const Contact = () => {
  const navigate = useNavigate();

  const [data, setData] = useState({
    contactName:"",
    contactEmail:"",
    message:''
  })

  function changeHandler(event) {

    setData( (prevData) =>(
        {
            ...prevData,
            [event.target.name]:event.target.value
        }
    ))

  }

  const submitHandler = (e) => {
    e.preventDefault();

    // validate name field
    // const nameRegex = /^[A-Za-z]+\s[A-Za-z]+$/;
    const nameRegex = /^[A-Z][a-z]+\s[A-Z][a-z]+$/;
    if (!nameRegex.test(data.contactName)) {
        toast.error('Please enter a valid name. Name format : "Firstname Lastname"');
        return;
    }

    // print form data 
    console.log('Form submitted:', data.contactName, data.contactEmail, data.message);

    //call server api for sending data
    contact(data).then((response) => {
      console.log(response)
      console.log("Success log")
      toast.success("Message Sent Successfully");
    }).catch((error) => {
      console.log(error)
      console.log("Error log")
      toast.error("Unsuccessfull");
    })
    
    navigate('/')  
  };

  return (
    <div className="bg-gray-900 text-white min-h-screen flex flex-col items-center justify-center">
      <div className="max-w-lg text-center">
        <h1 className="text-3xl font-bold mb-6 text-richblack-200">Contact Us</h1>
        <p className="text-base mb-6 text-richblack-200">
          If you have any questions or inquiries, please feel free to reach out to us. We're here to help!
        </p>
        <form onSubmit={submitHandler} className="mb-6">
          <div className="mb-4">
            <input
              required
              type="text"
              name="contactName"
              onChange={changeHandler}
              placeholder="Your Name"
              value={data.contactName}
              className="bg-gray-800 text-white py-2 px-4 w-full rounded"
            />
          </div>
          <div className="mb-4">
            <input
              required
              type="email"
              name="contactEmail"
              placeholder="Your Email"
              value={data.contactEmail}
              onChange={changeHandler}
              className="bg-gray-800 text-white py-2 px-4 w-full rounded"
            />
          </div>
          <div className="mb-4">
            <textarea
              required
              name="message"
              placeholder="Your Message"
              value={data.message}
              onChange={changeHandler}
              className="bg-gray-800 text-white py-2 px-4 w-full rounded"
              rows={4}
            ></textarea>
          </div>
          <button type="submit" className="bg-blue-500 hover:bg-blue-600 text-white py-2 px-4 rounded">
            Submit
          </button>
        </form>
        <div>
          <h2 className="text-xl font-semibold mb-3 text-richblack-200">Contact Details</h2>
          <p className="text-base text-richblack-200">Email: info@example.com</p>
          <p className="text-base text-richblack-200">Phone: +1 123-456-7890</p>
          <p className="text-base text-richblack-200">Address: 123 Street, City, Country</p>
        </div>
      </div>
    </div>
  );
};

export default Contact;