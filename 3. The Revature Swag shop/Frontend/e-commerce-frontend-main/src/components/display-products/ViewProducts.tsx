import Navbar from '../navbar/Narbar';
import Product from '../../models/Product';
import React from 'react';
import { useLocation } from 'react-router-dom';
import './ViewProducts.css';
import { ViewCart } from './ViewCart';
import ProductReviews from '../ProductReviews/ProductReviews';


export type ProductDetailsProps = {} & React.HTMLAttributes<HTMLDivElement>

export const ViewProducts = () => { 
    const {state} = useLocation();
    const product = state as any as Product;
    console.log(state);

    var truncated = Math.floor(product.reviewavg * 10) / 10; 
    var truncated2 = product.price.toFixed(2); 

    return (
    <React.Fragment>
        <Navbar/>
        <div className='view'>
            <p className='view-img'>
                <img src={product.image}/>
            </p>
            <div className='view-flexboxright'>
            <p className='view-name'>
                {product.name}
                <br/>
            </p>
            <p className='view-text'>
                {product.description}
                <br/>
            </p>
            <p className='view-price'>
                <p>${truncated2}</p>
                <br/>
            </p>
            <p className='view-rating'>
                <p>Review Score:</p>
                <p>{truncated}/5</p>
                <br/>
            </p>
            <p className='view-cart'>
            <ViewCart product={product} key={product.id} />
            </p>
           
            </div>

        </div>
        <div className= 'previews'>
          <ProductReviews product={product} />
        </div>
        
    </React.Fragment>
    );
}
