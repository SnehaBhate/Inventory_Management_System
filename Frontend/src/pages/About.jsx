import React from 'react';
import { Link } from 'react-router-dom';

const About = () => {
  return (
    <div className="bg-gray-900 text-white min-h-screen flex flex-col items-center justify-center">
      <div className="max-w-lg">
        <h1 className="text-3xl font-bold mb-6 text-richblack-200">About Our Inventory Management System</h1>
        <p className="text-base mb-6 text-justify text-richblack-200">
          Our Inventory Management System is designed to streamline and optimize your inventory operations. With a user-friendly interface and powerful features, our system helps you manage employees and items efficiently.
        </p>
        <p className="text-base text-justify text-richblack-200">
          Developed using ReactJS and powered by a SpringBoot REST API, our system ensures high performance and reliability. We prioritize data security and integrate code semantic plugins for improved quality and maintenance.
        </p>
      </div>
      <div className="mt-8 flex justify-end text-richblack-200">
        <Link to="/" className="bg-blue-500 hover:bg-blue-600 text-white py-2 px-4 rounded">
          Back to Home
        </Link>
      </div>
    </div>
  );
};

export default About;