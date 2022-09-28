import React, { useEffect, useState } from 'react';
import { Grid } from '@mui/material';
import './ProductReviews.css';
import Reviews from '../../models/Reviews';
import axios from 'axios';
import Product from '../../models/Product';
import { Link } from 'react-router-dom';

interface productProps {
  product: Product
}

export function ProductReviews(props: productProps) {

  let id:number = props.product.id;
  const apiurl='http://localhost:8080/api/review/new';

  const [reviews, setReviews]=useState<Reviews[]>([])
  useEffect (()=>{
      loadReviews();
     
  }, []);

  const loadReviews=async()=>{
    const result =await axios.get("http://localhost:8080/api/review/name/"+id)
    console.log(result.data);
    setReviews(result.data);
 };

 const[users, setUsers] = useState({email : "", firstName : "", lastName : ""})

 useEffect(() => {
  loadUser();
}, []);

 const loadUser = async () => {

  var axios = require('axios');

  var config = {
      method: 'post',
      url: 'http://localhost:8080/auth/userprofile',   
      withCredentials:true
    };
    
    axios(config)
    .then(function (response: any) {
        setUsers(response.data);
        console.log(response.data);
        setNewreview({...newreview,user:users.firstName});
    })
    .catch(function (error: any) {
      console.log(error);
    });    
};

const [newreview, setNewreview]=useState({
  score:0,
  text:'',
  user:users.firstName,
  product:id
});

console.log(newreview);
const{score, text, user, product}=newreview
const onInputChange=(e:any)=>{
  setNewreview({...newreview,[e.target.name]:e.target.value});
};


const onSubmit=async(e:any)=>{ 
  e.preventDefault()
  console.log(newreview);
  createPost();
};

const createPost=()=>{
  var axios = require('axios');
  var data = newreview;
  var config = {
          method: 'post',
          url: apiurl,
          headers: { 
                  'Content-Type': 'application/json'
          },
          withCredentials:true,
          data : data
  };

  axios(config)
  .then(function (response:any) {
          console.log(JSON.stringify(response.data));
  })
  .catch(function (error:any) {
          console.log(error);
  });
}

const handleChange=(e:any)=>{
  setNewreview({...newreview,score:e.target.value});
};

  return (
    <div className="main_container">
       <div className='row'>
                <div className='col'>

                    <h2 className='text-center m-4'>Review this Product</h2>
                    <form onSubmit={(e)=>onSubmit(e)}>
                    <div className='mb-3'>
                        <label htmlFor='score' className='form-label'>
                            Rate this Product:
                        </label>
                        <select className='form-select' value={score} onChange={(e)=> handleChange(e)}>
                          <option value={1}>1</option>
                          <option value={2}>2</option>
                          <option value={3}>3</option>
                          <option value={4}>4</option> 
                          <option value={5}>5</option> 
                        </select>    
                      
                    </div>
                    <div className='mb-4'>
                        <label htmlFor='yourreview' className='form-label'>
                            Comments:
                        </label>
                        <input
                            type={"text"}
                            className="form-control2"
                            placeholder='Comments'
                            name="text"
                            value={text}
                            onChange={(e)=> onInputChange(e)}
                        />
                        
                    </div>
               
                    <br/>
                <button type='submit' className='btn btn-outline-primary mx-2'>Submit</button>
                <Link type='submit' className='btn btn-outline-danger mx-2' to="/">Cancel</Link>  
                  </form>
                </div>
            </div>

    <table className = 'rtable'>
      <thead className='rhead'>
        <tr>
          <th scope="col">Score</th>
          <th scope="col">User</th>
          <th scope="col">Comment</th>
        </tr>
      </thead>
    <tbody className='rbody'>
    {reviews.map((review, index) => (
    <tr>
      <td>{review.score}</td>
      <td>{review.user}</td>
      <td>{review.text}</td>
            
    </tr> 
    ))
}
  </tbody>
  </table>
</div>
  )
}

export default ProductReviews;


