import { TextField } from '@material-ui/core';
import React, { useEffect, useState } from 'react';
import styled from "styled-components";
import Product from '../../models/Product';
import { apiGetAllProducts } from '../../remote/e-commerce-api/productService';
import Navbar from '../navbar/Narbar';
import { SearchName } from './SearchProducts';


const Container = styled.div`
    padding: 20px;
    display: flex;
    flex-flow: grid;
    align-items: center;
    flex-wrap: wrap;
    justify-content: space-between;
    margin: 0 auto;
`;
const SearchBar = styled.input`
    width: 20%;
    font-size: 20pt;
`
const SearchBarSpace= styled.form`
    padding: 40px;
    margin-left: 40%;
`
const SearchBarLabel = styled.label`
    padding: 10px;
    font-size: 20pt;
`
export const DisplayProducts = () => {

  const [ products, setProducts] = useState<Product[]>([])
  const [query, setQuery]=useState("")


  useEffect(() => {
    const fetchData = async () => {
      const result = await apiGetAllProducts()
      setProducts(result.payload)
    }
    fetchData()
  }, [])
//const products: Product[] = [
    // {
    //     id:1,
    //     image:"https://d3o2e4jr3mxnm3.cloudfront.net/Mens-Jake-Guitar-Vintage-Crusher-Tee_68382_1_lg.png",
    //     name: 'Vintage Tee',
    //     description: 'An epic t-shirt for epic employees.',
    //     price: 5,
    //     quantity: 10,
    //   },
    //   {
    //     id:3,
    //     image:"https://www.prada.com/content/dam/pradanux_products/U/UCS/UCS319/1YOTF010O/UCS319_1YOT_F010O_S_182_SLF.png",
    //     name: 'Prada Polo',
    //     description: "The programing professional's go-to dress shirt.",
    //     price: 10,
    //     quantity: 10,
    //   },
    //   {
    //     id:4,
    //     image:"https://www.burdastyle.com/pub/media/catalog/product/cache/7bd3727382ce0a860b68816435d76e26/107/BUS-PAT-BURTE-1320516/1170x1470_BS_2016_05_132_front.png",
    //     name: 'Dress Code',
    //     description: 'The dress for every occassion. Casual and comfy for our coding coeds',
    //     price: 15,
    //     quantity: 10,
    //   },
    //   {
    //     id:5,
    //     image:"https://images.ctfassets.net/5gvckmvm9289/3BlDoZxSSjqAvv1jBJP7TH/65f9a95484117730ace42abf64e89572/Noissue-x-Creatsy-Tote-Bag-Mockup-Bundle-_4_-2.png",
    //     name: 'Tote Bloat',
    //     description: 'A stylish, expanding canvas bag to hold all your extra stuff.',
    //     price: 5,
    //     quantity: 10,
    //   },
    //   {
    //     id:6,
    //     image:"https://d3o2e4jr3mxnm3.cloudfront.net/Rocket-Vintage-Chill-Cap_66374_1_lg.png",
    //     name: 'ALL CAPS',
    //     description: 'The cap to beat all hats. The snug fit, contouring design is made to excite all users.',
    //     price: 5,
    //     quantity: 10,
    //   },
    //   {
    //     id:8,
    //     image:"https://www.pngarts.com/files/3/Women-Jacket-PNG-High-Quality-Image.png",
    //     name: 'The Java Jacket',
    //     description: 'Just like the object-oriented language we all love, its perfect for Spring!',
    //     price: 20,
    //     quantity: 10,
    //   },
 // ]


  return (
    <React.Fragment>
        <Navbar/>
        <SearchBarSpace>
          <SearchBarLabel>Search</SearchBarLabel>
          <SearchBar
            type="search"
            placeholder='Product Name'
            onChange={(e)=>setQuery(e.target.value)} />
        </SearchBarSpace>
        <Container>
          <SearchName products={products} query={query}/>
        </Container>
    </React.Fragment>
    
  );
};


