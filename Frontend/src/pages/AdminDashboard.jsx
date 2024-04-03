import React from 'react';
import { Link } from 'react-router-dom';

const AdminDashboard = () => {
  return (
    <div className="bg-richblack-900 min-h-screen flex items-center justify-center">
      <div className="text-center">
        <h1 className="text-4xl font-bold text-white mb-8">Welcome to the Admin Dashboard</h1>
        <div className="flex justify-center gap-8">
          <Link to="/list-employees">
            <button className="bg-indigo-500 hover:bg-indigo-600 rounded-lg py-3 px-6 text-white font-medium">
              Manage Employees
            </button>
          </Link>
          <Link to="/list-items">
            <button className="bg-indigo-500 hover:bg-indigo-600 rounded-lg py-3 px-6 text-white font-medium">
              Manage Items
            </button>
          </Link>
        </div>
      </div>
    </div>
  );
};

export default AdminDashboard;