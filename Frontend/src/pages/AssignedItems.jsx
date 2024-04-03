import React, { useState, useEffect } from 'react';
import ItemServices from "../services/ItemService";

const AssignedItems = () => {
  const [items, setItems] = useState([]);

  useEffect(() => {
    getAllItems();
  }, []);

  const getAllItems = () => {
    ItemServices.getAllProduct().then((response) => {
        setItems(response.data);
        console.log(response.data);
      })
      .catch((error) => {
        console.log(error);
      });
  };

  return (
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
                if (item.status) {
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
  );
};

export default AssignedItems;