import React from 'react'
import { Link } from 'react-router-dom'

const Home = ({isLoggedIn}) => {
  return (
    <div className="flex flex-col items-center justify-center h-screen bg-gray-900 text-white">
    <Link to="/">
      <img
        src="https://d2nir1j4sou8ez.cloudfront.net/wp-content/uploads/2022/01/inventory-management-system.png"
        alt="Logo"
        className="w-64 h-64 mb-6 rounded-full"
      />
    </Link>
    <h1 className="text-4xl font-bold mb-6 text-center text-richblack-200 font-serif">Optimize Your Inventory with Ease</h1>

    {/* <h1 className="text-4xl font-bold mb-6 text-center text-richblack-200">Optimize Your Inventory with Ease</h1> */}
    {isLoggedIn && <p className =" text-richblack-200">Welcome, User!</p>}
  </div>
  )
}

export default Home;