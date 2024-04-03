import React, {useEffect, useState} from 'react';
import { useLocation } from 'react-router-dom';
import ItemService from '../services/ItemService';

const EmployeeDashboard = () => {

  const location = useLocation();
  const response = location.state;
  const [items, setItems] = useState([]);

  console.log(response);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const itemResponse = await ItemService.getAllProduct();
        const items = itemResponse.data;
        console.log(items);
        setItems(items);
        console.log(items);
        // return itemData;
        // const employeesResponse = await EmployeeService.getAllEmployees();
        // const employeesData = employeesResponse.data;
        // setEmployees(employeesData);
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    };
    fetchData();
  }, []);


  // const getAllItems = () => {
  //   ItemService.getAllProduct().then((response) => {
  //       setItems(response.data);
  //       console.log(items);
  //     })
  //     .catch((error) => {
  //       console.log(error);
  //     });
  // };
  
  return (
    <div className="container mx-auto mt-5 text-richblack-500 text-center">
      <h2 className="text-3xl mb-4 text-richblack-200">Employee Details</h2>
      <div className="bg-white p-6 rounded-lg shadow text flex flex-col items-center justify-center">
        <>
          <p className="mb-4">
            <strong>Employee ID: </strong>
            <strong>{response.id}</strong>
          </p>
          <p className="mb-4">
            <strong>Name: </strong>
            <strong>{response.name}</strong>
          </p>
          <p className="mb-4">
            <strong>Email: </strong>
            <strong>{response.email}</strong>
          </p>
          <p className="mb-4">
            <strong>Phone Number: </strong>
            <strong>{response.phone}</strong>
          </p>
          <p className="mb-4">
            <strong>Address: </strong>
            <strong>{response.address}</strong>
          </p>
          <p className="mb-4">
            <strong>Account Type: </strong>
            <strong>{response.accountType}</strong>
          </p>
        </>
      </div>
      {/* Assigned items */}
      <div className="bg-dark min-h-screen py-4 bg-gray-900">
      <div className="container mx-auto">
        <h2 className="text-center text-2xl font-bold mb-4 text-richblack-200">Assigned Items</h2>        
        <table className="table-auto w-full border-collapse border">
          <thead className="bg-gray-200">
            <tr>
              <th className="py-2 px-4 border">S. No.</th>
              <th className="py-2 px-4 border">Name</th>
              <th className="py-2 px-4 border">Warranty</th>
              <th className="py-2 px-4 border">Bill Number</th>
              <th className="py-2 px-4 border">Date</th>
              <th className="py-2 px-4 border">Status</th>
              <th className="py-2 px-4 border">Emp ID</th>
              <th className="py-2 px-4 border">Emp Name</th>
            </tr>
          </thead>
          <tbody>
            {items.map((item) => {
                if (item.empId === response.id) {
                  return(
                    <tr key={item.serialNumber}>
                      <td className="py-2 px-4 border text-richblack-200 text-center">{item.serialNumber}</td>
                      <td className="py-2 px-4 border text-richblack-200 text-center">{item.productName}</td>
                      <td className="py-2 px-4 border text-richblack-200 text-center">{item.warranty}</td>
                      <td className="py-2 px-4 border text-richblack-200 text-center">{item.billNumber}</td>
                      <td className="py-2 px-4 border text-richblack-200 text-center">{item.date}</td>
                      <td className="py-2 px-4 border text-richblack-200 text-center">{item.status ? "True" : "False"}</td>
                      <td className="py-2 px-4 border text-richblack-200 text-center">{item.empId}</td>
                      <td className="py-2 px-4 border text-richblack-200 text-center">{item.empName}</td>
                    </tr>
                  );
                }
            })}
          </tbody>
        </table>
      </div>
    </div>
    </div>
  );
};

export default EmployeeDashboard;