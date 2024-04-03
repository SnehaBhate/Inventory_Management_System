import { myAxios } from "./helper";
import axios from "axios";

//REST API
const EMPLOYEE_BASE_REST_API_URL = 'http://localhost:9090/api/';

export const signUp = (user) => {
    return myAxios.post('/api/Register', user).then((response) => response.data);
}

export const contact = (data) => {
    return axios.post(EMPLOYEE_BASE_REST_API_URL + 'saveContact', data, {
        header : {
                'Content-Type' : 'application/json'
            }
        }
    ).then((response) => response.data);
}

export const login = (data) => {
    return axios.post(EMPLOYEE_BASE_REST_API_URL + 'login', data , {
        header : {
            'Content-Type' : 'application/json'
        }
    }).then((response) => response.data);
}