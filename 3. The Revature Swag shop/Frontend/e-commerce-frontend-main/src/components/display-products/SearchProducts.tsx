import React from "react";
import Product from "../../models/Product";
import { ProductCard } from "./ProductCard";

interface searchProps{
    products:Array<Product>,
    query: String
}

export const SearchName=(props:searchProps)=>{
    var fproducts = [];

    if(props.query.length>0){
        fproducts=props.products.filter(
            (product)=>product.name.toLowerCase().includes(props.query.toLowerCase().trim()))
    } else {
        fproducts=props.products
    }
    if(fproducts.length<1 && props.query.length>0){
        return(
            <React.Fragment>
                <p>No Products Match Your Search</p>
            </React.Fragment>
        )
    }
    return(
        <React.Fragment>
            {fproducts.map((item)=>(
                <ProductCard product={item} key={item.id} />
            ))}
        </React.Fragment>
    )
}
