import React, { useEffect, useState } from 'react';
import Navbar from '../navbar/Narbar';
import "./styles.css";


export function ViewUser() {

    const[users, setUsers] = useState({email : "", firstName : "", lastName : ""})

    const[status, setStatus] = useState("")

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
            if (response.data == "") {
              setStatus ("it works")
            }
              
              setUsers(response.data);
              console.log(response.data);
          })
          .catch(function (error: any) {
            console.log(error);
          });    
      };

      if (status == "it works") return(
      <div>
      <div>
        <Navbar/>
        </div> 
        <h3 className='thead'>You need to be logged in</h3>
        </div>
        );
      return (
          <div>
            <Navbar/>
              <div>
                <p></p>
                  <div>
                    <h3 className="theader">User Profile</h3>
                      <table className="table">
                          <thead className="thead">
                              <tr className="trHead">
                                  <th className="th">Email</th>
                                  <th className="th">First Name</th>
                                  <th className="th">Last Name</th>
  
                              </tr>
                          </thead>
                          <tbody className="tbody">
    
                            <tr className="trBody">
                            <td className="td"> {users.email} </td>
                            <td className="td"> {users.firstName} </td>
                            <td className="td"> {users.lastName} </td>
                            </tr>

                          </tbody>
                      </table>
                  </div>
              </div>
          </div>
      );
  }


 // style={{width: "600px", border: "2px solid", borderBlockColor:"#000000", textAlign:"center", padding:"2em" }}