import React from "react";
import { render, unmountComponentAtNode } from "react-dom";
import { act } from "react-dom/test-utils";
import {ViewUser} from "./ProfileView";

let container = null;
beforeEach(() => {
  // setup a DOM element as a render target
  container = document.createElement("div");
  document.body.appendChild(container);
});

afterEach(() => {
  // cleanup on exiting
  unmountComponentAtNode(container);
  container.remove();
  container = null;
});

it("renders user data", async () => {
  const user = {    
    email: "",    
    firstName: "",    
    lastName: ""  };  

    jest.spyOn(global, "fetch").mockImplementation(() =>    
    Promise.resolve(   
       user    )  );


  // Use the asynchronous version of act to apply resolved promises
  await act(async () => {
    render(ViewUser(), container);
  });

  expect(container.querySelector("td").textContent).toBe(user.email);
  expect(container.querySelector("td").textContent).toBe(user.firstName);
  expect(container.querySelector("td").textContent).toBe(user.lastName);

//   remove the mock to ensure tests are completely isolated  
global.fetch.mockRestore();});



// test('regular input', ()=>{
//     const email="bob";
//     const firstName="bob";
//     const lastName="builder";

//     expect(
//         ViewUser{{email:fakeUser.email, firstName:fakeUser.firstName, lastName:fakeUser.lastName}}
//     )
// })