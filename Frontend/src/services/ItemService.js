import axios from "axios";
const API_URL = "http://localhost:9090/api";

class ProductService {
 saveProduct(item) {
   return axios.post(API_URL + "/save", item);
 }

 getAllProduct(){
   return axios.get(API_URL+"/getItems");
 }

 getProductById(serialNumber){
   return axios.get(API_URL+"/getItem/"+serialNumber);
 }

 deleteProduct(serialNumber){
   return axios.delete(API_URL+"/deleteItem/"+serialNumber);
 }

 editProduct(serialNumber, item){
   return axios.put(API_URL+"/updateItem/"+serialNumber, item);
 }
}
export default new ProductService();