import React from 'react'
import frameImage from "../assets/frame.png"
import SignupForm from './SignupForm'
import LoginForm from './LoginForm'


const Template = ({title, image, formtype, setIsLoggedIn}) => {
    
    console.log("Form type");
    console.log(formtype)

  return (
    <div className='flex justify-between w-11/12 max-w-[1160px] py-5 mx-auto gap-x-12 gap-y-0 bg-richblack-900'>
        <div className='w-11/12 max-w-[450px]' >
            <h1 className='text-richblack-5 font-semibold text-[1.875rem] leading-[2.375rem]'>
                {title}
            </h1>
            {formtype === "signup" ? 
            (<SignupForm setIsLoggedIn={setIsLoggedIn}/>):
            (<LoginForm setIsLoggedIn={setIsLoggedIn}/>)}
        </div>

        <div className='relative w-11/12 max-w-[450px]'>
            <img src={frameImage}
                alt="Pattern"
                width={558}
                height={504}
                loading="lazy"/>
                
            <img src={image}
                alt="Students"
                width={558}
                height={490}
                loading="lazy"
                className='absolute -top-4 right-4'
                />    
        </div>
    </div>
  )
}

export default Template;