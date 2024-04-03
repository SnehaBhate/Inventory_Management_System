import React, { useEffect, useState } from 'react';
import { Link, useParams, useNavigate } from 'react-router-dom';
import { toast } from 'react-hot-toast';
import ItemService from '../services/ItemService';

const AddItem = () => {
  const [productName, setProductName] = useState(''); // Item Name
  const [warranty, setWarranty] = useState(''); //Item Warranty
  const [billNumber, setBillNumber] = useState(''); //Item Bill NUmber
  const [status, setStatus] = useState(''); //Item Status
  const [empId, setEmpId] =useState(''); //Employee ID
  const [empName, setEmpName] = useState(''); //Employee Name
  const [date, setDate] = useState(''); // Item Date Of Purchase
  const currentDate = new Date().toISOString().split("T")[0];
  const navigate = useNavigate();
  const { serialNumber } = useParams(); // Item Serial Number
  console.log(serialNumber);

  useEffect(() => {
    ItemService.getProductById(serialNumber).then((response) => {
      console.log(response.data);
        setProductName(response.data.productName)
        setWarranty(response.data.warranty)
        setBillNumber(response.data.billNumber)
        setDate(response.data.date)
        setStatus(response.data.status)
        setEmpId(response.data.empId)
        setEmpName(response.data.empName)
    }).catch(error => {
        console.log(error)
    })
}, [])

  const saveItem = async (e) => {
    e.preventDefault();

    //validate name field
    if (!productName) {
      toast.error('product name cannot be null');
      return;
    }
    
    const nameRegex = /^[A-Z][a-zA-Z\s]+$/;
    if (!nameRegex.test(productName)) {
      toast.error('Product name should contain only alphabets and spaces');
      return;
    }

    //validate warranty field
    if (!warranty) {
      toast.error('warranty field cannot be null');
      return;
    }

    const warrantyRegex = /^\d[a-zA-Z0-9\s]*$/;
    if (!warrantyRegex.test(warranty)) {
      toast.error('Warranty should start with a digit and can be followed by lowercase letter, uppercase, digit and space');
      return;
    }

    //validate billNumber field
    if (!billNumber) {
      toast.error('Bill number field cannot be null');
      return;
    }

    const billNumberRegex = /^[a-zA-Z0-9]+$/;
    if (!billNumberRegex.test(billNumber)) {
      toast.error('Bill number should contain only alphabets and digits');
      return;
    }

    //validate date field
    if (!date) {
      toast.error('Date field cannot be null');
      return;
    }

    // Validation passed, continue with saving the item
    const item = { productName : productName, warranty: warranty, billNumber: billNumber, date: date, status: false};
    console.log(item);

 
    //call server API
    if(serialNumber)
    {
        const itemResponse = await ItemService.getProductById(serialNumber);
        const itemData = itemResponse.data;
        console.log(itemData)
        setStatus(itemData.status);
        setEmpId(itemData.empId);
        setEmpName(itemData.empName);
        const item = {productName: productName, warranty: warranty, billNumber: billNumber, date: date, status: status, empId: empId, empName: empName};
        console.log("Final Item Data To BE Updated");
        console.log(item);
        await ItemService.editProduct(serialNumber, item).then((response) => {
            toast.success('Item Details Updated');
            navigate('/list-items')
        }).catch(error => {
            console.log("error log");
        })
    }
    else
    {
        // console.log(employee);
        const status = false;
        const item = {productName, status, warranty, billNumber, date}
        ItemService.saveProduct(item).then((response) => {
            console.log(response.data)
            toast.success('Item Added');
            navigate('/list-items')
        }).catch(error => {
            console.log(error)
        })
    }
  };

  const title = () => {
    if(serialNumber){
        return <h2 className='text-center'>Update Item</h2>
    }
    else{
        return <h2 className='text-center'>Add Item</h2>
    }
  }

  return (
    <div className='bg-dark min-h-screen py-4 bg-gray-900'>
      <div className='container mx-auto bg-gray-900'>
        <div className='flex justify-center'>
          <div className='w-full md:w-1/2 lg:w-1/3'>
            <h2 className="text-center text-2xl font-bold mb-4 text-white">{title()}</h2>
            <div className='bg-white rounded shadow p-6 mt-6'>
              <form>
                <div className='mb-4'>
                  <label className='block mb-2 text-sm font-bold text-gray-700'>Name<sup className='text-pink-200'>*</sup></label>
                  <input
                    type='text'
                    placeholder='Enter Item Name'
                    name='productName'
                    className='w-full p-2 border border-gray-300 rounded'
                    value={productName}
                    onChange={(e) => setProductName(e.target.value)}
                  />
                </div>
                <div className='mb-4'>
                  <label className='block mb-2 text-sm font-bold text-gray-700'>Warranty<sup className='text-pink-200'>*</sup></label>
                  <input
                    type='text'
                    placeholder='Enter Item Warranty'
                    name='warranty'
                    className='w-full p-2 border border-gray-300 rounded'
                    value={warranty}
                    onChange={(e) => setWarranty(e.target.value)}
                  />
                </div>
                <div className='mb-4'>
                  <label className='block mb-2 text-sm font-bold text-gray-700'>Bill Number<sup className='text-pink-200'>*</sup></label>
                  <input
                    type='email'
                    placeholder='Enter Bill Number'
                    name='billNumber'
                    className='w-full p-2 border border-gray-300 rounded'
                    value={billNumber}
                    onChange={(e) => setBillNumber(e.target.value)}
                  />
                </div>
                <div className='mb-4'>
                  <label className='block mb-2 text-sm font-bold text-gray-700'>Date<sup className='text-pink-200'>*</sup></label>
                  <input
                    type='date'
                    placeholder='Enter Date Of Purchase'
                    name='date'
                    className='w-full p-2 border border-gray-300 rounded'
                    value={date}
                    max={currentDate}
                    onChange={(e) => setDate(e.target.value)}
                  />
                </div>
                <div className='flex items-center justify-between'>
                  <button
                    type='submit'
                    className='mr-2 py-2 px-4 bg-blue-500 hover:bg-blue-600 focus:ring-blue-700 focus:ring-offset-blue-200 text-white w-full transition ease-in duration-200 text-center text-base font-semibold shadow-md focus:outline-none rounded'
                    onClick={saveItem}>
                    Submit
                  </button>
                  <Link
                    to='/list-items'
                    className='py-2 px-4 bg-gray-500 hover:bg-gray-600 focus:ring-gray-700 focus:ring-offset-gray-200 text-white w-full transition ease-in duration-200 text-center text-base font-semibold shadow-md focus:outline-none rounded'>
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

export default AddItem;