import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import EmployeeServices from '../services/EmployeeService';
import ItemService from '../services/ItemService';
import { toast } from 'react-hot-toast';

const ListEmployee = () => {
  const [employees, setEmployees] = useState([]);
  const [items, setItems] = useState([])

  useEffect(() => {
    getAllEmployees();
  }, []);
  
  const getAllProduct = async () => {
    try {
      const response = await ItemService.getAllProduct();
      const itemsData = response.data;
      setItems(itemsData);
      return itemsData;
    } catch (error) {
      console.log(error);
    }
  };
  
  const getAllEmployees = async () => {
    try {
      const response = await EmployeeServices.getAllEmployees();
      const employeesData = response.data;
      setEmployees(employeesData);
    } catch (error) {
      console.log(error);
    }
  };

  const deleteEmployee = async (employee) => {
    console.log(employee);
    if (employee.id) {
      try {
        const itemsData = await getAllProduct();
        console.log(itemsData);
        const employeeInItems = itemsData.some((item) => item.empId === employee.id);
        console.log(employeeInItems);

        if (employeeInItems) {
          toast.error("First unassign the items and then try to delete");
          return;
        }
        await EmployeeServices.deleteEmployee(employee.id);
        toast.success("Employee Deleted");
        await getAllEmployees();
      } catch (error) {
        console.log("Delete Operation Failed");
      }
    }
  };
  
  return (
    <div className="bg-dark min-h-screen py-4 bg-gray-900">
      <div className="container mx-auto">
        <h2 className="text-center text-2xl font-bold mb-4 text-richblack-200">List Employees</h2>
        <div className="flex justify-between mb-2">
          <Link to="/add-employee" className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">
            Add Employee
          </Link>
          <Link to="/list-items" className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded mr-4">
            List Items
          </Link>
        </div>
        <table className="table-auto w-full border-collapse border">
          <thead className="bg-gray-200">
            <tr>
              <th className="py-2 px-4 border">Employee ID</th>
              <th className="py-2 px-4 border">Name</th>
              <th className="py-2 px-4 border">Email</th>
              <th className="py-2 px-4 border">Address</th>
              <th className="py-2 px-4 border">Phone</th>
              <th className="py-2 px-4 border">Account Type</th>
              <th className="py-2 px-4 border">Actions</th>
            </tr>
          </thead>
          <tbody>
            {employees.map((employee) => {
              if (employee.accountType === "employee") {
                return (
                  <tr key={employee.id}>
                    <td className="py-2 px-4 border text-richblack-200 text-center">{employee.id}</td>
                    <td className="py-2 px-4 border text-richblack-200 text-center">{employee.name}</td>
                    <td className="py-2 px-4 border text-richblack-200 text-center">{employee.email}</td>
                    <td className="py-2 px-4 border text-richblack-200 text-center">{employee.address}</td>
                    <td className="py-2 px-4 border text-richblack-200 text-center">{employee.phone}</td>
                    <td className="py-2 px-4 border text-richblack-200 text-center">{employee.accountType}</td>
                    <td className="py-2 px-4 border">
                      <button
                        className="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded ml-2"
                        onClick={() => deleteEmployee(employee)}>
                        Delete</button>
                    </td>
                  </tr>
               );
             }
            })}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default ListEmployee;