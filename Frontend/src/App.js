import { Route, Routes } from "react-router-dom";
import "./App.css";
import { useState } from 'react';
import Home from "./pages/Home";
import Login from "./pages/Login";
import About from "./pages/About";
import Signup from "./pages/Signup";
import AssignedItems from "./pages/AssignedItems";
import AdminDashboard from "./pages/AdminDashboard";
import UnassignedItems from "./pages/UnassignedItems";
import EmployeeDashboard from "./pages/EmployeeDashboard";
import Assign from "./components/Assign";
import Navbar from "./components/Navbar";
import AddItem from "./components/AddItem";
import ListItems from "./components/ListItems";
import AddEmployee from "./components/AddEmployee";
import ContactForm from "./components/ContactForm";
import PrivateRoute from "./components/PrivateRoute";
import ListEmployee from "./components/ListEmployee";

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);

  return (
    <div className="w-screen h-screen bg-richblack-900 flex flex-col">
      <Navbar isLoggedIn={isLoggedIn} setIsLoggedIn={setIsLoggedIn}/>
      <Routes>

        <Route path="/" element= {<Home isLoggedIn={isLoggedIn}/>} />
        <Route path="/about" element= {<About/>} />
        <Route path="/contact" element= {<ContactForm/>} />
        <Route path="/signup" element={<Signup/>} />
        <Route path="/login" element={<Login setIsLoggedIn={setIsLoggedIn}/>} />
        

        <Route path="/admindashboard" element = {
          <PrivateRoute isLoggedIn={isLoggedIn}>
              <AdminDashboard/>
          </PrivateRoute>
        } />
        
        <Route path="/employeedashboard" element = {
          <PrivateRoute isLoggedIn={isLoggedIn} >
              <EmployeeDashboard/>
          </PrivateRoute>
        } /> 

        <Route path="/add-employee" element = {
          <PrivateRoute isLoggedIn={isLoggedIn}>
              <AddEmployee/>
          </PrivateRoute>
        } /> 

        <Route path="/list-employees" element = {
          <PrivateRoute isLoggedIn={isLoggedIn}>
              <ListEmployee/>
          </PrivateRoute>
        } />

        {/* Item Routes  */}
        <Route path="/list-items" element = {
          <PrivateRoute isLoggedIn={isLoggedIn}>
              <ListItems/>
          </PrivateRoute>
        } />

        <Route path="/add-item" element = {
          <PrivateRoute isLoggedIn={isLoggedIn}>
              <AddItem/>
          </PrivateRoute>
        } />

        <Route path="/update-item/:serialNumber" element = {
          <PrivateRoute isLoggedIn={isLoggedIn}>
              <AddItem/>
          </PrivateRoute>
        } /> 

        <Route path="/list-assigned-items" element = {
          <PrivateRoute isLoggedIn={isLoggedIn}>
              <AssignedItems/>
          </PrivateRoute>
        } />


        <Route path="/assign-item/:serialNumber" element = {
          <PrivateRoute isLoggedIn={isLoggedIn}>
              <Assign/>
          </PrivateRoute>
        } />

        <Route path="/list-unassigned-items" element = {
          <PrivateRoute isLoggedIn={isLoggedIn}>
              <UnassignedItems/>
          </PrivateRoute>
        } />  

      </Routes> 
    </div>
  )
}

export default App;