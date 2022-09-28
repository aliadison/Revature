import { CartContext } from "../../context/cart.context";
import { useContext } from "react";
import { ShoppingCartOutlined } from "@material-ui/icons";
import Product from '../../models/Product';

interface productProps {
    product: Product,
    key: number
}

export const ViewCart = (props: productProps) => {
const { cart, setCart } = useContext(CartContext);
const addItemToCart = (product: Product) => {

    const newCart = [...cart]
    const index = newCart.findIndex((searchProduct) => {
      return searchProduct.id === product.id
    })

    if (index === -1) newCart.push(product)
    else newCart[index].quantity += product.quantity

    setCart(newCart)

    
}
 
  return (
    <ShoppingCartOutlined onClick={() => {addItemToCart({...props.product, quantity: 1})}} />
  )
}