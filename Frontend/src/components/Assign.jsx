import React, { useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import EmployeeService from '../services/EmployeeService';
import ItemServices from "../services/ItemService";
import { toast } from 'react-hot-toast';

const Assign = () => {
  const { serialNumber } = useParams(); // Item ID
  const [productName, setProductName] = useState(''); // Item Name
  const [employees, setEmployees] = useState([]); // List of employees
  const [empId, setEmpId] = useState(''); // Employee ID
  const [empName, setEmpName] = useState(''); //Employee Name
  const [warranty, setWarranty] = useState(''); // Item warranty
  const [billNumber, setBillNumber] = useState(''); //Item Bill Number
  const [date, setDate] = useState(''); //Item Date Of Purchase
  const navigate = useNavigate();

  useEffect(() => {
    const fetchData = async () => {
      try {
        const itemResponse = await ItemServices.getProductById(serialNumber);
        const itemData = itemResponse.data;
        setProductName(itemData.productName);
        setWarranty(itemData.warranty);
        setBillNumber(itemData.billNumber);
        setDate(itemData.date);

        const employeesResponse = await EmployeeService.getAllEmployees();
        const employeesData = employeesResponse.data;
        setEmployees(employeesData);
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    };
    fetchData();
  }, [serialNumber]);

  const assignItem = async (e) => {
    e.preventDefault();

    const item = { productName: productName, empId: empId, status: true, empName: empName, 
       warranty: warranty, billNumber: billNumber, date: date };

    if (serialNumber) {
      try {
        const itemResponse = await ItemServices.editProduct(serialNumber, item);
        toast.success("Item is Assigned");
        navigate('/list-items');
      } catch (error) {
        console.log("Item update error:", error);
        toast.error("Item is not assigned");
      }
    }
  }

  const selectEmployee = (employeeId, employeeName, event) => {
    event.preventDefault();
    setEmpId(employeeId);
    setEmpName(employeeName);
  }

  const handleSubmit = (e) => {
    e.preventDefault();
  };

  return (
    <div className="container mx-auto p-8 bg-gray-800 text-white">
      <div className="grid grid-cols-1 md:grid-cols-2 gap-8">
        <div>
          <h2 className="text-2xl mb-4">Assign Item</h2>
          <form onSubmit={handleSubmit}>
            <div className="mb-4">
              <label className="block text-white mb-2">Item Serial Number</label>
              <input
                required
                type="text"
                placeholder="Enter Serial Number"
                name="serialNumber"
                className="w-full px-4 py-2 bg-gray-700 rounded"
                value={serialNumber}
                readOnly
              />
            </div>
            <div className="mb-4">
              <label className="block text-white mb-2">Item Name</label>
              <input
                required
                type="text"
                placeholder="Enter Item Name"
                name="name"
                className="w-full px-4 py-2 bg-gray-700 rounded"
                value={productName}
                readOnly
              />
            </div>
            <div className="mb-4">
              <label className="block text-white mb-2">Employee ID</label>
              <input
                required
                type="text"
                placeholder="Enter Employee ID"
                name="empId"
                className="w-full px-4 py-2 bg-gray-700 rounded"
                value={empId}
                onChange={(e) => setEmpId(e.target.value)}
                readOnly
              />
            </div>
            <div className="mb-4">
              <label className="block text-white mb-2">Employee Name</label>
              <input
                required
                type="text"
                placeholder="Enter Employee Name"
                name="empName"
                className="w-full px-4 py-2 bg-gray-700 rounded"
                value={empName}
                onChange={(e) => setEmpName(e.target.value)}
                readOnly
              />
            </div>
            <button
              type="submit"
              className={`bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded ${!empId ? 'opacity-50 cursor-not-allowed' : ''}`}
              onClick={assignItem}
              disabled={!empId}
            >
              Assign
            </button>
          </form>
        </div>
        <div>
          <h2 className="text-2xl mb-4">Employee List</h2>
          <div className="bg-gray-700 py-4">
            <div className="overflow-x-auto">
              <table className="w-full table-auto">
                <thead>
                  <tr className="bg-gray-500">
                    <th className="py-2 px-4">Employee ID</th>
                    <th className="py-2 px-4">Employee Name</th>
                    <th className="py-2 px-4">Actions</th>
                  </tr>
                </thead>
                <tbody>
                  {employees.map((employee) => {
                    if (employee.accountType === "employee") {
                      return (
                        <tr key={employee.id}>
                        <td className="py-2 px-4">{employee.id}</td>
                        <td className="py-2 px-4">{employee.name}</td>
                        <td className="py-2 px-4">
                          <button
                            className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
                            onClick={(event) => selectEmployee(employee.id, employee.name, event)}>
                            Select
                          </button>
                        </td>
                      </tr>
                    );
                   }
                  })}
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Assign;