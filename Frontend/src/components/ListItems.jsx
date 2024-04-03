import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { toast } from 'react-hot-toast';
import ItemService from '../services/ItemService';

const ListItems = () => {
  const [items, setItems] = useState([]);

  useEffect(() => {
    init();
  }, []);

  const init = () => {
  ItemService.getAllProduct().then((res) => {
    console.log(res.data);
        setItems(res.data);
      })
      .catch((error) => {
        console.log(error);
      });
  };

  const unassignItem = async (serialNumber) => {

    try {
      const itemResponse = await ItemService.getProductById(serialNumber);
      const itemData = itemResponse.data;
      const productName = itemData.productName;
      const warranty = itemData.warranty;
      const billNumber = itemData.billNumber;
      const date = itemData.date;

      const item = { productName: productName, status: false, warranty: warranty, billNumber: billNumber, date: date };
      await ItemService.editProduct(serialNumber, item);
      init();
      toast.success('Item Unassigned');
    } catch (error) {
      console.log("Unassigned Task Failed");
    }
  }

  const deleteItem = (serialNumber) => {
    ItemService.deleteProduct(serialNumber).then((response) => {
        toast.success('Item Deleted');
        init();
      })
      .catch((error) => {
        console.log(error);
      });
  };
  

  return (
    <div className="bg-dark min-h-screen py-4 bg-gray-900">
      <div className="container mx-auto">
        <h2 className="text-center text-2xl font-bold mb-4 text-richblack-200">List Items</h2>
        <div className="flex justify-between mb-2">
          <div>
            <Link to="/add-item" className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">
              Add Item
            </Link>
            <Link to="/list-employees" className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded ml-2">
              List Employees
            </Link>
          </div>
          <div>
            <Link to="/list-assigned-items" className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded mr-2">
              Assigned Items
            </Link>
            <Link to="/list-unassigned-items" className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded mr-4">
                Unassigned Items
            </Link>
          </div>
        </div>
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
              <th className="py-2 px-4 border">Actions</th>
            </tr>
          </thead>
          <tbody>
            {items.map((item) => (
              <tr key={item.serialNumber}>
                <td className="py-2 px-4 border text-richblack-200 text-center">{item.serialNumber}</td>
                <td className="py-2 px-4 border text-richblack-200 text-center">{item.productName}</td>
                <td className="py-2 px-4 border text-richblack-200 text-center">{item.warranty}</td>
                <td className="py-2 px-4 border text-richblack-200 text-center">{item.billNumber}</td>
                <td className="py-2 px-4 border text-richblack-200 text-center">{item.date}</td>
                <td className="py-2 px-4 border text-richblack-200 text-center">{item.status ? "True" : "False"}</td>
                <td className="py-2 px-4 border text-richblack-200 text-center">{item.empId}</td>
                <td className="py-2 px-4 border text-richblack-200 text-center">{item.empName}</td>
                <td className="py-2 px-4 border">
                  <Link
                    className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded mr-2"
                    to={`/update-item/${item.serialNumber}`}>
                    Update
                  </Link>
                  {item.status ? (
                    <button
                      className="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded mr-2"
                      onClick={() => unassignItem(item.serialNumber)}>
                      Unassign                
                    </button>
                    ) : (
                        <span>
                          <Link
                            className="bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded mr-2"
                            to={`/assign-item/${item.serialNumber}`}>
                            Assign
                          </Link>
                          <button
                            className="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded"
                            onClick={() => deleteItem(item.serialNumber)}>
                            Delete
                          </button>
                        </span>
                   )}
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default ListItems;