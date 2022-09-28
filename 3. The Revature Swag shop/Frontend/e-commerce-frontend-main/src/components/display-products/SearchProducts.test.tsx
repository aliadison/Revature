import React from "react";
import { SearchName } from "./SearchProducts";
import { ProductCard } from "./ProductCard";
import { cleanup,} from "@testing-library/react";

afterEach(cleanup);

const testArray = [
        {
          "id": 1,
          "quantity": 10,
          "price": 20,
          "description": "A nice pair of headphones",
          "image": "https://i.insider.com/54eb437f6bb3f7697f85da71?width=1000&format=jpeg&auto=webp",
          "name": "Headphones",
          "reviewavg":4
        },
        {
          "id": 2,
          "quantity": 5,
          "price": 45,
          "description": "A nice TeeShirt",
          "image": "https://d3o2e4jr3mxnm3.cloudfront.net/Mens-Jake-Guitar-Vintage-Crusher-Tee_68382_1_lg.png",
          "name": "TeeShirt",
          "reviewavg":4
        },
        {
          "id": 3,
          "quantity": 20,
          "price": 2.5,
          "description": "A reusable shopping bag",
          "image": "https://images.ctfassets.net/5gvckmvm9289/3BlDoZxSSjqAvv1jBJP7TH/65f9a95484117730ace42abf64e89572/Noissue-x-Creatsy-Tote-Bag-Mockup-Bundle-_4_-2.png",
          "name": "Shopping Bag",
          "reviewavg":4
        },
        {
          "id": 4,
          "quantity": 20,
          "price": 10,
          "description": "A fancy cap for a fancy person",
          "image": "https://d3o2e4jr3mxnm3.cloudfront.net/Rocket-Vintage-Chill-Cap_66374_1_lg.png",
          "name": "Baseball Cap",
          "reviewavg":4
        },
        {
          "id": 5,
          "quantity": 3,
          "price": 80,
          "description": "A nice coat",
          "image": "https://www.pngarts.com/files/3/Women-Jacket-PNG-High-Quality-Image.png",
          "name": "Coat",
          "reviewavg":4
        }
      ];
const headphoneArray = [{
    "id": 1,
    "quantity": 10,
    "price": 20,
    "description": "A nice pair of headphones",
    "image": "https://i.insider.com/54eb437f6bb3f7697f85da71?width=1000&format=jpeg&auto=webp",
    "name": "Headphones",
    "reviewavg":4
  },];
const teeshirtArray = [{
  "id": 2,
  "quantity": 5,
  "price": 45,
  "description": "A nice TeeShirt",
  "image": "https://d3o2e4jr3mxnm3.cloudfront.net/Mens-Jake-Guitar-Vintage-Crusher-Tee_68382_1_lg.png",
  "name": "TeeShirt",
  "reviewavg":4
}];
const coatArray = [{
  "id": 5,
  "quantity": 3,
  "price": 80,
  "description": "A nice coat",
  "image": "https://www.pngarts.com/files/3/Women-Jacket-PNG-High-Quality-Image.png",
  "name": "Coat",
  "reviewavg":4
}]

test('blank query input', ()=>{
  const blankQuery ="";
  const singleSpaceQuery =" ";
  const quadrupleSpaceQuery ="    ";

  expect(
     SearchName({products:testArray, query:blankQuery})
  ).toEqual(
    <React.Fragment>
      {testArray.map((item)=>(
          <ProductCard product={item} key={item.id} />
      ))}
      </React.Fragment>)
  expect(
    SearchName({products:testArray, query:singleSpaceQuery})
 ).toEqual(
   <React.Fragment>
     {testArray.map((item)=>(
         <ProductCard product={item} key={item.id} />
     ))}
     </React.Fragment>)
 
 expect(
   SearchName({products:testArray, query:quadrupleSpaceQuery})
).toEqual(
  <React.Fragment>
    {testArray.map((item)=>(
        <ProductCard product={item} key={item.id} />
    ))}
    </React.Fragment>)
})

test('no match to query input', ()=>{
  const noMatchQuery="w";
  const blankNoMatchQuery=" w";
  const noMatchBlankQuery="w ";
  const blankNoMatchBlankQuery=" w ";
  //No product names in the test array contain w.
  expect(
    SearchName({products:testArray,query:noMatchQuery})
  ).toEqual(
    <React.Fragment>
        <p>No Products Match Your Search</p>
    </React.Fragment>
  )
  expect(
    SearchName({products:testArray,query:blankNoMatchQuery})
  ).toEqual(
    <React.Fragment>
        <p>No Products Match Your Search</p>
    </React.Fragment>
  )
  expect(
    SearchName({products:testArray,query:noMatchBlankQuery})
  ).toEqual(
    <React.Fragment>
        <p>No Products Match Your Search</p>
    </React.Fragment>
  )
  expect(
    SearchName({products:testArray,query:blankNoMatchBlankQuery})
  ).toEqual(
    <React.Fragment>
        <p>No Products Match Your Search</p>
    </React.Fragment>
  )
})

test('single result from exact query input', ()=>{
  const headphonesQuery = 'Headphones';
  const teeshirtQuery='TeeShirt';
  const coatQuery='Coat';
  const blankSingleQuery = '  Headphones  ';
  
  expect(
    SearchName({products:testArray,query:headphonesQuery})
  ).toEqual(
    <React.Fragment>
      {headphoneArray.map((item)=>(
          <ProductCard product={item} key={item.id} />
      ))}
    </React.Fragment>
  )
  expect(
    SearchName({products:testArray,query:teeshirtQuery})
  ).toEqual(
    <React.Fragment>
      {teeshirtArray.map((item)=>(
          <ProductCard product={item} key={item.id} />
      ))}
    </React.Fragment>
  )
  expect(
    SearchName({products:testArray,query:coatQuery})
  ).toEqual(
    <React.Fragment>
      {coatArray.map((item)=>(
          <ProductCard product={item} key={item.id} />
      ))}
    </React.Fragment>
  )
  expect(
    SearchName({products:testArray,query:blankSingleQuery})
  ).toEqual(
    <React.Fragment>
      {headphoneArray.map((item)=>(
          <ProductCard product={item} key={item.id} />
      ))}
    </React.Fragment>
  )
})